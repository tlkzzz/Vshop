package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;
// default package

/**
 * 项目名称：Vshop-entity   
 * 类名称：Address   
 * 类描述：   
 * 修改备注：   
 * @version    
 *
 */
@Data
@ToString
public class Address{
	
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