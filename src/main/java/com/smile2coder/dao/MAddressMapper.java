package com.smile2coder.dao;

import com.smile2coder.model.MAddress;

public interface MAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MAddress record);

    int insertSelective(MAddress record);

    MAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MAddress record);

    int updateByPrimaryKey(MAddress record);
}