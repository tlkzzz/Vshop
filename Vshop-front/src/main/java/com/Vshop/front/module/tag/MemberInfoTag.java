package com.Vshop.front.module.tag;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.core.entity.Order;
import com.Vshop.core.entity.base.Member;
import com.Vshop.front.utils.sessionKey.CacheUser;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.trade.common.OrderState;
import com.Vshop.service.module.trade.service.OrderService;

import freemarker.template.TemplateModelException;

/**
 * 会员信息标签
 * @author kviuff
 * @date 2015-07-17 12:00:00
 */
@Component
public class MemberInfoTag extends BaseFreeMarkerTag {

	@Resource
	private OrderService orderService;
	
	/**
	 * 获取当前登录会员的信息
	 */
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		CacheUser cacheUser = CacheUtils.getCacheUser();
		Member member = cacheUser.getMember();
		
		Order order = new Order();
		order.setBuyerId(member.getMemberId());
		order.setPaymentState(OrderState.PAYMENT_STATE_NO);
		order.setOrderState(OrderState.ORDER_STATE_NO_PATMENT);
		// 待付款订单
		int payment_no = orderService.findOrderCountByOrder(order);
		
		order = new Order();
		order.setBuyerId(member.getMemberId());
		order.setOrderState(OrderState.ORDER_STATE_NOT_RECEIVING);
		// 待收货订单
		int receiving_no = orderService.findOrderCountByOrder(order);
		
		order = new Order();
		order.setBuyerId(member.getMemberId());
		order.setOrderState(OrderState.ORDER_STATE_FINISH);
		order.setEvaluationStatus(OrderState.ORDER_EVALUATION_NO);
		// 未评论订单
		int evaluation_No = orderService.findOrderCountByOrder(order);
		
		
		order = new Order();
		order.setBuyerId(member.getMemberId());
		order.setOrderState(OrderState.ORDER_STATE_UNFILLED);
		//未发货订单
		int unfilled_No = orderService.findOrderCountByOrder(order);
		
		member.setNoEvaluationOrder(evaluation_No);
		member.setNoPayOrder(payment_no);
		member.setNoReceiveOrder(receiving_no);
		member.setNoFilledOrder(unfilled_No);
		
		return member;
	}

}
