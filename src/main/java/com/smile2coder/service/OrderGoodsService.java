package com.smile2coder.service;

import com.smile2coder.model.MOrderGoods;

/**
 * @author zxt
 **/
public interface OrderGoodsService {

    int insert(MOrderGoods orderGoods);

    MOrderGoods selectByOrderId(Integer orderId);
}
