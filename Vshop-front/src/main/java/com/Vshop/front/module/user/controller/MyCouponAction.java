package com.Vshop.front.module.user.controller;



import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

import com.Vshop.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：Vshop-front   
 * 类名称：MyFavAction   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2015年3月3日 下午10:15:28   
 * 修改人：liuhao   
 * 修改时间：2015年3月3日 下午10:15:28   
 * 修改备注：   
 * @version    
 *
 */
@Controller
@RequestMapping("/user/mycoupon")
@Slf4j
public class MyCouponAction {
	/**
	 * 已使用和未使用优惠券
	 * 
	 * @Title: myCouponIndex
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/myCouponIndex")
	public ModelAndView myCouponIndex(
			@RequestParam(required=false, value="couponIsUser",defaultValue="0")String couponIsUser
			){
		try {
			ModelAndView model = new ModelAndView("/user/coupon/my-coupon-index");
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
			@RequestParam(required=false, value="div",defaultValue="")String div,
			@RequestParam(required=false, value="couponIsUser",defaultValue="")String couponIsUser,
			@RequestParam(required=false, value="time",defaultValue="")Long time
			){
		try {
			ModelAndView model = new ModelAndView("/user/coupon/my-coupon-list");
	    	model.addObject("couponIsUser",Integer.valueOf(couponIsUser));// 是否使用
	    	model.addObject("time", time);//是否过期
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	

}
