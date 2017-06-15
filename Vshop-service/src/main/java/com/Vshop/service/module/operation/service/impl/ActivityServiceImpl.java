package com.Vshop.service.module.operation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.Activity;
import com.Vshop.service.module.operation.dao.ActivityDao;
import com.Vshop.service.module.operation.service.ActivityService;

/**
 * Created by rabook on 2014/11/11.
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    public List<Activity> findList(){
        return activityDao.findList();
    }
}
