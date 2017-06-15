/**
 * 
 */
package com.Vshop.core.cache.jedis;

import com.Vshop.core.common.PropertiesLoader;
import com.Vshop.core.common.StringUtils;

/**
 * <p>Title: JedisStatus.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年10月13日
 * @version 1.0
 */
public class JedisStatus {
	
	private static PropertiesLoader propertiesLoader = new PropertiesLoader("conf/redis.properties");
	
	//服务开启状态
	public static boolean JEDIS_STATUS;

	//####是否开启Redis服务应用
	//redis.unlock=false
	static {
		String redis_unlock = propertiesLoader.getProperty("redis.unlock");
		JEDIS_STATUS = StringUtils.isEmpty(redis_unlock) ? false : Boolean.parseBoolean(redis_unlock);
	}
	
}
