package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class BalanceReport {

	/**
	 * 会员id
	 */
	private Integer storeId;
	
	/**
	 * 会员名称
	 */
	private String storeName;
	
	/**
	 * 结算时间
	 */
	private String balanceTime;
	
	/**
	 * 已经结算数量
	 */
	private Integer balanceCount;
	
	/**
	 * 未经结算数量
	 */
	private Integer notBalanceCount;
	
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
