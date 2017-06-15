/**
 * 
 */
package com.Vshop.front.module.tag;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.core.entity.base.AdvPosition;
import com.Vshop.core.entity.vo.AdvPositionVo;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.adv.service.AdvPositionService;

import freemarker.template.TemplateModelException;

/**
 * <p>Title: AdvTag.java</p>
 * <p>Description: 广告位标签</p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月9日
 * @version 1.0
 */
@Component
public class AdvTag extends BaseFreeMarkerTag{

	@Resource
	private AdvPositionService advPositionService;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Object exec(Map params) throws TemplateModelException {
		int apId = ParamsUtils.getInt(params.get("apId"));
		String apKey = ParamsUtils.getString(params.get("apKey"));
		AdvPosition advPosition = new AdvPosition();
		if(apId != 0){
			advPosition.setApId(apId);
		}else if(StringUtils.isNotEmpty(apKey)){
			advPosition.setApKey(apKey);
		}
		AdvPositionVo advPositionVo = new  AdvPositionVo() ;
		try {
			advPositionVo = advPositionService.findAdvPositionVoList(advPosition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return advPositionVo;
	}

}
