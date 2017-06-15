package com.Vshop.core.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GoodsStoreCls {

	/**
     *主键
     */
    private Integer gcId;

    /**
     *父ID
     */
    private Integer gcParentId;
	
    /**
     * 店铺id
     */
    private Integer storeId;
    
    /**
     * 类别层级
     */
    private Integer clsLevel;
    
    /**
     *顶层id
     */
    private Integer topId;
}
