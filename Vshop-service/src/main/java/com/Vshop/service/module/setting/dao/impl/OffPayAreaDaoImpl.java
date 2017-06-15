package com.Vshop.service.module.setting.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.OffPayArea;
import com.Vshop.service.module.setting.dao.OffPayAreaDao;
import com.Vshop.service.module.setting.dao.mapper.OffPayAreaMapper;

/**
 * @author llf
 * @Package com.Vshop.service.module.setting.dao.impl
 * @Description:
 * @date 2014/12/8 14:42
 */
@Repository
public class OffPayAreaDaoImpl implements OffPayAreaDao{

    @Resource
    private OffPayAreaMapper offPayAreaMapper;
    /**
     * 查询area_id集合
     *
     * @return
     */
    @Override
    public String queryByStoreId() {
        return offPayAreaMapper.queryByStoreId();
    }

    /**
     * 保存
     *
     * @param offPayArea
     */
    @Override
    public void save(OffPayArea offPayArea) {
        offPayAreaMapper.save(offPayArea);
    }

    /**
     * 修改
     *
     * @param offPayArea
     */
    @Override
    public void update(OffPayArea offPayArea) {
        offPayAreaMapper.update(offPayArea);
    }

    /**
     * 查询是否有记录
     *
     * @param offPayArea
     * @return
     */
    @Override
    public int findCount(OffPayArea offPayArea) {
        return offPayAreaMapper.findCount(offPayArea);
    }
}
