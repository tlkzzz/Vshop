package com.Vshop.service.module.coupon.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.Coupon;
import com.Vshop.core.entity.base.CouponGoods;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.service.module.coupon.dao.CouponDao;
import com.Vshop.service.module.coupon.service.CouponService;
import com.Vshop.service.utils.page.Pager;

/**
 * 优惠券dao
 * @author kviuff
 * @date 2015-07-27 17:50:00
 */
@Service
public class CouponServiceImpl implements CouponService {

	@Resource
	private CouponDao couponDao;
	
	/**
	 * 保存优惠券
	 * @param coupon
	 */
	public void saveCoupon(Coupon coupon) {
		couponDao.saveCoupon(coupon);
	}

	/**
	 * 删除优惠券
	 * @param id
	 */
	public void deleteCoupon(int id) {
		couponDao.deleteCoupon(id);
	}

	/**
	 * 修改优惠券
	 * @param coupon
	 */
	public void updateCoupon(Coupon coupon) {
		couponDao.updateCoupon(coupon);
	}

	/**
	 * 获取优惠券数量
	 * @param pager
	 * @return
	 */
	public int findCouponCount(Coupon coupon) {
		return couponDao.findCouponCount(coupon);
	}

	/**
	 * 获取优惠券分页列表
	 * @param pager
	 * @return
	 */
	public List<Coupon> findCouponPagerList(Pager pager) {
		return couponDao.findCouponPagerList(pager);
	}

	/**
	 * 根据id获取优惠券
	 * @param id
	 * @return
	 */
	public Coupon getCouponById(int id) {
		return couponDao.getCouponById(id);
	}

	/**
	 * 获取所有的优惠券列表
	 * @return
	 */
	public List<Coupon> findCouponAllList(int storeId) {
		return couponDao.findCouponAllList(storeId);
	}

	/**
	 * 计算优惠价钱
	 * @param storeId  店铺id
	 * @param MemberId 会员id
	 * @param couponId 优惠券id
	 */
	public BigDecimal getCouponPrice(int storeId, int MemberId, int couponId) {
		BigDecimal price = new BigDecimal(0);
		Coupon coupon = couponDao.getCouponById(couponId);
		// 优惠券开始时间
		long sLong = coupon.getStartTime();
		// 优惠券结束时间
		long eLong = coupon.getEndTime();
		// 当前时间
		long nLong = System.currentTimeMillis();
		// 判断当前时间是否在优惠券开始时间与结束时间之间
		// 如果在时间段内返回优惠的价格，如果不在返回0
		if(nLong >= sLong && nLong <= eLong){
			price = coupon.getCouponPrice();
		}else{
			price = BigDecimal.valueOf(0.0);
		}
		return price;
	}
	/**
	 * 通过条件获取优惠券列表
	 * @return
	 */
	@Override
	public List<Coupon> findCouponbycolumList(Coupon coupon) {
		return couponDao.findCouponbycolumList(coupon);
	}
	
	/**
	 * 通过couponClassId获取优惠券
	 * @return
	 */
	@Override
	public List<Coupon>  findCouponByClassId(int couponClassId){
		return couponDao.findCouponByClassId(couponClassId);
	}

	@Override
	public List<Goods> findGoodsByCouponId(int couponId) {
		return couponDao.findGoodsByCouponId(couponId);
	}

	@Override
	public void saveCouponGoods(List<CouponGoods> couponGoodsList) {
		couponDao.saveCouponGoods(couponGoodsList);
	}
	
}
