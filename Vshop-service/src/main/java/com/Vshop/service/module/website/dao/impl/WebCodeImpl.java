package com.Vshop.service.module.website.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.WebCode;
import com.Vshop.service.module.website.dao.WebCodeDao;
import com.Vshop.service.module.website.dao.mapper.WebCodeMapper;

/**
 * @author llf
 * @Package com.Vshop.service.module.website.dao.impl
 * @Description:
 * @date 2014/12/16 14:24
 */
@Repository
public class WebCodeImpl implements WebCodeDao{

    @Resource
    private WebCodeMapper webCodeMapper;
    /**
     * 保存
     *
     * @param webCode
     */
    @Override
    public void save(WebCode webCode) {
        webCodeMapper.save(webCode);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<WebCode> queryAll() {
        return webCodeMapper.queryAll();
    }

    /**
     * 查询单个
     *
     * @param id
     * @return
     */
    @Override
    public WebCode queryById(int id) {
        return webCodeMapper.queryById(id);
    }

    /**
     * 修改
     *
     * @param webCode
     */
    @Override
    public void update(WebCode webCode) {
        webCodeMapper.update(webCode);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        webCodeMapper.delete(id);
    }
    
    
    /**
     * 获取指定类型的 webCode
     * @param type
     * @return
     */
    public List<WebCode> queryAllByType(String type){
    	return webCodeMapper.queryAllByType(type);
    }

    /**
     * 获取指定类型的 webCode
     * @param type
     * @return
     */
    public WebCode queryNewByType(String type){
    	return webCodeMapper.queryNewByType(type);
    }
}
