package com.Vshop.service.module.dictionary.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Dictionary;
import com.Vshop.service.module.dictionary.dao.DictionaryDao;
import com.Vshop.service.module.dictionary.dao.mapper.DictionaryMapper;
import com.Vshop.service.utils.page.Pager;

@Repository
public class DictionaryDaoImpl implements DictionaryDao{

	@Resource 
	private DictionaryMapper dictionaryMapper;
	
	@Override
	public void save(Dictionary dictionary) {
		dictionaryMapper.save(dictionary);
	}

	@Override
	public void update(Dictionary dictionary) {
		dictionaryMapper.update(dictionary);
	}

	@Override
	public void delete(Integer dictionaryId) {
		dictionaryMapper.delete(dictionaryId);
	}

	@Override
	public Dictionary findByDictionaryId(Integer dictionaryId) {
		return dictionaryMapper.findByDictionaryId(dictionaryId);
	}

	@Override
	public int countDictionaryidList(Dictionary dictionary) {
		return dictionaryMapper.countDictionaryidList(dictionary);
	}

	@Override
	public List<Dictionary> queryDictionaryidList(Pager pager) {
		return dictionaryMapper.queryDictionaryidList(pager);
	}

	@Override
	public List<Dictionary> findDictionaryByCode(String groupCode) {
		return dictionaryMapper.findDictionaryByCode(groupCode);
	}

	@Override
	public Dictionary findDictionaryByDictionaryId(Integer dictionaryId) {
		return dictionaryMapper.findDictionaryByDictionaryId(dictionaryId);
	}
	
	public void updateAllDictionaryCodeByGroupId(Dictionary dictionary){
		dictionaryMapper.updateAllDictionaryCodeByGroupId(dictionary);
	}
}
