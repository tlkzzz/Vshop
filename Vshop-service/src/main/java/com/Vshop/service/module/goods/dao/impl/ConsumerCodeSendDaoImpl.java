package com.Vshop.service.module.goods.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.ConsumerCodeSend;
import com.Vshop.service.module.goods.dao.ConsumerCodeSendDao;
import com.Vshop.service.module.goods.dao.mapper.ConsumerCodeSendMapper;
import com.Vshop.service.utils.page.Pager;

@Repository
public class ConsumerCodeSendDaoImpl implements ConsumerCodeSendDao {

	@Resource
	private ConsumerCodeSendMapper consumerCodeSendMapper;
	
	@Override
	public Integer save(ConsumerCodeSend consumerCodeSend) {
		// TODO Auto-generated method stub
		return consumerCodeSendMapper.save(consumerCodeSend);
	}

	@Override
	public void updateById(ConsumerCodeSend consumerCodeSend) {
		// TODO Auto-generated method stub
		consumerCodeSendMapper.updateById(consumerCodeSend);
	}

	@Override
	public void updateByCode(ConsumerCodeSend consumerCodeSend) {
		// TODO Auto-generated method stub
		consumerCodeSendMapper.updateByCode(consumerCodeSend);
	}

	@Override
	public ConsumerCodeSend findById(Integer consumerCodeId) {
		// TODO Auto-generated method stub
		return consumerCodeSendMapper.findById(consumerCodeId);
	}

	@Override
	public ConsumerCodeSend findByCode(String consumerCodeBunch) {
		// TODO Auto-generated method stub
		return consumerCodeSendMapper.findByCode(consumerCodeBunch);
	}

	@Override
	public List<ConsumerCodeSend> findPageList(Pager pager) {
		// TODO Auto-generated method stub
		return consumerCodeSendMapper.findPageList(pager);
	}

	@Override
	public Integer countById(Integer consumerCodeId) {
		// TODO Auto-generated method stub
		return consumerCodeSendMapper.countById(consumerCodeId);
	}

	@Override
	public Integer countByCode(String consumerCodeBunch) {
		// TODO Auto-generated method stub
		return consumerCodeSendMapper.countByCode(consumerCodeBunch);
	}

}
