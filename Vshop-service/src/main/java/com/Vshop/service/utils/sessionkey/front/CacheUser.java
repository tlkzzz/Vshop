package com.Vshop.service.utils.sessionkey.front;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.Store;

/**
 * @author kviuff
 * @Description:前台登录用户信息缓存对象
 * @date 2015/10/19 15:51
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
     * 店铺
     */
    private Store store;
}
