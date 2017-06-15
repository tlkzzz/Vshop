package com.Vshop.seller.module.trade.controller;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Vshop.core.entity.vo.GoodsTradeVo;
import com.Vshop.seller.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.utils.page.Pager;

/**
 * 交易管理首页
 *      
 * 项目名称：Vshop-seller   
 * 类名称：GoodsTradeAction   
 * 类描述：   
 * 修改备注：   
 * @version    
 *
 */
@Controller
@RequestMapping("/tradegoods")
@Slf4j
public class GoodsTradeAction {
	
	String message = "success";
	@Resource
	private GoodsService goodsService;
	
	/**
	 * 优惠券列表
	 * @param model
	 * @param pageNo
	 * @param couponTitle
	 * @param couponStartDate
	 * @param couponEndDate
	 * @return
	 */
	@RequestMapping("list")
	public String list(
			Model model,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") Integer pageNo
			){
		try {
			Pager pager = new Pager();
			pager.setPageNo(pageNo);
			pager.setPageSize(10);
			Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
			GoodsTradeVo goodsTradeVo=new GoodsTradeVo();
			goodsTradeVo.setStoreId(storeId);
			goodsTradeVo.setTradeGoodsCount("tradeGoodsCount");//按照交易商品数量排序
			goodsTradeVo.setOrderBy("desc");
			pager.setCondition(goodsTradeVo);
			int count = goodsService.findTradeGoodcount(goodsTradeVo);
			List<GoodsTradeVo> list = goodsService.findTradeGoodPagerList(pager);
			model.addAttribute("datas", list);// 结果集
			model.addAttribute("pager", pager);// 分页对象
			model.addAttribute("pageNo", pager.getPageNo());// 当前页
			model.addAttribute("pageSize", pager.getPageSize());// 每页显示条数
			model.addAttribute("recordCount", count);// 总数
			model.addAttribute("toUrl", "/tradegoods/list");
		} catch (Exception e) {
			log.error("商品交易表出错", e);
		}
		return "/trade/goodstradelist";
	}
	
	
}