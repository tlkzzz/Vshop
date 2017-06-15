package com.Vshop.service.module.sms.service;

public interface ISendSmsManager {
	
	public String sendSms(String userid,String pass,String mobiles,String msg,String time);
	
}
