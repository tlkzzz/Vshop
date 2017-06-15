package com.Vshop.service.module.member.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *    
 * 项目名称：Vshop-admin   
 * 类名称：AccountVo   
 * 类描述： 
 * 创建人：sangyuchen   
 * 创建时间：2014年11月10日 下午1:19:53   
 * 修改人：sangyuchen   
 * 修改时间：2014年11月10日 下午1:19:53   
 * 修改备注：   
 * @version    
 *
 */
@Data
@ToString
public class AccountVo implements Serializable {
	private static final long serialVersionUID = -8426339414555997936L;
	private Long id;
	/**
	 * 登录帐号
	 */
	private String loginName;
	/**
	 * 真实名称
	 */
	private String realName;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 头像
	 */
	private Date birthday;
	/**
	 * 性别1:男;2:女
	 */
	private Integer gender;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 新密码
	 */
	private String newPassword;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * QQ号,也可能是邮箱
	 */
	private String qq;
	/**
	 * 旺旺号,也可能是邮箱
	 */
	private String ww;
	/**
	 * 手机号
	 */
	private String cellPhone;
	/**
	 * 认证级别 0:无级别;1:一级牛仔;2:二级牛仔;3:三级牛仔
	 */
	private Integer certifyClass;
	/**
	 * 预存款可用金额
	 */
	private String amount;
	/**
	 * 预存款冻结金额
	 */
	private String freezeAmount;
	/**
	 * 省份ID
	 */
	private Integer provinceId;
	/**
	 * 省份
	 */
	private String provinceName;
	/**
	 * 城市id
	 */
	private Integer cityId;
	/**
	 * 城市
	 */
	private String cityName;
	/**
	 * 地区id
	 */
	private Integer areaId;
	/**
	 * 地区
	 */
	private String areaName;
	/**
	 * 街道
	 */
	private String streetName;
	/**
	 * 最近登录时间
	 */
	private String loginDate;
	/**
	 * 上次登录时间
	 */
	private String lastLoginDate;
	/**
	 * 登录次数
	 */
	private Long loginTime;
}
