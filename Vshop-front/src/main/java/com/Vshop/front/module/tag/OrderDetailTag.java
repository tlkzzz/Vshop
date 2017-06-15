package com.Vshop.front.module.tag;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.core.entity.Order;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.trade.service.OrderService;

import freemarker.template.TemplateModelException;

/**
 * 订单详细页标签
 * @author kviuff
 * @date 2015-07-17 16:30:00
 */
@Component
public class OrderDetailTag extends BaseFreeMarkerTag {

	@Resource
	private OrderService orderService;
	
	/**
	 * 订单详细标签
	 * @param orderId 订单id
	 */
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		int orderId = ParamsUtils.getInt(params.get("orderId"));
		Integer buyerId = CacheUtils.getCacheUser().getMember().getMemberId();
		Order order = orderService.findOrderDetail(Integer.valueOf(orderId),buyerId,null);
		return order;
	}

}
