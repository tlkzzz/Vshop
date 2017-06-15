package com.Vshop.service.module.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.GoodsSpecIndex;
import com.Vshop.service.module.goods.dao.GoodsSpecIndexDao;
import com.Vshop.service.module.goods.service.GoodsSpecIndexService;
import com.Vshop.service.utils.page.Pager;

@Service
public class GoodsSpecIndexServiceImpl implements GoodsSpecIndexService{

	@Autowired
	GoodsSpecIndexDao goodsSpecIndexDao;
	
	@Override
	public void save(GoodsSpecIndex goodsSpecIndex) {
		// TODO Auto-generated method stub
		goodsSpecIndexDao.save(goodsSpecIndex);
	}

	@Override
	public GoodsSpecIndex findByGoodsId(Integer goodsId) {
		// TODO Auto-generated method stub
		return goodsSpecIndexDao.findByGoodsId(goodsId);
	}

	@Override
	public void deleteByGoodsId(Integer goodsId) {
		// TODO Auto-generated method stub
		goodsSpecIndexDao.deleteByGoodsId(goodsId);
	}

	@Override
	public int findPagerListCount(Pager pager) {
		// TODO Auto-generated method stub
		return goodsSpecIndexDao.findPagerListCount(pager);
	}

	@Override
	public List<GoodsSpecIndex> findPagerList(Pager pager) {
		// TODO Auto-generated method stub
		return goodsSpecIndexDao.findPagerList(pager);
	}

}
