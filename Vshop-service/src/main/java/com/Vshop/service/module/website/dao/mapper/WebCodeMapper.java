package com.Vshop.service.module.website.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.core.entity.base.WebCode;

/**
 * @author llf
 * @Package com.Vshop.service.module.website.dao.mapper
 * @Description:
 * @date 2014/12/16 14:21
 */
@SqlMapper
public interface WebCodeMapper {

    /**
     * 保存
     * @param webCode
     */
    void save(WebCode webCode);

    /**
     * 查询所有
     * @return
     */
    List<WebCode> queryAll();

    /**
     * 查询单个
     * @param id
     * @return
     */
    WebCode queryById(@Param("codeId") int id);

    /**
     * 修改
     * @param webCode
     */
    void update(WebCode webCode);

    /**
     * 删除
     * @param id
     */
    void delete(@Param("id") int id);
    
    /**
     * 获取指定类型的 webCode
     * @param type
     * @return
     */
    public List<WebCode> queryAllByType(String type);

    /**
     * 获取指定类型的 webCode
     * @param type
     * @return
     */
    public WebCode queryNewByType(String type);
}
