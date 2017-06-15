package com.Vshop.core.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.base.TypeBrand;
import com.Vshop.core.entity.base.TypeSpec;

/**
 * Created by ss on 2014/10/15.
 */
@Data
@ToString
public class GoodsType implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5529721245302844483L;

	/**
     * 类型id
     */
    private Integer typeId;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 类型排序
     */
    private Integer typeSort;
    
    /**
     * 商品属性List
     */
    private List<GoodsAttribute> attributes;
    
    /**
     * 商品品牌List
     */
    private List<TypeBrand> tbs;
    
    /**
     * 商品规格List
     */
    private List<TypeSpec> tsList;

    /**
     * 所属分类id
     *//*
    private Integer gtClassId;

    *//**
     * 所属分类名称
     *//*
    private String gtClassName;

    *//**
     * 0:未删除;1.已删除
     *//*
    private int isDel;
    *//**
     * 创建时间
     *//*
    private Long createdTime;
    *//**
     * 更新时间
     *//*
    private Long updatedTime;

    *//**
     * 根据classid
     *//*
    private List<GoodsType> typeList;*/
}
