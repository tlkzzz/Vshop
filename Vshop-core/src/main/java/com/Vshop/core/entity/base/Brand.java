package com.Vshop.core.entity.base;

import lombok.Data;

/**
 * 品牌
 * Created by ss on 2014/10/10.
 */
@Data
public class Brand extends BaseEntity{

    /**
     * 索引
     */
    private Integer brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 类别名称
     */
    private String brandClass;

    /**
     * 图片
     */
    private String brandPic;

    /**
     * 排序
     */
    private Integer  brandSort;

    /**
     *推荐，0为否，1为是，默认为0
     */
    private Integer brandRecommend;

    /**
     * 店铺ID
     */
    private long storeId;

    /**
     * 品牌申请，0为申请中，1为通过，默认为1，申请功能是会员使用，系统后台默认为1
     */
    private Integer brandApply;

    /**
     * 所属分类id
     */
    private Long classId;

    /**
     * 0:未删除;1.已删除
     */
    //private int isDel;
    /**
     * 创建时间
     */
    //private Long createTime;
    /**
     * 更新时间
     */
    //private Long updateTime;

   

}
