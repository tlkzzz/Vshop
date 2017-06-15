package com.Vshop.core.entity.base;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 手机端商品列表api接口用到的商品属性
 * 
 * @author kviuff
 * @date 2015-07-20 17:50:00
 */
@Data
public class GoodsDetailBean {
	/**
	 * 主键ID
	 */
	private Integer goodsId;

	/**
	 * 商品名称
	 */
	private String goodsName;

	/**
	 * 商品副标题
	 */
	private String goodsSubtitle;

	/**
	 * 商品默认封面图片
	 */
	private String goodsImage;

	/**
	 * 商品店铺价格
	 */
	private BigDecimal goodsStorePrice;

	/**
	 * 评论次数
	 */
	private Integer commentnum;

	/**
	 * 商品运费承担方式 默认 0为买家承担 1为卖家承担
	 */
	private Integer goodsTransfeeCharge;

	/**
	 * 商品所在地(市)的名字
	 */
	private String cityName;
	
	/**
	 * 商品评分
	 */
	private BigDecimal evaluate;
	
	/**
	 * 商品销量
	 */
	private Integer salenum;
	
	/**
	  * 商品收藏数量
	  */
	 private Integer goodsCollect;
}
