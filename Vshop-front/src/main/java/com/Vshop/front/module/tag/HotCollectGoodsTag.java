package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.utils.page.Pager;

import freemarker.template.TemplateModelException;

/**
 * 课程收藏排行
 * @author gyh
 * @version 2015-08-31 11:36:00
 */
@Component
public class HotCollectGoodsTag extends BaseFreeMarkerTag {
	
	@Resource
    private GoodsService goodsService;

	/**
	 * 课程热销排行
	 * @param pageSize 每页显示条数
	 */
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		Integer pageSize = ParamsUtils.getInt(params.get("pagesize"));
		Integer storeId = ParamsUtils.getInt(params.get("storeId"));
		Pager pager = new Pager();
		pager.setPageNo(1);
		pager.setPageSize(pageSize);
		Goods goods = new Goods();
		goods.setStoreId(storeId);
		goods.setSortField("goodsCollect");
		goods.setOrderBy("desc");
		pager.setCondition(goods);
		List<Goods> list = goodsService.findGoodPagerList(pager);
		return list;
	}

}
