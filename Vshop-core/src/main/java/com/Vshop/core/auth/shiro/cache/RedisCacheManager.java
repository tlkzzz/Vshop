package com.Vshop.core.auth.shiro.cache;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.ShardedJedisPool;

import java.util.concurrent.ConcurrentMap;

/**
 * Created by yansheng on 2014/7/6.
 */
@Slf4j
public class RedisCacheManager implements CacheManager {
    @Autowired
    private ShardedJedisPool shardedJedisPool;

    private final ConcurrentMap<String, Cache> caches = Maps.newConcurrentMap();

    /**
     * The Redis key prefix for caches
     */
    @Setter@Getter
    private String keyPrefix = "shiro_redis_cache:";

    @Override
    public <K, V> Cache<K, V> getCache(String key) throws CacheException {
        log.debug("获取名称为: " + key + " 的RedisCache实例");
        Cache cache = caches.get(key);
        if (cache == null) {
            // create a new cache instance
            cache = new RedisCache<K, V>(shardedJedisPool.getResource(), keyPrefix);
            // add it to the cache collection
            caches.put(key, cache);
        }
        return cache;
    }
}
