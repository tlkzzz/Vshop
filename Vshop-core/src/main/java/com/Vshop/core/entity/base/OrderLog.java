package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.base.BaseEntity;

/**
 * ShopOrderLog entity. @author MyEclipse Persistence Tools
 */
@Data
@ToString
public class OrderLog extends BaseEntity{
	
	/**
	 * 订单处理历史索引id
	 */
	private Integer logId;
	
	/**
	 * 订单id
	 */
	private Integer orderId;
	
	/**
	 * 订单状态信息
	 */
	private String orderState;
	
	/**
	 * 下一步订单状态信息
	 */
	private String changeState;
	
	/**
	 * 订单状态描述
	 */
	private String stateInfo;
	
	/**
	 * 操作人
	 */
	private String operator;
}