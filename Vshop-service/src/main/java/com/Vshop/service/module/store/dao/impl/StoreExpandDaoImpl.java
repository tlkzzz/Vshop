package com.Vshop.service.module.store.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.StoreExpand;
import com.Vshop.service.module.store.dao.StoreExpandDao;
import com.Vshop.service.module.store.dao.mapper.StoreExpandMapper;

@Repository
public class StoreExpandDaoImpl implements StoreExpandDao {
	@Resource
	private StoreExpandMapper storeExpandMapper;
	
	@Override
	public void save(StoreExpand storeExpand) {
		storeExpandMapper.save(storeExpand);
	}

	@Override
	public StoreExpand findByStoreId(Integer storeId) {
		return storeExpandMapper.findByStoreId(storeId);
	}

	@Override
	public void update(StoreExpand storeExpand) {
		storeExpandMapper.update(storeExpand);
	}

}
