/**
 * 
 */
package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;

/**
 * <p>Title: Feedback.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年8月25日
 * @version 1.0
 */
@Data
@ToString
public class Feedback {
	/**
	 * id
	 */
	private int id;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 电话
	 */
	private String phone;
	
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 生成时间
	 */
	private Long createTime;
	
	/**
	 * 设备类型
	 */
	private Integer phoneType;

}
