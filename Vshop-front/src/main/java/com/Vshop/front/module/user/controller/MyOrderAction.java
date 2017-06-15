package com.Vshop.front.module.user.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.Order;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.RefundLog;
import com.Vshop.core.entity.base.ReturnOrder;
import com.Vshop.core.entity.vo.ReturnOrderVo;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.service.module.trade.common.OrderState;
import com.Vshop.service.module.trade.service.OrderService;
import com.Vshop.service.module.trade.service.RefundLogService;
import com.Vshop.service.module.trade.service.ReturnOrderService;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * 项目名称：Vshop-front 类名称：MyOrderAction 类描述： 创建人：weiyue 创建时间：2014年12月25日
 * 下午9:34:55 修改人：liuhao 修改时间：2014年12月25日 下午9:34:55 修改备注：
 * @version
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class MyOrderAction {

	@Resource
	private OrderService orderService;
	
    @Resource
    private MemberService memberService;
    
    @Resource
    private RefundLogService refundLogService;
    
    @Resource
    private ReturnOrderService returnOrderService;
	
	/**
	 * 导航主页面跳转,跳转到个人商家首页页面
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
			ModelAndView model = new ModelAndView("/user/order/my-order");
			Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
	        model.addObject("member",member);
	        model.addObject("apm", "index");
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！",e.toString());
			throw new RuntimeException("导航失败!");
		}
	}

	/**
	 * 订单购物车列表页面
	 * 
	 * @Title: list
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/list")
	public ModelAndView List(
			@RequestParam(required=false, value="pageNo",defaultValue="")String pageNo,
            @RequestParam(required=false, value="orderSn",defaultValue="")String orderSn,
			@RequestParam(required = false, value = "startTime", defaultValue = "") String startTime,
			@RequestParam(required = false, value = "endTime", defaultValue = "") String endTime,
			@RequestParam(required = false, value = "orderState", defaultValue = "") String orderState,
			@RequestParam(required = false, value = "evaluationStatus", defaultValue = "") String evaluationStatus) {
		try {
			ModelAndView model = new ModelAndView("/user/order/my-order-list");
			Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
			Pager page = new Pager();
			if(StringUtils.isNotEmpty(pageNo)){
				page.setPageNo(Integer.parseInt(pageNo));
			}
			if(StringUtils.isNotEmpty(evaluationStatus)){
				orderState = OrderState.ORDER_STATE_FINISH+""; //若查询订单评价状态,就是查询已完成订单
			}
			
			model.addObject("member",member);
	        model.addObject("pageNo", page.getPageNo());//当前页
	        model.addObject("pageSize", 20);//分页条数默认为20条
	        model.addObject("toUrl", "/user/list");//跳转页面
	        model.addObject("orderState", orderState);
	        model.addObject("evaluationStatus", evaluationStatus);
	        model.addObject("orderSn", orderSn);
	        model.addObject("startTime", startTime);
	        model.addObject("endTime", endTime);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}

	/**
	 * 订单详情页面
	 * @Title: detail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/detail")
	public ModelAndView detail(
			@RequestParam(required = false, value = "orderId", defaultValue = "") String orderId) {
		try {
			ModelAndView model = new ModelAndView("/user/order/my-order-detail");
			model.addObject("orderId", orderId);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
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
			ModelAndView model = new ModelAndView("/user/order/my-order-cancel");
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
			@RequestParam(required = false, value = "cancelCause", defaultValue = "") String cancelCause
			) {
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
			ModelAndView model = new ModelAndView("/user/order/my-order-finish");
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
			ModelAndView model = new ModelAndView("/user/order/my-order-refund");
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
	 * 退款查询页面
	 * 
	 * @Title: refundLogDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/refundLogDetail")
	public ModelAndView refundLogDetail(@RequestParam(value = "logId") Integer logId) {
		try {
			ModelAndView model = new ModelAndView("/user/order/my-order-refund-detail");
			RefundLog refundLog = refundLogService.findRefundLogByLogId(logId);
			model.addObject("refundLog", refundLog);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	
	/**
	 * 退款查询列表
	 * 
	 * @Title: refundList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/refundList")
	public ModelAndView refundList(@RequestParam(required = false, value = "type", defaultValue = "") String type,
								   @RequestParam(required = false, value = "key", defaultValue = "") String key,
								   @RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNo,
								   @RequestParam(required = false, value = "startTime", defaultValue = "") String startTime,
								   @RequestParam(required = false, value = "endTime", defaultValue = "") String endTime) {
		try {
			ModelAndView model = new ModelAndView("/user/order/my-order-refund-list");
			Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
	        model.addObject("member",member);
			RefundLog refundLog = new RefundLog();
			refundLog.setBuyerId(CacheUtils.getCacheUser().getMember().getMemberId());
			
			if(StringUtils.isNotBlank(key)){
				if("orderSn".equals(type)){
					refundLog.setOrderSn(key.trim());
					model.addObject("key", key);
				}else if("refundSn".equals(type)){
					refundLog.setRefundSn(key.trim());
					model.addObject("key", key);
				}else if("storeName".equals(type)){
					refundLog.setStoreName(key.trim());
					model.addObject("key", key);
				}
			}
			
			if(StringUtils.isNotBlank(startTime)){
				refundLog.setStartTime(DateUtils.strToLong(startTime+" 00:00:00"));
				model.addObject("startTime", startTime);
			}
			
			if(StringUtils.isNotBlank(endTime)){
				refundLog.setEndTime(DateUtils.strToLong(endTime+" 23:59:59"));
				model.addObject("endTime", endTime);
			}
			
			Pager pager = new Pager();
			if(StringUtils.isNotEmpty(pageNo)){
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager.setPageSize(5);
			pager.setCondition(refundLog);
			
	        List<RefundLog> refundLogList = refundLogService.findRefundLogList(pager);// 结果集
	        pager.setResult(refundLogList);
	        model.addObject("type",type); 
	        model.addObject("key",key);
	        model.addObject("pager", pager);
	        model.addObject("list", refundLogList); //结果集
	        model.addObject("pageNo", pager.getPageNo());//当前页
			model.addObject("pageSize", 5);//每页显示条数
	        model.addObject("recordCount", pager.getTotalRows());//总数
	        model.addObject("toUrl", "/user/refundList");//总数
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
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
			ModelAndView model = new ModelAndView("/user/order/my-order-return");
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
	 * 退课查询列表
	 * 
	 * @Title: returnList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/returnList")
	public ModelAndView returnList(@RequestParam(required = false, value = "type", defaultValue = "") String type,
								   @RequestParam(required = false, value = "key", defaultValue = "") String key,
								   @RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNo,
								   @RequestParam(required = false, value = "startTime", defaultValue = "") String startTime,
								   @RequestParam(required = false, value = "endTime", defaultValue = "") String endTime) {
		try {
			ModelAndView model = new ModelAndView("/user/order/my-order-return-goods-list");
			Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
	        model.addObject("member",member);
			ReturnOrder returnOrder = new ReturnOrder();
			returnOrder.setBuyerId(CacheUtils.getCacheUser().getMember().getMemberId());
			
			if(StringUtils.isNotBlank(key)){
				if("orderSn".equals(type)){
					returnOrder.setOrderSn(key.trim());
					model.addObject("key", key);
				}else if("returnSn".equals(type)){
					returnOrder.setReturnSn(key.trim());
					model.addObject("key", key);
				}else if("storeName".equals(type)){
					returnOrder.setStoreName(key.trim());
					model.addObject("key", key);
				}
			}
			
			if(StringUtils.isNotBlank(startTime)){
				returnOrder.setStartTime(DateUtils.strToLong(startTime));
				model.addObject("startTime", startTime);
			}
			
			if(StringUtils.isNotBlank(endTime)){
				returnOrder.setEndTime(DateUtils.strToLong(endTime));
				model.addObject("endTime", endTime);
			}
			
			Pager pager = new Pager();
			if(StringUtils.isNotEmpty(pageNo)){
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager.setPageSize(5);
			pager.setCondition(returnOrder);
			
	        List<ReturnOrder> returnOrderList = returnOrderService.findReturnOrderList(pager);// 结果集
	        pager.setResult(returnOrderList);
	        model.addObject("type",type); 
	        model.addObject("key",key);
	        model.addObject("pager", pager);
	        model.addObject("list", returnOrderList); //结果集
	        model.addObject("pageNo", pager.getPageNo());//当前页
			model.addObject("pageSize", 5);//每页显示条数
	        model.addObject("recordCount", pager.getTotalRows());//总数
	        model.addObject("toUrl", "/user/returnList");//总数
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 退款查询页面
	 * 
	 * @Title: returnOrderDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/returnOrderDetail")
	public ModelAndView returnOrderDetail(@RequestParam(value = "returnId") Integer returnId) {
		try {
			ModelAndView model = new ModelAndView("/user/order/my-order-return-detail");
			ReturnOrderVo returnOrderVo = returnOrderService.findById(returnId);
			model.addObject("returnOrder", returnOrderVo);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
}
