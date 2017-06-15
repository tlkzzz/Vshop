package com.Vshop.service.module.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.GoodsStore;
import com.Vshop.core.entity.base.RelGoodsRecommend;
import com.Vshop.service.module.goods.dao.RelGoodsRecommendDao;
import com.Vshop.service.module.goods.service.RelGoodsRecommendService;

@Slf4j
@Service
public class RelGoodsRecommendServiceImpl implements RelGoodsRecommendService{
	@Resource
    private RelGoodsRecommendDao relGoodsRecommendDao;
	
	@Override
	public void save(RelGoodsRecommend relGoodsRecommend) {
		relGoodsRecommendDao.save(relGoodsRecommend);
	}
	@Override
	public void delete(int id) {
		relGoodsRecommendDao.delete(id);
	}
	@Override
	public List<RelGoodsRecommend> findgoodsList(Integer reCommendId) {
		return relGoodsRecommendDao.findgoodsList(reCommendId);
	}
	
	@Override
	public List<RelGoodsRecommend> findStoregoodsList(Integer reCommendId,Integer storeid) {
		return relGoodsRecommendDao.findStoregoodsList(reCommendId,storeid);
	}
	
	
	@Override
	public List<RelGoodsRecommend> findgoodsids(Integer reCommendId) {
		return relGoodsRecommendDao.findgoodsids(reCommendId);
	}
	
	
	@Override
	public void saveStoreIdGoodsId(RelGoodsRecommend rel) {
		 relGoodsRecommendDao.saveStoreIdGoodsId(rel);
	}
	
	@Override
	public void deleteByGoodsIdAndStoreId(RelGoodsRecommend rel) {
		relGoodsRecommendDao.deleteByGoodsIdAndStoreId(rel);
	}
	@Override
	public RelGoodsRecommend findByStoreAndGoodsId(RelGoodsRecommend rel) {
		return relGoodsRecommendDao.findByStoreGoodsId(rel);
	}
	@Override
	public List<RelGoodsRecommend> findByStoreAndGoodsIds(RelGoodsRecommend rel) {
		return relGoodsRecommendDao.findByStoreGoodsIds(rel);
	}

}
