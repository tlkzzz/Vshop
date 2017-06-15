package com.Vshop.front.utils.ehcache;


/**
 * 缓存代理抽象类
 * @author linjm
 * @date 2015-04-14
 * @version 1.0
 * @param <T>
 */
public interface ICache<T> {
	/**
	 * Get an item from the cache, nontransactionally
	 * @param key
	 * @return the cached object or <tt>null</tt>
	 * @throws CacheException
	 */
	public T get(Object key);
	/**
	 * Add an item to the cache, nontransactionally, with
	 * failfast semantics
	 * @param key
	 * @param value
	 * @throws CacheException
	 */
	public void put(Object key, T value);
	/**
	 * Remove an item from the cache
	 */
	public void remove(Object key);
	/**
	 * Clear the cache
	 */
	public void clear();
}
