package com.Vshop.service.module.website.dao;

import com.Vshop.core.entity.base.Article;

import java.util.List;
import java.util.Map;

/**
 * Created by rabook on 2014/11/9.
 */
public interface ArticleDao {

	/**
	 * 保存
	 * @param article
	 */
    public void save(Article article);
    
    /**
     * 更新
     * @param article
     */
    public void update(Article article);
    
    /**
     * 删除
     * @param id
     */
    public void delete(int id);
    
    /**
     * 根据
     * @param id
     * @return
     */
    public Article findById(int id);
    
    /**
     * 查询列表
     * @param map
     * @return
     */
    public List<Article> findList();
    
    /**
     * 总条数
     * @param article
     * @return
     */
    public int findCount(Article article);
    
    /**
     * 分页查询列表
     * @param map
     * @return
     */
    public List<Article> findPageList(Map<String, Object> map);
    
    /**
     * 根据条件查询列表
     * @param article
     * @return List<Article>
     */
    public List<Article> findListByArticle(Article article);
}
