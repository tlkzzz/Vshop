/**
 * 
 */
package com.Vshop.core.entity.base;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
import lombok.ToString;

/**
 * <p>Title: PromotionClass.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月21日
 * @version 1.0
 */
@Data
@ToString
public class PromotionClass extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6740624272618257483L;

	/**
	 * 分类id
	 */
	private Integer pcId;
	
	/**
	 * 分类名称
	 */
	private String pcName;
	/**
	 * 排序
	 */
	private int pcSort ;
	
	/**
	 * 开始时间
	 */
//	private Long pcStartTime;
	
	/**
	 * 结束时间
	 */
//	private Long pcEndTime;
	
	/**
	 * 状态
	 */
	private int pcStatus;
	
	/**
	 * 添加时间
	 */
//	private Long pcCreateTime;
	

}
