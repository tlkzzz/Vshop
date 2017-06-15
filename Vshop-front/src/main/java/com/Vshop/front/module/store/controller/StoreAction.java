package com.Vshop.front.module.store.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Vshop.core.entity.base.Coupon;
import com.Vshop.core.entity.base.CouponMember;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.Store;
import com.Vshop.core.entity.searchbean.CouponSearch;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.coupon.service.CouponMemberService;
import com.Vshop.service.module.coupon.service.CouponService;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.service.module.store.service.EvaluateStoreService;
import com.Vshop.service.module.store.service.StoreGoodsClassService;
import com.Vshop.service.module.store.service.StoreNavigationService;
import com.Vshop.service.module.store.service.StoreService;
import com.Vshop.service.utils.page.Pager;

/**
 * @项目名称：Vshop-seller
 * @类名称：StoreAction
 * @类描述： 学院模块
 * @创建人：shining
 * @创建时间：2014年12月1日 下午10:39:53
 * @修改人：shining
 * @修改时间：2014年12月1日 下午10:39:53
 * @修改备注：
 */
@Controller
@RequestMapping("/store")
@Slf4j
public class StoreAction {
    @Resource
    private StoreService storeService;

    @Resource
    private StoreGoodsClassService storeGoodsClassService;

    @Resource
    private EvaluateStoreService evaluateStoreService;

    @Resource
    private StoreNavigationService storeNavigationService;
    
    @Resource
    private CouponMemberService couponMemberService;

    @Resource
    private GoodsService goodsService;
    
    @Resource
    private CouponService couponService;
    
    @Resource
    private MemberService memberService;

    @RequestMapping(value = "/shop")
    public ModelAndView shop(@RequestParam Integer storeId,
    	        @RequestParam(required = false, value = "storeClassId", defaultValue = "") Integer storeClassId,
    	        @RequestParam(required = false, value = "goodsName", defaultValue = "") String goodsName,
    	        @RequestParam(required = false, value = "orderField", defaultValue = "") String orderField,
    	        @RequestParam(required = false, value = "order", defaultValue = "") String order
    		) {
        ModelAndView model = new ModelAndView("/store/shop-my");
        Store store=storeService.findById(storeId);
//        if (store.getStoreId().equals(null) && store.getStoreId().equals("")) {
//       // return 
//        }
        if(store!=null){//当店铺，关闭，审核未通过，删除时跳到指定页面
      	  if(store.getStoreState()==0||store.getStoreState()==2||store.getIsDel()==1){
      		  model.addObject("store",store);
      		  model.setViewName("/store/shop-error");
      	  }
        }
        if(storeClassId==null){
      	  model.addObject("storeClassId",-1);
        }else{
      	  model.addObject("storeClassId",storeClassId);
        }
        model.addObject("storeId", storeId);
        model.addObject("goodsName", goodsName);
        model.addObject("orderField", orderField);
        model.addObject("order",order);
        model.addObject("apm", "shop");
        return model;
    }
    
    @RequestMapping(value = "/shops")
    public ModelAndView shops(
    	        @RequestParam(required = false, value = "storeClassId", defaultValue = "") Integer storeClassId,
    	        @RequestParam(required = false, value = "goodsName", defaultValue = "") String goodsName,
    	        @RequestParam(required = false, value = "orderField", defaultValue = "") String orderField,
    	        @RequestParam(required = false, value = "order", defaultValue = "") String order
    		) {
        ModelAndView model = new ModelAndView("/store/shop-my");
        Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
        Store store=storeService.findById(storeId);
//        if (store.getStoreId().equals(null) && store.getStoreId().equals("")) {
//       // return 
//        }
        if(store!=null){//当店铺，关闭，审核未通过，删除时跳到指定页面
      	  if(store.getStoreState()==0||store.getStoreState()==2||store.getIsDel()==1){
      		  model.addObject("store",store);
      		  model.setViewName("/store/shop-error");
      	  }
        }
        if(storeClassId==null){
      	  model.addObject("storeClassId",-1);
        }else{
      	  model.addObject("storeClassId",storeClassId);
        }
        model.addObject("storeId", storeId);
        model.addObject("goodsName", goodsName);
        model.addObject("orderField", orderField);
        model.addObject("order",order);
        model.addObject("apm", "shop");
        return model;
    }
    
