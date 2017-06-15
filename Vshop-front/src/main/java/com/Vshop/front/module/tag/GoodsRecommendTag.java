package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.RelGoodsRec;
import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.utils.page.Pager;

import freemarker.template.TemplateModelException;

@Component
public class GoodsRecommendTag extends BaseFreeMarkerTag {

	@Resource
    private GoodsService goodsService;
	
	@Override
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		// TODO Auto-generated method stub
		int storeId = ParamsUtils.getInt(params.get("storeId"));
		int reCommendId = ParamsUtils.getInt(params.get("reCommendId"));
		int goodsCommend = ParamsUtils.getInt(params.get("goodsCommend"));
		
		String sortField = ParamsUtils.getString(params.get("sortField"));
		String sortOrder = ParamsUtils.getString(params.get("sortOrder"));
		
		// 页码
		int pageNo = ParamsUtils.getInt(params.get("pageno"));
		// 每页数量
		int pageSize = ParamsUtils.getInt(params.get("pagesize"));

		Pager pager = new Pager();

		RelGoodsRec rgRecommend = new RelGoodsRec();
		if (reCommendId != 0) {
			rgRecommend.setReCommendId(reCommendId);
			pageSize = 1; // 查询是否有上架的课程，查一个足矣
		}
		rgRecommend.setStoreId(storeId);

		if (goodsCommend == 1) {
			rgRecommend.setGoodsCommend(goodsCommend);
			pageSize = 4; // 首页推荐只显示4个
		} else {
			pageSize = 10; // 列表里面先展示10个
		}

		if (pageSize > 0) {
			pager.setPageSize(pageSize);
		}
		if (pageNo > 0) {
			pager.setPageNo(pageNo);
		}
        
		if ("DESC".equalsIgnoreCase(sortOrder)) {
			sortOrder = "ASC";
		} else {
			sortOrder = "DESC";
		}
		rgRecommend.setOrderBy(sortOrder);
		
		if (sortField.trim().length() > 0) {
			rgRecommend.setSortField(sortField);
		} else {
			rgRecommend.setSortField("update_time");
		}
		
        pager.setCondition(rgRecommend);
        
		List<Goods> goodsList = goodsService.findRecommendGoodPager(pager);
		
		return goodsList;
	}

}
