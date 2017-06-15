package com.Vshop.front.module.tag;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Vshop.core.entity.base.Store;
import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.search.service.StoreSearchService;
import com.Vshop.service.utils.lucene.LucenePager;

import freemarker.template.TemplateModelException;

/**
 * 课程列表页标签
 * @author lkang
 * @version 2015-07-02 17:30:00
 */
@Component
public class StoreSearchTag extends BaseFreeMarkerTag {

	
	@Autowired
	StoreSearchService storeSearchService;
	
	/**
	 * 
	 * 过滤字段
	 * filterConditions:过滤
	 * 解释:这是一个json的String 结构为:[{filterName:provinceId,conditionData:1}]
	 * 	filterName后面的值是 需要过滤的字段,conditionData后面的值是 过滤条件,
	 * 	这是一个过滤多条件过滤,传多个实体则过滤多个
	 * 	但是目前只支持 地区的过滤,及:[{filterName:provinceId,conditionData:1}]
	 * 
	 * 排序字段
	 * sortField,sortOrder
	 * 解释:sortField:需要排序的字段 sortOrder:asc,desc 顺序,逆序
	 * 比如 sortField:salenum  sortOder:asc 则是销售量的排序
	 * 排序字段根据 store的实体类的属性名称而定
	 */
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		// 页码
		int pageNo = ParamsUtils.getInt(params.get("pageNo"));
		// 每页数量
		int pageSize = ParamsUtils.getInt(params.get("pagesize"));
		pageSize = pageSize == 0 ? 20:pageSize;
		String keyword = ParamsUtils.getString(params.get("keyword"));
		String filterConditions = ParamsUtils.getString(params.get("filterConditions"));
		String sortField = ParamsUtils.getString(params.get("sortField"));
		String sortOrder = ParamsUtils.getString(params.get("sortOrder"));
		
		//准备lucenepager
		LucenePager lucenePager = new LucenePager();
		
		//设置查询的区域
		lucenePager.setModel("store");
		
		//设置每页大小
		lucenePager.setPageSize(pageSize);
		
		//设置页面编号
		if(pageNo != 0){
			lucenePager.setPageNo(pageNo);
		}
		//设置关键词
		lucenePager.setKeyword(keyword);
		
		//设置搜索的类
		lucenePager.setSearchObjClass(Store.class);
		
		//是否过滤
		if(filterConditions != null && !filterConditions.equals("")){
			lucenePager.setFilterConditionsStr(filterConditions);
		}
		
		//检查是否排序
		if(sortOrder != null && !sortOrder.trim().equals("") && sortField != null && !sortField.trim().equals("")){
			//设置排序的字段
			lucenePager.setSortField(sortField);
			//设置排序规则
			lucenePager.setSortOrder(sortOrder);
		}
		
		lucenePager = storeSearchService.searchStore(lucenePager);
		
		return lucenePager;
	}

}
