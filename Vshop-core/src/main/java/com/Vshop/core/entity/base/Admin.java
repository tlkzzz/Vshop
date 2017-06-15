package com.Vshop.core.entity.base;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Admin implements Serializable {

    /**
     *管理员id
     */
    private Integer adminId;

    /**
     * 用户名
     */
    private String adminName;

    /**
     * 密码
     */
    private String adminPassword;

    /**
     * 登陆时间
     */
    private Long adminLoginTime;

    /**
     * 登陆次数
     */
    private Integer adminLoginNum;

    /**
     * 是否是是超级管理员：1
     */
    private Integer adminIsSuper;

    /**
     * 权限组ID
     */
    private Integer adminGid;

    /**
     * 是否删除
     */
    private boolean isDel;
    /**
     * 角色id
     */
    private String roleid;
    /**
     * 供应商ID
     */
    private Integer supplierId;

}
