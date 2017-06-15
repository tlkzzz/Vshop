package com.Vshop.service.module.goods.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.ConsumerCode;
import com.Vshop.service.module.goods.dao.ConsumerCodeDao;
import com.Vshop.service.module.goods.dao.mapper.ConsumerCodeMapper;
import com.Vshop.service.utils.page.Pager;

@Repository
public class ConsumerCodeDaoImpl implements ConsumerCodeDao {

	@Resource
	private ConsumerCodeMapper consumerCodeMapper;
	
	@Override
	public void save(ConsumerCode consumerCode) {
		// TODO Auto-generated method stub
		consumerCodeMapper.save(consumerCode);
	}

	@Override
	public void update(ConsumerCode consumerCode) {
		// TODO Auto-generated method stub
		consumerCodeMapper.update(consumerCode);
	}

	@Override
	public List<ConsumerCode> findPageList(Pager pager) {
		// TODO Auto-generated method stub
		return consumerCodeMapper.findPageList(pager);
	}

	@Override
	public Integer findPageListCount(Pager pager) {
		// TODO Auto-generated method stub
		return consumerCodeMapper.findPageListCount(pager);
	}

	@Override
	public ConsumerCode findById(Integer consumerCodeId) {
		// TODO Auto-generated method stub
		return consumerCodeMapper.findById(consumerCodeId);
	}

	@Override
	public void deleteById(Integer consumerCodeId) {
		// TODO Auto-generated method stub
		consumerCodeMapper.deleteById(consumerCodeId);
	}

	@Override
	public List<ConsumerCode> findListByMemberId(Integer memberId) {
		// TODO Auto-generated method stub
		return consumerCodeMapper.findListByMemberId(memberId);
	}

	@Override
	public int findByBunch(String consumerCodeBunch) {
		// TODO Auto-generated method stub
		return consumerCodeMapper.findByBunch(consumerCodeBunch);
	}

}
