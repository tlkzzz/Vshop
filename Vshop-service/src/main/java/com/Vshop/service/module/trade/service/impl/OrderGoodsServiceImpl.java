package com.Vshop.service.module.trade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.OrderGoods;
import com.Vshop.core.entity.vo.OrderGoodsVo;
import com.Vshop.service.module.trade.dao.OrderGoodsDao;
import com.Vshop.service.module.trade.service.OrderGoodsService;

/**
 * Created by rabook on 2014/11/17.
 */
@Service
public class OrderGoodsServiceImpl implements OrderGoodsService{

    @Autowired
    private OrderGoodsDao orderGoodsDao;
    
    /**
	 * 插入订单项
	 * @param orderGoods
	 */
	@Override
	public void saveOrderGoods(OrderGoods orderGoods) {
		orderGoodsDao.saveOrderGoods(orderGoods);
	}
	
    /**
     * 	修改订单项
     * @param orderGoods
     */
	@Override
	public void updateOrderGoods(OrderGoods orderGoods) {
		orderGoodsDao.updateOrderGoods(orderGoods);
	}
	
	/**
     * 根据订单id查询订单项
     * @param orderId	订单id
     * @return
     */
	@Override
	public List<OrderGoods> findByOrderId(Integer orderId) {
		return orderGoodsDao.findByOrderId(orderId);
	}
	
	/**
     * 根据id查询订单项
     * @param recId 订单项id
     * @return
     */
	@Override
	public OrderGoods findById(Integer recId) {
		return orderGoodsDao.findById(recId);
	}
	
	/**
     * 根据物品id查询物品订单信息
     * @return
     */
	@Override
	public List<OrderGoodsVo> findOrderGoodsVoByGoodsId(Integer goodsId) {
		return orderGoodsDao.findOrderGoodsVoByGoodsId(goodsId);
	}
	
    
    /**
     * 根据订单id删除订单项
     * @param orderId 订单id
     */
    public void deleteByOrderId(Integer orderId){
    	orderGoodsDao.deleteByOrderId(orderId);
    }
}
