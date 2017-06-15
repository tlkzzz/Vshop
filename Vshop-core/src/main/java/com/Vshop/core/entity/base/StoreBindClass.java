package com.Vshop.core.entity.base;


import java.util.Date;

import lombok.Data;
import lombok.ToString;

/**
 * 
 * 
 * @项目名称：Vshop-entity
 * @类名称：StoreBindClass
 * @类描述： 店铺可发布商品类目表
 * @创建人：shining
 * @创建时间：2014年12月2日 下午7:41:20
 * @修改人：shining
 * @修改时间：2014年12月2日 下午7:41:20
 * @修改备注：
 * @version
 * 
 */
@Data
@ToString
public class StoreBindClass {
	private Integer bid;
	private Integer storeId;
	private Double commisRate;
	private Integer class1;
	private Integer class2;
	private Integer class3;

	private String class1Name;
	private String class2Name;
	private String class3Name;
	/**
	 * 0:未删除;1.已删除
	 */
	private int isDel;
	/**
	 * 创建时间
	 */
	private Long createdTime;
	/**
	 * 更新时间
	 */
	private Long updatedTime;
}
