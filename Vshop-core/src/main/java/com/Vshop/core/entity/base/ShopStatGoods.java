package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString
public class ShopStatGoods implements Serializable {
	private static final long serialVersionUID = 85346524896674295L;
	/**
	 * id
	 */
	private Integer sId;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 商品id
	 */
	private Integer goodsId;
	/**
	 * 会员名称
	 */
	private String memberName;
	/**
	 * 会员id
	 */
	private Integer memberId;
	/**
	 * 登陆者ip
	 */
	private String loginIp;
	/**
	 * 登陆时间
	 */
	private Long loginTime;
	/**
	 * 店铺id
	 */
	private Integer storeId;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 县
	 */
	private String area;
}
