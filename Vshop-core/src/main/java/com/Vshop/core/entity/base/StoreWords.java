package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;
/**
 * 店铺关键词
 * @author cgl
 * 2015年08月31日15:10:53
 */
@Data
@ToString
public class StoreWords {
	
	/**
	 * 主键
	 */
	private Integer wordsId;
	
	/**
	 * 关键词
	 */
	private String keyword;
	
	/**
	 * 全拼
	 */
	private String quanPing;
	
	/**
	 * 关键词首字母
	 */
	private String shouZiMu;
	
	/**
	 * 关键词出现次数
	 */
	private Integer wordsNum;
	
	/**
	 * 修改时间
	 */
	private Long updateTime;
	
	/**
	 * 商品的单位
	 */
	private String unit;
}
