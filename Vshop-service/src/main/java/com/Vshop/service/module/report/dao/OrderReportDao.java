package com.Vshop.service.module.report.dao;

import java.util.List;

import com.Vshop.core.entity.base.OrderReport;

public interface OrderReportDao {
	
	/**
	 * 订单报表
	 * @param orderReport
	 * @return
	 */
	List<OrderReport> getOrderReport(OrderReport orderReport);
}
