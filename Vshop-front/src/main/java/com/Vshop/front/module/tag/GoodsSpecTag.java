package com.Vshop.front.module.tag;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.goods.service.GoodsService;

import freemarker.template.TemplateModelException;

/**
 * 课程规格标签
 * @author lkang
 * @version 2015-07-02 17:40:00
 */
@Component
public class GoodsSpecTag extends BaseFreeMarkerTag {

	@Resource
    private GoodsService goodsService;
	
	/**
	 * 课程规格
	 * @param goodsid 课程id
	 */
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		Integer goodId = ParamsUtils.getInt(params.get("goodsid"));
		Map<String, Object> goodsspec = goodsService.getGoodsSpec(goodId);
		return goodsspec;
	}

}
