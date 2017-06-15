package com.Vshop.core.entity.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class StoreGoodsClassVo implements Serializable {

    private int stcId;
    private Integer storeId;
    private Integer parentId;
    private String parentName;
    private Boolean parentState;
    private Integer parentSort;
    private Integer childId;
    private String childName;
    private Boolean childState;
    private Integer childSort;
}
