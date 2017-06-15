package com.Vshop.front.module.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Vshop.core.common.MyBeanUtils;
import com.Vshop.core.entity.Order;
import com.Vshop.core.entity.apibean.OrderApiBean;
import com.Vshop.core.entity.apibean.OrderPayBean;
import com.Vshop.core.entity.base.Cart;
import com.Vshop.core.entity.base.OrderPay;
import com.Vshop.core.entity.base.Payment;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.service.module.cart.service.CartService;
import com.Vshop.service.module.setting.service.PaymentService;
import com.Vshop.service.module.trade.service.OrderService;
import com.Vshop.service.utils.page.Pager;

/**
 * 订单api接口
 * @author kviuff
 * @date 2015-07-21 11:30:00
 */
@Slf4j
@Controller
@RequestMapping("/orderapi")
public class OrderApi extends BaseApi {
	
	@Resource
	private OrderService orderService;
	
	@Resource
    private CartService cartService;
	
	@Resource
	private PaymentService paymentService;

	
	/**
	 * 订单列表
	 * @param memberId 会员id
	 * @param status   状态
	 * @param pageNo   页码
	 * @param pageSize 页数
	 * @return
	 */
	@RequestMapping("orderlist")
	@ResponseBody
	public JSONObject orderList(
			@RequestParam(value = "memberId") Integer memberId,
			@RequestParam(value = "status",required=false) String status,
			@RequestParam(value = "pageNo",required=false, defaultValue="1") Integer pageNo,
			@RequestParam(value = "pageSize",required=false, defaultValue="10") Integer pageSize
			){
		jsonObj = new JSONObject();
		try {
			Order order = new Order();
			if(StringUtils.isNotEmpty(status)){
				order.setOrderState(Integer.parseInt(status));
			}
			order.setBuyerId(memberId);

			Pager pager = new Pager();
			pager.setPageNo(pageNo);
			pager.setPageSize(pageSize);
			pager.setCondition(order);
			
			List<Order> orderList = orderService.findOrderList(pager);// 结果集
			List<OrderApiBean> list = new ArrayList<OrderApiBean>();
			for(Order od:orderList){
				OrderApiBean orderApiBean = new OrderApiBean();
				MyBeanUtils.copyBeanNotNull2Bean(od, orderApiBean);
				list.add(orderApiBean);
			}
			jsonObj.put("result", 1);
			jsonObj.put("data", JSONArray.fromObject(list, JsonUtils.getJsonConfig()));
			jsonObj.put("status", status);
			jsonObj.put("pageNo", pageNo);
			jsonObj.put("pageSize", pageSize);
			
		} catch (Exception e) {
			log.error("订单列表API出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "无订单");
		}
		
		return jsonObj;
	}
	
	/**
	 * 订单详细
	 * @param orderId 订单id
	 * @return
	 */
	@RequestMapping("orderdetail")
	@ResponseBody
	public JSONObject orderDetail(
			@RequestParam(value = "orderid") Integer orderId
			){
		jsonObj = new JSONObject();
		try {
			Order order = orderService.findById(orderId);
			jsonObj.put("result", 1);
			jsonObj.put("data", JSONArray.fromObject(order, JsonUtils.getJsonConfig()));
		} catch (Exception e) {
			log.error("订单详细API出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "无订单");
		}
		return jsonObj;
	}
	
	/**
	 * 保存订单
	 * @param cartIds 需要购买的购物车的id
	 * @param addressId 收货地址id
	 * @param paytype 支付方式
	 * @param memberId 用户id
	 * @param freight 运费
	 * @param couponId 优惠券id
	 * @param invoiceId 发票id
	 * @param isPd 是否使用余额
	 * @return
	 */
	@RequestMapping("saveorder")
	@ResponseBody
	public JSONObject saveOrder(
			@RequestParam(value = "cartIds") String cartIds, 
			@RequestParam(value = "addressId") String addressId,
			@RequestParam(value = "paytype") String paytype,
			@RequestParam(value = "memberid") Integer memberId,
			@RequestParam(value = "freight",required=false,defaultValue="") String freight,
			@RequestParam(value = "couponId",required=false,defaultValue="") String couponId,
			@RequestParam(value = "invoiceId",required=false,defaultValue="") String invoiceId,
			@RequestParam(value = "isPd",required=false,defaultValue="0") Integer isPd,
			HttpServletRequest request
			){
		jsonObj = new JSONObject();
		try {
			//提交之前验证课程状态价格库存等
			Map<String, Object> validationMap = cartService.orderValidation(cartIds);
			//判断表单验证类型
			String exc = this.validation(validationMap);
			//判断是否有存在异常的课程
			if(StringUtils.isNotBlank(exc)){
				Map<String,String> map = new HashMap<String, String>();
				map.put("exc", exc);
				jsonObj.put("data", JSONArray.fromObject(map, JsonUtils.getJsonConfig()));
				jsonObj.put("result", 0);
	            jsonObj.put("msg", "课程状态异常");
	            return jsonObj;
			}
	    	
	    	Map<Integer,String> map = new HashMap<Integer, String>();
	    	//提交订单,返回订单支付实体
	    	OrderPay orderPay = orderService.addOrderReturnPaySn(cartIds, memberId, map, Integer.valueOf(addressId), paytype, freight, couponId, invoiceId, isPd);
	    	
	    	jsonObj.put("data", JSONArray.fromObject(orderPay, JsonUtils.getJsonConfig()));
			jsonObj.put("result", 1);
            jsonObj.put("msg", "保存成功");
		} catch (Exception e) {
			log.error("订单保存API出错", e);
			jsonObj.put("result", 0);
            jsonObj.put("msg", "保存失败");
		}
		return jsonObj;
	}
	
	/**
	 * 删除订单
	 * @param orderId 订单id
	 * @return
	 */
	@RequestMapping("delorder")
	@ResponseBody
	public JSONObject delOrder(
			@RequestParam(value = "orderid") Integer orderId
			){
		jsonObj = new JSONObject();
		try {
			orderService.deleteOrder(orderId);
			jsonObj.put("result", 1);
            jsonObj.put("msg", "删除成功");
		} catch (Exception e) {
			log.error("订单删除API出错", e);
			jsonObj.put("result", 0);
            jsonObj.put("msg", "删除失败");
		}
		return jsonObj;
	}
	
	/**
	 * 取消订单
	 * @param orderSn 订单编号
	 * @return
	 */
	@RequestMapping("cancleorder")
	@ResponseBody
	public JSONObject cancleOrder(
			@RequestParam(value = "ordersn") String orderSn
			){
		jsonObj = new JSONObject();
		try {
			orderService.updateCancelOrder(orderSn,"其他原因",1);
			jsonObj.put("result", 1);
            jsonObj.put("msg", "取消成功");
		} catch (Exception e) {
			log.error("订单取消API出错", e);
			jsonObj.put("result", 0);
            jsonObj.put("msg", "取消失败");
		}
		return jsonObj;
	}
	
	/**
	 * 订单确认收货
	 * @param orderSn
	 * @return
	 */
	@RequestMapping("finishorder")
	@ResponseBody
	public JSONObject finishOrder(
			@RequestParam(value = "ordersn") String orderSn
			){
		jsonObj = new JSONObject();
		try {
			orderService.updateFinishOrder(orderSn);
			jsonObj.put("result", 1);
            jsonObj.put("msg", "确认成功");
		} catch (Exception e) {
			log.error("订单确认收货API出错", e);
			jsonObj.put("result", 0);
            jsonObj.put("msg", "确认失败");
		}
		return jsonObj;
	}
	
	/**
	 * 获得支付方式列表
	 * @return
	 */
	@RequestMapping("paymentList")
	@ResponseBody
	public JSONObject paymentList(){
		jsonObj = new JSONObject();
		try {
			//获取开启状态的支付方式
			List<Payment> paymentlist = paymentService.querybystatelist("1");
			jsonObj.put("result", 1);
			jsonObj.put("data", JSONArray.fromObject(paymentlist, JsonUtils.getJsonConfig()));
            jsonObj.put("msg", "查询成功");
		} catch (Exception e) {
			log.error("订单获取支付方式API出错", e);
			jsonObj.put("result", 0);
            jsonObj.put("msg", "查询失败");
		}
		return jsonObj;
	}
	
	/**
	 * 付款成功,获取支付信息接口
	 * @param paySn
	 * @return
	 */
	@RequestMapping("getOrderPay")
	@ResponseBody
	public JSONObject getOrderPay(
			@RequestParam(value = "paySn") String paySn	
			){
		jsonObj = new JSONObject();
		try {
			//通过支付单号获取支付订单信息
			OrderPay orderPay = orderService.findOrderPayBySn(paySn);
			OrderPayBean bean = new OrderPayBean();
			MyBeanUtils.copyBeanNotNull2Bean(orderPay, bean);
			//将订单支付表订单数据清空
			//List<Order> list = new ArrayList<Order>();
			//orderPay.setOrderList(list);
			
			jsonObj.put("result", 1);
			jsonObj.put("data", JSONArray.fromObject(bean, JsonUtils.getJsonConfig()));
            jsonObj.put("msg", "查询成功");
		} catch (Exception e) {
			log.error("获取支付信息API出错", e);
			jsonObj.put("result", 0);
            jsonObj.put("msg", "查询失败");
		}
		return jsonObj;
	}
	@SuppressWarnings("unchecked")
	public String validation(Map<String,Object> validationMap){
		String str = "";
		List<Cart> list = new ArrayList<Cart>();
		
		//判断课程规格异常
		list = (List<Cart>) validationMap.get("specnotfund");
		if(list.size()!=0){
			String name = "";
			for(Cart cart:list){
				name += cart.getGoodsName() + "   ";
			}
			str = "您购买的课程:" + name +"不存在,请您重新提交订单!";
			return str;
		}
		
		//判断课程的状态
		list = (List<Cart>) validationMap.get("goodsshow");
		if(list.size()!=0){
			String name = "";
			for(Cart cart:list){
				name += cart.getGoodsName() + "   ";
			}
			str = "您购买的课程:" + name +"状态异常,请您重新提交订单!";
			return str;
		}
		
		//判断课程的库存
		list = (List<Cart>) validationMap.get("understock");
		if(list.size()!=0){
			String name = "";
			for(Cart cart:list){
				name += cart.getGoodsName() + "   ";
			}
			str = "您购买的课程:" + name +"库存量不足,请您重新提交订单!";
			return str;
		}
		
		//判断课程价格变动
		list = (List<Cart>) validationMap.get("pricechange");
		if(list.size()!=0){
			String name = "";
			for(Cart cart:list){
				name += cart.getGoodsName() + "   ";
			}
			str = "您购买的课程:" + name +"价格出现变动,请您重新提交订单!";
			return str;
		}
		
		return "";
	}
	
}
