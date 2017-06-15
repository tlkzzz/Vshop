package com.Vshop.core.entity.base;

import java.util.Date;

import lombok.Data;

/**
 * 品牌
 * Created by ss on 2014/10/10.
 */
@Data
public class Wxgoods extends BaseEntity{

    /**
     * 索引
     */
	private String url;
	 private String name;
  
    private String spbh;
    private String je;
    private String state;
    private String id;
    private String tgfmc;
    private String fszmc;
    private String sjzfc;
    private String hbzfy;
    /**
     * 品牌名称
     */
   

    /**
     * 类别名称
     */
    private String xsqy;

    /**
     * 图片
     */
    private String scs;

    /**
     * 排序
     */
    private String  smr;

    /**
     *推荐，0为否，1为是，默认为0
     */
    private String smsj;

    /**
     * 店铺ID
     */
   

    /**
     * 品牌申请，0为申请中，1为通过，默认为1，申请功能是会员使用，系统后台默认为1
     */
    private int nb;

    /**
     * 所属分类id
     */
    private String scsj;

    /**
     * 0:未删除;1.已删除
     */
 
    /**
     * 创建时间
     */
    private String hdmc;
    /**
     * 更新时间
     */
    private String bz;

    private String cjsj;
    private int sj;

}
