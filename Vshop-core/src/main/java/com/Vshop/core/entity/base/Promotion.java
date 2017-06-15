/**
 * 
 */
package com.Vshop.core.entity.base;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * <p>Title: Promotion.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月21日
 * @version 1.0
 */
@Data
@ToString
public class Promotion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7907104889919061875L;
	/**
	 * id
	 */
	private Integer Id;
	/**
	 * 分类名称
	 */
	private String Name;
	/**
	 * 起始值
	 */
	private Double startValue;
	/**
	 * 优惠值
	 */
	private Double promoteValue;
	/**
	 * 分类id
	 */
	private Integer pcId;
	
	/**
	 * 分类名字
	 */
	private String pcName;
	/**
	 * 状态
	 */
	private Integer status;
	
	/**
	 * 排序
	 */
	private Integer sort;

}
