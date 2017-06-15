package com.Vshop.core.entity.vo;


import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * 2015年06月29日15:49:59
 * @author cgl
 * 这个实体类不用于存数据
 */
@Data
@ToString
public class GoodsAttrVo {
    /**
     * 属性id
     */
    private Integer attrId;
    
    /**
     * 属性名称
     */
    private String attrName;
    
    /**
     * 自定义属性值id
     */
    private Integer attrValueId;
    
    /**
     * 自定义属性值名称
     */
    private String attrValueName;
}
