package com.smile2coder.service;

import com.smile2coder.model.MUser;

public interface UserService {
    
    MUser selectByUsername(String username);
    
    MUser selectByUserId(Integer userId);
}
