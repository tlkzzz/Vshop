package com.Vshop.front.module.html5.buyer.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.Area;
import com.Vshop.core.entity.Order;
import com.Vshop.core.entity.base.Address;
import com.Vshop.core.entity.base.EvaluateGoods;
import com.Vshop.core.entity.base.EvaluateStore;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.OrderGoods;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.front.utils.CommonConstants;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.area.service.AreaService;
import com.Vshop.service.module.cart.service.AddressService;
import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.service.module.store.service.EvaluateStoreService;
import com.Vshop.service.module.trade.service.OrderGoodsService;
import com.Vshop.service.module.trade.service.OrderService;
import com.Vshop.service.module.user.service.EvaluateService;
import com.Vshop.service.utils.page.Pager;





/**
 * 项目名称：Vshop-front
 * 类名称：BuyerHtml5Action
 * 类描述：会员中心
 * 创建人：zrh
 * 创建时间：2015年8月05日 下午03:29:33
 * 修改备注：
 */
@Controller
@RequestMapping("/m/authc/buyer")
@Slf4j
public class BuyerHtml5Action {

    String message = "success";
    
	@Resource
	private AddressService addressService;
	
	@Resource
	private AreaService areaService;
	
    @Resource
    private MemberService memberService;
    
    @Resource
	private OrderService orderService;
    
    @Resource
    private OrderGoodsService orderGoodsService;

    @Resource
    private EvaluateStoreService evaluateStoreService;
    
    @Resource
    private EvaluateService evaluateService;
    
