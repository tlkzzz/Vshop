package com.Vshop.service.utils.lucene;


import java.io.Serializable;

import lombok.Data;
import lombok.ToString;
 
@Data
@ToString
public class FilterCondition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5739313559657517187L;


	/**
	 * 筛选的名称
	 */
	private String filterName;
	
	
	/**
	 * 筛选的数据
	 */
	private String conditionData;
}
