package com.Vshop.front.module.tag;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.goods.service.GoodsService;

import freemarker.template.TemplateModelException;

/**
 * 课程基本属性标签
 * @author lkang
 * @version 2015-07-02 18:30:00
 */
@Component
public class GoodsBaseInfoTag extends BaseFreeMarkerTag {

	@Resource
    private GoodsService goodsService;
	
	/**
	 * 课程基本属性
	 * @param goodsid 课程id
	 */
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		Goods goods = goodsService.findGoodById(ParamsUtils.getInt(params.get("goodsid")));
		if(null == goods){
			goods = new Goods();
		}
		return goods;
	}

}
