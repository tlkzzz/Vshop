package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import com.Vshop.core.entity.base.ShopPointsLog;
import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.member.service.ShopPointsLogService;
import com.Vshop.service.utils.page.Pager;

import freemarker.template.TemplateModelException;

/**
 * 课程积分
 * 
 * @author gyh
 * @version 2015-07-16 13:30:00
 */
@Component
public class ShopPointsLogListTag extends BaseFreeMarkerTag {

	@Resource
	private ShopPointsLogService shopPointsLogService;

	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		ShopPointsLog shopPointsLog = new ShopPointsLog();
		//需要返回数据的类型 TagsDataType.java
		String tagType = ParamsUtils.getString(params.get("tagDataType"));
		// 页码
		int pageNo = ParamsUtils.getInt(params.get("pageNo"));
		// 每页数量
		int pageSize = ParamsUtils.getInt(params.get("pageSize"));
		if (SecurityUtils.getSubject().isAuthenticated()) {
			if(CacheUtils.getCacheUser().getMember()!=null&&CacheUtils.getCacheUser().getMember().getMemberId()!=null){
				shopPointsLog.setMemberid(CacheUtils.getCacheUser().getMember().getMemberId());
			}
		}
		Pager pager = new Pager();
		if (pageSize != 0) {
			pager.setPageSize(pageSize);
		}
		pager.setCondition(shopPointsLog);
		//查分页的list
		if(TagsDataType.PAGE_LIST.equals(tagType)){
			// 排序
			if (pageNo != 0) {
				pager.setPageNo(pageNo);
			}
			// 查找list
			List<ShopPointsLog> shopPointsLogsList=shopPointsLogService.findPageList(pager);
			return shopPointsLogsList;
		}else if(TagsDataType.RECORD_COUNT.equals(tagType)){//查分页的总条数
			pager.setCondition(shopPointsLog);
			Integer totalCount = null;
			totalCount=shopPointsLogService.findCount(shopPointsLog);
			return totalCount;
		}
		return null;
	}

}
