package com.Vshop.core.entity.base;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CouponMember extends Coupon implements Serializable {
	private static final long serialVersionUID = -4811992758304590941L;
	/**
	 * 优惠券id
	 */
	private Integer couponId;
	/**
	 * 会员id
	 */
	private Integer couponMemberId;
	
	/**
	 * 是否使用:0没有使用，1已使用
	 */
	private Integer couponIsUser;
	
	/**
	 * 优惠券是否达到优惠金额(能否使用),0:可以使用,1:不能使用
	 */
	private Integer couponSuc;
	
	private String storeLabel;
	
	/**
	 * 1 未过期   0已过期
	 */
	private String timeLimit;
}
