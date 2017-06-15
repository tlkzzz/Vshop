package com.Vshop.service.module.store.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.StoreExpand;
import com.Vshop.service.module.store.dao.StoreExpandDao;
import com.Vshop.service.module.store.service.StoreExpandService;

@Service
public class StoreExpandServiceImpl implements StoreExpandService {
	@Resource
	private StoreExpandDao storeExpandDao;
	
	@Override
	public void save(StoreExpand storeExpand) {
		storeExpandDao.save(storeExpand);
	}

	@Override
	public StoreExpand findByStoreId(Integer storeId) {
		return storeExpandDao.findByStoreId(storeId);
	}

	@Override
	public void update(StoreExpand storeExpand) {
		storeExpandDao.update(storeExpand);
	}

}
