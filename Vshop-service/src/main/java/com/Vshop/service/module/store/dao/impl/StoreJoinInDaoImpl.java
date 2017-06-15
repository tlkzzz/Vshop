package com.Vshop.service.module.store.dao.impl;

import com.Vshop.core.entity.StoreJoinin;
import com.Vshop.service.module.store.dao.StoreJoinInDao;
import com.Vshop.service.module.store.dao.mapper.StoreJoinInMapper;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by rabook on 2015/3/29.
 */
@Repository
public class StoreJoinInDaoImpl implements StoreJoinInDao {

    @Resource
    private StoreJoinInMapper storeJoinInMapper;

    @Override
    public void save(StoreJoinin storeJoinin) {
        storeJoinInMapper.save(storeJoinin);
    }

    @Override
    public StoreJoinin findById(Long id) {
        return storeJoinInMapper.findById(id);
    }

    @Override
    public void updateJoinIn(StoreJoinin storeJoinin) {
        storeJoinInMapper.updateJoinIn(storeJoinin);
    }
}
