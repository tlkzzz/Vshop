package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.core.entity.base.GoodsActivityRel;
import com.Vshop.core.entity.base.GoodsRecommend;
import com.Vshop.core.entity.base.RelGoodsRecommend;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.goods.service.GoodsActivityRelService;
import com.Vshop.service.module.goods.service.GoodsActivityService;
import com.Vshop.service.module.goods.service.GoodsRecommendService;
import com.Vshop.service.module.goods.service.RelGoodsRecommendService;

import freemarker.template.TemplateModelException;

/**
 * 推荐课程,最新上市,猜你喜欢课程的tag标签
 * @author LKANG
 * 添加时间：2015-08-25 12:12:00
 */
@Component
public class ActivityGoodsTag extends BaseFreeMarkerTag {

	@Resource
	private GoodsActivityService goodsActivityService;
	
	@Resource
	private GoodsActivityRelService relGoodsActivityService;
	
	/**
	 * 课程分类列表
	 * @param parentId  父级id
	 */
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		String activity_type = ParamsUtils.getString(params.get("activity_type"));
		String activity_id = ParamsUtils.getString(params.get("activity_id"));
		
		List<GoodsActivityRel> relGoodsActivitylist=relGoodsActivityService.findStoregoodsList(java.lang.Integer.valueOf(activity_id), java.lang.Integer.valueOf(activity_type));
		
		return relGoodsActivitylist;
	}

}
