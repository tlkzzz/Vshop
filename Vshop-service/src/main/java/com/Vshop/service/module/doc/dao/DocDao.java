package com.Vshop.service.module.doc.dao;

import java.util.List;

import com.Vshop.core.entity.base.DocEntity;
import com.Vshop.service.utils.page.Pager;

/**
 * 项目名称：Vshop-admin
 * 类名称：DocDao
 * 类描述：接口
 * 创建人：lkang
 * 创建时间：2015年4月22日 10:52:19
 */
public interface DocDao {
	/**
	 * 获取文档总数
	 * @param pager
	 * @return
	 */
	int getDocCount(DocEntity docEntity);
	
	/**
	 * 获取文档分页列表
	 * @param pager
	 * @return
	 */
	List<DocEntity> getDocList(Pager pager);
	
	/**
	 * 根据id获取文档详细
	 * @param id
	 * @return
	 */
	DocEntity getDocById(int id);
	
	/**
	 * 添加文档数据
	 * @param doc
	 */
	void save(DocEntity doc);
	
	/**
	 * 更新文档数据
	 * @param doc
	 */
	void update(DocEntity doc);
	
	/**
	 * 删除文档数据
	 * @param doc
	 */
	void delete(int id);
	
	/**
	 * 获取所有的文档
	 * @return
	 */
	List<DocEntity> getAllDocList(DocEntity doc);
}
