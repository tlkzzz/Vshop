package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.utils.page.Pager;

import freemarker.template.TemplateModelException;

@Component
public class GoodsAcListTag extends BaseFreeMarkerTag {

	@Resource
    private GoodsService goodsService;
	
	@Override
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		// TODO Auto-generated method stub
		int storeId = ParamsUtils.getInt(params.get("storeId"));
		int gcId = ParamsUtils.getInt(params.get("gcId"));

		// 页码
		int pageNo = ParamsUtils.getInt(params.get("pageno"));
		// 每页数量
		int pageSize = ParamsUtils.getInt(params.get("pagesize"));

		Pager pager = new Pager();
        
		Goods goods = new Goods();
		goods.setGcId(gcId);;
        goods.setStoreId(storeId);
        
        pager.setCondition(goods);

		if (pageSize > 0) {
			pager.setPageSize(pageSize);
		}
		if (pageNo > 0) {
			pager.setPageNo(pageNo);
		}

		List<Goods> goodsList = goodsService.findAcGoodPager(pager);
		
		return goodsList;
	}

}
