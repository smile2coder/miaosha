package com.smile2coder.dao;

import com.smile2coder.model.MOrder;

public interface MOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MOrder record);

    int insertSelective(MOrder record);

    MOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MOrder record);

    int updateByPrimaryKey(MOrder record);
}