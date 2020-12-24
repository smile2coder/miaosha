package com.smile2coder.service;

import com.smile2coder.dto.login.LoginReqDto;
import com.smile2coder.model.MUser;

public interface UserService {
    
    MUser selectByUsername(String username);
    
    MUser selectByUserId(Integer userId);

    MUser login(LoginReqDto loginReqDto);

    Integer logout();

    boolean existByUsername(String username);

    int lockUser(Integer userId);
}
