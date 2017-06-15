package com.Vshop.service.module.trade.dao.mapper;

import com.Vshop.core.entity.base.OrderInvoice;
import com.Vshop.core.orm.mybatis.SqlMapper;

/**
 * 订单发票表
 * @author liukai
 */
@SqlMapper
public interface OrderInvoiceMapper {
	
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
