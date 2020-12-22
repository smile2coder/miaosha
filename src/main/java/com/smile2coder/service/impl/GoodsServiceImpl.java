package com.smile2coder.service.impl;

import com.smile2coder.dao.MGoodsMapper;
import com.smile2coder.model.MGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zxt
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private MGoodsMapper goodsMapper;

    @Override
    public MGoods selectByGoodsId(Integer goodsId) {
        return this.goodsMapper.selectByPrimaryKey(goodsId);
    }
}
