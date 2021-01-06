package com.smile2coder.service.impl.v1;

import com.google.common.util.concurrent.RateLimiter;
import com.smile2coder.service.Limiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zxt
 * @date 12/25/20
 * @desc 基于 Guava 实现的单机限流器
 */
public class DefaultRateLimiter implements Limiter<RateLimiter> {

    private static Map<Object, RateLimiter> limiters = new ConcurrentHashMap(256);

    public DefaultRateLimiter() {}

    @Override
    public RateLimiter createInstance(Object identity, double permitsPerSecond) {
        if(limiters.containsKey(identity)) {
            return limiters.get(identity);
        }
        limiters.putIfAbsent(identity, RateLimiter.create(permitsPerSecond));
        return limiters.get(identity);
    }

    @Override
    public boolean tryAcquire(Object identity, double permitsPerSecond) {
        return createInstance(identity, permitsPerSecond).tryAcquire();
    }

}
