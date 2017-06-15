package com.Vshop.service.module.trade.service;

import com.Vshop.core.entity.base.OrderAddress;

/**
 * 订单收货地址
 * @author liukai
 */
public interface OrderAddressService {
	/**
	 * 保存订单收货地址
	 * @param orderAddress
	 */
	void saveOrderAddress(OrderAddress orderAddress);
	
	/**
	 * 通过id查询订单收货地址
	 * @param addressId
	 * @return
	 */
	OrderAddress findById(Integer addressId);
	
	/**
	 * 修改订单收货地址
	 * @param orderAddress
	 */
	void updateAddress(OrderAddress orderAddress);
}
