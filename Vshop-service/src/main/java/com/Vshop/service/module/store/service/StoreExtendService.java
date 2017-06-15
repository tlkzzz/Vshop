package com.Vshop.service.module.store.service;

import java.util.List;

import com.Vshop.core.entity.base.Express;
import com.Vshop.core.entity.base.StoreExtend;

public interface StoreExtendService {
	/**
	 * 保存店铺物流表
	 * @param storeExtend
	 */
	void saveStoreExtend(StoreExtend storeExtend);
	
	/**
	 * 修改店铺物流表
	 * @param storeExtend
	 */
	void updateStoreExtend(StoreExtend storeExtend);
	
	/**
	 * 删除店铺物流表
	 * @param storeId
	 */
	void deleteStoreExtend(Integer storeId);
	
	/**
	 * 通过id查询店铺物流表
	 * @param storeId
	 * @return
	 */
	StoreExtend findByStoreId(Integer storeId);
	
	/**
	 * 查询店铺选择的物流
	 * @return
	 */
	List<Express> findExpressByExpressIds(Integer storeId);
}
