package com.Vshop.service.module.coupon.dao;

import java.util.List;

import com.Vshop.core.entity.base.CouponClass;
import com.Vshop.service.utils.page.Pager;

/**
 * 优惠券类型dao
 * @author kviuff
 * @date 2015-07-23 10:00:00
 */
public interface CouponClassDao {

	/**
	 * 保存优惠券类型
	 * @param conpon
	 */
	void saveCoupon(CouponClass conpon);
	
	/**
	 * 删除优惠券类型
	 * @param classId
	 */
	void deleteCoupon(Integer classId);
	
	/**
	 * 修改优惠券类型
	 * @param conpon
	 */
	void updateCoupon(CouponClass conpon);
	
	/**
	 * 获取优惠分类总数
	 * @param pager
	 * @return
	 */
	int findCouponCount(CouponClass conpon);
	
	/**
	 * 获取优惠券分类列表
	 * @param pager
	 * @return
	 */
	List<CouponClass> findCouponPageList(Pager pager);
	
	/**
	 * 获取所有的优惠券列表
	 * @return
	 */
	List<CouponClass> findAllCouponList();
	
	/**
	 * 根据id获取优惠券分类
	 * @param id
	 * @return
	 */
	CouponClass getCouponById(int id);
}
