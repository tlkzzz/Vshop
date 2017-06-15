package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.Order;
import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.trade.service.OrderService;
import com.Vshop.service.utils.page.Pager;

import freemarker.template.TemplateModelException;

/**
 * 订单标签
 * @author liukai
 */
@Component
public class OrderTag extends BaseFreeMarkerTag {
	
	@Resource
	private OrderService orderService;

	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		
		//获取订单编号
		String orderSn = ParamsUtils.getString(params.get("orderSn"));
		//获取订单开始时间
		String startTime = ParamsUtils.getString(params.get("startTime"));
		//获取订单截止时间
		String endTime = ParamsUtils.getString(params.get("endTime"));
		//获取订单状态
		String orderState = ParamsUtils.getString(params.get("orderState"));
		//返回类型,1:返回分页总数,2:返回分页列表
		String returnDataType = ParamsUtils.getString(params.get("returnDataType"));
		//订单评价状态
		String evaluationStatus = ParamsUtils.getString(params.get("evaluationStatus"));
		
		int pageSize = ParamsUtils.getInt(params.get("pageSize"));//分页条数
		int pageNo = ParamsUtils.getInt(params.get("pageNo"));//当前页数
		if(pageNo == 0){
			pageNo = 1;
		}
		
		Order order = new Order();
		order.setBuyerId(CacheUtils.getCacheUser().getMember().getMemberId());
		
		//订单状态
		if (StringUtils.isNotBlank(orderState) && !"99".equals(orderState)) {
			order.setOrderState(Integer.valueOf(orderState));
		}
		
		//订单评价状态
		if(StringUtils.isNotBlank(evaluationStatus)){
	        order.setEvaluationStatus(Integer.valueOf(evaluationStatus));
	    }
		
		//订单编号
	    if(StringUtils.isNotBlank(orderSn)){
	        order.setOrderSn(orderSn.trim());
	    }
	    
	    //订单开始时间
	    if(StringUtils.isNotBlank(startTime)){
	    	order.setStartTime(DateUtils.strToLong(startTime+" 00:00:00"));
	    }
	    
	    //订单截止时间
	    if(StringUtils.isNotBlank(endTime)){
	    	order.setEndTime(DateUtils.strToLong(endTime+" 23:59:59"));
	    }
	    
	    //构造pager，每页显示5条
		Pager pager = new Pager();
		pager.setPageSize(pageSize); //分页条数
		pager.setPageNo(pageNo);
		pager.setCondition(order);
		
		if("1".equals(returnDataType)){
			int total = orderService.findOrderCount(order);// 获取总条数
			return total;
		}else if("2".equals(returnDataType)){
			List<Order> orderList = orderService.findOrderList(pager);// 结果集
			return orderList;
		}else{ //不传递返回类型,返回订单集合
			List<Order> orderList = orderService.findOrderList(pager);// 结果集
			return orderList;
		}
	}
	
}
