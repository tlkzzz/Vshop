package com.Vshop.front.module.api.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
import lombok.ToString;

/**
 * 
 * 
 * 项目名称：Vshop-entity 类名称：Member 类描述：会员实体类 创建人：sangyuchen 创建时间：2014年11月10日
 * 下午1:17:51 修改人：sangyuchen 修改时间：2014年11月10日 下午1:17:51 修改备注：
 * 
 * @version 1.0
 * 
 */
@Data
@ToString
public class MemberApiBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
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
	private Timestamp memberBirthday;
	
	/**
	 * 会员积分
	 */
	private Integer memberPoints;
	
	/**
	 * 预存款可用金额
	 */
	private String availablePredeposit;
	
	/**
	 * 预存款冻结金额
	 */
	private String freezePredeposit;
	
	/**
	 * 
	 */
	private String memberAreainfo;
	
	/**
	 * 手机号
	 */
	private String memberMobile;
	
	/**
	 * 待付款订单数量
	 */
	private int noPayOrder;
	
	/**
	 * 待收货数量
	 */
	private int noReceiveOrder;
	
	/**
	 * 待评论订单数量
	 */
	private int noEvaluationOrder;
	
	/**
	 * 待发货订单数量
	 */
	private int noFilledOrder;
	
	/**
	 * 已完成订单
	 */
	private int finishOrder;
	
	/**
	 * 收藏的课程的数量
	 */
	private Integer favGoodsCount;
	
	/**
	 * 收藏的学院的数量
	 */
	private Integer favStoreCount;
	
}