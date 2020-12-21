package com.smile2coder.dao;

import com.smile2coder.model.MUser;

public interface MUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MUser record);

    int insertSelective(MUser record);

    MUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MUser record);

    int updateByPrimaryKey(MUser record);
}