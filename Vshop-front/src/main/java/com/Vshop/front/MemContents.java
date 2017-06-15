package com.Vshop.front;

import com.Vshop.core.common.PropertiesLoader;

public class MemContents {
	/**
	 * 银联
	 */
	private static PropertiesLoader propertiesLoader = new PropertiesLoader("conf/merchant.properties");
    public static final String MEM_ID = propertiesLoader.getProperty("acpsdk.signCert.merId");//商户号码 
    public static final String version= propertiesLoader.getProperty("version");//版本号
    public static final String encoding= propertiesLoader.getProperty("encoding");//字符集编码 默认"UTF-8"
    public static final String signMethod= propertiesLoader.getProperty("signMethod");//签名方法 01
    public static final String txnType= propertiesLoader.getProperty("txnType");//交易类型 01-消费
    public static final String txnSubType= propertiesLoader.getProperty("txnSubType");//交易子类型 01:自助消费 02:订购 03:分期付款
    public static final String bizType= propertiesLoader.getProperty("bizType");//业务类型 000201 B2C网关支付
    public static final String channelType= propertiesLoader.getProperty("channelType");//渠道类型 07-互联网渠道
    public static final String backUrl= propertiesLoader.getProperty("backUrl");//商户/收单后台接收地址 必送
    public static final String frontUrl= propertiesLoader.getProperty("frontUrl");//商户/收单前台接收地址 必送
    public static final String accessType= propertiesLoader.getProperty("accessType");//接入类型:商户接入填0 0- 商户 ， 1： 收单， 2：平台商户
    public static final String currencyCode= propertiesLoader.getProperty("currencyCode");//交易币种
    /**
     * 支付宝
     */
    public static final String aplipaybackUrl= propertiesLoader.getProperty("aplipaybackUrl");//商户/收单后台接收地址 必送
    public static final String aplipayfrontUrl= propertiesLoader.getProperty("aplipayfrontUrl");//商户/收单前台接收地址 必送
    /**
     * 国际支付宝
     */
    public static final String aplipayinternabackUrl= propertiesLoader.getProperty("aplipayinternabackUrl");//商户/收单后台接收地址 必送
    public static final String aplipayinternafrontUrl= propertiesLoader.getProperty("aplipayinternafrontUrl");//商户/收单前台接收地址 
    public static final String aplipayratefiledir= propertiesLoader.getProperty("aplipayratefiledir");//支付宝汇率文件存放目录
    public static final String aplipayratefilename= propertiesLoader.getProperty("aplipayratefilename");//汇率文件名称
    
    /**
     * 支付宝h5
     */
    public static final String alipayh5backUrl= propertiesLoader.getProperty("alipayh5backUrl");//商户/收单后台接收地址 必送
    public static final String alipayh5frontUrl= propertiesLoader.getProperty("alipayh5frontUrl");//商户/收单前台接收地址 必送
    
    
     /**
      * 微信
      */
    public static final String appid= propertiesLoader.getProperty("appid");//公众账号ID
    public static final String appsecret= propertiesLoader.getProperty("appsecret");//应用密钥
    public static final String partner= propertiesLoader.getProperty("partner");//微信支付商户号
    public static final String partnerkey= propertiesLoader.getProperty("partnerkey");//交易密码
    
    public static final String h5index= propertiesLoader.getProperty("h5index");//微商城首页
    public static final String Oauth2back= propertiesLoader.getProperty("Oauth2back");//授权成功后回调页面
    
    public static final String oauth2= propertiesLoader.getProperty("oauth2");//跳转到授权页面
    public static final String getaccesstoken= propertiesLoader.getProperty("getaccesstoken");//获取access_token跳转
    public static final String refreshtoken= propertiesLoader.getProperty("refreshtoken");//刷新access_token
    public static final String getuserifo= propertiesLoader.getProperty("getuserifo");//获取用户信息
    public static final String istoken= propertiesLoader.getProperty("istoken");//判断授权是否有效
    
    
}
