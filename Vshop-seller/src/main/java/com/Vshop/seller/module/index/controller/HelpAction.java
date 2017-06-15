/**
 * 异步分页
 */
package com.Vshop.seller.module.index.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Vshop.service.module.website.service.ArticleClassService;
import com.Vshop.service.module.website.service.ArticleService;
import com.Vshop.service.utils.page.Pager;

/**
 * <p>Title: HelpAction.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月1日
 * @version 1.0
 */
@Controller
@RequestMapping("/help")
public class HelpAction {
	
	@Resource
	private ArticleClassService articleClassService;
	
	@Resource
	private ArticleService articleService;
	
	@RequestMapping("/index")
	public ModelAndView index(@RequestParam(required=false ,defaultValue="")String acId){
		ModelAndView mv = new ModelAndView("/help/index");
//		Article article = new Article();
//		if(StringUtils.isNumeric(acId)){
//			article.setAcId(Integer.valueOf(acId));
//		}
//		int total = articleService.findCount(article);
		Pager pager = new Pager();
		mv.addObject("pageNo", pager.getPageNo());//当前页
		mv.addObject("pageSize", pager.getPageSize());//每页显示条数
//		mv.addObject("recordCount", total);//总数
		mv.addObject("acId", acId);
		return mv;
	}
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(required=false , value="pageNo",defaultValue="")String pageNo,
			@RequestParam(required=false , value="acId",defaultValue="")String acId){
		ModelAndView mv = new ModelAndView("/help/list");
		Pager pager = new Pager();
		if(StringUtils.isNumeric(pageNo)){
			pager.setPageNo(Integer.valueOf(pageNo.toString()));
		}
//		Article article = new Article();
//		if(StringUtils.isNumeric(acId)){
//			article.setAcId(Integer.valueOf(acId));
//		}
//		int total = articleService.findCount(article);
		mv.addObject("toUrl", "/help/list");//提供回调的url
		mv.addObject("pageNo", pager.getPageNo());//当前页
		mv.addObject("pageSize", pager.getPageSize());//每页显示条数
//		mv.addObject("recordCount", total);//总数
		mv.addObject("acId", acId);//总数
		return mv;
	}
	
	@RequestMapping("/content")
	public ModelAndView content(@RequestParam(required=false ,defaultValue="")String acid,
			@RequestParam(required=false,defaultValue="")Integer articleId){
		ModelAndView mv = new ModelAndView("/help/content");
		
		mv.addObject("articleId", articleId);
		mv.addObject("acid", acid);
		return mv;
	}

}
