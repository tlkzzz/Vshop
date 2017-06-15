package com.Vshop.core.entity.vo;

import java.math.BigDecimal;
import java.util.List;

import com.google.common.collect.Lists;
import com.Vshop.core.entity.base.Cart;

import lombok.Data;
import lombok.ToString;

/**
 * 生成订单使用的订单超类
 * @author liukai
 */
@Data
@ToString
public class OrderVo{
	
	/**
	 * 购物车集合
	 */
	private List<Cart> list = Lists.newArrayList();
	
	/**
	 * 商品总数量
	 */
    private int goodsNum;
    
    /**
     * 商品总价格
     */
    private BigDecimal goodsAmount;
    
    /**
     * 订单运费
     */
    private BigDecimal shippingFee;
    
    /**
     * 商品店铺id
     */
    private Integer storeId;
    
    /**
     * 商品店铺名称
     */
    private String storeName;
    
    /**
     * 订单总价(应付金额)
     */
    private BigDecimal orderAmount;
    
    /**
     * 余额支付金额
     */
    private BigDecimal predepositAmount;
    
    public OrderVo(){
    	predepositAmount = BigDecimal.valueOf(0.0);
    }
}