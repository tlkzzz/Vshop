package com.Vshop.service.module.website.service;

import java.util.List;

import com.Vshop.core.entity.base.Navigation;
import com.Vshop.service.utils.page.Pager;

/**
 * @author llf
 * @Package com.Vshop.service.module.website.service
 * @Description:
 * @date 2014/11/11 14:39
 */
public interface NavigationService {

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
     * 列表查询
     * @param pager
     * @return
     */
    public List<Navigation> findListForPage(Pager pager);

    /**
     * 查询总条数
     * @return
     */
    public int findCount(Navigation navigation);
    
    
    /**
     * 根据参数获取 列表查询
     * @param navigation
     * @return
     */
    public List<Navigation> findAllList(Navigation navigation);
}
