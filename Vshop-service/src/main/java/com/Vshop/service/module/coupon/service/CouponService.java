package com.Vshop.service.module.coupon.service;

import java.math.BigDecimal;
import java.util.List;

import com.Vshop.core.entity.base.Coupon;
import com.Vshop.core.entity.base.CouponGoods;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.service.utils.page.Pager;

/**
 * 优惠券接口
 * @author kviuff
 * @date 2015-07-27 17:50:00
 */
public interface CouponService {
	
	/**
	 * 保存优惠券
	 * @param coupon
	 */
	void saveCoupon(Coupon coupon);
	
	/**
	 * 删除优惠券
	 * @param id
	 */
	void deleteCoupon(int id);
	
	/**
	 * 修改优惠券
	 * @param coupon
	 */
	void updateCoupon(Coupon coupon);
	
	/**
	 * 获取优惠券数量
	 * @param pager
	 * @return
	 */
	int findCouponCount(Coupon coupon);
	
	/**
	 * 获取优惠券分页列表
	 * @param pager
	 * @return
	 */
	List<Coupon> findCouponPagerList(Pager pager);
	
	/**
	 * 根据id获取优惠券
	 * @param id
	 * @return
	 */
	Coupon getCouponById(int id);
	
	/**
	 * 获取所有的优惠券列表
	 * @return
	 */
	List<Coupon> findCouponAllList(int storeId);
	
	/**
	 * 计算优惠价钱
	 * @param storeId 店铺id
	 * @param MemberId 会员id
	 * @return
	 */
	BigDecimal getCouponPrice(int storeId, int MemberId, int couponId);
	/**
	 * 通过条件获取优惠券列表
	 * @return
	 */
	List<Coupon>  findCouponbycolumList(Coupon coupon);
	
	/**
	 * 通过couponClassId获取优惠券
	 * @return
	 */
	List<Coupon>  findCouponByClassId(int couponClassId);
	
	/**
	 * 通过couponId获取产品列表
	 * @param couponId
	 * @return
	 */
	List<Goods> findGoodsByCouponId(int couponId);
	
	void saveCouponGoods(List<CouponGoods> couponGoodsList);
}
