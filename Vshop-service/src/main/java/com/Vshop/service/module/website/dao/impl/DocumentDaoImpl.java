package com.Vshop.service.module.website.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Document;
import com.Vshop.service.module.website.dao.DocumentDao;
import com.Vshop.service.module.website.dao.mapper.DocumentMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * @author llf
 * @Package com.Vshop.service.module.website.dao.impl
 * @Description:
 * @date 2014/11/11 11:29
 */
@Repository
public class DocumentDaoImpl implements DocumentDao{

    @Resource
    private DocumentMapper documentMapper;
    /**
     * 保存
     *
     * @param document
     */
    @Override
    public void save(Document document) {
        documentMapper.save(document);
    }

    /**
     * 修改
     *
     * @param document
     */
    @Override
    public void update(Document document) {
        documentMapper.update(document);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        documentMapper.delete(id);
    }

    /**
     * 查询单条记录
     *
     * @param id
     * @return
     */
    @Override
    public Document findById(int id) {
        return documentMapper.findById(id);
    }

    /**
     * 查询条数
     *
     * @return
     */
    @Override
    public int findCount() {
        return documentMapper.findCount();
    }

    /**
     * 分页列表
     *
     * @param pager
     * @return
     */
    @Override
    public List<Document> findPageList(Pager pager) {
        return documentMapper.findPageList(pager);
    }
}
