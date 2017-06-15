package com.Vshop.service.module.operation.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Activity;
import com.Vshop.service.module.operation.dao.ActivityDao;
import com.Vshop.service.module.operation.dao.mapper.ActivityMapper;

/**
 * Created by rabook on 2014/11/11.
 */

@Repository
public class ActivityDaoImpl implements ActivityDao{

    @Autowired
    private ActivityMapper activityMapper;
    @Override
    public List<Activity> findList() {
        return activityMapper.findList();
    }
}
