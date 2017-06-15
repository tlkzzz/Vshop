package com.Vshop.service.module.goods.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.GoodsAttribute;
import com.Vshop.service.module.goods.dao.GoodsAttributeDao;
import com.Vshop.service.module.goods.dao.mapper.GoodsAttributeMapper;

/**
 * Created by ss on 2014/10/15.
 */
@Repository
public class GoodsAttributeDaoImpl implements GoodsAttributeDao{

    @Autowired
    private GoodsAttributeMapper attrMapper;
    public List<GoodsAttribute> findListByType(Integer typeId) {
        return attrMapper.findListByType(typeId);
    }
    public List<GoodsAttribute> findByType(@Param("typeId") int typeId){
    	return attrMapper.findByType(typeId);
    }
    
    @Override
    public void save(GoodsAttribute goodsAttribute) {
        attrMapper.save(goodsAttribute);
    }

    @Override
    public void update(GoodsAttribute goodsAttribute) {
        attrMapper.update(goodsAttribute);
    }

    @Override
    public void delete(int id) {
        attrMapper.delete(id);
    }

    @Override
    public GoodsAttribute findById(int id) {
        return attrMapper.findById(id);
    }

    @Override
    public List<GoodsAttribute> findList(GoodsAttribute goodsAttribute) {
        return attrMapper.findList(goodsAttribute);
    }

    @Override
    public void batchSave(List<GoodsAttribute> list) {
        attrMapper.batchSave(list);
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

}
