package com.Vshop.service.module.website.service;

import java.util.List;

import com.Vshop.core.entity.base.Document;
import com.Vshop.service.utils.page.Pager;

/**
 * @author llf
 * @Package com.Vshop.service.module.website.service
 * @Description:
 * @date 2014/11/11 11:32
 */
public interface DocumentService {

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
     * 查询单条
     * @param id
     * @return
     */
    public Document findById(int id);

    /**
     * 分页列表
     * @param pager
     * @return
     */
    public List<Document> findListForPage(Pager pager);

    /**
     * 总条数
     * @return
     */
    public int findCount();
}
