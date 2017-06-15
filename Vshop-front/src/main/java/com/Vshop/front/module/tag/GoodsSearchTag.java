package com.Vshop.front.module.tag;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.search.service.GoodsSearchService;
import com.Vshop.service.utils.lucene.LucenePager;

import freemarker.template.TemplateModelException;

/**
 * 课程列表页标签
 * @author lkang
 * @version 2015-07-02 17:30:00
 */
@Component
public class GoodsSearchTag extends BaseFreeMarkerTag {

	
	@Resource
    private GoodsSearchService goodsSearchService;
	
	/**
	 * 查询类型
	 * searchType:查询类型,设置为下面几个类型的时候的意义:
	 * 1. keywordSearch 关键词搜索
	 * 2. allSearch 搜索所有
	 * 3. gcIdSearch 分类查找,在keyword设置对应的id
	 * 4. typeIdSearch 类型查找,在keyword设置对应的id
	 * 5. BrandIdSearch 品牌查找,在keyword设置对应的id
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
	 * 排序字段根据 goods的实体类的属性名称而定
	 * 
	 * 规格过滤器
	 * specFilter:过滤规格
	 * 解释:传入规格值id,多个过滤以逗号分隔 比如:2,3则会过滤出 课程中 规格值id包含有2和3的
	 */
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		// 页码
		int pageNo = ParamsUtils.getInt(params.get("pageNo"));
		// 每页数量
		int pageSize = ParamsUtils.getInt(params.get("pagesize"));
		pageSize = pageSize == 0 ? 20:pageSize;
		String keyword = ParamsUtils.getString(params.get("keyword"));
		String searchType = ParamsUtils.getString(params.get("searchType"));
		String filterConditions = ParamsUtils.getString(params.get("filterConditions"));
		String sortField = ParamsUtils.getString(params.get("sortField"));
		String sortOrder = ParamsUtils.getString(params.get("sortOrder"));
		String specFilter = ParamsUtils.getString(params.get("specFilter"));
		
		//准备lucenepager
		LucenePager lucenePager = new LucenePager();
		
		//设置每页大小
		lucenePager.setPageSize(pageSize);
		
		//设置页面编号
		if(pageNo != 0){
			lucenePager.setPageNo(pageNo);
		}
		//设置关键词
		lucenePager.setKeyword(keyword);
		
		//设置查询的区域
		lucenePager.setModel("goods");
		if(searchType.equals("")){
			lucenePager.setSearchType("allSearch");
		}else{
			lucenePager.setSearchType(searchType);
		}
		
		
		System.out.println("===================="+keyword);
		
		//设置搜索的类
		lucenePager.setSearchObjClass(Goods.class);
		
		//是否过滤
		if(filterConditions != null && !filterConditions.equals("")){
			lucenePager.setFilterConditionsStr(filterConditions);
		}
		
		//规格筛选
		if(specFilter != null && !specFilter.equals("")){
			String[] specFilterStr = specFilter.split(",");
			lucenePager.setSpValueIdsFilter(specFilterStr);
		}
		
		//检查是否排序
		if(sortOrder != null && !sortOrder.trim().equals("") && sortField != null && !sortField.trim().equals("")){
			//设置排序的字段
			lucenePager.setSortField(sortField);
			//设置排序规则
			lucenePager.setSortOrder(sortOrder);
		}
		lucenePager = goodsSearchService.searchGoods(lucenePager);
		
		return lucenePager;
	}

}
