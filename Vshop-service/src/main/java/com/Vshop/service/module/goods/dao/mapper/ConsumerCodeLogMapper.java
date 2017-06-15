package com.Vshop.service.module.goods.dao.mapper;

import com.Vshop.core.entity.base.ConsumerCodeLog;
import com.Vshop.core.orm.mybatis.SqlMapper;

@SqlMapper
public interface ConsumerCodeLogMapper {

	void save(ConsumerCodeLog consumerCodeLog);
	
}
