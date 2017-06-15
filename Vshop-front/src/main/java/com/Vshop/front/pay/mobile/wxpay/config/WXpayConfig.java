/**
 * 
 */
package com.Vshop.front.pay.mobile.wxpay.config;

import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * 微信支付参数配置
 * 
 * @company 雷铭智信
 * @author 谢进伟
 * @DateTime 2015-3-4 下午2:12:30
 */
public class WXpayConfig {
	
	/**
	 * appid
	 */
	public static String APP_ID = "";
	
	/**
	 * 商户号
	 */
	public static String MCH_ID = "";
	
	/**
	 * API密钥，在商户平台设置
	 */
	public static String API_KEY = "";
	/**
	 * 课程名称
	 */
	public static String GOODSNAME;
	/**
	 * 课程详情描述
	 */
	public static String GOODSDETAIL;
	/**
	 * 微信支付异步通知路径
	 */
	public static String NOTIFYURL = "";
	/**
	 * 微信充值异步通知路径
	 */
	public static String NOTIFYURL_TOPUP= "";
	static {
		// 读取参数
		ResourceBundle rb = ResourceBundle.getBundle("com.Vshop.front.pay.mobile.wxpay.config.wxpayConfig");
		Enumeration<String> keys = rb.getKeys();
		Class<WXpayConfig> cls = WXpayConfig.class;
		Field fields[] = cls.getDeclaredFields();
		String key;
		try {
			WXpayConfig wc = cls.newInstance();
			while (keys.hasMoreElements()) {
				key = keys.nextElement();
				for(Field f : fields) {
					if(f.getName().toUpperCase().equals(key.toUpperCase())) {
						if(f.getType().getSimpleName() == "boolean") {
							f.set(wc , Boolean.parseBoolean(rb.getString(key)));
						} else {
							f.set(wc , rb.getString(key));
						}
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("APP_ID:"+APP_ID+"  MCH_ID:"+MCH_ID+"  API_KEY："+API_KEY+"  GOODSNAME:"+GOODSNAME+"  GOODSDETAIL:"+GOODSDETAIL+"  NOTIFYURL:"+NOTIFYURL);
	}
}
