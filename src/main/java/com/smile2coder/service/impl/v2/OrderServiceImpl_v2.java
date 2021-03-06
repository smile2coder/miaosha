package com.smile2coder.service.impl.v2;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smile2coder.dao.MOrderMapper;
import com.smile2coder.dto.order.OrderDetailRespDto;
import com.smile2coder.dto.order.OrderReqDto;
import com.smile2coder.exception.CommonException;
import com.smile2coder.exception.GoodsFinshException;
import com.smile2coder.exception.JoinUserToManyException;
import com.smile2coder.exception.RepeatJoinException;
import com.smile2coder.holder.UserHolder;
import com.smile2coder.model.*;
import com.smile2coder.service.*;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zxt
 * @date 12/22/20
 * @desc 基于数据库的实现，适用于单机部署
 */
@SuppressWarnings("ALL")
public class OrderServiceImpl_v2 implements OrderService {

    private static ThreadPoolExecutor executor
            = new ThreadPoolExecutor(16, 32, 60*60,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(128), new ThreadPoolExecutor.CallerRunsPolicy());

    @Autowired
    private MOrderMapper orderMapper;
    @Autowired
    private SwitchService switchService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderGoodsService orderGoodsService;
    @Autowired
    private Access access;
    @Autowired
    private UserService userService;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private StockService stockService;

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = GoodsFinshException.class)
    public Integer order(OrderReqDto orderReqDto) {
        MGoods goods = this.goodsService.selectByGoodsId(orderReqDto.getGoodsId());
        // 查看当前商品是否开始秒杀或者结束秒杀
        if (!checkGoods(goods)) {
            throw new GoodsFinshException();
        }

        MUser user = UserHolder.get();
        // 检查是否已经成功秒杀过。这里有一个问题，就是如果检查没有秒杀过，可能有多个请求通过，导致一个用户秒杀多个商品
        // 解决方案：给当前用户加个锁
        RLock lock = redissonClient.getLock(String.format("lock_%s_%s", goods.getId(), user.getId()));
        if (!lock.tryLock()) {
            throw new JoinUserToManyException();
        }
        boolean success = this.isSuccess(user.getId(), orderReqDto.getGoodsId());
        if (success) {
            throw new RepeatJoinException();
        }

        // 根据一定的算法排除一些用户
        if (!this.access.access(user.getId())) {
            throw new JoinUserToManyException();
        }

        // 减去库存
        if (!this.stockService.decrStock(goods.getId())) {
            goodsFinsh(goods.getId());
            throw new GoodsFinshException();
        }

        // 生成订单，这一步可以异步处理，但是结果要异步通知
        // （或者另一种思路，不用异步通知，因为到这一步，秒杀肯定是成功了，订单是会创建成功的，所以可以根据用户ID和商品ID可以查到这一单）
        executor.execute(new Runnable() {
            @Override
            public void run() {
                createOrder(orderReqDto, goods, user);
            }
        });
        return null;
    }

    /**
     * 活动结束
     * @param goodsId
     */
    private void goodsFinsh(Integer goodsId) {
        switchService.setSwitch(goodsId, false);
        this.goodsService.updateStatus(goodsId, MGoods.STATUS_FINSH);
    }

    private Integer createOrder(OrderReqDto orderReqDto, MGoods goods, MUser user) {
        MOrder order = new MOrder();
        BeanUtils.copyProperties(orderReqDto, order);
        order.setActualPrice(goods.getActualPrice());
        order.setPayPrice(goods.getPayPrice());
        order.setUserId(user.getId());
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        order.setStatus(BaseModel.STATUS_NORMAL);
        this.orderMapper.insertSelective(order);

        MOrderGoods orderGoods = new MOrderGoods();
        orderGoods.setActualPrice(goods.getActualPrice());
        orderGoods.setPayPrice(goods.getPayPrice());
        orderGoods.setCount(orderReqDto.getCount());
        orderGoods.setGoodsId(goods.getId());
        orderGoods.setGoodsName(goods.getName());
        orderGoods.setOrderId(order.getId());
        orderGoods.setCreateTime(new Date());
        orderGoods.setUpdateTime(new Date());
        orderGoods.setStatus(BaseModel.STATUS_NORMAL);
        this.orderGoodsService.insert(orderGoods);
        return order.getId();
    }

    @Override
    public int selectCountByUserIdAndGoodsId(Integer userId, Integer goodsId) {
        return this.orderMapper.selectCountByUserIdAndGoodsId(userId, goodsId);
    }

    @Override
    public OrderDetailRespDto detail(Integer orderId) {
        MOrder order = this.selectByPrimaryKey(orderId);
        if (order == null) {
            throw new CommonException("订单不存在，订单id【%s】", orderId);
        }
        MOrderGoods orderGoods = this.orderGoodsService.selectByOrderId(orderId);
        // 拼装
        OrderDetailRespDto result = new OrderDetailRespDto();
        BeanUtils.copyProperties(order, result);
        result.setGoodsName(orderGoods.getGoodsName());
        result.setGoodsActualPrice(orderGoods.getActualPrice());
        result.setGoodsPayPrice(orderGoods.getPayPrice());
        result.setGoodsCount(orderGoods.getCount());
        return result;
    }

    @Override
    public MOrder selectByPrimaryKey(Integer id) {
        return this.orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean isSuccess(Integer goodsId) {
        Integer userId = UserHolder.get().getId();
        return isSuccess(userId, goodsId);
    }

    @Override
    public boolean isSuccess(Integer userId, Integer goodsId) {
        int count = this.selectCountByUserIdAndGoodsId(userId, goodsId);
        return count > 0;
    }

    @Override
    public PageInfo page(Integer page, Integer limit) {
        MUser user = UserHolder.get();
        PageHelper.startPage(page, limit);
        List<MOrder> list = this.orderMapper.list(user.getId());
        return new PageInfo(list);
    }

    private boolean checkGoods(MGoods goods) {
        boolean isOpen = switchService.isOpen(goods.getId(), false);
        if (!isOpen) {
            return false;
        }
        Date now = new Date();
        if(now.before(goods.getStartTime())
                || now.after(goods.getEndTime())
                || goods.getStatus().shortValue() == MGoods.STATUS_FINSH) {
            switchService.setSwitch(goods.getId(), false);
            return false;
        }
        return true;
    }
}
