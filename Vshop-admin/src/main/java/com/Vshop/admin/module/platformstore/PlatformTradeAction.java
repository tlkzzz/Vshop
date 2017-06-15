package com.Vshop.admin.module.platformstore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Vshop.core.platform.info.PlatformInfo;

/**
 * action描述:平台关于订单的跳转action
 * 创建人：cgl   
 * 创建时间：2015年08月05日16:32:53
 * 平台自营
 */
@Controller
@RequestMapping("/platform/trade")
public class PlatformTradeAction {
	
	/**
	 * 方法描述:跳转订单评论
	 */
	@RequestMapping("/reviewIndex")
	public String reviewIndex(){
		return "forward:/trade/evalGoods/list?gevalStoreId="+PlatformInfo.PLATFORM_STORE_ID;
	}
}
