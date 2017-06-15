package com.Vshop.front.module.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.base.Favorites;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.vo.ArticleClassTitleVo;
import com.Vshop.core.entity.vo.FavGoodsVo;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.cart.service.FavoritesService;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.module.store.service.StoreService;
import com.Vshop.service.module.website.service.ArticleClassService;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：Vshop-front   
 * 类名称：MyFavAction   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2015年3月3日 下午10:15:28   
 * 修改人：liuhao   
 * 修改时间：2015年3月3日 下午10:15:28   
 * 修改备注：   
 * @version    
 *
 */
@Controller
@RequestMapping("/myuser")
@Slf4j
public class MyUserAction {
	
	@Resource
	private FavoritesService favoritesService;
	
	@Resource
	private ArticleClassService articleClassService;
	
    @Resource
	private StoreService storeService;
    @Resource
	private GoodsService goodsService;
	/**
	 * 导航主页面跳转
	 * 
	 * @Title: index
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		try {
			ModelAndView model = new ModelAndView("/user/fav/my-fav");
			model.addObject("cur", "myfav");
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}

	/**
	 * 查询收藏课程页面
	 * 
	 * @Title: goodsFavIndex
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/goodsFavIndex")
	public ModelAndView goodsFavIndex(){
		try {
			ModelAndView model = new ModelAndView("/user/fav/my-goodsfav-index");
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 查询收藏课程页面
	 * 
	 * @Title: goodsFavList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/goodsFavList")
	public ModelAndView goodsFavList(
			@RequestParam(required=false, value="div",defaultValue="")String div,
			@RequestParam(required=false, value="pageNo",defaultValue="")String pageNo
			){
		try {
			ModelAndView model = new ModelAndView("/user/fav/my-goodsfav-list");
			Pager pager = new Pager();
	    	if(StringUtils.isNotBlank(pageNo)) {
	             pager.setPageNo(Integer.parseInt(pageNo));
	        }
        	model.addObject("pageNo",pager.getPageNo());// 当前页
            model.addObject("pageSize", pager.getPageSize());// 每页显示条数
            model.addObject("toUrl", "/myuser/goodsFavList");// 跳转URL
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 导航主页面跳转
	 * 
	 * @Title: index
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/storeindex")
	public ModelAndView dpindex() {
		try {
			ModelAndView model = new ModelAndView("/user/fav/my-storefav-index");
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	
	/**
	 * 查询收藏学院页面
	 * 
	 * @Title: storeFavList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/storeFavList")
	public ModelAndView storeFavList(
			@RequestParam(required=false, value="div",defaultValue="")String div,
			@RequestParam(required=false, value="pageNo",defaultValue="")String pageNo){
		try {
			ModelAndView model = new ModelAndView("/user/fav/my-storefav-list");
			Pager pager = new Pager();
	    	if(StringUtils.isNotBlank(pageNo)) {
	             pager.setPageNo(Integer.parseInt(pageNo));
	        }
        	model.addObject("pageNo",pager.getPageNo());// 当前页
            model.addObject("pageSize", pager.getPageSize());// 每页显示条数
            model.addObject("toUrl", "/myuser/storeFavList");// 跳转URL
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 查询页面
	 * 
	 * @Title: list
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/dplist")
	public ModelAndView dplist(
			@RequestParam(required=false, value="div",defaultValue="")String div,
			@RequestParam(required=false, value="pageNo",defaultValue="")String pageNo,
			@RequestParam(required=false, value="addTime",defaultValue="")String addTime,
			@RequestParam(required=false, value="storeName",defaultValue="")String storeName
			){
		try {
			ModelAndView model = new ModelAndView("/user/fav/my-dpfav-list");
			Favorites favorites = new Favorites();
			favorites.setFavType("store");
			favorites.setMemberId(CacheUtils.getCacheUser().getMember().getMemberId());
			if(Strings.isNotEmpty(addTime)){
				// 增加查询条件
                if (addTime != null && !addTime.equals("")) {
                    if (addTime.equals("threeMonth")) {
                        favorites.setEndTime(DateUtils.getMonth(
                                DateUtils.getDateStr(new Date()), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss", -3));
                        favorites.setBeginTime(DateUtils.getDateStr(new Date()));
                    } else if (addTime.equals("oneYear")) {
                        favorites.setEndTime(DateUtils.getMonth(
                                DateUtils.getDateStr(new Date()), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss", -12));
                        favorites.setBeginTime(DateUtils.getDateStr(new Date()));
                    }
                }
			}
			if(Strings.isNotEmpty(storeName)){
				favorites.setStoreName(storeName);
			}
			// 增加查询条件
			
			Pager pager = new Pager();
			if(Strings.isNotEmpty(pageNo)){
				pager.setPageNo(Integer.valueOf(pageNo));
			}
			pager.setCondition(favorites);
			
			int total = favoritesService.countfindShopAll(pager);// 获取总条数
	        List<FavGoodsVo> orderList = favoritesService.findShopList(pager);// 结果集
	        
	        pager.setTotalRows(total);
	        pager.setResult(orderList);
	        model.addObject("pager", pager);
	        model.addObject("pageNo", pager.getPageNo());//当前页
			model.addObject("pageSize", pager.getPageSize());//每页显示条数
	        model.addObject("recordCount", total);//总数
	        model.addObject("toUrl", "/myuser/dplist");//总数
			// 尾部菜单
			List<ArticleClassTitleVo> listArticleClassTitleVo = articleClassService
					.findTitleList();
			
			model.addObject("orderLists", orderList);
			model.addObject("listArticleClassTitleVo", listArticleClassTitleVo);
			model.addObject("apm", "index");
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	
	/**
	 * 删除收藏夹数据
	 * @Title: deleteAllFav 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param favIds
	 * @param @param favType
	 * @param @return    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/deleteAllFav", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> deleteAllFav(@RequestParam(value = "favIds") String favIds,
			@RequestParam(value = "favType") String favType){
		Map<String, String> map = Maps.newHashMap();
		String memberId = CacheUtils.getCacheUser().getMember().getMemberId().toString();
		String [] favId = favIds.split(",");
		int result = 0;
		for(String str : favId){
			try {
				Favorites favorites = new Favorites();
				favorites.setFavId(Integer.valueOf(str));
				favorites.setFavType(favType);
				favorites.setMemberId(Integer.valueOf(memberId));
				result = favoritesService.deleteAllFav(favorites);
				if(result==1){
					if(favType.equals("store")){
						 //减少学院的收藏量
						 updateStorecount(str,"reduce");
					}else if(favType.equals("goods")){
						 //减少课程的收藏量
						 Goods goods=new Goods();
						 goods.setGoodsId(Integer.valueOf(str));//课程id
						 goods.setGoodsCollect(-1);//-1表示取消收藏
						 try {
							goodsService.updateGoods(goods);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} catch (NumberFormatException e) {
				result = 0;
			}
		}
		map.put("result","取消成功");
		map.put("success","true");
		return map;
	}
	
	/**
	 * 修改学院收藏的数量
	 */
	public void updateStorecount(String storeId, String flags) {
		Map<String, String> storeMap = new HashMap<String, String>();
		storeMap.put("storeId", storeId);
		storeMap.put("flags", flags);//flags是add时表示增加收藏，等于reduce时表示取消收藏
		storeService.updateStoreCount(storeMap);
	}
}
