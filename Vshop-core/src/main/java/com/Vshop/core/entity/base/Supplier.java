package com.Vshop.core.entity.base;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

/**
 * 供货商实体类
 * @author Administrator
 *
 */
@Data
@ToString
public class Supplier implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 976868387677932680L;
	private Integer id;
	private String name;
	private String shortName;
	private String supplierNO;
	private Integer entType;
	private String address;
	private BigDecimal longitude;
	private BigDecimal latitude;
	private String contacter;
	private String mobile;
	private String fixedTel;
	private String fax;
	private String contacterBak;
	private String mobileBak;
	private Integer busType;
	private Integer marketType;
	private BigDecimal busArea;
	private String managerId;
	private String busLicenseNO;
	private String busLicenPurl;
	private String dishuiRegistNO;
	private String dishuiRegistPurl;
	private String guoshuiRegistNO;
	private String guoshuiRegistPurl;
	private String zzshuiPurl;
	private String legaler;
	private String legalerID;
	private String legalerPurl;
	private Integer shopType;
	private String accountBank;
	private String openingBank;
	private String accountNO;
	private String openingName;
	private Integer accountMethod;
	private Integer accountCycle;
	private Date acountDate;
	private Integer deleted;
	private Date createTime;
	private Date updateTime;
	
	private Integer nameAuth;//店主认证，0-未认证，1-认证
    private Integer memberId;//会员id
    private String memberName;//会员名称
	private Integer supplierState;//店铺状态，0关闭，1开启，2审核中
	
	private Long supplierLogintime;//当前登陆时间
    private Long supplierLastLogintime;//上次登陆时间
    private String supplierLogo;//机构首页LOGO
    private String supplierCloseInfo;//审核不通过原因
	    
    private Timestamp supplierLastLogintimestr;
	
}
