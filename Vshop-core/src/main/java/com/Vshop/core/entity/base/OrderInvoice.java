package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;

/**
 * 订单发票表
 * @author liukai
 */
@Data
@ToString
public class OrderInvoice {
	
	/**
	 * 索引id
	 */
	private Integer invId;
	
	/**
	 * 用户id
	 */
	private Integer memberId;
	
	/**
	 * 订单id
	 */
	private Integer orderId;
	
	/**
	 * 1:普通发票 2:增值税发票
	 */
	private String invState;
	
	/**
	 * 发票抬头[普通发票]
	 */
	private String invTitle;
	
	/**
	 * 发票内容[普通发票]
	 */
	private String invContent;
	
	/**
	 * 单位名称
	 */
	private String invCompany;
	
	/**
	 * 纳税人识别号
	 */
	private String invCode;
	
	/**
	 * 注册地址
	 */
	private String invRegAddr;
	
	/**
	 * 注册电话
	 */
	private String invRegPhone;
	
	/**
	 * 开户银行
	 */
	private String invRegBname;
	
	/**
	 * 银行帐户
	 */
	private String invRegBaccount;
	
	/**
	 * 收票人姓名
	 */
	private String invRecName;
	
	/**
	 * 收票人手机号
	 */
	private String invRecMobphone;
	
	/**
	 * 收票人省份
	 */
	private String invRecProvince;
	
	/**
	 * 送票地址
	 */
	private String invGotoAddr;
	
	/**
	 * 是否是默认的
	 * 0:否
	 * 1:是
	 */
	private Integer isDefault;
}
