package com.Vshop.front.utils.ehcache;


/**
 * 缓存代理抽象类
 * @author linjm
 * @date 2015-04-14
 * @version 1.0
 * @param <T>
 */
public final class CacheFactory {
	
//	public static final String GOODS_CLASS_TREE = "goodsClassTreeCache";
//	public static final String ARTICLE_CACHE = "articlesCache";
	
	@SuppressWarnings("unchecked")
	public static <T> ICache<T> getCache(String name){
		ICache<T> ehCache = new EhCacheImpl(name);
		return ehCache;
	}
	
}
