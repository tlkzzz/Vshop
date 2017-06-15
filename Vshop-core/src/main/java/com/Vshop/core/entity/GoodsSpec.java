package com.Vshop.core.entity;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
/**
 * 对应的表为shop_goods_spec
 * @author cgl
 *
 */
public class GoodsSpec {

	/**
	 * 商品规格索引id
	 */
	private Integer goodsSpecId;
	
	/**
	 * 商品id
	 */
	private Integer goodsId;
	
	/**
	 * 规格名称
	 */
	private String specName;
	
	/**
	 * 规格商品价格
	 */
	private BigDecimal specGoodsPrice;
	
	/**
	 * 规格商品库存
	 */
	private Integer specGoodsStorage;
	
	/**
	 * 售出数量
	 */
	private Integer specSalenum;
	
	/**
	 * 规格商品颜色
	 */
	private String specGoodsColor;
	
	/**
	 * 规格商品编号
	 */
	private String specGoodsSerial;
	
	/**
	 * 商品规格序列化
	 */
	private String specGoodsSpec;
	
	/**
	 * 这个商品颜色对应的图片
	 */
	private String colImg;
	
	/**
	 * 当前商品的 规格名称 对应的规格值
	 */
	private Map<String, String> sepcMap;
	
	/**
	 * 序列化Str经过实体化,变为map
	 */
	private Map<String, String> specGoodsSpecMap;
	
	/**
	 * 所有规格值id以逗号分隔
	 */
	private String specValueIdStr;
}
