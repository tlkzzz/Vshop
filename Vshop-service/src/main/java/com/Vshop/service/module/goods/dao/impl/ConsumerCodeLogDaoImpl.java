package com.Vshop.service.module.goods.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.ConsumerCodeLog;
import com.Vshop.service.module.goods.dao.ConsumerCodeLogDao;
import com.Vshop.service.module.goods.dao.mapper.ConsumerCodeLogMapper;

@Repository
public class ConsumerCodeLogDaoImpl implements ConsumerCodeLogDao {

	@Resource
	private ConsumerCodeLogMapper consumerCodeLogMapper;
	
	@Override
	public void save(ConsumerCodeLog consumerCodeLog) {
		// TODO Auto-generated method stub
		consumerCodeLogMapper.save(consumerCodeLog);
	}

}
