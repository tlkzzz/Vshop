package com.Vshop.admin.module.goodsgroup.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.RelGoodsRecommend;
import com.Vshop.service.module.goods.service.RelGoodsRecommendService;



/**
 * <p>Title: RelGoodsRecommend.java</p>
 * <p>Description: 首页组合商品 新品上市／推荐商品／狂购抢购／猜你喜欢/中间关联</p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author gyh
 * @date 2015年8月25日
 * @version 1.0
 */
@Controller
@RequestMapping("/RelGoodsRecommend")
public class RelGoodsRecommendAction {
	@Resource
	private RelGoodsRecommendService relGoodsRecommendService;
	
	    @RequestMapping(value="/save", method = RequestMethod.POST)
	    public  @ResponseBody Map<String, String> save(
	    	@RequestParam(required=false , value="reCommendid" ,defaultValue="0")Integer reCommendid,
	    	@RequestParam(required=false, value="goodsids",defaultValue="")String goodsids){
	        //转发请求到FTL页面
	    	Map<String, String> map = Maps.newHashMap();
			if (StringUtils.isBlank(goodsids)) {
				map.put("result", "ID为空");
				map.put("message", "true");
				return map;
			}
			if(goodsids!=null&&!"".equals(goodsids)){
				String[] idArray = StringUtils.split(goodsids, ",");
				for (String idStr : idArray) {
					if(!"".equals(idStr)){
						RelGoodsRecommend relGoodsRecommend=new RelGoodsRecommend();
						relGoodsRecommend.setReCommendId(reCommendid);
						relGoodsRecommend.setGoodsId(Integer.valueOf(idStr));
						relGoodsRecommendService.save(relGoodsRecommend);
						relGoodsRecommend=null;
					}
				}
			}
			map.put("result", "保存成功");
			map.put("message", "true");
			return map;
	 }
	    
	    /**
		 * 
		 * @Title: checkrecommendlist
		 * @Description: (加载数据页面)
		 * @return String 返回类型
		 * @throws
		 */
		@RequestMapping("/checkrecommendlist")
		public String recommendlist(@ModelAttribute Goods goods,Model model,
	            @RequestParam(required=false, value="reCommendId",defaultValue="")Integer reCommendId) {

				// 页面查询条件品牌列表
			    List<RelGoodsRecommend> checklist=relGoodsRecommendService.findgoodsList(reCommendId);
			    model.addAttribute("reCommendId",reCommendId);
	            model.addAttribute("checklist",checklist);
				// 转发请求到FTL页面
	            
				return "/goodsgroup/checkrecommendlist";
		}
		
		  @RequestMapping(value="/delete")
		    public  @ResponseBody Map<String, String> delete(
		    	@RequestParam(required=false , value="id" ,defaultValue="0")Integer id){
		        //转发请求到FTL页面
		    	Map<String, String> map = Maps.newHashMap();
		    	relGoodsRecommendService.delete(id);
				map.put("result", "删除成功");
				map.put("success", "true");
				return map;
		 }
}
