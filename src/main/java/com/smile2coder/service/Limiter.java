package com.smile2coder.service;

/**
 * @author zxt
 * @date 12/25/20
 * @desc 限流器
 */
public interface Limiter<T> {

    T createInstance(Object identity, double permitsPerSecond);

    /**
     *
     * @param identity 身份
     * @param permitsPerSecond 速率
     * @return
     */
    boolean tryAcquire(Object identity, double permitsPerSecond);

}
