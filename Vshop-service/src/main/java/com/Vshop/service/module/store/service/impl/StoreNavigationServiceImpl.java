package com.Vshop.service.module.store.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.StoreNavigation;
import com.Vshop.service.module.store.dao.StoreNavigationDao;
import com.Vshop.service.module.store.service.StoreNavigationService;

@Service
public class StoreNavigationServiceImpl implements StoreNavigationService {
	
   @Resource
   private StoreNavigationDao storeNavigationDao;
	
   public List<StoreNavigation> findAll(Integer id){
	   return storeNavigationDao.findAll(id);
   }
   
}