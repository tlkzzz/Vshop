package com.Vshop.service.module.goods.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.base.GoodsAttributeValue;
import com.Vshop.core.orm.mybatis.SqlMapper;


/**
 * Created by ss on 2014/11/5.
 */
@SqlMapper
public interface GoodsAttributeValueMapper {

    void save(GoodsAttributeValue attrValue);

    void deleteBatch(@Param("typeId")int id);

    void deleteByType(@Param("typeId")int typeId);

    void deleteBatchByType(@Param("ids") String ids);
    
    /**
     * 新增属性值，有排序
     * @param attrValue
     */
    void saveAttrVal(GoodsAttributeValue attrValue);

    /**
     * 通过id删除
     * @param id
     */
    void deleteById(@Param("id") int id);
    
    /**
     * 根据属性id删除属性值
     * @param attrId
     */
    void deleteByAttrId(int attrId);

    /**
     * 修改
     * @param attributeValue
     */
    void update(GoodsAttributeValue attributeValue);
    
    /**
     * 根据属性id查询属性值
     * @param attrId
     * @return
     */
    List<GoodsAttributeValue> findListByAttr(Integer attrId);
}
