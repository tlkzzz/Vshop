package com.Vshop.core.entity.base;



import lombok.Data;
import lombok.ToString;
// default package

/**
 * 规格值表
 */
@Data
@ToString
public class SpecValue{

	/**
	 * 规格值id
	 */
	private Integer spValueId;
	
	/**
	 * 规格值名称
	 */
	private String spValueName;
	
	/**
	 * 所属规格id
	 */
	private Integer spId;
	
	/**
	 * 规格图片
	 */
	private String spValueImage;
	
	/**
	 * 排序
	 */
	private Integer spValueSort;
	
}