/**
 * 
 */
package com.Vshop.front.module.tag;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.core.entity.base.Navigation;
import com.Vshop.service.module.website.service.NavigationService;

import freemarker.template.TemplateModelException;

/**
 * <p>Title: NavigationTag.java</p>
 * <p>Description: 页面导航标签</p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月1日
 * @version 1.0
 */
@Component
public class NavigationTag extends BaseFreeMarkerTag{

	@Resource
	private NavigationService navigationService;
	
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		Object navLocation = params.get("navLocation");//导航位置，0头部，1中部，2底部，默认为0
		Object navType = params.get("navType");//类别，0自定义导航，1课程分类，2文章导航，3活动导航，默认为0
		Object navItemId = params.get("navItemId");//类别ID，对应着nav_type中的内容，默认为0
		
		Navigation navigation = new Navigation();
		if(navLocation!=null){
			navigation.setNavLocation(Integer.valueOf(navLocation+""));
		}
		if(navItemId!=null){
			navigation.setNavItemId(Integer.valueOf(navItemId+""));
		}
		if(navType!=null){
			navigation.setNavType(Integer.valueOf(navType+""));
		}
		return navigationService.findAllList(navigation);
	}

}
