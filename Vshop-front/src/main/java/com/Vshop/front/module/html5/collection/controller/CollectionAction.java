package com.Vshop.front.module.html5.collection.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.cart.service.FavoritesService;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.module.store.service.StoreService;

import freemarker.template.TemplateException;

/**
 * @项目名称：Vshop-seller
 * @类名称：FavoriteAction
 * @类描述： 卖家收藏
 * @创建人：zrh
 * @创建时间：2015年8月14日 下午14:15:53
 * 
 */
@Controller
@RequestMapping("/Favorite")
@Slf4j
public class CollectionAction {
	@Resource
	private FavoritesService favoritesService;
	@Resource
	private StoreService storeService;
	@Resource
	private GoodsService goodsService;

	/**
	 * 收藏课程或学院
	 */
	@RequestMapping("/saveCollection")
	public @ResponseBody
	Map<String, Object> saveCollection(
			@RequestParam(required = false, value = "storeId", defaultValue = "") Integer storeId,
			@RequestParam(required = false, value = "favType", defaultValue = "") String favTpe,
			@RequestParam(required = false, value = "goodsId", defaultValue = "") Integer goodsId) {
		Map<String, Object> map = Maps.newHashMap();
		Subject currentUser = SecurityUtils.getSubject();
		Integer sgId = null;
		// 判断是学院还是课程
		if (favTpe.equals("1")) {
			sgId = goodsId;
		} else {
			sgId = storeId;
		}
		try {
			if (currentUser.isAuthenticated()) {
				if (CacheUtils.getCacheUser() != null) {
					if (null != CacheUtils.getCacheUser().getStore()) {
						// 店主会员收藏课程或学院
						if (CacheUtils.getCacheUser().getStore().getStoreId() != storeId) {
							collectionGoodsOrStore(favTpe, map, sgId);
						} else {
							map.put("msg", "不能收藏自己的学院或者课程!");
						}

					} else {
						// 普通会员收藏课程或学院
						collectionGoodsOrStore(favTpe, map, sgId);
					}
				}

			} else {
				map.put("msg", "请登录后收藏");
			}
		} catch (Exception e) {
			log.error("收藏失败", e);
			map.put("success", false);
			map.put("msg", "收藏失败");
		}
		return map;
	}
	
	/**
	 * 
	 * @param favTpe 1:收藏课程 2:收藏学院
	 * @param map 提示信息
	 * @param sgId goodsId 或 storeId
	 * @throws IOException
	 * @throws TemplateException
	 * @throws ServletException
	 */
	private void collectionGoodsOrStore(String favTpe, Map<String, Object> map, Integer sgId)
			throws IOException, TemplateException, ServletException {
		if (CacheUtils.getCacheUser().getMember().getMemberId() != null) {
			Integer fav = favoritesService.findcountFav(sgId, CacheUtils.getCacheUser().getMember().getMemberId(),
					Integer.valueOf(favTpe));
			if (fav == 0) {
				// 保存方法
				favoritesService.saveFav(sgId, CacheUtils.getCacheUser().getMember().getMemberId(),
						Integer.valueOf(favTpe));
				// 没有重复收藏的情况
				if (favTpe.equals("2")) {
					// 修改学院的收藏数量
					updateStoreCollectionCount(sgId.toString(), "add");
					map.put("favType", 2);
				}
				if (favTpe.equals("1")) {
					// 修改课程的的收藏数量
					Goods goods = new Goods();
					goods.setGoodsId(Integer.valueOf(sgId));// 课程id
					goods.setGoodsCollect(1);// 1表示新增收藏
					goodsService.updateGoods(goods);
					map.put("favType", 1);
				}
				map.put("success", true);
				map.put("favFlag", 1);
				map.put("msg", "收藏成功");
			} else {
				if("1".equals(favTpe)) {  // 课程
					favoritesService.deleteFavGoods(sgId, CacheUtils.getCacheUser().getMember().getMemberId());
					
					// 修改课程的的收藏数量
					Goods goods = new Goods();
					goods.setGoodsId(Integer.valueOf(sgId));// 课程id
					goods.setGoodsCollect(-1); //
					goodsService.updateGoods(goods);
					
					map.put("favType", favTpe);
				} else if("2".equals(favTpe)) { // 学院
					favoritesService.deleteFavStore(sgId, CacheUtils.getCacheUser().getMember().getMemberId());
					
					// 修改学院的收藏数量
					updateStoreCollectionCount(sgId.toString(), "reduce");
					
					map.put("favType", favTpe);
				}
				
				map.put("success", true);
				map.put("favFlag", 0);
				map.put("msg", "取消收藏成功");
			}
		}
	}

	/**
	 * 修改学院收藏的数量
	 */
	public void updateStoreCollectionCount(String storeId, String flags) {
		Map<String, String> storeMap = new HashMap<String, String>();
		storeMap.put("storeId", storeId);
		storeMap.put("flags", flags);//flags是add时表示增加收藏，等于reduce时表示取消收藏
		storeService.updateStoreCount(storeMap);
	}
	
}
