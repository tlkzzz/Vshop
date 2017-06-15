package com.Vshop.service.module.cart.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.OrderPay;
import com.Vshop.core.orm.mybatis.SqlMapper;

/**
 * 项目名称：Vshop-front
 * 类名称：OrderPayMapper
 * 类描述：
 * 创建人：liuhao
 * 创建时间：2014年12月28日 下午9:58:11
 * 修改人：liuhao
 * 修改时间：2014年12月28日 下午9:58:11
 * 修改备注：
 */
@SqlMapper
public interface OrderPayMapper {
	
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
