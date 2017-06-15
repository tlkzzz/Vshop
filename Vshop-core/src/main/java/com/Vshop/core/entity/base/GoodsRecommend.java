package com.Vshop.core.entity.base;



import lombok.Data;

/**
 * 推荐商品栏目
 * @author guo
 * 2015年08月24日16:17:44
 */
@Data
public class GoodsRecommend extends BaseEntity{
	private Integer reCommendid;
	/**
	 *栏目名称
	 */
	private String recommendName;
	/**
	 *栏目描述
	 */
	private String recommendInfo;
	/**
	 *是否启用0启用,1停用
	 */
	private Integer recommendUse;
}
