package com.Vshop.service.module.trade.service;

import java.util.List;

import com.Vshop.core.entity.base.ReturnGoods;

/**
 * 退货商品
 * @author liukai
 */
public interface ReturnGoodsService { 
	/**
	 * 保存退货商品表
	 * @param returnGoods
	 */
	void saveReturnGoods(ReturnGoods returnGoods);
	
	/**
	 * 根据退货表id删除退货商品表
	 * @param returnId
	 */
	void deleteByReturnOrderId(Integer returnId);
	
	/**
	 * 根据退货表id删除退货商品信息
	 * @param returnId
	 * @return
	 */
	List<ReturnGoods> findByReturnOrderId(Integer returnId);
}
