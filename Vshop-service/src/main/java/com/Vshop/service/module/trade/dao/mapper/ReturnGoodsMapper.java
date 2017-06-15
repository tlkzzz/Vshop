package com.Vshop.service.module.trade.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.ReturnGoods;
import com.Vshop.core.orm.mybatis.SqlMapper;

/**
 * 退货商品
 * @author liukai
 */
@SqlMapper
public interface ReturnGoodsMapper {
	
	/**
	 * 保存退货商品表
	 * @param returnGoods
	 */
	void saveReturnGoods(ReturnGoods returnGoods);
	
	/**
	 * 根据退货表id删除退货商品表
	 * @param returnId
	 */
	void deleteByReturnOrderId(Integer returnId);
	
	/**
	 * 根据退货表id删除退货商品信息
	 * @param returnId
	 * @return
	 */
	List<ReturnGoods> findByReturnOrderId(Integer returnId);
}
