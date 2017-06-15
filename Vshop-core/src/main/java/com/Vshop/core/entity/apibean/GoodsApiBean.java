package com.Vshop.core.entity.apibean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.Vshop.core.entity.base.EvaluateStore;
import com.Vshop.core.entity.vo.GoodsSpecVo;

import lombok.Data;
import lombok.ToString;

/**
 * 
 * @author Administrator
 *
 */
@Data
@ToString
public class GoodsApiBean {

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
	 * 商品描述
	 */
	private String goodsDescription;

	/**
	 * 商品店铺价格
	 */
	private BigDecimal goodsStorePrice;

	/**
	 * 店铺id
	 */
	private Integer storeId;

	/**
	 * 店铺名称
	 */
	private String storeName;

	/**
	 * 店铺店标
	 */
	private String storeLabel;

	/**
	 * 店铺分类名称
	 */
	private String storeclassname;

	/**
	 * 商品相册
	 */
	private List<String> goodsCallyList;

	/**
	 * 商品属性
	 */
	// private List<GoodsAttrVo> goodsAttrList;

	/**
	 * 规格名称
	 */
	private String specName;

	/**
	 * 商品运费承担方式 默认 0为买家承担 1为卖家承担
	 */
	private Integer goodsTransfeeCharge;

	/**
	 * 评论次数
	 */
	private Integer commentnum;

	/**
	 * 商品所在地(市)的名字
	 */
	private String cityName;
	
	/**
	 * 商品评分
	 */
	private BigDecimal evaluate;

	/**
	 * 商品的库存
	 */
	private Integer goodsTotalStorage;

	/**
	 * 商品规格值
	 */
	private Map<String, List<GoodsSpecVo>> goodsSpecValueAll;
	
	/**
	 * 商品店铺评分
	 */
	private EvaluateStore evaluateStore;
	
	/**
	 * 商品评论(两条)
	 */
	private List<EvaluateGoodsApiBean> evaluateGoodsList;
	
	/**
	 * 促销规则
	 */
	private String promotionClass;
	
	/**
	 * 商品是否已收藏
	 */
	private int isFav;
	
	/**
	 * 会员购物车数量总合
	 */
	private int cartCount;
	
	/**
	  * 商品上架1下架0
	  */
	 private Integer goodsShow;
	 
	 /**
	  * 商品状态，0开启，1违规下架	
	  */
	 private Integer goodsState;
}
