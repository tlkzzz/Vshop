package com.Vshop.service.module.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.GoodsActivityRel;
import com.Vshop.core.entity.base.RelGoodsRecommend;
import com.Vshop.service.module.goods.dao.GoodsActivityRelDao;
import com.Vshop.service.module.goods.service.GoodsActivityRelService;
import com.Vshop.service.utils.page.Pager;

@Service
public class GoodsActivityRelServiceImpl implements GoodsActivityRelService {

	@Resource
    private GoodsActivityRelDao goodsActivityRelDao;
	
	@Override
	public Object save(GoodsActivityRel goodsActivityRel) {
		// TODO Auto-generated method stub
		return goodsActivityRelDao.save(goodsActivityRel);
	}

	@Override
	public List<GoodsActivityRel> findPageList(Pager pager) {
		// TODO Auto-generated method stub
		return goodsActivityRelDao.findPageList(pager);
	}

	@Override
	public int update(GoodsActivityRel goodsActivityRel) {
		// TODO Auto-generated method stub
		return goodsActivityRelDao.update(goodsActivityRel);
	}

	@Override
	public int deleteById(Integer relId) {
		// TODO Auto-generated method stub
		return goodsActivityRelDao.deleteById(relId);
	}

	@Override
	public GoodsActivityRel findById(Integer relId) {
		// TODO Auto-generated method stub
		return goodsActivityRelDao.findById(relId);
	}

	@Override
	public List<GoodsActivityRel> findPageListMore(Pager pager) {
		// TODO Auto-generated method stub
		return goodsActivityRelDao.findPageListMore(pager);
	}
	
	
	@Override
	public List<GoodsActivityRel> findStoregoodsList(Integer activity_id,Integer activity_type) {
		return goodsActivityRelDao.findgoodsids(activity_id,activity_type);
	}
	

}
