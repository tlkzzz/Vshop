package com.Vshop.service.module.goods.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Vshop.core.entity.base.SpecValue;
import com.Vshop.service.module.goods.dao.SpecValueDao;
import com.Vshop.service.module.goods.dao.mapper.SpecValueMapper;
import com.Vshop.service.utils.page.Pager;

@Component
public class SpecValueDaoImpl implements SpecValueDao{

	@Autowired
	SpecValueMapper goodsSpecValueMapper;
	
	@Override
	public void save(SpecValue specValue) {
		goodsSpecValueMapper.save(specValue);
	}

	@Override
	public void update(SpecValue specValue) {
		goodsSpecValueMapper.update(specValue);
	}

	@Override
	public void deleteBySpId(Integer id) {
		goodsSpecValueMapper.deleteBySpId(id);
	}

	@Override
	public SpecValue findById(Integer id) {
		return goodsSpecValueMapper.findById(id);
	}

	@Override
	public List<SpecValue> findListBySpId(Integer spId) {
		return goodsSpecValueMapper.findListBySpId(spId);
	}

	@Override
	public int findCount(Pager pager) {
		return goodsSpecValueMapper.findCount(pager);
	}

	@Override
	public List<SpecValue> findPageList(Pager pager) {
		return goodsSpecValueMapper.findPageList(pager);
	}

}
