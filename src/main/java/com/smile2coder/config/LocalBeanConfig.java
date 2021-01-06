package com.smile2coder.config;

import com.smile2coder.service.*;
import com.smile2coder.service.impl.RandomAccess;
import com.smile2coder.service.impl.v2.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zxt
 * @date 12/23/20
 * @desc
 */
@Configuration
public class LocalBeanConfig {

    /*** v2 start ***/
    @Bean
    public Access access() {
        return new RandomAccess(0.5f);
    }

    @Bean
    public SwitchService switchService() {
        return new RedisSwitchServiceImpl();
    }

    @Bean
    public TokenService tokenService() {
        return new RedisTokenServiceImpl();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl_v2();
    }

    @Bean
    public Limiter limiter() {
        return new RedisRateLimiter();
    }

    @Bean
    public StockService stockService() {
        return new RedisStockServiceImpl();
    }
    /*** v2 end ***/
    /*** v1 start ***/
//    @Bean
//    public Access access() {
//        return new RandomAccess(0.5f);
//    }
//
//    @Bean
//    public SwitchService switchService() {
//        return new CacheSwitchServiceImpl();
//    }
//
//    @Bean
//    public TokenService tokenService() {
//        return new CacheTokenServiceImpl();
//    }
//
//    @Bean
//    public OrderService orderService() {
//        return new OrderServiceImpl_v1();
//    }
//
//    @Bean
//    public Limiter limiter() {
//        return new DefaultRateLimiter();
//    }
//
//    @Bean
//    public StockService stockService() {
//        return new DefaultStockServiceImpl();
//    }
    /*** v1 end ***/
}
