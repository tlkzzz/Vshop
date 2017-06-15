package com.Vshop.service.module.trade.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.OrderInvoice;
import com.Vshop.service.module.trade.dao.OrderInvoiceDao;
import com.Vshop.service.module.trade.service.OrderInvoiceService;

/**
 * 订单发票表
 * @author liukai
 */
@Service
public class OrderInvoiceServiceImpl implements OrderInvoiceService{
	
	@Resource
	private OrderInvoiceDao orderInvoiceDao;
	
	/**
	 * 根据订单id查询订单发票信息
	 * @param orderId
	 * @return
	 */
	public OrderInvoice findByOrderId(Integer orderId){
		return orderInvoiceDao.findByOrderId(orderId);
	}
	
	/**
	 * 保存订单发票表
	 * @param orderInvoice
	 */
	public void saveOrderInvoice(OrderInvoice orderInvoice){
		orderInvoiceDao.saveOrderInvoice(orderInvoice);
	}
}
