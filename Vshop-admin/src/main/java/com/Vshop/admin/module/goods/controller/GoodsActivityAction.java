package com.Vshop.admin.module.goods.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.base.GoodsActivity;
import com.Vshop.service.module.goods.service.GoodsActivityService;
import com.Vshop.service.utils.page.Pager;

@Controller
@RequestMapping("/goods/activity")
public class GoodsActivityAction {

	@Resource
    private GoodsActivityService goodsActivityService;
	
	/**
     * 列表
     * @param model
     * @return
     */
	@RequestMapping("/list")
	public String list(@ModelAttribute GoodsActivity goodsActivity, Model model,
			@RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNo) {

		Pager pager = new Pager();
		pager.setCondition(goodsActivity);
		if (!StringUtils.isEmpty(pageNo)) {
			pager.setPageNo(Integer.parseInt(pageNo));
		}
		List<GoodsActivity> list = goodsActivityService.findPageList(pager);
		pager.setResult(list);
		model.addAttribute("pager", pager);// 总数
		return "goods/activity/list";
	}

	/**
     * 校验表单
     * @return
     */
    @RequestMapping("/validate")
	public @ResponseBody String validateForm(@ModelAttribute GoodsActivity goodsActivity) {

		if (goodsActivity == null || goodsActivity.getActivityName() == null
				|| goodsActivity.getActivityName().trim().length() <= 0) {
			return "true";
		}
    	
		Pager pager = new Pager();
		GoodsActivity cond = new GoodsActivity();
		cond.setActivityName(goodsActivity.getActivityName());
		
		pager.setCondition(cond);

		List<GoodsActivity> alist = goodsActivityService.findPageList(pager);
		// 校验重复
		if (alist != null && alist.size() > 0 && alist.get(0) != null) {
			if(goodsActivity.getActivityId() == null || goodsActivity.getActivityId() <= 0) {
				return "false";	
			} else {
				if (alist.size() > 1) { // 大于1个记录，肯定有重复
					return "false";
				} else if(alist.size() == 1) { // 
					GoodsActivity cond2 = alist.get(0);
					if(goodsActivity.getActivityId() == cond2.getActivityId()) {
						return "true";  // 自己的
					} else {
						return "false";  
					}
				}
			}
		}
		
		return "true";
	}
	
    /**
     * 删除
     * @return
     */
    @RequestMapping("/delete")
	public String delete(@RequestParam int[] ids, HttpServletRequest request, Model model) {

		String referer = request.getHeader("Referer");
		for (int id : ids) {
			goodsActivityService.deleteById(id);
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
		// model.addAttribute("list",goodsActivityService.findAll());
		if (id == 0) {
			return "goods/activity/save";
		} else {
			GoodsActivity goodsActivity = goodsActivityService.findById(id);
			model.addAttribute("goodsActivity", goodsActivity);
			return "goods/activity/edit";
		}
	}

    /**
     * 新增
     * @param goodsActivity
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/save")
	public String save(@ModelAttribute("goodsActivity") GoodsActivity goodsActivity, HttpServletRequest request, Model model) {
		if (goodsActivity != null && goodsActivity.getActivityName() != null) {
			goodsActivity.setCreateTime(System.currentTimeMillis());
			goodsActivityService.save(goodsActivity);
		}
		return confirmView(model, "新增成功", request);
	}
    
    /**
     * 修改
     * @param goodsActivity
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/update")
	public String update(@ModelAttribute("goodsActivity") GoodsActivity goodsActivity, HttpServletRequest request, Model model) {
		if (goodsActivity != null && goodsActivity.getActivityId() != null) {
			goodsActivity.setLastUpdateTime(System.currentTimeMillis());
			goodsActivityService.update(goodsActivity);
		}

		return confirmView(model, "修改成功", request);
	}
    
    private String confirmView(Model model, String msg, HttpServletRequest request){
    	String referer = request.getHeader("Referer");
        model.addAttribute("referer", referer);
        model.addAttribute("msg", msg);
        return Constants.MSG_URL;
    }
}
