package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.core.entity.base.GoodsCombination;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.goods.service.GoodsCombinationService;

import freemarker.template.TemplateModelException;

/**
 * 课程基本属性标签
 * @author lkang
 * @version 2015-07-02 18:30:00
 */
@Component
public class GoodsCombinationTag extends BaseFreeMarkerTag {

	@Resource
	private GoodsCombinationService  goodsCombinationService;
	
	/**
	 * 课程基本属性
	 * @param goodsid 课程id
	 */
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {	
		
		try{
		Integer goodsId = ParamsUtils.getInt(params.get("goodsid"));
    	/**创建设置查询条件*/
    	GoodsCombination goodsCombination = new GoodsCombination();
    	
    	/**设置查询条件*/
    	goodsCombination.setGoodsId(goodsId);
    	
    	/**查询*/
    	List<GoodsCombination> list = goodsCombinationService.selectByCondition(goodsCombination);
    	
    	return list;

		}catch(Exception e){
			return null;
		}
	}

}
