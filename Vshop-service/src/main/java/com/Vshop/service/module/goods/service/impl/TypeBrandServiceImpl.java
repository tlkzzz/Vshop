package com.Vshop.service.module.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.TypeBrand;
import com.Vshop.service.module.goods.dao.TypeBrandDao;
import com.Vshop.service.module.goods.service.TypeBrandService;

@Service
public class TypeBrandServiceImpl implements TypeBrandService{

    @Resource
    private TypeBrandDao typeBrandDao;

	@Override
	public List<TypeBrand> findListByType(Integer typeId) {
		
		List<TypeBrand> brandlist = typeBrandDao.findListByType(typeId);
		if(brandlist!=null && brandlist.size()>0){
			return brandlist;
		}else{
			return null;
		}
	}

    
}
