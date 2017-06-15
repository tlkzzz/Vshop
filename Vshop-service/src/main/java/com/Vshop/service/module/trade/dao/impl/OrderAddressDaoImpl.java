package com.Vshop.service.module.trade.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.OrderAddress;
import com.Vshop.service.module.trade.dao.OrderAddressDao;
import com.Vshop.service.module.trade.dao.mapper.OrderAddressMapper;

/**
 * 订单收货地址
 * @author liukai
 */
@Repository
public class OrderAddressDaoImpl implements OrderAddressDao{
	
	@Resource
	private OrderAddressMapper orderAddressMapper;
	
	/**
	 * 保存订单收货地址
	 * @param orderAddress
	 */
	@Override
	public void saveOrderAddress(OrderAddress orderAddress) {
		orderAddressMapper.saveOrderAddress(orderAddress);
	}
	
	/**
	 * 通过id查询订单收货地址
	 * @param addressId
	 * @return
	 */
	@Override
	public OrderAddress findById(Integer addressId) {
		return orderAddressMapper.findById(addressId);
	}
	
	/**
	 * 修改订单收货地址
	 * @param orderAddress
	 */
	@Override
	public void updateAddress(OrderAddress orderAddress) {
		orderAddressMapper.updateAddress(orderAddress);
	}

}
