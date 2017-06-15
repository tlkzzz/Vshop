package com.Vshop.service.module.website.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Navigation;
import com.Vshop.service.module.website.dao.NavigationDao;
import com.Vshop.service.module.website.dao.mapper.NavigationMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * @author llf
 * @Package com.Vshop.service.module.website.dao.impl
 * @Description:
 * @date 2014/11/11 13:37
 */

@Repository
public class NavigationDaoImpl implements NavigationDao{

    @Autowired
    private NavigationMapper navigationMapper;
    /**
     * 保存
     *
     * @param navigation
     */
    @Override
    public void save(Navigation navigation) {
        navigationMapper.save(navigation);
    }

    /**
     * 修改
     *
     * @param navigation
     */
    @Override
    public void update(Navigation navigation) {
        navigationMapper.update(navigation);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        navigationMapper.delete(id);
    }

    /**
     * 查询单条
     *
     * @param id
     * @return
     */
    @Override
    public Navigation findById(int id) {
        return navigationMapper.findById(id);
    }

    /**
     * 总条数
     *
     * @param navigation
     * @return
     */
    @Override
    public int findCount(Navigation navigation) {
        return navigationMapper.findCount(navigation);
    }

    /**
     * 分页列表
     * @return
     */
    @Override
    public List<Navigation> findPageList(Pager pager) {
        return navigationMapper.findPageList(pager);
    }

	@Override
	public List<Navigation> findAllList(Navigation navigation) {
		return navigationMapper.findAllList(navigation);
	}
}
