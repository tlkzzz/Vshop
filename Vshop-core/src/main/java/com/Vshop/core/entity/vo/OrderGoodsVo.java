package com.Vshop.core.entity.vo;



import java.sql.Timestamp;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.base.BaseEntity;

/**
 * 物品订单信息
 * @author liukai
 *
 */
@Data
@ToString
public class OrderGoodsVo extends BaseEntity{

	//商品id
	private Integer goodsId;
	
	//买家id
	private Integer buyerId;
	
	//买家名称
	private String buyerName;
	
	//商品名称
	private String goodsName;
	
	//订单状态
	private Integer orderState;
	
	//订单完成时间
	private Long finnshedTime;
	
	//订单生成时间-页面字段
	private Timestamp createTimeStr;
	
	//订单完成时间-页面字段
	private Timestamp finnshedTimeStr;


	public Long getFinnshedTime() {
		return finnshedTime;
	}

	public void setFinnshedTime(Long finnshedTime) {
		this.finnshedTime = finnshedTime;
		finnshedTimeStr = DateUtils.getTimestampByLong(finnshedTime);
		this.finnshedTimeStr = finnshedTimeStr;
	}

	public Timestamp getFinnshedTimeStr() {
		return finnshedTimeStr;
	}

	public void setFinnshedTimeStr(Timestamp finnshedTimeStr) {
		finnshedTimeStr = DateUtils.getTimestampByLong(finnshedTime);
		this.finnshedTimeStr = finnshedTimeStr;
	}
}
