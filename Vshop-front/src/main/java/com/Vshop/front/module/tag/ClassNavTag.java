package com.Vshop.front.module.tag;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.entity.GoodsClass;
import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.goods.service.BrandService;
import com.Vshop.service.module.goods.service.GoodsClassService;
import com.Vshop.service.utils.CommonConstants;

import freemarker.template.TemplateModelException;

/**
 * 课程列表页面包线分类标签
 * @author kviuff
 * @date 2015-07-14
 */

@Component
public class ClassNavTag extends BaseFreeMarkerTag {

	@Resource
    private GoodsClassService goodsClassService;
	
	@Resource
    private BrandService brandService;
	
	/**
	 * @param classId 课程分类id
	 */
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		String classId = ParamsUtils.getString(params.get("classId"));
		String searchType = ParamsUtils.getString(params.get("searchType"));
		String nav = "";
		if("gcIdSearch".equals(searchType)){
			GoodsClass gClass = goodsClassService.findById(Integer.parseInt(classId));
			if(gClass!=null){
				String gcIdpath = gClass.getGcIdpath();
				String[] idpath = gcIdpath.split(",");
				for (String string : idpath) {
					gClass = goodsClassService.findById(Integer.parseInt(string));
					String gcName = gClass.getGcName();
					nav += "<span class=\"arrow\">&gt;</span>";
					nav += "<span><a href=\"" + CommonConstants.FRONT_SERVER 
							+ "/search/goodsSearch?searchType=gcIdSearch&keyword=" 
							+ string + "\">" + gcName + "</a></span>";
				}
			}
		}else if("BrandIdSearch".equals(searchType)){
			Brand brand = brandService.findById(Long.parseLong(classId));
			nav += "<span class=\"arrow\">&gt;</span>";
			nav += "<span>";
			nav += "<a href=\"" + CommonConstants.FRONT_SERVER 
					+ "/search/goodsSearch?searchType=BrandIdSearch&keyword=" 
					+ brand.getBrandId() + "\">";
			nav += brand.getBrandName();
			nav += "</a>";
			nav += "</span>";
		}
		return nav;
	}
	
}
