package com.Vshop.front.module.tag;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.trade.service.EvaluateGoodsService;

import freemarker.template.TemplateModelException;

/**
 * 课程评论列表
 * @author cgl
 * 添加时间：2015年08月10日14:26:40
 */
@Component
public class GoodsEvaluteScollbyGoodsIdTag extends BaseFreeMarkerTag {
	 @Resource
	 private EvaluateGoodsService evaluateGoodsService;
		/**
		 * 课程评论列表
		 * @param MemberId 会员id
		 * @param StoreId  学院id
		 */
	 @SuppressWarnings("rawtypes")
		protected Object exec(Map params) throws TemplateModelException {
			// 课程id
			Integer goodsId = ParamsUtils.getInt(params.get("goodsId"));
			if(goodsId != null && goodsId != 0){
				//平均分
				BigDecimal avearageScoll = evaluateGoodsService.getAverageScoreByGooodsId(goodsId);
				
				if(avearageScoll == null){
					return 0;
				}
				
				return avearageScoll;
			}else{
				return 0;
			}
		}

}
