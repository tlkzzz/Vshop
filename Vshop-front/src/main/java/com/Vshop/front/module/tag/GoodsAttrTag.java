package com.Vshop.front.module.tag;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.goods.service.GoodsService;

import freemarker.template.TemplateModelException;
import lombok.extern.log4j.Log4j;

/**
 * 课程基本属性标签
 * @author lkang
 * @version 2015-07-02 18:30:00
 */
@Log4j
@Component
public class GoodsAttrTag extends BaseFreeMarkerTag {

	@Resource
    private GoodsService goodsService;
	
	/**
	 * 课程属性
	 * @param goodsid课程id
	 */
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		int goodsId = ParamsUtils.getInt(params.get("goodsid"));
		Map<String, Object> goodsAttr = goodsService.getGoodsAttr(goodsId);
		try {
			goodsService.updateGoodsClick(goodsId);  // 更新点击量
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("更新点击量报错", ex);
		}
		return goodsAttr;
	}

}
