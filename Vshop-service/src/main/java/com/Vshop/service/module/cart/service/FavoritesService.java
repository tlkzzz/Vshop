package com.Vshop.service.module.cart.service;

import java.util.List;

import com.Vshop.core.entity.base.Favorites;
import com.Vshop.core.entity.vo.FavGoodsVo;
import com.Vshop.service.utils.page.Pager;

public interface FavoritesService {
	/**
	 * 获取到收藏夹信息
	 * @Title: queryFavById 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param favorites
	 * @param @return    设定文件 
	 * @return List<Favorites>    返回类型 
	 * @throws
	 */
	List<Favorites> queryFavById(Favorites favorites);
	
	/**
	 * 查询收藏(商品)分页数量
	 * @param pager
	 * @return
	 */
	int countfindAll(Pager pager);
	
	/**
	 * 分页查询收藏(店铺)信息
	 * @param pager
	 * @return 返回收藏店铺的信息
	 */
	List<FavGoodsVo> findShopList(Pager pager);
	
	/**
	 * 查询收藏(店铺)分页数量
	 * @param pager
	 * @return
	 */
	int countfindShopAll(Pager pager);
	
	/**
	 * 删除收藏信息
	 * @param favorites
	 * 删除时传值：memberId 会员id favId商品或店铺id favType收藏类型,1-收藏商品，2-收藏店铺
	 * @return
	 */
	int deleteAllFav(Favorites favorites);
	
	/**
	 * 添加收藏商品
	 * @param id 商品id
	 * @param memberId 用户id
	 * @return
	 */
	void saveFavGoods(Integer goodsId,Integer memberId);
	
	/**
	 * 添加收藏商品
	 * @param id 商品id
	 * @param memberId 用户id
	 * @return
	 */
	void saveFavStore(Integer storeId,Integer memberId);
	
	/**
	 * 查询收藏(商品)分页数量
	 * @param pager
	 * @return
	 */
    int FavoriteGoodsCount(Favorites favorites);
    /**
	 * 分页查询收藏(商品)信息
	 * @param pager
	 * @return 返回收藏商品表信息
	 */
	List<Favorites> findFavoriteGoodsList(Pager pager);
	
    /**
	 * 查询收藏(店铺)分页数量
	 * @param pager
	 * @return
	 */
    int FavoriteStoreCount(Favorites favorites);
    /**
	 * 分页查询收藏(店铺)信息
	 * @param pager
	 * @return 返回收藏店铺的信息
	 */
	List<Favorites> findFavoriteStoreList(Pager pager);
	
	/**
	 * 判断查询是否重复
	 * @param id 商品id或店铺id
	 * @param buyerId 用户id
	 * @param FavType 收藏类型,1-收藏商品，2-收藏店铺
	 * @return
	 */
	int findcountFav(Integer id,Integer buyerId,Integer FavType);
	
	/**
	 * 添加收藏
	 * @param id 商品id或店铺id
	 * @param buyerId 用户id
	 * @param FavType 收藏类型,1-收藏商品，2-收藏店铺
	 * @return
	 */
	void saveFav(Integer goodsId,Integer buyerId,Integer FavType);
	
	/**
	 * 取消收藏商品
	 * @param goodsId
	 * @param buyerId
	 */
	void deleteFavGoods(Integer goodsId,Integer memberId);
	
	/**
	 * 取消收藏店铺
	 * @param storeId
	 * @param buyerId
	 */
	void deleteFavStore(Integer storeId,Integer memberId);

}
