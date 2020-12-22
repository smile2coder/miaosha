package com.smile2coder.service.impl;

import com.smile2coder.dao.MOrderGoodsMapper;
import com.smile2coder.model.MOrderGoods;
import com.smile2coder.service.OrderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zxt
 **/
public class OrderGoodsServiceImpl implements OrderGoodsService {

    @Autowired
    private MOrderGoodsMapper orderGoodsMapper;

    @Override
    public int insert(MOrderGoods orderGoods) {
        return this.orderGoodsMapper.insert(orderGoods);
    }
}
