package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;
// default package

/**
 * ShopOrderBill entity. @author MyEclipse Persistence Tools
 */
@Data
@ToString
public class OrderBill{

	// Fields

	private Integer obNo;
	private Integer obStartDate;
	private Integer obEndDate;
	private Double obOrderTotals;
	private Double obShippingTotals;
	private Double obOrderReturnTotals;
	private Double obCommisTotals;
	private Double obCommisReturnTotals;
	private Double obStoreCostTotals;
	private Double obResultTotals;
	private Integer obCreateDate;
	private Integer osMonth;
	private String obState;
	private Integer obPayDate;
	private String obPayContent;
	private Integer obStoreId;
	private String obStoreName;

	

}