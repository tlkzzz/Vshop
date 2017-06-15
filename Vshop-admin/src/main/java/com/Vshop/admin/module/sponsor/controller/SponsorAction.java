package com.Vshop.admin.module.sponsor.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.ImmutableMap;
import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.base.Sponsor;
import com.Vshop.service.module.operation.service.SponsorService;
import com.Vshop.service.utils.page.Pager;

@Slf4j
@Controller
@RequestMapping("/sponsor")
public class SponsorAction {
	@Resource
    private SponsorService sponsorService;
	
	@RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public String List(@ModelAttribute Sponsor sponsor, Model model, @RequestParam(required=false, value="pageNo",defaultValue="")String pageNo){
		Pager pager = new Pager();
        if(StringUtils.isNotBlank(pageNo)){
            pager.setPageNo(Integer.parseInt(pageNo));
        }
        pager.setCondition(sponsor);
        List<Sponsor> results = sponsorService.findPageList(pager);
        pager.setResult(results);
        model.addAllAttributes(ImmutableMap.of("pager", pager, "sponsor", sponsor));
		return "sponsor/list";
	}
	
	@RequestMapping(value = "/forward", method = {RequestMethod.POST, RequestMethod.GET})
    public String forward(Model model, @RequestParam int id){
        if(id == 0){
            return "sponsor/save";
        }else{
        	model.addAllAttributes(ImmutableMap.of("sponsor", sponsorService.findById(id)));
            return "sponsor/edit";
        }
    }
	
	@RequestMapping(value = "/save", method = {RequestMethod.POST, RequestMethod.GET})
    public String save(@ModelAttribute("sponsor") Sponsor sponsor, HttpServletRequest request, Model model){
		sponsorService.save(sponsor);
        return confirmView(model, "新增成功", request);
    }
	
	@RequestMapping(value = "/edit", method = {RequestMethod.POST, RequestMethod.GET})
    public String edit(@ModelAttribute("sponsor") Sponsor sponsor, HttpServletRequest request, Model model){
		sponsorService.update(sponsor);
        return confirmView(model, "修改成功", request);
    }
	
	@RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.GET})
    public String  delete(@RequestParam int[] ids, HttpServletRequest request, Model model){
		for(int id : ids){
			sponsorService.delete(id);
		}
        return confirmView(model, "删除成功", request);
    }
	
	@RequestMapping(value = "/validate", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody String validateForm(@ModelAttribute Sponsor sponsor){
        //校验重复
        if(sponsorService.countSponsor(sponsor) > 0){
            return "false";
        }else{
            return "true";
        }
    }
    
    private String confirmView(Model model, String msg, HttpServletRequest request){
    	String referer = request.getHeader("Referer");
        model.addAttribute("referer", referer);
        model.addAttribute("msg", msg);
        log.debug(msg);
        return Constants.MSG_URL;
    }
}
