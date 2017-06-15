package com.Vshop.front.module.user.controller;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Vshop.core.entity.base.Member;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.service.module.member.service.ShopPointsLogService;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：Vshop-front   
 * 类名称：ShopPointsLogAction   
 * 类描述：   
 * 创建人：gyh  
 * 创建时间：2015年7月24日 下午10:15:28   
 * 修改备注：   
 * @version    
 *
 */
@Controller
@RequestMapping("/user/shoppoints")
@Slf4j
public class ShopPointsLogAction {
	
	@Resource
	private ShopPointsLogService shopPointsLogService;
	
	@Resource
	private MemberService memberService;
	
	/**
	 * 导航主页面跳转
	 * 
	 * @Title: mypointslogindex
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/mypointslogindex")
	public ModelAndView dpindex() {
		try {
			ModelAndView model = new ModelAndView("/user/mypointslog/my-mypointslog-index");
			Member member = memberService.findById(CacheUtils.getCacheUser().getMember().getMemberId());
			model.addObject("member", member);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	
	/**
	 * 查询课程收藏日志页面
	 * 
	 * @Title: mypointslogList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/mypointslogList")
	public ModelAndView storeFavList(
			@RequestParam(required=false, value="div",defaultValue="")String div,
			@RequestParam(required=false, value="pageNo",defaultValue="")String pageNo){
		try {
			ModelAndView model = new ModelAndView("/user/mypointslog/my-mypointslog-list");
			Pager pager = new Pager();
	    	if(StringUtils.isNotBlank(pageNo)) {
	             pager.setPageNo(Integer.parseInt(pageNo));
	        }
        	model.addObject("pageNo",pager.getPageNo());// 当前页
            model.addObject("pageSize", pager.getPageSize());// 每页显示条数
            model.addObject("toUrl", "/user/shoppoints/mypointslogList");// 跳转URL
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
}
