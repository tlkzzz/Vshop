package com.Vshop.core.entity.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.ToString;

import com.google.common.collect.Lists;
import com.Vshop.core.entity.base.Goods;

/**
 * 收藏商品表
 *    
 * 项目名称：Vshop-front   
 * 类名称：FavGoodsVo   
 * 类描述：   
 * 修改备注：   
 * @version    
 *
 */
@Data
@ToString
public class FavGoodsVo {
	/**
	 * 主键ID
	 */
	private Integer goodsId;
	/**
	 * 商品公共表id
	 */
	private Integer goodsCommonid;
	/**
	 * 商品主图
	 */
	private String goodsImage;
	/**
	 * 商品名称（+规格名称）
	 */
	private String goodsName;
	/**
	 * 店铺名称
	 */
	private String storeName;
	/**
	 * 商品价格
	 */
	private Double goodsPrice;
	/**
	 * 商品库存
	 */
	private Integer goodsStorage;
	/**
	 * 商品状态 0下架，1正常，10违规（禁售）
	 */
	private int goodsState;
	/**
	 * 商品审核 1通过，0未通过，10审核中
	 */
	private int goodsVerify;

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

	/**
	 * 品牌id
	 */
	private Integer brandId;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 商品分类id
	 */
	private Integer gcId;
	/**
	 * 商品分类类型名称
	 */
	private String typeName;

	
	
	private String goodsJingle;
	private Integer storeId;
	private Double goodsMarketprice;
	private String goodsSerial;
	private Integer goodsClick;
	private Integer goodsSalenum;
	private Integer goodsCollect;
	private String goodsSpec;
	private Date goodsAddtime;
	private Date goodsEdittime;
	private Integer areaid1;
	private Integer areaid2;
	private Integer colorId;
	private Integer transportId;
	private BigDecimal goodsFreight;
	private Short goodsVat;
	private Short goodsCommend;
	private String goodsStcids;
	private Short evaluationGoodStar;
	private Integer evaluationCount;
	
	
	private String spValueName;
	
	/**
	 * 自定义商品ID，逗号分隔使用的
	 */
	private String goodsIds;
	
	/**
	 * cart实体的属性
	 */
	private Short goodsNum;
	private Integer cartId;
	private Integer buyerId;
	private Integer blId;
	
	private String cartIds;
	
	private String favTime;
	private Integer favId;
	private String favType;
	private Integer memberId;
	private String storeImage;
	
	private List<Goods> list = Lists.newLinkedList();
}
