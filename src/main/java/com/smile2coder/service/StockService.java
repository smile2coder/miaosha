package com.smile2coder.service;

/**
 * @author zxt
 * @date 1/6/21
 * @desc
 */
public interface StockService {

    void init();

    boolean decrStock(Integer goodsId);
}
