package com.Vshop.service.module.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.ConsumerCodeLog;
import com.Vshop.core.entity.base.ConsumerCodeSend;
import com.Vshop.service.module.goods.dao.ConsumerCodeLogDao;
import com.Vshop.service.module.goods.service.ConsumerCodeLogService;

@Service
public class ConsumerCodeLogServiceImpl implements ConsumerCodeLogService {

	@Autowired
	ConsumerCodeLogDao consumerCodeLogDao;
	
	@Override
	public void save(ConsumerCodeLog consumerCodeLog) {
		// TODO Auto-generated method stub
		consumerCodeLogDao.save(consumerCodeLog);
	}

	public void save(ConsumerCodeSend consumerCodeSend, String LogResult) {
		// TODO Auto-generated method stub
		ConsumerCodeLog consumerCodeLog = new ConsumerCodeLog();
		consumerCodeLog.setConsumerCodeBunch(consumerCodeSend.getConsumerCodeBunch());
		consumerCodeLog.setCodeStatus(consumerCodeSend.getCodeStatus());
		consumerCodeLog.setLogTime(System.currentTimeMillis());
		// consumerCodeLog.setLogFlag(1);
		// consumerCodeLog.setLogIp("");
		consumerCodeLog.setLogUser(consumerCodeSend.getSendUser());
		consumerCodeLog.setLogResult(LogResult);

		consumerCodeLogDao.save(consumerCodeLog);
	}
	
}
