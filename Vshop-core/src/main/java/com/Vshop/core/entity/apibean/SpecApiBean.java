package com.Vshop.core.entity.apibean;


import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * 商品规格表
 */

@Data
@ToString

public class SpecApiBean implements Serializable{

    private static final long serialVersionUID = -6821151359949478536L;
    /**
     * 规格ID
     */
    private String spId;

    /**
     * 规格名称
     */
    private String spName;
    
    /**
     * 规格呈现方式
     * 0为仅文字
     * 1文字+图片
     */
    private Integer spFormat;
    
    /**
     * 规格值,以逗号分隔
     */
    private String spValue;

    /**
     * 排序
     */
    private Integer spSort;
    
}
