package com.Vshop.core.entity.base;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * Created by rabook on 2014/12/21.
 */
@Data
@ToString
public class ComplainGoods extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 2279696882133171358L;

    private Integer complainGoodsId;

    private Integer complainId;

    private Integer goodsId;

    private String goodsName;

    private Double goodsPrice;

    private Integer goodsNum;

    private String goodsImage;

    private String complainMessage;

    private Integer orderGoodsId;

    private Integer orderGoodsType;
}
