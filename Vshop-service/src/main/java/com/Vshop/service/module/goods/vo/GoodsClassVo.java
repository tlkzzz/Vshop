package com.Vshop.service.module.goods.vo;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GoodsClassVo {
	/**
     *主键
     */
    private Integer gcId;

    /**
     *分类名称
     */
    private String gcName;

    /**
     *父ID
     */
    private Integer gcParentId;

    /**
     *名称
     */
    private String gcTitle;

    /**
     * 是否在首页显示
     */
    //private Integer isNavi;

    /**
     * 分类状态
     */
    //private Integer gcStatus;

    /**
     * 排序
     */
    private Integer gcSort;

    /**
     *是否有子类
     */
    //private int hasChild;

    /**
     * 深度
     */
    //private int deep;

    /**
     * 类型名称
     */
    private String typename;

    private List<GoodsClassVo> classList;
}
