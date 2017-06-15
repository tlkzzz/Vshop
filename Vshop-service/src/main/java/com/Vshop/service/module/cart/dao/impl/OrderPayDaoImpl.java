package com.Vshop.service.module.cart.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.OrderPay;
import com.Vshop.service.module.base.BaseDao;
import com.Vshop.service.module.cart.dao.OrderPayDao;
import com.Vshop.service.module.cart.dao.mapper.OrderPayMapper;


/**
 * 项目名称：Vshop-front
 * 类名称：AddressDaoImpl
 * 类描述：
 * 创建人：liuhao
 * 创建时间：2014年12月27日 下午5:06:54 
 * 
 * 修改人：liuhao
 * 修改时间：2014年12月27日 下午5:06:54
 * 修改备注：
 */
@Repository
public class OrderPayDaoImpl extends BaseDao implements OrderPayDao {
    @Resource
    private OrderPayMapper orderPayMapper;

    /**
     * 根据用户ID 获取到订单数据
     */
    public List<OrderPay> queryBuyerId(OrderPay orderPay) {
        return orderPayMapper.queryBuyerId(orderPay);
    }


    /**
     * 保存订单数据
     */
    public void saveOrderPay(OrderPay orderPay) {
        orderPayMapper.saveOrderPay(orderPay);
    }

    /**
     * 通过id查询
     * @param orderPay
     * @return
     */
    @Override
    public OrderPay findById(OrderPay orderPay) {
        // TODO Auto-generated method stub
        return orderPayMapper.findById(orderPay);
    }
    
    /**
     * 通过支付单号和买家id修改状态
     * @param orderPay
     */
    @Override
    public void updateOrderPayState(OrderPay orderPay) {
        orderPayMapper.updateOrderPayState(orderPay);
    }


}
