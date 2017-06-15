package com.Vshop.service.module.store.service;

import com.Vshop.core.entity.base.Upload;

import java.util.List;

/**
 * Created by rabook on 2015/3/7.
 */
public interface UploadService {

    public void update(Upload upload);

    public void save(Upload upload);

    public List<Upload> findByStore(int id);

    public void saveUploadSilde(String[] slide,String[] imgurls);
}
