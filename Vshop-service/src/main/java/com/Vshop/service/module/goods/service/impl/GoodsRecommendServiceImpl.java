package com.Vshop.service.module.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.GoodsRecommend;
import com.Vshop.service.module.goods.dao.GoodsRecommendDao;
import com.Vshop.service.module.goods.service.GoodsRecommendService;
import com.Vshop.service.utils.page.Pager;

@Service
public class GoodsRecommendServiceImpl implements GoodsRecommendService{
	@Resource
    private GoodsRecommendDao goodsRecommendDao;
	
	@Override
	public void save(GoodsRecommend goodsRecommend) {
		goodsRecommend.setCreateTime(System.currentTimeMillis());//创建时间
		goodsRecommendDao.save(goodsRecommend);
	}

	@Override
	public void delete(int reCommendid) {
		goodsRecommendDao.delete(reCommendid);
	}

	@Override
	public void update(GoodsRecommend goodsRecommend) {
		goodsRecommendDao.update(goodsRecommend);
	}

	@Override
	public GoodsRecommend findById(Integer reCommendid) {
		return goodsRecommendDao.findById(reCommendid);
	}

	@Override
	public List<GoodsRecommend> findList(GoodsRecommend goodsRecommend) {
		return goodsRecommendDao.findList(goodsRecommend);
	}

	@Override
	public int findCount(GoodsRecommend goodsRecommend) {
		return goodsRecommendDao.findCount(goodsRecommend);
	}

	@Override
	public List<GoodsRecommend> findPageList(Pager pager) {
		return goodsRecommendDao.findPageList(pager);
	}

	@Override
	public GoodsRecommend findBycolum(String recommendName) {
		return goodsRecommendDao.findBycolum(recommendName);
	}

}
