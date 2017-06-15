package com.Vshop.service.module.trade.service;

import com.Vshop.core.entity.base.OrderDaddress;

/**
 * 订单发货地址
 * @author liukai
 */
public interface OrderDaddressService {
	/**
	 * 保存订单发货地址
	 * @param daddress
	 */
	void saveOrderDaddress(OrderDaddress OrderDaddress);
	
	/**
	 * 修改订单发货地址
	 * @param daddress
	 */
	void updateOrderDaddress(OrderDaddress orderDaddress);
	
	/**
	 * 根据id查询订单发货地址
	 * @param addressId
	 * @return
	 */
	OrderDaddress findOrderDaddressById(Integer addressId);
}
