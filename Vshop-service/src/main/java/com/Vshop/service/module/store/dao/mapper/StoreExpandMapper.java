package com.Vshop.service.module.store.dao.mapper;

import com.Vshop.core.entity.base.StoreExpand;
import com.Vshop.core.orm.mybatis.SqlMapper;

@SqlMapper
public interface StoreExpandMapper {
	
	void save(StoreExpand storeExpand);
	
	StoreExpand findByStoreId(Integer storeId);
	
	void update(StoreExpand storeExpand);
}
