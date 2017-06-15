package com.Vshop.service.module.trade.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.OrderDaddress;
import com.Vshop.service.module.trade.dao.OrderDaddressDao;
import com.Vshop.service.module.trade.service.OrderDaddressService;

/**
 * 订单发货地址
 * @author liukai
 */
@Service
public class OrderDaddressServiceImpl implements OrderDaddressService{
	
	@Resource
	private OrderDaddressDao orderDaddressDao;
	
	/**
	 * 保存订单发货地址
	 * @param daddress
	 */
	@Override
	public void saveOrderDaddress(OrderDaddress OrderDaddress) {
		orderDaddressDao.saveOrderDaddress(OrderDaddress);
	}
	
	/**
	 * 修改订单发货地址
	 * @param daddress
	 */
	@Override
	public void updateOrderDaddress(OrderDaddress orderDaddress) {
		orderDaddressDao.updateOrderDaddress(orderDaddress);
	}
	
	/**
	 * 根据id查询订单发货地址
	 * @param addressId
	 * @return
	 */
	@Override
	public OrderDaddress findOrderDaddressById(Integer addressId) {
		return orderDaddressDao.findOrderDaddressById(addressId);
	}
	

}
