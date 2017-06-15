package com.Vshop.service.module.report.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.MemberRegister;
import com.Vshop.core.orm.mybatis.SqlMapper;

@SqlMapper
public interface MemberReportMapper {
	
	/**
	 * 订单报表
	 * @param orderReport
	 * @return
	 */
	List<MemberRegister> getMemberRegister(MemberRegister memberRegister);
}
