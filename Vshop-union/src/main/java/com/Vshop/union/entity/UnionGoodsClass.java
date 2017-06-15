package com.Vshop.union.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.common.DateUtils;

/**
 * 联盟商品分类
 * 
 * @author liuzhen
 * @version 2015-9-21
 */
@Data
@ToString
public class UnionGoodsClass implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String name; // 名称
	private String names;// 所有分类名称
	private String icon;// 图标
	private String showFlag;// 是否显示（0：不显示；1：显示）
	private int sort;// 排序
	private int pid;// 父id
	private String idpaths;
	private int hasChild;// 子数据条数
	private int levels;// 层级

	private List<UnionGoodsClass> classList;

	private String remarks; // 备注
	private int createBy; // 创建者
	private Long createDate; // 创建日期
	private int updateBy; // 更新者
	private Long updateDate; // 更新日期
	private int delFlag; // 删除标记（0：正常；1：删除；2：审核）

	/* 转换后后日期 */
	private Timestamp createDateStr;
	private Timestamp updateDateStr;

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
		if (null != createDate) {
			createDateStr = DateUtils.getTimestampByLong(createDate);
		} else {
			this.createDateStr = null;
		}
	}

	public void setUpdateDate(Long updateDate) {
		this.updateDate = updateDate;
		if (null != updateDate) {
			updateDateStr = DateUtils.getTimestampByLong(updateDate);
		} else {
			this.updateDateStr = null;
		}
	}

}
