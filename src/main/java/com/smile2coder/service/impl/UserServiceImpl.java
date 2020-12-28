package com.smile2coder.service.impl;

import com.smile2coder.dao.MUserMapper;
import com.smile2coder.dto.login.LoginReqDto;
import com.smile2coder.dto.user.UserRegisterReqDto;
import com.smile2coder.exception.IncorrectCredentialsException;
import com.smile2coder.holder.TokenHolder;
import com.smile2coder.holder.UserHolder;
import com.smile2coder.model.BaseModel;
import com.smile2coder.model.MUser;
import com.smile2coder.service.TokenService;
import com.smile2coder.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
    @Transactional
    public String login(LoginReqDto loginReqDto) {
        MUser user = this.selectByUsername(loginReqDto.getUsername());
        if(user == null || !user.getPassword().equals(loginReqDto.getPassword())) {
            throw new IncorrectCredentialsException();
        }
        String token = tokenService.generateToken();
        tokenService.saveToken(token, user.getId());

        MUser userOfUpdate = new MUser();
        userOfUpdate.setId(user.getId());
        userOfUpdate.setLastLoginTime(new Date());
        userOfUpdate.setUpdateTime(new Date());
        this.updateByPrimaryKeySelective(userOfUpdate);
        return token;
    }

    @Override
    public Integer logout() {
        return tokenService.remove(TokenHolder.get());
    }

    @Override
    public boolean existByUsername(String username) {
        return this.selectByUsername(username) != null;
    }

    @Override
    public int lockUser(Integer userId) {
        return this.mUserMapper.lockUser(userId);
    }

    @Override
    public MUser detail() {
        return UserHolder.get();
    }

    @Override
    public Integer register(UserRegisterReqDto userRegisterReqDto) {
        MUser user = new MUser();
        BeanUtils.copyProperties(userRegisterReqDto, user);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setStatus(BaseModel.STATUS_NORMAL);
        this.mUserMapper.insertSelective(user);
        return user.getId();
    }

    @Override
    public int updateByPrimaryKeySelective(MUser record) {
        return this.mUserMapper.updateByPrimaryKeySelective(record);
    }
}
