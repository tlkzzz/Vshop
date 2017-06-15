package com.Vshop.core.entity;

import java.util.List;

import com.Vshop.core.entity.base.BaseEntity;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.base.BaseEntity;

/**
 * store class 店铺分类表
 *    
 * 项目名称：Vshop-entity   
 * 类名称：Classs   
 * 类描述：   
 * 修改备注：   
 * @version    
 *
 */
@Data
@ToString
public class Type extends BaseEntity{

	/**
	 * 主键ID
	 */
	private Integer id;

	/**
	 * 分类名称
	 */
	private String name;
	
	/**
	 * 父ID
	 */
	private Integer parentId;
	
	/**
	 * 排序
	 */
	private int sort;
	
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
	
	private int hasChild;

    /**
     * 深度
     */
    private int deep;

    private List<Type> classList;
}
