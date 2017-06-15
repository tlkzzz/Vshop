package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


/**
 * Created by ss on 2014/10/15.
 */
@Data
@ToString
public class TypeSpec implements Serializable{

    private static final long serialVersionUID = -3817048730654392423L;

    /**
     * 类型id
     */
    private Integer typeId;

    /**
     * 规格id
     */
    private Integer spId;

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
    private Long updatedTime;*/
}
