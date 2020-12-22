package com.smile2coder.service;

/**
 * @author zxt
 * @date 12/22/20
 * @desc
 */
public interface TokenService {

    String generateToken();

    int saveToken(String token, Integer userId);

    Integer getUserId(String token);

    boolean exist(String token);

    boolean equal(String token, Integer userId);

    Integer remove(String token);
}
