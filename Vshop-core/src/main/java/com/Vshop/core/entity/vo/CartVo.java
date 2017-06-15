package com.Vshop.core.entity.vo;

import java.util.Iterator;
import java.util.List;

import lombok.Data;
import lombok.ToString;

import com.google.common.collect.Lists;
import com.Vshop.core.entity.base.Cart;


/**
 * 购物车超类
 *    
 * 项目名称：Vshop-front   
 * 类名称：CartVo   
 * 类描述：   
 * 修改备注：   
 * @version    
 *
 */
@Data
@ToString
public class CartVo {
	
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
    private double goodsTotalPrice;
    
    /**
     * 商品总运费
     */
    private Double goodsTotalFreight;
    
    /**
     * 商品店铺id
     */
    private Integer storeId;
    
    /**
     * 商品店铺名称
     */
    private String storeName;
    
    /**
     * 多个购物车id
     */
    private String cartIds;
    
    /**
     * 订单留言
     */
    private String orderMessage;
    
    /**
     * 不包邮的商品的数量
     */
    private int transGoodsNum;
    
    public void add(Cart cart){
        list.add(cart);
    }

    public int update(Cart cart){
    	//修改商品标识,若为0,商品修改时同一商品数量超过100
    	int result = 1;
        for(Cart cart1 : list){
            if(cart1.getGoodsId().equals(cart.getGoodsId())&&cart1.getSpecId().equals(cart.getSpecId())){
            	//购物车同一件商品数量不能超过100
            	if(cart1.getGoodsNum() + cart.getGoodsNum()<=100){
            		cart1.setGoodsNum((short) (cart1.getGoodsNum() + cart.getGoodsNum()));
            		result = -2;
            	}else{ //商品修改时同一商品数量超过100
            		result = 0;
            	}
            }
        }
        //判断是否添加
        if(result==1){
        	add(cart);
        }
        return result;
    }

    public void delete(int goodsId,int specId){

        Iterator<Cart> iterator = list.iterator();
        while(iterator.hasNext()) {
             Cart cart = iterator.next();
             if(cart.getGoodsId() == goodsId&&cart.getSpecId() == specId) {
                 iterator.remove();
             }
        }
    }
}
