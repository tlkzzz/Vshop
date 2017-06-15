package com.Vshop.service.module.report.service;

import java.util.List;

import com.Vshop.core.entity.base.OrderReport;

public interface OrderReportService {
	
	
	/**
	 * 订单报表
	 * @param orderReport
	 * @return
	 */
	List<OrderReport> getOrderReport(OrderReport orderReport);
}
