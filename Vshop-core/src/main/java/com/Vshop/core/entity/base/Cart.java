package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;
// default package

/**
 * 购物车
 * ShopCart entity. @author MyEclipse Persistence Tools
 */
@Data
@ToString
public class Cart{

	// Fields
	
	/**
	 * 购物车id
	 */
	private Integer cartId;
	
	/**
	 * 会员id
	 */
	private Integer memberId;
	
	/**
	 * 店铺id
	 */
	private Integer storeId;
	
	/**
	 * 店铺名称
	 */
	private String storeName;
	
	/**
	 * 商品id
	 */
	private Integer goodsId;
	
	/**
	 * 商品名称
	 */
	private String goodsName;
	
	/**
	 * 商品价格
	 */
	private Double goodsPrice;
	
	/**
	 * 购买商品数量
	 */
	private Short goodsNum;
	
	/**
	 * 商品图片
	 */
	private String goodsImages;

	private String cartIds;

	//private Integer goodsStorage;
	
	/**
	 * 商品规格id
	 */
	private Integer specId;
	
	/**
	 * 商品规格内容
	 */
	private String specInfo;
}