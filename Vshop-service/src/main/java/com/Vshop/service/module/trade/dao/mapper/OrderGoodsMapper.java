package com.Vshop.service.module.trade.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.OrderGoods;
import com.Vshop.core.entity.vo.OrderGoodsVo;
import com.Vshop.core.orm.mybatis.SqlMapper;

/**
 * 订单商品(订单项)
 * @author Liuk
 */
@SqlMapper
public interface OrderGoodsMapper {
	
	/**
	 * 插入订单项
	 * @param orderGoods
	 */
    void saveOrderGoods(OrderGoods orderGoods);

    /**
     * 	修改订单项
     * @param orderGoods
     */
    void updateOrderGoods(OrderGoods orderGoods);
    
    /**
     * 根据订单id查询订单项
     * @param orderId	订单id
     * @return
     */
    List<OrderGoods> findByOrderId(Integer orderId);
    
    /**
     * 根据id查询订单项
     * @param recId 订单项id
     * @return
     */
    OrderGoods findById(Integer recId);
    
    /**
     * 根据物品id查询物品订单信息
     * @return
     */
    List<OrderGoodsVo> findOrderGoodsVoByGoodsId(Integer goodsId);
    
    /**
     * 根据订单id删除订单项
     * @param orderId 订单id
     */
    void deleteByOrderId(Integer orderId);
}
