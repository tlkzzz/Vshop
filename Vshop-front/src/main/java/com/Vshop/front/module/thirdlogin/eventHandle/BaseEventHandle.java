package com.Vshop.front.module.thirdlogin.eventHandle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Vshop.front.utils.CommonConstants;

/**
 * 事件基础类
 * @author coolzlay
 *
 */
@Component("baseEventHandle")
public class BaseEventHandle {

	
	/**
	 * 获取微信APPID
	 * @return
	 */
	public String getAppID(){
		return  CommonConstants.WEIXIN_APPID ;
	}
	
	/**
	 * 获取微信秘钥
	 * @return
	 */
	public String getAppSecret(){
		return  CommonConstants.WEIXIN_APPSECRET;
	}
}
