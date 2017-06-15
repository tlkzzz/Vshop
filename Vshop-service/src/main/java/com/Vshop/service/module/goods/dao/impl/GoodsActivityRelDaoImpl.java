package com.Vshop.service.module.goods.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.GoodsActivityRel;
import com.Vshop.service.module.goods.dao.GoodsActivityRelDao;
import com.Vshop.service.module.goods.dao.mapper.GoodsActivityRelMapper;
import com.Vshop.service.utils.page.Pager;

@Repository
public class GoodsActivityRelDaoImpl implements GoodsActivityRelDao {

	@Resource
	private GoodsActivityRelMapper goodsActivityRelMapper;
	
	@Override
	public Object save(GoodsActivityRel goodsActivityRel) {
		// TODO Auto-generated method stub
		return goodsActivityRelMapper.save(goodsActivityRel);
	}

	@Override
	public List<GoodsActivityRel> findPageList(Pager pager) {
		// TODO Auto-generated method stub
		return goodsActivityRelMapper.findPageList(pager);
	}

	@Override
	public int update(GoodsActivityRel goodsActivityRel) {
		// TODO Auto-generated method stub
		return goodsActivityRelMapper.update(goodsActivityRel);
	}

	@Override
	public int deleteById(Integer relId) {
		// TODO Auto-generated method stub
		return goodsActivityRelMapper.deleteById(relId);
	}

	@Override
	public GoodsActivityRel findById(Integer relId) {
		// TODO Auto-generated method stub
		return goodsActivityRelMapper.findById(relId);
	}

	@Override
	public List<GoodsActivityRel> findPageListMore(Pager pager) {
		// TODO Auto-generated method stub
		return goodsActivityRelMapper.findPageListMore(pager);
	}
	
	@Override
	public List<GoodsActivityRel> findgoodsids(java.lang.Integer activeid,java.lang.Integer activetype) {
		// TODO Auto-generated method stub
		return goodsActivityRelMapper.findgoodsList(activeid,activetype);
	}
	
	

}
