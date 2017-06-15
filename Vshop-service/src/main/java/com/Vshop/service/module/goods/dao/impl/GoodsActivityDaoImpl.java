package com.Vshop.service.module.goods.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.GoodsActivity;
import com.Vshop.service.module.goods.dao.GoodsActivityDao;
import com.Vshop.service.module.goods.dao.mapper.GoodsActivityMapper;
import com.Vshop.service.utils.page.Pager;

@Repository
public class GoodsActivityDaoImpl implements GoodsActivityDao {

	@Resource
	private GoodsActivityMapper goodsActivityMapper;
	
	@Override
	public Object save(GoodsActivity goodsActivity) {
		// TODO Auto-generated method stub
		return goodsActivityMapper.save(goodsActivity);
	}

	@Override
	public List<GoodsActivity> findPageList(Pager pager) {
		// TODO Auto-generated method stub
		return goodsActivityMapper.findPageList(pager);
	}

	@Override
	public int update(GoodsActivity goodsActivity) {
		// TODO Auto-generated method stub
		return goodsActivityMapper.update(goodsActivity);
	}

	@Override
	public int deleteById(Integer activityId) {
		// TODO Auto-generated method stub
		return goodsActivityMapper.deleteById(activityId);
	}

	@Override
	public GoodsActivity findById(Integer activityId) {
		// TODO Auto-generated method stub
		return goodsActivityMapper.findById(activityId);
	}

}
