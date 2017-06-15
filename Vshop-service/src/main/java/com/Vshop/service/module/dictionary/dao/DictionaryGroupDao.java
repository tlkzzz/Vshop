package com.Vshop.service.module.dictionary.dao;

import java.util.List;

import com.Vshop.core.entity.base.DictionaryGroup;
import com.Vshop.service.utils.page.Pager;

public interface DictionaryGroupDao {

	void save(DictionaryGroup dictionaryGroup);
	void update(DictionaryGroup dictionaryGroup);
	void delete(Integer groupId);
	/**
	 * 根据字典组id查询字典组实体
	 * @param groupId
	 * @return
	 */
	DictionaryGroup findByGroupId(Integer groupId);
	/**
	 * 总数查询
	 * @param pager
	 * @return
	 */
	public int countGroupidList(DictionaryGroup dictionaryGroup);
	/**
	 * 分页列表
	 * @param pager
	 * @return
	 */
	public List<DictionaryGroup> queryGroupidList(Pager pager);
	
	public DictionaryGroup selectGroupByGroupCode(String groupCode);
}
