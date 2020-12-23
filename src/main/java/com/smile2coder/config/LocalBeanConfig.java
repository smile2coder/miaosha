package com.smile2coder.config;

import com.smile2coder.service.Access;
import com.smile2coder.service.SwitchService;
import com.smile2coder.service.TokenService;
import com.smile2coder.service.impl.CacheSwitchServiceImpl;
import com.smile2coder.service.impl.CacheTokenServiceImpl;
import com.smile2coder.service.impl.RandomAccess;
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
        return new RandomAccess(0.1f);
    }

    @Bean
    public SwitchService switchService() {
        return new CacheSwitchServiceImpl();
    }

    @Bean
    public TokenService tokenService() {
        return new CacheTokenServiceImpl();
    }
}
