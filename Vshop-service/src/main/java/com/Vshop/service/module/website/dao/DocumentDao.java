package com.Vshop.service.module.website.dao;

import java.util.List;

import com.Vshop.core.entity.base.Document;
import com.Vshop.service.utils.page.Pager;

/**
 * @author llf
 * @Package com.Vshop.service.module.website.dao
 * @Description:
 * @date 2014/11/11 11:27
 */
public interface DocumentDao {

    /**
     * 保存
     * @param document
     */
    public void save(Document document);

    /**
     * 修改
     * @param document
     */
    public void update(Document document);

    /**
     * 删除
     * @param id
     */
    public void delete(int id);

    /**
     * 查询单条记录
     * @param id
     * @return
     */
    public Document findById(int id);

    /**
     * 查询条数
     * @return
     */
    public int findCount();

    /**
     * 分页列表
     * @param pager
     * @return
     */
    public List<Document> findPageList(Pager pager);
}
