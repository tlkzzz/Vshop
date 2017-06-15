package com.Vshop.front.module.api;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.core.entity.Area;
import com.Vshop.service.module.area.service.AreaService;

/**
 * 地区API接口
 * @author LKANG
 * @version 2015-07-06 16:00:00
 */
@Slf4j
@Controller
@RequestMapping("/area/api")
public class AreaApi extends BaseApi {
	
	@Resource
	private AreaService areaService;
	
	/**
	 * 地区列表
	 * @return
	 */
	@RequestMapping("arealist")
	@ResponseBody
	public JSONObject areaList(){
		jsonObj = new JSONObject();
		try {
			List<Area> list = areaService.queryProvince();
			jsonObj.put("result", 1);
			jsonObj.put("data", JSONArray.fromObject(list, JsonUtils.getJsonConfig()));
		} catch (Exception e) {
			log.error("地区列表API出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "无地区");
		}
		
		return jsonObj;
	}
}
