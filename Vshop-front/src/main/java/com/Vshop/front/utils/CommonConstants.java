package com.Vshop.front.utils;

import com.Vshop.core.common.PropertiesLoader;

public class CommonConstants {
	
	private static PropertiesLoader propertiesLoader = new PropertiesLoader("conf/Vshop.properties");

    public static final String IMG_SERVER = propertiesLoader.getProperty("img.server");
    public static final String FILE_BASEPATH = propertiesLoader.getProperty("file.basepath");
    public static final String ADMIN_SERVER = propertiesLoader.getProperty("admin.server");
    public static final String FRONT_SERVER = propertiesLoader.getProperty("front.server");
    public static final String SELLER_SERVER = propertiesLoader.getProperty("seller.server");
    public static final String LUCENE_BASEPATH = propertiesLoader.getProperty("lucene.server");
    public static final String REPORT_BASEPATH = propertiesLoader.getProperty("report.basepath");
    public static final String MODEL_BASEPATH = propertiesLoader.getProperty("model.basepath");
    public static final String STATIC_PAGE_BASEPATH = propertiesLoader.getProperty("staticpage.basepath");
    
    /**
     * 微信登录请求参数
     */
    private static PropertiesLoader weixinPropertiesLoader = new PropertiesLoader("conf/weixinconnect.properties");
    public static final String WEIXIN_APPID = weixinPropertiesLoader.getProperty("AppID");
    public static final String WEIXIN_APPSECRET = weixinPropertiesLoader.getProperty("AppSecret");//Token
    public static final String WEIXIN_TOKEN = weixinPropertiesLoader.getProperty("Token");
    public static final String WEIXIN_AUTOR_RUI = weixinPropertiesLoader.getProperty("autor_uri");
    public static final String WEIXIN_REDIRECT_URI = weixinPropertiesLoader.getProperty("redirect_uri");
    public static final String WEIXIN_REDIRECT_URI_FHB = weixinPropertiesLoader.getProperty("redirect_uri_fhb");
    public static final String WEIXIN_REDIRECT_URI_C = weixinPropertiesLoader.getProperty("redirect_uri_c");
    public static final String WEIXIN_REDIRECT_URI_G = weixinPropertiesLoader.getProperty("redirect_uri_g");
    public static final String WEIXIN_SCOPE = weixinPropertiesLoader.getProperty("scope");
    public static final String WEIXIN_RESPONSE_TYPE = weixinPropertiesLoader.getProperty("response_type");
    public static final String WEIXIN_AUTHORIZATIONCODEURL = weixinPropertiesLoader.getProperty("authorizationCodeURL");
    public static final String WEIXIN_ACCESSTOKEN = weixinPropertiesLoader.getProperty("accessToken");
    public static final String WEIXIN_REFRESHTOKEN = weixinPropertiesLoader.getProperty("refreshToken");
    public static final String WEIXIN_CHECKACCESSTOKEN = weixinPropertiesLoader.getProperty("checkAccessToken");
    public static final String WEIXIN_USERINFO = weixinPropertiesLoader.getProperty("userInfo");
    
    
}
