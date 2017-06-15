package com.Vshop.front.module.tag;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.entity.base.Store;
import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.store.service.StoreService;

import freemarker.template.TemplateModelException;

@Component
public class StoreNameTag extends BaseFreeMarkerTag {

	@Resource
    private StoreService storeService;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Object exec(Map params) throws TemplateModelException {
		// TODO Auto-generated method stub
		
		int storeId = -1;
		if (params.get("storeId") != null) {
			storeId = ParamsUtils.getInt(params.get("storeId"));
		} else if (params.get("storeid") != null) {
			storeId = ParamsUtils.getInt(params.get("storeid"));
		}

		if (storeId > -1) {
			Store store = storeService.findById(storeId);
			if (store != null) {
				return store.getStoreName();
			}
		}

		return null;
	}

}
