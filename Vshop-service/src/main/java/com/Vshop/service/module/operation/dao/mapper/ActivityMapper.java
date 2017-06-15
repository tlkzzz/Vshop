package com.Vshop.service.module.operation.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.Activity;
import com.Vshop.core.orm.mybatis.SqlMapper;

/**
 * Created by rabook on 2014/11/11.
 */

@SqlMapper
public interface ActivityMapper {

    List<Activity> findList();
}
