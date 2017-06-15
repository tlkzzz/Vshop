package com.Vshop.service.module.store.dao;


import java.util.List;

import com.Vshop.core.entity.Classs;
import com.Vshop.service.module.store.vo.ClasssVo;
import com.Vshop.service.utils.page.Pager;

/**
 *
 */
public interface ClasssDao {

    public List<ClasssVo> queryClasssList(Pager pager);

    public List<Classs> queryClasssParentList();

    public void save(Classs classs);

    public void delete(Long id);

    public Classs queryById(int id);

    public void update(Classs classs);

    /**
     * 查询子节点
     * @return
     */
    public List<Classs> queryClasssChildrenList(Classs classs);

    /**
     * 去重
     * @param classs
     * @return
     */
    public int findCount(Classs classs);
    
    /**
     * 根据父id查询分类列表
     * @param parentid 为0查询一级分类
     * @return
     */
    public List<Classs> findList(int parentid);
}
