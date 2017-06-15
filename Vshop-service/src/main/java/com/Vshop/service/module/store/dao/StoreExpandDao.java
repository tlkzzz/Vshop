package com.Vshop.service.module.store.dao;

import com.Vshop.core.entity.base.StoreExpand;

public interface StoreExpandDao {
	
	void save(StoreExpand storeExpand);
	
	StoreExpand findByStoreId(Integer storeId);
	
	void update(StoreExpand storeExpand);
}
