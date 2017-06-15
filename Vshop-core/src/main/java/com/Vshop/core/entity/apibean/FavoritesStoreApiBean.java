package com.Vshop.core.entity.apibean;

import lombok.Data;
import lombok.ToString;

/**
 * ShopncFavorites entity. @author MyEclipse Persistence Tools
 */
@Data
@ToString
public class FavoritesStoreApiBean{

	private Long favTime;
    private StoreApiBean store;
}