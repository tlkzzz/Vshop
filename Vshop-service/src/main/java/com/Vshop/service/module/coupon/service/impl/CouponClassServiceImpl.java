package com.Vshop.service.module.coupon.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.CouponClass;
import com.Vshop.service.module.coupon.dao.CouponClassDao;
import com.Vshop.service.module.coupon.service.CouponClassService;
import com.Vshop.service.utils.page.Pager;

/**
 * 优惠券类型接口实现
 * @author kviuff
 * @date 2015-07-23 10:00:00
 */

@Service
public class CouponClassServiceImpl implements CouponClassService {
	
	@Resource
	private CouponClassDao couponClassDao;

	/**
	 * 保存优惠券类型
	 * @param conpon
	 */
	public void saveCoupon(CouponClass conpon) {
		couponClassDao.saveCoupon(conpon);
	}

	/**
	 * 删除优惠券类型
	 * @param classId
	 */
	public void deleteCoupon(Integer classId) {
		couponClassDao.deleteCoupon(classId);
	}

	/**
	 * 修改优惠券类型
	 * @param conpon
	 */
	public void updateCoupon(CouponClass conpon) {
		couponClassDao.updateCoupon(conpon);
	}

	/**
	 * 获取优惠分类总数
	 * @param pager
	 * @return
	 */
	public int findCouponCount(CouponClass conpon) {
		return couponClassDao.findCouponCount(conpon);
	}

	/**
	 * 获取优惠券分类列表
	 * @param pager
	 * @return
	 */
	public List<CouponClass> findCouponPageList(Pager pager) {
		return couponClassDao.findCouponPageList(pager);
	}

	/**
	 * 获取所有的优惠券类型列表
	 */
	public List<CouponClass> findAllCouponList() {
		return couponClassDao.findAllCouponList();
	}

	/**
	 * 根据id获取优惠券分类
	 * @param id
	 * @return
	 */
	public CouponClass getCouponById(int id) {
		return couponClassDao.getCouponById(id);
	}
	
	
	
}
