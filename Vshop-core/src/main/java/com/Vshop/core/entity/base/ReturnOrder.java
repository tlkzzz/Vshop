package com.Vshop.core.entity.base;


import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.base.BaseEntity;
import com.Vshop.core.entity.base.ReturnGoods;

/**
 * 退货表
 * @author liukai
 */
@Data
public class ReturnOrder extends BaseEntity{
	
	//退货记录ID
	private Integer returnId;
	
	//订单ID
	private Integer orderId;
	
	//退货编号
	private String returnSn;
	
	//订单编号
	private String orderSn;
	
	//店铺ID
	private Integer storeId;
	
	//店铺ID
	private Integer supplierId;
	
	//店铺名称
	private String storeName;
	
	//买家ID
	private Integer buyerId;
	
	//买家会员名
	private String buyerName;
	
	//退货数量
	private Integer returnGoodsNum;
	
	//退货备注
	private String returnMessage;
	
	//退货原因
	private String buyerMessage;
	
	//类型:1为买家,2为卖家,默认为2
	private Integer returnType;
	
	//状态:1为待审核,2为同意,3为不同意,默认为2
	private Integer returnState;
	
	//卖家处理时间
	private Long sellerTime;
	
	//卖家处理时间-页面字段
	private Timestamp sellerTimeStr;
	
	public Long getSellerTime() {
		return sellerTime;
	}

	public void setSellerTime(Long sellerTime) {
		this.sellerTime = sellerTime;
		sellerTimeStr = DateUtils.getTimestampByLong(sellerTime);
		this.sellerTimeStr = sellerTimeStr;
	}

	public Timestamp getSellerTimeStr() {
		return sellerTimeStr;
	}

	public void setSellerTimeStr(Timestamp sellerTimeStr) {
		sellerTimeStr = DateUtils.getTimestampByLong(sellerTime);
		this.sellerTimeStr = sellerTimeStr;
	}
}
