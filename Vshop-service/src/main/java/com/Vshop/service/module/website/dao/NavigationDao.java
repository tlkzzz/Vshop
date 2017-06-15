package com.Vshop.service.module.website.dao;

import java.util.List;

import com.Vshop.core.entity.base.Navigation;
import com.Vshop.service.utils.page.Pager;

/**
 * @author llf
 * @Package com.Vshop.service.module.website.dao
 * @Description:
 * @date 2014/11/11 13:32
 */
public interface NavigationDao {

    /**
     * 保存
     * @param navigation
     */
    public void save(Navigation navigation);

    /**
     * 修改
     * @param navigation
     */
    public void update(Navigation navigation);

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
    public Navigation findById(int id);

    /**
     * 总条数
     * @param navigation
     * @return
     */
    public int findCount(Navigation navigation);

    /**
     * 分页列表
     * @return
     */
    public List<Navigation> findPageList(Pager pager);
    
    /**
     * 根据参数获取 列表查询
     * @param navigation
     * @return
     */
    public List<Navigation> findAllList(Navigation navigation);
}
