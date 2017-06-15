package com.Vshop.service.module.website.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.Document;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * @author llf
 * @Package com.Vshop.service.module.website.dao.mapper
 * @Description:
 * @date 2014/11/11 11:14
 */

@SqlMapper
public interface DocumentMapper {

    public void save(Document document);
    public void update(Document document);
    public void delete(int id);
    public Document findById(int id);
    public int findCount();
    public List<Document> findPageList(Pager pager);
}
