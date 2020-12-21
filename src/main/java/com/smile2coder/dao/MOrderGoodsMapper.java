package com.smile2coder.dao;

import com.smile2coder.model.MOrderGoods;

public interface MOrderGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MOrderGoods record);

    int insertSelective(MOrderGoods record);

    MOrderGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MOrderGoods record);

    int updateByPrimaryKey(MOrderGoods record);
}