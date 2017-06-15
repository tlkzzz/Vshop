package com.Vshop.service.module.goods.dao.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.GoodsClass;
import com.Vshop.core.entity.GoodsStoreCls;
import com.Vshop.service.module.goods.dao.GoodsClassDao;
import com.Vshop.service.module.goods.dao.mapper.GoodsClassMapper;

@Repository
public class GoodsClassDaoImpl implements GoodsClassDao{

    @Resource
    private GoodsClassMapper goodsClassMapper;

	@Override
	public void save(GoodsClass goodsClass) {
		goodsClassMapper.save(goodsClass);
	}

	@Override
	public void update(GoodsClass goodsClass) {
		goodsClassMapper.update(goodsClass);
	}

	@Override
	public void delete(Integer id) {
		goodsClassMapper.delete(id);
	}

	@Override
	public int findCount(GoodsClass goodsClass) {
		return goodsClassMapper.findCount(goodsClass);
	}

	@Override
	public List<GoodsClass> findAll() {
		return goodsClassMapper.findAll();
	}

	@Override
	public GoodsClass findById(Integer gcId) {
		return goodsClassMapper.findById(gcId);
	}

	@Override
	public List<GoodsClass> findList(int parentid) {
		return goodsClassMapper.findList(parentid);
	}

	@Override
	public List<GoodsClass> findListbyishow(GoodsClass goodsClass) {
		return goodsClassMapper.findListbyishow(goodsClass);
	}

	@Override
	public List<GoodsClass> findAllbyisshow(GoodsClass goodsClass) {
		return goodsClassMapper.findAllbyisshow(goodsClass);
	}
	
	/**
     * 通过父id查询子分类
     * @param gcParentId
     * @return
     */
    public List<GoodsClass> findChild(Integer gcParentId){
    	return goodsClassMapper.findChild(gcParentId);
    }

	@Override
	public List<GoodsClass> findListByStoreId(GoodsStoreCls gsc) {
		// TODO Auto-generated method stub
		return goodsClassMapper.findListByStoreId(gsc);
	}

	@Override
	public List<GoodsClass> findLeafListByStoreId(GoodsStoreCls gsc) {
		// TODO Auto-generated method stub
		return goodsClassMapper.findLeafListByStoreId(gsc);
	}

	@Override
	public List<GoodsClass> findByList(List<Integer> gcIdList) {
		// TODO Auto-generated method stub
		return goodsClassMapper.findByList(gcIdList);
	}
	
	
}
