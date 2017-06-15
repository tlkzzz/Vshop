package com.Vshop.core.entity.base;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.ToString;

/**
 * 优惠券
 * @author kviuff
 * @date 2015-07-27 17:50:00
 */
@Data
@ToString
public class Coupon extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 4028365179770564469L;
	
	/**
	 * 优惠id
	 */
	private Integer couponId;
	
	/**
	 * 优惠名称
	 */
	private String couponTitle;
	
	/**
	 * 优惠券分类
	 */
	private String couponType;
	
	/**
	 * 优惠券图片
	 */
	private String couponPic;
	
	/**
	 * 优惠券描述
	 */
	private String couponDesc;
	
	/**
	 * 优惠金额
	 */
	private BigDecimal couponPrice;
	
	/**
	 * 金额限制
	 */
	private BigDecimal couponLimit;
	
	/**
	 * 是否使用:0没有使用，1已使用
	 */
	private Integer couponIsUser;
	
	/**
	 * 店铺id
	 */
	private Integer storeId;
	
	/**
	 * 上、下架状态  1下架，0上架
	 */
	private Integer couponState;
	
	/**
	 * 总数量
	 */
	private Integer couponstorage;
	
	/**
	 * 使用数量
	 */
	private Integer couponusage;
	
	/**
	 * 是否锁定：1锁定，0未锁定
	 */
	private Integer couponIock;
	
	/**
	 * 分类
	 */
	private Integer couponClassId;
	
	/**
	 * 点击次数
	 */
	private Integer coupClick;
	
	/**
	 * 
	 */
	private String couponPrintStyle;
	
	/**
	 * 0不推荐 1推荐到首页
	 */
	private Integer couponRecommend;
	
	/**
	 * 审核状态 0为待审核 1已通过 2未通过
	 */
	private Integer couponAllowState;
	
	/**
	 * 审核备注
	 */
	private String couponAllowmark;
	
	/**
	 * 店铺名称
	 */
	private String storeName;
	
	/**
	 * 分类名称
	 */
	private String couponTypeName;
	
	private Integer sponsorId;
	
}
