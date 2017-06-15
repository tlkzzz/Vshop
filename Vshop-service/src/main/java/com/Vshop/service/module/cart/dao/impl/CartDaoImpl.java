package com.Vshop.service.module.cart.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Cart;
import com.Vshop.service.module.base.BaseDao;
import com.Vshop.service.module.cart.dao.CartDao;
import com.Vshop.service.module.cart.dao.mapper.CartMapper;


/**
 *    
 * 项目名称：Vshop-front  
 * 
 *      
 * 类名称：CartDaoImpl   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年12月24日 下午10:29:06   
 * 修改人：liuhao   
 * 修改时间：2014年12月24日 下午10:29:06   
 * 修改备注：   
 * @version    
 *
 */
@Repository
public class CartDaoImpl extends BaseDao implements CartDao {
    @Resource
    private CartMapper cartMapper;

    /**
	 * 通过用户id查询购物车,同时可以传入商品的id和规格id查询存在相同商品
	 * @param cart
	 * @return
	 */
	public List<Cart> queryBuyCart(Cart cart){
		return cartMapper.queryBuyCart(cart);
	}
	
	/**
	 * 保存购物车
	 * @param cart
	 * @return
	 */
	public int saveCart(Cart cart){
		return cartMapper.saveCart(cart);
	}
	
	/**
	 * 修改购物车
	 * @param cart
	 */
	public void updateCart(Cart cart){
		cartMapper.updateCart(cart);
	}
	
	/**
	 * 删除购物车
	 * @param cartId
	 */
	public void deleteCart(Integer cartId){
		cartMapper.deleteCart(cartId);
	}
	
	/**
	 * 修改购物车数量
	 * @param cart
	 */
	public void updateCartNum(Cart cart){
		cartMapper.updateCartNum(cart);
	}
	
	/**
	 * 购物车下单  根据cartid 查询商品
	 * @Title: queryCartById 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param cart
	 * @param @return    设定文件 
	 * @return List<Cart>    返回类型 
	 * @throws
	 */
	public Cart queryCartById(Integer cartId){
		return cartMapper.queryCartById(cartId);
	}
	
	/**
	 * 根据用户id,商品id,商品规格id删除购物车
	 * @param memberId 用户id
	 * @param goodsId 商品id 
	 * @param specId 规格id
	 */
	@Override
	public void deleteByMGS(Integer memberId, Integer goodsId, Integer specId) {
		cartMapper.deleteByMGS(memberId, goodsId, specId);
	}
	
	/**
	 * 多个cartId查询购物车
	 * @param cart 需提供购物车id(可多条,用","隔开),用户id
	 * @return
	 */
	public List<Cart> queryByCartIds(Cart cart){
		return cartMapper.queryByCartIds(cart);
	}
	
	/**
	 * 根据用户id和店铺id查询购物车
	 * @param memberId
	 * @param StoreId
	 * @return
	 */
	@Override
	public List<Cart> queryCartByStoreId(Integer memberId, Integer StoreId) {
		return cartMapper.queryCartByStoreId(memberId, StoreId);
	}
	
	/**
	 * 清空购物车
	 * @param memberId 用户id
	 */
	public void deleteAllCartByMemberId(Integer memberId){
		cartMapper.deleteAllCartByMemberId(memberId);
	}
	
	/**
	 * 查询用户购物车数量
	 * @param memberId
	 * @return
	 */
	public Integer queryCountByMemberId(Integer memberId){
		return cartMapper.queryCountByMemberId(memberId);
	}
}
