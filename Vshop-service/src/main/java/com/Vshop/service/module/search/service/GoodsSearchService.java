package com.Vshop.service.module.search.service;

import java.util.List;

import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.service.utils.lucene.LucenePager;

/**
 * 商品搜索
 * @author cgl
 */
public interface GoodsSearchService {

	/**
	 * 批量建立商品索引
	 * @param goodsConditions 自定义条件
	 * @return 返回1 建立成功 返回0 则发生错误
	 */
	Integer saveGoodsIndex(Goods goodsConditions);
	
	/**
	 * 建立指定商品索引
	 * @return 返回1 建立成功 返回0 则发生错误
	 * @param goodsId 商品索引id
	 */
	Integer saveOneGoodsIndex(Integer goodsId);
	
	/**
	 * 删除指定商品索引
	 * @param field 商品的lucene某一个字段名称
	 * @param id 该字段名称对应的id为多少的商品将会被删除
	 */
	void deleteGoodsIndex(String field,Integer id);
	
	/**
	 * 搜索商品
	 * @param lucenePager lucenePager实体类
	 * @return LucenePager lucenePager实体类 该方法将会result属性,注入查询结果
	 */
	LucenePager searchGoods(LucenePager lucenePager);
	
	/**
	 * 获得当前关键词所对应的可选品牌
	 */
	List<Brand> getBrandListByKeyword(String keyword);
	
	/**
	 * 获得当前关键词所对应的可选品牌
	 */
	List<Brand> getBrandListByGcId(Integer gcId);
	
	/**
	 * 获得当前关键词所对应的可选品牌
	 */
	List<Brand> getBrandListByTypeId(Integer typeId);
	
}
