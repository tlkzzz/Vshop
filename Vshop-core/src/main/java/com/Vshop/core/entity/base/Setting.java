package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;

/**
 * 系统设置
 * @author liukai
 */
@Data
@ToString
public class Setting {

	/**
	 * 主键名称
	 */
	private String name;

	/**
	 * 值
	 */
	private String value;
	
}
