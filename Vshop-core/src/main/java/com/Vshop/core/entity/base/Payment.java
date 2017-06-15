package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;

/**
 * 支付方式表
 * ShopPayment entity. @author MyEclipse Persistence Tools
 */
@Data
@ToString
public class Payment extends BaseEntity {
	
	//支付索引id
	private Long paymentId;
	
	//支付代码名称
	private String paymentCode;
	
	//支付名称
	private String paymentName;
	
	//支付接口配置信息
	private String paymentConfig;
	
	//接口状态0禁用1启用
	private String paymentState;
	
	//支付logo
	private String paymentLogo;

}