package com.Vshop.core.entity.apibean;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * 客户端接口所用的实体－会员
 * @author KVIUFF
 *
 */
@Data
@ToString
public class MemberApiBean implements Serializable {

	private static final long serialVersionUID = -4637080967621999359L;
	
	/**
	 * 会员id
	 */
	private Integer memberId;
	
	/**
	 * 会员名称
	 */
	private String memberName;
	
	/**
	 * 真实姓名
	 */
	private String memberTruename;
	
	/**
	 * 会员头像
	 */
	private String memberAvatar;
	
	/**
	 * 会员性别
	 */
	private Integer memberSex;
	
	/**
	 * 会员生日
	 */
	private Long memberBirthday;
	
	/**
	 * 邮箱
	 */
	private String memberEmail;
	
	/**
	 * QQ
	 */
	private String memberQq;
	
	/**
	 * 手机号
	 */
	private String memberMobile;
	/**
	 * 未支付的订单数量-非数据库字段
	 */
	private int noPayOrder;
	
	/**
	 * 待收货订单数量-非数据库字段
	 */
	private int noReceiveOrder;
	
	/**
	 * 待发货订单数量-非数据库字段
	 */
	private int noFilledOrder;
	
	/**
	 * 已完成的订单数量-非数据库字段
	 */
	private int finishOrder;
	
	/**
	 * 收藏的商品的数量-非数据库字段
	 */
	private int favGoodsCount;
	
	/**
	 * 收藏的店铺的数量-非数据库字段
	 */
	private int favStoreCount;
	
	/**
	 * 用户名code
	 */
	private String memberNameCode;
	
	/**
	 * 会员消费积分
	 */
	private String memberConsumePoints;
	
	/**
	 * 预存款可用金额
	 */
	private String availablePredeposit;
	
	/**
	 * 地区内容
	 */
	private String memberAreainfo;
	
	
}
