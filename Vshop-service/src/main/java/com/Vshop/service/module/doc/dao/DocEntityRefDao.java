package com.Vshop.service.module.doc.dao;

import java.util.List;
import java.util.Map;

import com.Vshop.core.entity.base.DocEnRefEntity;
import com.Vshop.service.utils.page.Pager;

/**
 *    
 * 项目名称：Vshop-admin   
 * 类名称：DocEntityRefDao   
 * 类描述：文档实体关联管理
 * 创建人：lkang   
 * 创建时间：2015年5月05日 02:00:00   
 */
public interface DocEntityRefDao {

	/**
	 * 获取关联总数
	 * @param pager
	 * @return
	 */
	int getRefTotal(DocEnRefEntity ref);
	
	/**
	 * 获取关联的分页列表
	 * @param pager
	 * @return
	 */
	List<Map<String, String>> getRefList(Pager pager);
	
	/**
	 * 删除关联的数据
	 * @param id
	 */
	void delete(int id);
	
	/**
	 * 保存关联数据
	 * @param ref
	 */
	void save(DocEnRefEntity ref);
	
	/**
	 * 根据文档id获取关联的所有列表
	 * @param pager
	 * @return
	 */
	List<Map<String, String>> getAllRefList(int docid);
}
