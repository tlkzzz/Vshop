/**
 * 
 */
package com.Vshop.service.utils;

import com.Vshop.core.common.PropertiesLoader;

/**
 * <p>Title: MessageConstants.java</p>
 * <p>Description: 加载短信配置</p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年8月10日
 * @version 1.0
 */
public class MessageConstants {

	private static PropertiesLoader propertiesLoader = new PropertiesLoader("conf/sms.properties");

    public static final String PASSWORD = propertiesLoader.getProperty("sms.password");
    public static final String USERID = propertiesLoader.getProperty("sms.userid");
}
