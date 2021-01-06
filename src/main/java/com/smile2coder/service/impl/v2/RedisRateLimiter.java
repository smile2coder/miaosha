package com.smile2coder.service.impl.v2;

import com.smile2coder.service.Limiter;
import lombok.AllArgsConstructor;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * @author zxt
 * @date 1/6/21
 * @desc
 */
@AllArgsConstructor
public class RedisRateLimiter implements Limiter<RRateLimiter> {

    private static final String PREFIX = "limiter_";

    @Autowired
    private RedissonClient redissonClient;

    private String getKey(Object identity) {
        if(Objects.isNull(identity)) {
            throw new IllegalArgumentException("identity is null");
        }
        return PREFIX.concat(String.valueOf(identity));
    }

    @Override
    public RRateLimiter createInstance(Object identity, double permitsPerSecond) {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(getKey(identity));
        rateLimiter.trySetRate(RateType.OVERALL, (int)permitsPerSecond, 1, RateIntervalUnit.SECONDS);
        return rateLimiter;
    }

    @Override
    public boolean tryAcquire(Object identity, double permitsPerSecond) {
        return createInstance(identity, permitsPerSecond).tryAcquire();
    }
}
