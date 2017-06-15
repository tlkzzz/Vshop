package com.Vshop.service.module.goods.service;

import com.Vshop.core.entity.GoodsAttribute;

/**
 * 
 * @author cgl
 * 描述: 属性service
 */
public interface AttributeService {
	/**
     * 根据id查询
     * @param id goodsAttribute的主键attrId
     * @return GoodsAttribute 返回 GoodsAttribute实体类
     */
    GoodsAttribute findById(int id);

    /**
     * 保存
     * @param goodsAttribute 实体类
     */
    void save(GoodsAttribute goodsAttribute);
    
    /**
     * 删除属性和属性值
     * @param attrId goodsAttribute的主键
     */
    void deleteAttrById(Integer attrId);
}
