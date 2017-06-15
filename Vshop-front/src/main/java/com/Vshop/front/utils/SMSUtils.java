package com.Vshop.front.utils;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang3.StringUtils;

import com.Vshop.front.alipay.util.httpClient.HttpProtocolHandler;
import com.Vshop.front.alipay.util.httpClient.HttpRequest;
import com.Vshop.front.alipay.util.httpClient.HttpResponse;
import com.Vshop.front.alipay.util.httpClient.HttpResultType;

public class SMSUtils {
	
	
	// 返回数据编码格式
	private final static String encoding = "utf-8";
	
	
	/**
     * 建立请求，以模拟远程HTTP的POST请求方式构造并获取处理结果
     * 如：postRequest(sParaTemp)
     * @param sParaTemp 请求参数数组
     * @return 处理结果
     * @throws Exception
     */
    public static String postRequest(Map<String, String> sParaTemp) throws Exception {
        //待请求参数数组

        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();

        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        //设置编码集
        request.setCharset(encoding);
        request.setParameters(generatNameValuePair(sParaTemp));
        request.setUrl(MessageConstants.MSG_CALLAPI);
        
        HttpResponse response = httpProtocolHandler.execute(request, "", "");
        if (response == null) {
            return null;
        }
        
        String strResult = response.getStringResult();

        return strResult;
    }
    
    

	/**
     * 建立请求，以模拟远程HTTP的GET请求方式构造并获取处理结果
     * 如：postRequest(sParaTemp)
     * @param sParaTemp 请求参数数组
     * @return 处理结果
     * @throws Exception
     */
    public static String getRequest(String url) throws Exception {
        //待请求参数数组

        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();

        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        //设置编码集
        request.setCharset(encoding);
        request.setMethod(HttpRequest.METHOD_GET);
        request.setUrl(url);
        
        HttpResponse response = httpProtocolHandler.execute(request, null, null);
        if (response == null) {
            return null;
        }
        
        String strResult = response.getStringResult();

        return strResult;
    }
    
    /**
     * MAP类型数组转换成NameValuePair类型
     * @param properties  MAP类型数组
     * @return NameValuePair类型数组
     */
    private static NameValuePair[] generatNameValuePair(Map<String, String> properties) {
        NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            nameValuePair[i++] = new NameValuePair(entry.getKey(), entry.getValue());
        }
        return nameValuePair;
    }
    
    public static String pasrXMl(String result){
    	
    	return null;
    }
    
    /**
     * 发送短信
     * @param content
     * @return
     */
    public String sendMsg(String content, String mobile){
    	String result = sendMsg("", "", "", mobile, content);
    	return result;
    }
    
    /**
     * 发送短信
     * @param userid
     * @param account
     * @param password
     * @param mobile
     * @param content
     * @return
     */
    public String sendMsg(String userid ,String account, String password, String mobile,
			String content){
		if(StringUtils.isEmpty(account) || StringUtils.isEmpty(password) || StringUtils.isEmpty(userid)){
			account = MessageConstants.MSG_ACCOUNT;
			password = MessageConstants.MSG_PASSWORD;
			userid = MessageConstants.MSG_PASSWORD;
		}
		//post请求参数
		Map<String ,String> postData = new HashMap<String ,String>();
		postData.put("action", "send");
		postData.put("userid", MessageConstants.MSG_USERID);
		postData.put("account", account);
		postData.put("password", password);
		postData.put("mobile", mobile);
		postData.put("content", content);
		
		String str = "";
		try {
			
			String responseMsg =  SMSUtils.postRequest(postData);
			if(str != null){
				ByteArrayInputStream in = new ByteArrayInputStream(responseMsg.getBytes("UTF-8"));
				Map<String ,String> msgMap = XMLUtils.xmlToString(in);
				str = msgMap.get("returnstatus");
			}
		} catch (Exception e) {
			
		}
		return str;
	}
    
    
    

}
