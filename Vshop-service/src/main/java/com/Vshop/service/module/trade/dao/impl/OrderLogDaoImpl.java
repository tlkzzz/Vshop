package com.Vshop.service.module.trade.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.OrderLog;
import com.Vshop.service.module.trade.dao.OrderLogDao;
import com.Vshop.service.module.trade.dao.mapper.OrderLogMapper;

/**
 * Created by rabook on 2014/11/15.
 */

@Repository
public class OrderLogDaoImpl implements OrderLogDao{

    @Resource
    private OrderLogMapper orderLogMapper;
    
    /**
	 * 根据订单id查询订单日志
	 * @param orderId
	 * @return
	 */
	@Override
	public List<OrderLog> findByOrderId(Integer orderId) {
		orderLogMapper.findByOrderId(orderId);
		return null;
	}
	
	/**
     * 通过id查询订单日志
     * @param orderLogId 
     * @return
     */
	@Override
	public OrderLog findById(Integer orderLogId) {
		return orderLogMapper.findById(orderLogId);
	}
	
	/**
     * 插入orderLog实体
     * @param order
     * @return
     */
	@Override
	public void saveOrderLog(OrderLog orderLog) {
		orderLogMapper.saveOrderLog(orderLog);
	}
	
	/**
     * 更新orderLog实体
     * @param order
     * @return
     */
	@Override
	public void updateOrderLog(OrderLog orderLog) {
		orderLogMapper.updateOrderLog(orderLog);
	}
    
}
