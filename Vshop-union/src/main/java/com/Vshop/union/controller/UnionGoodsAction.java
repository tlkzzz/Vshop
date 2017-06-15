package com.Vshop.union.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.Vshop.core.common.Constants;
import com.Vshop.service.utils.CommonConstants;
import com.Vshop.service.utils.page.Pager;
import com.Vshop.union.entity.APIResult;
import com.Vshop.union.entity.UnionGoods;
import com.Vshop.union.entity.UnionGoodsClass;
import com.Vshop.union.service.UnionGoodsClassService;
import com.Vshop.union.service.UnionGoodsService;
import com.Vshop.union.service.UnionJDService;
import com.Vshop.union.utils.UnionConstants;

/**
 * 联盟商品
 * 
 * @author liuzhen
 * @version 2015-9-22
 */
@Slf4j
@Controller
@RequestMapping("/unionGoods")
public class UnionGoodsAction {

	/** 联盟商品分类service */
	@Resource
	private UnionGoodsClassService unionGoodsClassService;
	/** 联盟商品service */
	@Resource
	private UnionGoodsService unionGoodsService;
	/** 联盟接口 */
	@Resource
	private UnionJDService unionJDService;

	/**
	 * 联盟商品列表
	 * 
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(required = false, value = "skuId", defaultValue = "0") int skuId,
			@RequestParam(required = false, value = "goodsName", defaultValue = "") String goodsName,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "classId", defaultValue = "0") int classId) {

		Pager pager = new Pager();
		pager.setPageNo(pageNo);

		UnionGoods goods = new UnionGoods();
		goods.setSkuId(skuId);
		goods.setGoodsName(goodsName);
		goods.setClassId(classId);
		pager.setCondition(goods);

		pager.setResult(unionGoodsService.findPagerList(pager));

		Map<String, Object> m = new HashMap<String, Object>();
		List<UnionGoodsClass> goodsClasss = unionGoodsClassService.findAll();
		m.put("goodsClasss", goodsClasss);
		m.put("pager", pager);
		return new ModelAndView("/union/goods/union_goods_list", m);
	}

	/**
	 * 跳转至联盟商品分类新增或修改页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/forward")
	public ModelAndView add(
			@RequestParam(required = false, value = "id", defaultValue = "0") int id) {

		Map<String, Object> m = new HashMap<String, Object>();
		UnionGoods goods = unionGoodsService.findById(id);
		m.put("goods", goods);

		List<UnionGoodsClass> goodsClasss = unionGoodsClassService.findAll();
		m.put("goodsClasss", goodsClasss);
		m.put("getCodeUrl", UnionConstants.JD_GETCODE_URL);
		return new ModelAndView("/union/goods/union_goods", m);
	}

	/**
	 * 联盟商品分类修改或保存
	 * 
	 * @param goods
	 * @param id
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	public ModelAndView saveOrUpdate(
			@ModelAttribute UnionGoods goods,
			@RequestParam(required = false, value = "id", defaultValue = "0") int id) {

		Map<String, Object> m = Maps.newHashMap();
		m.put("referer", CommonConstants.ADMIN_SERVER + "/unionGoods/list");

		if (id != 0) {
			m.put("msg", "修改成功");
			UnionGoods oldGoods = unionGoodsService.findById(id);
			if (oldGoods.getSkuId() != goods.getSkuId()) {
				APIResult result = unionJDService.setgoodsinfo(goods);
				if (!"0".equals(result.getCode())) {
					m.put("noAuto", true);
					m.put("msg", "修改失败！" + result.getMsg() + "<br/><br/>"
							+ UnionConstants.JD_ERROR_MSG);
					m.put("referer", "#");
				}
			} else {
				goods.setUnitPrice(oldGoods.getUnitPrice());
				goods.setCommisionRatioPc(oldGoods.getCommisionRatioPc());
				goods.setCommisionRatioWl(oldGoods.getCommisionRatioWl());
				goods.setShopId(oldGoods.getShopId());
				goods.setSource(oldGoods.getSource());
			}
			unionGoodsService.update(goods);
		} else {
			m.put("msg", "保存成功");
			APIResult result = unionJDService.setgoodsinfo(goods);
			if (!"0".equals(result.getCode())) {
				m.put("noAuto", true);
				m.put("msg", "保存失败！" + result.getMsg() + "<br/><br/>"
						+ UnionConstants.JD_ERROR_MSG);
				m.put("referer", "#");
			}
			unionGoodsService.save(goods);
		}

		return new ModelAndView(Constants.MSG_URL, m);
	}

	/**
	 * 联盟商品删除
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
			unionGoodsService.deleteById(id);
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

		UnionGoods goods = unionGoodsService.findById(id);
		if (goods == null) {
			return false;
		}
		goods.setId(id);
		goods.setSort(sort);

		unionGoodsService.update(goods);
		return true;
	}

	/**
	 * 修改排序
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modifyGoodsName")
	public Boolean modifyGoodsName(
			@RequestParam(required = false, value = "id", defaultValue = "0") int id,
			@RequestParam(required = false, value = "value", defaultValue = "") String goodsName) {

		UnionGoods goods = unionGoodsService.findById(id);
		if (goods == null) {
			return false;
		}
		goods.setId(id);
		goods.setGoodsName(goodsName);

		unionGoodsService.update(goods);
		return true;
	}

}
