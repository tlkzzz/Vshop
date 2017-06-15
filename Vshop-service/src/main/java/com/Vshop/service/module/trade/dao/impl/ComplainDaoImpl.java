package com.Vshop.service.module.trade.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Complain;
import com.Vshop.service.module.trade.dao.ComplainDao;
import com.Vshop.service.module.trade.dao.mapper.ComplainMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * Created by rabook on 2014/12/20.
 */
@Repository
public class ComplainDaoImpl implements ComplainDao{

    @Resource
    private ComplainMapper complainMapper;
    /**
     * 列表
     *
     * @param pager
     * @return
     */
    @Override
    public List<Complain> findList(Pager pager) {
        return complainMapper.findList(pager);
    }

    /**
     * 查询条数
     *
     * @param pager
     * @return
     */
    @Override
    public int findCount(Pager pager) {
        return complainMapper.findCount(pager);
    }

    @Override
    public Complain findById(int id) {
        return complainMapper.findById(id);
    }
}
