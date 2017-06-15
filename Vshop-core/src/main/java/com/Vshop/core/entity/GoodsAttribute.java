package com.Vshop.core.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.base.BaseEntity;
import com.Vshop.core.entity.base.GoodsAttributeValue;

/**
 * @author cgl <br>
 * 时间: 2015年06月29日15:49:52 <br>
 * 商品属性实体类
 */
@Data
@ToString
public class GoodsAttribute extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 6503609381963026112L;

    /**
     * 属性id
     */
    private Integer attrId;

    /**
     * 属性名称
     */
    private String attrName;

    /**
     * 类型id
     */
    private Integer typeId;

    /**
     * 属性值列
     */
    private String attrValue;

    /**
     * 是否显示。0为不显示、1为显示
     */
    private Integer attrShow;

    /**
     * 排序
     */
    private Integer attrSort;

    /**
     * 0:未删除;1.已删除
     */
    private int isDel;
    /**
     * 创建时间
     */
    //private Long createdTime;
    /**
     * 更新时间
     */
    //private Long updatedTime;

    /**
     * 自定义属性值id
     */
    private Integer attrValueId;

    /**
     * 自定义属性值名称
     */
    private String attrValueName;

    private List<GoodsAttributeValue> avList;
}
