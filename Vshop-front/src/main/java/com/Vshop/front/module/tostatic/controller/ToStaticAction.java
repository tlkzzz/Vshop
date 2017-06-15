package com.Vshop.front.module.tostatic.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Vshop.service.module.tostatic.service.ToStaticService;

/**
 * 静态化页面action
 * @author cgl
 * 2015年08月10日11:26:42
 */
@Controller
@RequestMapping("/toStatic")
public class ToStaticAction {
	
	@Autowired
	ToStaticService toStaticService;

	
	/**
	 * 首页静态化
	 * @return
	 */
	@RequestMapping("/indexStatic")
	public String staticIndex(HttpServletRequest request){
		
		try {
			toStaticService.indexToStatic();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//成功
		return "success";
	}
	
	/**
	 * 课程详细页静态化
	 * @return
	 */
	@RequestMapping("/goodsDetailStatic")
	public String goodsDetailStatic(){
		
		try {
			toStaticService.goodsDetailToStaticBatch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//成功
		return "success";
	}
	
	/**
	 * 指定课程详细页静态化
	 * @return
	 */
	@RequestMapping("/oneGoodsDetailStatic")
	public String oneGoodsDetailStatic(Integer goodsId, Integer storeId){
		
		try {
			toStaticService.goodsDetailToStaticByGoodsId(goodsId, storeId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//成功
		return "success";
	}
	
}
