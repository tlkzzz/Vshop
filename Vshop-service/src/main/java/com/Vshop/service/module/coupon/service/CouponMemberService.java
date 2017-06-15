package com.Vshop.service.module.coupon.service;

import java.util.List;

import com.Vshop.core.entity.base.CouponMember;
import com.Vshop.core.entity.searchbean.CouponSearch;

/**
 * 会员领取优惠券接口
 * @author kviuff
 * @date 2015-07-31 16:00:00
 */
public interface CouponMemberService {
	
	/**
	 * 保存领取优惠券
	 * @param couponMember
	 */
	void saveCouponMember(CouponMember couponMember);
	
	/**
	 * 根据会员id获取所领取的优惠券数量
	 * @param memberId
	 * @return
	 */
	int getCouponMemberCount(Integer couponId);
	
	/**
	 * 根据会员id和店铺id获取优惠券列表
	 * @param couponSearch 查询实体
	 * @return
	 */
	List<CouponMember> getCouponListByMemberIdAndStoreId(CouponSearch couponSearch);
	
	/**
	 * 修改是否已使用状态
	 * @param couponMember
	 */
	void updateCouponMember(CouponMember couponMember);
}