    /**
     * 学员中心页跳转
     *
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     */
    @RequestMapping(value = "/center")
    public ModelAndView center() {
        try {
            ModelAndView model = new ModelAndView("/html5/buyer/center");
            Member member = memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
            model.addObject("member", member);
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("学员中心页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }   

    /**
     * 
     * 学员中心页未付款未收货发货模块跳转
     *
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     */
    @RequestMapping(value = "/orderList")
    public ModelAndView orderList(
    		@RequestParam(value = "orderState") String orderState,
    		@RequestParam(required=false, value="pageNo",defaultValue="")String pageNo) {
        try {
        		ModelAndView model = new ModelAndView();
        		Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
        		Pager page = new Pager();
        		
        		
        		model = new ModelAndView("/html5/buyer/orderlist");
				model.addObject("orderState", orderState);
        		
        		
    			if(StringUtils.isNotEmpty(pageNo)){
    				page.setPageNo(Integer.parseInt(pageNo));
    			}
    			
    			model.addObject("member",member);
    	        model.addObject("pageNo", page.getPageNo());//当前页
    	        model.addObject("pageSize", 2);//分页条数默认为5条
    			return model;
    			
        	
           
        } catch (Exception e) {
            e.printStackTrace();
            log.error("学员中心页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }   
    /**
     * 
     * 学员中心页未付款未收货发货模块跳转
     *
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     */
    @RequestMapping(value = "/orderListPage")
    public ModelAndView orderListPage(
    		@RequestParam(value = "orderState") String orderState,
    		@RequestParam(required=false, value="pageNo",defaultValue="")String pageNo) {
        try {
        		ModelAndView model = new ModelAndView();
        		Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
        		Pager page = new Pager();
        		
        		
        		model = new ModelAndView("/html5/buyer/orderlistpage");
				model.addObject("orderState", orderState);
        		
        		
    			if(StringUtils.isNotEmpty(pageNo)){
    				page.setPageNo(Integer.parseInt(pageNo));
    			}
    			model.addObject("member",member);
    	        model.addObject("pageNo", page.getPageNo());//当前页
    	        model.addObject("pageSize", 2);//分页条数默认为2条
    			return model;
    			
        	
           
        } catch (Exception e) {
            e.printStackTrace();
            log.error("学员中心页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }   

    
    /**
     * 
     * 学员中心订单详细页跳转
     *
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     */
    @RequestMapping(value = "/orderView")
    public ModelAndView orderView(
    		@RequestParam(required = false, value = "orderId", defaultValue = "") String orderId) {
        try {
            ModelAndView model = new ModelAndView("/html5/buyer/order_view");
            model.addObject("orderId", orderId);
			return model;
           
        } catch (Exception e) {
            e.printStackTrace();
            log.error("学员中心订单详细页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }   
    
    /**
     * 
     * 学员中心订单支付页跳转
     *
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     */
    @RequestMapping(value = "/orderPayView")
    public ModelAndView orderPayView() {
        try {
            ModelAndView model = new ModelAndView("/html5/buyer/order_pay_view");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("学员中心订单详细页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }   
    
    /**
     * 
     * 学员中心查看物流页跳转
     *
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     */
    @RequestMapping(value = "/shipDetail")
    public ModelAndView shipDetail() {
        try {
            ModelAndView model = new ModelAndView("/html5/buyer/ship_detail");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("学员中心查看物流页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    } 
    
    /**
     * 
     * 服务中心页跳转
     *
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     */
    @RequestMapping(value = "/serviceCenter")
    public ModelAndView serviceCenter() {
        try {
            ModelAndView model = new ModelAndView("/html5/buyer/service_center");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("服务中心页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }   
    
    /**
     * 
     * 修改密码页面跳转
     *
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     */
    @RequestMapping(value = "/editPassword")
    public ModelAndView editPassword() {
        try {
            ModelAndView model = new ModelAndView("/html5/buyer/edit_password");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("服务中心页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }   
    
    /**
     * 
     * 地址管理页面跳转
     *
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     */
    @RequestMapping(value = "/address")
    public ModelAndView address() {
        try {
            ModelAndView model = new ModelAndView("/html5/buyer/address");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("地址管理页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }   
    
    /**
     * 
     * 退课管理页面跳转
     *
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     */
    @RequestMapping(value = "/orderReturnList")
    public ModelAndView orderReturnList() {
        try {
            ModelAndView model = new ModelAndView("/html5/buyer/order_return_list");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("地址管理页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }   
    
    /**
	 * 添加地址页面跳转
	 * 
	 * @Title: index
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
    @RequestMapping(value = "/addressAdd")
    public ModelAndView addressAdd(@RequestParam(value = "page") Integer page,
    		                       @RequestParam(value = "cartIds") String cartIds) {
        try {
        	try {
    			ModelAndView model = new ModelAndView("/html5/buyer/address_add");
    			Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
    	        model.addObject("member",member);
    			
    			List<Area> areas = areaService.queryAll();
    			model.addObject("page",page);//1:购物车页面 2.地址管理页面
    			if(page==1){
    				model.addObject("cartIds",cartIds);
    			}
    			if(page==2){
    				model.addObject("cartIds",cartIds);
    			}
    			model.addObject("areas",areas);
    			model.addObject("memberId",CacheUtils.getCacheUser().getMember().getMemberId());
    			return model;
    		} catch (Exception e) {
    			e.printStackTrace();
    			log.error("卖家中心首页加载失败！");
    			throw new RuntimeException("导航失败!");
    		}
        } catch (Exception e) {
            e.printStackTrace();
            log.error("地址添加页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }  
    
    /**
     * 
     * 生活购退款页面跳转
     *
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     */
    @RequestMapping(value = "/groupLifeRreturn")
    public ModelAndView groupLifeRreturn() {
        try {
            ModelAndView model = new ModelAndView("/html5/buyer/group_life_return");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("地址添加页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }  
    
	/**
	 *
	 * @Title: saveAddress
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jsondata
	 * @param @param model
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return Map<String,Object>    返回类型
	 * @throws
	 */
	@RequestMapping(value = "/saveAddress", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> saveAddress(@RequestParam(value = "data") String jsondata,
									@RequestParam(value = "page") Integer page,
									@RequestParam(value = "cartIds") String cartIds,
									Model model) throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		map = addressService.saveAddress(jsondata,CacheUtils.getCacheUser().getMember().getMemberId());
		map.put("page", page);
		map.put("cartIds", cartIds);
		return map;
	}
    
	/**
	 * 
	 * @Title: deleteAddress 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param ids
	 * @param @param model
	 * @param @return    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/deleteAddress", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> deleteAddress(@RequestParam(value = "id") String id,
									 @RequestParam(value = "cartIds") String cartIds,
									 Model model) {
		Map<String, String> map = Maps.newHashMap();
		addressService.deleteAddress(id);
		map.put("result", "删除成功");
		map.put("cartIds",cartIds);
		map.put("success", "true");
		return map;
	}
	
	/**
	 * 
	 * @Title: updateAddress 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return ModelAndView    返回类型 
	 * @throws
	 */
	@RequestMapping("/updateAddress")
	public ModelAndView updateAddress(@RequestParam(value = "id") String addressId,
									  @RequestParam(value = "page") Integer page,
									  @RequestParam(value = "cartIds") String cartIds) {
		try {
			ModelAndView model = new ModelAndView("/html5/buyer/address_update");
			Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
	        model.addObject("member",member);
			Address address = addressService.queryById(Integer.valueOf(addressId));
			
			Area area = areaService.queryParentList(address.getCityId());
			
			List<Area> areas = areaService.queryAll();
			model.addObject("page",page);//1:购物车页面 2.地址管理页面
			if(page==1){
				model.addObject("cartIds",cartIds);
			}
			if(page==2){
				model.addObject("cartIds",cartIds);
			}
			model.addObject("area",area);
			model.addObject("address",address);
			model.addObject("areas",areas);
			model.addObject("memberId",CacheUtils.getCacheUser().getMember().getMemberId());
			model.addObject("titleName", "收货地址");
			model.addObject("cur", "address");
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	

	/**
	 * 
	 * @Title: updateAds
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param jsondata
	 * @param @param model
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/updateAds" ,method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> updateAddress(@RequestParam(value = "data") String jsondata,
									  @RequestParam(value = "page") Integer page,
									  @RequestParam(value = "cartIds") String cartIds,
									  Model model) throws Exception {
		Map<String, String> map = Maps.newHashMap();
		int result = addressService.updateAddress(jsondata);
		if(result == 1){
			map.put("success", "true");
		}else{
			map.put("success", "false");
		}
		map.put("page", String.valueOf(page));
		map.put("cartIds", cartIds);
		return map;
	}

	
	
    /**
     * 
     * 查看返修/退换货记录页面跳转
     *
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     */
    @RequestMapping(value = "/orderReturnListlog")
    public ModelAndView orderReturnListlog() {
        try {
            ModelAndView model = new ModelAndView("/html5/buyer/order_return_listlog");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("生活购退款页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }  
    
	/**
	 * 进入取消订单首页
	 * 
	 * @Title: cancelOrderIndex
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/cancelOrderIndex")
	public ModelAndView cancelOrderIndex(
			@RequestParam(required = false, value = "orderSn", defaultValue = "") String orderSn) {
		try {
			ModelAndView model = new ModelAndView("/html5/buyer/order_cancel");
			model.addObject("orderSn", orderSn);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 取消订单
	 * 
	 * @Title: cancelOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return Map<String,Object> 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/cancelOrder")
	@ResponseBody
	public Map<String,Object> cancelOrder(
			@RequestParam(required = false, value = "orderSn", defaultValue = "") String orderSn,
			@RequestParam(required = false, value = "cancelCause", defaultValue = "") String cancelCause) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			orderService.updateCancelOrder(orderSn,cancelCause,1);
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
		return map;
	}
    
	/**
	 * 进入订单完成页
	 * 
	 * @Title: finishOrderIndex
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/finishOrderIndex")
	public ModelAndView finishOrderIndex(
			@RequestParam(required = false, value = "orderSn", defaultValue = "") String orderSn) {
		try {
			ModelAndView model = new ModelAndView("/html5/buyer/order_finish");
			model.addObject("orderSn", orderSn);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 订单完成
	 * 
	 * @Title: finishOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/finishOrder")
	@ResponseBody
	public Map<String,Object> finishOrder(
			@RequestParam(required = false, value = "orderSn", defaultValue = "") String orderSn) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			orderService.updateFinishOrder(orderSn);
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
		return map;
	}
	
	/**
	 * 进入订单退款页
	 * 
	 * @Title: refundOrderIndex
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/refundOrderIndex")
	public ModelAndView refundOrderIndex(
			@RequestParam(required = false, value = "orderId", defaultValue = "") String orderId) {
		try {
			ModelAndView model = new ModelAndView("/html5/buyer/order_refund");
			Order order = orderService.findById(Integer.valueOf(orderId));
			model.addObject("order", order);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	
	/**
	 * 订单退款
	 * 
	 * @Title: refundOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/refundOrder")
	@ResponseBody
	public Map<String,Object> refundOrder(
			@RequestParam(required = false, value = "orderId", defaultValue = "") String orderId,
			@RequestParam(required = false, value = "orderRefund", defaultValue = "") Double orderRefund,
			@RequestParam(required = false, value = "buyerMessage", defaultValue = "") String buyerMessage) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			BigDecimal.valueOf(orderRefund.doubleValue());
			orderService.addOrderRefund(Integer.valueOf(orderId), BigDecimal.valueOf(orderRefund) , buyerMessage);
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
		return map;
	}
	
	/**
	 * 进入订单退课页
	 * 
	 * @Title: returnOrderIndex
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/returnOrderIndex")
	public ModelAndView returnOrderIndex(
			@RequestParam(required = false, value = "orderId", defaultValue = "") String orderId) {
		try {
			ModelAndView model = new ModelAndView("/html5/buyer/order_return");
			Order order = orderService.findById(Integer.valueOf(orderId));	
			model.addObject("order", order);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 订单退课
	 * 
	 * @Title: returnOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/returnOrder")
	@ResponseBody
	public Map<String,Object> returnOrder(
			@RequestParam(required = false, value = "orderId", defaultValue = "") String orderId,
			@RequestParam(required = false, value = "data", defaultValue = "") String jsondata,
			@RequestParam(required = false, value = "buyerMessage", defaultValue = "") String buyerMessage,
			@RequestParam(required = false, value = "goodsIds", defaultValue = "") String goodsIds) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			orderService.addOrderReturn(Integer.valueOf(orderId), jsondata, buyerMessage, goodsIds);
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
		return map;
	}
   
	 /**
     * 支付成功后跳转
     *
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     */
    @RequestMapping(value = "/orderpaysuccess")
    public ModelAndView ordersuccess() {
        try {
            ModelAndView model = new ModelAndView("/html5/buyer/orderpay_success");
            Member member = memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
            model.addObject("member", member);
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("学员中心页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    } 
    
    
    /**
     * 
     * 余额充值页面跳转
     *
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     */
   
    @RequestMapping(value = "/predeposit")
    public ModelAndView predeposit() {
        try {
            ModelAndView model = new ModelAndView("/html5/buyer/recharge_index");
            Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
			model.addObject("member",member);
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("余额充值页面加载失败！");
            throw new RuntimeException("导航失败!");
        }
    } 
    
    
    /**
	 * 微信会员中心优惠券页面跳转
	 * 
	 * @Title: myCouponList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/couponIndex")
	public ModelAndView couponIndex(
			@RequestParam(required=false, value="couponIsUser",defaultValue="0")String couponIsUser
			){
		try {
			ModelAndView model = new ModelAndView("/html5/buyer/coupon_index");
			if("2".equals(couponIsUser)){
				// couponIsUser 2 已过期
				long time = System.currentTimeMillis();
				model.addObject("time", time);
			}
			// couponIsUser 0 未使用，1 已使用
			model.addObject("couponIsUser", couponIsUser);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("学员中心优惠券列表页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 优惠券列表页
	 * 
	 * @Title: myCouponList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/myCouponList")
	public ModelAndView myCouponList(
			@RequestParam(required=false, value="couponIsUser",defaultValue="")String couponIsUser,
			@RequestParam(required=false, value="time",defaultValue="")Long time
			){
		try {
			ModelAndView model = new ModelAndView("/html5/buyer/coupon_list");
	    	model.addObject("couponIsUser",Integer.valueOf(couponIsUser));// 是否使用
	    	model.addObject("time", time);//是否过期
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("优惠券列表页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	/**
	 * 学员课程评价页面
	 * 
	 * @Title: reviews
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param orderSn:订单编号
	 * @param recId:规格编号
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
   @RequestMapping("/reviews")
    public String reviews(Model model,
    					 @RequestParam long orderSn,
    					 @RequestParam int recId){
	   OrderGoods orderGoods = orderGoodsService.findById(recId);
       EvaluateStore evaluateStore = evaluateStoreService.findEvaluateStore(orderGoods.getStoreId());
       model.addAttribute("evaluateStore",evaluateStore);
       model.addAttribute("orderSn",orderSn);
       model.addAttribute("recId",recId);
       model.addAttribute("orderGoods",orderGoods);
       model.addAttribute("specInfo", orderGoods.getSpecInfo());//添加
       Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
       model.addAttribute("member",member);
       return "/html5/buyer/pro_reviews";
    }
   /**
	 * 学员课程评价页面上传图片
	 * 
	 * @Title: reviews
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param orderSn:订单编号
	 * @param recId:规格编号
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
   @RequestMapping(value="/imageFileUpload")
   public String imageFileUpload(@RequestParam MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response){
   	response.setContentType("text/plain; charset=UTF-8");
   	response.setContentType("text/html");
   	//可以在上传文件的同时接收其它参数
       Map<String, Object> map = Maps.newHashMap();
       //已上传评论图片数量
       String imgNo = request.getParameter("imgNo");
       //判断评论上传图片不能大于三张
   	if(myfiles.length+Integer.valueOf(imgNo)>3){
   		map.put("success",false);
   		map.put("result", "最多上传三张图片");
   		String json = JsonUtils.toJsonStr(map);
   		try {
				response.getWriter().write(json);
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
   	}
		String type = request.getParameter("type");
		
       
       String originalFilename = null;
       
       StringBuffer photoSrc = new StringBuffer();//StringBuffer用来存放上传文件的所有地址
       StringBuffer photoNewName = new StringBuffer();//用来存放图片的新名字
       StringBuffer oldName = new StringBuffer();//原来的名字
       for(MultipartFile myfile : myfiles){
           if(myfile.isEmpty()){
               map.put("result", "请选择文件后上传");
               map.put("success", false);
           }else{
               originalFilename = String.valueOf(new DateTime().getMillis())+ myfile.getOriginalFilename().substring( myfile.getOriginalFilename().indexOf("."));
              try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					log.error("",e1);
				}                
               try {
           		String realPath = CommonConstants.FILE_BASEPATH + Constants.EVALUATE_UPLOAD_URL + "/" ;
           		//这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
           		//此处也可以使用Spring提供的MultipartFile.transferTo(File dest)方法实现文件的上传
           		FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, originalFilename));
           		//上传成功的时候将图片的地址给已经准备好的Stringbuffer
           		photoSrc.append(CommonConstants.FILE_BASEPATH + Constants.EVALUATE_UPLOAD_URL+"/" + originalFilename + ",");
           		//上传成功的时候将图片的新名字给StringBuffer
           		photoNewName.append(originalFilename +  ",");
               } catch (IOException e) {
               	if("attach"==type){
               		log.error("文件[" + myfile.getOriginalFilename() + "]上传失败,堆栈轨迹如下");
               	}else{
               		log.error("文件[" + originalFilename + "]上传失败,堆栈轨迹如下");
               	}
                   map.put("result", "文件上传失败，请重试！！");
                   map.put("success", false);
                   
               }
           }
       }
       if("attach".equals(type)){
       	 map.put("oldName", oldName.toString());
       }
       map.put("imgPath",Constants.EVALUATE_UPLOAD_URL+ "/");
       map.put("photoNewName", photoNewName.toString());
       map.put("photoSrc", photoSrc.toString());
       map.put("filename", originalFilename);
		map.put("success", true);
		map.put("listimgSize", myfiles.length+"");
		String json = JsonUtils.toJsonStr(map);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
       return null;
   }
   /**
	 * 学员课程评价页面评价的保存
	 * 
	 * @Title: reviews
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param orderSn:订单编号
	 * @param recId:规格编号
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
   @RequestMapping("/saveReviews")
   public @ResponseBody Map<String,Object> saveReviews(@RequestParam long orderSn,
   													@RequestParam int recId,
   													@RequestParam String specInfo,
   													@RequestParam(value="goodsImageMore",required=false,defaultValue="") String goodsImageMore,
   													@ModelAttribute EvaluateStore evaluateStore,
   													@ModelAttribute EvaluateGoods evaluateGoods){

       Map<String,Object> map = Maps.newHashMap();
       Member member = CacheUtils.getCacheUser().getMember();
       try{
       	if(StringUtils.isNotBlank(goodsImageMore)){
       		evaluateGoods.setGevalImage(goodsImageMore);
       	}
           map = evaluateService.saveEvaluate(orderSn,recId,evaluateStore,evaluateGoods, member.getMemberId(), member.getMemberName(),specInfo);
       }catch (Exception e){
       	e.printStackTrace();
           log.error("评价失败",e.toString());
           map.put("success",false);
           map.put("msg","评论失败");
       }
       return map;
   }
}