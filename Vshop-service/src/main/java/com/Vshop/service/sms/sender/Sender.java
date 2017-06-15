package com.Vshop.service.sms.sender;

import org.apache.commons.lang3.StringUtils;

import com.Vshop.service.sms.core.HttpSender;
import com.Vshop.service.sms.sender.client.Message;
import com.Vshop.service.sms.sender.client.Result;

public class Sender {

	// private static final String URL = "http://222.73.117.158/msg/HttpBatchSendSM";// 应用地址
	private static final String URL = "http://222.73.117.156/msg/HttpBatchSendSM";// 应用地址
	private static final String USERNAME = "cishishij";// 账号
	private static final String PASSWORD = "Txb123456";// 密码
	private static final boolean NEEDSTATUS = false;// 是否需要状态报告，需要true，不需要false
	private static final String PRODUCT = "281242299";// 产品ID
	private static final String EXTNO = null;// 扩展码
	
	public static Result send(Message message) throws Exception{
		String _result = HttpSender.batchSend(URL, USERNAME, PASSWORD, message.getMobiles(), message.getMessage(), NEEDSTATUS, PRODUCT, EXTNO);
		Result resutl = new Result();
		if(StringUtils.isNoneBlank(_result)){
			String[] results = StringUtils.split(_result, ",");
			resutl.setSendDate(Long.valueOf(results[0]));
			resutl.setStatus(Integer.valueOf(results[1]));
		}else{
			throw new RuntimeException("发送信息异常");
		}
		
		return resutl;
	}
	
	public static Result send(String mobile, String message) throws Exception{
		String _result = HttpSender.batchSend(URL, USERNAME, PASSWORD, mobile, message, NEEDSTATUS, PRODUCT, EXTNO);
		
		freemarker.log.Logger.getLogger(Sender.class.getName()).info(String.format("execute result: ", _result));
		
		Result resutl = new Result();
		if(StringUtils.isNoneBlank(_result)){
			String[] results = StringUtils.split(_result, ",");
			resutl.setSendDate(Long.valueOf(results[0]));
			resutl.setStatus(Integer.valueOf(results[1]));
		}else{
			throw new RuntimeException("发送信息异常");
		}
		
		return resutl;
	}
}
