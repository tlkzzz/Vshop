package com.Vshop.service.module.goods.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.GoodsAttributeValue;
import com.Vshop.service.module.goods.dao.AttributeValueDao;
import com.Vshop.service.module.goods.dao.mapper.GoodsAttributeValueMapper;

@Repository
public class AttributeValueDaoImpl implements AttributeValueDao {

	@Resource
    private GoodsAttributeValueMapper attrMapper;
	
	@Override
	public void save(GoodsAttributeValue attrValue) {
		attrMapper.save(attrValue);
	}

	@Override
	public void deleteBatch(int id) {
		attrMapper.deleteBatch(id);
	}

	@Override
	public void deleteByType(int id) {
		attrMapper.deleteByType(id);
	}

	@Override
	public void deleteBatchByType(String ids) {
		attrMapper.deleteBatchByType(ids);
	}

	@Override
	public void deleteById(int attrId) {
		attrMapper.deleteById(attrId);
	}

	@Override
	public void update(GoodsAttributeValue attributeValue) {
		attrMapper.update(attributeValue);
	}
	
	/**
     * 根据属性id删除属性值
     * @param attrId
     */
    public void deleteByAttrId(int attrId){
    	attrMapper.deleteByAttrId(attrId);
    }

}
