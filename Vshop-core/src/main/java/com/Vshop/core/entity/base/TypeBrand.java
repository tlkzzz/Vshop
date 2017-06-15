package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


/**
 * Created by ss on 2014/10/15.
 */
@Data
@ToString
public class TypeBrand implements  Serializable{

    private static final long serialVersionUID = -7108493082513828798L;

    /**
     * 类型id
     */
    private Integer typeId;

    /**
     * 品牌id
     */
    private Integer brandId;

    /**
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
     * 自定义品牌名称
     *//*
    private String brandName;*/

}
