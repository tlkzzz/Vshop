package com.Vshop.service.module.goods.dao;

import com.Vshop.core.entity.base.GoodsAttributeValue;

public interface AttributeValueDao {
	void save(GoodsAttributeValue attrValue);

    void deleteBatch(int id);

    void deleteByType(int id);

    void deleteBatchByType(String ids);

    /**
     * 通过id删除
     * @param attrId
     */
    void deleteById(int attrId);

    /**
     * 修改
     * @param attributeValue
     */
    void update(GoodsAttributeValue attributeValue);
    
    /**
     * 根据属性id删除属性值
     * @param attrId
     */
    void deleteByAttrId(int attrId);
}
