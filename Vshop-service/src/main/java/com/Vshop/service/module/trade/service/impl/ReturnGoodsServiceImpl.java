package com.Vshop.service.module.trade.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.ReturnGoods;
import com.Vshop.service.module.trade.dao.ReturnGoodsDao;
import com.Vshop.service.module.trade.service.ReturnGoodsService;

/**
 * 退货商品
 * @author liukai
 */
@Service
public class ReturnGoodsServiceImpl implements ReturnGoodsService{
	
	@Resource
	private ReturnGoodsDao returnGoodsDao;
	
	/**
	 * 保存退货商品表
	 * @param returnGoods
	 */
	public void saveReturnGoods(ReturnGoods returnGoods){
		returnGoodsDao.saveReturnGoods(returnGoods);
	}
	
	/**
	 * 根据退货表id删除退货商品表
	 * @param returnId
	 */
	public void deleteByReturnOrderId(Integer returnId){
		returnGoodsDao.deleteByReturnOrderId(returnId);
	}
	
	/**
	 * 根据退货表id删除退货商品信息
	 * @param returnId
	 * @return
	 */
	public List<ReturnGoods> findByReturnOrderId(Integer returnId){
		return returnGoodsDao.findByReturnOrderId(returnId);
	}
}
