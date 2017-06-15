package com.Vshop.core.entity.base;

import java.sql.Timestamp;

import lombok.Data;
import lombok.ToString;

/**
 * ShopncFavorites entity. @author MyEclipse Persistence Tools
 */
@Data
@ToString
public class Favorites{

	private Integer memberId;
	private Integer favId;//收藏类型id
	private String favType;//1-收藏商品，2-收藏店铺
	private Long favTime;//收藏时间
	private Timestamp favTimestr;
    private String endTime;
    private String beginTime;
    private String goodsName;
    private String storeName;
    private String StoreAssistantName;//店铺管理员账号
    private Store store;
    private Goods goods;
    private int orderBy = 0;//按照时间顺序，0升序，1倒序
}