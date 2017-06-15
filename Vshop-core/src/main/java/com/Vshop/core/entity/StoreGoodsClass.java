package com.Vshop.core.entity;


import java.util.List;

import com.Vshop.core.entity.base.BaseEntity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StoreGoodsClass extends BaseEntity{
    private Integer stcId;

    private String stcName;//分类名称

    private Integer stcParentId;//分类父id

    private Boolean stcState;//店铺商品分类状态

    private Integer storeId;//店铺id

    private Integer stcSort;//排序
    
    private int isDel;
    
    private int hasChild;

    private List<StoreGoodsClass> classList;
}