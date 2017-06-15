/**
 * 
 */
package com.Vshop.admin.module.feedback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.base.Feedback;
import com.Vshop.service.module.feedback.service.FeedbackService;
import com.Vshop.service.utils.page.Pager;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

/**
 * <p>Title: FeedbackAction.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年8月25日
 * @version 1.0
 */

@Controller
@RequestMapping("/feedback")
@Slf4j
public class FeedbackAction {
	
	@Autowired
	private FeedbackService feedbackService;
	
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNoStr,
			Feedback feedback){
		Pager pager = new Pager();
		if (StringUtils.isNumeric(pageNoStr)) {
			pager.setPageNo(Integer.parseInt(pageNoStr));
		}
		pager.setCondition(feedback);//实体加载在pager中
		List<Feedback> results = feedbackService.findBylist(pager);
        pager.setResult(results);
        model.addAttribute("pager",pager);
		return "/feedback/listFeedback";
	}
	
	@RequestMapping("/add")
	public String add(Model model, Feedback feedback){
		return "/feedback/addFeedback";
	}
	
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(Model model,Feedback feedback,
			HttpServletRequest request,
			@RequestParam(required = false, value = "div", defaultValue = "") String div) {
        //String referer = request.getHeader("Referer");
        model.addAttribute("referer", "/feedback/listFeedback");
		feedbackService.save(feedback);
		model.addAttribute("msg", "修改成功");
		return Constants.MSG_URL;
	}
	
	@RequestMapping("/saveContact")
	public @ResponseBody Object saveContact(String jsonpCallback,Feedback feedback){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			feedback.setTitle(new String(feedback.getTitle().getBytes("ISO-8859-1"),"UTF-8"));
			feedback.setEmail(new String(feedback.getEmail().getBytes("ISO-8859-1"),"UTF-8"));
			feedback.setContent(new String(feedback.getContent().getBytes("ISO8859-1"),"UTF-8"));
			feedbackService.saveContact(feedback);
			map.put("name","success");
			JSONObject resultJSON = JSONObject.fromObject(map); //根据需要拼装json  
			return jsonpCallback+"("+resultJSON+")";
		} catch (Exception e) {
			e.getMessage();
			map.put("name","error");
			JSONObject resultJSON = JSONObject.fromObject(map); //根据需要拼装json  
			return jsonpCallback+"("+resultJSON+")";
		}
	}
}
