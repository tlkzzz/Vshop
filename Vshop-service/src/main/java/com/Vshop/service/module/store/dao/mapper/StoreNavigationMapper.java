package com.Vshop.service.module.store.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.base.StoreNavigation;
import com.Vshop.core.orm.mybatis.SqlMapper;

@SqlMapper
public interface StoreNavigationMapper {

  public List<StoreNavigation> findAll(@Param("id")Integer id);
   
}