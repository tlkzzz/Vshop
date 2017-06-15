package com.Vshop.admin.module.goods.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.base.Admin;
import com.Vshop.core.entity.base.ConsumerCode;
import com.Vshop.service.module.admin.service.AdminService;
import com.Vshop.service.module.goods.service.ConsumerCodeService;
import com.Vshop.service.utils.page.Pager;

@Controller
@RequestMapping("/goods/consumercode")
public class ConsumerCodeAction {

	@Resource
    private ConsumerCodeService consumerCodeService;
	
	@Resource
    private AdminService adminService;
	
	/**
     * 列表
     * @param model
     * @return
     */
	@RequestMapping("/list")
	public String list(@ModelAttribute ConsumerCode consumerCode, Model model,
			@RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNo) {

		Pager pager = new Pager();
		pager.setCondition(consumerCode);
		if (!StringUtils.isEmpty(pageNo)) {
			pager.setPageNo(Integer.parseInt(pageNo));
		}
		List<ConsumerCode> list = consumerCodeService.findPageList(pager);
		pager.setResult(list);
		model.addAttribute("pager", pager);// 总数
		return "goods/consumercode/list";
	}

	
    /**
     * 删除
     * @return
     */
    @RequestMapping("/delete")
    public String  delete(@RequestParam int[] ids,HttpServletRequest request,Model model){

        String referer = request.getHeader("Referer");
        for(int id : ids){
        	consumerCodeService.deleteById(id);
        }
        model.addAttribute("referer", referer);
        model.addAttribute("msg", "删除成功");
        return Constants.MSG_URL;
    }

    /**
     * 跳转
     * @param id
     * @return
     */
    @RequestMapping("/forward")
	public String forward(@RequestParam Integer id, Model model) {
		// model.addAttribute("list",consumerCodeService.findAll());
		if (id == 0) {
			return "goods/consumercode/save";
		} else {
			ConsumerCode consumerCode = consumerCodeService.findById(id);
			model.addAttribute("consumerCode", consumerCode);

			return "goods/consumercode/edit";
		}
	}

    /**
     * 新增
     * @param consumerCode
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/save")
	public String save(@ModelAttribute("consumerCode") ConsumerCode consumerCode,
			@RequestParam(value = "code_numbers") int code_numbers, HttpServletRequest request, Model model) {
		Subject subject = SecurityUtils.getSubject();
		String username = subject.getPrincipal().toString();
		Admin admin = adminService.findByAdminName(username);

		consumerCode.setMemberId(admin.getAdminId());
		if(code_numbers > 0 && code_numbers < 1000) {
			for (int i = 0; i < code_numbers; i++) {
				consumerCodeService.save(consumerCode);
			}
		}
    	
		return confirmView(model, "新增成功", request);
	}
    
    /**
     * 修改
     * @param consumerCode
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/update")
	public String update(@ModelAttribute("consumerCode") ConsumerCode consumerCode, HttpServletRequest request, Model model) {
    	consumerCodeService.update(consumerCode);
		return confirmView(model, "修改成功", request);
	}
    
    private String confirmView(Model model, String msg, HttpServletRequest request){
    	String referer = request.getHeader("Referer");
        model.addAttribute("referer", referer);
        model.addAttribute("msg", msg);
        return Constants.MSG_URL;
    }
}
