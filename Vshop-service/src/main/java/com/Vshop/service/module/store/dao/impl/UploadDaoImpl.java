package com.Vshop.service.module.store.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Upload;
import com.Vshop.service.module.store.dao.UploadDao;
import com.Vshop.service.module.store.dao.mapper.UploadMapper;

/**
 * Created by rabook on 2015/3/7.
 */
@Repository
public class UploadDaoImpl implements UploadDao{

    @Resource
    private UploadMapper uploadMapper;
    /**
     * 保存
     *
     * @param upload
     */
    @Override
    public void save(Upload upload) {
        uploadMapper.save(upload);
    }

    /**
     * 删除
     *
     */
    @Override
    public void update(Upload upload) {
        uploadMapper.update(upload);
    }

    @Override
    public List<Upload> findByStoreId(int id) {
        return uploadMapper.findByStoreId(id);
    }
}
