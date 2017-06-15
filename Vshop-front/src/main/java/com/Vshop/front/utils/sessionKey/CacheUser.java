package com.Vshop.front.utils.sessionKey;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.Store;

/**
 * @author llf
 * @Package com.Vshop.front.utils.sessionKey
 * @Description:
 * @date 2015/3/13 15:51
 */
@Data
@ToString
public class CacheUser implements Serializable{

    private static final long serialVersionUID = -5238744888313423560L;
    /**
     *会员
     */
    private Member member;
    
    /**
     * 学院
     */
    private Store store;
}
