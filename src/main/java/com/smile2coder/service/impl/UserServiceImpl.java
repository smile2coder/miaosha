package com.smile2coder.service.impl;

import com.smile2coder.dao.MUserMapper;
import com.smile2coder.model.MUser;
import com.smile2coder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MUserMapper mUserMapper;

    @Override
    public MUser selectByUsername(String username) {
        return this.mUserMapper.selectByUsername(username);
    }

    @Override
    public MUser selectByUserId(Integer userId) {
        return this.mUserMapper.selectByPrimaryKey(userId);
    }
}
