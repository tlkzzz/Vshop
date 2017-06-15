package com.Vshop.service.module.cart.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.Favorites;
import com.Vshop.core.entity.vo.FavGoodsVo;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;


/**
 * 
 * @author WY
 *
 */
@SqlMapper
public interface FavoritesMapper {
	
	List<Favorites> queryFavById(Favorites favorites);
	
	int saveFav(Favorites favorites);

	List<FavGoodsVo> findList(Pager pager);
	
	int countfindAll(Pager pager);
	
	
	List<FavGoodsVo> findShopList(Pager pager);
	
	int countfindShopAll(Pager pager);
	
	void deleteAllFav(Favorites favorites);
	
	int FavoriteGoodsCount(Favorites favorites);//收藏商品数量
	
	List<Favorites> findFavoriteGoodsList(Pager pager);//收藏商品
	
    int FavoriteStoreCount(Favorites favorites);//收藏店铺数量
	
	List<Favorites> findFavoriteStoreList(Pager pager);//收藏店铺
}
