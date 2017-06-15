package com.Vshop.service.module.trade.dao;

import java.util.List;

import com.Vshop.core.entity.base.OrderLog;

/**
 * Created by rabook on 2014/11/15.
 */
public interface OrderLogDao {
	
	/**
	 * 根据订单id查询订单日志
	 * @param orderId
	 * @return
	 */
	List<OrderLog> findByOrderId(Integer orderId);
	
	/**
     * 通过id查询订单日志
     * @param orderLogId 
     * @return
     */
	OrderLog findById(Integer orderLogId);
	
	/**
     * 插入orderLog实体
     * @param order
     * @return
     */
    void saveOrderLog(OrderLog orderLog);
	
    
    /**
     * 更新orderLog实体
     * @param order
     * @return
     */
    void updateOrderLog(OrderLog orderLog);
}
