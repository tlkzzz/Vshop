package com.Vshop.union.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.common.DateUtils;

/**
 * 联盟商品
 * 
 * @author liuzhen
 * @version 2015-9-23
 */
@Data
@ToString
public class UnionGoods implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private int classId;// 商品分类id
	private String className;//分类名称
	private String classIds;//商品分类ids
	private String classNames;//所有分类名称
	private String goodsName; // 商品名称
	private String imageUrl;// 商品图片
	private int skuId;// skuId
	private double unitPrice;// double(10,1)商品单价即京东价（单价为-1表示未查询到改商品单价）
	private double commisionRatioPc;// double(3,2)PC佣金比例
	private double commisionRatioWl;// double(3,2)无线佣金比例
	private int shopId;// 店铺ID
	private String materialUrl;// 商品落地页
	private Long startDate;// 推广开始日期
	private Long endDate;// 推广结束日期
	private int source;// 商品来源（例如：京东、淘宝）目前值只有"1"代表京东

	private String showFlag;// 是否显示（0：不显示；1：显示）
	private int sort;// 排序

	private String remarks; // 备注
	private int createBy; // 创建者
	private Long createDate; // 创建日期
	private int updateBy; // 更新者
	private Long updateDate; // 更新日期
	private int delFlag; // 删除标记（0：正常；1：删除；2：审核）

	/* 转换后后日期 */
	private Timestamp startDateStr;
	private Timestamp endDateStr;
	private Timestamp createDateStr;
	private Timestamp updateDateStr;

	/* 查询条件 */
	private int minCommisionRatio;// 最小佣金率
	private int maxCommisionRatio;// 最大佣金率
	private String type;// 商品符合的类型，类型分别为“满就送” “精选特卖” “热销商品”

	// 1 满就送：为京东无线佣金比例大于等于50%的推广商品。--与下面满就送按钮功能一致。
	// 2 精选特卖：为京东无线佣金比例大于等于20%小于50%的推广商品。--与下面精选按钮功能一致。
	// 3 热销商品：为京东无线佣金比例小于20%的推广商品。

	public void setCommisionRatioWl(double commisionRatioWl) {
		this.commisionRatioWl = commisionRatioWl;

		if (commisionRatioWl > 49) {
			type = "满就送";
		}
		if (commisionRatioWl > 19 && commisionRatioWl < 50) {
			type = "精选特卖";
		}
		if (commisionRatioWl < 20) {
			type = "热销";
		}
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
		if (null != startDate) {
			startDateStr = DateUtils.getTimestampByLong(startDate);
		} else {
			this.startDateStr = null;
		}
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
		if (null != endDate) {
			endDateStr = DateUtils.getTimestampByLong(endDate);
		} else {
			this.endDateStr = null;
		}
	}

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
