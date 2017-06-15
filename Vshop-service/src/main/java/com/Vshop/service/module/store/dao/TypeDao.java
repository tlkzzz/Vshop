package com.Vshop.service.module.store.dao;


import java.util.List;

import com.Vshop.core.entity.Type;
import com.Vshop.service.module.store.vo.TypesVo;
import com.Vshop.service.utils.page.Pager;

/**
 *
 */
public interface TypeDao {

    public List<TypesVo> queryClasssList(Pager pager);

    public List<Type> queryClasssParentList();

    public void save(Type classs);

    public void delete(Long id);

    public Type queryById(int id);

    public void update(Type classs);

    /**
     * 查询子节点
     * @return
     */
    public List<Type> queryClasssChildrenList(Type classs);

    /**
     * 去重
     * @param classs
     * @return
     */
    public int findCount(Type classs);
    
    /**
     * 根据父id查询分类列表
     * @param parentid 为0查询一级分类
     * @return
     */
    public List<Type> findList(int parentid);
    
    List<Type> findAllList();
}
