package com.Vshop.seller.module.trade.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.Order;
import com.Vshop.core.entity.base.EvaluateGoods;
import com.Vshop.core.entity.base.EvaluateStore;
import com.Vshop.core.entity.base.RefundLog;
import com.Vshop.core.entity.base.ReturnOrder;
import com.Vshop.core.entity.base.Store;
import com.Vshop.core.entity.vo.ReturnOrderVo;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.seller.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.store.service.EvaluateStoreService;
import com.Vshop.service.module.trade.service.EvaluateGoodsService;
import com.Vshop.service.module.trade.service.OrderService;
import com.Vshop.service.module.trade.service.RefundLogService;
import com.Vshop.service.module.trade.service.ReturnOrderService;
import com.Vshop.service.utils.page.Pager;

/**
 * 交易管理首页
 *      
 * 项目名称：Vshop-seller   
 * 类名称：TradeAction   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年11月25日 下午9:15:07   
 * 修改人：liuhao   
 * 修改时间：2014年11月25日 下午9:15:07   
 * 修改备注：   
 * @version    
 *
 */
@Controller
@RequestMapping("/trade")
@Slf4j
public class TradeAction {
	
	String message = "success";
	@Resource
	private OrderService orderService;
	@Resource
	private EvaluateGoodsService evaluateGoodsService;
	@Resource 
	private RefundLogService refundLogService;
	@Resource
	private ReturnOrderService returnOrderService;
	@Resource
    private EvaluateStoreService evaluateStoreService;
	
