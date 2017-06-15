package com.Vshop.service.module.store.dao.mapper;

import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.core.entity.StoreJoinin;

import org.apache.ibatis.annotations.Param;

/**
 * Created by rabook on 2015/3/29.
 */
@SqlMapper
public interface StoreJoinInMapper {

    void save(StoreJoinin storeJoinin);

    public StoreJoinin findById(@Param("id") Long id);

    void updateJoinIn(StoreJoinin storeJoinin);
}
