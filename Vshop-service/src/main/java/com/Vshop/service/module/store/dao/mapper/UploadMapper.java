package com.Vshop.service.module.store.dao.mapper;

import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.core.entity.base.Upload;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by rabook on 2015/3/7.
 */
@SqlMapper
public interface UploadMapper {

    /**
     * 保存
     * @param upload
     */
    void save(Upload upload);

    /**
     * 修改
     * @param id
     */
    void update(Upload upload);

    List<Upload> findByStoreId(@Param("id") int id);
}
