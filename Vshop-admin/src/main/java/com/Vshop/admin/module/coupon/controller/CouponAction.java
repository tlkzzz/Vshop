package com.Vshop.admin.module.coupon.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jersey.repackaged.com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.Vshop.core.common.Constants;
import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.base.Coupon;
import com.Vshop.core.entity.base.CouponClass;
import com.Vshop.core.entity.base.CouponGoods;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.Sponsor;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.core.ossconfigure.OSSConfigure;
import com.Vshop.core.ossconfigure.OSSManageUtil;
import com.Vshop.core.platform.info.PlatformInfo;
import com.Vshop.service.module.coupon.service.CouponClassService;
import com.Vshop.service.module.coupon.service.CouponService;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.module.operation.service.SponsorService;
import com.Vshop.service.utils.CommonConstants;
import com.Vshop.service.utils.page.Pager;


/**
 * 优惠券
 * @author kviuff
 * @date 2015-07-23 10:00:00
 */
@Controller
@RequestMapping("/coupon")
@Slf4j
public class CouponAction {
	
	
	@Resource
	private CouponService couponService;
	
	@Resource
	private CouponClassService couponClassService;
	
	@Resource
	private SponsorService sponsorService;
	
	@Resource
	private GoodsService goodsService;
	
	/**
	 * 优惠券列表
	 * @return
	 */
	@RequestMapping("list")
	public String list(
			Model model,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(required = false, value = "couponAllowState") String couponAllowState
			
			){
		try {
			int status = 0;
			Pager pager = new Pager();
			pager.setPageNo(pageNo);
			
			Coupon coupn = new Coupon();
			if(StringUtils.isNotEmpty(couponAllowState)){
				coupn.setCouponAllowState(Integer.parseInt(couponAllowState));
				status = 1;
			}
			pager.setCondition(coupn);
			
			List<Coupon> list = couponService.findCouponPagerList(pager);
			pager.setResult(list);
			model.addAttribute("datas", list);// 结果集
			model.addAttribute("pager", pager);
			model.addAttribute("status", status);
			
		} catch (Exception e) {
			log.error("优惠券列表出错", e);
		}
		
		return "/coupon/coupon/list";
	}
	
	
	/**
	 * 跳转到新增页面
	 * @param model
	 * @return
	 */
	@RequestMapping("add")
	public String add(Model model){
		try {
			List<CouponClass> list = couponClassService.findAllCouponList();
			model.addAllAttributes(ImmutableMap.of("classlist", list, "sponsorList", sponsorService.findList()));
		} catch (Exception e) {
			log.error("优惠券新增出错", e);
		}
		return "/coupon/coupon/add";
	}
	
	
	/**
	 * 优惠券列表
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(
			Model model,
			@RequestParam(required = false, value = "ids") String ids,
			HttpServletRequest request
			){
		try {
			String referer = request.getHeader("Referer");
			model.addAttribute("referer", referer);
			if (StringUtils.isBlank(ids)) {
				model.addAttribute("result", "ID为空");
				model.addAttribute("msg", "删除失败，ID为空");
			}else{
				String[] idArray = StringUtils.split(ids, ",");
				for (String idStr : idArray) {
					couponService.deleteCoupon(Integer.parseInt(idStr));
				}
				model.addAttribute("msg", "删除成功");
			}
		} catch (Exception e) {
			log.error("优惠券列表出错", e);
		}
		
		return Constants.MSG_URL;
	}
	
	/**
	 * 跳转到编辑页面
	 * @param model
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("edit")
	public String edit(
			Model model,
			@RequestParam(required = false, value = "id") Integer id,
			HttpServletRequest request
			){
		try {
			Coupon coupon = couponService.getCouponById(id);
			Sponsor sponsor = sponsorService.findById(coupon.getSponsorId() == null ? 0 : coupon.getSponsorId());
			List<Goods> goodsList = couponService.findGoodsByCouponId(id);
			model.addAllAttributes(ImmutableMap.of("coupon", coupon, "sponsor", sponsor == null ? new Sponsor() : sponsor, "goodsList", goodsList));
		} catch (Exception e) {
			log.error("优惠券编辑出错", e);
		}
		return "/coupon/coupon/edit";
	}
	
	/**
	 * 保存
	 * @param couponClass
	 * @param model
	 * @return
	 */
	@RequestMapping("update")
	public String update(
			@ModelAttribute Coupon coupon,
			Model model, 
			HttpServletRequest request
			){
		try {
			model.addAttribute("referer", CommonConstants.ADMIN_SERVER + "/coupon/list");
			coupon.setCreateTime(System.currentTimeMillis());
			couponService.updateCoupon(coupon);
			model.addAttribute("msg", "编辑成功");
		} catch (Exception e) {
			log.error("优惠券保存出错", e);
			model.addAttribute("msg", "编辑失败");
		}
		return Constants.MSG_URL;
	}
	
	
	/**
	 * 保存
	 * @param couponClass
	 * @param model
	 * @return
	 */
	@RequestMapping("saveOrUpdate")
	public String saveOrUpdate(
			@ModelAttribute Coupon coupon, @RequestParam("goodsIds") String goodsIds,
			Model model,
			HttpServletRequest request
			){
		try {
			model.addAttribute("referer", CommonConstants.ADMIN_SERVER + "/coupon/list");
			
			Integer storeId =  PlatformInfo.PLATFORM_STORE_ID;
			String storeName = PlatformInfo.PLATFORM_STORE_NAME;
			coupon.setStoreId(storeId);
			coupon.setStoreName(storeName);
			String couponStartDate = request.getParameter("couponStartDate");
			String couponEndDate = request.getParameter("couponEndDate");
			if(StringUtils.isNotEmpty(couponStartDate)&&StringUtils.isNotEmpty(couponEndDate)){
				//优惠券开始时间
				coupon.setStartTime(DateUtils.strToLong(couponStartDate+" 00:00:00"));
				if(couponStartDate.equals(couponEndDate)){
					//优惠券截止时间
					coupon.setEndTime(DateUtils.strToLong(couponEndDate+" 23:59:59"));
				}else{
					//优惠券戒指时间
					coupon.setEndTime(DateUtils.strToLong(couponEndDate+" 00:00:00"));
				}
			}
			
			if(coupon.getCouponId() == null){
				coupon.setCouponState(0);
				coupon.setCouponusage(0);
				coupon.setCouponIock(0);
				coupon.setCoupClick(1);
				coupon.setCouponRecommend(0);
				coupon.setCouponAllowState(0);
				
				
				/*SqlSession session = SqlSessionUtils.getSqlSession();
				session.commit(false);
				CouponMapper mp = session.getMapper(CouponMapper.class);
				mp.saveCoupon(coupon);*/
				
				/*try {
					Integer couponId = coupon.getCouponId();
					List<CouponGoods> couponGoodsList = Lists.newArrayList();
					for(String goodsId : Arrays.asList(StringUtils.split(goodsIds, ","))){
						CouponGoods couponGoods = new CouponGoods();
						couponGoods.setGoodsId(Integer.valueOf(goodsId));
						couponGoods.setCouponId(null);
						
						couponGoodsList.add(couponGoods);
					}
					mp.saveCouponGoods(couponGoodsList);
					session.commit();
				} catch (Exception e) {
					session.rollback(true);
					session.close();
					e.printStackTrace();
				}*/
				
				if(StringUtils.isBlank(goodsIds)) throw new Exception("优惠券不能为空");
				couponService.saveCoupon(coupon);
				
				Integer couponId = coupon.getCouponId();
				List<CouponGoods> couponGoodsList = Lists.newArrayList();
				for(String goodsId : Arrays.asList(StringUtils.split(goodsIds, ","))){
					CouponGoods couponGoods = new CouponGoods();
					couponGoods.setGoodsId(Integer.valueOf(goodsId));
					couponGoods.setCouponId(couponId);
					
					couponGoodsList.add(couponGoods);
				}
				couponService.saveCouponGoods(couponGoodsList);
			}else{
				couponService.updateCoupon(coupon);
			}
			model.addAttribute("msg", "新增成功");
		} catch (Exception e) {
			log.error("优惠券保存出错", e);
			model.addAttribute("msg", "新增失败");
		}
		return  Constants.MSG_URL;
	}
	
	
	/**
	 * 通过couponClassId获取优惠券
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findCouponByClassId", method = RequestMethod.POST)
	public Map<String,Object> findCouponByClassId(
			@RequestParam(value = "couponClassId") Integer couponClassId
			){
		Map<String,Object> map = Maps.newHashMap();
		try {
			
			List<Coupon> list = couponService.findCouponByClassId(couponClassId);
			int size = 0;
			if(list!=null){
				size = list.size();
			}
			map.put("size",size);
			map.put("couponClassId", couponClassId);
			return map;
		} catch (Exception e) {
			log.error("优惠券列表出错", e);
		}
		return map;
	}
	
	@RequestMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam MultipartFile[] files,
                             HttpServletRequest request, HttpServletResponse response) throws IOException {
        //可以在上传文件的同时接收其它参数
        Map<String, Object> map = Maps.newHashMap();
        try {
       	 OSSConfigure ossConfigure=new OSSConfigure();
   		 map =OSSManageUtil.uploadFile(ossConfigure, files, Constants.IMGALBUM_UPLOAD_URL,"images",request);
		
        } catch (Throwable e) {
            e.printStackTrace();
            log.error("上传文件失败", e.toString());
        }
        String json = JsonUtils.toJsonStr(map);
        response.setContentType("text/html");
        response.getWriter().write(json);

        return null;
    }
	
	@RequestMapping("goods/list")
	public @ResponseBody String goodsList(Model model, 
		   @RequestParam(required = false, value = "page", defaultValue = "1") Integer page,
		   @RequestParam(required = false, value = "rows", defaultValue = "10") Integer rows){
		try {
			Pager pager = new Pager();
			pager.setPageNo(page);
			pager.setPageSize(rows);
			
			Goods goods = new Goods();
			pager.setCondition(goods);// 实体加载在pager中
			
			List<Goods> list = goodsService.findGoodPagerList(pager);
			pager.setResult(list);
			return JSON.toJSONString(ImmutableMap.of("total", pager.getTotalRows(), "rows", pager.getResult()));
		} catch (Exception e) {
			log.error("优惠券列表出错", e);
		}
		
		return null;//JSON.toJSONString(list);
	}
	
}
