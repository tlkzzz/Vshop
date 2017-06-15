package com.Vshop.core.entity.base;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class GoodsSpecIndex {

	private Integer goodsId;
	private Integer gcId;
	private Integer typeId;
	private Integer spId;
	private Integer spValueId;
	private String spValueName;
	
	/**
	 * 价格
	 */
	private BigDecimal goodsStorePrice;
	
	 /**
	  * 商品浏览数
	  */
	 private Integer goodsClick;
	 
	 /**
	  * 售出数量
	  */
	 private Integer salenum;
	 
	 /**
	  * 省级id
	  */
	 private Integer provinceId;
}
