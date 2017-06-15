package com.Vshop.service.module.trade.dao;

import java.util.List;

import com.Vshop.core.entity.base.Complain;
import com.Vshop.service.utils.page.Pager;

/**
 * Created by rabook on 2014/12/20.
 */
public interface ComplainDao {

    /**
     * 列表
     * @param pager
     * @return
     */
    List<Complain> findList(Pager pager);

    /**
     * 查询条数
     * @param pager
     * @return
     */
    int findCount(Pager pager);

    Complain findById(int id);
}
