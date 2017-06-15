package com.Vshop.front.module.tag;

import java.text.NumberFormat;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.core.entity.base.EvaluateStore;
import com.Vshop.front.module.store.util.Util;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.store.service.EvaluateStoreService;

import freemarker.template.TemplateModelException;

/**
 * 学院评分表自定义标签
 * @author gyh
 * @version 2015-07-14 17:30:00
 */
@Component
public class EvaluateStorebyStoreIdTag extends BaseFreeMarkerTag {
	@Resource
	private EvaluateStoreService evaluateStoreService;
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		Integer storeId=ParamsUtils.getInt(params.get("storeId"));
		EvaluateStore evaluateStore=new EvaluateStore();
		if(storeId!=0){
			evaluateStore=evaluateStoreService.findEvaluateStore(storeId);
			//保留一位小数
			NumberFormat numberFormat= NumberFormat.getNumberInstance() ; 
			numberFormat.setMaximumFractionDigits(1);
			if(evaluateStore!=null){
				//发货速度评分
				if(evaluateStore.getSevalDeliverycredit()!=null){
					evaluateStore.setSevalDeliverycredit(Double.valueOf(numberFormat.format(evaluateStore.getSevalDeliverycredit())));
				}
				//描述相符评分
				if(evaluateStore.getSevalDesccredit()!=null){
					evaluateStore.setSevalDesccredit(Double.valueOf(numberFormat.format(evaluateStore.getSevalDesccredit())));
				}
				//服务态度评分
				if(evaluateStore.getSevalServicecredit()!=null){
					evaluateStore.setSevalServicecredit(Double.valueOf(numberFormat.format(evaluateStore.getSevalServicecredit())));
				}
			}
			 if(!evaluateStore.getCount().equals("0")){
			    evaluateStore.setAverageCredit(Util.getAverageCreditFormat(evaluateStore));
			 }
		}
		return evaluateStore;
	}
}
