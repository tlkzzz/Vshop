package com.Vshop.supplier.utils.sessionKey;

import java.io.Serializable;

import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.Store;
import com.Vshop.core.entity.base.Supplier;

import lombok.Data;

/**
 * @author llf
 * @Package com.Vshop.seller.module.index.vo
 * @Description:
 * @date 2015/3/2 14:40
 */
@Data
public class CacheUser implements Serializable{


    /**
     * 店铺
     */
    private Store store;
    /**
     * 卖家
     */
   // private Seller seller;
    /**
     * 会员
     */
    private Member member;
    
    
    /**
     * 店铺
     */
    private Supplier supplier;
}
