package com.ems.config.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @program: ems-vue3
 * Comentario en espanol
 * @author: starao
 * @create: 2023-07-07 22:34
 **/
@Component
public class CacheConfig {

    private Cache<String, String> cache;

    /**
     * Comentario en espanol
     * @param key
     * @param value
     * @param expireTime
     */
    public void put(String key, String value, int expireTime) {
        cache = CacheBuilder.newBuilder().expireAfterWrite(expireTime, TimeUnit.MINUTES).build();
        cache.put(key, value);
    }

    /**
     * Comentario en espanol
     * @param key
     * @return
     */
    public String get(String key) {
        return cache.getIfPresent(key);
    }

    /**
     * Comentario en espanol
     * @param key
     */
    public void invalidate(String key){
        cache.invalidate(key);
    }

    /**
     * Comentario en espanol
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void clearExpired() {
        if (cache != null){
            cache.cleanUp();
        }
    }
}
