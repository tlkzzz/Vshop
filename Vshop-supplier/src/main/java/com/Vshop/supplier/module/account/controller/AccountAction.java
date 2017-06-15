package com.Vshop.supplier.module.account.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 账户设置首页
 * 项目名称：Vshop-supplier   
 * 类名称：AccountAction   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年11月25日 下午9:07:10   
 * 修改人：liuhao   
 * 修改时间：2014年11月25日 下午9:07:10   
 * 修改备注：   
 * @version    
 *
 */
@Controller
@RequestMapping("/account")
@Slf4j
public class AccountAction {
	
	String message = "success";
	
	

	/**
	 * 导航主页面跳转
	 * @Title: index 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param apm 加载的
	 * @param @return    设定文件 
	 * @return ModelAndView    返回类型 
	 * @throws RuntimeException
	 */
	@RequestMapping("/index.htm")
	public ModelAndView index(){
		try{
			ModelAndView model = new ModelAndView("/account/acc_index");
			model.addObject("apm", "account");
			return model;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("账户设置首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 账户信息
	 * @Title: accinfo 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return ModelAndView    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/accinfo")
	public ModelAndView accinfo(){
		ModelAndView model = new ModelAndView("/account/acc_info");
		return model;
	}
	/**
	 * 账户绑定
	 * @Title: accsafety 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return ModelAndView    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/accsafety")
	public ModelAndView accsafety(){
		ModelAndView model = new ModelAndView("/account/acc_safety");
		return model;
	}
	/**
	 * 收货地址
	 * @Title: accaddress 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return ModelAndView    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/accaddress")
	public ModelAndView accaddress(){
		ModelAndView model = new ModelAndView("/account/acc_address");
		return model;
	}
}