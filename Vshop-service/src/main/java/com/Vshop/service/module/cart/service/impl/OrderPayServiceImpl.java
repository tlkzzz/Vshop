package com.Vshop.service.module.cart.service.impl;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.OrderPay;
import com.Vshop.service.module.cart.dao.OrderPayDao;
import com.Vshop.service.module.cart.service.OrderPayService;

@Service
@Slf4j
public class OrderPayServiceImpl implements OrderPayService {
	
	@Resource
	private OrderPayDao orderPayDao;
	
	/**
	 * 根据用户id查询
	 * @param orderPay
	 * @return
	 */
	@Override
	public List<OrderPay> queryBuyerId(OrderPay orderPay) {
		return orderPayDao.queryBuyerId(orderPay);
	}
	
	/**
     * 保存
     * @param orderPay
     */
	@Override
	public void saveOrderPay(OrderPay orderPay) {
		orderPayDao.saveOrderPay(orderPay);
	}
	
	/**
     * 通过id查询
     * @param orderPay
     * @return
     */
	@Override
	public OrderPay findById(OrderPay orderPay) {
		return orderPayDao.findById(orderPay);
	}
	
	/**
     * 通过支付单号和买家id修改状态
     * @param orderPay
     */
	@Override
	public void updateOrderPayState(OrderPay orderPay) {
		orderPayDao.updateOrderPayState(orderPay);
	}
}
