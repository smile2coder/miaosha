package com.smile2coder.service.impl.v2;

import com.smile2coder.dto.goods.GoodsStockDto;
import com.smile2coder.model.BaseModel;
import com.smile2coder.service.GoodsService;
import com.smile2coder.service.StockService;
import com.smile2coder.service.SwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * @author zxt
 * @date 1/6/21
 * @desc
 */
public class RedisStockServiceImpl implements StockService {

    private static final String PREFIX = "stock_";

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SwitchService switchService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void init() {
        List<GoodsStockDto> goodsStockDtos = this.goodsService.selectStockByStatus(BaseModel.STATUS_NORMAL);
        goodsStockDtos.forEach(goodsStockDto -> {
            this.switchService.setSwitch(goodsStockDto.getGoodsId(), true);
            this.redisTemplate.opsForValue().set(getKey(goodsStockDto.getGoodsId()), goodsStockDto.getStock());
        });
    }

    @Override
    public boolean decrStock(Integer goodsId) {
        Long decrement = redisTemplate.opsForValue().decrement(getKey(goodsId));
        return decrement >= 0;
    }

    public String getKey(Integer goodsId) {
        return String.format("%s%s", PREFIX, goodsId);
    }
}
