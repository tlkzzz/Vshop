package com.Vshop.service.module.report.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Vshop.core.entity.base.GoodsClick;
import com.Vshop.core.entity.base.StorePurchaseRate;
import com.Vshop.core.entity.base.StoreSellCount;
import com.Vshop.core.entity.base.StoreTotalCount;
import com.Vshop.service.module.report.dao.StoreReportDao;
import com.Vshop.service.module.report.dao.mapper.StoreReportMapper;

@Component
public class StoreReportDaoImpl implements StoreReportDao{

	@Autowired
	private StoreReportMapper storeReportMapper;

	/**
	 * 获得商品的点击量
	 * @param storeId
	 * @return List<GoodsClick>
	 */
	@Override
	public List<GoodsClick> getGoodsClick(GoodsClick goodsClick) {
		// TODO Auto-generated method stub
		return storeReportMapper.getGoodsClick(goodsClick);
	}

	/**
	 * 本店铺销售总量统计
	 * @param storeSellCount
	 * @return
	 */
	@Override
	public List<StoreSellCount> getStoreSellCount(StoreSellCount storeSellCount) {
		// TODO Auto-generated method stub
		return storeReportMapper.getStoreSellCount(storeSellCount);
	}

	/**
	 * 本店铺销售总量统计
	 * @param StoreTotalCount
	 * @return
	 */
	@Override
	public List<StoreTotalCount> getStoreTotalCount(
			StoreTotalCount storeTotalCount) {
		// TODO Auto-generated method stub
		return storeReportMapper.getStoreTotalCount(storeTotalCount);
	}

	/**
	 * 本店商品购买率
	 * @param storeId
	 * @return
	 */
	@Override
	public List<StorePurchaseRate> getStorePurchaseRate(Integer storeId) {
		// TODO Auto-generated method stub
		return storeReportMapper.getStorePurchaseRate(storeId);
	}
	
	/**
	 * 获得所有商品的点击量
	 * @param storeId
	 * @return List<GoodsClick>
	 */
	@Override
	public List<GoodsClick> getAllGoodsClick(GoodsClick goodsClick) {
		// TODO Auto-generated method stub
		return storeReportMapper.getAllGoodsClick(goodsClick);
	}
}
