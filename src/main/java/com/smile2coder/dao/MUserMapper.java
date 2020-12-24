package com.smile2coder.dao;

import com.smile2coder.model.MUser;
import org.springframework.stereotype.Repository;

@Repository
public interface MUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MUser record);

    int insertSelective(MUser record);

    MUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MUser record);

    int updateByPrimaryKey(MUser record);

    MUser selectByUsername(String username);

    int lockUser(Integer userId);
}