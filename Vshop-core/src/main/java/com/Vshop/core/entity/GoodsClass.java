package com.Vshop.core.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
public class GoodsClass implements Serializable{

    private static final long serialVersionUID = 6210315253221632717L;
    /**
     *主键
     */
    private Integer gcId;

    /**
     *分类名称
     */
    private String gcName;
    
    /**
     *分类图标
     */
    private String gcpic;

    /**
     *类型ID
     */
    private Integer typeId;

    /**
     *类型名称
     */
    private String typename;

    /**
     *父ID
     */
    private Integer gcParentId;

    /**
     *排序
     */
    private Integer gcSort;
    
    /**
     * 0为否，1为是，默认为1
     */
    private Integer gcshow;

    /**
     *名称
     */
    private String gcTitle;

    /**
     *关键词
     */
    private String gcKeywords;

    /**
     *描述
     */
    private String gcDescription;
    
    /**
     *层级path
     */
    private String gcIdpath;
    
    private int deep;
    
    private int hasChild;

    private List<GoodsClass> classList;
    
    private String searchType = "gcIdSearch";
    
    /**
     * 最后一级所有的第一个分类名称(手机客户端使用)
     */
    private String gcLastChild;
    
}
