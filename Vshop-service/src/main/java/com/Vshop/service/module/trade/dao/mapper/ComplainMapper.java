package com.Vshop.service.module.trade.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.base.Complain;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * Created by rabook on 2014/12/20.
 */
@SqlMapper
public interface ComplainMapper {

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

    Complain findById(@Param("id") int id);
}
