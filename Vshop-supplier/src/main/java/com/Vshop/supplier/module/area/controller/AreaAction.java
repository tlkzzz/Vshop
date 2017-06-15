package com.Vshop.supplier.module.area.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.collect.Maps;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.core.entity.Area;
import com.Vshop.service.module.area.service.AreaService;

@Controller
@RequestMapping("/area")
public class AreaAction {
	
	@Resource
	private AreaService areaService;
	
    /**
     * 根据父类ID 获取到 下级城市
     *
     * @param @param  parentid
     * @param @return
     * @param @throws JsonGenerationException
     * @param @throws JsonMappingException
     * @param @throws Exception    设定文件
     * @return Map<String,String>    返回类型
     * @throws
     * @Title: getChildArea
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    @RequestMapping(value = "/getChildArea", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, String> getChildArea(@RequestParam(value = "id") String parentid) throws JsonGenerationException, JsonMappingException,
            Exception {
        Map<String, String> map = Maps.newHashMap();

        List<Area> areas = areaService.queryChildList(Integer.valueOf(parentid));
        String json = "null";
        if (areas != null && areas.size() > 0) {
            json = JsonUtils.toJsonStr(areas);
        }
        map.put("result", json);
        map.put("success", "true");
        return map;
    }
}
