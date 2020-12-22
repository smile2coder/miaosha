package com.smile2coder.service.impl;

import com.smile2coder.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zxt
 * @date 12/22/20
 * @desc 基于本地缓存的 token 缓存策略
 *
 * 一个 Node 节点占用的内存大概为 4(hash)+4(key的引用)+4(value的引用)+4(next的引用)=16 字节
 * 一个 Integer 占用 16 字节
 * 一个 String（UUID）占用 16 字节
 * 10000000 * (16+16+16)B = 480MB
 */
@Service
public class CacheTokenServiceImpl implements TokenService {

    private static Map<String, Integer> cache = new ConcurrentHashMap<>();

    @Override
    public String generateToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Override
    public int saveToken(String token, Integer userId) {
        cache.put(token, userId);
        return 1;
    }

    @Override
    public Integer getUserId(String token) {
        return cache.get(token);
    }

    @Override
    public boolean exist(String token) {
        return cache.containsKey(token);
    }

    @Override
    public boolean equal(String token, Integer userId) {
        Integer cacheUserId;
        return (cacheUserId = cache.get(token)) == null? false: cacheUserId.equals(userId);
    }

    @Override
    public Integer remove(String token) {
        return cache.remove(token);
    }

}
