package com.Vshop.service.module.report.dao;

import java.util.List;

import com.Vshop.core.entity.base.BalanceReport;

public interface BalanceReportDao {
	
	/**
	 * 订单报表
	 * @param orderReport
	 * @return
	 */
	List<BalanceReport> getHaveBalanced(BalanceReport balanceReport);
}
