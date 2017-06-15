package com.Vshop.service.module.store.service;

import com.Vshop.core.entity.base.StoreExpand;

public interface StoreExpandService {
	
	void save(StoreExpand storeExpand);
	
	StoreExpand findByStoreId(Integer storeId);
	
	void update(StoreExpand storeExpand);
}
