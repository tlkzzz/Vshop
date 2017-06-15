package com.Vshop.service.module.cart.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Favorites;
import com.Vshop.core.entity.vo.FavGoodsVo;
import com.Vshop.service.module.base.BaseDao;
import com.Vshop.service.module.cart.dao.FavoritesDao;
import com.Vshop.service.module.cart.dao.mapper.FavoritesMapper;
import com.Vshop.service.utils.page.Pager;


/**
 *    
 * 项目名称：Vshop-front   
 * 类名称：FavoritesDaoImpl   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年12月28日 下午10:30:51   
 * 修改人：liuhao   
 * 修改时间：2014年12月28日 下午10:30:51   
 * 修改备注：   
 * @version    
 *
 */
@Repository
public class FavoritesDaoImpl extends BaseDao implements FavoritesDao {
    @Resource
    private FavoritesMapper favoritesMapper;

	@Override
	public List<Favorites> queryFavById(Favorites favorites) {
		return favoritesMapper.queryFavById(favorites);
	}

	@Override
	public int saveFav(Favorites favorites) {
		return favoritesMapper.saveFav(favorites);
	}

	@Override
	public List<FavGoodsVo> findList(Pager pager) {
		return favoritesMapper.findList(pager);
	}

	@Override
	public int countfindAll(Pager pager) {
		return favoritesMapper.countfindAll(pager);
	}

	
	
	@Override
	public List<FavGoodsVo> findShopList(Pager pager) {
		return favoritesMapper.findShopList(pager);
	}
	
	@Override
	public int countfindShopAll(Pager pager) {
		return favoritesMapper.countfindShopAll(pager);
	}

	@Override
	public void deleteAllFav(Favorites favorites) {
		favoritesMapper.deleteAllFav(favorites);
	}

	@Override
	public int FavoriteGoodsCount(Favorites favorites) {
		return favoritesMapper.FavoriteGoodsCount(favorites);
	}

	@Override
	public List<Favorites> findFavoriteGoodsList(Pager pager) {
		return favoritesMapper.findFavoriteGoodsList(pager);
	}

	@Override
	public int FavoriteStoreCount(Favorites favorites) {
		return favoritesMapper.FavoriteStoreCount(favorites);
	}

	@Override
	public List<Favorites> findFavoriteStoreList(Pager pager) {
		return favoritesMapper.findFavoriteStoreList(pager);
	}
	
}
