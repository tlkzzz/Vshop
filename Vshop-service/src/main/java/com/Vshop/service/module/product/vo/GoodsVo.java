package com.Vshop.service.module.product.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.ToString;

import com.google.common.collect.Lists;

/**
 * 
 * 
 * @项目名称：Vshop-entity
 * @类名称：Goods
 * @类描述： goods 商品表
 * @创建人：shining
 * @创建时间：2014年11月10日 上午12:39:22
 * @修改人：shining
 * @修改时间：2014年11月10日 上午12:39:22
 * @修改备注：
 * @version
 * 
 */
@Data
@ToString
public class GoodsVo {
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
	private Timestamp createdTime;
	/**
	 * 更新时间
	 */
	private Timestamp updatedTime;

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

	/**
	 * 商品子类的集合(同一个goods_commonid的商品的相同部分提取出来算作一个商品大类)
	 */
	private List<GoodsVo> childrens = Lists.newArrayList();

	public void addChildrens(GoodsVo goods) {
		childrens.add(goods);
	}
	
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
	
	private List<String> gcIds;

	private String ncDistinct;
	private String orderby;
	
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
}
