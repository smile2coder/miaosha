package com.smile2coder.service;

import java.util.concurrent.TimeUnit;

/**
 * @author zxt
 * @date 12/25/20
 * @desc 限流器
 */
public interface Limiter {

    double DEFAULT_PERMITS = 5;

    boolean createInstance(Object identity, double permitsPerSecond);

    boolean tryAcquire(Object identity);

    boolean tryAcquire(Object identity, int permits);

    void acquire(Object identity);

    void acquire(Object identity, int permits);

    boolean tryAcquire(Object identity, int timeout, TimeUnit unit);

    boolean tryAcquire(Object identity, int permits, int timeout, TimeUnit unit);
}
