package com.Vshop.service.module.trade.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.OrderInvoice;
import com.Vshop.service.module.trade.dao.OrderInvoiceDao;
import com.Vshop.service.module.trade.dao.mapper.OrderInvoiceMapper;

/**
 * 订单发票表
 * @author liukai
 */
@Repository
public class OrderInvoiceDaoImpl implements OrderInvoiceDao{
	
	@Resource
	private OrderInvoiceMapper orderInvoiceMapper;
	
	/**
	 * 根据订单id查询订单发票信息
	 * @param orderId
	 * @return
	 */
	public OrderInvoice findByOrderId(Integer orderId){
		return orderInvoiceMapper.findByOrderId(orderId);
	}
	
	/**
	 * 保存订单发票表
	 * @param orderInvoice
	 */
	public void saveOrderInvoice(OrderInvoice orderInvoice){
		orderInvoiceMapper.saveOrderInvoice(orderInvoice);
	}

}
