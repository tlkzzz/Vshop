package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.entity.base.Express;
import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.setting.service.ExpressService;
import com.Vshop.service.utils.page.Pager;

import freemarker.template.TemplateModelException;

/**
 * 配送方式列表页标签
 * 
 * @author gyh
 * @version 2015-07-21 11:30:00
 */
@Component
public class ExpressListTag extends BaseFreeMarkerTag {

	@Resource
	private  ExpressService expressService;
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		Express express=new Express();
		//需要返回数据的类型 TagsDataType.java
		String tagType = ParamsUtils.getString(params.get("tagDataType"));
		// 页码
		int pageNo = ParamsUtils.getInt(params.get("pageNo"));
		// 每页数量
		int pageSize = ParamsUtils.getInt(params.get("pageSize"));
		//快递名称
        String eName = ParamsUtils.getString(params.get("eName"));
		if(eName!=null&&!eName.equals("")){
			express.setSeName(eName);
		}
		// 准备分页pager
		Pager pager = new Pager();
		if (pageSize != 0) {
			pager.setPageSize(pageSize);
		}
		//查分页的list
		if(TagsDataType.PAGE_LIST.equals(tagType)){
			if (pageNo != 0) {
				pager.setPageNo(pageNo);
			}
			pager.setCondition(express);
			// 查找list
			List<Express> expressList = expressService.findExpressList(pager);
			return expressList;
		}else if(TagsDataType.RECORD_COUNT.equals(tagType)){//查分页的总条数
			int totalCount = expressService.findExpressCount(express);
			return totalCount;
		}
		return null;
	}
}
