package com.Vshop.core.entity.searchbean;

/**
 * 优惠券查询条件
 * @author zhaorh
 * @data 2015-10-13 10:41:00
 */
public class CouponSearch {
	/**
	 * 会员ID
	 */
	private Integer memberId;
	/**
	 * 是否已使用
	 */
	private Integer couponIsUser;
	/**
	 * 店铺ID
	 */
	private Integer storeId;
	/**
	 * 当前时间，用于查询已过期优惠券
	 */
	private Long time;
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getCouponIsUser() {
		return couponIsUser;
	}
	public void setCouponIsUser(Integer couponIsUser) {
		this.couponIsUser = couponIsUser;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	
	
}
