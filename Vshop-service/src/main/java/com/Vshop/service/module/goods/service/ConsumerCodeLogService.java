package com.Vshop.service.module.goods.service;

import com.Vshop.core.entity.base.ConsumerCodeLog;
import com.Vshop.core.entity.base.ConsumerCodeSend;

public interface ConsumerCodeLogService {

	void save(ConsumerCodeLog consumerCodeLog);
	
	public void save(ConsumerCodeSend consumerCodeSend, String LogResult);
}
