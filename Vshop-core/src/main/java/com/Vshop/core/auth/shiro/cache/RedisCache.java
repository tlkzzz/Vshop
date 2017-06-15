package com.Vshop.core.auth.shiro.cache;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.Vshop.core.codec.util.SerializeUtil;

import lombok.Getter;
import lombok.Setter;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.ShardedJedis;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class RedisCache<K, V> implements Cache<K, V> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
		
	/**
     * The wrapped Jedis instance.
     */
	private ShardedJedis cache;
	
	/**
	 * The Redis key prefix for the sessions 
	 */
    @Setter@Getter
	private String keyPrefix = "shiro_redis_session:";
	
	/**
	 * 通过一个JedisManager实例构造RedisCache
	 */
	public RedisCache(ShardedJedis cache){
		 if (cache == null) {
	         throw new IllegalArgumentException("Cache argument cannot be null.");
	     }
	     this.cache = cache;
	}
	
	/**
	 * Constructs a cache instance with the specified
	 * Redis manager and using a custom key prefix.
	 * @param cache The cache manager instance
	 * @param prefix The Redis key prefix
	 */
	public RedisCache(ShardedJedis cache, String prefix){
		this( cache );
		this.keyPrefix = prefix;
	}
	
	/**
	 * 获得byte[]型的key
	 * @param key
	 * @return
	 */
	private byte[] getByteKey(K key){
		if(key instanceof String){
			String preKey = this.keyPrefix + key;
    		return preKey.getBytes();
    	}else{
    		return SerializeUtil.serialize(key);
    	}
	}
 	
	@Override
	public V get(K key) throws CacheException {
		logger.debug("根据key从Redis中获取对象 key [" + key + "]");
		try {
			if (key == null) {
	            return null;
	        }else{
	        	byte[] rawValue = cache.get(getByteKey(key));
                if (rawValue != null) {
                    V value = (V) SerializeUtil.unserialize(rawValue);
                    return value;
                } else {
                    return null;
                }
	        }
		} catch (Throwable t) {
			throw new CacheException(t);
		}

	}

	@Override
	public V put(K key, V value) throws CacheException {
		logger.debug("根据key从存储 key [" + key + "]");
		 try {
			 	cache.set(getByteKey(key), SerializeUtil.serialize(value));
	            return value;
	        } catch (Throwable t) {
	            throw new CacheException(t);
	        }
	}

	@Override
	public V remove(K key) throws CacheException {
		logger.debug("从redis中删除 key [" + key + "]");
		try {
            V previous = get((K)(this.keyPrefix + key));
            cache.del(getByteKey(key));
            return previous;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
	}

	@Override
	public void clear() throws CacheException {
		logger.debug("从redis中删除所有元素");
		try {
            Set<String> keys = cache.hkeys(this.keyPrefix + "*");
            for (String key : keys) {
                cache.del(key);
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
	}

	@Override
	public int size() {
		try {
			Long longSize = new Long(cache.hkeys(keyPrefix).size());
            return longSize.intValue();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
	}

	@Override
	public Set<K> keys() {
		try {
            Set<K> newKeys = Sets.newHashSet();
            Set<String> keys = cache.hkeys(this.keyPrefix + "*");
            for(String key : keys){
                newKeys.add((K)key);
            }
            return newKeys;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
	}

	@Override
	public Collection<V> values() {
		try {
            Set<String> keys = cache.hkeys(this.keyPrefix + "*");
            List<V> values = Lists.newArrayList();
            for (String key : keys) {
                V value = get((K)key);
                values.add(value);
            }
            return values;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
	}

}
