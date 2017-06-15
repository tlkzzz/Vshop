package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.entity.base.EvaluateGoods;
import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.trade.service.EvaluateGoodsService;
import com.Vshop.service.utils.page.Pager;

import freemarker.template.TemplateModelException;

/**
 * 课程评论自定义标签
 * @author Administrator
 */
@Component
public class EvaluateGoodsTag extends BaseFreeMarkerTag {

	@Resource
	private EvaluateGoodsService evaluateGoodsService;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Object exec(Map params) throws TemplateModelException {
		// TODO Auto-generated method stub

		Integer goodsId = ParamsUtils.getInt(params.get("goodsId"));
		Object storeId_ = params.get("storeId");
		
		EvaluateGoods egoods = new EvaluateGoods();
		egoods.setGevalGoodsId(goodsId);

		if (storeId_ != null) {
			Integer storeId = ParamsUtils.getInt(storeId_);
			egoods.setGevalStoreId(storeId);
		}

		int pageNo = ParamsUtils.getInt(params.get("pageNo"));
		int pageSize = ParamsUtils.getInt(params.get("pageSize"));

		Pager pager = new Pager();
		pager.setCondition(egoods);
		if (pageNo != 0) {
			pager.setPageNo(pageNo);
		}
		if (pageSize != 0) {
			pager.setPageSize(pageSize);
		} else {
			pager.setPageSize(3);
		}
		
		List<EvaluateGoods> evaluateGoodsList = evaluateGoodsService.findPageList(pager);
			
		return evaluateGoodsList;
	}

}
