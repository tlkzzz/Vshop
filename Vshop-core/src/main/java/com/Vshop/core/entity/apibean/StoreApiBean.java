package com.Vshop.core.entity.apibean;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * @author llf
 * @Package com.Vshop.core.entity.store
 * @Description:
 * @date 2014/11/14 15:43
 */

@Data
@ToString
public class StoreApiBean implements Serializable{

    private static final long serialVersionUID = 6160718121026169524L;

    private Integer storeId;

    private String storeName;//店铺名称

    private String storeLogo;//店标
    
    private Integer storeGoodsCount; //商品数量

    private Integer storeCollect;//店铺收藏数量
    
}
