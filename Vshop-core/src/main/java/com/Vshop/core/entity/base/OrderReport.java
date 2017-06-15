package com.Vshop.core.entity.base;

import java.math.BigDecimal;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderReport {
	
	/**
	 * 店铺id
	 */
	private Integer storeId;
	
	/**
	 * 店铺名称
	 */
	private String storeName;
	
	/**
	 * 订单数量
	 */
	private Integer orderCount;
	
	/**
	 * 订单金额
	 */
	private BigDecimal orderAmount;
	
	/**
	 * 时间
	 */
	private String finishTime;

	/**
	 * 时间条件
	 * 按这周查:week
	 * 按这个月查:month
	 * 按今年年查:year
	 */
	private String condition;
	
	/**
	 * 开始时间
	 */
	private Long startTime;
	
	/**
	 * 结束时间
	 */
	private Long endTime;
}
