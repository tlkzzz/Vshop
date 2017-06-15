package com.Vshop.core.entity.base;

import lombok.Data;

/**
 * Created by ss on 2014/11/5.
 */
@Data
public class GoodsAttributeValue extends BaseEntity{

    /**
     * 属性值id
     */
    private Integer attrValueId;

    /**
     * 属性值名称
     */
    private String attrValueName;

    /**
     * 所属属性id
     */
    private Integer attrId;

    /**
     * 类型id
     */
    private Integer typeId;

    /**
     * 属性值排序
     */
    private Integer attrValueSort;

    /**
     * 0:未删除;1.已删除
     */
    //private int isDel;
    /**
     * 创建时间
     */
    //private Long createdTime;
    /**
     * 更新时间
     */
   // private Long updatedTime;

    private Integer delSign;
    
    /**
     * 所属性值名称
     */
    private String attrName;
}
