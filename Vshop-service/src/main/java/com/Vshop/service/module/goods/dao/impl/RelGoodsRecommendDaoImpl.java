package com.Vshop.service.module.goods.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.GoodsStore;
import com.Vshop.core.entity.base.RelGoodsRecommend;
import com.Vshop.service.module.goods.dao.RelGoodsRecommendDao;
import com.Vshop.service.module.goods.dao.mapper.RelGoodsRecommendMapper;
/**
 * 
 *    
 * 项目名称：Vshop-seller  
 * 类名称：RelGoodsRecommendDaoImpl   
 * 类描述：   
 * 创建人：gyh 
 * 创建时间：2015年8月25日 下午10:59:04   
 * 修改备注：   
 * @version    
 *
 */
@Repository
public class RelGoodsRecommendDaoImpl implements RelGoodsRecommendDao{
	@Resource
	private  RelGoodsRecommendMapper  relGoodsRecommendMapper;
		
	
	@Override
	public void save(RelGoodsRecommend relGoodsRecommend) {
		relGoodsRecommendMapper.save(relGoodsRecommend);
	}
	@Override
	public void delete(int id) {
		relGoodsRecommendMapper.delete(id);
	}

	@Override
	public List<RelGoodsRecommend> findgoodsList(Integer reCommendId) {
		return relGoodsRecommendMapper.findgoodsList(reCommendId);
	}
	
	
	@Override
	public List<RelGoodsRecommend> findStoregoodsList(Integer reCommendId,Integer storeid) {
		return relGoodsRecommendMapper.findStoregoodsList(reCommendId,storeid);
	}
	@Override
	public List<RelGoodsRecommend> findgoodsids(Integer reCommendId) {
		return relGoodsRecommendMapper.findgoodsids(reCommendId);
	}
	
	@Override
	public void saveStoreIdGoodsId(RelGoodsRecommend rel) {
		relGoodsRecommendMapper.saveStoreIdGoodsId(rel);
	}
	
	@Override
	public void deleteByGoodsIdAndStoreId(RelGoodsRecommend rel) {
		relGoodsRecommendMapper.deleteByGoodsIdAndStoreId(rel);
	}
	@Override
	public RelGoodsRecommend findByStoreGoodsId(RelGoodsRecommend rel) {
		return relGoodsRecommendMapper.findByStoreGoodsId(rel);
	}
	@Override
	public List<RelGoodsRecommend> findByStoreGoodsIds(RelGoodsRecommend rel) {
		return relGoodsRecommendMapper.findByStoreGoodsIds(rel);
	}

}
