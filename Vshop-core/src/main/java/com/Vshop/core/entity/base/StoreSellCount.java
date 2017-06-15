package com.Vshop.core.entity.base;
import java.sql.Date;


import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class StoreSellCount {

	/**
	 * 商品id
	 */
	private Integer goodsId;
	
	/**
	 * 商品名称
	 */
	private String goodsName;
	
	/**
	 * 出售数量
	 */
	private Integer goodsNum;
	
	/**
	 * 订单完成时间(精确到天)
	 */
	private Date finnshedTime;
	
	/**
	 * 店铺id
	 */
	private Integer storeId;
	
	/**
	 * 开始时间
	 */
	private Long startTime;
	
	/**
	 * 结束时间
	 */
	private Long endTime;
}
