package com.Vshop.core.entity.base;

import java.util.Date;
// default package

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.base.BaseEntity;

/**
 * ShopOrderStatis entity. @author MyEclipse Persistence Tools
 */
@Data
@ToString
public class OrderStatis extends BaseEntity{

	// Fields

	private Integer osMonth;
	private Short osYear;
	private Date osStartDate;
	private Date osEndDate;
	private Double osOrderTotals;
	private Double osShippingTotals;
	private Double osOrderReturnTotals;
	private Double osCommisTotals;
	private Double osCommisReturnTotals;
	private Double osStoreCostTotals;
	private Double osResultTotals;
	private Date osCreateDate;

	

}