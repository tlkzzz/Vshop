package com.Vshop.service.module.doc.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.DocEntity;
import com.Vshop.service.module.base.BaseDao;
import com.Vshop.service.module.doc.dao.DocDao;
import com.Vshop.service.module.doc.dao.mapper.DocMapper;
import com.Vshop.service.utils.page.Pager;

/**
 *    
 * 项目名称：Vshop-admin   
 * 类名称：DocDaoImpl
 * 类描述：DAO 实现类
 * 创建人：lkang   
 * 创建时间：2015年4月22日 10:52:19   
 */
@Service("docDao")
public class DocDaoImpl extends BaseDao implements DocDao {

	@Autowired
	private DocMapper docMapper;
	/**
	 * 获取文档总数
	 * @param pager
	 * @return
	 */
	@Override
	public int getDocCount(DocEntity docEntity) {
		return docMapper.getDocCount(docEntity);
	}

	/**
	 * 获取文档分页列表
	 * @param pager
	 * @return
	 */
	@Override
	public List<DocEntity> getDocList(Pager pager) {
		return docMapper.getDocList(pager);
	}

	/**
	 * 根据id获取文档详细
	 * @param id
	 * @return
	 */
	@Override
	public DocEntity getDocById(int id) {
		return docMapper.getDocById(id);
	}

	/**
	 * 添加文档数据
	 * @param doc
	 */
	@Override
	public void save(DocEntity doc) {
		docMapper.save(doc);
	}

	/**
	 * 更新文档数据
	 * @param doc
	 */
	@Override
	public void update(DocEntity doc) {
		docMapper.update(doc);
	}

	/**
	 * 删除文档数据
	 * @param doc
	 */
	@Override
	public void delete(int id) {
		docMapper.delete(id);
	}

	/**
	 * 获取所有的文档
	 * @return
	 */
	public List<DocEntity> getAllDocList(DocEntity doc){
		return docMapper.getAllDocList(doc);
	}
}
