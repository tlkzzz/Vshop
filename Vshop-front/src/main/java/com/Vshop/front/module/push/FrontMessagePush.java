package com.Vshop.front.module.push;

import java.util.HashMap;
import java.util.Map;

import com.Vshop.core.push.MessagePush;
import com.Vshop.core.push.util.PushType;

/**
 * APP推送
 * @author liukai
 */
public class FrontMessagePush {
	
	/**
	 * 促销消息发送
	 * @param ticker 通知栏提示文字
	 * @param title 通知标题
	 * @param text 通知文字描述 
	 * @param urlH5 促销信息h5页面
	 */
	public static void promotionPush(String ticker,String title,String text,String urlH5){
		Map<String,String> map = new HashMap<String, String>();
		map.put("type", PushType.PUSH_TYPE_PROMOTION);  //发送消息的类型,promotion为促销消息
		map.put("data", urlH5); //促销信息h5页面链接
		//客户端推送
		MessagePush.sendBroadcast(ticker, title, text, "促销消息发送", map);
		//H5客户端推送
		MessagePush.sendBroadcastForH5(ticker, title, text, "促销消息发送", map);
	}
	
	public static void main(String[] args) {
		promotionPush("全场八折,速来抢购!", "好消息", "时间有限,多购多得!", "www.baidu.com");
	}
	
}
