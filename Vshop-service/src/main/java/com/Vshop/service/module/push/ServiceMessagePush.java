package com.Vshop.service.module.push;

import java.util.HashMap;
import java.util.Map;

import com.Vshop.core.push.MessagePush;
import com.Vshop.core.push.util.PushType;

/**
 * APP推送
 * @author liukai
 */
public class ServiceMessagePush {
	
	/**
	 * 客户端订单发货推送
	 * @param ticker 通知栏提示文字
	 * @param title 通知标题
	 * @param text 通知文字描述 
	 * @param alias 要推送的买家id
	 * @param orderId 订单id
	 */
	public static void orderShipPush(String ticker,String title,String text,Integer buyerId,Integer orderId){
		Map<String,String> map = new HashMap<String, String>(); 
		map.put("type", PushType.PUSH_TYPE_ORDERSHIP); //发送消息的类型,orderShip为订单发货
		map.put("data", orderId.toString()); //订单id
		MessagePush.sendCustomizedcast(ticker, title, text, buyerId.toString(), "订单发货推送", map);
	}
	
	/**
	 * H5客户端订单发货推送
	 * @param ticker 通知栏提示文字
	 * @param title 通知标题
	 * @param text 通知文字描述 
	 * @param alias 要推送的买家id
	 * @param orderId 订单id
	 * @param urlH5 微信页面url地址
	 */
	public static void orderShipPushForH5(String ticker,String title,String text,Integer buyerId,String urlH5){
		Map<String,String> map = new HashMap<String, String>(); 
		map.put("type", PushType.PUSH_TYPE_ORDERSHIP); //发送消息的类型,orderShip为订单发货
		map.put("data", urlH5); //微信页面url地址(订单列表)
		MessagePush.sendCustomizedcastForH5(ticker, title, text, buyerId.toString(), "订单发货推送", map);
	}
	
	public static void main(String[] args) {
		//orderShipPush("订单已发货", "你购买的商品已发货", "订单发货:您的订单已发货", 10, 1);
		orderShipPushForH5("订单已发货", "你购买的商品已发货", "订单发货:您的订单已发货", 10, "http://b2b2c.Vshop.com/Vshop-front/m/authc/buyer/orderList?orderState=99");
	}
}
