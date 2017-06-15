package com.Vshop.front.module.shopstatgoods.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.Vshop.core.common.IpUtil;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.ShopStatGoods;
import com.Vshop.core.ip.IPSeeker;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.goods.service.ShopStatGoodsService;
import com.Vshop.service.module.member.service.MemberService;

/**
 * @项目名称：Vshop-seller
 * @类名称：    ShopStatGoodsAction
 * @类描述：    课程浏览记录表
 * @创建人：    gyh
 * @创建时间：2015年7月23日 下午17:39:53
 * 
 */
@Controller
@RequestMapping("/shopStatGoods")
@Slf4j
public class ShopStatGoodsAction {
	@Resource
	private ShopStatGoodsService shopStatGoodsService;
	@Resource
	private MemberService memberService;

	/**
	 * 保存浏览记录
	 */
	@RequestMapping("/saveShopStatGoods")
	public @ResponseBody
	Map<String, Object> saveShopStatGoods(
			@RequestParam(required = false, value = "storeId", defaultValue = "") Integer storeId,
			@RequestParam(required = false, value = "goodsId", defaultValue = "") Integer goodsId,
			@RequestParam(required = false, value = "goodsName", defaultValue = "") String goodsName,
			HttpServletRequest request) {
		Map<String, Object> map = Maps.newHashMap();
		Subject currentUser = SecurityUtils.getSubject();
		Member member=null;
		ShopStatGoods shopStatGoods=new ShopStatGoods();//实例化对象
		try {
			shopStatGoods.setGoodsId(goodsId);//课程id
			shopStatGoods.setGoodsName(goodsName);//课程名称
			shopStatGoods.setStoreId(storeId);//学院id
			//判断是已登录用户还是游客
			if (currentUser.isAuthenticated()) {
			if (CacheUtils.getCacheUser() != null && CacheUtils.getCacheUser().getMember().getMemberId() != null) {
				shopStatGoods.setMemberId(CacheUtils.getCacheUser().getMember().getMemberId());
				shopStatGoods.setMemberName(CacheUtils.getCacheUser().getMember().getMemberName());
				member=memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
			  }else{
				shopStatGoods.setMemberName("游客");
			  }
			}
			//根据ip得到相应的的归属地
			IPSeeker ip=  IPSeeker.getInstance();
			String infos=ip.getCountry(IpUtil.getIpAddr(request));
			String[] str=null;
			if(infos!=null&&!"".equals(infos)){
				str=infos.split("省");
				shopStatGoods.setProvince(str[0]+"省");
				//shopStatGoods.setCity(str[1]);
			}
			shopStatGoods.setLoginIp(IpUtil.getIpAddr(request));
			if(member!=null){
				shopStatGoods.setLoginTime(member.getMemberLoginTime());//保存登陆时间
			}
			shopStatGoodsService.save(shopStatGoods);
			map.put("success",true);
			map.put("msg", "成功");
		} catch (Exception e) {
			log.error("失败", e);
			map.put("success", false);
			map.put("msg", "失败");
		}
		return map;
	}
	
	    
}
