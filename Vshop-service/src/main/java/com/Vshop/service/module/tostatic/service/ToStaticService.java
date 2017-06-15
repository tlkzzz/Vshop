package com.Vshop.service.module.tostatic.service;

import java.io.IOException;

import javax.servlet.ServletException;

import freemarker.template.TemplateException;

/**
 * 静态化
 * @author cgl
 * 2015年08月10日11:18:09
 */
public interface ToStaticService {

	/**
	 * 将首页页面转化为静态页面
	 */
	void indexToStatic() throws IOException, TemplateException, ServletException ;
	
	/**
	 * 指定某一条商品详细页转为静态页面
	 */
	void goodsDetailToStaticByGoodsId(Integer goodsId, Integer storeId) throws IOException, TemplateException, ServletException ;
	
	/**
	 * 批量将商品详细页转为静态页面
	 */
	void goodsDetailToStaticBatch() throws IOException, TemplateException, ServletException ;
	
	/**
	 * 删除指定的商品静态页面
	 */
	void deleteGoodsDetailStaticPage(Integer goodsId);
	
}
