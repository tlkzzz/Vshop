package com.Vshop.service.module.website.dao;

import java.util.List;

import com.Vshop.core.entity.ArticleClass;
import com.Vshop.core.entity.ArticleClassVo;

public interface ArticleClassDao {

	/**
	 * 添加
	 * @param articleClass
	 */
	public void save(ArticleClass articleClass);
	
	/**
     * 修改
     * @param articleClass
     */
	public void update(ArticleClass articleClass);
    
	/**
     * 删除
     * @param id
     */
	public void delete(int id);
    
	 /**
     * 根据id查找分类
     * @param id
     * @return
     */
	public ArticleClass findById(int id);
    
	/**
     * 列表
     * @return
     */
	public List<ArticleClass> findList();
    
	 /**
     * 获取子分类
     * @param id
     * @return
     */
	public List<ArticleClassVo> hasChildren(int id);
    
	/**
     * 获取总条数
     * @return
     */
	public int findCount();
    
	 /**
     * 列表分页方法
     * @return
     */
	public List<ArticleClassVo> findForPage();
    
	/**
     * 获取所有分类
     * @return
     */
	public List<ArticleClass> findAllList();
    
	 /**
	  * 重复数
	  * @param articleClass
	  * @return
	  */
	public int duplicate(ArticleClass articleClass);
    
	/**
     * 获取文章分类标题以及下面的文章标题 （acCode不为空的）
     * @return
     */
	public List<ArticleClassVo> findList(ArticleClassVo ArticleClass);
}
