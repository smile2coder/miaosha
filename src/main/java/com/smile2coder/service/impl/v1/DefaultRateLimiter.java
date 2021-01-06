package com.smile2coder.service.impl.v1;

import com.google.common.util.concurrent.RateLimiter;
import com.smile2coder.service.Limiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author zxt
 * @date 12/25/20
 * @desc 基于 Guava 实现的单机限流器
 */
public class DefaultRateLimiter implements Limiter {

    private static Map<Object, RateLimiter> limiters = new ConcurrentHashMap(256);

    private double permitsPerSecond;

    public DefaultRateLimiter() {
        this.permitsPerSecond = DEFAULT_PERMITS;
    }

    public DefaultRateLimiter(int permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
    }

    private RateLimiter getLimiter(Object identity) {
        if(limiters.containsKey(identity)) {
            return limiters.get(identity);
        }
        RateLimiter limiter = limiters.putIfAbsent(identity, RateLimiter.create(permitsPerSecond));
        return limiter;
    }

    @Override
    public boolean createInstance(Object identity, double permitsPerSecond) {
        if(limiters.containsKey(identity)) {
            return true;
        }
        limiters.putIfAbsent(identity, RateLimiter.create(permitsPerSecond));
        return true;
    }

    @Override
    public boolean tryAcquire(Object identity) {
        return getLimiter(identity).tryAcquire();
    }

    @Override
    public boolean tryAcquire(Object identity, int permits) {
        return getLimiter(identity).tryAcquire(permits);
    }

    @Override
    public void acquire(Object identity) {
        getLimiter(identity).acquire();
    }

    @Override
    public void acquire(Object identity, int permits) {
        getLimiter(identity).acquire(permits);
    }

    @Override
    public boolean tryAcquire(Object identity, int timeout, TimeUnit unit) {
        return getLimiter(identity).tryAcquire(timeout, unit);
    }

    @Override
    public boolean tryAcquire(Object identity, int permits, int timeout, TimeUnit unit) {
        return getLimiter(identity).tryAcquire(permits, timeout, unit);
    }
}
