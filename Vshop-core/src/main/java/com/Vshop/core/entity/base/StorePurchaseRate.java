package com.Vshop.core.entity.base;

import java.math.BigDecimal;
import java.sql.Date;


import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class StorePurchaseRate {

	/**
	 * 商品id
	 */
	private Integer goodsId;
	
	/**
	 * 商品名称
	 */
	private String goodsName;
	
	/**
	 * 商品点击量
	 */
	private BigDecimal click;
	
	
	/**
	 * 出售数量
	 */
	private BigDecimal saleCount;
	
}
