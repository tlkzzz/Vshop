package com.Vshop.service.module.goods.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.GoodsStore;
import com.Vshop.core.entity.vo.GoodsTradeVo;
import com.Vshop.service.module.goods.dao.GoodsDao;
import com.Vshop.service.module.goods.dao.mapper.GoodsMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：Vshop-seller   
 * 类名称：GoodsDaoImpl   
 * 类描述：   
 * 创建人：cgl
 * 创建时间：2015年06月29日10:01:27
 * 修改人：liuhao   
 * 修改时间：2015年06月29日10:01:27
 * 修改备注：   
 * @version    
 *
 */
@Repository
public class GoodsDaoImpl implements GoodsDao{

    @Resource
    private GoodsMapper goodsMapper;

	public Goods findGoodById(Integer goodsId) {
		return goodsMapper.findGoodById(goodsId);
	}

	public List<Goods> findGoodPagerList(Pager pager) {
		return goodsMapper.findGoodPagerList(pager);
	}

	public void saveGoods(Goods goods) {
		goodsMapper.saveGoods(goods);
	}

	public void updateGoods(Goods goods) {
		goodsMapper.updateGoods(goods);
	}

	public void deleteGoods(Integer goodsId) {
		goodsMapper.deleteGoods(goodsId);
	}

	public Goods findOneGoodByCondition(Goods goods) {
		return goodsMapper.findOneGoodByCondition(goods);
	}

	public int countGoods(Goods goods) {
		return goodsMapper.countGoods(goods);
	}
	
	/**
	 * 分页查询获得findTradeGoodlist
	 * @param pager
	 * @return
	 */
	@Override
	public List<GoodsTradeVo> findTradeGoodPagerList(Pager pager) {
		return goodsMapper.findTradeGoodPagerList(pager);
	}
	/**
	 * 根据商品字段获取商品的数量
	 * @param GoodsTradeVo
	 * @return
	 */
	@Override
	public int findTradeGoodcount(GoodsTradeVo goodsTradeVo) {
		return goodsMapper.findTradeGoodcount(goodsTradeVo);
	}
	/**
	 * 根据店铺id字段商品
	 * @param storeId
	 * @return
	 */
	@Override
	public List<Goods> findGoodidList(Integer storeId) {
		return goodsMapper.findGoodidList(storeId);
	}
	
	
	public void saveStoreGoods(GoodsStore goodsStore) {
		goodsMapper.saveStoreGoods(goodsStore);
	}
	
	public GoodsStore findStoreGoodByCondition(GoodsStore goodsStore) {
		return goodsMapper.findStoreGoodByCondition(goodsStore);
	}
	
	public void updateStoreGoods(GoodsStore goodsStore) {
		goodsMapper.updateStoreGoods(goodsStore);
	}
	
	public List<Goods> findStoreGoodPagerList(Pager pager) {
		return goodsMapper.findStoreGoodPagerList(pager);
	}
	public List<Goods> findSaleGoodPagerList(Pager pager) {
		return goodsMapper.findSaleGoodPagerList(pager);
	}

	@Override
	public Goods findGoodsById(Integer goodsId) {
		return goodsMapper.findGoodsById(goodsId);
	}



	@Override
	public GoodsStore findGoodsStoreEndTime(Goods gd) {
		return goodsMapper.findGoodsStoreEndTime(gd);
	}

	/**
	 * 更新点击量
	 * @param storeId
	 */
	@Override
	public void updateGoodsClick(Integer goodsId) {
		goodsMapper.updateGoodsClick(goodsId);
	}

	@Override
	public List<Goods> findGoodsByTypeId(int[] ids) {
		return goodsMapper.findGoodsByTypeId(ids);
	}

	@Override
	public void minusGoodsCollect(Integer goodsId) {
		// TODO Auto-generated method stub
		goodsMapper.minusGoodsCollect(goodsId);
	}

	@Override
	public List<Goods> findRecommendGoodPager(Pager pager) {
		// TODO Auto-generated method stub
		return goodsMapper.findRecommendGoodPager(pager);
	}

	@Override
	public List<Goods> findRcGoodPager(Pager pager) {
		// TODO Auto-generated method stub
		return goodsMapper.findRcGoodPager(pager);
	}

	@Override
	public List<Goods> findAcGoodPager(Pager pager) {
		// TODO Auto-generated method stub
		return goodsMapper.findAcGoodPager(pager);
	}
	
	
}
