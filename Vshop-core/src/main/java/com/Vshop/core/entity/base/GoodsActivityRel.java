package com.Vshop.core.entity.base;

import java.sql.Timestamp;

import com.Vshop.core.common.DateUtils;

import lombok.Data;
import lombok.ToString;

/**
 * 商品活动关联关系
 */
@Data
@ToString
public class GoodsActivityRel {

	private Integer relId; // int(11)主键
	private Integer activityId; // int(11)活动Id
	private String activityName; // 活动名称
	private Integer activityType; // int(11)活动类型Id
	private String activityTypeName; // 活动类型名称
	private Integer goodsId; // int(11)商品Id
	private String goodsName; // int(11)商品Id
	private Integer storeId; // int(10)商店Id
	private String storeName; // int(10)商店Id
	private Long createTime; // bigint(13)标记活动时间
	private Timestamp createTimeStr;
	/**
	 *关联的商品
	 */
	private Goods goods;
	
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
