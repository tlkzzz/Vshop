package com.Vshop.service.module.website.dao.mapper;

import java.util.List;
import java.util.Map;

import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.core.entity.base.Article;

/**
 * Created by rabook on 2014/11/9.
 */

@SqlMapper
public interface ArticleMapper {

	 /**
     * 保存
     * @param article
     * @return List<Article>
     */
    public void save(Article article);
    
    /**
     * 更新
     * @param article
     * @return List<Article>
     */
    public void update(Article article);
    
    /**
     * 删除
     * @param article
     * @return List<Article>
     */
    public void delete(int id);
    
    /**
     * 根据id查询对象
     * @param article
     * @return List<Article>
     */
    public Article findById(int id);
    
    /**
     * 查询列表
     * @param article
     * @return List<Article>
     */
    public List<Article> findList();
    
    /**
     * 总条数
     * @param article
     * @return int
     */
    public int findCount(Article article);
    
    /**
     * 分页查询列表
     * @param map
     * @return List<Article>
     */
    public List<Article> findPageList(Map<String, Object> map);
    
    /**
     * 根据条件查询列表
     * @param article
     * @return List<Article>
     */
    public List<Article> findListByArticle(Article article);
}
