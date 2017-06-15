package com.Vshop.front.utils.ehcache;



/**
 * 缓存代理抽象类
 * @author linjm
 * @date 2015-04-14
 * @version 1.0
 * @param <T>
 */
public abstract class AbstractCacheProxy<T> {

	protected ICache<T> cache;
	public AbstractCacheProxy(){
		
	}
	public AbstractCacheProxy(String cacheName) {
		cache = CacheFactory.getCache(cacheName);
	}
	
	
}
