package com.Vshop.core.entity.base;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * 优惠券分类实体
 * @author kviuff
 * @date 2015-07-23 10:00:00
 */
@Data
@ToString
public class CouponClass implements Serializable{
	
	private static final long serialVersionUID = 4028365179770564469L;
	
	/**
	 * 分类id
	 */
	private Integer classId;
	/**
	 * 父类id
	 */
	private Integer classParentId;
	/**
	 * 分类名称
	 */
	private String className;
	/**
	 * 分类排序
	 */
	private Integer classSort;
	/**
	 * 是否显示 0显示，1不显示
	 */
	private Integer classShow;
	
}
