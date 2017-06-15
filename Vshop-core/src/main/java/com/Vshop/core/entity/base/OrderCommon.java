package com.Vshop.core.entity.base;

import java.util.Date;

import com.Vshop.core.entity.base.Express;

import lombok.Data;
import lombok.ToString;
// default package

/**
 * ShopOrderCommon entity. @author MyEclipse Persistence Tools
 */
@Data
@ToString
public class OrderCommon{

	// Fields

	private Integer orderId;
	private Integer storeId;
	private Date shippingTime;
	private Integer shippingExpressId;
	private Date evaluationTime;
	private String evalsellerState;
	private Date evalsellerTime;
	private String orderMessage;
	private Integer orderPointscount;
	private Integer voucherPrice;
	private String voucherCode;
	private String deliverExplain;
	private Integer daddressId;
	private String reciverName;
	private String reciverInfo;
	private Integer reciverProvinceId;
	private String invoiceInfo;
	private String promotionInfo;

	private Express express;
	
	private String shippingCode;
	private String goodsId;

}