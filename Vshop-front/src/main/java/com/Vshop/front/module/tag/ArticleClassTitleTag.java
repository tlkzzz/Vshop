/**
 * 
 */
package com.Vshop.front.module.tag;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.service.module.website.service.ArticleClassService;

import freemarker.template.TemplateModelException;

/**
 * <p>Title: ArticleClassTitleTag.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2019</p>
 * <p>Company: Vshop</p>
 * @author linjm
 * @date 2015年6月30日
 * @version 1.0
 */
@Component
public class ArticleClassTitleTag  extends BaseFreeMarkerTag{

	@Resource
	private ArticleClassService articleClassService;
	
	/*(non-Javadoc)
	 * @see com.Vshop.core.freemarker.BaseFreeMarkerTag#exec(java.util.Map)
	 */
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
//		Object level = params.get("level");//分类级别  
//		Object acId =params.get("acId");//分类id
		return articleClassService.findTitleList();
	}

}
