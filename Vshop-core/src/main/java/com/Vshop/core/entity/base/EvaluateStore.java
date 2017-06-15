package com.Vshop.core.entity.base;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * @author llf
 * @Package com.Vshop.core.entity.trade
 * @Description:
 * @date 2014/11/12 17:32
 */

@Data
@ToString
public class EvaluateStore extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 2021203832667967634L;

    /**
     * 评价ID
     */
    private Integer sevalId;

    /**
     *订单ID
     */
    private Integer sevalOrderId;

    /**
     *订单编号
     */
    private String sevalOrderNo;

    /**
     *评价时间
     */
    private Long sevalAddTime;

    /**
     *店铺编号
     */
    private Integer sevalStoreId;

    /**
     *店铺名称
     */
    private String  sevalStoreName;

    /**
     *买家编号
     */
    private Integer sevalMemberId;

    /**
     *买家名称
     */
    private String sevalMemberName;

    /**
     *描述相符评分
     */
    private Double sevalDesccredit;

    /**
     *服务态度评分
     */
    private Double sevalServicecredit;

    /**
     *发货速度评分
     */
    private Double sevalDeliverycredit;

    /**
     * 0:未删除;1.已删除
     */
    private int isDel;
//    /**
//     * 创建时间
//     */
//    private Long createdTime;
//    /**
//     * 更新时间
//     */
//    private Long updatedTime;

//    /**
//     * 页面开始时间
//     */
//    private String startTime;
//
//    /**
//     * 页面结束时间
//     */
//    private String endTime;
    /**
     * 评分数量
     */
    private String count;
    /**
     * 店铺信用评分
     */
    private String averageCredit;
}
