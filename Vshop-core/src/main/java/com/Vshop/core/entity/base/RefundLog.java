package com.Vshop.core.entity.base;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.base.BaseEntity;

/**
 * 退款表 
 * @author liukai
 */
@Data
@ToString
public class RefundLog extends BaseEntity{
	
	//退款记录ID
	private Integer logId;
	
	//订单ID
	private Integer orderId;
	
	//退款编号
	private String refundSn;
	
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
	
	//订单金额
	private BigDecimal orderAmount;
	
	//退款金额
	private BigDecimal orderRefund;
	
	//支付方式名称
	private String refundPaymentName;
	
	//支付方式代码
	private String refundPaymentCode;
	
	//退款备注
	private String refundMessage;
	
	//退款原因
	private String buyerMessage;
	
	//管理员处理原因
	private String adminMessage;
	
	//卖家处理时间
	private Long sellerTime;
	
	//管理员处理时间
	private Long adminTime;
	
	//买家确认收款时间
	private Long confirmTime;
	
	//类型:1为买家,2为卖家,默认为2
	private Integer refundType;
	
	//状态:1为待处理,2为同意,3为拒绝,默认为2
	private Integer refundState;
	
	//确认收款状态:1为待确认,2为已确认,默认为2
	private Integer buyerConfirm;
	
	//卖家处理时间-页面字段
	private Timestamp sellerTimeStr;
	
	//管理员处理时间-页面字段
	private Timestamp adminTimeStr;
	
	//买家确认收款时间-页面字段
	private Timestamp confirmTimeStr;

	public Long getSellerTime() {
		return sellerTime;
	}

	public void setSellerTime(Long sellerTime) {
		this.sellerTime = sellerTime;
		sellerTimeStr = DateUtils.getTimestampByLong(sellerTime);
		this.sellerTimeStr = sellerTimeStr;
	}

	public Long getAdminTime() {
		return adminTime;
	}

	public void setAdminTime(Long adminTime) {
		this.adminTime = adminTime;
		adminTimeStr = DateUtils.getTimestampByLong(adminTime);
		this.adminTimeStr = adminTimeStr;
	}

	public Long getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Long confirmTime) {
		this.confirmTime = confirmTime;
		confirmTimeStr = DateUtils.getTimestampByLong(confirmTime);
		this.confirmTimeStr = confirmTimeStr;
	}

	public Timestamp getSellerTimeStr() {
		return sellerTimeStr;
	}

	public void setSellerTimeStr(Timestamp sellerTimeStr) {
		sellerTimeStr = DateUtils.getTimestampByLong(sellerTime);
		this.sellerTimeStr = sellerTimeStr;
	}

	public Timestamp getAdminTimeStr() {
		return adminTimeStr;
	}

	public void setAdminTimeStr(Timestamp adminTimeStr) {
		sellerTimeStr = DateUtils.getTimestampByLong(adminTime);
		this.adminTimeStr = adminTimeStr;
	}

	public Timestamp getConfirmTimeStr() {
		return confirmTimeStr;
	}

	public void setConfirmTimeStr(Timestamp confirmTimeStr) {
		sellerTimeStr = DateUtils.getTimestampByLong(confirmTime);
		this.confirmTimeStr = confirmTimeStr;
	}
	
}
