package com.Vshop.front.module.tag;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.entity.base.Coupon;
import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.coupon.service.CouponService;

import freemarker.template.TemplateModelException;

/**
 * 学院优惠券标签
 * @author kviuff
 * @2015-08-04 11:00:00
 */
@Component
public class CouponTag extends BaseFreeMarkerTag {

	@Resource
	private CouponService couponService;
	
	/**
	 * 学院优惠券标签
	 * @param storeId 学院id
	 */
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		int storeId = ParamsUtils.getInt(params.get("storeId"));
		Coupon coupont=new Coupon();
		coupont.setStoreId(storeId);
		coupont.setCouponState(0);//上、下架状态  1下架，0上架
		coupont.setCouponAllowState(1);//审核状态 0为待审核 1已通过 2未通过
		List<Coupon> couponList = couponService.findCouponbycolumList(coupont);
		for (int i = 0; i < couponList.size(); i++) {
			Coupon coupon = couponList.get(i);
			if(coupon!=null){
				// 优惠券开始时间
//				long sLong = coupon.getStartTime();
//				// 优惠券结束时间
//				long eLong = coupon.getEndTime();
//				// 当前时间
//				Date nowDate = new Date();
				Long nowtime=System.currentTimeMillis();//获取当前时间
				if(nowtime>coupon.getEndTime()){
					couponList.remove(i);
				}
				//long nLong = nowDate.getTime();//获取当前时间
//				if((nLong < sLong && nLong > eLong)){
//					couponList.remove(i);
//				}
		   }	
		}
		return couponList;
	}

}
