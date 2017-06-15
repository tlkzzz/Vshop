package com.Vshop.core.entity.base;

import java.sql.Date;


import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class StoreTotalCount {
	
	/**
	 * 出售数量
	 */
	private Integer goodsNum;
	
	/**
	 * 订单完成时间(精确到天)
	 */
	private String finnshedTime;
	
	/**
	 * 店铺id
	 */
	private Integer storeId;
	
	/**
	 * 店铺名称
	 */
	private String storeName;
	
	/**
	 * 开始时间
	 */
	private Long startTime;
	
	/**
	 * 结束时间
	 */
	private Long endTime;
	
	/**
	 * 查询条件(week,month,year)
	 */
	private String condition;

}
