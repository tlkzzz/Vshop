package com.Vshop.service.module.website.service;

import java.util.List;

import com.Vshop.core.entity.base.Article;
import com.Vshop.service.utils.page.Pager;

/**
 * Created by rabook on 2014/11/9.
 */
public interface ArticleService {

    public void save(Article article);
    public void update(Article article);
    public void delete(int id);
    public Article findById(int id);
    public List<Article> findList();
    public List<Article> findListForPage(Pager pager, Article article);
    public int findCount(Article article);
    /**
     * 根据条件查询列表
     * @param article
     * @return List<Article>
     */
    public List<Article> findListByArticle(Article article);
}
