package com.Vshop.service.module.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.TypeSpec;
import com.Vshop.service.module.goods.dao.TypeSpecDao;
import com.Vshop.service.module.goods.service.TypeSpecService;

@Service
public class TypeSpecServiceImpl implements TypeSpecService{

    @Resource
    private TypeSpecDao typeSpecDao;

	@Override
	public void batchSave(List<TypeSpec> list) {
		typeSpecDao.batchSave(list);
	}

	@Override
	public void delete(Integer typeId) {
		typeSpecDao.delete(typeId);
	}

	@Override
	public List<TypeSpec> findListByType(Integer typeId) {
		return typeSpecDao.findListByType(typeId);
	}

    
}
