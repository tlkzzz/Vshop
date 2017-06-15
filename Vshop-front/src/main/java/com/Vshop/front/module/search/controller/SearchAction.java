package com.Vshop.front.module.search.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Vshop.core.entity.base.GoodsWords;
import com.Vshop.core.entity.base.StoreWords;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.service.module.search.service.GoodsWordsService;
import com.Vshop.service.module.search.service.StoreWordsService;

/**
 * 列表和搜索页面
 * @author cgl
 *
 */

@Controller
@RequestMapping("/search")
public class SearchAction {
	
	@Autowired
	GoodsWordsService goodsWordsService;
	
	@Autowired
	StoreWordsService storeWordsService;
	
	/**
	 * 搜索课程
	 * @param searchType 搜索课程的类型,比如:搜索课程的名字,分类,类型,学院
	 * @param keyword 关键词
	 * @param pageNo 分页的页码
	 * @param sortOrder 排序类型,desc,asc
	 * @param sortField 排序的字段
	 * @param filterConditions 过滤条件
	 * @return
	 */
	@RequestMapping("/goodsSearch")
	public ModelAndView goodsSearch(	
			@RequestParam(value="searchType",required=true, defaultValue="allSearch") String searchType,
			@RequestParam(value="keyword",required=false) String keyword,
			@RequestParam(value="pageNo",required=false)String pageNo,
			@RequestParam(value="filterConditions",required=false)String filterConditions,
			@RequestParam(value="specFilter",required=false)String specFilter,
			@RequestParam(value="sortField",required=false)String sortField,
			@RequestParam(value="sortOrder",required=false)String sortOrder
			){
		ModelAndView model = new ModelAndView("/search/goods-search");
		model.addObject("searchType", searchType);
		if(!searchType.equals("allSearch")){
			model.addObject("keyword", keyword);
		}else{
			model.addObject("keyword", "");
		}
		if(pageNo != null && !pageNo.equals("")){
			model.addObject("pageNo", pageNo);
		}
		if(filterConditions != null && !filterConditions.equals("")){
			model.addObject("filterConditions", filterConditions);
		}
		if(specFilter != null && !specFilter.equals("")){
			model.addObject("specFilter", specFilter);
		}
		if(sortField != null && !sortField.equals("")){
			model.addObject("sortField", sortField);
		}
		if(sortOrder != null && !sortOrder.equals("")){
			model.addObject("sortOrder", sortOrder);
		}
		//图片目录
		model.addObject("toUrl", "/search/goodsSearch");// 跳转URL
		return model;
	}
	
	/**
	 * 搜索课程
	 * @param keyword 关键词
	 * @param pageNo 分页的页码
	 * @param sortOrder 排序类型,desc,asc
	 * @param sortField 排序的字段
	 * @param filterConditions 过滤条件
	 * @return  storeSearchService
	 */
	@RequestMapping("/storeSearch")
	public ModelAndView storeSearch(	
			@RequestParam(value="keyword",required=false) String keyword,
			@RequestParam(value="pageNo",required=false)String pageNo,
			@RequestParam(value="filterConditions",required=false)String filterConditions,
			@RequestParam(value="sortField",required=false)String sortField,
			@RequestParam(value="sortOrder",required=false)String sortOrder
			){
		ModelAndView model = new ModelAndView("/search/store-search");
		if(pageNo != null && !pageNo.equals("")){
			model.addObject("pageNo", pageNo);
		}
		if(keyword != null && !keyword.equals("")){
			model.addObject("keyword", keyword);
		}
		if(filterConditions != null && !filterConditions.equals("")){
			model.addObject("filterConditions", filterConditions);
		}
		if(sortField != null && !sortField.equals("")){
			model.addObject("sortField", sortField);
		}
		if(sortOrder != null && !sortOrder.equals("")){
			model.addObject("sortOrder", sortOrder);
		}

		//图片目录
		model.addObject("toUrl", "/search/storeSearch");// 跳转URL
		return model;
	}
	
	/**
	 * 课程关键词的匹配搜索
	 */
	@ResponseBody
	@RequestMapping("/goodsKeywordMatch")
	public String goodsKeywordMatch(String keyword){
		
		if(StringUtils.isNotEmpty(keyword)){
		
			List<GoodsWords> list = goodsWordsService.keywordMatch(keyword);
			
			String jsonStr = JsonUtils.toJsonStr(list);
			
			return jsonStr;
			
			}
		
		return "";
	}
	
	/**
	 * 学院关键词的匹配搜索
	 */
	@ResponseBody
	@RequestMapping("/storeKeywordMatch")
	public String storeKeywordMatch(String keyword){
		
		if(StringUtils.isNotEmpty(keyword)){
			
			List<StoreWords> list = storeWordsService.keywordMatch(keyword);
			
			String jsonStr = JsonUtils.toJsonStr(list);
			
			return jsonStr;
			
		}
		
		return "";
	}
	
}
