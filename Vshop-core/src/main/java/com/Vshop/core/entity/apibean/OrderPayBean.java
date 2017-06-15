package com.Vshop.core.entity.apibean;

import java.math.BigDecimal;

import lombok.Data;
import lombok.ToString;

/**
 * 订单支付表,(父订单),多个订单一块支付
 * ShopOrderPay entity. @author MyEclipse Persistence Tools
 */
@Data
@ToString
public class OrderPayBean{

	private Integer payId;
	//支付单号
	private String paySn;
	//买家ID
	private Integer buyerId;
	//0默认未支付1已支付(只有第三方支付接口通知到时才会更改此状态)
	private String apiPayState;
	//支付金额
	private BigDecimal payAmount;
	//订单状态(单个订单的时候使用,多个订单为空值)
	private Integer orderState;

}