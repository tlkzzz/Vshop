package com.Vshop.service.module.dictionary.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.Dictionary;
import com.Vshop.core.entity.base.DictionaryGroup;
import com.Vshop.service.module.dictionary.dao.DictionaryDao;
import com.Vshop.service.module.dictionary.dao.DictionaryGroupDao;
import com.Vshop.service.module.dictionary.service.DictionaryGroupService;
import com.Vshop.service.utils.page.Pager;

@Service
public class DictionaryGroupServiceImpl implements DictionaryGroupService{
	@Resource
	private DictionaryGroupDao dictionaryGroupdao;
	
	@Resource
	private DictionaryDao dictionaryDao;
	
	
	@Override
	public void save(DictionaryGroup dictionaryGroup) {
		dictionaryGroupdao.save(dictionaryGroup);
	}

	@Override
	public void update(DictionaryGroup dictionaryGroup) {
		dictionaryGroupdao.update(dictionaryGroup);
		Dictionary dictionary = new Dictionary();
		dictionary.setGroupId(dictionaryGroup.getGroupId());
		dictionary.setDictionaryCode(dictionaryGroup.getGroupCode());
		dictionaryDao.updateAllDictionaryCodeByGroupId(dictionary);
	}

	@Override
	public void delete(Integer groupId) {
		dictionaryGroupdao.delete(groupId);
	}

	@Override
	public DictionaryGroup findByGroupId(Integer groupId) {
		return dictionaryGroupdao.findByGroupId(groupId);
	}

	@Override
	public int countGroupidList(DictionaryGroup dictionaryGroup) {
		return dictionaryGroupdao.countGroupidList(dictionaryGroup);
	}

	@Override
	public List<DictionaryGroup> queryGroupidList(Pager pager) {
		return dictionaryGroupdao.queryGroupidList(pager);
	}
	
	public DictionaryGroup selectGroupByGroupCode(String groupCode){
		return dictionaryGroupdao.selectGroupByGroupCode(groupCode);
	}

	
}
