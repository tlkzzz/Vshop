package com.Vshop.core.entity.vo;


import java.util.List;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.base.Spec;
import com.Vshop.core.entity.base.SpecValue;

/**
 * 商品规格表
 */

@Data
public class SpecVo extends Spec{
    
    /**
     * 规格值
     */
    private List<SpecValue> specValueList;
    
}
