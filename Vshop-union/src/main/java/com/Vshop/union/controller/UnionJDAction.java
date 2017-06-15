package com.Vshop.union.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.Vshop.core.common.Constants;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.union.entity.APIResult;
import com.Vshop.union.entity.UnionGoods;
import com.Vshop.union.entity.UnionGoodsClass;
import com.Vshop.union.service.UnionGoodsClassService;
import com.Vshop.union.service.UnionGoodsService;
import com.Vshop.union.service.UnionJDService;
import com.Vshop.union.utils.CookieUtils;
import com.Vshop.union.utils.UnionConstants;

/**
 * 京东联盟对接
 * 
 * @author liuzhen
 * @version 2015-9-23
 */
@Slf4j
@Controller
@RequestMapping("/unionJD")
public class UnionJDAction {

	/** 联盟接口 */
	@Resource
	private UnionJDService unionJDService;
	/** 联盟商品管理接口 */
	@Resource
	private UnionGoodsService unionGoodsService;
	/** 联盟商品分类管理接口 */
	@Resource
	private UnionGoodsClassService unionGoodsClassService;

	/**
	 * 应用的回调地址,用于获取授权码code以及state参数
	 * 
	 * @param request
	 */
	@RequestMapping("/callBack")
	public ModelAndView callBack(
			@RequestParam(required = false, value = "code", defaultValue = "") String code,
			@RequestParam(required = false, value = "state", defaultValue = "") String state) {

		Map<String, Object> m = Maps.newHashMap();
		m.put("noAuto", true);
		m.put("referer", "#");

		log.debug("state:" + state + "\t" + "code:" + code);
		if (UnionConstants.JD_CODE_STATE.equals(state)) {
			UnionConstants.JD_CODE = code;

			String returnMsg = "<br/>code更新成功,新code值为：" + code + "<br/><br/>";

			APIResult token = unionJDService.getAccessToken();

			if (!"0".equals(token.getCode())) {
				returnMsg += "获取AccessToken失败！<span style=\"color:red;\">错误码："
						+ token.getCode() + "</span>" + "<br/><br/>"
						+ UnionConstants.JD_ERROR_MSG;
			} else {
				returnMsg += "已经可以正常操作！！";
			}

			m.put("msg", returnMsg);
		} else {
			m.put("msg", "<br/>您没有做任何操作<br/>");
		}

		return new ModelAndView(Constants.MSG_URL, m);
	}

	/**
	 * 展品展示首页
	 * 
	 * @param subUnionId
	 *            子联盟帐号
	 * @param response
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(
			@RequestParam(required = false, value = "subUnionId", defaultValue = "") String subUnionId,
			HttpServletResponse response) {

		if (StringUtils.isNotBlank(subUnionId)) {
			// 将子联盟帐号加入cookie
			CookieUtils.addCookie(response, "subUnionId", subUnionId,
					60 * 60 * 24);
		}

		Map<String, Object> m = Maps.newHashMap();

		int indexGoodsNum = 16;

		m.put("mjsGoods", unionGoodsService.findmjs(indexGoodsNum));
		m.put("jxtmGoods", unionGoodsService.findjxtm(indexGoodsNum));
		m.put("rxGoods", unionGoodsService.findrx(indexGoodsNum));

		return new ModelAndView("/union/jd/union_jd_index", m);
	}

	/**
	 * 商品列表
	 * 
	 * @param classId
	 *            商品分裂id
	 * @param type
	 *            类型(mjs：满就送、jxtm：精选特卖、rx：热销商品)
	 * @param pageNo
	 *            页码
	 * @param keyword
	 *            关键字
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(required = false, value = "classId", defaultValue = "0") int classId,
			@RequestParam(required = false, value = "type", defaultValue = "") String type,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "keyword", defaultValue = "") String keyword) {
		Map<String, Object> m = Maps.newHashMap();

		List<UnionGoods> goodsList = unionGoodsService.findGoodsList(classId,
				type, pageNo, keyword);

		UnionGoodsClass goodsClass = null;
		if (classId != 0) {
			goodsClass = unionGoodsClassService.findById(classId);
		}

		if (goodsClass != null) {
			if (goodsClass.getHasChild() > 0) {
				m.put("goodsClasss",
						unionGoodsClassService.findFrontListByPid(classId));
			} else {
				m.put("goodsClasss", unionGoodsClassService
						.findFrontListByPid(goodsClass.getPid()));
			}
			m.put("pClass",
					unionGoodsClassService.findById(goodsClass.getPid()));
		} else {
			m.put("goodsClasss", unionGoodsClassService.findFrontListByPid(0));
		}

		m.put("goodsList", goodsList);
		m.put("classId", classId);

		m.put("type", type);
		m.put("pageNo", pageNo);
		m.put("keyword", keyword);

		return new ModelAndView("/union/jd/union_jd_list", m);
	}

	/**
	 * 商品列表
	 * 
	 * @param classId
	 *            商品分裂id
	 * @param type
	 *            类型(mjs：满就送、jxtm：精选特卖、rx：热销商品)
	 * @param pageNo
	 *            页码
	 * @param keyword
	 *            关键字
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listForJSON")
	public JSONObject listForJSON(
			@RequestParam(required = false, value = "classId", defaultValue = "0") int classId,
			@RequestParam(required = false, value = "type", defaultValue = "") String type,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "keyword", defaultValue = "") String keyword) {

		List<UnionGoods> goodsList = unionGoodsService.findGoodsList(classId,
				type, pageNo, keyword);

		JSONObject obj = new JSONObject();
		obj.accumulate("s", 1);

		if (goodsList != null && goodsList.size() > 0) {
			obj.accumulate("r",
					JSONArray.fromObject(goodsList, JsonUtils.getJsonConfig()));
		}
		return obj;
	}

	/**
	 * 商品详情页
	 * 
	 * @param goodsId
	 *            商品id
	 * @param request
	 * @return
	 */
	@RequestMapping("/goodsDetail")
	public ModelAndView goodsDetail(
			@RequestParam(required = false, value = "id", defaultValue = "0") int goodsId,
			HttpServletRequest request) {

		Map<String, Object> m = Maps.newHashMap();

		UnionGoods goods = unionGoodsService.findById(goodsId);
		m.put("goods", goods);

		Cookie cookie = CookieUtils.getCookieByName(request, "subUnionId");
		String subUnionId = null;
		if (cookie != null) {
			subUnionId = cookie.getValue();
		}

		if (StringUtils.isNotBlank(subUnionId)) {
			APIResult result = unionJDService.getOnePromotionCode(
					goods.getMaterialUrl(), subUnionId);
			String jdUrl = "";
			if ("0".equals(result.getCode())) {
				jdUrl = result.getMsg();
			}else{
				m.put("errorCode", "token_invalid");
			}
			m.put("jdUrl", jdUrl);
		}else{
			m.put("errorCode", "subUnionId_is_null");
		}

		return new ModelAndView("/union/jd/union_jd_goods_detail", m);
	}

	/**
	 * 会员中心
	 * 
	 * @return
	 */
	@RequestMapping("/memberCenter")
	public ModelAndView memberCenter() {
		Map<String, Object> m = Maps.newHashMap();

		return new ModelAndView("/union/jd/union_jd_memberCenter", m);
	}
}
