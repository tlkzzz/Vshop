package com.Vshop.service.module.trade.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.ReturnGoods;
import com.Vshop.service.module.trade.dao.ReturnGoodsDao;
import com.Vshop.service.module.trade.dao.mapper.ReturnGoodsMapper;

/**
 * 退货商品
 * @author liukai
 */
@Repository
public class ReturnGoodsDaoImpl implements ReturnGoodsDao{
	
	@Resource
	private ReturnGoodsMapper returnGoodsMapper;
	
	/**
	 * 保存退货商品表
	 * @param returnGoods
	 */
	public void saveReturnGoods(ReturnGoods returnGoods){
		returnGoodsMapper.saveReturnGoods(returnGoods);
	}
	
	/**
	 * 根据退货表id删除退货商品表
	 * @param returnId
	 */
	public void deleteByReturnOrderId(Integer returnId){
		returnGoodsMapper.deleteByReturnOrderId(returnId);
	}
	
	/**
	 * 根据退货表id删除退货商品信息
	 * @param returnId
	 * @return
	 */
	public List<ReturnGoods> findByReturnOrderId(Integer returnId){
		return returnGoodsMapper.findByReturnOrderId(returnId);
	}
}
