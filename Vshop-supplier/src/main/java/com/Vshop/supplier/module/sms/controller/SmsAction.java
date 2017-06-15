package com.Vshop.supplier.module.sms.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 站内信首页
 * 项目名称：Vshop-supplier   
 * 类名称：SmsAction   
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
@RequestMapping("/sms")
@Slf4j
public class SmsAction {
	
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
			ModelAndView model = new ModelAndView("/sms/sms_index");
			model.addObject("apm", "sms");
			return model;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("站内信首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	

	
	/**
	 * 站内信
	 * @Title: email 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return ModelAndView    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/email")
	public ModelAndView email(){
		ModelAndView model = new ModelAndView("/sms/sms_email");
		return model;
	}
}