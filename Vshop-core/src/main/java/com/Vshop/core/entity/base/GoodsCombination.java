package com.Vshop.core.entity.base;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 组合商品
 * @author chen
 * 2015年08月14日10:56:44
 */
@Data
public class GoodsCombination {
	
	/**
	 * 商品id
	 */
	private Integer goodsId;

	/**
	 * 被组合的商品id
	 */
	private Integer combinationGoodsId;
	
	/**
	 * 商品名
	 */
	private String combinationGoodsName;
	
	/**
	 * 组合商品的图片
	 */
	private String combinationGoodsImg;
	
	/**
	 * 组合商品的价格
	 */
	private BigDecimal combinationGoodsPrice;
	
	/**
	 * 所有被组合的商品id,以逗号分隔
	 */
	private String allCombinationGoodsIdStr;
	
}
