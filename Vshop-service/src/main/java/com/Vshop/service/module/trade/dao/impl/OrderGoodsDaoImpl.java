package com.Vshop.service.module.trade.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.OrderGoods;
import com.Vshop.core.entity.vo.OrderGoodsVo;
import com.Vshop.service.module.trade.dao.OrderGoodsDao;
import com.Vshop.service.module.trade.dao.mapper.OrderGoodsMapper;

/**
 * Created by rabook on 2014/11/17.
 */

@Repository
public class OrderGoodsDaoImpl implements OrderGoodsDao {

	@Autowired
	private OrderGoodsMapper orderGoodsMapper;

	/**
	 * 插入订单项
	 * @param orderGoods
	 */
	@Override
	public void saveOrderGoods(OrderGoods orderGoods) {
		orderGoodsMapper.saveOrderGoods(orderGoods);
	}

	/**
	 * 修改订单项
	 * @param orderGoods
	 */
	@Override
	public void updateOrderGoods(OrderGoods orderGoods) {
		orderGoodsMapper.updateOrderGoods(orderGoods);
	}

	/**
	 * 根据订单id查询订单项
	 * @param orderId	订单id
	 * @return
	 */
	@Override
	public List<OrderGoods> findByOrderId(Integer orderId) {
		return orderGoodsMapper.findByOrderId(orderId);
	}

	/**
	 * 根据id查询订单项
	 * @param recId	订单项id
	 * @return
	 */
	@Override
	public OrderGoods findById(Integer recId) {
		return orderGoodsMapper.findById(recId);
	}
	
	/**
     * 根据物品id查询物品订单信息
     * @return
     */
	@Override
	public List<OrderGoodsVo> findOrderGoodsVoByGoodsId(Integer goodsId) {
		return orderGoodsMapper.findOrderGoodsVoByGoodsId(goodsId);
	}
	
	/**
     * 根据订单id删除订单项
     * @param orderId 订单id
     */
    public void deleteByOrderId(Integer orderId){
    	orderGoodsMapper.deleteByOrderId(orderId);
    }
}
