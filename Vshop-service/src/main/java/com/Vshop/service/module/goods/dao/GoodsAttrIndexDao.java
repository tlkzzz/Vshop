package com.Vshop.service.module.goods.dao;

import com.Vshop.core.entity.base.GoodsAttrIndex;

public interface GoodsAttrIndexDao {

	/**
	 * 保存
	 */
	void save(GoodsAttrIndex goodsAttrIndex);
	
	/**
	 * 查询通过goodsId
	 */
	GoodsAttrIndex findByGoodsId(Integer goodsId);
	
	/**
	 * 根据goodsId删除
	 */
	void deleteByGoodsId(Integer goodsId);
}
