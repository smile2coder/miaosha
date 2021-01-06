package com.smile2coder.service.impl.v2;

import com.smile2coder.service.SwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

/**
 * @author zxt
 * @date 1/5/21
 * @desc
 */
public class RedisSwitchServiceImpl implements SwitchService {

    private static final String PREFIX = "switch_";

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean isOpen(Object o, Boolean defaultValue) {
        Object result;
        return (result = redisTemplate.opsForValue().get(getKey(o))) == null? defaultValue: Boolean.valueOf(result.toString());
    }

    @Override
    public void setSwitch(Object o, Boolean b) {
        redisTemplate.opsForValue().set(getKey(o), b);
    }

    private String getKey(Object o) {
        if(Objects.isNull(o)) {
            throw new IllegalArgumentException("o is null");
        }
        return PREFIX.concat(String.valueOf(o));
    }
}
