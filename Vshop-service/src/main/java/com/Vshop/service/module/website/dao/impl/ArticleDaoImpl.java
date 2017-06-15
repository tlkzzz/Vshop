package com.Vshop.service.module.website.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Article;
import com.Vshop.service.module.website.dao.ArticleDao;
import com.Vshop.service.module.website.dao.mapper.ArticleMapper;

/**
 * Created by rabook on 2014/11/9.
 */
@Repository
public class ArticleDaoImpl implements ArticleDao{

    @Resource
    private ArticleMapper articleMapper;
    @Override
    public void save(Article article) {
        articleMapper.save(article);
    }

    @Override
    public void update(Article article) {
        articleMapper.update(article);
    }

    @Override
    public void delete(int id) {
        articleMapper.delete(id);
    }

    @Override
    public Article findById(int id) {
        return articleMapper.findById(id);
    }

    @Override
    public List<Article> findList() {
        return articleMapper.findList();
    }

    @Override
    public int findCount(Article article) {
        return articleMapper.findCount(article);
    }

    @Override
    public List<Article> findPageList(Map<String, Object> map) {
        return articleMapper.findPageList(map);
    }

	@Override
	public List<Article> findListByArticle(Article article) {
		return articleMapper.findListByArticle(article);
	}
}
