package com.Vshop.core.entity.base;

import lombok.Data;

/**
 * 订单地址信息表
 * @author liukai
 */
@Data
public class OrderAddress {
	
	/**
	 * 收货地址id
	 */
	private Integer addressId;
	
	/**
	 * 会员id
	 */
	private Integer memberId;
	
	/**
	 * 会员姓名
	 */
	private String trueName;
	
	/**
	 * 地区id
	 */
	private Integer areaId;
	
	/**
	 * 市级id
	 */
	private Integer cityId;
	
	/**
	 * 地区内容
	 */
	private String areaInfo;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 座机电话
	 */
	private String telPhone;
	
	/**
	 * 手机电话
	 */
	private String mobPhone;
	
	/**
	 * 默认收货地址
	 */
	private String isDefault;
	
	/**
	 * 省级id
	 */
	private Integer provinceId;
	
	/**
	 * 邮编
	 */
	private Integer zipCode;

}
