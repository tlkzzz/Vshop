/**
 * 
 */
package com.Vshop.service.utils;

import com.Vshop.core.common.PropertiesLoader;

/**
 * <p>Title: JDBCCommontants.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年9月11日
 * @version 1.0
 */
public class JDBCConstants {
	
	 private static PropertiesLoader propertiesLoader = new PropertiesLoader("conf/jdbc.properties");
	 
	 public static final String JDBC_TYPE = propertiesLoader.getProperty("jdbc.type");
	 public static final String JDBC_DRIVER = propertiesLoader.getProperty("jdbc.driverClassName.db01");
	 public static final String JDBC_URL = propertiesLoader.getProperty("jdbc.url.db01");
	 public static final String JDBC_USERNAME = propertiesLoader.getProperty("jdbc.username.db01");
	 public static final String JDBC_PASSWORD = propertiesLoader.getProperty("jdbc.password.db01");
	 
	 

}
