package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员帐户
 * Created by yansheng on 2014/7/3.
 */
@Data
@ToString
public class Manager extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -8436339414555997935L;
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
     * 手机号
     */
    private String cellPhone;
    /**
     * 0:停止;1.激活
     */
    private int status;
    /**
     * 登录次数
     */
    private Long loginTime;
    /**
     * 最近登录时间
     */
    private Date loginDate;
    /**
     * 0:未删除;1.已删除
     */
//    private int isDel;
//    /**
//     * 创建时间
//     */
//    private Long createdTime;
//    /**
//     * 更新时间
//     */
//    private Long updatedTime;
}
