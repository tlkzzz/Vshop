package com.Vshop.service.module.stat.service.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.Member;
import com.Vshop.service.module.stat.dao.mapper.StatMemberMapper;
import com.Vshop.service.module.stat.service.StatMemberService;

/**
 *    
 * 项目名称：Vshop-admin   
 * 类名称：AdminLogServiceImpl   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年11月5日 下午10:43:18   
 * 修改人：liuhao   
 * 修改时间：2014年11月5日 下午10:43:18   
 * 修改备注：   
 * @version    
 *
 */
@Service("statmemberService")
@Slf4j
public class StatMemberServiceImpl implements StatMemberService {

	@Autowired
    private StatMemberMapper statmemberMapper;

	
	public List<Member> queryStatMemberList(Member member) {
		log.info("-----------");
		return statmemberMapper.queryStatMemberList(member);
	}

	
	public List<Member> queryStatMemberMonthList(Member member) {
		return statmemberMapper.queryStatMemberMonthList(member);
	}

	
	
}

