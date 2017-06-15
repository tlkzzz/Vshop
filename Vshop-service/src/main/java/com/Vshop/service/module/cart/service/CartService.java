package com.Vshop.service.module.cart.service;


import java.util.List;
import java.util.Map;

import com.Vshop.core.entity.GoodsSpec;
import com.Vshop.core.entity.base.Cart;
import com.Vshop.core.entity.base.CouponMember;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.vo.CartVo;

/**
 * 
 */
public interface CartService {
	
	/**
	 * 通过用户id查询购物车
	 * @param memberId 用户id
	 * @return
	 */
	List<Cart> queryBuyCart(Integer memberId);
	
	/**
	 * 根据用户id和店铺id查询购物车
	 * @param memberId
	 * @param StoreId
	 * @return
	 */
	List<Cart> queryCartByStoreId(Integer memberId, Integer StoreId);
	
	/**
	 * 通过用户id查询购物车
	 * @param memberId 
	 * @return 
	 */
	List<CartVo> queryCartByMemberID(Integer memberId);
	
	/**
	 * 通过多个购物车id查询购物车,分单
	 * @param cartIds 返回一个分单后的CartVo集合,一个CartVo为一个订单
	 * @return
	 */
	List<CartVo> queryVOListByCartIds(String cartIds);
	
	/**
	 * 保存购物车
	 * @param goods 商品实体
	 * @param memberId 用户id
	 * @param count 商品数量
	 * @param spec 商品规格
	 * @param saveType 加入类型(加入购物车:0/立即购买:1)
	 * @return
	 */
	int saveCart(Goods goods,Integer memberId,Integer count,GoodsSpec goodsSpec,Integer saveType);
	
	/**
	 * 删除购物车 
	 * @param cartId 购物车id
	 * @return
	 */
	int deleteCart(Integer cartId); 
	
	/**
	 * 根据用户id,商品id,商品规格id删除购物车
	 * @param memberId 用户id
	 * @param goodsId 商品id 
	 * @param specId 规格id
	 */
	void deleteByMGS(Integer memberId, Integer goodsId, Integer specId);
	
	/**
	 * 修改购物车数量
	 * @param cartId 购物车id
	 * @param count 商品数量
	 * @return 返回int类型,1为成功,0为失败
	 */
	int updatecart(Integer cartId,Integer count);
	
	/**
	 * 批量查询购物车
	 * @param cartIds 多个购物车id,多个之间以逗号隔开
	 * @param memberId 用户id
	 * @return
	 */
	List<Cart> queryCartByIds(String cartIds,Integer memberId);
	
	/**
	 * 根据cartid 查询商品
	 * @Title: queryCartById 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param cart
	 * @param @return    设定文件 
	 * @return List<Cart>    返回类型 
	 * @throws
	 */
	Cart queryCartById(Integer cartId);
	
	/**
	 * 批量查询购物车,不支持分单
	 * @param cartIds 多个购物车id,多个之间以逗号隔开
	 * @param memberId 用户id
	 * @return 返回一个CartVo,购物车集合保存在cartVo中
	 */
	CartVo queryCartByCartIds(String cartIds,Integer memberId);
	
	/**
	 * 将商品信息存入cart实体中
	 * @param goods
	 * @param goodsSpec
	 */
	Cart copyGoodsToCart(Goods goods,GoodsSpec goodsSpec);
	
	/**
	 * 登录保存购物车信息
	 * @param cartVo
	 */
	void addLoginCart(CartVo cartVo,Integer memberId);
	
	/**
     * 获取到订单的总数，总金额
     * @param cartList 购物车集合
     * @return
     */
	CartVo getCartVoByCart(List<Cart> cartList);
	
	/**
     * 获取订单总金额和订单总数量
     * @param cartVoList CartVo集合
     * @return 返回一个Map<String,Object>集合,键:"goodsNum",商品总数量;"goodsTotalPrice",订单总价格(不含运费)
     */
	Map<String,Object> getTotalPrice(List<CartVo> cartVoList);
	
	
	/**
	 * 计算订单最后应付金额
	 * @param cartIds 购物车的id
	 * @param freight 运费信息
	 * @param couponId 优惠券id
	 * @param cityId 城市id
	 * @param isPd 是否使用余额
	 * @param memberId 用户id
	 * @return
	 */
	Map<String,Object> queryTotalPrice(String cartIds,String freight, String couponId, String cityId, String isPd, Integer memberId);
	

	/**
	 * 查询商品库存,和商品价格是否变动
	 * @param cartIds 
	 * @return 返回一个Map<String,Object>,键为类型:understock:库存不足,pricechange:价格变动,值为两个List<Cart>
	 */
	Map<String,Object> orderValidation(String cartIds);
	
	/**
	 * 通过cartIds,查询运费,收货地址id可为空
	 * @param cartIds 购物车的id
	 * @param cityId 如果二级地区id(cityId)设为null,可为空,为空时返回的价格是默认的运费模板(全国)的价格标准
	 * @return Map<String,Object> 键为店铺id,值为对应运费的map
	 */
	Map<String,Object> queryFreightByCartIds(Integer cityId,String cartIds);
	
	/**
	 * 通过cartIds,用户id,查询优惠券
	 * @param cartIds 多个购物车id
	 * @param memberId 用户id
	 * @return 返回一个优惠券集合
	 */
	List<CouponMember> queryCouponByCartIds(String cartIds,Integer memberId);
	
	/**
	 * 清空购物车
	 * @param memberId 用户id
	 */
	void deleteAllCartByMemberId(Integer memberId);
	
	/**
	 * 查询用户购物车数量
	 * @param memberId
	 * @return
	 */
	Integer queryCountByMemberId(Integer memberId);
	
}
