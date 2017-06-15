package com.Vshop.admin.module.platformstore;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Vshop.core.platform.info.PlatformInfo;

/**
 * action描述:平台关于报表跳转action
 * 创建人：cgl   
 * 创建时间：2015年08月03日16:04:35
 * 平台自营
 */
@Controller
@RequestMapping("/platform/report")
public class PlatformReportAction {
	
	/**
	 * 商品流量
	 */
	@RequestMapping("/goodsClick")
	public String goodsClick(){
		Integer storeId = PlatformInfo.PLATFORM_STORE_ID;
		return "forward:/report/goodsClickIndex?storeId="+storeId;
	}
	
	/**
	 * 销售情况
	 */
	@RequestMapping("/goodsSell")
	public String goodsSell(Model model){
		Integer storeId = PlatformInfo.PLATFORM_STORE_ID;
		return "forward:/report/orderIndex?storeId="+storeId;
	}
}