package com.smile2coder.service;

import com.github.pagehelper.PageInfo;
import com.smile2coder.model.MGoods;

import java.util.List;

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

    PageInfo<MGoods> list(Integer page, Integer limit);

    int updateStatus(Integer goodsId, Byte statusFinsh);

    List<Integer> selectIdsByStatus(byte status);
}
