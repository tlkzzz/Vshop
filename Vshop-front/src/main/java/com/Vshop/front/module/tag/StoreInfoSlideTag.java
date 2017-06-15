package com.Vshop.front.module.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.Vshop.core.entity.base.Store;
import com.Vshop.core.entity.base.Upload;
import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.store.service.StoreService;

import freemarker.template.TemplateModelException;

/**
 * 学院幻灯片信息
 * @author gyh
 * 2015-09-7下午15:20:00
 */
@Component
public class StoreInfoSlideTag extends BaseFreeMarkerTag {

	
	@Resource
    private StoreService storeService;
	
	/**
	 * 获取学院的信息
	 * @param storeId 学院id
	 */
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		int storeId = ParamsUtils.getInt(params.get("storeId"));
		List<Upload> list = new ArrayList<Upload>();
		Store store = storeService.findById(storeId);
		if (StringUtils.isNotEmpty(store.getStoreSlide())) {
			String[] slide = store.getStoreSlide().split(",");
			for (int i = 0; i < slide.length; i++) {
				Upload upload = new Upload();
				upload.setFileName(slide[i]);
				list.add(upload);
			}
			if (store.getStoreSlideUrl() != null && StringUtils.isNotEmpty(store.getStoreSlideUrl())) {
				String[] urls = store.getStoreSlideUrl().split(",");
				for (int j = 0; j < slide.length; j++) {
					Upload upload = list.get(j);
					upload.setImgUrl(urls[j]);
				}
			}
		}
		return list;
	}

}
