/**
 * 
 */
package com.Vshop.admin.module.promotion.controller;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.base.Promotion;
import com.Vshop.core.entity.base.PromotionClass;
import com.Vshop.service.module.promotion.service.PromotionClassService;
import com.Vshop.service.module.promotion.service.PromotionService;
import com.Vshop.service.utils.CommonConstants;
import com.Vshop.service.utils.page.Pager;

/**
 * <p>Title: PromotionAction.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月21日
 * @version 1.0
 */
@Controller
@Slf4j
@RequestMapping("/promotion")
public class PromotionAction {
	
	@Resource
	private  PromotionService  promotionService;
	
	@Resource
	private PromotionClassService promotionClassService;
	
	@RequestMapping("/index")
	public String index(Model model,@RequestParam(required=false ,value="pageNo" ,defaultValue="")String pageNo){
		//ModelAndView mv = new ModelAndView("/promotion/list");
		Pager pager = new Pager();
		if(StringUtils.isNotEmpty(pageNo)){
			pager.setPageNo(Integer.valueOf(pageNo));
		}
//		pager.setPageSize(2);
//		int count = promotionService.findCount(pager);
		
		List<Promotion> list = promotionService.findList(pager);
		for (Promotion Promotion : list) {
			PromotionClass pc= promotionClassService.findById(Promotion.getPcId());
			if(pc!=null) Promotion.setPcName(pc.getPcName());
		}
//		pager.setTotalRows(count);
        pager.setResult(list);
        model.addAttribute("pager",pager);
		return "/promotion/list";
	}
	
	@RequestMapping("/add")
	public String add(Model model,@RequestParam(required=false , value="pId" ,defaultValue="")String id){
		List<PromotionClass> list = promotionClassService.findList(new Pager());
		if(StringUtils.isNotEmpty(id)){
			Promotion promotion = promotionService.findById(Integer.valueOf(id));
			model.addAttribute("promotion", promotion );
		}
		
		model.addAttribute("pclist", list);
		return "/promotion/add";
	}
	
	
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(Model model, @ModelAttribute Promotion promotion){
		if(promotion != null && promotion.getId()!= null){
			promotionService.update(promotion);
			model.addAttribute("msg", "修改成功");
		}else{
			promotionService.save(promotion);
			model.addAttribute("msg", "保存成功");
		}
		
		model.addAttribute("referer", CommonConstants.ADMIN_SERVER + "/promotion/index");
		
		return Constants.MSG_URL;
	}
	
	
	@RequestMapping("/delete")
	public String delete(Model model,@RequestParam(required=false , value="pId" ,defaultValue="")String pId){
		if(StringUtils.isNotEmpty(pId)){
			promotionService.delete(Integer.valueOf(pId));
			model.addAttribute("msg", "删除成功");
		}
		model.addAttribute("referer", CommonConstants.ADMIN_SERVER + "/promotion/index");
		
		return Constants.MSG_URL;
	}

}
