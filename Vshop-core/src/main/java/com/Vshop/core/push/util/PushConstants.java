package com.Vshop.core.push.util;

import com.Vshop.core.common.PropertiesLoader;

/**
 * 推送信息公共类
 * @author liukai
 */
public class PushConstants {
	
	private static PropertiesLoader propertiesLoader = new PropertiesLoader("conf/push.properties");

	/**
	 * Android应用唯一标识
	 */
	public static final String ANDROID_APPKEY = propertiesLoader.getProperty("android.appkey");
	/**
	 * Android应用唯一Secret
	 */
	public static final String ANDROID_APPMASTERSECRET = propertiesLoader.getProperty("android.appmastersecret");
	/**
	 * IOS应用唯一标识
	 */
	public static final String IOS_APPKEY = propertiesLoader.getProperty("ios.appkey");
	/**
	 * IOS应用唯一Secret
	 */
	public static final String IOS_APPMASTERSECRET = propertiesLoader.getProperty("ios.appmastersecret");
	
	/**
	 * IOS H5应用唯一标识
	 */
	public static final String IOS_APPKEY_H5 = propertiesLoader.getProperty("ios.appkey.h5");
	/**
	 * IOS H5应用唯一Secret
	 */
	public static final String IOS_APPMASTERSECRET_H5 = propertiesLoader.getProperty("ios.appmastersecret.h5");
	
}
