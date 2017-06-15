package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.core.entity.StoreGoodsClass;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.store.service.StoreGoodsClassService;

import freemarker.template.TemplateModelException;

/**
 * 商店自定义分类页标签
 * @author gyh
 * @version 2015-07-09 17:30:00
 */
@Component
public class StoreGoodsClassTag extends BaseFreeMarkerTag {
	@Resource
	private StoreGoodsClassService storeGoodsClassService;
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		//学院id
		int storeid=ParamsUtils.getInt(params.get("storeid"));
		//商店自定义分类里父级id
		String parentid = ParamsUtils.getString(params.get("parentId"));
		StoreGoodsClass storeGoodsClass1 = new StoreGoodsClass();
		if(storeid!=0){
			storeGoodsClass1.setStoreId(storeid);
		}
		if(parentid!=null){
			storeGoodsClass1.setStcParentId(Integer.valueOf(parentid));
		}
		storeGoodsClass1.setStcState(true);
		List<StoreGoodsClass> list=storeGoodsClassService.findListbystate(storeGoodsClass1);
		return list;
	}

}
