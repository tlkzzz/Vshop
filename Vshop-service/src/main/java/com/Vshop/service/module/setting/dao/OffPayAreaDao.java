package com.Vshop.service.module.setting.dao;

import com.Vshop.core.entity.base.OffPayArea;

/**
 * @author llf
 * @Package com.Vshop.service.module.setting.dao
 * @Description:
 * @date 2014/12/8 14:42
 */
public interface OffPayAreaDao {

    /**
     * 查询area_id集合
     * @return
     */
    String queryByStoreId();

    /**
     * 保存
     * @param offPayArea
     */
    void save(OffPayArea offPayArea);

    /**
     * 修改
     * @param offPayArea
     */
    void update(OffPayArea offPayArea);

    /**
     * 查询是否有记录
     * @param offPayArea
     * @return
     */
    int findCount(OffPayArea offPayArea);
}
