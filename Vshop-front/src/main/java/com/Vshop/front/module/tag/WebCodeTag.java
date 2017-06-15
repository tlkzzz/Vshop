/**
 * 
 */
package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.core.entity.base.WebCode;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.website.service.WebCodeService;

import freemarker.template.TemplateModelException;

/**
 * <p>Title: WebCodeTag.java</p>
 * <p>Description: 首页展示标签</p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月3日
 * @version 1.0
 */
@Component
public class WebCodeTag extends BaseFreeMarkerTag {
	
	@Resource
	private WebCodeService webCodeService;

	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		// 楼层列表 (floor_list) ,首页广告图(banner)
		String webCodeType = ParamsUtils.getString(params.get("webCodeType"));
		
		List<WebCode> list = webCodeService.queryAllByType(webCodeType);
		return list;
	}

}
