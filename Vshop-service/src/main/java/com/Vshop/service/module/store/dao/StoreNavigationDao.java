package com.Vshop.service.module.store.dao;

import java.util.List;

import com.Vshop.core.entity.base.StoreNavigation;

public interface StoreNavigationDao {

   public List<StoreNavigation> findAll(Integer id);
   
}