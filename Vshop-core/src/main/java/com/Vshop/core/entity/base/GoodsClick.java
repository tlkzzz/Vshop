package com.Vshop.core.entity.base;



import lombok.Data;

@Data
public class GoodsClick extends BaseEntity{

	/**
	 * 店铺id
	 */
	private Integer storeId;
	
	/**
	 * 商品id
	 */
	private Integer goodsId;
	
	/**
	 * 商品名称
	 */
	private String goodsName;
	
	/**
	 * 点击量
	 */
	private Integer goodsClick;
	
	/**
	 * 访问时间
	 */
	private String loginTime;

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
	//private Long startTime;
	
	/**
	 * 结束时间
	 */
	//private Long endTime;
}
