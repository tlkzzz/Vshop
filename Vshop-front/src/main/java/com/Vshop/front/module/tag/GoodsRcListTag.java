package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.html5.index.controller.IndexHtml5Action;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.utils.page.Pager;

import freemarker.template.TemplateModelException;

@Component
public class GoodsRcListTag extends BaseFreeMarkerTag {

	@Resource
    private GoodsService goodsService;
	
	@Override
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		// TODO Auto-generated method stub
		int storeId = ParamsUtils.getInt(params.get("storeId"));
		
		String storeid = IndexHtml5Action.SELF_SUPPORT_STORE_ID;
		try {
			if (CacheUtils.getCacheUser().getStore() != null)
				storeid = CacheUtils.getCacheUser().getStore().getStoreId() + "";
		} catch (Exception e) {}
		
		try {
			storeId = Integer.parseInt(storeid);
		} catch (Exception ex) {}
		
		int gcId = ParamsUtils.getInt(params.get("gcId"));
		int gcType = ParamsUtils.getInt(params.get("gcType"));

		String sortField = ParamsUtils.getString(params.get("sortField"));
		String sortOrder = ParamsUtils.getString(params.get("sortOrder"));
		String keyword = ParamsUtils.getString(params.get("keyword"));
		
		// 页码
		int pageNo = ParamsUtils.getInt(params.get("pageNo"));
		// 每页数量
		int pageSize = ParamsUtils.getInt(params.get("pagesize"));

		Pager pager = new Pager();
        
		Goods goods = new Goods();
		if (gcId > 0) {
			goods.setGcId(gcId);
		}
        goods.setStoreId(storeId);
        
        
		if (pageSize > 0) {
			pager.setPageSize(pageSize);
		}
		if (pageNo > 0) {
			pager.setPageNo(pageNo);
		}
		
		if ("ASC".equalsIgnoreCase(sortOrder)) {
			sortOrder = "ASC";
		} else {
			sortOrder = "DESC";
		}
		goods.setOrderBy(sortOrder);
		
		if (sortField.trim().length() > 0) {
			goods.setSortField(sortField);
		} else {
			goods.setSortField("update_time");
		}
		
		if (keyword.trim().length() > 0) {
			goods.setGoodsName(keyword);
		}
		
        pager.setCondition(goods);
        
        List<Goods> goodsList = null;
        
        if(gcType==0) {
        	goodsList = goodsService.findRcGoodPager(pager);
        } else {
        	goodsList = goodsService.findAcGoodPager(pager);
        }
        
		return goodsList;
	}

}
