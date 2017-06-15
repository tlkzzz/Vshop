package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.core.entity.base.GoodsRecommend;
import com.Vshop.core.entity.base.RelGoodsRecommend;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.goods.service.GoodsRecommendService;
import com.Vshop.service.module.goods.service.RelGoodsRecommendService;

import freemarker.template.TemplateModelException;

/**
 * 推荐课程,最新上市,猜你喜欢课程的tag标签
 * @author LKANG
 * 添加时间：2015-08-25 12:12:00
 */
@Component
public class RecommendGoodsTag extends BaseFreeMarkerTag {

	@Resource
	private GoodsRecommendService goodsRecommendService;
	
	@Resource
	private RelGoodsRecommendService relGoodsRecommendService;
	
	/**
	 * 课程分类列表
	 * @param parentId  父级id
	 */
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		String recommendName = ParamsUtils.getString(params.get("goodsflagsname"));//课程栏目名称 storeid
		String storeid = ParamsUtils.getString(params.get("storeid"));
		if(storeid!=null&&storeid.trim().length()==0){
			storeid="0";
		}
		System.out.println("==============1234=============="+storeid);
		List<RelGoodsRecommend> relGoodsRecommedlist=null;
		if(recommendName!=null&&!"".equals(recommendName)){
			GoodsRecommend goodsRecommend=new GoodsRecommend();
			goodsRecommend=goodsRecommendService.findBycolum(recommendName);
			if(goodsRecommend!=null){
				
					
					relGoodsRecommedlist =relGoodsRecommendService.findStoregoodsList(goodsRecommend.getReCommendid(), java.lang.Integer.valueOf(storeid));
				
				
			}
		}
		return relGoodsRecommedlist;
	}

}
