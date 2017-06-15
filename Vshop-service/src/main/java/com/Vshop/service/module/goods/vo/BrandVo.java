package com.Vshop.service.module.goods.vo;

import lombok.Data;
import lombok.ToString;

/**
 * 品牌页查询使用
 * Created by ss on 2014/10/13.
 */
@Data
@ToString
public class BrandVo{

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌类别
     */
    private String brandClass;

    /**
     * 状态
     */
    private Integer brandStatus;

    /**
     * 推荐
     */
    private Integer brandRecommend;

    /**
     * 分页
     */
    private Integer start;
    private Integer limit;
}
