package com.Vshop.service.module.website.service;

import java.util.List;

import com.Vshop.core.entity.ArticleClass;
import com.Vshop.core.entity.ArticleClassVo;
import com.Vshop.core.entity.vo.ArticleClassTitleVo;

public interface ArticleClassService {

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
     * 列表分页方法
     * @return
     */
    public List<ArticleClassVo> findListForPage();
    
    /**
     * 获取子分类
     * @param id
     * @return
     */
    List<ArticleClassVo> findChildren(int id);
    
    /**
     * 获取总条数
     * @return
     */
    public int findCount();
    
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
    
//    public List<ArticleClassVo> findList(ArticleClassVo ArticleClass);
    
    /**
     * 获取文章分类标题以及下面的文章标题 （acCode不为空的）
     * @return
     */
    public List<ArticleClassTitleVo> findTitleList();
}
