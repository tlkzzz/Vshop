package com.Vshop.core.entity.base;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * @author llf
 * @Package com.Vshop.core.entity.operation
 * @Description:
 * @date 2014/11/11 17:07
 */
@Data
@ToString
public class Activity implements Serializable{

    private static final long serialVersionUID = -6618905499399079914L;

    /**
     * id
     */
    private Integer activityId;

    /**
     * 标题
     */
    private String activityTitle;

    /**
     * 活动类型 1:商品 2:团购
     */
    private String activityType;

    /**
     * 活动横幅大图片
     */
    private String activityBanner;

    /**
     * 活动页面模板样式标识码
     */
    private String activityStyle;

    /**
     * 描述
     */
    private String activityDes;

    /**
     * 开始时间
     */
    private Long activityStartDate;

    /**
     * 结束时间
     */
    private Long activityEndDate;

    /**
     * 排序
     */
    private Integer activitySort;

    /**
     * 活动状态 0为关闭 1为开启
     */
    private Integer activityState;

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
}
