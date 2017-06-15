/**
 * 
 */
package com.Vshop.core.cache.jedis;

import com.Vshop.core.codec.util.SerializeUtil;

/**
 * <p>Title: JedisKeyUtil.java</p>
 * <p>Description: 主要用于给键加前缀和后缀并且序列化</p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年10月14日
 * @version 1.0
 */
public class JedisKeyUtil {
	
	/**
	 * 获取加前缀序列化键
	 * @param keyPrefix 前缀
	 * @param key 原键
	 * @return
	 */
	public byte[] getPrefixKey(String keyPrefix, String key){
		if(key instanceof String){
			String preKey = keyPrefix + key;
    		return preKey.getBytes();
    	}else{
    		return SerializeUtil.serialize(key);
    	}
	}
	
	/**
	 * 没有前缀的序列化键
	 * @param key
	 * @return
	 */
	public byte[] getPrefixKey(String key){
		return getPrefixKey(null,key);
	}
	
	
	/**
	 * 获取加前缀序列化键
	 * @param suffix 后缀
	 * @param key 原键
	 * @return
	 */
	public byte[] getKeySuffix(String suffix, String key){
		if(key instanceof String){
			String preKey = key + suffix;
    		return preKey.getBytes();
    	}else{
    		return SerializeUtil.serialize(key);
    	}
	}
	
	/**
	 * 没有前缀的序列化键
	 * @param key
	 * @return
	 */
	public byte[] getKeySuffix(String key){
		return getKeySuffix(null, key);
	}
	
	public static void main(String[] args) {
		JedisKeyUtil j = new JedisKeyUtil();
		
		byte[] bbb = j.getKeySuffix("11");
		System.out.println(SerializeUtil.unserialize(bbb));
	}

}
