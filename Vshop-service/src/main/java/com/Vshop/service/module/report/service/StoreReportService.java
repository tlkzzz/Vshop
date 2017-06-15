package com.Vshop.service.module.report.service;

import java.util.List;

import com.Vshop.core.entity.base.GoodsClick;
import com.Vshop.core.entity.base.StorePurchaseRate;
import com.Vshop.core.entity.base.StoreSellCount;
import com.Vshop.core.entity.base.StoreTotalCount;

public interface StoreReportService {

	/**
	 * 获得商品的点击量
	 * @param storeId
	 * @return List<GoodsClick>
	 */
	List<GoodsClick> getGoodsClick(GoodsClick goodsClick);
	
	/**
	 * 获得所有商品的点击量
	 * @param storeId
	 * @return List<GoodsClick>
	 */
	List<GoodsClick> getAllGoodsClick(GoodsClick goodsClick);
	
	/**
	 * 本店铺销售总量统计
	 * @param storeSellCount
	 * @return
	 */
	List<StoreSellCount> getStoreSellCount(StoreSellCount storeSellCount);
	
	/**
	 * 本店铺销售总量统计
	 * @param StoreTotalCount
	 * @return
	 */
	List<StoreTotalCount> getStoreTotalCount(StoreTotalCount storeTotalCount);
	
	/**
	 * 本店商品购买率
	 * @param storeId
	 * @return
	 */
	List<StorePurchaseRate> getStorePurchaseRate(Integer storeId);
}
