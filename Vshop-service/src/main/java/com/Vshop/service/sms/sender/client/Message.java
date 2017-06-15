package com.Vshop.service.sms.sender.client;

import java.util.List;

import jersey.repackaged.com.google.common.base.Joiner;

import org.apache.commons.lang.Validate;

public class Message {
	private static String TEMPLATE = "尊贵的课程汇学员您好，您购买的课程【%s】订单号：%s，消费凭证【%s】。咨询热线：400 016 4018";
	private List<String> mobiles;
	private String message;
	
	public String getMobiles()throws Exception {
		return Joiner.on(",").join(mobiles);
	}
	
	public void setMobiles(List<String> mobiles)throws Exception {
		Validate.noNullElements(mobiles, "手机号不能为空");
		this.mobiles = mobiles;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String className, String orderSn, String code) {
		Validate.notEmpty(className, "课程名不能为空");
		Validate.notEmpty(orderSn, "订单号不能为空");
		Validate.notEmpty(code, "消费凭证不能为空");
		this.message = String.format(TEMPLATE, className, orderSn, code);
	}
}
