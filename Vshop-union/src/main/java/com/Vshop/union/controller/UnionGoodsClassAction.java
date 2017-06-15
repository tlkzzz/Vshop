package com.Vshop.union.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.Vshop.core.common.Constants;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.service.utils.CommonConstants;
import com.Vshop.service.utils.page.Pager;
import com.Vshop.union.entity.UnionGoodsClass;
import com.Vshop.union.service.UnionGoodsClassService;

/**
 * 联盟商品分类Action
 * 
 * @author liuzhen
 * @version 2015-9-21
 */
@Slf4j
@Controller
@RequestMapping("/unionGoodsClass")
public class UnionGoodsClassAction {

	/** 联盟商品分类service */
	@Resource
	private UnionGoodsClassService unionGoodsClassService;

	/**
	 * 联盟商品分类列表
	 * 
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(required = false, value = "name", defaultValue = "") String name,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo) {

		Pager pager = new Pager();
		pager.setPageNo(pageNo);

		UnionGoodsClass goodsClass = new UnionGoodsClass();
		goodsClass.setName(name);
		pager.setCondition(goodsClass);

		pager.setResult(unionGoodsClassService.findListByPid(0));

		Map<String, Object> m = new HashMap<String, Object>();
		m.put("pager", pager);
		return new ModelAndView("/union/goodsclass/union_goodsclass_list", m);
	}

	@RequestMapping("/loadChildList")
	public ModelAndView loadChildList(
			@RequestParam(required = false, value = "id", defaultValue = "0") int id) {
		List<UnionGoodsClass> goodsClasss = null;

		if (id != 0) {
			goodsClasss = unionGoodsClassService.findListByPid(id);
		}

		Map<String, Object> m = new HashMap<String, Object>();
		m.put("goodsClasss", goodsClasss);
		m.put("id", id);
		return new ModelAndView("/union/goodsclass/union_goodsclass_childlist",
				m);
	}

	/**
	 * 跳转至联盟商品分类新增或修改页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/forward")
	public ModelAndView add(
			@RequestParam(required = false, value = "id", defaultValue = "0") int id,
			@RequestParam(required = false, value = "pid", defaultValue = "0") int pid) {

		UnionGoodsClass goodsClass = unionGoodsClassService.findById(id);

		if (goodsClass == null) {
			goodsClass = new UnionGoodsClass();
			goodsClass.setSort(1);
		}

		if (pid != 0) {
			goodsClass.setPid(pid);
		}

		List<UnionGoodsClass> goodsClasss = unionGoodsClassService.findAll();

		Map<String, Object> m = new HashMap<String, Object>();
		m.put("goodsClass", goodsClass);
		m.put("goodsClasss", goodsClasss);
		return new ModelAndView("/union/goodsclass/union_goodsclass", m);
	}

	/**
	 * 联盟商品分类图标上传
	 * 
	 * @param myfiles
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/uploadImage")
	public String uploadImage(@RequestParam MultipartFile[] myfiles,
			HttpServletRequest request) {
		// 可以在上传文件的同时接收其它参数
		Map<String, Object> map = Maps.newHashMap();

		for (MultipartFile multipartFile : myfiles) {
			MultipartFile[] files = myfiles;
			// 使用公用上传方法上传图片
			try {
				map = com.Vshop.core.common.FileUtils.fileUpload(files,
						CommonConstants.FILE_BASEPATH,
						Constants.UNION_GOODSCLASS_UPLOAD_URL, request);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 上传后信息写入json回显
		String json = JsonUtils.toJsonStr(map);
		return json;
	}

	/**
	 * 联盟商品分类修改或保存
	 * 
	 * @param goodsClass
	 * @param id
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	public ModelAndView saveOrUpdate(
			@ModelAttribute UnionGoodsClass goodsClass,
			@RequestParam(required = false, value = "id", defaultValue = "0") int id) {

		Map<String, Object> m = Maps.newHashMap();

		if (id != 0) {
			unionGoodsClassService.update(goodsClass);
			m.put("msg", "修改成功");
		} else {
			unionGoodsClassService.save(goodsClass);
			m.put("msg", "保存成功");
		}

		m.put("referer", CommonConstants.ADMIN_SERVER + "/unionGoodsClass/list");
		return new ModelAndView(Constants.MSG_URL, m);
	}

	/**
	 * 联盟商品分类删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam int[] ids,
			HttpServletRequest request) {

		Map<String, Object> m = Maps.newHashMap();

		String referer = request.getHeader("Referer");
		for (int id : ids) {
			unionGoodsClassService.deleteById(id);
		}

		m.put("referer", referer);
		return new ModelAndView(Constants.MSG_URL, m);
	}

	/**
	 * 修改排序
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modifySort")
	public Boolean modifySort(
			@RequestParam(required = false, value = "id", defaultValue = "0") int id,
			@RequestParam(required = false, value = "value", defaultValue = "1") int sort) {

		UnionGoodsClass goodsClass = unionGoodsClassService.findById(id);
		if (goodsClass == null) {
			return false;
		}
		goodsClass.setId(id);
		goodsClass.setSort(sort);

		unionGoodsClassService.update(goodsClass);
		return true;
	}

	/**
	 * 修改排序
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modifyName")
	public Boolean modifyName(
			@RequestParam(required = false, value = "id", defaultValue = "0") int id,
			@RequestParam(required = false, value = "value", defaultValue = "") String name) {

		UnionGoodsClass goodsClass = unionGoodsClassService.findById(id);
		if (goodsClass == null) {
			return false;
		}
		goodsClass.setId(id);
		goodsClass.setName(name);

		unionGoodsClassService.update(goodsClass);
		return true;
	}
}
