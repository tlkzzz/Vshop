package com.Vshop.service.module.goods.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.TypeSpec;
import com.Vshop.service.module.goods.dao.TypeSpecDao;
import com.Vshop.service.module.goods.dao.mapper.TypeSpecMapper;

/**
 * Created by ss on 2014/10/15.
 */
@Repository
public class TypeSpecDaoImpl implements TypeSpecDao{
	@Resource
	private TypeSpecMapper typeSpecMapper;
	
	@Override
	public void batchSave(List<TypeSpec> list) {
		typeSpecMapper.batchSave(list);
	}

	@Override
	public void delete(Integer typeId) {
		typeSpecMapper.delete(typeId);
	}

	@Override
	public List<TypeSpec> findListByType(Integer typeId) {
		return typeSpecMapper.findListByType(typeId);
	}

}
