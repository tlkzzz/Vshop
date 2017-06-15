package com.Vshop.service.module.doc.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.DocEnRefEntity;
import com.Vshop.service.module.base.BaseDao;
import com.Vshop.service.module.doc.dao.DocEntityRefDao;
import com.Vshop.service.module.doc.dao.mapper.DocEntityRefMapper;
import com.Vshop.service.utils.page.Pager;

/**
 *    
 * 项目名称：Vshop-admin   
 * 类名称：DocEntityRefDaoImpl
 * 类描述：文档实体关联管理
 * 创建人：lkang   
 * 创建时间：2015年5月05日 02:00:00   
 */
@Service("docEntityRefDao")
public class DocEntityRefDaoImpl extends BaseDao implements DocEntityRefDao {

	@Autowired
	private DocEntityRefMapper docEntityRefMapper;
	/**
	 * 获取关联总数
	 * @param pager
	 * @return
	 */
	public int getRefTotal(DocEnRefEntity ref) {
		return docEntityRefMapper.getRefTotal(ref);
	}

	/**
	 * 获取关联的分页列表
	 * @param pager
	 * @return
	 */
	public List<Map<String, String>> getRefList(Pager pager) {
		return docEntityRefMapper.getRefList(pager);
	}

	/**
	 * 删除关联的数据
	 * @param id
	 */
	public void delete(int id) {
		docEntityRefMapper.delete(id);
	}

	/**
	 * 保存关联数据
	 * @param ref
	 */
	public void save(DocEnRefEntity ref) {
		docEntityRefMapper.save(ref);
	}

	/**
	 * 根据文档id获取关联的所有列表
	 * @param docid
	 * @return
	 */
	public List<Map<String, String>> getAllRefList(int docid) {
		return docEntityRefMapper.getAllRefList(docid);
	}
	
}
