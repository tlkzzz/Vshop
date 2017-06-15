package com.Vshop.service.module.goods.service;

import java.util.List;

import com.Vshop.core.entity.base.GoodsActivityRel;
import com.Vshop.core.entity.base.RelGoodsRecommend;
import com.Vshop.service.utils.page.Pager;

public interface GoodsActivityRelService {

	Object save(GoodsActivityRel goodsActivityRel);

	List<GoodsActivityRel> findPageList(Pager pager);

	int update(GoodsActivityRel goodsActivityRel);

	int deleteById(java.lang.Integer relId);

	GoodsActivityRel findById(java.lang.Integer relId);

	List<GoodsActivityRel> findPageListMore(Pager pager);
	
	/**
     * 根据商品栏目id查询商品
     * @param reCommendId
     * @param storeid
     */
	List<GoodsActivityRel> findStoregoodsList(Integer activity_id,Integer activity_type);
}
