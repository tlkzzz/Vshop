package com.Vshop.front.module.api;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Vshop.core.entity.base.CouponMember;
import com.Vshop.core.entity.searchbean.CouponSearch;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.service.module.coupon.service.CouponMemberService;

/**
 * 用户优惠券接口
 * @author liukai
 */
@Slf4j
@Controller
@RequestMapping("/couponApi")
public class CouponApi extends BaseApi {
	
	@Resource
    private CouponMemberService couponMemberService;
	
	/**
	 * 用户优惠券列表
	 * @param memberId
	 * @return
	 */
	@RequestMapping("couponMemberList")
	@ResponseBody
	public JSONObject cartList(
			@RequestParam(value = "memberId") Integer memberId
			){
		JSONObject jsonObj = new JSONObject();
		try {
			CouponSearch couponSearch = new CouponSearch();
			couponSearch.setMemberId(memberId);
			//通过用户id查询用户所有的优惠券
			List<CouponMember> couponMemberList = couponMemberService.getCouponListByMemberIdAndStoreId(couponSearch);
			for (CouponMember couponMember : couponMemberList) {
				// 优惠券结束时间
				long eLong = couponMember.getEndTime();
				// 当前时间
				long nLong = System.currentTimeMillis();
				if(nLong <= eLong){
					couponMember.setTimeLimit("1");
				}else{
					couponMember.setTimeLimit("0");
				}
			}
			jsonObj.put("result", 1);
			jsonObj.put("data", JSONArray.fromObject(couponMemberList, JsonUtils.getJsonConfig()));
		} catch (Exception e) {
			log.error("用户优惠券列表API出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "无数据");
		}
		return jsonObj;
	}

}
