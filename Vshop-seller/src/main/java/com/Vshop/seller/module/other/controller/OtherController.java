package com.Vshop.seller.module.other.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@Controller
@RequestMapping("/other")
public class OtherController {
	/**
	 * 金币管理
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/storegbuy")
	public ModelAndView storenavigationadd(){
		try{
			ModelAndView model = new ModelAndView("/other/store_gbuy");
			return model;
		}catch (Exception e){
			e.printStackTrace();
			log.error("金币管理加载失败！");
			throw new RuntimeException("金币管理加载失败!");
		}
	}
	/**
	 * 金币日志管理
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/goldlog")
	public ModelAndView goldlog(){
		try{
			ModelAndView model = new ModelAndView("/other/gold_log");
			return model;
		}catch (Exception e){
			e.printStackTrace();
			log.error("金币日志管理加载失败！");
			throw new RuntimeException("金币日志管理加载失败!");
		}
	}
	
	/**
	 * 我的相册管理
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/albumcate")
	public ModelAndView albumcate(){
		try{
			ModelAndView model = new ModelAndView("/other/store_album");
			return model;
		}catch (Exception e){
			e.printStackTrace();
			log.error("我的相册管理加载失败！");
			throw new RuntimeException("我的相册管理加载失败!");
		}
	}
	
	/**
	 * 水印管理
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/storewatermark")
	public ModelAndView storewatermark(){
		try{
			ModelAndView model = new ModelAndView("/other/store_watermark");
			return model;
		}catch (Exception e){
			e.printStackTrace();
			log.error("水印管理加载失败！");
			throw new RuntimeException("水印管理加载失败!");
		}
	}
}
