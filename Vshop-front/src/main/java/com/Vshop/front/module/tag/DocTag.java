package com.Vshop.front.module.tag;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.core.entity.base.DocEntity;
import com.Vshop.service.module.doc.service.DocService;

import freemarker.template.TemplateModelException;

/**
 * api列表标签
 * @author LKANG
 * 2015-05-11下午9:10:23
 */
@Component
public class DocTag extends BaseFreeMarkerTag {
	
	@Autowired
	private DocService docService;
	
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		int typeid = Integer.parseInt(params.get("typeid") + "");
		int pid = Integer.parseInt(params.get("pid") + "");
		DocEntity doc = new DocEntity();
		doc.setStatus(1);
		doc.setTypeid(typeid);
		doc.setPid(pid);
		return docService.getAllDocList(doc);
	}

}
