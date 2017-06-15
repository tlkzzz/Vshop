package com.Vshop.front.module.user.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Vshop.core.entity.base.Member;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.member.service.MemberService;

/**
 * 我的举报
 * @author Liuk
 */
@Controller
@RequestMapping("/myInform")
public class MyInformAction {
	
	@Resource   
    private MemberService memberService;
	
	@RequestMapping("/index")
    public ModelAndView index(){
		ModelAndView model = new ModelAndView("/user/inform/my-inform-list");
		Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
        model.addObject("member",member);
        return model;
	}
}
