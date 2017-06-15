package com.Vshop.service.module.report.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.OrderReport;
import com.Vshop.core.orm.mybatis.SqlMapper;

@SqlMapper
public interface OrderReportMapper {
	
	/**
	 * 订单报表
	 * @param orderReport
	 * @return
	 */
	List<OrderReport> getOrderReport(OrderReport orderReport);
}
