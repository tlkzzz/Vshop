package com.Vshop.admin.module.setting.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.collect.Maps;
import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.Area;
import com.Vshop.core.entity.base.OffPayArea;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.service.module.area.service.AreaService;
import com.Vshop.service.module.setting.service.OffPayAreaService;

/**
 * 
 * 
 * @项目名称：Vshop-admin
 * @类名称：AreaAction
 * @类描述：
 * @修改备注：
 * @version
 * 
 */
@Controller
@RequestMapping("/setting/area")
@Slf4j
public class AreaAction {

	@Resource
	private AreaService areaService;
    @Resource
    private OffPayAreaService offPayAreaService;

    /**
     * 列表
     * @param model
     * @return
     */
    @RequestMapping("/list")
	public String list(Model model){

        Map<String,Object> map =areaService.queryAllArea();
        model.addAttribute("province",map.get("province"));
        model.addAttribute("country",map.get("country"));
        return "setting/area/list";
    }

    /**
     * 保存或修改
     * @param county
     */
    @RequestMapping("/edit")
    public String edit(@RequestParam String county,HttpServletRequest request,Model model){
        String referer = request.getHeader("Referer");
        OffPayArea offPayArea = new OffPayArea();
        offPayArea.setStoreId(1);
        offPayArea.setAreaId(county);
        offPayAreaService.saveOrUpdate(offPayArea);
        model.addAttribute("referer", referer);
        model.addAttribute("msg", "保存成功");
        return Constants.MSG_URL;
    }
	
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
