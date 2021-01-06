package com.smile2coder.service.impl.v2;

import com.smile2coder.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;
import java.util.UUID;

/**
 * @author zxt
 * @date 1/6/21
 * @desc
 */
public class RedisTokenServiceImpl implements TokenService {

    private static final String PREFIX = "token_";

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String generateToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Override
    public int saveToken(String token, Integer userId) {
        redisTemplate.opsForValue().set(getKey(token), userId);
        return 1;
    }

    @Override
    public Integer getUserId(String token) {
        Object o;
        return (o = redisTemplate.opsForValue().get(getKey(token))) == null? null:new Integer(o.toString());
    }

    @Override
    public boolean exist(String token) {
        return redisTemplate.hasKey(getKey(token));
    }

    @Override
    public boolean equal(String token, Integer userId) {
        Object o;
        return (o = redisTemplate.opsForValue().get(getKey(token))) == null? false:new Integer(o.toString()).compareTo(userId) == 0;
    }

    @Override
    public Integer remove(String token) {
        redisTemplate.delete(getKey(token));
        return 1;
    }

    private String getKey(String token) {
        if(Objects.isNull(token)) {
            throw new IllegalArgumentException("token is null");
        }
        return String.format("%s%s", PREFIX, token);
    }
}
