package com.Vshop.service.module.goods.dao.mapper;

import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.core.entity.GoodsAttribute;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ss on 2014/10/15.
 */
@SqlMapper
public interface GoodsAttributeMapper {


    List<GoodsAttribute> findListByType(@Param("typeId") int typeId);
    List<GoodsAttribute> findByType(@Param("typeId") int typeId);

    void save(GoodsAttribute goodsAttribute);

    void update(GoodsAttribute goodsAttribute);

    void delete(@Param("attrId")int id);

    GoodsAttribute findById(@Param("attrId")int id);

    List<GoodsAttribute> findList(GoodsAttribute goodsAttribute);

    void batchSave(List<GoodsAttribute> list);

    void deleteBatch(@Param("typeId") int typeId);

    void deleteByType(@Param("typeId") int id);

    void deleteBatchByType(@Param("ids") String ids);
    
    void findDetailListByType(int typeId);
}
