package com.Vshop.core.entity.base;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;


/**
 * @author llf
 * @Package com.Vshop.core.entity.website
 * @Description:
 * @date 2014/11/11 13:00
 */

@Data
@ToString
public class Navigation extends BaseEntity implements Serializable{

    private static final long serialVersionUID = -8914255485895169181L;

    /**
     * 索引ID
     */
    private Integer navId;

    /**
     * 类别，0自定义导航，1商品分类，2文章导航，3活动导航，默认为0
     */
    private Integer navType;

    /**
     * 导航标题
     */
    private String navTitle;

    /**
     * 导航链接
     */
    private String navUrl;

    /**
     * 导航位置，0头部，1中部，2底部，默认为0
     */
    private Integer navLocation;

    /**
     * 是否以新窗口打开，0为否，1为是，默认为0
     */
    private Integer navNewOpen;

    /**
     * 排序
     */
    private Integer navSort;

    /**
     * 类别ID，对应着nav_type中的内容，默认为0
     */
    private Integer navItemId;

    /**
     * 0:未删除;1.已删除
     */
    private int isDel;
    /**
     * 创建时间
     */
    //private Long createdTime;
    /**
     * 更新时间
     */
    //private Long updatedTime;

    private Integer gcId;

    private Integer acId;
}
