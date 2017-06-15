package com.Vshop.front.module.user.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.service.module.trade.service.ConsultService;
import com.Vshop.service.utils.page.Pager;

/**
 * @author llf
 * @Package com.Vshop.front.module.user.controller
 * @Description:
 * @date 2015/3/11 10:50
 */
@Controller
@RequestMapping("/myconsult")
public class MyConsultAction {

	/**
	 * 全部咨询
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(@RequestParam(required=false ,value="pageNo" ,defaultValue="")String pageNo, 
			@RequestParam(required=false ,value="consultReply" ,defaultValue="") String consultReply,@RequestParam(required=false ,value="cur" ,defaultValue="") String cur) {
		ModelAndView model = new ModelAndView("user/consult/cus-consult");
		Pager pager = new Pager();
		if(StringUtils.isNotEmpty(pageNo)){
			pager.setPageNo(Integer.valueOf(pageNo));
		}
		model.addObject("pageNo", pager.getPageNo());// 当前页
		model.addObject("pageSize", pager.getPageSize());// 每页显示条数
		model.addObject("consultReply", consultReply);
		model.addObject("toUrl", "/myconsult/index");
		if(StringUtils.isEmpty(cur)) cur="index";
		model.addObject("cur", cur);
		
		model.addObject("apm", "myconsult");

		return model;
	}

//	/**
//	 * 已回复咨询查询列表
//	 * 
//	 * @return
//	 */
//	@RequestMapping("/replyConsultList")
//	public ModelAndView replyConsultList() {
//		ModelAndView model = new ModelAndView("user/consult/cus-reply-consult");
//		Member member = memberService.findMemberById(CacheUtils.getCacheUser()
//				.getMember().getMemberId());
//		model.addObject("member", member);
//		return model;
//	}
//
//	/**
//	 * 未回复咨询查询列表
//	 * 
//	 * @return
//	 */
//	@RequestMapping("/noreplyConsultList")
//	public ModelAndView noreplyConsultList() {
//		ModelAndView model = new ModelAndView(
//				"user/consult/cus-noreply-consult");
//		Member member = memberService.findMemberById(CacheUtils.getCacheUser()
//				.getMember().getMemberId());
//		model.addObject("member", member);
//		return model;
//	}
//
//	/**
//	 * 咨询查询列表方法
//	 * 
//	 * @param div
//	 * @param pageNo
//	 * @return
//	 */
//	@RequestMapping("/list")
//	public ModelAndView list(
//			@RequestParam(required = false, value = "div", defaultValue = "") String div,
//			@RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNo,
//			@RequestParam(required = false, value = "consultType", defaultValue = "") String consultType) {
//		try {
//			Pager pager = new Pager();
//			Consult consult = new Consult();
//			consult.setMemberId(CacheUtils.getCacheUser().getMember()
//					.getMemberId());
//			if (StringUtils.isNotBlank(pageNo)) {
//				pager.setPageNo(Integer.parseInt(pageNo));
//			}
//			pager.setCondition(consult);
//			pager.setPageSize(10);
//			ModelAndView model = new ModelAndView(
//					"/user/consult/cus-consult-list");
//
//			int count = consultService.findCount(pager);
//			List<Consult> lists = consultService.findList(pager);// 结果集
//			model.addObject("data", lists);// 结果集
//			model.addObject("pageNo", pager.getPageNo());// 当前页
//			model.addObject("pageSize", pager.getPageSize());// 每页显示条数
//			model.addObject("recordCount", count);// 总数
//			model.addObject("toUrl", "/myconsult/list");
//			model.addObject("div", "dataListDiv1");// 显示的DIV数据区域
//			return model;
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error("客户咨询加载失败！");
//			throw new RuntimeException("导航失败!");
//		}
//	}
}
