package com.Vshop.service.module.store.dao;

import com.Vshop.core.entity.Classs;

import java.util.List;

/**
 * Created by rabook on 2015/3/29.
 */
public interface StoreClassDao {

    List<Classs> queryParentClassList();

    List<Classs> queryChildrenClassList(int id);
}
