package com.Vshop.front.utils;

import com.Vshop.core.common.PropertiesLoader;


public class MessageConstants {

    private static PropertiesLoader propertiesLoader = new PropertiesLoader("conf/sms.properties");

    public static final String MSG_ACCOUNT = propertiesLoader.getProperty("msg.account");
    public static final String MSG_PASSWORD = propertiesLoader.getProperty("msg.password");
    public static final String MSG_CALLAPI = propertiesLoader.getProperty("msg.callapi");
    public static final String MSG_USERID = propertiesLoader.getProperty("msg.userid");
    
    
}
