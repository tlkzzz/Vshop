package com.Vshop.service.module.coupon.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.CouponMember;
import com.Vshop.core.entity.searchbean.CouponSearch;
import com.Vshop.service.module.coupon.dao.CouponMemberDao;
import com.Vshop.service.module.coupon.service.CouponMemberService;

/**
 * 会员领取优惠券接口实现
 * @author kviuff
 * @date 2015-07-31 16:00:00
 */
@Service
public class CouponMemberServiceImpl implements CouponMemberService {

	@Resource
	private CouponMemberDao couponMemberDao;
	/**
	 * 保存领取优惠券
	 * @param couponMember
	 */
	public void saveCouponMember(CouponMember couponMember) {
		couponMemberDao.saveCouponMember(couponMember);
	}

	/**
	 * 根据会员id获取所领取的优惠券数量
	 * @param memberId
	 * @return
	 */
	public int getCouponMemberCount(Integer couponId) {
		return couponMemberDao.getCouponMemberCount(couponId);
	}

	/**
	 * 根据会员id和店铺id获取优惠券列表
	 * @param memberId
	 * @param storeId
	 * @return
	 */
	public List<CouponMember> getCouponListByMemberIdAndStoreId(CouponSearch couponSearch) {
		return couponMemberDao.getCouponListByMemberIdAndStoreId(couponSearch);
	}

	/**
	 * 修改是否已使用状态
	 * @param couponMember
	 */
	public void updateCouponMember(CouponMember couponMember) {
		couponMemberDao.updateCouponMember(couponMember);
	}

}
