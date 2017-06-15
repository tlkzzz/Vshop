package com.Vshop.front.module.tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.core.entity.base.CouponMember;
import com.Vshop.core.entity.searchbean.CouponSearch;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.cart.service.CartService;
import com.Vshop.service.module.coupon.service.CouponMemberService;

import freemarker.template.TemplateModelException;

/**
 * 购物车用户查询可用优惠券
 * @author liukai
 */
@Component
public class CouponMemberTag extends BaseFreeMarkerTag {
	
	@Resource
	private CartService cartService;
	@Resource
	private CouponMemberService couponMemberService;
	
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		CouponSearch couponSearch = new CouponSearch();
		Map<String,Object> map = new HashMap<String, Object>();
		List<CouponMember> couponMemberList = new ArrayList<CouponMember>();
		String cartIds = ParamsUtils.getString(params.get("cartIds")); //多个购物车id
		String couponIsUser = ParamsUtils.getString(params.get("couponIsUser")); //优惠券是否使用
		String currentTime = ParamsUtils.getString(params.get("currentTime"));//获取当前时间
		Integer memberId = CacheUtils.getCacheUser().getMember().getMemberId();
		if(StringUtils.isNotBlank(cartIds)&&!cartIds.equals("null")){
			//通过多个购物车id和用户id查询优惠券集合
			couponMemberList = cartService.queryCouponByCartIds(cartIds, memberId);
			//可以使用的优惠券的数量
			int cannum = 0;
			//不可使用的优惠券的数量
			int nonum = 0;
			for(CouponMember couponMember:couponMemberList){
				if(couponMember.getCouponSuc()==1){ //优惠券不可使用
					nonum += 1;
				}else{ //优惠券可以使用
					cannum += 1;
				}
			}
			map.put("cannum", cannum);
			map.put("nonum", nonum);
		}else{
			if("0".equals(couponIsUser)||"1".equals(couponIsUser)){
				if(StringUtils.isNotBlank(couponIsUser)&&!couponIsUser.equals("null")){
					couponSearch.setCouponIsUser(Integer.parseInt(couponIsUser));
					couponSearch.setMemberId(memberId);
					//通过couponIsUser优惠券是否使用和用户id查询优惠券集合
					couponMemberList = couponMemberService.getCouponListByMemberIdAndStoreId(couponSearch);
				}
			}else{
				if(StringUtils.isNotBlank(currentTime)&&!currentTime.equals("null")){
					couponSearch.setMemberId(memberId);
					couponSearch.setTime(Long.valueOf(currentTime));
					//通过当前时间和用户id查询优惠券集合
					couponMemberList = couponMemberService.getCouponListByMemberIdAndStoreId(couponSearch);
				}
			}
			
			
			
		}
		map.put("couponMemberList", couponMemberList);
		return map;
	}

}
