package com.Vshop.service.module.goods.dao;

import java.util.List;

import com.Vshop.core.entity.base.GoodsActivity;
import com.Vshop.service.utils.page.Pager;

public interface GoodsActivityDao {

	Object save(GoodsActivity goodsActivity);
	
	List<GoodsActivity> findPageList(Pager pager);
	
	int update(GoodsActivity goodsActivity);
	
	int deleteById(java.lang.Integer activityId);

	GoodsActivity findById(java.lang.Integer activityId);

}
