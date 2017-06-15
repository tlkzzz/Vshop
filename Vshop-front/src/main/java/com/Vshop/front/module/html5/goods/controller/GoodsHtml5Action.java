package com.Vshop.front.module.html5.goods.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.GoodsClass;
import com.Vshop.core.entity.base.Cart;
import com.Vshop.core.entity.base.Favorites;
import com.Vshop.core.entity.vo.CartVo;
import com.Vshop.front.module.html5.index.controller.IndexHtml5Action;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.cart.dao.FavoritesDao;
import com.Vshop.service.module.cart.service.CartService;
import com.Vshop.service.module.goods.service.GoodsClassService;

/**
 * 项目名称：Vshop-front
 * 类名称：CategoryHtml5Action
 * 类描述：
 * 创建人：zrh
 * 创建时间：2015年7月30日 上午11:29:35
 * 修改备注：
 */
@Controller
@RequestMapping("/m/goods")
@Slf4j
public class GoodsHtml5Action {

    String message = "success";
    
    @Resource
	private FavoritesDao favoritesDao;
    
    @Resource
    private CartService cartService;

    @Resource
    private GoodsClassService goodsClassService;
    
    /**
     * 跳转到课程列表页
     * @param searchType
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/goodslist")
    public ModelAndView goodslist(
    		@RequestParam(value="searchType",required=true, defaultValue="allSearch") String searchType,
    		@RequestParam(value="keyword",required=false) String keyword,
    		@RequestParam(value="pageNo",required=false, defaultValue = "1")String pageNo,
			@RequestParam(value="filterConditions",required=false)String filterConditions,
			@RequestParam(value="specFilter",required=false)String specFilter,
			@RequestParam(value="sortField",required=false)String sortField,
			@RequestParam(value="sortOrder",required=false)String sortOrder
			) {
        try {
            ModelAndView model = new ModelAndView("/html5/goods/goodslist");
            model.addObject("searchType", searchType);
    		if(!searchType.equals("allSearch")){
    			model.addObject("keyword", keyword);
    		}else{
    			model.addObject("keyword", "");
    		}
    		if(pageNo != null && !pageNo.equals("")){
    			model.addObject("pageNo", pageNo);
    		}
    		if(filterConditions != null && !filterConditions.equals("")){
    			model.addObject("filterConditions", filterConditions);
    		}
    		if(specFilter != null && !specFilter.equals("")){
    			model.addObject("specFilter", specFilter);
    		}
    		if(sortField != null && !sortField.equals("")){
    			model.addObject("sortField", sortField);
    		}
    		if(sortOrder != null && !sortOrder.equals("")){
    			model.addObject("sortOrder", sortOrder);
    		}
    		//图片目录
    		model.addObject("toUrl", "/search/goodsSearch");// 跳转URL
           return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("卖家中心分类列表页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }   
    
    
    /**
     * 跳转到课程列表页 根据分类查询
     * @param searchType
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/gcgoodslist")
	public ModelAndView gcgoodslist(
			@RequestParam(value = "gcId", required = true) Integer gcId,
			@RequestParam(value = "pcId", required = true) Integer pcId,
			@RequestParam(value = "gcType", required = false, defaultValue = "2") Integer gcType,
			@RequestParam(value = "mcId", required = false, defaultValue = "0") Integer mcId,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNo,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "sortField", required = false) String sortField,
			@RequestParam(value = "sortOrder", required = false) String sortOrder) {
    	
		try {
			ModelAndView model = new ModelAndView("/html5/goods/gcgoodslist");
			String storeid = IndexHtml5Action.SELF_SUPPORT_STORE_ID;
			try {
				if (CacheUtils.getCacheUser().getStore() != null)
					storeid = CacheUtils.getCacheUser().getStore().getStoreId() + "";
			} catch (Exception e) {

			}
			
			List<Integer> gcIdList = new ArrayList<Integer>();
			if(gcId > 0) {
				gcIdList.add(gcId);
			}
			if(mcId > 0 && !gcIdList.contains(mcId)) {
				gcIdList.add(mcId);
			}
			if(pcId > 0 && !gcIdList.contains(pcId)) {
				gcIdList.add(pcId);
			}
			
			if (keyword != null && keyword.trim().length() > 0) {
				model.addObject("titleStr", keyword.trim());
			} else {
				StringBuffer titleStr = new StringBuffer();
				List<GoodsClass> gcList = goodsClassService.findByList(gcIdList);
				Map<Integer, String> gcMap = new HashMap<Integer, String>();

				if (gcList != null && !gcList.isEmpty()) {
					for (GoodsClass gclass : gcList) {
						if (gclass != null && gclass.getGcName() != null && !gcMap.containsKey(gclass.getGcId())) {
							gcMap.put(gclass.getGcId(), gclass.getGcName());
						}
					}
				}

				if (gcMap.containsKey(pcId)) {
					titleStr.append(gcMap.get(pcId));
				}
				if (gcMap.containsKey(mcId) && !mcId.equals(pcId)) {
					titleStr.append(".").append(gcMap.get(mcId));
				} else if (gcMap.containsKey(gcId) && !pcId.equals(gcId) && !mcId.equals(gcId)) {
					titleStr.append(".");
				}
				if (gcMap.containsKey(gcId) && !pcId.equals(gcId) && !mcId.equals(gcId)) {
					titleStr.append(".").append(gcMap.get(gcId));
				}

				model.addObject("titleStr", titleStr);
			}

			if (pcId.equals(gcId)) {
				gcType = 0;
			}
			
			model.addObject("storeid", storeid);
			model.addObject("gcId", gcId);
			model.addObject("pcId", pcId);
			model.addObject("gcType", gcType);
			model.addObject("mcId", mcId);

			if (pageNo != null && !pageNo.equals("")) {
				model.addObject("pageNo", pageNo);
			}

			if ("DESC".equalsIgnoreCase(sortOrder)) {
				sortOrder = "ASC";
			} else {
				sortOrder = "DESC";
			}

			if (sortField != null && !sortField.equals("")) {
				model.addObject("sortField", sortField);
			}
			if (sortOrder != null && !sortOrder.equals("")) {
				model.addObject("sortOrder", sortOrder);
			}
			if (keyword != null && !keyword.equals("")) {
				model.addObject("keyword", keyword);
			}
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("学院分类列表页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
    
    /**
     * 跳转到课程列表页 根据分类查询
     * @param searchType
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/gcgoodslistdata")
	public ModelAndView gcgoodslistdata(@RequestParam(value = "gcId", required = false) Integer gcId,
			@RequestParam(value = "pcId", required = false) Integer pcId,
			@RequestParam(value = "gcType", required = false, defaultValue = "2") Integer gcType,
			@RequestParam(value = "mcId", required = false, defaultValue = "0") Integer mcId,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNo,
			@RequestParam(value = "sortField", required = false) String sortField,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "sortOrder", required = false) String sortOrder) {

		try {
			ModelAndView model = new ModelAndView("/html5/goods/gcgoodslistdata");
			String storeid = IndexHtml5Action.SELF_SUPPORT_STORE_ID;
			try {
				if (CacheUtils.getCacheUser().getStore() != null)
					storeid = CacheUtils.getCacheUser().getStore().getStoreId() + "";
			} catch (Exception e) {

			}

			if (pcId.equals(gcId)) {
				gcType = 0;
			}

			model.addObject("storeid", storeid);
			model.addObject("gcId", gcId);
			model.addObject("pcId", pcId);
			model.addObject("gcType", gcType);
			model.addObject("mcId", mcId);
			
			if (keyword != null && !keyword.equals("")) {
				model.addObject("keyword", keyword);
			}
			
			if (pageNo != null && !pageNo.equals("")) {
				model.addObject("pageNo", pageNo);
			}

			if ("ASC".equalsIgnoreCase(sortOrder)) {
				sortOrder = "ASC";
			} else {
				sortOrder = "DESC";
			}

			if (sortField != null && !sortField.equals("")) {
				model.addObject("sortField", sortField);
			}
			if (sortOrder != null && !sortOrder.equals("")) {
				model.addObject("sortOrder", sortOrder);
			}

			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("学院分类列表页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
    
    /**
     * 跳转到课程列表页 新品上市
     * @param searchType
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/newgoodslist")
	public ModelAndView newgoodslist(
			@RequestParam(required = false, value = "reCommendId") String reCommendId) {
		try {
			ModelAndView model = new ModelAndView("/html5/goods/newgoodslist");
			String storeid = IndexHtml5Action.SELF_SUPPORT_STORE_ID;
			try {
				if (CacheUtils.getCacheUser().getStore() != null)
					storeid = CacheUtils.getCacheUser().getStore().getStoreId() + "";
			} catch (Exception e) {

			}
			model.addObject("storeid", storeid);
			model.addObject("reCommendId", reCommendId);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("学院分类列表页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
    
    
    
    /**
     * 跳转到课程列表页  老师力荐
     * @param searchType
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/recommendgoodslist")
	public ModelAndView recommendgoodslist(
			@RequestParam(required = false, value = "reCommendId") String reCommendId) {
		try {
			ModelAndView model = new ModelAndView("/html5/goods/recommendgoodslist");
			String storeid = IndexHtml5Action.SELF_SUPPORT_STORE_ID;
			try {
				if (CacheUtils.getCacheUser().getStore() != null)
					storeid = CacheUtils.getCacheUser().getStore().getStoreId() + "";
			} catch (Exception e) {

			}
			model.addObject("storeid", storeid);
			model.addObject("reCommendId", reCommendId);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("学院分类列表页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
    
    
    
    /**
     * 课程详细页跳转
     *
     * @param @param  apm 加载的
     * @param @return 设定文件
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     * @Title: index
     * @Description: TODO(课程详情页加载)
     */
    @RequestMapping(value = "/goodsdetail")
    public ModelAndView goodsdetail(@RequestParam(value = "id") Integer goodsId,
    								HttpSession session) {
        try { 
        	int result = 0;//没有收藏该课程
        	int isLogin = 0;//用户未登录
            ModelAndView model = new ModelAndView("/html5/goods/goods_detail");
            Subject currentUser = SecurityUtils.getSubject();
            if(currentUser.isAuthenticated()){
            	if(CacheUtils.getCacheUser() != null){
            		if(null!=CacheUtils.getCacheUser().getMember()){
            			//用户已经登陆
            			isLogin = 1;
            			model.addObject("isLogin", isLogin);
            			//是否收藏开始
            			int buyerId = CacheUtils.getCacheUser().getMember().getMemberId();
            			Favorites favorites = new Favorites();
            			favorites.setFavId(goodsId);
            			favorites.setMemberId(buyerId);
            			favorites.setFavType("goods");//收藏课程
            			List<Favorites> list = favoritesDao.queryFavById(favorites);
            			if(list!=null && list.size()>0){
            				result =1;//已经收藏过该课程
            			}
            			//是否收藏结束
            			//获取数据库表中购物车课程数量开始
            			List<Cart> cartList = cartService.queryBuyCart(CacheUtils.getCacheUser().getMember().getMemberId());
            			model.addObject("cartNum", cartService.getCartVoByCart(cartList));
            			//获取数据库表中购物车课程数量结束
            		}
            		
            	}
            }else{
            	//用户未登录,通过session获取购物车课程数量
            	model.addObject("isLogin", isLogin);
            	//若未登录,在session中获取
    	        CartVo cartVo = (CartVo) session.getAttribute(Constants.CART_KEY);
    	        if(cartVo!=null){
    	        	model.addObject("cartNum", cartService.getCartVoByCart(cartVo.getList()));
    	        }else{
    	        	CartVo vo = new CartVo();
    	        	vo.setGoodsNum(0);
    	        	model.addObject("cartNum", vo);
    	        }
            }
            model.addObject("isCollection", result);
            model.addObject("goodsId", goodsId);
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("课程详情加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }   
    
    /**
     * 课程介绍页面跳转
     *
     * @param @param  apm 加载的
     * @param @return 设定文件
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     * @Title: index
     * @Description: TODO(课程详情页加载)
     */
    @RequestMapping(value = "/goodsIntroduce")
    public ModelAndView goodsIntroduce(@RequestParam(value = "goodsId") Integer goodsId,
    								   @RequestParam(value = "isShow") Integer isShow) {
        try {
            ModelAndView model = new ModelAndView("/html5/goods/goods_introduce");
            model.addObject("goodsId", goodsId);
            model.addObject("isShow", isShow);// 1:显示  0:不显示
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("课程详情加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }  
    
    /**
     * 课程列表分页
     * @param searchType
     * @param keyword
     * @param pageNo
     * @param filterConditions
     * @param specFilter
     * @param sortField
     * @param sortOrder
     * @return
     */
    @RequestMapping(value="goodsListPage")
    public ModelAndView goodsListPage(
    		@RequestParam(value="searchType",required=true, defaultValue="allSearch") String searchType,
    		@RequestParam(value="keyword",required=false) String keyword,
    		@RequestParam(value="pageNo",required=false)String pageNo,
			@RequestParam(value="filterConditions",required=false)String filterConditions,
			@RequestParam(value="specFilter",required=false)String specFilter,
			@RequestParam(value="sortField",required=false)String sortField,
			@RequestParam(value="sortOrder",required=false)String sortOrder
    		){
    	  try {
    		  	ModelAndView model = new ModelAndView("/html5/goods/goodslistpage");
    	    	model.addObject("searchType", searchType);
    	    	model.addObject("keyword", keyword);
    	    	model.addObject("pageNo", Integer.parseInt(pageNo));
    	    	model.addObject("filterConditions", filterConditions);
    	    	model.addObject("specFilter", specFilter);
    	    	model.addObject("sortField", sortField);
    	    	model.addObject("sortOrder", sortOrder);
    	    	return model;
          } catch (Exception e) {
              e.printStackTrace();
              log.error("卖家中心分类列表页加载失败！");
              throw new RuntimeException("导航失败!");
          }
    	
    }
    
    /**
     * @param @param  goodsId
     * @param @param  pageNo
     * @param @return 设定文件
     * @return ModelAndView 返回类型
     * @throws
     * @Title: evaluateGoods
     * @Description: 课程评价，需要翻页，所以单独提出来
     */
    @RequestMapping("/goodsEvaluteList")
    public ModelAndView evaluateGoods(
            @RequestParam(value = "goodsId") String goodsId,
            @RequestParam(required = false, value = "pageNo", defaultValue = "1") String pageNo,
            @RequestParam(value = "isShow",required=false,defaultValue="") String isShow) {
    	ModelAndView model = new ModelAndView("/html5/goods/goods-evaluate-list");
    	try {
            model.addObject("goodsId", goodsId);
            model.addObject("pageNo", pageNo);//当前页
            model.addObject("toUrl", "/product/evaluateGoods");// 跳转URL
            if(StringUtils.isNotEmpty(isShow)){
    			model.addObject("isShow", isShow);
    		}
            return model;
        } catch (Exception e) {
            log.error("课程评价页加载失败！",e);
            throw new RuntimeException("导航失败!");
        }
    }
    
    
    
    /**
     * 课程评价分页
     * @param searchType
     * @param keyword
     * @param pageNo
     * @param filterConditions
     * @param specFilter
     * @param sortField
     * @param sortOrder
     * @return
     */
    @RequestMapping(value="goodsEvalutePage")
    public ModelAndView goodsEvalutePage(
    		@RequestParam(value="goodsId",required=true) String goodsId,
    		@RequestParam(value="pageNo",required=false) String pageNo
    		){
    	try {
    		ModelAndView model = new ModelAndView("/html5/goods/goods-evaluate-page");
    		model.addObject("goodsId", goodsId);
    		model.addObject("pageNo", pageNo);
    		return model;
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("卖家中心分类列表页加载失败！");
    		throw new RuntimeException("导航失败!");
    	}
    	
    }
    
    /**
     * 课程评价分页
     * @param searchType
     * @param keyword
     * @param pageNo
     * @param filterConditions
     * @param specFilter
     * @param sortField
     * @param sortOrder
     * @return
     */
    @RequestMapping(value="goodsConsultList")
    public ModelAndView goodsConsultList(
    		@RequestParam(value="goodsId",required=true) String goodsId
    		){
    	try {
    		ModelAndView model = new ModelAndView("/html5/goods/goods-consult-list");
    		model.addObject("goodsId", goodsId);
    		return model;
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("卖家中心分类列表页加载失败！");
    		throw new RuntimeException("导航失败!");
    	}
    	
    }
    
    /**
     * 课程评价分页
     * @param searchType
     * @param keyword
     * @param pageNo
     * @param filterConditions
     * @param specFilter
     * @param sortField
     * @param sortOrder
     * @return
     */
    @RequestMapping(value="goodsConsultPage")
    public ModelAndView goodsConsultPage(
    		@RequestParam(value="goodsId",required=true) String goodsId,
    		@RequestParam(value="pageNo",required=false) String pageNo
    		){
    	try {
    		ModelAndView model = new ModelAndView("/html5/goods/goods-consult-page");
    		model.addObject("goodsId", goodsId);
    		model.addObject("pageNo", pageNo);
    		return model;
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("卖家中心分类列表页加载失败！");
    		throw new RuntimeException("导航失败!");
    	}
    	
    }

}