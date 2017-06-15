package com.Vshop.front.module.tag;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.base.Store;
import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.store.service.StoreService;

import freemarker.template.TemplateModelException;

/**
 * 学院基本信息
 * @author LKANG
 * 2015-07-10下午15:20:00
 */
@Component
public class StoreInfoTag extends BaseFreeMarkerTag {

	
	@Resource
    private StoreService storeService;
	
	/**
	 * 获取学院的信息
	 * @param storeId 学院id
	 */
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		int storeId = ParamsUtils.getInt(params.get("storeId"));
		Store store = storeService.findById(storeId);
		if(store!=null){
			if(StringUtils.isNotEmpty(store.getStoreSlide())){
				String[] slides = store.getStoreSlide().split(",");
				String storeSlide = "";
				for (String string : slides) {
					if(StringUtils.isNotEmpty(string)){
						storeSlide += string + ",";
					}
				}
				store.setStoreSlide(storeSlide.substring(0, storeSlide.length() -1));
			}
		}
		
		 if(store!=null){
			 if(store.getStoreLogintime()!=null){
				 store.setStoreLastLogintimestr((DateUtils.getTimestampByLong(store.getStoreLogintime())));
			 }
		 }
		return store;
	}

}
