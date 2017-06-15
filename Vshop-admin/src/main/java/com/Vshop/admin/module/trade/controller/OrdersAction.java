package com.Vshop.admin.module.trade.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Vshop.core.common.Constants;
import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.Order;
import com.Vshop.core.entity.base.Payment;
import com.Vshop.service.module.setting.service.PaymentService;
import com.Vshop.service.module.trade.common.OrderState;
import com.Vshop.service.module.trade.service.OrderService;
import com.Vshop.service.utils.page.Pager;

/**
 * 店铺详情
 * <p>
 * 项目名称：Vshop-admin 类名称：StoreDetailAction 类描述： 创建人：yanghui 创建时间：2014年11月7日
 * 下午4:49:31 修改人：yanghui 修改时间：2014年11月7日 下午4:49:31 修改备注：
 */
@Controller
@RequestMapping("/orders")
@Slf4j
public class OrdersAction {

    String message = "success";

    @Resource
    private PaymentService paymentService;
    @Resource
    private OrderService orderService;

    /**
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: list
     * @Description: TODO(查询方法)
     */
    @RequestMapping("/list")
    public String list(
            Model model,
            @RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNo,
            @RequestParam(required = false, value = "searchStartTime", defaultValue = "") String startTime,
            @RequestParam(required = false, value = "searchEndTime", defaultValue = "") String endTime,
            @ModelAttribute Order order) {
    	Pager pager = new Pager();
        if (StringUtils.isNotBlank(pageNo)) {
            pager.setPageNo(Integer.parseInt(pageNo));
        }
        if(StringUtils.isNotBlank(order.getOrderSn())){
        	String orderSn = order.getOrderSn().trim();
        	order.setOrderSn(orderSn);
        }
        if (StringUtils.isNotBlank(startTime)) {
            order.setStartTime(DateUtils.strToLong(startTime));
        }
        if (StringUtils.isNotBlank(endTime)) {
            order.setEndTime(DateUtils.strToLong(endTime));
        }
        if (StringUtils.isNotBlank(pageNo)) {
            pager.setPageNo(Integer.parseInt(pageNo));
        }
        pager.setCondition(order);// 实体加载在pager中
        List<Order> results = orderService.findOrderList(pager);// 结果集
        pager.setResult(results);
        model.addAttribute("pager", pager);
        model.addAttribute("order", order);
        List<Payment> paymentList = paymentService.querybystatelist(1+"");
        model.addAttribute("datas", paymentList);// 结果集
        return "/trade/orders/ordersList";
    }

    /**
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: cancleOrder
     * @Description: TODO(取消订单)
     */
    @RequestMapping("/cancleOrder")
    public String cancleOrder(Model model,
                              @RequestParam(required = false, value = "id", defaultValue = "") String id
            , HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        Order order = new Order();
        order.setOrderId(Integer.valueOf(id));
        order.setOrderState(OrderState.ORDER_STATE_CANCLE);
        orderService.updateOrder(order);
        model.addAttribute("referer", referer);
        model.addAttribute("msg", "取消成功");
        return Constants.MSG_URL;

    }

    /**
     * 显示订单详细信息
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/show")
    public String showOrder(Model model, @RequestParam int id) {

    	Order order = orderService.findById(id);
    	model.addAttribute("order", order);
        return "trade/orders/orderDetail";
    }
    
    /**
     * 订单结算列表
     * @param 设定文件
     * @return String 返回类型
     * @throws
     * @Title: list
     * @Description: TODO(查询方法)
     */
    @RequestMapping("/balanceList")
    public String balanceList(
            Model model,
            @RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNo,
            @RequestParam(required = false, value = "startTime", defaultValue = "") String startTime,
			@RequestParam(required = false, value = "endTime", defaultValue = "") String endTime,
			@RequestParam(required = false, value = "storeName", defaultValue = "") String storeName) {
    	Pager pager = new Pager();
    	Order order = new Order();
        if (StringUtils.isNotBlank(pageNo)) {
            pager.setPageNo(Integer.parseInt(pageNo));
        }
        //店铺名称
        if(StringUtils.isNotBlank(storeName)){
        	order.setStoreName(storeName.trim());
        }
        //订单开始时间
	    if(StringUtils.isNotBlank(startTime)){
	    	order.setStartTime(DateUtils.strToLong(startTime+" 00:00:00"));
	    	model.addAttribute("startTime", startTime);
	    }
	    //订单截止时间
	    if(StringUtils.isNotBlank(endTime)){
	    	order.setEndTime(DateUtils.strToLong(endTime+" 23:59:59"));
	    	model.addAttribute("endTime", endTime);
	    }
        order.setOrderState(OrderState.ORDER_STATE_FINISH); //查询已完成的订单
        pager.setCondition(order);// 实体加载在pager中
        List<Order> results = orderService.findOrderList(pager);// 结果集
        pager.setResult(results);
        model.addAttribute("pager", pager);
        model.addAttribute("order", order);
        List<Payment> paymentList = paymentService.queryAll();
        model.addAttribute("datas", paymentList);// 结果集
        return "/trade/orders/ordersBalanceList";
    }
    
    /**
     * 订单结算
     * @param orderSn
     * @param ids
     * @return
     */
    @RequestMapping("/balance")
    @ResponseBody
    public Map<String,Object> balance(@RequestParam(required = false, value = "orderSn", defaultValue = "") String orderSn,
    								  @RequestParam(required = false, value = "ids", defaultValue = "") String ids
    								 ){
    	Map<String,Object> map = new HashMap<String, Object>();
    	try {
			if(StringUtils.isNotBlank(orderSn)){ //单个更改
				orderService.updateBalanceOrder(orderSn);
			}else if(StringUtils.isNotBlank(ids)){ //批量更改
				orderService.updateBalanceOrderByIds(ids);
			}
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}
    	return map;
    }
}