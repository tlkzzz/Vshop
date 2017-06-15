package com.Vshop.core.freemarker;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ConfigurableApplicationContext;

import com.Vshop.core.common.SpringContextUtil;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 自定义标签创建器
 * @author LKANG
 * 2015-05-11下午9:10:23
 */
@SuppressWarnings("deprecation")
public class TagCreator implements TemplateMethodModel {

	@SuppressWarnings("rawtypes")
	public Object exec(List args) throws TemplateModelException {
		String beanid = (String) args.get(0);
		if (StringUtils.isEmpty(beanid)) {
			throw new TemplateModelException("标签beanid参数不能为空");
		}
		ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) SpringContextUtil.getApplicationContext();
		Object obj = applicationContext.getBean(beanid);
		return obj;
	}

}
