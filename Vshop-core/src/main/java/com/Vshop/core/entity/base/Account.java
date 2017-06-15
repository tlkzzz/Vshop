package com.Vshop.core.entity.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

/**
 * 会员帐户
 * Created by yansheng on 2014/7/3.
 */
@Data
@ToString
public class Account implements Serializable {
    private static final long serialVersionUID = -8426339414555997935L;
    /**
     * 激活状态
     */
    public static final int STATUS_ACTIVITY = 1;
    /**
     * 锁定状态
     */
    public static final int STATUS_DISABLE = 0;
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
    private BigDecimal amount;
    /**
     * 预存款冻结金额
     */
    private BigDecimal freezeAmount;
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
     * 登录次数
     */
    private Long loginTime;
    /**
     * 最近登录时间
     */
    private Date loginDate;
    /**
     * 上次登录时间
     */
    private Date lastLoginDate;
    /**
     * 0:停止;1.激活
     */
    private int status;
    /**
     * 0:未删除;1.已删除
     */
    private int isDel;
    /**
     * 创建时间
     */
    private Long createdTime;
    /**
     * 更新时间
     */
    private Long updatedTime;
    
    private String oldpassword;
}
