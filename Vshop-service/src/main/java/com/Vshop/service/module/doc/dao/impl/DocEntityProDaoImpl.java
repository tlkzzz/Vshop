package com.Vshop.service.module.doc.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.DocEnProEntity;
import com.Vshop.service.module.base.BaseDao;
import com.Vshop.service.module.doc.dao.DocEntityProDao;
import com.Vshop.service.module.doc.dao.mapper.DocEntityProMapper;
import com.Vshop.service.utils.page.Pager;

/**
 *    
 * 项目名称：Vshop-admin   
 * 类名称：DocEntityProDaoImpl   
 * 类描述：文档实体属性管理
 * 创建人：lkang   
 * 创建时间：2015年5月04日 02:00:00   
 */
@Service("docEntityProDao")
public class DocEntityProDaoImpl extends BaseDao implements DocEntityProDao {

	@Autowired
	private DocEntityProMapper docEntityProMapper;
	/**
	 * 获取属性的总数
	 * @param pager
	 * @return
	 */
	public int getProTotal(DocEnProEntity pro) {
		return docEntityProMapper.getProTotal(pro);
	}

	/**
	 * 获取属性的分页数据
	 * @param pager
	 * @return
	 */
	public List<DocEnProEntity> getProList(Pager pager) {
		return docEntityProMapper.getProList(pager);
	}

	/**
	 * 根据id获取属性
	 * @param id
	 * @return
	 */
	public DocEnProEntity getProById(int id) {
		return docEntityProMapper.getProById(id);
	}

	/**
	 * 保存属性
	 * @param pro
	 */
	public void save(DocEnProEntity pro) {
		docEntityProMapper.save(pro);
	}

	/**
	 * 修改属性
	 * @param pro
	 */
	public void update(DocEnProEntity pro) {
		docEntityProMapper.update(pro);
	}

	/**
	 * 删除属性
	 * @param pro
	 */
	public void delete(int id) {
		docEntityProMapper.delete(id);
	}

	/**
	 * 根据实体id获取参数列表
	 * @param entityid
	 * @return
	 */
	public List<DocEnProEntity> getAllProList(int entityid) {
		return docEntityProMapper.getAllProList(entityid);
	}
	
	

}
