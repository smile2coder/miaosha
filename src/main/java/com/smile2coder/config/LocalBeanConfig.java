package com.smile2coder.config;

import com.smile2coder.service.*;
import com.smile2coder.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zxt
 * @date 12/23/20
 * @desc
 */
@Configuration
public class LocalBeanConfig {

    @Bean
    public Access access() {
        return new RandomAccess(0.5f);
    }

    @Bean
    public SwitchService switchService() {
        return new CacheSwitchServiceImpl();
    }

    @Bean
    public TokenService tokenService() {
        return new CacheTokenServiceImpl();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl_v1();
    }

    @Bean
    public Limiter limiter() {
        return new DefaultRateLimiter();
    }
}
