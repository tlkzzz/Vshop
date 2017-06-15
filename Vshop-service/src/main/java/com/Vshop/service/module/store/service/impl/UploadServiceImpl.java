package com.Vshop.service.module.store.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.Vshop.core.common.Collections3;
import com.Vshop.core.entity.base.Store;
import com.Vshop.core.entity.base.Upload;
import com.Vshop.service.module.store.dao.StoreDao;
import com.Vshop.service.module.store.dao.UploadDao;
import com.Vshop.service.module.store.service.UploadService;

/**
 * Created by rabook on 2015/3/8.
 */
@Service
public class UploadServiceImpl implements UploadService{

    @Resource
    private UploadDao uploadDao;

    @Resource
    private StoreDao storeDao;


    @Override
    public void update(Upload upload) {
        uploadDao.update(upload);
    }

    @Override
    public void save(Upload upload) {
        uploadDao.save(upload);
    }

    @Override
    public List<Upload> findByStore(int id) {
        return uploadDao.findByStoreId(id);
    }

    @Override
    public void saveUploadSilde(String[] slide, String[] imgurls) {
int storeId = 1;
        List<Upload> list = findByStore(storeId);
        for(String s : slide){
            String fileName = s.split(";")[0].split("=")[1];
            String fileSize = s.split(";")[1].split("=")[1];
            int index = Integer.parseInt(s.split(";")[2].split("=")[1]);
            if(index < list.size()){
                int uploadId = list.get(index).getUploadId();
                Upload upload = new Upload();
                upload.setUploadId(uploadId);
                upload.setFileName(fileName);
                upload.setFileSize(Integer.parseInt(fileSize));
                //修改
                update(upload);
            }else{
                //新增
                Upload upload = new Upload();
                upload.setFileName(fileName);
                upload.setFileSize(Integer.parseInt(fileSize));
                upload.setStoreId(storeId);
                upload.setUploadType(7);
                save(upload);
            }
        }
        List<String> slideList = Lists.newArrayList();
        List<String> imgList = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(list)){
            for(Upload upload : list){
                slideList.add(upload.getFileName());
            }
            Store store = storeDao.findById(1);//controller传过来取活的
            for(String url : store.getStoreSlideUrl().split(",")){
                imgList.add(url);
            }
        }
        int index = 0;
        for(String s : slide){
            slideList.add(s.split(";")[0].split("=")[1]);
            if(imgurls.length >= index){
                imgList.add(imgurls[index]);
            }else{
                imgList.add("");
            }
            index++;
        }
        Store store = new Store();
        store.setStoreId(storeId);
        store.setStoreSlide(Collections3.convertToString(slideList, ","));
        store.setStoreSlideUrl(Collections3.convertToString(imgList,","));
        //修改store
        storeDao.updateStore(store);
    }


}
