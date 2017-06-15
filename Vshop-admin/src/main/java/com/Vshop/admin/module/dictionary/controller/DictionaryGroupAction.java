package com.Vshop.admin.module.dictionary.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.base.DictionaryGroup;
import com.Vshop.service.module.dictionary.service.DictionaryGroupService;
import com.Vshop.service.utils.CommonConstants;
import com.Vshop.service.utils.page.Pager;

/**
 * 数据字典组
* <p>Title: DictionaryGroupAction.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014-2018</p>
* <p>Company: Vshop.com</p>
* @author linjm
* @date 2015年10月13日
* @version 1.0
 */
@Controller
@RequestMapping("/group/dictionaryGroup")
@Slf4j
public class DictionaryGroupAction {

	@Resource
	private DictionaryGroupService dictionaryGroupService;
	
	/**
	 * 字典组分页列表
	 * @param model
	 * @param dictionaryGroup
	 * @param div
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model,@ModelAttribute DictionaryGroup dictionaryGroup,
			@RequestParam(required = false, value = "div", defaultValue = "") String div,
			@RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNo){
		try {
			if(dictionaryGroup.getGroupName() != null){
				dictionaryGroup.setGroupCode(dictionaryGroup.getGroupName());
			}
			Pager pager = new Pager();
			pager.setCondition(dictionaryGroup);
			if(!StringUtils.isEmpty(pageNo)){
	            pager.setPageNo(Integer.parseInt(pageNo));
	        }
			List<DictionaryGroup> dictionaryGroupList = dictionaryGroupService.queryGroupidList(pager);
			pager.setResult(dictionaryGroupList);
			model.addAttribute("dictionaryGroupList", dictionaryGroupList);
			model.addAttribute("pager", pager);//总数
			model.addAttribute("toUrl", "group/dictionaryGroup/list");
			return "group/list";
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("导航失败!");
		}
	}
	/**
	 * 跳转保存、编辑页面
	 * @param id
	 * @param model
	 * @param dictionaryGroup
	 * @return
	 */
	@RequestMapping("/forward")
    public String forward(@RequestParam int id,Model model,@ModelAttribute DictionaryGroup dictionaryGroup){
		if(id==0){
			return  "group/save";
		}else{
			model.addAttribute("dictionaryGroup", dictionaryGroupService.findByGroupId(id));
			return "group/edit";
		}
	}
	
	/**
	 * 验证字典编码是否重复
	 */
	@ResponseBody
	@RequestMapping("/checkGroupCode")
	public String checkGroupCode(@RequestParam("groupCode") String groupCode){
		DictionaryGroup dictionaryGroup = dictionaryGroupService.selectGroupByGroupCode(groupCode);
		if(dictionaryGroup == null){
			return "1";//没有重复
		}
		return "0";//重复
	}
	/**
	 * 字典组保存、修改
	 * @param dictionaryGroup
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/save")
    public String save(@ModelAttribute DictionaryGroup dictionaryGroup,Model model,HttpServletRequest request){
//		String referer = request.getHeader("Referer");
		model.addAttribute("referer", CommonConstants.ADMIN_SERVER+"/group/dictionaryGroup/list");
	    if(dictionaryGroup.getGroupId()!=null){
	    	dictionaryGroupService.update(dictionaryGroup);
	    	model.addAttribute("msg", "修改成功");
//	    	adminLogService.save("字典组修改", request);
	    }else{
	    	dictionaryGroupService.save(dictionaryGroup);
	    	model.addAttribute("msg", "保存成功");
//	    	adminLogService.save("字典组保存", request);
	    }
		return Constants.MSG_URL;
	}
	/**
	 * 字典组删除
	 * @param ids
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/delete")
    public String delete(@RequestParam int[] ids,HttpServletRequest request,Model model){

        String referer = request.getHeader("Referer");
        for(int id : ids){
        	dictionaryGroupService.delete(id);
        }
        model.addAttribute("referer", referer);
        model.addAttribute("msg", "删除成功");
        return Constants.MSG_URL;
    }
}
