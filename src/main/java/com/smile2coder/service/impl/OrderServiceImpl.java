package com.smile2coder.service.impl;

import com.smile2coder.dao.MOrderMapper;
import com.smile2coder.dto.order.OrderReqDto;
import com.smile2coder.exception.CommonException;
import com.smile2coder.holder.UserHolder;
import com.smile2coder.model.*;
import com.smile2coder.service.Access;
import com.smile2coder.service.OrderGoodsService;
import com.smile2coder.service.OrderService;
import com.smile2coder.service.SwitchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author zxt
 * @date 12/22/20
 * @desc
 */
public class OrderServiceImpl implements OrderService {

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean order(OrderReqDto orderReqDto) {
        MGoods goods = this.goodsService.selectByGoodsId(orderReqDto.getGoodsId());
        if (!checkGoods(goods)) {
            throw new CommonException("秒杀活动已结束");
        }
        MUser user = UserHolder.get();
        int count = this.selectCountByUserIdAndGoodsId(user.getId(), orderReqDto.getGoodsId());
        if (count > 0) {
            throw new CommonException("您已经参加过本次秒杀活动");
        }
        // 根据一定的算法排除一些用户
        if (this.access.access(goods.getId())) {
            throw new CommonException("秒杀活动已结束");
        }
        // 减去库存
        if (!this.goodsService.decrStock(goods.getId())) {
            switchService.setSwitch(goods.getId(), false);
            throw new CommonException("秒杀活动已结束");
        }

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
        return true;
    }

    @Override
    public int selectCountByUserIdAndGoodsId(Integer userId, Integer goodsId) {
        return this.orderMapper.selectCountByUserIdAndGoodsId(userId, goodsId);
    }

    private boolean checkGoods(MGoods goods) {
        boolean isOpen = switchService.isOpen(goods.getId(), false);
        if (!isOpen) {
            return false;
        }
        Date now = new Date();
        if(now.before(goods.getStartTime()) || now.after(goods.getEndTime())) {
            return false;
        }
        return true;
    }
}
