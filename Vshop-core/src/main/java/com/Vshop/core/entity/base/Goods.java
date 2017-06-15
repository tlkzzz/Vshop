package com.Vshop.core.entity.base;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.Vshop.core.common.DateUtils;
import lombok.Data;

/**
 * @author cgl
 * 时间: 2015年06月10日16:20:41
 * 商品实体类
 */
@Data
public class Goods extends BaseEntity{
	
	/**
	 * 主键ID
	 */
	private Integer goodsId;
	
	/**
	 * 商品名称
	 */
	private String goodsName;
	
	/**
	 * 商品副标题
	 */
	private String goodsSubtitle;	
	
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
	 * 供应商id
	 */
	private Integer supplierId;
	
	/**
	 * 供应商名称
	 */
	private String supplierName;
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
	  * 商品规格开启状态，1开启，0关闭
	  */
	 private Integer specOpen;
	
	 /**
	  * 商品默认对应的规格id
	  */
	 private Integer specId;

	 /**
	  * 规格名称
	  */
	 private String specName;
	 
	 /**
	  * 商品默认封面图片
	  */
	 private String goodsImage;
	 
	 /**
	  * 商品多图
	  */
	 private String goodsImageMore;
	 
	 /**
	  * 商品店铺价格
	  */
	 private BigDecimal goodsStorePrice;
	 /**
	  * 市场价格
	  */
	 private BigDecimal goodsMarketPrice;
	 
	 
	 /**
	  * 成本价格
	  */
	 private BigDecimal goodsCostPrice;
	 
	 /**
	  * 价格区间
	  */
	 private String goodsStorePriceInterval;
	 
	 /**
	  * 商品货号
	  */
	 private String goodsSerial;
	 
	 /**
	  * 商品上架1下架0
	  */
	 private Integer goodsShow;
	 
	 /**
	  * 商品浏览数
	  */
	 private Integer goodsClick;
	 
	 /**
	  * 商品状态，0开启，1违规下架	
	  */
	 private Integer goodsState;
	 
	 /**
	  * 商品推荐
	  * 是:1 否:0
	  */
	 private Integer goodsCommend;
	 
	 /**
	  * 商品添加时间
	  */
//	 private Long goodsAddTime;
	 
	 /**
	  * 商品关键字
	  */
	 private String goodsKeywords;
	 
	 /**
	  * 商品描述 
	  */
	 private String goodsDescription;
	 
	 /**
	  * 商品详细内容
	  */
	 private String goodsBody;
	 
	 /**
	  * 商品属性 
	  */
	 private String goodsAttr;
	 
	 /**
	  * 商品规格
	  */
	 private String goodsSpec;
	 
	 /**
	  * 颜色自定义图片
	  */
	 private String goodsColImg;
	 
	 /**
	  * 发布时间
	  */
	 //private Long prepareUpTime;
	 
	 /**
	  * 发布开始时间
	  */
	 //private Long goodsStarttime;
	 
	 /**
	  * 发布结束时间
	  */
	 //private Long goodsEndtime;
	 
	 /**
	  * 商品类型,1为全新、2为二手
	  */
	 private Integer goodsForm; 
	 
	 /**
	  * 运费模板ID，不使用运费模板值为0
	  */
	 private Integer transportId;
	 
	 /**
	  * 平邮
	  */
	 private BigDecimal pyPrice;
	 
	 /**
	  * 快递
	  */
	 private BigDecimal kdPrice;
	 
	 /**
	  * EMS
	  */
	 private BigDecimal esPrice;
	 
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
	  * 商品违规下架原因
	  */
	 private String goodsCloseReason;
	 
	 /**
	  * 商品所在店铺状态 0开启 1关闭
	  */
	 private Integer goodsStoreState;
	 
	 /**
	  * 评论次数
	  */
	 private Integer commentnum;
	 
	 /**
	  * 售出数量
	  */
	 private Integer salenum;
	 
	 /**
	  * 商品收藏数量
	  */
	 private Integer goodsCollect;
	 
	 /**
	  * 商品运费承担方式 默认 0为买家承担 1为卖家承担
	  */
	 private Integer goodsTransfeeCharge;
	 
	 /**
	  * 店铺自定义分类id
	  */
	 private Integer storeClassId;
	 
	 /**
	  * 是否已删除  0:未删除  1:已删除
	  */
	 //private Integer isDel;
	 
	 /**
	  * 商品的库存,在存数据库的时候不用这个字段,只在取的时候用到
	  */
	 private Integer goodsTotalStorage;
	 
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
	 /**
	  * 栏目里包括的ids
	  */
	 private String[] goodids;
	 
	 private String existsStore;
	 
	 /**
	 * 学院中检测是否下架的标识
	 */
	private Integer storeGoodsShow;
	 /**
	 * 是否发码
	 */
	private Integer isCode;
	
	
	/**
	 * 佣金比例
	 */
	private BigDecimal goodsCommissionRate;
	
	/**
	 * 产品位通过审核理由
	 */
	private String goodsCloseInfo;
	
	
	
	
}
