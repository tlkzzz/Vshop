package com.Vshop.service.module.setting.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.OffPayArea;
import com.Vshop.service.module.setting.dao.OffPayAreaDao;
import com.Vshop.service.module.setting.service.OffPayAreaService;

/**
 * @author llf
 * @Package com.Vshop.service.module.setting.service.impl
 * @Description:
 * @date 2014/12/8 14:44
 */
@Service
public class OffPayAreaServiceImpl implements OffPayAreaService{

    @Resource
    private OffPayAreaDao offPayAreaDao;

    /**
     * 修改或保存
     *
     * @param offPayArea
     */
    @Override
    public void saveOrUpdate(OffPayArea offPayArea) {

        if(offPayAreaDao.findCount(offPayArea) == 0){
            offPayAreaDao.save(offPayArea);
        }else{
            offPayAreaDao.update(offPayArea);
        }
    }
}
