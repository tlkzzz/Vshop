package com.Vshop.service.utils.lucene;

import java.util.List;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.vo.SpecVo;
import com.Vshop.service.utils.page.Pager;

@Data
@ToString
public class LucenePager extends Pager{
	
	/**
	 * 排序字段
	 */
	private String sortField;
	
	/**
	 * 排序规则
	 */
	private String sortOrder;
	
	/**
	 * 当前搜索对应的类
	 */
	private Class<?> searchObjClass;
	
	/**
	 * json筛选条件
	 */
	private String filterConditionsStr;
	
	/**
	 * 规格删选条件
	 */
	private String[] spValueIdsFilter;
	
	/**
	 * 关键词
	 */
	private String keyword;
	
	/**
	 * 搜索的区域(商品搜索,店铺搜索)
	 */
	private String model;
	
	/**
	 * 需要搜索的字段
	 */
	private String searchType;
	
	/**
	 * 搜索结果所对应的规格
	 */
	private List<SpecVo> specList;
	
	/**
	 * 搜索结果所对应的品牌
	 */
	private List<Brand> brandList;
}
