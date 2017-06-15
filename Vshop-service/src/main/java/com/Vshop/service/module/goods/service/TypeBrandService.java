package com.Vshop.service.module.goods.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.base.TypeBrand;


public interface TypeBrandService {
	
	List<TypeBrand> findListByType(@Param("typeId") Integer typeId);

}
