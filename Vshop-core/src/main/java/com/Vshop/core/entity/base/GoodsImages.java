package com.Vshop.core.entity.base;

import lombok.Data;
// default package

/**
 * ShopncGoodsImages entity. @author MyEclipse Persistence Tools
 */
@Data
public class GoodsImages{

	// Fields

	private Integer goodsImageId;
	private Integer goodsCommonid;
	private Integer storeId;
	private Integer colorId;
	private String goodsImage;
	private Short goodsImageSort;
	private Short isDefault;
	
	private String goodsAllImgs;

}