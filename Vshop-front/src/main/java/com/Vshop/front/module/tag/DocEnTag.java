package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.service.module.doc.service.DocEntityRefService;

import freemarker.template.TemplateModelException;

/**
 * api实体列表列表标签
 * @author LKANG
 * 2015-05-11下午9:10:23
 */
@Component
public class DocEnTag extends BaseFreeMarkerTag {

	@Autowired
	private DocEntityRefService docEntityRefService;
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		int docid = Integer.parseInt(params.get("docid") + "");
		// 数据结构列表
		List<Map<String, String>> refList = docEntityRefService.getAllRefList(docid);
		return refList;
	}

}
