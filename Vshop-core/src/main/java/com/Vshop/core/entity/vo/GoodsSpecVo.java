package com.Vshop.core.entity.vo;

import lombok.Data;
import lombok.ToString;


/**
 * 2015年06月29日17:58:33
 * @author cgl
 * 这个实体类不用于存数据
 */
@Data
@ToString
public class GoodsSpecVo {
	/**
	 * 规格id
	 */
	private Integer spId;
	/**
	 * 规格名称
	 */
	private String spName;
	/**
	 * 规格值id
	 */
	private Integer spValueId;
	/**
	 * 规格值名称
	 */
	private String spValueName;
	
	/**
	 * 对应的图片
	 */
	private String colImg;
}
