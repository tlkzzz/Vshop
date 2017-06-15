package com.Vshop.service.module.store.dao.mapper;

import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.core.entity.Classs;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by rabook on 2015/3/29.
 */
@SqlMapper
public interface StoreClassMapper {

    List<Classs> queryParentClassList();

    List<Classs> queryChildrenClassList(@Param("parentId") int id);
}
