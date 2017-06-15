package com.Vshop.service.module.goods.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Vshop.core.entity.base.GoodsAttrIndex;
import com.Vshop.service.module.goods.dao.GoodsAttrIndexDao;
import com.Vshop.service.module.goods.dao.mapper.GoodsAttrIndexMapper;

@Component
public class GoodsAttrIndexDaoImpl implements GoodsAttrIndexDao{

	@Autowired
	GoodsAttrIndexMapper goodsAttrIndexMapper;
	
	@Override
	public void save(GoodsAttrIndex goodsAttrIndex) {
		// TODO Auto-generated method stub
		goodsAttrIndexMapper.save(goodsAttrIndex);
	}

	@Override
	public GoodsAttrIndex findByGoodsId(Integer goodsId) {
		// TODO Auto-generated method stub
		return goodsAttrIndexMapper.findByGoodsId(goodsId);
	}

	@Override
	public void deleteByGoodsId(Integer goodsId) {
		// TODO Auto-generated method stub
		goodsAttrIndexMapper.deleteByGoodsId(goodsId);
	}

}
