package com.Vshop.core.entity.base;

import java.math.BigDecimal;


import lombok.Data;
import lombok.ToString;

/**
 *    
 * 项目名称：Vshop-entity   
 * 类名称：pointsGoods   
 * 类描述：   
 * 创建人：cgl
 * 创建时间：2015年08月26日10:39:48
 * 修改人：cgl   
 * 修改时间：2015年08月26日10:39:55
 * 修改备注：   
 * @version    
 *
 */
@Data
@ToString
public class PointsGoods {
	/**
	 * 主键ID
	 */
	private Integer pointsGoodsId;
	
	/**
	 * 规格商品id
	 */
	private Integer goodsSpecId;
	
	/**
	 * 商品名称
	 */
	private String pointsGoodsName;
	
	/**
	 * 商品副标题
	 */
	private String pointsGoodsSubtitle;	
	
	/**
	 * 商品分类id
	 */
	private Integer gcId;
	
	/**
	 * 商品分类名称
	 */
	private String gcName;
	
	/**
	 * 商品品牌id
	 */
	private Integer brandId;
	
	/**
	 * 商品品牌名称
	 */
	private String brandName;
	
	/**
	 * 类型id
	 */
	private Integer typeId;
	
	/**
	 * 店铺id
	 */
	 private Integer storeId;
	 
	 /**
	  * 店铺名称
	  */
	 private String storeName;
	 
	 /**
	  * 商品默认封面图片
	  */
	 private String pointsGoodsImage;
	 
	 /**
	  * 商品多图
	  */
	 private String pointsGoodsImageMore;
	 
	 /**
	  * 商品店铺价格
	  */
	 private BigDecimal pointsGoodsStorePrice;
	 
	 /**
	  * 兑换商品所需要的积分
	  */
	 private Integer pointsNums;
	 
	 /**
	  * 商品货号
	  */
	 private String pointsGoodsSerial;
	 
	 /**
	  * 商品上架1上架0下架
	  */
	 private Integer pointsGoodsShow;
	 
	 /**
	  * 商品浏览数
	  */
	 private Integer pointsGoodsClick;
	 
	 /**
	  * 商品推荐
	  * 是:1 否:0
	  */
	 private Integer pointsGoodsCommend;
	 
	 /**
	  * 商品添加时间
	  */
	 private Long pointsGoodsAddTime;
	 
	 /**
	  * 商品详细内容
	  */
	 private String pointsGoodsBody;
	 
	 /**
	  * 商品属性 
	  */
	 private String pointsGoodsAttr;
	 
	 /**
	  * 商品规格
	  */
	 private String pointsGoodsSpec;
	 
	 /**
	  * 兑换开始时间
	  */
	 private Long pointsGoodsStarttime;
	 
	 /**
	  * 兑换结束时间
	  */
	 private Long pointsGoodsEndtime;
	 
	 /**
	  * 商品所在地(市)
	  */
	 private Integer cityId;
	 /**
	  * 商品所在地(市)的名字
	  */
	 private String cityName;
	 
	 /**
	  * 商品所在地(省)
	  */
	 private Integer provinceId;
	 
	 /**
	  * 商品所在地(省)的名字
	  */
	 private String provinceName;
	 
	 /**
	  * 售出数量
	  */
	 private Integer salenum;
	 
	 /**
	  * 商品收藏数量
	  */
	 private Integer pointsGoodsCollect;
	 
	 /**
	  * 商品运费承担方式 默认 0为买家承担 1为卖家承担
	  */
	 private Integer pointsGoodsTransfeeCharge;
	 
	 /**
	  * 是否已删除  0:未删除  1:已删除
	  */
	 private Integer isDel;
	 
	 /**
	  * 要求会员等级
	  */
	 private Integer memberGradeId;
	 
	 /**
	  * 每个会员可兑换数量
	  */
	 private Integer exchangeCount;
	 
	 /**
	  * 商品的库存
	  */
	 private Integer pointsGoodsStorage;
	 
	 /**
	  * 分类路径classPath
	  */
	 private String classPath;
	 
	 /**
	  * 需要排序的字段
	  */
	 private String sortField;
	 
	 /**
	  * 时间排序,降序desc,升序asc
	  */
	 private String orderBy;
}
