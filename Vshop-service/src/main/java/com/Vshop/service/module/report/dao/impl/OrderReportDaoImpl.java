package com.Vshop.service.module.report.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Vshop.core.entity.base.OrderReport;
import com.Vshop.service.module.report.dao.OrderReportDao;
import com.Vshop.service.module.report.dao.mapper.OrderReportMapper;

@Component
public class OrderReportDaoImpl implements OrderReportDao{

	@Autowired
	private OrderReportMapper orderReportMapper;

	@Override
	public List<OrderReport> getOrderReport(OrderReport orderReport) {
		// TODO Auto-generated method stub
		return orderReportMapper.getOrderReport(orderReport);
	}

}
