package com.Vshop.service.module.trade.dao.mapper;

import java.util.List;

import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.core.entity.Order;
import com.Vshop.service.utils.page.Pager;

/**
 * 订单
 * @author liukai
 */
@SqlMapper
public interface OrderMapper {
	
	/**
	 * 插入订单
	 * @param order
	 */
	void saveOrder(Order order);
	
	/**
	 * 删除订单
	 * @param orderId 订单id
	 */
	void deleteOrder(Integer orderId);
	
	/**
	 * 修改订单
	 * @param order
	 */
	void updateOrder(Order order);
	
	/**
	 * 修改订单状态
	 * @param order 订单实体,需orderaId,orderState字段,如果需要修改付款状态,需给paymentState字段赋值,
	 * 同时可以传入finnshedTime和shippingTime来更改订单完成和配送时间
	 */
	void updateOrderState(Order order);
	
	/**
	 * 根据订单编号修改订单状态和付款状态
	 * @param orderSn 订单编号
	 * @param orderState 订单状态
	 * @param paymentState 付款状态
	 * @param paymentTime 付款时间
	 */
	void updateOrderStateByOrderSn(String orderSn, Integer orderState, Integer paymentState,Long paymentTime);
	
	/**
	 * 分页查询订单总条数
	 * @param order 
	 * 			可加查询条件:订单编号,店铺名称,订单状态,买家名称,支付名称编号,开始结束时间(starttime,endtime),店铺id,买家id
	 * @return
	 */
	int findOrderCount(Order order); 
	
	/**
	 * 分页查询订单
	 * @param pager 
	 * 			可加查询条件:订单编号,店铺名称,订单状态,买家名称,支付名称编号,开始结束时间(starttime,endtime),店铺id,买家id
	 * @return
	 */
	List<Order> findOrderList(Pager pager);
	
	List<Order> findOrderListByCodeStatus(Pager pager);
	
	/**
	 * 根据id查询订单,有订单项,订单日志
	 * @param orderId
	 * @return
	 */
	Order findById(Integer orderId);
	
	/**
	 * 订单详情,必传订单id,可传用户id和店铺id
	 * @param order
	 * @return
	 */
	Order findOrderDetail(Order order);
	
	/**
	 * 根据订单编号查询订单信息
	 * @param orderSn
	 * @return
	 */
	Order findByOrderSn(String orderSn);
	
	/**
	 * 根据订单支付编号查询订单
	 * @param paySn
	 * @return
	 */
	List<Order> findByPaySn(String paySn);
	
	/**
	 * 根据订单支付id查询订单
	 * @param payId
	 * @return
	 */
	List<Order> findByPayId(Integer payId);
	
	/**
	 * 根据订单状态查询订单数量
	 * @param order 可加查询条件:订单编号,店铺名称,订单状态,买家名称,支付名称编号,开始结束时间(starttime,endtime),店铺id,买家id
	 * @return
	 */
	int findOrderCountByOrder(Order order);
}
