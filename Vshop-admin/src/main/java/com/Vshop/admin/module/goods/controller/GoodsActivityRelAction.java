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
import com.Vshop.core.entity.base.Dictionary;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.GoodsActivity;
import com.Vshop.core.entity.base.GoodsActivityRel;
import com.Vshop.service.module.dictionary.service.DictionaryService;
import com.Vshop.service.module.goods.service.GoodsActivityRelService;
import com.Vshop.service.module.goods.service.GoodsActivityService;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.utils.page.Pager;

@Controller
@RequestMapping("/goods/activityrel")
public class GoodsActivityRelAction {

	@Resource
    private GoodsActivityRelService goodsActivityRelService;
	
	@Resource
	private DictionaryService dictionaryService;

	@Resource
    private GoodsActivityService goodsActivityService;
	
	@Resource
	private GoodsService goodsService;
	
	public static final String ACTIVITY_TYPE = "activity_type"; // 字典组表，活动类型

	/**
     * 列表
     * @param model
     * @return
     */
	@RequestMapping("/relactivity")
	public String relGoodsActivity(@ModelAttribute GoodsActivityRel goodsActivityRel, Model model,
			@RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNo) {

		Pager pager = new Pager();
		pager.setCondition(goodsActivityRel);
		if (!StringUtils.isEmpty(pageNo)) {
			pager.setPageNo(Integer.parseInt(pageNo));
		}

		// List<Dictionary> activityTypeList = dictionaryService.findDictionaryByCode(ACTIVITY_TYPE); // 活动类型
		
		// goodsActivityService.findPageList(pager);
		
		List<GoodsActivityRel> list = goodsActivityRelService.findPageList(pager);
		pager.setResult(list);
		model.addAttribute("pager", pager);// 总数

		// model.addAttribute("activityTypeList", activityTypeList);
		return "goods/activityrel/rel";
	}
	
	/**
     * 列表
     * @param model
     * @return
     */
	@RequestMapping("/list")
	public String list(@ModelAttribute GoodsActivityRel goodsActivityRel, Model model,
			@RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNo) {

		Pager pager = new Pager();
		pager.setCondition(goodsActivityRel);
		if (!StringUtils.isEmpty(pageNo)) {
			pager.setPageNo(Integer.parseInt(pageNo));
		}
		List<GoodsActivityRel> list = goodsActivityRelService.findPageListMore(pager);
		pager.setResult(list);
		
		model.addAttribute("pager", pager); // 总数
		if (goodsActivityRel != null && goodsActivityRel.getGoodsId() != null) {
			model.addAttribute("goodsId", goodsActivityRel.getGoodsId()); //
		}
		if (goodsActivityRel != null) {
			model.addAttribute("storeId", goodsActivityRel.getStoreId()); //
		}

		return "goods/activityrel/list";
	}

	
    /**
     * 删除
     * @return
     */
    @RequestMapping("/delete")
	public String delete(@RequestParam int[] ids, HttpServletRequest request, Model model) {

		String referer = request.getHeader("Referer");
		for (int id : ids) {
			goodsActivityRelService.deleteById(id);
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
	public String forward(@RequestParam Integer id, @RequestParam Integer goodsId, @RequestParam Integer storeId, Model model) {
	
    	List<Dictionary> activityTypeList = dictionaryService.findDictionaryByCode(ACTIVITY_TYPE); // 活动类型
    	model.addAttribute("activityTypeList", activityTypeList);
    	
    	Pager pager = new Pager();
    	GoodsActivity goodsActivity = new GoodsActivity();
    	goodsActivity.setActivityUse(1);
		pager.setCondition(goodsActivity);
		
    	List<GoodsActivity> goodsActivityList = goodsActivityService.findPageList(pager);
    	model.addAttribute("goodsActivityList", goodsActivityList);
    	
		Goods goods = goodsService.findGoodById(goodsId);
		if (goods != null) {
			model.addAttribute("goodsName", goods.getGoodsName());
			model.addAttribute("storeName", goods.getStoreName());
		}
    	
		model.addAttribute("goodsId", goodsId);
		model.addAttribute("storeId", storeId);
		
    	if (id == 0) {
			return "goods/activityrel/save";
		} else {
			GoodsActivityRel goodsActivityRel = goodsActivityRelService.findById(id);
			model.addAttribute("goodsActivityRel", goodsActivityRel);
			return "goods/activityrel/edit";
		}
	}

    /**
     * 校验表单
     * @return
     */
    @RequestMapping("/validate")
	public @ResponseBody String validateForm(@ModelAttribute GoodsActivityRel goodsActivityRel) {

		if (goodsActivityRel == null) {
			return "false";
		}
		if (goodsActivityRel.getGoodsId() == null || goodsActivityRel.getStoreId() == null) {
			return "false";
		}

		int size = 0;

		if (goodsActivityRel.getActivityId() != null && goodsActivityRel.getActivityId() > 0) {
			size++;
		}
		if (goodsActivityRel.getActivityType() != null && goodsActivityRel.getActivityType() > 0) {
			size++;
		}

		if (size > 1) {
			Pager pager = new Pager();
			pager.setCondition(goodsActivityRel);
			List<GoodsActivityRel> result = goodsActivityRelService.findPageList(pager);

			if (result != null && result.size() > 0) {
				return "false";
			}
		}

		return "true";
	}
    
    /**
     * 新增
     * @param goodsActivityRel
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/save")
	public String save(@ModelAttribute("goodsActivityRel") GoodsActivityRel goodsActivityRel, HttpServletRequest request, Model model) {
		if (goodsActivityRel != null && goodsActivityRel.getGoodsId() != null) {
			goodsActivityRel.setCreateTime(System.currentTimeMillis());
			goodsActivityRelService.save(goodsActivityRel);
		}
		
		model.addAttribute("goodsId", goodsActivityRel.getGoodsId());
		model.addAttribute("storeId", goodsActivityRel.getStoreId());
		
		return confirmView(model, "新增成功", request);
	}
    
    /**
     * 修改
     * @param goodsActivityRel
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/update")
	public String update(@ModelAttribute("goodsActivityRel") GoodsActivityRel goodsActivityRel, HttpServletRequest request, Model model) {
		if (goodsActivityRel != null && goodsActivityRel.getRelId() != null) {
			goodsActivityRelService.update(goodsActivityRel);
		}
		return confirmView(model, "修改成功", request);
	}

	private String confirmView(Model model, String msg, HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		model.addAttribute("referer", referer);
		model.addAttribute("msg", msg);
		return Constants.MSG_URL;
	}
	
}
