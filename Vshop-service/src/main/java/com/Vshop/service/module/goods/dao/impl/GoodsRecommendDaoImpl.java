package com.Vshop.service.module.goods.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.GoodsRecommend;
import com.Vshop.service.module.goods.dao.GoodsRecommendDao;
import com.Vshop.service.module.goods.dao.mapper.GoodsRecommendMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：Vshop-front
 * 类名称：GoodsRecommendDaoImpl   
 * 类描述：   
 * 创建人：gyh
 * 创建时间：2015年08月25日10:01:27
 * 修改备注：   
 * @version    
 *
 */
@Repository
public class GoodsRecommendDaoImpl implements GoodsRecommendDao{
    @Resource
    private GoodsRecommendMapper  goodsRecommendMapper;
	
	@Override
	public void save(GoodsRecommend goodsRecommend) {
		goodsRecommendMapper.save(goodsRecommend);
	}

	@Override
	public void delete(int reCommendid) {
		goodsRecommendMapper.delete(reCommendid);
	}

	@Override
	public void update(GoodsRecommend goodsRecommend) {
		goodsRecommendMapper.update(goodsRecommend);
	}

	@Override
	public GoodsRecommend findById(Integer reCommendid) {
		return goodsRecommendMapper.findById(reCommendid);
	}

	@Override
	public List<GoodsRecommend> findList(GoodsRecommend goodsRecommend) {
		return goodsRecommendMapper.findList(goodsRecommend);
	}

	@Override
	public int findCount(GoodsRecommend goodsRecommend) {
		return goodsRecommendMapper.findCount(goodsRecommend);
	}

	@Override
	public List<GoodsRecommend> findPageList(Pager pager) {
		return goodsRecommendMapper.findPageList(pager);
	}

	@Override
	public GoodsRecommend findBycolum(String recommendName) {
		return goodsRecommendMapper.findBycolum(recommendName);
	}

}
