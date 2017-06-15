package com.Vshop.service.module.trade.dao;

import com.Vshop.core.entity.base.OrderInvoice;

/**
 * 订单发票信息表
 * @author liukai
 */
public interface OrderInvoiceDao {
	/**
	 * 根据订单id查询订单发票信息
	 * @param orderId
	 * @return
	 */
	OrderInvoice findByOrderId(Integer orderId);
	
	/**
	 * 保存订单发票表
	 * @param orderInvoice
	 */
	void saveOrderInvoice(OrderInvoice orderInvoice);
}
