package com.Vshop.service.module.goods.vo;

import java.util.List;

import lombok.Data;

import com.Vshop.core.entity.GoodsAttribute;
import com.Vshop.core.entity.GoodsType;
import com.Vshop.core.entity.base.TypeBrand;
import com.Vshop.core.entity.base.TypeSpec;

/**
 * Created by ss on 2014/10/15.
 */
@Data
public class GoodsTypeVo {

    private GoodsType goodsType;

    /**
     * 属性集合类
     */
    private List<GoodsAttribute> attrList;

    /**
     * 品牌集合类
     */
    private List<TypeBrand> brandList;

    /**
     * 规格集合类
     */
    private List<TypeSpec> specList;

}
