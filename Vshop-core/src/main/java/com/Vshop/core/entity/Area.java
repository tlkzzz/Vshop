package com.Vshop.core.entity;

import java.util.List;

import lombok.Data;
import lombok.ToString;

import com.google.common.collect.Lists;

/**
 * shop_area 地区表
 * 
 * @项目名称：Vshop-entity
 * @类名称：Area
 * @类描述：
 * @修改备注：
 * @version
 * 
 */
@Data
@ToString
public class Area {
	/**
	 * 主键ID
	 */
	private Integer areaId;
	/**
	 * 地区名称
	 */
	private String areaName;
	/**
	 * 地区父ID
	 */
	private Integer areaParentId;
	/**
	 * 排序
	 */
	private Integer areaSort;
	/**
	 * 地区深度，从1开始
	 */
	private Integer areaDeep;
	/**
	 * 当前地区的下级地区集合
	 */
	private List<Area> childern = Lists.newArrayList();

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

    /**
     * 是否全选
     */
    private Boolean isChecked;

    private int countChildren;
}
