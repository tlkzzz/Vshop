package com.Vshop.service.module.goods.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.base.TypeSpec;
import com.Vshop.core.orm.mybatis.SqlMapper;

/**
 * Created by ss on 2014/10/15.
 */
@SqlMapper
public interface TypeSpecMapper {
	/**
	 * 将List<TypeSpec>插入表
	 * @param list
	 */
    void batchSave(List<TypeSpec> list);
    
    /**
     * 根据id删除
     * @param typeId
     */
    void delete(@Param("typeId") Integer typeId);
    
    /**
     * 根据分类id查询所有分类规格关联实体
     * @param typeId
     * @return
     */
    List<TypeSpec> findListByType(Integer typeId);
}
