package com.Vshop.service.utils.http;

import java.io.IOException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.Vshop.service.utils.CommonConstants;

/**
 * 发布静态页,这个工具类帮助封装了跳转至front 发布静态页面
 * @author chen
 *
 */
public class ToStaticSendToFront {

	/**
	 * 发布首页静态页面
	 */
    public static void indexStatic() throws IOException{  
    	
       HttpClient httpClient = new DefaultHttpClient();
       
       HttpGet httpGet = new HttpGet(CommonConstants.FRONT_SERVER + "/toStatic/indexStatic");
       
	   httpClient.execute(httpGet);
    }  
	
	/**
	 * 发布指定商品页面
	 */
    public static void onegoodsDetailStatic(Integer goodsId, Integer storeId) throws IOException{  
    	
        HttpClient httpClient = new DefaultHttpClient();
        
        HttpGet httpGet = new HttpGet(CommonConstants.FRONT_SERVER + "/toStatic/oneGoodsDetailStatic?goodsId="+goodsId+"&storeId="+storeId);
        
 	    httpClient.execute(httpGet);
     } 
	
	/**
	 * 批量发布所有的商品页面
	 */
    public static void goodsDetailBatchStatic() throws IOException{  
    	
        HttpClient httpClient = new DefaultHttpClient();
        
        HttpGet httpGet = new HttpGet(CommonConstants.FRONT_SERVER + "/toStatic/goodsDetailStatic");
        
 	   httpClient.execute(httpGet);
     } 
}
