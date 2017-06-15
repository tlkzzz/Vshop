package com.Vshop.service.module.dictionary.service;

import java.util.List;

import com.Vshop.core.entity.base.Dictionary;
import com.Vshop.service.utils.page.Pager;

public interface DictionaryService {

	void save(Dictionary dictionary);
	void update(Dictionary dictionary);
	void delete(Integer dictionaryId);
	/**
	 * 根据字典id查询字典实体
	 * @param dictionaryId
	 * @return
	 */
	Dictionary findByDictionaryId(Integer dictionaryId);
	
	/**
	 * 总数查询
	 * @param dictionary
	 * @return int
	 */
	public int countDictionaryidList(Dictionary dictionary);
	/**
	 * 分页列表
	 * @param pager
	 * @return
	 */
	public List<Dictionary> queryDictionaryidList(Pager pager);
	
	public List<Dictionary> findDictionaryByCode(String groupCode);
	
	public Dictionary findDictionaryByDictionaryId(Integer dictionaryId);

}
