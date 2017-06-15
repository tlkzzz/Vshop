package com.Vshop.front.module.wxfhb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.Vshop.front.wechat.util.MD5Util;
import com.Vshop.front.wechat.util.MyX509TrustManager;
import com.qq.connect.utils.http.BASE64Encoder;

public class wxsendRedPack {

	
	public static String sendRedPack(String opid,int je,String fszmc,String tgfmc,String hdmc,String bz,String hbzfy,String sjzfc) throws Exception{  
		String httpurl ="https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
		String noncestr=sjzfc;//随机字符串
		String mchid="1397416502";//商户号
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");//设置日期格式
        String times=(df.format(new Date())+((int)(Math.random()*10))).toString();// new Date()为获取当前系统时间
		String mchbillno=mchid+times;//商户订单号（每个订单号必须唯一）组成： mch_id+yyyymmdd+10位一天内不能重复的数字（可以为hhMMssDD1）。接口根据商户订单号支持重入， 如出现超时可再调用。
//		String mchbillno="1397416502201703150924172627";
		String wxappid="wxd26b23a0c2ff805f";//公众账号appid
		String nickname=tgfmc;//提供方名称
		String sendname=fszmc;//红包发送者名称
//		String reopenid=opid;//用戶openID
		String reopenid=opid;//用戶openID
		int totalamount=je;//付款金额
		int totalnum=1;//红包发放总人数
		String wishing=hbzfy;//红包祝福语
		String clientip="139.196.228.107";//调用接口的机器Ip地址
		String actname=hdmc;//活动名称
		String remark=bz;//备注信息
		String stringSignTemp=
				"act_name="+actname+
				"&client_ip="+clientip+
				"&mch_billno="+mchbillno+
				"&mch_id="+mchid+
				"&nick_name="+nickname+
				"&nonce_str="+noncestr+
				"&re_openid="+reopenid+
				"&remark="+remark+
				"&send_name="+sendname+
				"&total_amount="+totalamount+
				"&total_num="+totalnum+
				"&wishing="+wishing+
				"&wxappid="+wxappid+
				"&key=1924250b4c09247ec02edce69f6a2d56";
		String sign=MD5(stringSignTemp).toUpperCase();//签名
		
		String pr1="<xml>"+
		"<act_name>"+actname+"</act_name>"+
		"<client_ip>"+clientip+"</client_ip>"+
		"<mch_billno>"+mchbillno+"</mch_billno>"+
		"<mch_id>"+mchid+"</mch_id>"+
		"<nick_name>"+nickname+"</nick_name>"+
		"<nonce_str>"+noncestr+"</nonce_str>"+
		"<re_openid>"+reopenid+"</re_openid>"+
		"<remark>"+remark+"</remark>"+
		"<send_name>"+sendname+"</send_name>"+
		"<total_amount>"+totalamount+"</total_amount>"+
		//"<min_value>"+minvalue+"</min_value>"+
		//"<max_value>"+maxvalue+"</max_value>"+
		"<total_num>"+totalnum+"</total_num>"+
		"<wishing>"+wishing+"</wishing>"+
		"<wxappid>"+wxappid+"</wxappid>"+
		"<sign>"+sign+"</sign>"+
	"</xml>";
		
//		String pr1="<xml>"+
//		"<act_name>五一发红包</act_name>"+
//		"<client_ip>139.196.228.107</client_ip>"+
//		"<mch_billno>1397416502201703150924172627</mch_billno>"+
//		"<mch_id>1397416502</mch_id>"+
//		"<nick_name>优彼</nick_name>"+
//		"<nonce_str>5K8264ABCDEF16CQ2502SI8ZNMTM67RS</nonce_str>"+
//		"<re_openid>ogu7ywyS5bmjUNbhmMdrY-KATGr4</re_openid>"+
//		"<remark>发红包活动</remark>"+
//		"<send_name>国服卡牌</send_name>"+
//		"<total_amount>100</total_amount>"+
//		"<total_num>1</total_num>"+
//		"<wishing>五一快乐</wishing>"+
//		"<wxappid>wxd26b23a0c2ff805f</wxappid>"+
//		"<sign>A358DC436EBED430980DC3E53214D06C</sign>"+
//	    "</xml>";

		
		
		
		
		System.out.println("---------------------");
		System.out.println(pr1);
		InputStream in = null;
		StringBuilder sb = new StringBuilder();
		HttpEntity entity=null;
		try {
			//加载密钥 
  			File file=new File("C:/Users/Administrator/Desktop/cert/apiclient_cert.p12");//证书路径，这个路径本地测试可以，但在服务器中不对
  			FileInputStream fileInputStream=new FileInputStream(file);
  			KeyStore clientTrustKeyStore = KeyStore.getInstance("PKCS12");  
  			clientTrustKeyStore.load(fileInputStream, mchid.toCharArray());
  			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());  
  			kmf.init(clientTrustKeyStore, mchid.toCharArray()); 
  			TrustManager[] tm = { new MyX509TrustManager() }; 
  			SSLContext sslContext = SSLContext.getInstance("TLSv1");  
  			sslContext.init(kmf.getKeyManagers(), tm, new java.security.SecureRandom());
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			HttpPost httppost = new HttpPost(httpurl);
			httppost.setEntity(new StringEntity(pr1, "utf-8"));
			System.out.println(EntityUtils.toString(httppost.getEntity()));
			CloseableHttpResponse response = httpclient.execute(httppost);
			entity = response.getEntity();
			in=entity.getContent();
			byte[] bytes = new byte[1024];
			int len = 0;
			while((len = in.read(bytes)) !=-1){
				sb.append(new String(bytes, 0, len));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(in != null){
				try {
					in.close();
					EntityUtils.toString(entity);
				} catch (IOException e) {
				}finally{
					if(entity != null){
					}
				}
			}
		}
		  Map<String, String> map=readStringXmlOut(sb.toString());
	        if(map.get("result_code").equals("SUCCESS")){
	        	return "true";
	        }else{
	        	return "false";
	        }
	
    }  

	 public static Map<String, String> readStringXmlOut(String xml) {
	    	Map<String, String> map=new HashMap<String, String>();;
	        Document doc = null;
	        int num=0;
	        try {
	        	
	            doc = DocumentHelper.parseText(xml); // 
	        	Element employees=doc.getRootElement();   
//	        	for(Iterator i = employees.elementIterator(); i.hasNext();){   
//		        	Element employee = (Element) i.next();   
		        	for(Iterator j = employees.elementIterator(); j.hasNext();){   
			        	Element node=(Element) j.next();  
//			        	System.out.println(node.getName()+":"+node.getText());   
			        	if(map.size()>0 && null!=map.get(node.getName())){
			        		map.put(node.getName()+String.valueOf(num), node.getText());
			        		System.out.println(node.getName()+String.valueOf(num)+":"+node.getText());   
			        	}else{
			        		map.put(node.getName(), node.getText());
			        		System.out.println(node.getName()+":"+node.getText());   
			        	}
//		        	}   
		        	num++;
	        	}   
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return map;
	    }
	
	public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
          //  System.out.println("MD5(" + sourceStr + ",32) = " + result);
           // System.out.println("MD5(" + sourceStr + ",16) = " + buf.toString().substring(8, 24));
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }
	
	
	public static void main(String args[]) throws Exception{  
		
}  
}
