package com.Vshop.core.freemarker;

import freemarker.core.Environment;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import org.springframework.web.servlet.support.RequestContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.springframework.web.servlet.view.AbstractTemplateView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE;

/**
 * Freemarker标签工具类
 */
public abstract class DirectiveUtils {

	/**
	 * 将params的值复制到variable中
	 * 
	 * @param env
	 * @param params
	 * @return 原Variable中的值
	 * @throws freemarker.template.TemplateException
	 */
	public static Map<String, TemplateModel> addParamsToVariable(
			Environment env, Map<String, TemplateModel> params)
			throws TemplateException {
		Map<String, TemplateModel> origMap = new HashMap<String, TemplateModel>();
		if (params.size() <= 0) {
			return origMap;
		}
		Set<Map.Entry<String, TemplateModel>> entrySet = params.entrySet();
		String key;
		TemplateModel value;
		for (Map.Entry<String, TemplateModel> entry : entrySet) {
			key = entry.getKey();
			value = env.getVariable(key);
			if (value != null) {
				origMap.put(key, value);
			}
			env.setVariable(key, entry.getValue());
		}
		return origMap;
	}

	/**
	 * 将variable中的params值移除
	 *
	 * @param env
	 * @param params
	 * @param origMap
	 * @throws freemarker.template.TemplateException
	 */
	public static void removeParamsFromVariable(Environment env,
			Map<String, TemplateModel> params,
			Map<String, TemplateModel> origMap) throws TemplateException {
		if (params.size() <= 0) {
			return;
		}
		for (String key : params.keySet()) {
			env.setVariable(key, origMap.get(key));
		}
	}

	/**
	 * 获得RequestContext
	 *
	 * ViewResolver中的exposeSpringMacroHelpers必须为true
	 *
	 * @param env
	 * @return
	 * @throws freemarker.template.TemplateException
	 */
	public static RequestContext getContext(Environment env)
			throws TemplateException {
		TemplateModel ctx = env
				.getGlobalVariable(SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE);
		if (ctx instanceof AdapterTemplateModel) {
			return (RequestContext) ((AdapterTemplateModel) ctx)
					.getAdaptedObject(RequestContext.class);
		} else {
			throw new TemplateModelException("RequestContext '"
					+ SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE
					+ "' not found in DataModel.");
		}
	}
}
