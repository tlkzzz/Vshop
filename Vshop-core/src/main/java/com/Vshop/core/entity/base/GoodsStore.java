package com.Vshop.core.entity.base;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.Vshop.core.common.DateUtils;

import lombok.Data;

/**
 * @author cgl
 * 时间: 2015年06月10日16:20:41
 * 商品店铺上架商品类
 */
@Data
public class GoodsStore extends BaseEntity{
	
	/**
	 * 主键ID
	 */
	private Integer goodsId;
	
	/**
	 * 店铺id
	 */
	 private Integer storeId;
	 
	 /**
	  * 店铺名称
	  */
	 private String storeName;
	 
	 /**
	  * 商品店铺价格
	  */
	 private BigDecimal goodsStorePrice;
	 
	 /**
	  * 商品上架1下架0
	  */
	 private Integer goodsShow;
	 
	 
	 /**
	  * 商品推荐
	  * 是:1 否:0
	  */
	 private Integer goodsCommend;
	 
	 /**
	  * 店铺自定义分类id
	  */
	 private Integer storeClassId;
	 
	 
	 /**
	  * 商品所在店铺状态 0开启 1关闭
	  */
	 private Integer goodsStoreState;
	 
	 /**
	  * 需要排序的字段
	  */
	 private String sortField;
	 
	 /**
	  * 时间排序,降序desc,升序asc
	  */
	 private String orderBy;
	 /**
	  * 栏目里包括的ids
	  */
	 private String[] goodids;
	 
	 /**
	 * 学院中的商品
	 */
	private Goods goods;
	 
	
	
	
}
