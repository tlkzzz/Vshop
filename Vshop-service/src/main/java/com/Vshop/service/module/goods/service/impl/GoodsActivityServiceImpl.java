package com.Vshop.service.module.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.GoodsActivity;
import com.Vshop.service.module.goods.dao.GoodsActivityDao;
import com.Vshop.service.module.goods.service.GoodsActivityService;
import com.Vshop.service.utils.page.Pager;

@Service
public class GoodsActivityServiceImpl implements GoodsActivityService {

	@Resource
    private GoodsActivityDao goodsActivityDao;
	
	@Override
	public Object save(GoodsActivity goodsActivity) {
		// TODO Auto-generated method stub
		return goodsActivityDao.save(goodsActivity);
	}

	@Override
	public List<GoodsActivity> findPageList(Pager pager) {
		// TODO Auto-generated method stub
		return goodsActivityDao.findPageList(pager);
	}

	@Override
	public int update(GoodsActivity goodsActivity) {
		// TODO Auto-generated method stub
		return goodsActivityDao.update(goodsActivity);
	}

	@Override
	public int deleteById(Integer activityId) {
		// TODO Auto-generated method stub
		return goodsActivityDao.deleteById(activityId);
	}

	@Override
	public GoodsActivity findById(Integer activityId) {
		// TODO Auto-generated method stub
		return goodsActivityDao.findById(activityId);
	}

}
