package com.Vshop.service.module.coupon.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.CouponMember;
import com.Vshop.core.entity.searchbean.CouponSearch;
import com.Vshop.service.module.coupon.dao.CouponMemberDao;
import com.Vshop.service.module.coupon.dao.mapper.CouponMemberMapper;

/**
 * 会员领取优惠券dao实现
 * @author kviuff
 * @date 2015-07-31 16:00:00
 */
@Repository
public class CouponMemberDaoImpl implements CouponMemberDao {

	@Resource
	private CouponMemberMapper couponMemberMapper;
	/**
	 * 保存领取优惠券
	 * @param couponMember
	 */
	public void saveCouponMember(CouponMember couponMember) {
		couponMemberMapper.saveCouponMember(couponMember);
	}

	/**
	 * 根据会员id获取所领取的优惠券数量
	 * @param memberId
	 * @return
	 */
	public int getCouponMemberCount(Integer couponId) {
		return couponMemberMapper.getCouponMemberCount(couponId);
	}

	/**
	 * 根据会员id和店铺id获取优惠券列表
	 * @param memberId
	 * @param storeId
	 * @return
	 */
	public List<CouponMember> getCouponListByMemberIdAndStoreId(CouponSearch couponSearch) {
		return couponMemberMapper.getCouponListByMemberIdAndStoreId(couponSearch);
	}

	/**
	 * 修改是否已使用状态
	 * @param couponMember
	 */
	public void updateCouponMember(CouponMember couponMember) {
		couponMemberMapper.updateCouponMember(couponMember);
	}

}
