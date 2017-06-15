package com.Vshop.service.module.goods.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.base.ShopStatGoods;
import com.Vshop.service.module.goods.dao.ShopStatGoodsDao;
import com.Vshop.service.module.goods.dao.mapper.ShopStatGoodsMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * 
 * @项目名称：Vshop-seller
 * @类名称：ShopStatGoodsDaoImpl
 * @类描述：
 * @修改备注：
 * @version
 * 
 */
@Repository
public class ShopStatGoodsDaoImpl implements ShopStatGoodsDao {
	@Resource
	private ShopStatGoodsMapper shopStatGoodsMapper;

	@Override
	public void save(ShopStatGoods shopStatGoods) {
		shopStatGoodsMapper.save(shopStatGoods);
	}
	@Override
	public void delete(int sId) {
		shopStatGoodsMapper.delete(sId);
	}

	@Override
	public int findCount(Pager pager) {
		return shopStatGoodsMapper.findCount(pager);
	}

	@Override
	public List<ShopStatGoods> findPageList(Pager pager) {
		return shopStatGoodsMapper.findPageList(pager);
	}

	@Override
	public Brand findbyIds(ShopStatGoods shopStatGoods) {
		return shopStatGoodsMapper.findbyIds(shopStatGoods);
	}
	@Override
	public List<ShopStatGoods> findList() {
		return shopStatGoodsMapper.findList();
	}

	
}
