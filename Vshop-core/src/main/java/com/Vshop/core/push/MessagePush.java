package com.Vshop.core.push;

import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.Vshop.core.push.android.AndroidBroadcast;
import com.Vshop.core.push.android.AndroidCustomizedcast;
import com.Vshop.core.push.android.AndroidFilecast;
import com.Vshop.core.push.android.AndroidGroupcast;
import com.Vshop.core.push.android.AndroidUnicast;
import com.Vshop.core.push.ios.IOSBroadcast;
import com.Vshop.core.push.ios.IOSCustomizedcast;
import com.Vshop.core.push.ios.IOSFilecast;
import com.Vshop.core.push.ios.IOSGroupcast;
import com.Vshop.core.push.ios.IOSUnicast;
import com.Vshop.core.push.util.PushConstants;

/**
 * 友盟推送
 * @author liukai
 */
public class MessagePush {
	//时间戳，10位或者13位均可，时间戳有效期为10分钟
	private static final String timestamp = Integer.toString((int)(System.currentTimeMillis() / 1000));
	
	/**
	 * 广播推送(Android和IOS)
	 * @param ticker 通知栏提示文字
	 * @param title 通知标题
	 * @param text 通知文字描述 
	 * @param description 发送消息描述
	 * @param map 可选 用户自定义内容, "d","p"为友盟保留字段
	 * @throws Exception
	 */
	public static void sendBroadcast(String ticker,String title,String text,String description,Map<String,String> map){
		try {
			sendAndroidBroadcast(PushConstants.ANDROID_APPMASTERSECRET,
					PushConstants.ANDROID_APPKEY,ticker,title,text,description,map); //Android广播
			sendIOSBroadcast(PushConstants.IOS_APPMASTERSECRET,
					PushConstants.IOS_APPKEY,ticker,description,map); //IOS广播
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过alias推送(Android和IOS)
	 * @param ticker 通知栏提示文字
	 * @param title 通知标题
	 * @param text 通知文字描述 
	 * @param alias 别名 要求不超过50个alias,多个alias以英文逗号间隔。
	 * @param description 发送消息描述
	 * @param map 可选 用户自定义内容, "d","p"为友盟保留字段
	 * @throws Exception
	 */
	public static void sendCustomizedcast(String ticker,String title,String text,String alias,String description,Map<String,String> map){
		try {
			sendAndroidCustomizedcast(PushConstants.ANDROID_APPMASTERSECRET,
					PushConstants.ANDROID_APPKEY,ticker,title,text,alias,description,map); //Android通过alias推送
			sendIOSCustomizedcast(PushConstants.IOS_APPMASTERSECRET,
					PushConstants.IOS_APPKEY,text,alias,description,map); //IOS通过alias推送
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 广播推送(Android和IOSH5客户端推送)
	 * @param ticker 通知栏提示文字
	 * @param title 通知标题
	 * @param text 通知文字描述 
	 * @param description 发送消息描述
	 * @param map 可选 用户自定义内容, "d","p"为友盟保留字段
	 * @throws Exception
	 */
	public static void sendBroadcastForH5(String ticker,String title,String text,String description,Map<String,String> map){
		try {
			sendIOSBroadcast(PushConstants.IOS_APPMASTERSECRET_H5,
					PushConstants.IOS_APPKEY_H5,ticker,description,map); //IOS广播
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过alias推送(Android和IOSH5客户端推送)
	 * @param ticker 通知栏提示文字
	 * @param title 通知标题
	 * @param text 通知文字描述 
	 * @param alias 别名 要求不超过50个alias,多个alias以英文逗号间隔。
	 * @param description 发送消息描述
	 * @param map 可选 用户自定义内容, "d","p"为友盟保留字段
	 * @throws Exception
	 */
	public static void sendCustomizedcastForH5(String ticker,String title,String text,String alias,String description,Map<String,String> map){
		try {
			sendIOSCustomizedcast(PushConstants.IOS_APPMASTERSECRET_H5,
					PushConstants.IOS_APPKEY_H5,text,alias,description,map); //IOS通过alias推送
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Android广播
	 * @param androidSecret Android应用唯一Secret
	 * @param androidAppkey Android应用唯一标识
	 * @param ticker 通知栏提示文字
	 * @param title 通知标题
	 * @param text 通知文字描述 
	 * @param afterOpenStr 点击"通知"的后续行为，默认为打开app。 
	 * 		  "go_app": 打开应用,"go_url": 跳转到URL,"go_activity": 打开特定的activity, "go_custom": 用户自定义内容, 可以为字符串或者JSON格式
	 * @param description 发送消息描述
	 * @param map 可选 用户自定义内容, "d","p"为友盟保留字段
	 * @throws Exception
	 */
	public static void sendAndroidBroadcast(String androidSecret,String androidAppkey,String ticker,String title,String text,String description,Map<String,String> map) throws Exception {
		AndroidBroadcast broadcast = new AndroidBroadcast();
		broadcast.setAppMasterSecret(androidSecret); //Android应用唯一Secret
		broadcast.setPredefinedKeyValue("appkey", androidAppkey); //Android应用唯一标识
		broadcast.setPredefinedKeyValue("timestamp", timestamp); //时间戳
		broadcast.setPredefinedKeyValue("ticker", ticker); //通知栏提示文字
		broadcast.setPredefinedKeyValue("title",  title); //通知标题
		broadcast.setPredefinedKeyValue("text",   text); //通知文字描述 
		broadcast.setPredefinedKeyValue("after_open", "go_app"); //点击"通知"的后续行为，默认为打开app。
		broadcast.setPredefinedKeyValue("display_type", "notification"); //消息类型 值可以为:notification-通知，message-消息
		// TODO Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		broadcast.setPredefinedKeyValue("production_mode", "false"); //true正式/false测试模式。测试模式下，广播/组播只会将消息发给测试设备。
		broadcast.setPredefinedKeyValue("description",description); //发送消息描述，建议填写。
		// Set customized fields
		/**
		 * 可选 用户自定义内容, "d","p"为友盟保留字段
		 */
		if(map!=null){
			Set<String> set = map.keySet();
			for(String str:set){
				broadcast.setExtraField(str, map.get(str));
			}
		}
		broadcast.send();
	}
	
	/**
	 * Android单播
	 * @param androidSecret Android应用唯一Secret
	 * @param androidAppkey Android应用唯一标识
	 * @param deviceTokens 设备唯一标识,指定单个设备
	 * @param title 通知标题
	 * @param text 通知文字描述 
	 * @param ticker 通知栏提示文字
	 * @param description 发送消息描述
	 * @param map 可选 用户自定义内容, "d","p"为友盟保留字段
	 * @throws Exception
	 */
	public static void sendAndroidUnicast(String androidSecret,String androidAppkey,String deviceTokens,String title,String text,String ticker,String description,Map<String,String> map) throws Exception {
		AndroidUnicast unicast = new AndroidUnicast();
		unicast.setAppMasterSecret(androidSecret); //Android应用唯一Secret
		unicast.setPredefinedKeyValue("appkey", androidAppkey); //Android应用唯一标识
		unicast.setPredefinedKeyValue("timestamp", timestamp); //时间戳
		// TODO Set your device token
		unicast.setPredefinedKeyValue("device_tokens", deviceTokens); //设备唯一表示
		unicast.setPredefinedKeyValue("ticker", ticker); //通知栏提示文字
		unicast.setPredefinedKeyValue("title",  title); //通知标题
		unicast.setPredefinedKeyValue("text",   text); //通知文字描述 
		unicast.setPredefinedKeyValue("after_open", "go_app"); //点击"通知"的后续行为，默认为打开app。
		unicast.setPredefinedKeyValue("display_type", "notification");  //消息类型 值可以为:notification-通知，message-消息
		// TODO Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		unicast.setPredefinedKeyValue("production_mode", "false"); //true正式/false测试模式。测试模式下，广播/组播只会将消息发给测试设备。
		unicast.setPredefinedKeyValue("description",description); //发送消息描述，建议填写。
		// Set customized fields
		/**
		 * 可选 用户自定义内容, "d","p"为友盟保留字段
		 */
		if(map!=null){
			Set<String> set = map.keySet();
			for(String str:set){
				unicast.setExtraField(str, map.get(str));
			}
		}
		unicast.send();
	}
	
	/**
	 * Android组播
	 * @param androidSecret Android应用唯一Secret
	 * @param androidAppkey Android应用唯一标识
	 * @param title 通知标题
	 * @param text 通知文字描述 
	 * @param ticker 通知栏提示文字
	 * @param description 发送消息描述
	 * @param map 可选 用户自定义内容, "d","p"为友盟保留字段
	 * @param filterMap 组播获取分组条件
	 * @throws Exception
	 */
	public static void sendAndroidGroupcast(String androidSecret,String androidAppkey,String title,String text,String ticker,String description,Map<String,String> map,Map<String,String> filterMap) throws Exception {
		AndroidGroupcast groupcast = new AndroidGroupcast();
		groupcast.setAppMasterSecret(androidSecret); //Android应用唯一Secret
		groupcast.setPredefinedKeyValue("appkey", androidAppkey); //Android应用唯一标识
		groupcast.setPredefinedKeyValue("timestamp", timestamp); //时间戳
		/*  TODO
		 * 组播获取分组条件
		 *  Construct the filter condition:
		 *  "where": 
		 *	{
    	 *		"and": 
    	 *		[
      	 *			{"tag":"test"},
      	 *			{"tag":"Test"}
    	 *		]
		 *	}
		 */
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		JSONObject testTag = new JSONObject();
		if(filterMap!=null){
			Set<String> filterSet = filterMap.keySet();
			for(String str:filterSet){
				testTag.put(str, filterMap.get(str));
			}
		}
		tagArray.put(testTag);
		whereJson.put("and", tagArray);
		filterJson.put("where", whereJson);
		
		groupcast.setPredefinedKeyValue("filter", filterJson);
		groupcast.setPredefinedKeyValue("ticker", ticker); //通知栏提示文字
		groupcast.setPredefinedKeyValue("title", title); //通知标题
		groupcast.setPredefinedKeyValue("text", text); //通知文字描述 
		groupcast.setPredefinedKeyValue("after_open", "go_app"); //点击"通知"的后续行为，默认为打开app。
		groupcast.setPredefinedKeyValue("display_type", "notification"); //消息类型 值可以为:notification-通知，message-消息
		// TODO Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		groupcast.setPredefinedKeyValue("production_mode", "false"); //true正式/false测试模式。测试模式下，广播/组播只会将消息发给测试设备。
		groupcast.setPredefinedKeyValue("description",description); //发送消息描述，建议填写。
		/**
		 * 可选 用户自定义内容, "d","p"为友盟保留字段
		 */
		if(map!=null){
			Set<String> set = map.keySet();
			for(String str:set){
				groupcast.setExtraField(str, map.get(str));
			}
		}
		groupcast.send();
	}
	
	/**
	 * Android通过alias推送
	 * @param androidSecret Android应用唯一Secret
	 * @param androidAppkey Android应用唯一标识
	 * @param ticker 通知栏提示文字
	 * @param title 通知标题
	 * @param text 通知文字描述 
	 * @param alias 别名 要求不超过50个alias,多个alias以英文逗号间隔。
	 * @param description 发送消息描述
	 * @param map 可选 用户自定义内容, "d","p"为友盟保留字段
	 * @throws Exception
	 */
	public static void sendAndroidCustomizedcast(String androidSecret,String androidAppkey,String ticker,String title,String text,String alias,String description,Map<String,String> map) throws Exception {
		AndroidCustomizedcast customizedcast = new AndroidCustomizedcast();
		customizedcast.setAppMasterSecret(androidSecret); //Android应用唯一Secret
		customizedcast.setPredefinedKeyValue("appkey", androidAppkey); //Android应用唯一标识
		customizedcast.setPredefinedKeyValue("timestamp", timestamp); //时间戳
		// TODO Set your alias here, and use comma to split them if there are multiple alias.
		// And if you have many alias, you can also upload a file containing these alias, then 
		// use file_id to send customized notification.
		customizedcast.setPredefinedKeyValue("alias", alias); //别名
		// TODO Set your alias_type here
		customizedcast.setPredefinedKeyValue("alias_type", "b2b2c"); //别名类型
		customizedcast.setPredefinedKeyValue("ticker", ticker); //通知栏提示文字
		customizedcast.setPredefinedKeyValue("title",  title); //通知标题
		customizedcast.setPredefinedKeyValue("text",   text); //通知文字描述 
		customizedcast.setPredefinedKeyValue("after_open", "go_app"); //点击"通知"的后续行为，默认为打开app。
		customizedcast.setPredefinedKeyValue("display_type", "notification"); //消息类型 值可以为:notification-通知，message-消息
		// TODO Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		customizedcast.setPredefinedKeyValue("production_mode", "false"); //true正式/false测试模式。测试模式下，广播/组播只会将消息发给测试设备。
		customizedcast.setPredefinedKeyValue("description",description); //发送消息描述，建议填写。
		/**
		 * 可选 用户自定义内容, "d","p"为友盟保留字段
		 */
		if(map!=null){
			Set<String> set = map.keySet();
			for(String str:set){
				customizedcast.setExtraField(str, map.get(str));
			}
		}
		customizedcast.send();
	}
	
	/**
	 * Android文件播
	 * @param androidSecret Android应用唯一Secret
	 * @param androidAppkey Android应用唯一标识
	 * @param ticker 通知栏提示文字
	 * @param title 通知标题
	 * @param text 通知文字描述 
	 * @param deviceTokens 设备唯一表示,内容为多条device_token,device_token以回车符('\n')分隔
	 * @param description 发送消息描述
	 * @param map 可选 用户自定义内容, "d","p"为友盟保留字段
	 * @throws Exception
	 */
	public static void sendAndroidFilecast(String androidSecret,String androidAppkey,String ticker,String title,String text,String deviceTokens,String description,Map<String,String> map) throws Exception {
		AndroidFilecast filecast = new AndroidFilecast();
		filecast.setAppMasterSecret(androidSecret); //Android应用唯一Secret
		filecast.setPredefinedKeyValue("appkey", androidAppkey); //Android应用唯一标识
		filecast.setPredefinedKeyValue("timestamp", timestamp); //时间戳
		// TODO upload your device tokens, and use '\n' to split them if there are multiple tokens 
		filecast.uploadContents(deviceTokens);
		filecast.setPredefinedKeyValue("ticker", ticker); //通知栏提示文字
		filecast.setPredefinedKeyValue("title",  title); //通知标题
		filecast.setPredefinedKeyValue("text",   text); //通知文字描述 
		filecast.setPredefinedKeyValue("after_open", "go_app"); //点击"通知"的后续行为，默认为打开app。
		filecast.setPredefinedKeyValue("display_type", "notification"); //消息类型 值可以为:notification-通知，message-消息
		filecast.setPredefinedKeyValue("production_mode", "false"); //true正式/false测试模式。测试模式下，广播/组播只会将消息发给测试设备。
		filecast.setPredefinedKeyValue("description",description); //发送消息描述，建议填写。
		/**
		 * 可选 用户自定义内容, "d","p"为友盟保留字段
		 */
		if(map!=null){
			Set<String> set = map.keySet();
			for(String str:set){
				filecast.setExtraField(str, map.get(str));
			}
		}
		filecast.send();
	}
	
	/**
	 * IOS广播
	 * @param IOS应用唯一Secret
	 * @param IOS应用唯一标识
	 * @param alert 推送内容
	 * @param description 发送消息描述
	 * @param map 可选 用户自定义内容, "d","p"为友盟保留字段
	 * @throws Exception
	 */
	public static void sendIOSBroadcast(String iosSecret,String iosAppkey,String alert,String description,Map<String,String> map) throws Exception {
		IOSBroadcast broadcast = new IOSBroadcast();
		broadcast.setAppMasterSecret(iosSecret); //IOS应用唯一Secret
		broadcast.setPredefinedKeyValue("appkey", iosAppkey); //IOS应用唯一标识
		broadcast.setPredefinedKeyValue("timestamp", timestamp); //时间戳

		broadcast.setPredefinedKeyValue("alert", alert); //推送内容
		broadcast.setPredefinedKeyValue("badge", 0); //
		broadcast.setPredefinedKeyValue("sound", "chime"); // 自定义通知声音
		// TODO set 'production_mode' to 'true' if your app is under production mode
		broadcast.setPredefinedKeyValue("production_mode", "false"); //true正式/false测试模式。测试模式下，广播/组播只会将消息发给测试设备。
		broadcast.setPredefinedKeyValue("description",description); //发送消息描述，建议填写。
		// Set customized fields
		/**
		 * 可选 用户自定义内容, "d","p"为友盟保留字段
		 */
		if(map!=null){
			Set<String> set = map.keySet();
			for(String str:set){
				broadcast.setCustomizedField(str, map.get(str));
			}
		}
		broadcast.send();
	}
	
	/**
	 * IOS单播
	 * @param IOS应用唯一Secret
	 * @param IOS应用唯一标识
	 * @param deviceTokens 设备唯一标识,指定单个设备
	 * @param alert 推送内容
	 * @param description 发送消息描述
	 * @param map 可选 用户自定义内容, "d","p"为友盟保留字段
	 * @throws Exception
	 */
	public static void sendIOSUnicast(String iosSecret,String iosAppkey,String deviceTokens,String alert,String description,Map<String,String> map) throws Exception {
		IOSUnicast unicast = new IOSUnicast();
		unicast.setAppMasterSecret(iosSecret); //IOS应用唯一Secret
		unicast.setPredefinedKeyValue("appkey", iosAppkey); //IOS应用唯一标识
		unicast.setPredefinedKeyValue("timestamp", timestamp); //时间戳
		// TODO Set your device token
		unicast.setPredefinedKeyValue("device_tokens", "xx"); //设备唯一标识  必填, 表示指定的单个设备
		unicast.setPredefinedKeyValue("alert", alert); //推送内容
		unicast.setPredefinedKeyValue("badge", 0); 
		unicast.setPredefinedKeyValue("sound", "chime"); // 自定义通知声音
		// TODO set 'production_mode' to 'true' if your app is under production mode
		unicast.setPredefinedKeyValue("production_mode", "false"); //true正式/false测试模式。测试模式下，广播/组播只会将消息发给测试设备。
		unicast.setPredefinedKeyValue("description", description); //发送消息描述，建议填写。
		// Set customized fields
		/**
		 * 可选 用户自定义内容, "d","p"为友盟保留字段
		 */
		if(map!=null){
			Set<String> set = map.keySet();
			for(String str:set){
				unicast.setCustomizedField(str, map.get(str));
			}
		}
		unicast.send();
	}
	
	/**
	 * IOS组播
	 * @param IOS应用唯一Secret
	 * @param IOS应用唯一标识
	 * @param alert 推送内容
	 * @param description 发送消息描述
	 * @param filterMap 组播获取分组条件
	 * @param map 可选 用户自定义内容, "d","p"为友盟保留字段
	 * @throws Exception
	 */
	public static void sendIOSGroupcast(String iosSecret,String iosAppkey,String alert,String description,Map<String,String> filterMap,Map<String,String> map) throws Exception {
		IOSGroupcast groupcast = new IOSGroupcast(); 
		groupcast.setAppMasterSecret(iosSecret); //IOS应用唯一Secret
		groupcast.setPredefinedKeyValue("appkey", iosAppkey); //IOS应用唯一标识
		groupcast.setPredefinedKeyValue("timestamp", timestamp); //时间戳
		/*  TODO
		 * 组播获取分组条件
		 *  Construct the filter condition:
		 *  "where": 
		 *	{
    	 *		"and": 
    	 *		[
      	 *			{"app_version":"1.0"} 发送给app_version为1.0的用户群
    	 *		]
		 *	}
		 */
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		JSONObject testTag = new JSONObject();
		if(filterMap!=null){
			Set<String> filterSet = filterMap.keySet();
			for(String str:filterSet){
				testTag.put(str, filterMap.get(str));
			}
		}
		tagArray.put(testTag);
		whereJson.put("and", tagArray);
		filterJson.put("where", whereJson);
		
		// Set filter condition into rootJson
		groupcast.setPredefinedKeyValue("filter", filterJson); //终端用户筛选条件,如用户标签、地域、应用版本以及渠道等
		groupcast.setPredefinedKeyValue("alert", alert); //推送内容
		groupcast.setPredefinedKeyValue("badge", 0); //
		groupcast.setPredefinedKeyValue("sound", "chime"); // 自定义通知声音
		// TODO set 'production_mode' to 'true' if your app is under production mode
		groupcast.setPredefinedKeyValue("production_mode", "false"); //true正式/false测试模式。测试模式下，广播/组播只会将消息发给测试设备。
		groupcast.setPredefinedKeyValue("description", description); //发送消息描述，建议填写。
		/**
		 * 可选 用户自定义内容, "d","p"为友盟保留字段
		 */
		if(map!=null){
			Set<String> set = map.keySet();
			for(String str:set){
				groupcast.setCustomizedField(str, map.get(str));
			}
		}
		groupcast.send();
	}
	
	/**
	 * IOS通过alias推送
	 * @param IOS应用唯一Secret
	 * @param IOS应用唯一标识
	 * @param alert 推送内容
	 * @param alias 别名 要求不超过50个alias,多个alias以英文逗号间隔。
	 * @param description 发送消息描述
	 * @param map 可选 用户自定义内容, "d","p"为友盟保留字段
	 * @throws Exception
	 */
	public static void sendIOSCustomizedcast(String iosSecret,String iosAppkey,String alert,String alias,String description,Map<String,String> map) throws Exception {
		IOSCustomizedcast customizedcast = new IOSCustomizedcast();
		customizedcast.setAppMasterSecret(iosSecret); //IOS应用唯一Secret
		customizedcast.setPredefinedKeyValue("appkey", iosAppkey); //IOS应用唯一标识
		customizedcast.setPredefinedKeyValue("timestamp", timestamp); //时间戳
		// TODO Set your alias here, and use comma to split them if there are multiple alias.
		// And if you have many alias, you can also upload a file containing these alias, then 
		// use file_id to send customized notification.
		customizedcast.setPredefinedKeyValue("alias", alias); //别名
		// TODO Set your alias_type here
		customizedcast.setPredefinedKeyValue("alias_type", "b2b2c"); //别名类型
		customizedcast.setPredefinedKeyValue("alert", alert); //推送内容
		customizedcast.setPredefinedKeyValue("badge", 0); 
		customizedcast.setPredefinedKeyValue("sound", "chime"); // 自定义通知声音
		// TODO set 'production_mode' to 'true' if your app is under production mode
		customizedcast.setPredefinedKeyValue("production_mode", "false"); //true正式/false测试模式。测试模式下，广播/组播只会将消息发给测试设备。
		customizedcast.setPredefinedKeyValue("description", description); //发送消息描述，建议填写。
		/**
		 * 可选 用户自定义内容, "d","p"为友盟保留字段
		 */
		if(map!=null){
			Set<String> set = map.keySet();
			for(String str:set){
				customizedcast.setCustomizedField(str, map.get(str));
			}
		}
		customizedcast.send();
	}
	
	/**
	 * IOS文件播
	 * @param IOS应用唯一Secret
	 * @param IOS应用唯一标识
	 * @param alert 推送内容
	 * @param description 发送消息描述
	 * @param map 可选 用户自定义内容, "d","p"为友盟保留字段
	 * @param deviceTokens 设备唯一表示,内容为多条device_token,device_token以回车符('\n')分隔
	 * @throws Exception
	 */
	public static void sendIOSFilecast(String iosSecret,String iosAppkey,String alert,String deviceTokens,String description,Map<String,String> map) throws Exception {
		IOSFilecast filecast = new IOSFilecast();
		filecast.setAppMasterSecret(iosSecret); //IOS应用唯一Secret
		filecast.setPredefinedKeyValue("appkey", iosAppkey); //IOS应用唯一标识
		filecast.setPredefinedKeyValue("timestamp", timestamp); //时间戳
		// TODO upload your device tokens, and use '\n' to split them if there are multiple tokens 
		filecast.uploadContents(deviceTokens);
		filecast.setPredefinedKeyValue("alert", alert); //推送内容
		filecast.setPredefinedKeyValue("badge", 0);
		filecast.setPredefinedKeyValue("sound", "chime"); // 自定义通知声音
		// TODO set 'production_mode' to 'true' if your app is under production mode
		filecast.setPredefinedKeyValue("production_mode", "false"); //true正式/false测试模式。测试模式下，广播/组播只会将消息发给测试设备。
		filecast.setPredefinedKeyValue("description", description); //发送消息描述，建议填写。
		/**
		 * 可选 用户自定义内容, "d","p"为友盟保留字段
		 */
		if(map!=null){
			Set<String> set = map.keySet();
			for(String str:set){
				filecast.setCustomizedField(str, map.get(str));
			}
		}
		filecast.send();
	}
	
}
