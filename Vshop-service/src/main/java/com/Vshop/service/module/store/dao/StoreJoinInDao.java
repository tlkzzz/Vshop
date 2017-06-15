package com.Vshop.service.module.store.dao;

import com.Vshop.core.entity.StoreJoinin;

/**
 * Created by rabook on 2015/3/29.
 */
public interface StoreJoinInDao {

    void save(StoreJoinin storeJoinin);

    public StoreJoinin findById(Long id);

    void updateJoinIn(StoreJoinin storeJoinin);
}
