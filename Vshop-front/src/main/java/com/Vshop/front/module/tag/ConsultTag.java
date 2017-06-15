/**
 * 
 */
package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import com.Vshop.core.entity.base.Consult;
import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.trade.service.ConsultService;
import com.Vshop.service.utils.page.Pager;

import freemarker.template.TemplateModelException;

/**
 * <p>Title: ConsultTag.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月17日
 * @version 1.0
 */
@Component
public class ConsultTag extends BaseFreeMarkerTag {

	@Resource
    private ConsultService consultService; 
	
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		//需要返回数据的类型 TagsDataType.java
		String tagType = ParamsUtils.getString(params.get("tagDataType"));
		String consultReply = ParamsUtils.getString(params.get("consultReply"));
		
		int pageNo = ParamsUtils.getInt(params.get("pageNo"));
		int pageSize = ParamsUtils.getInt(params.get("pageSize"));
		Pager pager = new Pager();
		if(pageNo!=0){
			pager.setPageNo(pageNo);
		}
		if(pageSize!=0){
			pager.setPageSize(pageSize);
		}
		Consult consult = new Consult();
		if (SecurityUtils.getSubject().isAuthenticated()) {
			if(CacheUtils.getCacheUser() != null &&CacheUtils.getCacheUser().getMember() != null){
				consult.setMemberId(CacheUtils.getCacheUser().getMember().getMemberId());
			}
		}
		if(StringUtils.isNotEmpty(consultReply)){
			consult.setReplyStatus(Integer.valueOf(consultReply));
		}
		
		pager.setCondition(consult);
		if(TagsDataType.PAGE_LIST.equals(tagType)){
			if(pageNo != 0){
				pager.setPageNo(pageNo);
			}
			if(pageSize != 0){
				pager.setPageSize(pageSize);
			}
			
			List<Consult> list = consultService.findMemberList(pager);
			return list;
		}else if(TagsDataType.RECORD_COUNT.equals(tagType)){
			
			return consultService.findMemberCount(consult);
		}
		return null;
	}

}
