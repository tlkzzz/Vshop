package com.Vshop.front.module.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.Vshop.core.entity.GoodsClass;
import com.Vshop.core.entity.GoodsStoreCls;
import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.goods.service.GoodsClassService;

import freemarker.template.TemplateModelException;

/**
 * 课程分类的tag标签
 * @author LKANG
 * 添加时间：2015-06-30 12:12:00
 */
@Component
public class GoodsClassTag extends BaseFreeMarkerTag {

	@Resource
    private GoodsClassService goodsClassService;
	
	/**
	 * 课程分类列表
	 * @param parentId  父级id
	 */
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		String parendid = ParamsUtils.getString(params.get("parentid"));
		int storeId = ParamsUtils.getInt(params.get("storeid"));
		int clsLevel = ParamsUtils.getInt(params.get("clsLevel"));
		int topId = ParamsUtils.getInt(params.get("topId"));
		
		List<GoodsClass> classList = new ArrayList<GoodsClass>();
		GoodsClass goodsclass = new GoodsClass();
		goodsclass.setGcshow(1);// 是否显示1为显示0为不显示

		if (StringUtils.isNotEmpty(parendid) && !"null".equals(parendid)) {
			int parentId = Integer.parseInt(parendid);
			goodsclass.setGcParentId(parentId);

			if (storeId > 0 && parentId >= 0 && clsLevel > 0) {
				GoodsStoreCls gsc = new GoodsStoreCls();
				gsc.setGcParentId(parentId);
				gsc.setStoreId(storeId);
				gsc.setClsLevel(clsLevel);
				classList = goodsClassService.findListByStoreId(gsc);
			} else {
				classList = goodsClassService.findListbyishow(goodsclass);
			}
		} else if (topId > 0 && storeId > 0) {
			GoodsStoreCls gsc = new GoodsStoreCls();
			gsc.setStoreId(storeId);
			gsc.setTopId(topId);
			classList = goodsClassService.findLeafListByStoreId(gsc);
		} else {
			classList = goodsClassService.findAllbyisshow(goodsclass);
		}
		
		for (GoodsClass goodsClass : classList) {
			goodsClass.setDeep(goodsClass.getGcIdpath().split(",").length);
		}

		return classList;
	}

}
