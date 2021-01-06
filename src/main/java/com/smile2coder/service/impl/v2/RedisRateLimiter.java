package com.smile2coder.service.impl.v2;

import com.smile2coder.service.Limiter;
import lombok.AllArgsConstructor;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RedissonClient;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author zxt
 * @date 1/6/21
 * @desc
 */
@AllArgsConstructor
public class RedisRateLimiter implements Limiter {

    private static final String PREFIX = "limiter_";

    private RedissonClient redissonClient;

    @Override
    public boolean createInstance(Object identity, double permitsPerSecond) {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(getKey(identity));
        return rateLimiter != null;
    }

    @Override
    public boolean tryAcquire(Object identity) {
        return false;
    }

    @Override
    public boolean tryAcquire(Object identity, int permits) {
        return false;
    }

    @Override
    public void acquire(Object identity) {

    }

    @Override
    public void acquire(Object identity, int permits) {

    }

    @Override
    public boolean tryAcquire(Object identity, int timeout, TimeUnit unit) {
        return false;
    }

    @Override
    public boolean tryAcquire(Object identity, int permits, int timeout, TimeUnit unit) {
        return false;
    }

    private String getKey(Object identity) {
        if(Objects.isNull(identity)) {
            throw new IllegalArgumentException("identity is null");
        }
        return PREFIX.concat(String.valueOf(identity));
    }
}
