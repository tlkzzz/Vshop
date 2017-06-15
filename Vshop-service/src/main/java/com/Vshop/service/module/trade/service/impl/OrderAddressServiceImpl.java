package com.Vshop.service.module.trade.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.OrderAddress;
import com.Vshop.service.module.trade.dao.OrderAddressDao;
import com.Vshop.service.module.trade.service.OrderAddressService;

/**
 * 订单收货地址
 * @author liukai
 */
@Service
public class OrderAddressServiceImpl implements OrderAddressService{
	
	@Resource
	private OrderAddressDao orderAddressDao;
	
	/**
	 * 保存订单收货地址
	 * @param orderAddress
	 */
	@Override
	public void saveOrderAddress(OrderAddress orderAddress) {
		orderAddressDao.saveOrderAddress(orderAddress);
	}
	
	/**
	 * 通过id查询订单收货地址
	 * @param addressId
	 * @return
	 */
	@Override
	public OrderAddress findById(Integer addressId) {
		return orderAddressDao.findById(addressId);
	}
	
	/**
	 * 修改订单收货地址
	 * @param orderAddress
	 */
	@Override
	public void updateAddress(OrderAddress orderAddress) {
		orderAddressDao.updateAddress(orderAddress);
	}
	
}
