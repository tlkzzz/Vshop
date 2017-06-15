package com.Vshop.service.module.search.service;

/**
 * 公用搜索
 * @author cgl
 */
import java.io.IOException;

import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocsCollector;

import com.Vshop.service.utils.lucene.LucenePager;

/**
 * 
 * @author cgl
 * 2015年07月06日14:46:32
 */
public interface SearchService {
	
	/**
	 * 得到query对象
	 */
	public Query getQuery(LucenePager lucenePager);
	
	/**
	 * 数据筛选
	 * @param booleanQuery
	 * @param lucenePager
	 * @return
	 */
	public BooleanQuery doFilter(BooleanQuery booleanQuery, LucenePager lucenePager);
	
	/**
	 * 判断用户是否是否选择了排序,以及排序的规则,分页等
	 */
	TopDocsCollector<?> getCollector(LucenePager lucenePager) throws NoSuchFieldException, SecurityException, IOException;

	/**
	 * 规格删选器
	 */
	public Filter getSpecFilter(LucenePager lucenePager);
}
