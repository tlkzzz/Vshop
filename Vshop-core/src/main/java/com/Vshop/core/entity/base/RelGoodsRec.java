package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;

/**
 * 商品栏目与商品关联
 */
@Data
@ToString
public class RelGoodsRec {

	private Integer relId;
	
	/**
	 *新品上市、老师力荐
	 */
	private Integer reCommendId;
	
	/**
	 *课程Id
	 */
	private Integer goodsId;
	
	/**
	 * 学院Id
	 */
	private Integer storeId;
	
	/**
	 * 推荐首页显示
	 */
	private Integer goodsCommend;
	
	/**
	  * 需要排序的字段
	  */
	 private String sortField;
	 
	 /**
	  * 时间排序,降序desc,升序asc
	  */
	 private String orderBy;
}
