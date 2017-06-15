package com.Vshop.service.module.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.MemberRegister;
import com.Vshop.service.module.report.dao.MemberReportDao;
import com.Vshop.service.module.report.service.MemberReportService;

@Service
public class MemberReportServiceImpl implements MemberReportService{
	
	@Autowired
	private MemberReportDao memberReportDao;

	@Override
	public List<MemberRegister> getMemberRegister(MemberRegister memberRegister) {
		// TODO Auto-generated method stub
		return memberReportDao.getMemberRegister(memberRegister);
	}

}