    /**
     * @param @param  model
     * @param @param  div
     * @param @param  pageNoStr
     * @param @param  brandName
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Description: 
     */
    @RequestMapping("/shopmiddle")
    public  ModelAndView list(
            @RequestParam(required = false, value = "div", defaultValue = "") String div)
          {
        try {
        	ModelAndView model = new ModelAndView("/store/shop-middle");
            // 转发请求到FTL页面
            return model;
        } catch (Exception e) {
            log.error("导航失败!", e);
            throw new RuntimeException("导航失败!");
        }
    }
    
    /**
     * @param @param  model
     * @param @param  div
     * @param @param  pageNoStr
     * @param @param  brandName
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Description: 
     */
    @RequestMapping("/shopall")
    public  ModelAndView shopall(
            @RequestParam(required = false, value = "div", defaultValue = "") String div,
            @RequestParam(required = false, value = "storeId", defaultValue = "") String storeId,
            @RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNo,
            @RequestParam(required = false, value = "storeClassId", defaultValue = "") Integer storeClassId,
            @RequestParam(required = false, value = "goodsName", defaultValue = "") String goodsName,
            @RequestParam(required = false, value = "orderField", defaultValue = "") String orderField,
	        @RequestParam(required = false, value = "order", defaultValue = "") String order
	      ) 
       {
        try {
        	ModelAndView model = new ModelAndView("/store/shop-all");
        	Pager pager = new Pager();
        	 if (StringUtils.isNotBlank(pageNo)) {
                 pager.setPageNo(Integer.parseInt(pageNo));
             }
        	pager.setPageSize(15);
        	model.addObject("goodsName",goodsName);// 当前页
        	model.addObject("storeId", storeId);// 当前页
        	model.addObject("storeClassId",storeClassId);
        	model.addObject("pageNo",pager.getPageNo());// 当前页
            model.addObject("pageSize", pager.getPageSize());// 每页显示条数
            model.addObject("orderField", orderField);
            model.addObject("order",order);
            model.addObject("toUrl", "/store/shopall");// 跳转URL
            // 转发请求到FTL页面
            return model;
        } catch (Exception e) {
            log.error("导航失败!", e);
            throw new RuntimeException("导航失败!");
        }
    }
    
    @RequestMapping(value = "/info")
    public ModelAndView shopInfo(@RequestParam Integer storeId) {
        ModelAndView model = new ModelAndView("/store/shop-info");
        Store store=storeService.findById(storeId);
        Member member=null;
        if(store!=null&&store.getMemberId()!=null){
        	member=memberService.findById(store.getMemberId());
        }
        model.addObject("member", member);
        model.addObject("storeId", storeId);
        model.addObject("apm", "info");
        return model;
    }
    
    @RequestMapping(value = "/credit")
    public ModelAndView shopCredit(@RequestParam Integer storeId) {
        ModelAndView model = new ModelAndView("/store/shop-credit");
        model.addObject("storeId", storeId);
        model.addObject("apm", "credit");
        return model;
    }

