package com.Vshop.service.module.doc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.DocEnEntity;
import com.Vshop.service.module.doc.dao.DocEntityDao;
import com.Vshop.service.module.doc.service.DocEntityService;
import com.Vshop.service.utils.page.Pager;

/**
 *    
 * 项目名称：Vshop-admin   
 * 类名称：DocEntityServiceImpl   
 * 类描述：文档实体管理
 * 创建人：lkang   
 * 创建时间：2015年5月04日 10:52:19   
 */
@Service("docEntityService")
public class DocEntityServiceImpl implements DocEntityService {

	@Autowired
	private DocEntityDao docEntityDao;
	/**
	 * 获取引用实体总数
	 * @param pager
	 * @return
	 */
	public int getDocEntityTotal(DocEnEntity docEntity) {
		return docEntityDao.getDocEntityTotal(docEntity);
	}

	/**
	 * 获取引用实体分页数据
	 * @param pager
	 * @return
	 */
	public List<DocEnEntity> getDocEntityList(Pager pager) {
		return docEntityDao.getDocEntityList(pager);
	}

	/**
	 * 根据id获取引用实体
	 * @param entityId
	 * @return
	 */
	public DocEnEntity getDocEntityById(int entityId) {
		return docEntityDao.getDocEntityById(entityId);
	}

	/**
	 * 保存引用实体
	 * @param docEntity
	 */
	public void saveDocEntity(DocEnEntity docEntity) {
		docEntity.setCreateTime(System.currentTimeMillis());
		docEntityDao.saveDocEntity(docEntity);
	}

	/**
	 * 修改引用实体
	 * @param docEntity
	 */
	public void updateDocEntity(DocEnEntity docEntity) {
		docEntity.setUpdateTime(System.currentTimeMillis());
		docEntityDao.updateDocEntity(docEntity);
	}

	/**
	 *根据id删除引用实体
	 * @param entityId
	 */
	public void deleteDocEntity(int entityId) {
		docEntityDao.deleteDocEntity(entityId);
	}

	/**
	 * 获取所有的实体
	 * @return
	 */
	public List<DocEnEntity> getAllDocEntityList() {
		return docEntityDao.getAllDocEntityList();
	}

	/**
	 * 根据名字获取引用实体
	 * @param entityName
	 * @return
	 */
	public DocEnEntity getDocEntityByName(String entityName) {
		return docEntityDao.getDocEntityByName(entityName);
	}
	
	
	

}
