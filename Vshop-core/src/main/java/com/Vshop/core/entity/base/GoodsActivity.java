package com.Vshop.core.entity.base;

import java.sql.Timestamp;

import com.Vshop.core.common.DateUtils;

import lombok.Data;

/**
 * 商品活动
 */
@Data
public class GoodsActivity extends BaseEntity {

	private Integer activityId; // int(11)主键
	private String activityName; // varchar(150)活动名称
	private String activityBanner; // varchar(300)活动banner
	private Integer activityUse; // tinyint(1)是否启用,0,停用,1.启用
	private Long createTime; // bigint(13)活动创建时间
	private Long lastUpdateTime; // bigint(13)最后一次更新时间
	private String activityBanner2; // varchar(300)活动banner2

	private Timestamp lastUpdateTimeStr; // bigint(13)最后一次更新时间
	private Timestamp createTimeStr;
	
	public Timestamp getLastUpdateTimeStr() {
		if (null != lastUpdateTime) {
			lastUpdateTimeStr = DateUtils.getTimestampByLong(lastUpdateTime);
		}
		return lastUpdateTimeStr;
	}

	public void setUpdateTimeStr(Timestamp lastUpdateTimeStr) {
		if (null != lastUpdateTime) {
			lastUpdateTimeStr = DateUtils.getTimestampByLong(lastUpdateTime);
		}
		this.lastUpdateTimeStr = lastUpdateTimeStr;
	}
	
	public Timestamp getCreateTimeStr() {
		if (null != createTime) {
			createTimeStr = DateUtils.getTimestampByLong(createTime);
		}
		return createTimeStr;
	}

	public void setCreateTimeStr(Timestamp createTimeStr) {
		if (null != createTime) {
			createTimeStr = DateUtils.getTimestampByLong(createTime);
		}
		this.createTimeStr = createTimeStr;
	}
}
