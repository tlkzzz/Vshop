package com.Vshop.seller.module.tag;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.entity.Site;
import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.service.module.setting.service.SiteService;

import freemarker.template.TemplateModelException;

/**
 * 站点设置标签
 * @author zhaorh
 */
@Component
public class SiteSettingTag extends BaseFreeMarkerTag{
	
	@Resource
	private SiteService settingService;

	@SuppressWarnings("rawtypes")
	@Override
	protected Object exec(Map params) throws TemplateModelException {
		Site site = new Site();
		//获取设置的地址 
		site = settingService.findById();
		
		
		return site;
	}
}
