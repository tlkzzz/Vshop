package com.Vshop.service.module.goods.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.GoodsActivityRel;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;

@SqlMapper
public interface GoodsActivityRelMapper {

	Integer save(GoodsActivityRel goodsActivityRel);

	List<GoodsActivityRel> findPageList(Pager pager);

	int update(GoodsActivityRel goodsActivityRel);

	int deleteById(java.lang.Integer relId);

	GoodsActivityRel findById(java.lang.Integer relId);
	
	List<GoodsActivityRel> findPageListMore(Pager pager);
	
	List<GoodsActivityRel> findgoodsList(java.lang.Integer activeid,java.lang.Integer activetype);
	
}
