/**
 * 
 */
package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.core.entity.base.Article;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.website.service.ArticleService;
import com.Vshop.service.utils.page.Pager;

import freemarker.template.TemplateModelException;

/**
 * <p>Title: ArticleTag.java</p>
 * <p>Description: 文章标签</p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月1日
 * @version 1.0
 */
@Component
public class ArticleListTag extends BaseFreeMarkerTag {
	
	@Resource
	private ArticleService articleService;

	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		//为0是需在mapper.xml过滤掉 空的话默认为0
		int acId = ParamsUtils.getInt(params.get("acId"));//分类id
		//需要返回数据的类型 TagsDataType.java
		int pageNo = ParamsUtils.getInt(params.get("pageNo"));
		int pageSize = ParamsUtils.getInt(params.get("pageSize"));
		//需要返回数据的类型 TagsDataType.java
		String tagType = ParamsUtils.getString(params.get("tagDataType"));
		
		if(TagsDataType.PAGE_LIST.equals(tagType)){
			Pager pager = new Pager();
			
			Article article = new Article();
			article.setAcId(acId);
			pager.setCondition(article);
			
			if(pageNo != 0){
				pager.setPageNo(pageNo);
			}
			if(pageSize != 0){
				pager.setPageSize(pageSize);
			}
			
			List<Article> list = articleService.findListForPage(pager,article);
			return list;
		}else if(TagsDataType.RECORD_COUNT.equals(tagType)){
			Article article = new Article();
			article.setAcId(Integer.valueOf(acId));
			return articleService.findCount(article);
		}else{
			return null;
		}
	}

}
