/**
 * 
 */
package com.Vshop.front.module.api;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.core.entity.base.AdvPosition;
import com.Vshop.core.entity.vo.AdvPositionVo;
import com.Vshop.service.module.adv.service.AdvPositionService;

/**
 * <p>Title: AdvApi.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月10日
 * @version 1.0
 */
@RequestMapping("/advApi")
@Controller
@Slf4j
public class AdvApi extends BaseApi{

	@Resource
	private AdvPositionService advPostionService;

	/**
	 * 获取所有广告位
	 * @return
	 */
	@RequestMapping("/advPositionList")
	public @ResponseBody JSONObject AdvPositionList(){
		jsonObj = new JSONObject();
		List<AdvPosition> list = advPostionService.findAllAdvPosition(new AdvPosition());
		if(list.size()!=0){
			jsonObj.put("result", 1);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data", JSONArray.fromObject(list, JsonUtils.getJsonConfig()));
		}
		return jsonObj;
	}
	
	/**
	 * 获取单个广告信息
	 * @param apId
	 * @return
	 */
	@RequestMapping("/adv")
	public @ResponseBody JSONObject Adv(@RequestParam(required=false ,value="apId" , defaultValue="" ) String apId,
			@RequestParam(required=false ,value="apKey" , defaultValue="" )String apKey){
		jsonObj = new JSONObject();
		AdvPosition advPosition = new AdvPosition();
		if(StringUtils.isNotEmpty(apId)){
			advPosition.setApId(Integer.valueOf(apId));
		}
		if(StringUtils.isNotEmpty(apKey)){
			advPosition.setApKey(apKey);
		}
		try {
			AdvPositionVo ap = advPostionService.findAdvPositionVoList(advPosition);
			if(ap != null){
				jsonObj.put("result", 1);
				jsonObj.put("msg", "获取成功");
				jsonObj.put("data", JSONArray.fromObject(ap, JsonUtils.getJsonConfig()));
			}
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("", e);
			jsonObj.put("result", -1);
			jsonObj.put("data", "");
			jsonObj.put("msg", "服务器出错啦，请稍后再试！");
			return jsonObj;
		}
		return jsonObj;
	}
}