	/**
	 * 订单管理
	 * @Title: list 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return ModelAndView    返回类型 
	 */
	@RequestMapping(value = "/orderList")
	public String orderlist(
			Model model,
			@RequestParam(required=false, value="pageNo",defaultValue="")String pageNoStr,
			@RequestParam(required=false, value="buyerName",defaultValue="")String buyerName,
			@RequestParam(required=false, value="orderSn",defaultValue="")String orderSn,
			@RequestParam(required=false, value="startTime",defaultValue="")String startTime,
			@RequestParam(required=false, value="endTime",defaultValue="")String endTime,
			@RequestParam(required=false, value="orderState",defaultValue="99")String orderState){
		try {
			Pager pager = new Pager();
			Order order = new Order();
			/**查询条件，放入实体中，**/
            
			if(StringUtils.isNotBlank(startTime)){
				order.setStartTime(DateUtils.strToLong(startTime));
				model.addAttribute("startTime", startTime);
			}
			
			if(StringUtils.isNotBlank(endTime)){
				order.setEndTime(DateUtils.strToLong(endTime));
				model.addAttribute("endTime", endTime);
			}
			
			if(StringUtils.isNotBlank(buyerName)){
				order.setBuyerName(buyerName);
				model.addAttribute("buyerName", buyerName);
			}
			
			if(StringUtils.isNotBlank(orderSn)){
				order.setOrderSn(orderSn.trim());
				model.addAttribute("orderSn", orderSn.trim());
			}
			
			if(StringUtils.isNotBlank(orderState)&&!"99".equals(orderState)){
				order.setOrderState(Integer.valueOf(orderState));
			}
			
			if(StringUtils.isNotBlank(pageNoStr)){
				pager.setPageNo(Integer.parseInt(pageNoStr));
			}

            order.setStoreId(CacheUtils.getCacheUser().getStore().getStoreId());
			pager.setCondition(order);//实体加载在pager中
			pager.setPageSize(20);//每页默认显示20条

			List<Order> results = orderService.findOrderList(pager);// 结果集
			
			//List<Express> epresslist = orderService.findExpressList();
			
			model.addAttribute("orderLists", results);// 结果集
			model.addAttribute("pageNo", pager.getPageNo());// 当前页
			model.addAttribute("pageSize", pager.getPageSize());// 每页显示条数
			model.addAttribute("recordCount", pager.getTotalRows());// 总数
            model.addAttribute("toUrl","/trade/orderList");
            model.addAttribute("orderState",orderState);
            //model.addAttribute("epresslist", epresslist);// 快递公司
			//log.error(JsonUtils.toJsonStr(results));
			return "/trade/trade_order";
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 订单详情
	 * @param model
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/orderDetail")
	public String orderDetail(
			Model model,
			@RequestParam(required=false, value="orderId",defaultValue="")String orderId){
		try {
			Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
			Order results = orderService.findOrderDetail(Integer.valueOf(orderId),null,storeId); //结果集
			model.addAttribute("order", results);// 结果集
			return "/trade/trade_order_detail";
		} catch (Exception e) {
            log.error("订单详情导航失败",e.toString());
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
			ModelAndView model = new ModelAndView("/trade/trade_cancel");
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
	 * @param model
	 * @param orderSn 订单编号
	 * @return
	 */
	@RequestMapping("/cancelOrder")
	public @ResponseBody Map<String, Object> cancleOrder(
			Model model,
			@RequestParam(required = false, value = "orderSn", defaultValue = "") String orderSn,
			@RequestParam(required = false, value = "cancelCause", defaultValue = "") String cancelCause) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			orderService.updateCancelOrder(orderSn,cancelCause,2);
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
	 * 进入调整费用首页
	 * 
	 * @Title: cancelOrderIndex
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/updateAmountIndex")
	public ModelAndView updateAmountIndex(
			@RequestParam(required = false, value = "orderId", defaultValue = "") String orderId) {
		try {
			ModelAndView model = new ModelAndView("/trade/trade_update_amount");
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
	 * 调整订单费用
	 * @param model
	 * @param orderId 订单id
	 * @param orderAmount 调整后的价格,订单总额
	 * @return
	 */
	@RequestMapping("/updateAmount")
	public @ResponseBody Map<String, Object> updateAmount(
			Model model,
			@RequestParam(required = false, value = "orderId", defaultValue = "") String orderId,
			@RequestParam(required = false, value = "orderAmount", defaultValue = "") String orderAmount
			) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			Order order = new Order();
			order.setOrderId(Integer.valueOf(orderId));
			order.setOrderAmount(BigDecimal.valueOf(Double.valueOf(orderAmount)));
			orderService.updateOrder(order);
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
	 * 进入确认订单首页(货到付款确认)
	 * 
	 * @Title: cancelOrderIndex
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/confirmOrderIndex")
	public ModelAndView confirmOrderIndex(
			@RequestParam(required = false, value = "orderId", defaultValue = "") String orderId) {
		try {
			ModelAndView model = new ModelAndView("/trade/trade_confirm_order");
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
	 * 确认订单(货到付款确认订单)
	 * @param model
	 * @param orderId 订单id
	 * @param orderAmount 调整后的价格,订单总额
	 * @return
	 */
	@RequestMapping("/confirmOrder")
	public @ResponseBody Map<String, Object> confirmOrder(
			Model model,
			@RequestParam(required = false, value = "orderSn", defaultValue = "") String orderSn
			) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			orderService.updateConfirmOrder(orderSn);
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
	 * @Title: cancelOrderIndex
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/refundOrderIndex")
	public ModelAndView refundOrderIndex(
			@RequestParam(required = false, value = "logId", defaultValue = "") String logId) {
		try {
			ModelAndView model = new ModelAndView("/trade/trade_order_refund");
			RefundLog refundLog = refundLogService.findRefundLogByLogId(Integer.valueOf(logId));
			model.addObject("refundLog", refundLog);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 订单退款
	 * @param model
	 * @param orderId 订单id
	 * @param orderAmount 调整后的价格,订单总额
	 * @return
	 */
	@RequestMapping("/refundOrder")
	public @ResponseBody Map<String, Object> refundOrder(
			Model model,
			@RequestParam(required = false, value = "logId", defaultValue = "") String logId,
			@RequestParam(required = false, value = "refundState", defaultValue = "") String refundState,
			@RequestParam(required = false, value = "refundMessage", defaultValue = "") String refundMessage) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			refundLogService.updateRefundLogSeller(Integer.valueOf(logId), Integer.valueOf(refundState), refundMessage, System.currentTimeMillis());
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
	 * 进入退款记录页
	 * 
	 * @Title: cancelOrderIndex
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/refundOrderList")
	public ModelAndView refundOrderList(@RequestParam(required = false, value = "type", defaultValue = "") String type,
			   						    @RequestParam(required = false, value = "key", defaultValue = "") String key,
			   						    @RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNo,
			   						    @RequestParam(required = false, value = "startTime", defaultValue = "") String startTime,
			   						    @RequestParam(required = false, value = "endTime", defaultValue = "") String endTime) {
		try {
			ModelAndView model = new ModelAndView("/trade/trade_refund_list");
			RefundLog refundLog = new RefundLog();
			refundLog.setStoreId(CacheUtils.getCacheUser().getStore().getStoreId());
			if(StringUtils.isNotBlank(key)){
				if("orderSn".equals(type)){
					refundLog.setOrderSn(key);
					model.addObject("key", key);
				}else if("refundSn".equals(type)){
					refundLog.setRefundSn(key);
					model.addObject("key", key);
				}else if("buyerName".equals(type)){
					refundLog.setBuyerName(key);
					model.addObject("key", key);
				}
			}
			
			if(StringUtils.isNotBlank(startTime)){
				refundLog.setStartTime(DateUtils.strToLong(startTime+" 00:00:00"));
				model.addObject("startTime", startTime);
			}
			
			if(StringUtils.isNotBlank(endTime)){
				refundLog.setStartTime(DateUtils.strToLong(endTime+" 23:59:59"));
				model.addObject("endTime", endTime);
			}
			
			Pager pager = new Pager();
			if(StringUtils.isNotBlank(pageNo)){
				pager.setPageNo(Integer.valueOf(pageNo));
			}
			pager.setPageSize(5);
			pager.setCondition(refundLog);
			
	        List<RefundLog> refundLogList = refundLogService.findRefundLogList(pager);// 结果集
	        pager.setResult(refundLogList);
	        model.addObject("pager", pager);
	        model.addObject("list", refundLogList); //结果集
	        model.addObject("pageNo", pager.getPageNo());//当前页
			model.addObject("pageSize", 5);//每页显示条数
	        model.addObject("recordCount", pager.getTotalRows());//总数
	        model.addObject("toUrl", "/trade/refundOrderList");//总数
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
	 * @Title: refundIndex
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/refundLogDetail")
	public ModelAndView refundIndex(@RequestParam(value = "logId") Integer logId) {
		try {
			ModelAndView model = new ModelAndView("/trade/trade_refund_detail");
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
	 * 进入订单退货页
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
			@RequestParam(required = false, value = "returnId", defaultValue = "") String returnId) {
		try {
			ModelAndView model = new ModelAndView("/trade/trade_order_return");
			ReturnOrderVo returnOrderVo = returnOrderService.findById(Integer.valueOf(returnId));
			model.addObject("returnOrder", returnOrderVo);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 订单退款
	 * @param model
	 * @param orderId 订单id
	 * @param orderAmount 调整后的价格,订单总额
	 * @return
	 */
	@RequestMapping("/returnOrder")
	public @ResponseBody Map<String, Object> returnOrder(
			Model model,
			@RequestParam(required = false, value = "returnId", defaultValue = "") String returnId,
			@RequestParam(required = false, value = "returnState", defaultValue = "") String returnState,
			@RequestParam(required = false, value = "returnMessage", defaultValue = "") String returnMessage) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			returnOrderService.updateReturnOrderSeller(Integer.valueOf(returnId), Integer.valueOf(returnState), returnMessage);
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
	 * 退货查询页面
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
			ModelAndView model = new ModelAndView("/trade/trade_return_detail");
			ReturnOrderVo returnOrderVo = returnOrderService.findById(returnId);
			model.addObject("returnOrder", returnOrderVo);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 进入退货记录页
	 * 
	 * @Title: returnOrderList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/returnOrderList")
	public ModelAndView returnOrderList(@RequestParam(required = false, value = "type", defaultValue = "") String type,
			   						    @RequestParam(required = false, value = "key", defaultValue = "") String key,
			   						    @RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNo,
			   						    @RequestParam(required = false, value = "startTime", defaultValue = "") String startTime,
			   						    @RequestParam(required = false, value = "endTime", defaultValue = "") String endTime) {
		try {
			ModelAndView model = new ModelAndView("/trade/trade_return_list");
			ReturnOrder returnOrder = new ReturnOrder();
			returnOrder.setStoreId(CacheUtils.getCacheUser().getStore().getStoreId());
			if(StringUtils.isNotBlank(key)){
				if("orderSn".equals(type)){
					returnOrder.setOrderSn(key);
					model.addObject("key", key);
				}else if("returnSn".equals(type)){
					returnOrder.setReturnSn(key);
					model.addObject("key", key);
				}else if("buyerName".equals(type)){
					returnOrder.setBuyerName(key);
					model.addObject("key", key);
				}
			}
			
			if(StringUtils.isNotBlank(startTime)){
				returnOrder.setStartTime(DateUtils.strToLong(startTime+" 00:00:00"));
				model.addObject("startTime", startTime);
			}
			
			if(StringUtils.isNotBlank(endTime)){
				returnOrder.setStartTime(DateUtils.strToLong(endTime+" 23:59:59"));
				model.addObject("endTime", endTime);
			}
			
			Pager pager = new Pager();
			if(StringUtils.isNotBlank(pageNo)){
				pager.setPageNo(Integer.valueOf(pageNo));
			}
			pager.setPageSize(5);
			pager.setCondition(returnOrder);
			
	        List<ReturnOrder> returnOrderList = returnOrderService.findReturnOrderList(pager);// 结果集
	        pager.setResult(returnOrderList);
	        model.addObject("pager", pager);
	        model.addObject("list", returnOrderList); //结果集
	        model.addObject("pageNo", pager.getPageNo());//当前页
			model.addObject("pageSize", 5);//每页显示条数
	        model.addObject("recordCount", pager.getTotalRows());//总数
	        model.addObject("toUrl", "/trade/returnOrderList");//总数
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 评价管理
	 * @Title: main 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return ModelAndView    返回类型 
	 */
//	@RequestMapping("/review")
//	public ModelAndView review(){
//		try{
//			ModelAndView model = new ModelAndView("/trade/trade_review");
//			model.addObject("apm", "trade");
//			return model;
//		}catch (Exception e) {
//			e.printStackTrace();
//			log.error("评价管理管理首页加载失败！");
//			throw new RuntimeException("导航失败!");
//			
//		}
//	}
	
	/**
	 * 评价管理
	 * @Title: list 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return ModelAndView    返回类型 
	 */
	@RequestMapping(value = "/review")
	public String review(
			Model model){
		try {
			Store store = CacheUtils.getCacheUser().getStore();
			EvaluateStore evaluateStore = evaluateStoreService.findEvaluateStore(store.getStoreId());
			model.addAttribute("evaluateStore", evaluateStore);
			model.addAttribute("store", store);
			return "/trade/trade_review";
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 评价管理
	 * @Title: list 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return ModelAndView    返回类型 
	 */
	@RequestMapping(value = "/reviewList")
	public String reviewlist(
			Model model,
			@RequestParam(required=false, value="pageNo",defaultValue="")String pageNoStr,
			@RequestParam(required=false, value="startDate",defaultValue="")String startDate,
			@RequestParam(required=false, value="stype",defaultValue="")String stype,
			@RequestParam(required=false, value="svalue",defaultValue="")String svalue,
			@RequestParam(required=false, value="gevalscore",defaultValue="")String gevalscore,
			@RequestParam(required=false, value="havecontent",defaultValue="")String havecontent){
		try {
			Pager pager = new Pager();
			EvaluateGoods evaluateGoods = new EvaluateGoods();
			
			/**时间格式*/
			String sourcePattern = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			/**当前时间*/
			String dateString = format.format(new Date());
			
			/**查询条件，放入实体中，**/
			if(startDate.equals("1") || StringUtils.isBlank(startDate)){
				evaluateGoods.setStartTime(DateUtils.strToLong(DateUtils.getMonth(dateString, sourcePattern, sourcePattern, -3)));
				evaluateGoods.setEndTime(System.currentTimeMillis());
			}else if(startDate.equals("2")){
				evaluateGoods.setStartTime(DateUtils.strToLong(DateUtils.getMonth(dateString, sourcePattern, sourcePattern, -12)));
				evaluateGoods.setEndTime(DateUtils.strToLong(DateUtils.getMonth(dateString, sourcePattern, sourcePattern, -3)));
			}
			if(StringUtils.isNotBlank(stype)&&StringUtils.isNotBlank(svalue))
				if(stype.equals("1")){
					evaluateGoods.setGevalGoodsName(svalue);
				}else if(stype.equals("2")){
					evaluateGoods.setGevalFrommembername(svalue);
				}
			
			if(StringUtils.isNotBlank(pageNoStr)){
				pager.setPageNo(Integer.parseInt(pageNoStr));
			}
			evaluateGoods.setGevalStoreId(CacheUtils.getCacheUser().getStore().getStoreId());
			//设置评分等级条件
			if(StringUtils.isNotEmpty(gevalscore) && !"0".equals(gevalscore)){
				evaluateGoods.setGevalScore(Integer.parseInt(gevalscore));
			}
			//设置有无评分内容
			
			//有评分内容
			if(StringUtils.isNotEmpty(havecontent) && "1".equals(havecontent)){
				evaluateGoods.setHaveContent(Integer.parseInt(havecontent));
			}
			//无评分内容
			if(StringUtils.isNotEmpty(havecontent) && "2".equals(havecontent)){
				evaluateGoods.setHaveContent(Integer.parseInt(havecontent));
			}
			pager.setCondition(evaluateGoods);//实体加载在pager中
			
			List<EvaluateGoods> results = evaluateGoodsService.findPageList(pager);// 结果集
			
			model.addAttribute("datas", results);// 结果集
			model.addAttribute("pageNo", pager.getPageNo());// 当前页
			model.addAttribute("pageSize", pager.getPageSize());// 每页显示条数
			model.addAttribute("recordCount", pager.getTotalRows());// 总数
			model.addAttribute("pageCount", pager.getTotalRows());// 总数
			model.addAttribute("toUrl","/trade/reviewList");
			log.error(JsonUtils.toJsonStr(results));
			return "/trade/trade_review_list";
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("导航失败!");
		}
	}
	
	@RequestMapping("/reply")
	public @ResponseBody Map<String, String> reply(
			Model model,
			@RequestParam(required = false, value = "gevalId", defaultValue = "") String gevalId,
			@RequestParam(required = false, value = "gevalExplain", defaultValue = "") String gevalExplain) {
		Map<String, String> map = Maps.newHashMap();
		try {
			EvaluateGoods goods = new EvaluateGoods();
			goods.setGevalId(Integer.parseInt(gevalId));
			goods.setGevalExplain(gevalExplain);
			goods.setUpdateTime(System.currentTimeMillis());
			evaluateGoodsService.update(goods);
			map.put(message, "true");
		} catch (Exception e) {
			e.printStackTrace();
			map.put(message, "false");
			map.put("msg", "获取对象为空！");
		}
		return map;
	}
	
	
	
	/**
	 * 支付方式
	 * @Title: main 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return ModelAndView    返回类型 
	 */
	@RequestMapping("/payment")
	public ModelAndView payment(){
		try{
			ModelAndView model = new ModelAndView("/trade/trade_payment");
			return model;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("支付方式加载失败！");
			throw new RuntimeException("导航失败!");
			
		}
	}
	
	
}