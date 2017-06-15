package com.Vshop.service.module.trade.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.Complain;
import com.Vshop.service.module.trade.dao.ComplainDao;
import com.Vshop.service.module.trade.service.ComplainService;
import com.Vshop.service.utils.page.Pager;

/**
 * Created by rabook on 2014/12/20.
 */
@Service
public class ComplainServiceImpl implements ComplainService{

    @Resource
    private ComplainDao complainDao;

    /**
     * 列表
     *
     * @param pager
     * @return
     */
    @Override
    public List<Complain> findList(Pager pager) {
        return complainDao.findList(pager);
    }

    /**
     * 查询条数
     *
     * @param pager
     * @return
     */
    @Override
    public int findCount(Pager pager) {
        return complainDao.findCount(pager);
    }

    @Override
    public Complain findById(int id) {
        return complainDao.findById(id);
    }
}
