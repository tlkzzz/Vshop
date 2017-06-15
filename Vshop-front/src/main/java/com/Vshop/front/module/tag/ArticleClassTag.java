/**
 * 
 */
package com.Vshop.front.module.tag;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.website.service.ArticleClassService;

import freemarker.template.TemplateModelException;

/**
 * <p>Title: ArticleClassTag.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月1日
 * @version 1.0
 */
@Component
public class ArticleClassTag extends BaseFreeMarkerTag {
	
	@Resource
	private ArticleClassService articleClassService;

	@SuppressWarnings("rawtypes")
	@Override
	protected Object exec(Map params) throws TemplateModelException {
		int acId = ParamsUtils.getInt(params.get("acId"));
		//需要返回数据的类型 TagsType.java
		String tagType = ParamsUtils.getString(params.get("tagDataType"));
		
		//int pageNo = ParamsUtils.getInt(params.get("pageNo"));
		//int pageSize = ParamsUtils.getInt(params.get("pageSize"));
		if(TagsDataType.LIST.equals(tagType)){
			
			return articleClassService.findList();
		}else if(TagsDataType.PAGE_LIST.equals(tagType)){
//			Pager pager = new Pager();
//			if(pageNo!=null && pageSize!=null){
//				pager.setStart(Integer.valueOf(pageNo));
//				pager.setPageSize(Integer.valueOf(pageSize));
//			}
			return articleClassService.findListForPage();
		}else {
			return articleClassService.findById(acId);
		}
	}

}
