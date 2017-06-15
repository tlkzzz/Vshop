package com.Vshop.service.module.setting.dao.mapper;

import com.Vshop.core.entity.base.OffPayArea;
import com.Vshop.core.orm.mybatis.SqlMapper;

/**
 * @author llf
 * @Package com.Vshop.service.module.setting.dao.mapper
 * @Description:
 * @date 2014/12/8 14:41
 */
@SqlMapper
public interface OffPayAreaMapper {

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
