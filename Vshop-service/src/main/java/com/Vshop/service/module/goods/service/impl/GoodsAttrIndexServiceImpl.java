package com.Vshop.service.module.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.GoodsAttrIndex;
import com.Vshop.service.module.goods.dao.GoodsAttrIndexDao;
import com.Vshop.service.module.goods.service.GoodsAttrIndexService;

@Service
public class GoodsAttrIndexServiceImpl implements GoodsAttrIndexService{

	@Autowired
	GoodsAttrIndexDao goodsAttrIndexDao;
	
	@Override
	public void save(GoodsAttrIndex goodsAttrIndex) {
		// TODO Auto-generated method stub
		goodsAttrIndexDao.save(goodsAttrIndex);
	}

	@Override
	public GoodsAttrIndex findByGoodsId(Integer goodsId) {
		// TODO Auto-generated method stub
		return goodsAttrIndexDao.findByGoodsId(goodsId);
	}

	@Override
	public void deleteByGoodsId(Integer goodsId) {
		// TODO Auto-generated method stub
		goodsAttrIndexDao.deleteByGoodsId(goodsId);
	}

}
