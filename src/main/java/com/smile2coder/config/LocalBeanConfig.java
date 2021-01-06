package com.smile2coder.config;

import com.smile2coder.service.*;
import com.smile2coder.service.impl.*;
import com.smile2coder.service.impl.v1.CacheSwitchServiceImpl;
import com.smile2coder.service.impl.v1.CacheTokenServiceImpl;
import com.smile2coder.service.impl.v1.DefaultRateLimiter;
import com.smile2coder.service.impl.OrderServiceImpl;
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
        return new OrderServiceImpl();
    }

    @Bean
    public Limiter limiter() {
        return new DefaultRateLimiter();
    }
}
