package com.Vshop.front.module.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easemob.server.example.httpclient.api.EasemobIMUsers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.Vshop.core.common.MyBeanUtils;
import com.Vshop.core.entity.StoreGoodsClass;
import com.Vshop.core.entity.base.Favorites;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.GoodsDetailBean;
import com.Vshop.core.entity.base.Store;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.core.state.goods.GoodsState;
import com.Vshop.service.module.cart.service.FavoritesService;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.module.store.service.StoreGoodsClassService;
import com.Vshop.service.module.store.service.StoreService;
import com.Vshop.service.utils.page.Pager;

/**
 * 学院api
 * @author kviuff
 * @date 2015-07-21 15:30:00
 */
@Slf4j
@Controller
@RequestMapping("/storeapi")
public class StoreApi extends BaseApi {

	@Resource
    private StoreService storeService;
	
	@Resource
    private StoreGoodsClassService storeGoodsClassService;
	
	@Resource
	private GoodsService goodsService;
	
	@Resource
	private FavoritesService favoritesService;
	
	/**
	 * 学院详细
	 * @param storeId
	 * @return
	 */
	@RequestMapping("storedetail")
	@ResponseBody
	public JSONObject storeDetail(
			@RequestParam(value = "storeId") Integer storeId,
			@RequestParam(required=false, value="memberId",defaultValue="")String memberId
			){
		jsonObj = new JSONObject();
		try {
			
			Goods goods = new Goods();
			goods.setStoreId(storeId);
			//设置只查询上架的课程
			goods.setGoodsState(0);
			goods.setGoodsShow(1);
			int count = goodsService.countGoods(goods);
			Store store = storeService.findById(storeId);
			store.setStoreGoodsCount(count);
			
			/**
			 * 查询学院是否收藏
			 */
			int isFav = 0;
			if(StringUtils.isNotBlank(memberId)){
				isFav = favoritesService.findcountFav(storeId,Integer.valueOf(memberId),2);
			}
			store.setIsFav(isFav);
			
			jsonObj.put("result", 1);
			jsonObj.put("data", JSONArray.fromObject(store, JsonUtils.getJsonConfig()));
		} catch (Exception e) {
			log.error("学院详细API出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "无信息");
		}
		return jsonObj;
	}
	
	/**
	 * 学院分类
	 * @param storeId
	 * @param parentId
	 * @return
	 */
	@RequestMapping("storeclass")
	@ResponseBody
	public JSONObject storeGlass(
			@RequestParam(value = "storeId") Integer storeId,
			@RequestParam(value = "parentId", defaultValue = "0") Integer parentId
			){
		jsonObj = new JSONObject();
		try {
			StoreGoodsClass storeGoodsClass = new StoreGoodsClass();
			storeGoodsClass.setStoreId(storeId);
			storeGoodsClass.setStcParentId(parentId);
			List<StoreGoodsClass> list=storeGoodsClassService.findList(storeGoodsClass);
			jsonObj.put("result", 1);
			jsonObj.put("data", JSONArray.fromObject(list, JsonUtils.getJsonConfig()));
		} catch (Exception e) {
			log.error("学院分类API出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "无信息");
		}
		return jsonObj;
	}
	
	/**
	 * 学院课程
	 * @param storeId
	 * @return
	 */
	@RequestMapping("storegoods")
	@ResponseBody
	public JSONObject storeGoods(
			@RequestParam(value = "storeId") Integer storeId,
			@RequestParam(value = "pageno", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pagesize", defaultValue = "10") Integer pageSize
			){
		jsonObj = new JSONObject();
		try {
			
			Goods goods = new Goods();
			goods.setStoreId(storeId);
			// 设置状态开启
			goods.setGoodsState(GoodsState.GOODS_OPEN_STATE);
			// 设置上架状态
			goods.setGoodsShow(GoodsState.GOODS_ON_SHOW);
			
			Pager pager = new Pager();
			pager.setPageNo(pageNo);
			if (pageSize != 0) {
				pager.setPageSize(pageSize);
			}
			pager.setCondition(goods);
			
			List<Goods> goodsList = goodsService.findGoodPagerList(pager);
			List<GoodsDetailBean> list = new ArrayList<GoodsDetailBean>();
			for (Goods goods2 : goodsList) {
				GoodsDetailBean detail = new GoodsDetailBean();
				MyBeanUtils.copyBeanNotNull2Bean(goods2, detail);
				list.add(detail);
			}
			jsonObj.put("result", 1);
			jsonObj.put("data", JSONArray.fromObject(list, JsonUtils.getJsonConfig()));
		} catch (Exception e) {
			log.error("学院课程API出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "无信息");
		}
		return jsonObj;
	}
	
	/**
	 * 学院收藏
	 * @param storeId
	 * @param memberId
	 * @param favType 1-收藏课程，2-收藏学院
	 * @return
	 */
	@RequestMapping("storecollection")
	@ResponseBody
	public JSONObject storeCollection(
			@RequestParam(value = "storeId", required=false) Integer storeId,
			@RequestParam(value = "memberId") Integer memberId,
			@RequestParam(value = "favType") String favType,
			@RequestParam(value = "goodsId", required=false) Integer goodsId
			){
		jsonObj = new JSONObject();
		try {
			// 1已收藏，0未收藏
			int isFav = favoritesService.findcountFav(goodsId,Integer.valueOf(memberId),1);
			if("2".equals(favType)){
				isFav = favoritesService.findcountFav(storeId,Integer.valueOf(memberId),2);
			}
			
			if(isFav == 0){
				if("1".equals(favType)){
					favoritesService.saveFavGoods(goodsId, memberId);
				}else{
					favoritesService.saveFavStore(storeId, memberId);
				}
				jsonObj.put("result", 1);
				jsonObj.put("isfav", 1);
				jsonObj.put("msg", "收藏成功");
			}else if(isFav == 1){
				if("1".equals(favType)){
					favoritesService.deleteFavGoods(goodsId, memberId);
				}else{
					favoritesService.deleteFavStore(storeId, memberId);
				}
				jsonObj.put("result", 1);
				jsonObj.put("isfav", 0);
				jsonObj.put("msg", "取消收藏成功");
			}
		} catch (Exception e) {
			log.error("收藏API出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
		}
		return jsonObj;
	}
	
	/**
	 * 取消收藏
	 * @param storeId
	 * @param memberId
	 * @return
	 */
	@RequestMapping("canclecollection")
	@ResponseBody
	public JSONObject cancleCollection(
			@RequestParam(value = "storeId") Integer storeId,
			@RequestParam(value = "memberId") Integer memberId
			){
		jsonObj = new JSONObject();
		try {
			Favorites favorites = new Favorites();
			favorites.setMemberId(memberId);
			favorites.setFavId(storeId);
			favorites.setFavType("2");
			favoritesService.deleteAllFav(favorites);
			jsonObj.put("result", 1);
			jsonObj.put("msg", "取消收藏成功");
		} catch (Exception e) {
			log.error("取消学院收藏API出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
		}
		return jsonObj;
	}
	
	/**
	 * 根据学院id判断环信是否有该用户
	 * @param storeId
	 * @return
	 */
	@RequestMapping("storeIMUsers")
	@ResponseBody
	public JSONObject storeIMUsers(
			@RequestParam(value = "storeId") Integer storeId
			){
		jsonObj = new JSONObject();
		try {
			Store store = storeService.findById(storeId);
			if(null != store){
				String name = store.getMemberName();
				ObjectNode getIMUsersByUserNameNode = EasemobIMUsers.getIMUsersByUserName(name);
				ObjectMapper mapper = new ObjectMapper();  
				try {
					JsonNode rootNode = mapper.readTree(getIMUsersByUserNameNode.toString());
					String status = rootNode.path("statusCode").toString();
					if("404".equals(status)){
						ObjectNode node = EasemobIMUsers.createNewIMUserSingle(name, "lmshopb2b2c");
						if (null != node) {
							System.out.println("EASEMOBIMUSERS-注册IM用户[单个]: " + node.toString());
						}else{
							System.out.println("EASEMOBIMUSERS-注册IM用户[单个]:失败");
						}
					}
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} 
				Map<String, String> map = new HashMap<String, String>();
				map.put("storeName", name);
				jsonObj.put("result", 1);
				jsonObj.put("data", JSONArray.fromObject(map));
				jsonObj.put("msg", "请求成功");
			}else{
				jsonObj.put("result", 0);
				jsonObj.put("msg", "请求失败");
			}
		} catch (Exception e) {
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
		}
		return jsonObj;
	}
}
