package com.Vshop.service.module.doc.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.DocParamEntity;
import com.Vshop.service.module.base.BaseDao;
import com.Vshop.service.module.doc.dao.DocParamDao;
import com.Vshop.service.module.doc.dao.mapper.DocParamMapper;
import com.Vshop.service.utils.page.Pager;

/**
 *    
 * 项目名称：Vshop-admin   
 * 类名称：DocParamDaoImpl   
 * 类描述：API文档参数管理
 * 创建人：lkang   
 * 创建时间：2015年5月07日 02:00:00   
 */
@Service("docParamDaoImpl")
public class DocParamDaoImpl extends BaseDao implements DocParamDao {

	@Autowired
	private DocParamMapper docParamMapper;
	
	/**
	 * 获取参数的总条数
	 * @param pager
	 * @return
	 */
	public int getParamTotal(DocParamEntity param) {
		return docParamMapper.getParamTotal(param);
	}

	/**
	 * 获取参数的分页数据
	 * @param pager
	 * @return
	 */
	public List<DocParamEntity> getParamList(Pager pager) {
		return docParamMapper.getParamList(pager);
	}

	/**
	 * 保存参数
	 * @param param
	 */
	public void save(DocParamEntity param) {
		docParamMapper.save(param);
	}

	/**
	 * 更新参数
	 * @param param
	 */
	public void update(DocParamEntity param) {
		docParamMapper.update(param);
	}

	/**
	 * 根据id删除参数
	 * @param id
	 */
	public void delete(int id) {
		docParamMapper.delete(id);
	}

	/**
	 * 根据id获取参数
	 * @param id
	 * @return
	 */
	public DocParamEntity getParamById(int id) {
		return docParamMapper.getParamById(id);
	}

	/**
	 * 根据docid获取所有参数
	 * @param docid
	 * @return
	 */
	public List<DocParamEntity> getAllParamList(int docid) {
		return docParamMapper.getAllParamList(docid);
	}

}
