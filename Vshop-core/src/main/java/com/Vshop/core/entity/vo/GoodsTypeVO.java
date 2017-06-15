package com.Vshop.core.entity.vo;

import java.util.List;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.GoodsAttribute;
import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.base.Spec;

@Data
public class GoodsTypeVO {

	/**
     * 类型id
     */
    private Integer typeId;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 类型排序
     */
    private Integer typeSort;
    
    /**
     * 商品属性List
     */
    private List<GoodsAttribute> attributes;
    
    /**
     * 商品品牌List
     */
    private List<Brand> brandList;
    
    /**
     * 商品规格List
     */
    private List<SpecVo> specList;
	
}
