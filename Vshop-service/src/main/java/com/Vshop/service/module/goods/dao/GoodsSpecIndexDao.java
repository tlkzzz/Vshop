package com.Vshop.service.module.goods.dao;

import java.util.List;

import com.Vshop.core.entity.base.GoodsSpecIndex;
import com.Vshop.service.utils.page.Pager;

public interface GoodsSpecIndexDao {

	/**
	 * 保存
	 */
	void save(GoodsSpecIndex goodsSpecIndex);
	
	/**
	 * 查询通过goodsId
	 */
	GoodsSpecIndex findByGoodsId(Integer goodsId);
	
	/**
	 * 根据goodsId删除
	 */
	void deleteByGoodsId(Integer goodsId);
	
	/**
	 * 分页查找总数
	 */
	int findPagerListCount(Pager pager);
	
	/**
	 * 分页查询
	 */
	List<GoodsSpecIndex> findPagerList(Pager pager);
}