    /**
     * 出售中的课程查询
     *
     * @param @return 设定文件
     * @return ModelAndView 返回类型
     * @throws
     * @Title: saleList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    @RequestMapping(value = "/goodList")
    public String goodList(
            @RequestParam(required = false, value = "orderBy", defaultValue = "") String orderBy,
            @RequestParam(required = false, value = "goodName", defaultValue = "") String goodName,
            @RequestParam(required = false, value = "sort", defaultValue = "") String sort,
            @RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNoStr,
            @RequestParam(required = false, value = "div", defaultValue = "") String div,
            Model model) {

        Pager pager = new Pager();
        Goods goods = new Goods();
        goods.setGoodsState(1);//上架状态的
//        goods.setGoodsVerify(1);//审核通过的
        goods.setStoreId(1);
        if (StringUtils.isNotEmpty(goodName)) {
            goods.setGoodsName(goodName);//课程名称
        }

        if (StringUtils.isNotEmpty(sort)) {
//            goods.setGoodsStcids(sort);
        }

        if (StringUtils.isNotEmpty(orderBy)) {
//            goods.setOrderby(orderBy);
        }

        pager.setPageSize(12);

        if (StringUtils.isNotBlank(pageNoStr)) {
            pager.setPageNo(Integer.parseInt(pageNoStr));
        }

        pager.setCondition(goods);// 实体加载在pager中

        List<Goods> goodList = goodsService.findGoodPagerList(pager);
        model.addAttribute("goodsDatas", goodList);// 结果集合
        model.addAttribute("pageNo", pager.getPageNo());// 当前页
        model.addAttribute("goodName", goodName);// 课程名
        model.addAttribute("orderBy", orderBy);// 排序
        model.addAttribute("pageSize", pager.getPageSize());// 每页显示条数
        model.addAttribute("recordCount", pager.getTotalRows());// 总数
        model.addAttribute("toUrl", "store/goodsList");// 跳转URL
        model.addAttribute("div", "goodsListDiv");// 显示的DIV数据区域
        return "/store/shop-my-list";
    }
    
    /**
	 * 领取优惠券
	 * @param couponClass
	 * @param model
	 * @return
	 */
	@RequestMapping("receiveCoupon")
	@ResponseBody
	public Map<String, Object> receiveCoupon(
			@RequestParam(value = "couponId") Integer couponId,
			@RequestParam(value = "storeId") Integer storeId,
			Model model
			){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			Subject currentUser = SecurityUtils.getSubject();
			if(currentUser.getPrincipal() != null){
				CouponSearch couponSearch = new CouponSearch();
				Member member = new Member();
				member = CacheUtils.getCacheUser().getMember();
				Integer memberId = member.getMemberId();
				couponSearch.setMemberId(memberId);
				couponSearch.setStoreId(storeId);
				List<CouponMember> list = couponMemberService.getCouponListByMemberIdAndStoreId(couponSearch);
				int count = couponMemberService.getCouponMemberCount(couponId);
				Coupon coupon = couponService.getCouponById(couponId);
				int couponCount = coupon.getCouponstorage();
				Long nowtime=System.currentTimeMillis();
				if(coupon!=null){
					 if(nowtime<coupon.getStartTime()){
						 map.put("success", false);
						 map.put("msg", "优惠券时间还没有开始,敬请等待！");
					 }else if(nowtime>coupon.getEndTime()){
						 map.put("success", false);
						 map.put("msg", "优惠券已过期!");
					 }else{
						 if(count > couponCount){
								map.put("success", false);
								map.put("msg", "优惠券已领取完毕!");
							}else if(list.size() > 0){
								map.put("success", false);
								map.put("msg", "已领取该优惠券,不能重复领取.");
							}else{
								CouponMember couponMember = new CouponMember();
								couponMember.setCouponId(couponId);
								couponMember.setCouponMemberId(memberId);
								couponMember.setCouponIsUser(0);
								couponMemberService.saveCouponMember(couponMember);
								map.put("success", true);
								map.put("msg", "领取成功");
							}
					 }
				}
				
			}else{
				map.put("success", false);
				map.put("msg", "请先登录");
			}
		} catch (Exception e) {
			log.error("领取优惠券领取出错", e);
			map.put("success", false);
			map.put("msg", "领取失败");
		}
		return map;
	}

}
