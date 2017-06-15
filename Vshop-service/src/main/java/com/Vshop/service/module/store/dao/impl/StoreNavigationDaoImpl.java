package com.Vshop.service.module.store.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.StoreNavigation;
import com.Vshop.service.module.store.dao.StoreNavigationDao;
import com.Vshop.service.module.store.dao.mapper.StoreNavigationMapper;

@Repository
public class StoreNavigationDaoImpl implements StoreNavigationDao {
	
   @Resource
   private StoreNavigationMapper storeNavigationMapper;

   @Override
   public List<StoreNavigation> findAll(Integer id){
	  return storeNavigationMapper.findAll(id);
   }
   
}