package com.smile2coder.dao;

import com.smile2coder.model.MGoods;

public interface MGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MGoods record);

    int insertSelective(MGoods record);

    MGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MGoods record);

    int updateByPrimaryKey(MGoods record);
}