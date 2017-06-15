package com.Vshop.front.module.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Vshop.core.common.MyBeanUtils;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.GoodsDetailBean;
import com.Vshop.core.entity.base.GoodsRecommend;
import com.Vshop.core.entity.base.RelGoodsRecommend;
import com.Vshop.service.module.goods.service.GoodsRecommendService;
import com.Vshop.service.module.goods.service.RelGoodsRecommendService;

/**
 * 关联课程API接口
 * @author gyh
 * @version 2015-08-25 16:00:00
 */
@Slf4j
@Controller
@RequestMapping("/recommendGoodsApi/api")
public class RecommendGoodsApi{
	
	@Resource
	private GoodsRecommendService goodsRecommendService;
	
	@Resource
	private RelGoodsRecommendService relGoodsRecommendService;
	
	
	/**
	 * 课程栏目api
	 * @param goodsflagsname 栏目名称
	 * @return relGoodsRecommedlist
	 */
	@RequestMapping("Recommedgoodslist")
	@ResponseBody
	public JSONObject RecommedgoodsApi(@RequestParam String goodsflagsname){
		JSONObject jsonObj = new JSONObject();
		List<RelGoodsRecommend> relGoodsRecommedlist=null;
		try {
			List<GoodsDetailBean> list = new ArrayList<GoodsDetailBean>();
			if(goodsflagsname!=null&&!"".equals(goodsflagsname)){
				GoodsRecommend goodsRecommend=new GoodsRecommend();
				goodsRecommend=goodsRecommendService.findBycolum(goodsflagsname);
				if(goodsRecommend!=null){
					relGoodsRecommedlist =relGoodsRecommendService.findgoodsList(goodsRecommend.getReCommendid());
					for (RelGoodsRecommend relgoods : relGoodsRecommedlist) {
						GoodsDetailBean bean = new GoodsDetailBean();
						Goods goods = relgoods.getGoods();
						MyBeanUtils.copyBeanNotNull2Bean(goods, bean);
						list.add(bean);
					}
				}
			}
			if(relGoodsRecommedlist.size() == 0){
				jsonObj.put("result", 0);
				jsonObj.put("msg", "无课程分类");
				jsonObj.put("data", "无课程分类");
			}else{
				jsonObj.put("result", 1);
				jsonObj.put("msg", "获取");
				jsonObj.put("data", JSONArray.fromObject(list, JsonUtils.getJsonConfig()));
			}
		} catch (Exception e) {
			log.error("关联课程API出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "无商");
		}
		
		return jsonObj;
	}
	
	
	
	
	
}
