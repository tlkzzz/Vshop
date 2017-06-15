package com.Vshop.seller.module.product.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.Store;
import com.Vshop.seller.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.module.store.service.StoreGoodsClassService;
import com.Vshop.service.utils.page.Pager;

/**
 * 商品查询类
 * 
 * 项目名称：Vshop-seller 类名称：GoodsCommonAction 类描述： 创建人：weiyue 创建时间：2014年11月28日
 * 下午10:34:56 修改人：weiyue 修改时间：2014年11月28日 下午10:34:56 修改备注：
 * 
 * @version
 * 
 */
@Controller
@RequestMapping("/common")
@Slf4j
public class GoodsCommonAction {

	String message = "success";
	@Resource
    private StoreGoodsClassService storeGoodsClassService;
	
    @Resource
    private GoodsService goodsService;
	/**
	 * 出售中的商品查询
	 * 
	 * @Title: saleList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/saleList")
	public String saleList(
			@RequestParam(required = false, value = "findBy", defaultValue = "") String findBy,
			@RequestParam(required = false, value = "findStr", defaultValue = "") String findStr,
			@RequestParam(required = false, value = "sort", defaultValue = "") String sort,
			@RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNoStr,
			@RequestParam(required = false, value = "div", defaultValue = "") String div,
			Model model) {
		Pager pager = new Pager();
		Goods goods = new Goods();
		goods.setGoodsShow(1);//上架中
		if (!findStr.equals("")) {
			if (findBy.equals("1")) {
				goods.setGoodsName(findStr);
			} else if (findBy.equals("2")) {
				goods.setGoodsSerial(findStr);
			}
		}

		//暂时注释
//		if (!sort.equals("")) {
//			List<String> list = Lists.newArrayList();
//            list.add(sort);
//            List<StoreGoodsClass> classList = storeGoodsClassService.findChild(Integer.parseInt(sort));
//            if(CollectionUtils.isNotEmpty(classList)){
//                list = Collections3.union(list, Collections3.extractToList(classList, "stcId"));
//            }
//            goodsCommon.setSgcIds(list);
//		}
		
		if(StringUtils.isNotBlank(pageNoStr)){
			pager.setPageNo(Integer.parseInt(pageNoStr));
		}
        Store store = CacheUtils.getCacheUser().getStore();//这里注意替换登陆人的商户编号
        goods.setStoreId(store.getStoreId());
		pager.setCondition(goods);// 实体加载在pager中
		

		int total = goodsService.countGoods(goods);// 获取总条数

        List<Goods> gcList = goodsService.findGoodPagerList(pager);
		model.addAttribute("datas", gcList);// 结果集合
		model.addAttribute("store", store);// 结果集合
		model.addAttribute("pageNo", pager.getPageNo());// 当前页
		model.addAttribute("pageSize", pager.getPageSize());// 每页显示条数
		model.addAttribute("recordCount", total);// 总数
		model.addAttribute("toUrl", "common/saleList");// 跳转URL
		model.addAttribute("div", "dataListDiv1");// 显示的DIV数据区域
		return "/product/pro-sale-list";
	}

	
	/**
	 * 仓库中的商品查询(下架商品)
	 * 
	 * @Title: saleList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/storeList")
	public String storeList(
			@RequestParam(required = false, value = "findBy", defaultValue = "") String findBy,
			@RequestParam(required = false, value = "findStr", defaultValue = "") String findStr,
			@RequestParam(required = false, value = "sort", defaultValue = "") String sort,
			@RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNoStr,
			@RequestParam(required = false, value = "div", defaultValue = "") String div,
			Model model) {
		Pager pager = new Pager();
		Goods goods = new Goods();
		List<Goods> gcList = new ArrayList<Goods>();
		goods.setGoodsShow(0);
		if (!findStr.equals("")) {
			if (findBy.equals("1")) {
				goods.setGoodsName(findStr);
			} else if (findBy.equals("2")) {
				goods.setGoodsSerial(findStr);
			}
		}
		
		if(StringUtils.isNotBlank(pageNoStr)){
			pager.setPageNo(Integer.parseInt(pageNoStr));
		}
        Store store = CacheUtils.getCacheUser().getStore();//这里注意替换登陆人的商户编号
        goods.setStoreId(store.getStoreId());
		pager.setCondition(goods);// 实体加载在pager中
		

		int total = goodsService.countGoods(goods);// 获取总条数
		
		gcList = goodsService.findGoodPagerList(pager);
		model.addAttribute("datas", gcList);// 结果集合
		model.addAttribute("store", store);// 结果集合
		model.addAttribute("pageNo", pager.getPageNo());// 当前页
		model.addAttribute("pageSize", pager.getPageSize());// 每页显示条数
		model.addAttribute("recordCount", total);// 总数
		model.addAttribute("toUrl", "common/storeList");// 跳转URL
		model.addAttribute("div", "dataListDiv1");// 显示的DIV数据区域
		return "/product/pro-store-list";
	}
	
	/**
	 * 违规的商品查询
	 * 
	 * @Title: illegalList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/illegalList")
	public String illegalList(
			@RequestParam(required = false, value = "findBy", defaultValue = "") String findBy,
			@RequestParam(required = false, value = "findStr", defaultValue = "") String findStr,
			@RequestParam(required = false, value = "sort", defaultValue = "") String sort,
			@RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNoStr,
			@RequestParam(required = false, value = "div", defaultValue = "") String div,
			Model model) {
		Pager pager = new Pager();
		Goods goods = new Goods();
		List<Goods> gcList = new ArrayList<Goods>();
		goods.setGoodsState(1);//违规下架
		if (!findStr.equals("")) {
			if (findBy.equals("1")) {
				goods.setGoodsName(findStr);
			} else if (findBy.equals("2")) {
				goods.setGoodsSerial(findStr);
			}
		}
		
		if(StringUtils.isNotBlank(pageNoStr)){
			pager.setPageNo(Integer.parseInt(pageNoStr));
		}
        Store store = CacheUtils.getCacheUser().getStore();//这里注意替换登陆人的商户编号
        goods.setStoreId(store.getStoreId());
		pager.setCondition(goods);// 实体加载在pager中
		
		int total = goodsService.countGoods(goods);// 获取总条数
		gcList = goodsService.findGoodPagerList(pager);
		
		model.addAttribute("datas", gcList);// 结果集合
		model.addAttribute("store", store);// 结果集合
		model.addAttribute("pageNo", pager.getPageNo());// 当前页
		model.addAttribute("pageSize", pager.getPageSize());// 每页显示条数
		model.addAttribute("recordCount", total);// 总数
		model.addAttribute("toUrl", "common/storeList");// 跳转URL
		model.addAttribute("div", "dataListDiv1");// 显示的DIV数据区域
		return "/product/pro-illegal-list";
	}
	
//	/**
//	 * 待审核的商品查询
//	 * 
//	 * @Title: pendingList
//	 * @Description: TODO(这里用一句话描述这个方法的作用)
//	 * @param @return 设定文件
//	 * @return ModelAndView 返回类型
//	 * @throws
//	 */
//	@RequestMapping(value = "/pendingList")
//	public String pendingList(
//			@RequestParam(required = false, value = "findBy", defaultValue = "") String findBy,
//			@RequestParam(required = false, value = "findStr", defaultValue = "") String findStr,
//			@RequestParam(required = false, value = "sort", defaultValue = "") String sort,
//			@RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNoStr,
//			@RequestParam(required = false, value = "div", defaultValue = "") String div,
//			Model model) {
//		Pager pager = new Pager();
//		Goods goods = new Goods();
//		List<Goods> gcList = new ArrayList<Goods>();
//		goodsCommon.setGoodsVerify(100);//审核通过的
//		if (!findStr.equals("")) {
//			if (findBy.equals("1")) {
//				goodsCommon.setGoodsName(findStr);
//			} else if (findBy.equals("2")) {
//				goodsCommon.setGoodsSerial(findStr);
//			}
//		}
//
//		if (!sort.equals("")) {
//			
//			goodsCommon.setGoodsStcids(sort);
//		}
//		
//		if(StringUtils.isNotBlank(pageNoStr)){
//			pager.setPageNo(Integer.parseInt(pageNoStr));
//		}
//        Store store = CacheUtils.getCacheUser().getStore();//这里注意替换登陆人的商户编号
//        goodsCommon.setStoreId(store.getStoreId());
//		pager.setCondition(goodsCommon);// 实体加载在pager中
//		
//
//		int total = goodsService.countGoods(pager);// 获取总条数
//		gcList = goodsService.findGoodList(pager);
//		model.addAttribute("datas", gcList);// 结果集合
//		model.addAttribute("store", store);// 结果集合
//		model.addAttribute("pageNo", pager.getPageNo());// 当前页
//		model.addAttribute("pageSize", pager.getPageSize());// 每页显示条数
//		model.addAttribute("recordCount", total);// 总数
//		model.addAttribute("toUrl", "common/storeList");// 跳转URL
//		model.addAttribute("div", "dataListDiv1");// 显示的DIV数据区域
//		return "/product/pro-pending-list";
//	}
	/**
	 * 商户商品
	 * 
	 * @Title: delete
	 * @Description: 删除方法
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> delete(@RequestParam(value = "ids") String ids,
			Model model) {

		Map<String, String> map = Maps.newHashMap();

		// 先验证如数信息是否正确
		if (StringUtils.isBlank(ids)) {
			model.addAttribute("result", "ID为空");
			map.put("result", "ID为空");
			map.put(message, "true");
			return map;
		}
		String[] idArray = StringUtils.split(ids, ",");
		for (String idStr : idArray) {
			goodsService.deleteGoods(Integer.valueOf(idStr));
		}
		map.put("result", "删除成功");
		map.put(message, "true");
		return map;

	}
	
	/**
	 * 商户下架
	 * 
	 * @Title: updateDownload
	 * @Description: 删除方法
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/updateDown", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> updateDown(@RequestParam(value = "ids") String ids,
			Model model) {

		Map<String, String> map = Maps.newHashMap();

		// 先验证如数信息是否正确
		if (StringUtils.isBlank(ids)) {
			model.addAttribute("result", "ID为空");
			map.put("result", "ID为空");
			map.put(message, "true");
			return map;
		}
		
		
		String[] idArray = StringUtils.split(ids, ",");
		Goods goods = new Goods();
		goods.setGoodsShow(0);//下架
		for (String idStr : idArray) {
			goods.setGoodsId(Integer.valueOf(idStr));
//			goodsService.updateGoods(goodsCommon);
		}
		map.put("result", "操作成功");
		map.put(message, "true");
		return map;

	}
	
	/**
	 * 商户上架
	 * 
	 * @Title: updateDownload
	 * @Description: 删除方法
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/updateUp", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> updateUp(@RequestParam(value = "ids") String ids,
			Model model) {

		Map<String, String> map = Maps.newHashMap();

		// 先验证如数信息是否正确
		if (StringUtils.isBlank(ids)) {
			model.addAttribute("result", "ID为空");
			map.put("result", "ID为空");
			map.put(message, "true");
			return map;
		}
		
		
		String[] idArray = StringUtils.split(ids, ",");
		Goods goods=new Goods();
		goods.setGoodsShow(1);//0 已下架,1上架
		for (String idStr : idArray) {
			goods.setGoodsId(Integer.valueOf(idStr));
//            shopGoodsCommonService.updateGoods(goodsCommon);
		}
		map.put("result", "操作成功");
		map.put(message, "true");
		return map;

	}
}