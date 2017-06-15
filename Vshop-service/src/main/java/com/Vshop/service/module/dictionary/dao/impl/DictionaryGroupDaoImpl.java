package com.Vshop.service.module.dictionary.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.DictionaryGroup;
import com.Vshop.service.module.dictionary.dao.DictionaryGroupDao;
import com.Vshop.service.module.dictionary.dao.mapper.DictionaryGroupMapper;
import com.Vshop.service.utils.page.Pager;

@Repository
public class DictionaryGroupDaoImpl implements DictionaryGroupDao{

	@Resource 
	private DictionaryGroupMapper dictionaryGroupMapper;
	
	@Override
	public void save(DictionaryGroup dictionaryGroup) {
		dictionaryGroupMapper.save(dictionaryGroup);
	}

	@Override
	public void update(DictionaryGroup dictionaryGroup) {
		dictionaryGroupMapper.update(dictionaryGroup);
	}

	@Override
	public void delete(Integer groupId) {
		dictionaryGroupMapper.delete(groupId);
	}

	@Override
	public DictionaryGroup findByGroupId(Integer groupId) {
		return dictionaryGroupMapper.findByGroupId(groupId);
	}

	@Override
	public int countGroupidList(DictionaryGroup dictionaryGroup) {
		return dictionaryGroupMapper.countGroupidList(dictionaryGroup);
	}

	@Override
	public List<DictionaryGroup> queryGroupidList(Pager pager) {
		return dictionaryGroupMapper.queryGroupidList(pager);
	}
	
	public DictionaryGroup selectGroupByGroupCode(String groupCode){
		return dictionaryGroupMapper.selectGroupByGroupCode(groupCode);
	}

	
}
