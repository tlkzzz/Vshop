package com.Vshop.service.module.report.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.BalanceReport;
import com.Vshop.core.orm.mybatis.SqlMapper;

@SqlMapper
public interface BalanceReportMapper {
	
	/**
	 * 订单报表
	 * @param orderReport
	 * @return
	 */
	List<BalanceReport> getHaveBalanced(BalanceReport balanceReport);
}
