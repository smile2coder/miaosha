package com.smile2coder.service.impl;

import com.smile2coder.dao.MUserMapper;
import com.smile2coder.dto.login.LoginReqDto;
import com.smile2coder.exception.IncorrectCredentialsException;
import com.smile2coder.holder.TokenHolder;
import com.smile2coder.model.MUser;
import com.smile2coder.service.TokenService;
import com.smile2coder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MUserMapper mUserMapper;
    @Autowired
    private TokenService tokenService;

    @Override
    public MUser selectByUsername(String username) {
        return this.mUserMapper.selectByUsername(username);
    }

    @Override
    public MUser selectByUserId(Integer userId) {
        return this.mUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public MUser login(LoginReqDto loginReqDto) {
        MUser user = this.selectByUsername(loginReqDto.getUsername());
        if(user == null || !user.getPassword().equals(loginReqDto.getPassword())) {
            throw new IncorrectCredentialsException();
        }
        String token = tokenService.generateToken();
        tokenService.saveToken(token, user.getId());

        user.setPassword("");
        return user;
    }

    @Override
    public Integer logout() {
        return tokenService.remove(TokenHolder.get());
    }

    @Override
    public boolean existByUsername(String username) {
        return this.selectByUsername(username) == null;
    }
}
