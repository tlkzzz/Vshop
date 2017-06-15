package com.Vshop.service.module.store.dao.mapper;

import com.Vshop.core.entity.base.AlbumClass;
import com.Vshop.core.orm.mybatis.SqlMapper;

/**
 * @author llf
 * @Package com.Vshop.service.module.store.dao.mapper
 * @Description:
 * @date 2014/12/11 16:12
 */
@SqlMapper
public interface AlbumClassMapper {

    /**
     * 保存
     * @param albumClass
     */
    void save(AlbumClass albumClass);
}
