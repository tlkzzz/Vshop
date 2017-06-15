/**
 * 
 */
package com.Vshop.front.module.api;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Vshop.core.entity.base.Feedback;
import com.Vshop.service.module.feedback.service.FeedbackService;

/**
 * <p>Title: FeedbackApi.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年8月25日
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/feedbackApi")
public class FeedbackApi extends BaseApi{
	
	@Autowired
	private FeedbackService feedbackService;
	
	@RequestMapping("/feeback")
	@ResponseBody
	public JSONObject feeback(Feedback feedback){
		jsonObj = new JSONObject();
		try {
			feedback.setCreateTime(System.currentTimeMillis());
			feedbackService.save(feedback);
			jsonObj.put("result", 1);
			jsonObj.put("msg", "提交成功");
		} catch (Exception e) {
			log.error("出错了",e);
			jsonObj.put("result", 0);
	        jsonObj.put("msg", "服务器异常");
		}
		return jsonObj;
	}

}
