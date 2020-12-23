package com.smile2coder.service.impl;

import com.smile2coder.model.MGoods;

/**
 * @author zxt
 **/
public interface GoodsService {

    MGoods selectByGoodsId(Integer goodsId);

    /**
     * 减去库存 1
     * @param goodsId
     * @return
     */
    boolean decrStock(Integer goodsId);
}
