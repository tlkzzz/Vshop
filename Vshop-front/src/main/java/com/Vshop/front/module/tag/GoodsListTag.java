package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.core.state.goods.GoodsState;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.utils.page.Pager;

import freemarker.template.TemplateModelException;

/**
 * 课程列表页标签
 * 
 * @author lkang
 * @version 2015-07-02 17:30:00
 */
@Component
public class GoodsListTag extends BaseFreeMarkerTag {

	@Resource
	private GoodsService goodsService;

	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		Goods goods = new Goods();
		//需要返回数据的类型 TagsDataType.java
		String tagType = ParamsUtils.getString(params.get("tagDataType"));
		// 页码
		int pageNo = ParamsUtils.getInt(params.get("pageno"));
		// 每页数量
		int pageSize = ParamsUtils.getInt(params.get("pagesize"));
		//排序字段
		String sortField = ParamsUtils.getString(params.get("orderField"));
		// 按照时间升序、降序
		String orderBy = ParamsUtils.getString(params.get("order"));
		// 学院id
		int storeId = ParamsUtils.getInt(params.get("storeid"));
		//学院分类id
		int storeClassId = ParamsUtils.getInt(params.get("storeClassId"));
		//课程名称
        String goodsName = ParamsUtils.getString(params.get("goodsName"));
		if(storeClassId != 0){
			goods.setStoreClassId(storeClassId);
		}
		if(!goodsName.equals("")){
			goods.setGoodsName(goodsName);
		}
		//是否推荐
		String commend = ParamsUtils.getString(params.get("commend"));
		if(!commend.equals("")){
			goods.setGoodsCommend(Integer.parseInt(commend));
		}
		// 设置当前学院
		if (storeId != 0) {
			goods.setStoreId(storeId);
		}
		// 设置状态开启
		goods.setGoodsState(GoodsState.GOODS_OPEN_STATE);
		// 设置上架状态
		goods.setGoodsShow(GoodsState.GOODS_ON_SHOW);
		// 准备分页pager
		Pager pager = new Pager();
		if (pageSize != 0) {
			pager.setPageSize(pageSize);
		}
		//查分页的list
		if(TagsDataType.PAGE_LIST.equals(tagType)){
			// 排序
			if(StringUtils.isNotEmpty(sortField) && StringUtils.isNotEmpty(orderBy)){
				goods.setSortField(sortField);
				goods.setOrderBy(orderBy);
			}
			if (pageNo != 0) {
				pager.setPageNo(pageNo);
			}
			pager.setCondition(goods);
			// 查找list
			List<Goods> goodsList = goodsService.findGoodPagerList(pager);
			return goodsList;
		}else if(TagsDataType.RECORD_COUNT.equals(tagType)){//查分页的总条数
			int totalCount = goodsService.countGoods(goods);
			return totalCount;
		}
		return null;
	}

}
