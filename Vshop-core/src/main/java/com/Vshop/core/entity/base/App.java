/**
 * 
 */
package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;

/**
 * <p>Title: App.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年11月4日
 * @version 1.0
 */
@Data
@ToString
public class App {
	
	private Integer id;
	
	private String domain;
	
	private String mac;

	private String title;
	
	private Long createTime;

}
