package com.Vshop.service.module.cart.service;


import java.util.List;

import com.Vshop.core.entity.base.OrderPay;


/**
 *
 */
public interface OrderPayService {
	/**
	 * 根据用户id查询
	 * @param orderPay
	 * @return
	 */
    List<OrderPay> queryBuyerId(OrderPay orderPay);
    
    /**
     * 保存
     * @param orderPay
     */
    void saveOrderPay(OrderPay orderPay);
    
    /**
     * 通过id查询
     * @param orderPay
     * @return
     */
    OrderPay findById(OrderPay orderPay);
    
    /**
     * 通过支付单号和买家id修改状态
     * @param orderPay
     */
    void updateOrderPayState(OrderPay orderPay);
}
