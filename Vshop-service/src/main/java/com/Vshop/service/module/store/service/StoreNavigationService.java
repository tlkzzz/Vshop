package com.Vshop.service.module.store.service;

import java.util.List;

import com.Vshop.core.entity.base.StoreNavigation;

public interface StoreNavigationService {
	
   public List<StoreNavigation> findAll(Integer id);
   
}