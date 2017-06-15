package com.Vshop.service.module.goods.dao;

import java.util.List;

import com.Vshop.core.entity.GoodsAttribute;

/**
 * Created by ss on 2014/10/15.
 */
public interface GoodsAttributeDao {


    List<GoodsAttribute> findListByType(Integer typeId);
    
    List<GoodsAttribute> findByType(int typeId);
    
    void save(GoodsAttribute goodsAttribute);

    void update(GoodsAttribute goodsAttribute);

    void delete(int id);

    GoodsAttribute findById(int id);

    List<GoodsAttribute> findList(GoodsAttribute goodsAttribute);

    void batchSave(List<GoodsAttribute> list);

    void deleteBatch(int id);

    void deleteByType(int id);

    void deleteBatchByType(String ids);

}
