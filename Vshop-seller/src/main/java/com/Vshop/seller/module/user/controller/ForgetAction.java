package com.Vshop.seller.module.user.controller;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.Vshop.core.common.NumberUtils;
import com.Vshop.core.entity.base.Member;
import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.service.module.user.service.ForgetService;
import com.Vshop.service.sms.sender.Sender;

/**
 * 找回密码
 * cgl
 * 2015年08月21日11:18:28
 */
@Controller
@RequestMapping("/forget")
@Slf4j
public class ForgetAction {

	
	@Resource
	private ForgetService forgetService;
	
    @Resource
    private MemberService memberService;
    
//    @Resource
//	private MessageService messageService;
	
	/**
	 * 导航主页面跳转
	 * 
	 * @Title: index
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request){
		try {
			String flags=request.getParameter("flags");
			ModelAndView model = new ModelAndView("/user/forget/forget");
			model.addObject("flags", flags);
			return model;
		} catch (Exception e) {
			log.error("找回密码首页加载失败！",e);
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @Title: deleteAddress 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param ids
	 * @param @param model
	 * @param @return    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> sendMail(@RequestParam(value = "memberEmail") String memberEmail,
			@RequestParam(value = "memberName") String memberName,
			Model model) throws Exception {
		int result = forgetService.saveSendEmail(memberName, memberEmail);
		Map<String, String> map = Maps.newHashMap();
		if(result == 1){
			map.put("success", "true");
		}else if(result == 2){
			map.put("success", "false");
			map.put("message", "邮箱不正确！");
		}else{
			map.put("success", "false");
			map.put("message", "发送邮箱失败！");
		}
		return map;
	}
	
	/**
	 * 验证邮箱数据
	 * @Title: activate 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param username
	 * @param @param code
	 * @param @return    设定文件 
	 * @return ModelAndView    返回类型 
	 * @throws
	 */
	@RequestMapping("/activate")
	public ModelAndView activate(@RequestParam(value = "username") String username,
			@RequestParam(value = "code") String code) {
		try {
			ModelAndView model = new ModelAndView("/user/forget/forget-reset");
			
			Member member = memberService.findMemberByName(username);
			
			if(!code.equals(member.getMemberPasswd())){
				model = new ModelAndView("/user/forget/forget-error");
			}
			model.addObject("member", member);
			return model;
		} catch (Exception e) {
			log.error("卖家中心首页加载失败！",e);
			throw new RuntimeException("导航失败!");
		}
	}
	
	@RequestMapping(value = "/updatePass", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> updatePass(@RequestParam(value = "memberId") String memberId,
			@RequestParam(value = "newPasswd") String newPasswd,
			Model model) throws Exception {
		
		Map<String, String> map = Maps.newHashMap();
		
        int result = memberService.updatePass(newPasswd,Integer.valueOf(memberId));
		if(result == 1){
			map.put("success", "true");
		}else{
			map.put("success", "false");
			map.put("message", "重置密码失败！");
		}
		return map;
	}
	
	@RequestMapping("/success")
	public ModelAndView success() {
		try {
			ModelAndView model = new ModelAndView("/user/forget/forget-success");
			return model;
		} catch (Exception e) {
			log.error("找回密码首页加载失败！",e);
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * @Description:手机号 获取验证码的方法
	 * @param phone
	 * @param currentTimeMillis
	 * @param @param model
	 * @return Map<String,String>    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/getValid", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> sendValid(@RequestParam(value = "mobile") String mobile, @RequestParam(value = "currStr") String currStr, Model model)  {
		Map<String, String> map = Maps.newHashMap();
		Integer valid = NumberUtils.getRandomInt(99999);
		
		try {
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			
			Sender.send(mobile, String.format("验证码：%s", valid));
			
			session.setAttribute("validCode", valid);//设置验证码时间
			session.setAttribute("recordTime", System.currentTimeMillis());//设置当前时间点
			session.setAttribute("mobile", mobile);
					
			map.put("success", "true");
			map.put("validCode", valid + "");
			map.put("message", "一分钟后可重新获取");
		} catch (Exception e) {
			log.error("获取验证码失败",e);
			map.put("success", "false");
			map.put("message", "获取验证码失败！");
			return map;
		}
		return map;
	}
	
	
	/**
	 * 手机号 验证码校验
	 * @param validCode
	 * @param phone
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/checkValidCode" , method=RequestMethod.POST)
	public String checkValidCode(@RequestParam(value="validCode") String validCode, 
			@RequestParam(value="mobile") String mobile,@RequestParam(value="flags") String flags, Model model){
		mobile = mobile.trim();
		validCode = validCode.trim();
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		
		Object code = session.getAttribute("validCode");//设置验证码时间
		Long recordTime =  (Long) session.getAttribute("recordTime");//发送验证码的时候设置的时间点
		if(validCode==""||mobile==""||code==null||recordTime==null){
			model.addAttribute("error", "验证码或手机号不能为空");
			return "/user/forget/forget";
		}
		Long now = System.currentTimeMillis();
		log.debug("1时间=="+(now));
		log.debug("2时间=="+(recordTime));
		log.debug("记录时间=="+(now-recordTime));
		if((now-recordTime)>65000){
			model.addAttribute("error", "验证码已经失效");
			return "/user/forget/forget";
		}
		if(validCode.length()==0||!validCode.equals(code.toString())){
			model.addAttribute("error", "验证码错误");
			return "/user/forget/forget";
		}
		if(!session.getAttribute("mobile").equals(mobile)){
			model.addAttribute("error", "手机输入不正确!");
			return "/user/forget/forget";
		}
		//model.addAttribute("username", mobile);
		return "forward:/forget/toRest?mobilep="+mobile+"&vCode="+validCode+"&flags="+flags;
	}
	
	
	/**
	 * 验证邮箱数据
	 * @Title: activate 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param username
	 * @param @param code
	 * @param @return    设定文件 
	 * @return ModelAndView    返回类型 
	 * @throws
	 */
	@RequestMapping("/toRest")
	public ModelAndView toRest(@RequestParam(value = "mobilep") String mobilep,
			@RequestParam(value = "vCode") String vCode,HttpServletRequest request) {
		try {
			String flags=request.getParameter("flags");
			ModelAndView model = new ModelAndView("/user/forget/forget-reset");
			Member member = memberService.findMemberByMobile(mobilep);
			if(member==null){
				model =new ModelAndView("/user/forget/forget");
				model.addObject("error", "用户不存在；找回密码失败！");
				return model;
			}
			model.addObject("member", member);
			model.addObject("validCode", vCode);
			model.addObject("flags", flags);
			return model;
		} catch (Exception e) {
			log.error("卖家中心首页加载失败！",e);
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 修改密码
	 * @param memberid
	 * @param validCode
	 * @param newPasswd
	 * @param flags
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatePWD", method = RequestMethod.POST)
	public @ResponseBody String updatePWD(@RequestParam(value = "memberid") String memberid,
			@RequestParam(value = "validCode") String validCode,
			@RequestParam(value = "newPasswd") String newPasswd,
			@RequestParam(value = "flags") String flags,
			HttpServletRequest request) throws Exception {
		if(StringUtils.isEmpty(validCode)||StringUtils.isEmpty(memberid)||StringUtils.isEmpty(newPasswd)){
			return "fail";
		}
        int result = memberService.updatePass(newPasswd, Integer.valueOf(memberid));
		if(result!=1){
			return "fail";
		}
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		session.removeAttribute("validCode");
		return "success";
	}
	/**
	 * 
	 * @Title: updateAddress 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return ModelAndView    返回类型 
	 * @throws
	 *//*
	@RequestMapping("/updateAddress")
	public ModelAndView updateAddress(@RequestParam(value = "id") String addressId) {
		try {
			ModelAndView model = new ModelAndView("/user/address/my-address-update");
			Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
	        model.addObject("member",member);
			Address address = addressService.queryById(Integer.valueOf(addressId));
			
			Area area = areaService.queryParentList(address.getCityId());
			
			// 尾部菜单
			List<ArticleClassTitleVo> listArticleClassTitleVo = articleClassService.findTitleList();
			
			model.addObject("listArticleClassTitleVo", listArticleClassTitleVo);
			
			List<Area> areas = areaService.queryAll();
			
			model.addObject("area",area);
			model.addObject("address",address);
			model.addObject("areas",areas);
			model.addObject("memberId",CacheUtils.getCacheUser().getMember().getMemberId());
			model.addObject("titleName", "收货地址");
			model.addObject("cur", "address");
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	

	*//**
	 * 
	 * @Title: updateAddress 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param jsondata
	 * @param @param model
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @throws
	 *//*
	@RequestMapping(value = "/updateAddress", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> updateAddress(@RequestParam(value = "data") String jsondata,Model model) throws Exception {
		Map<String, String> map = Maps.newHashMap();
		int result = addressService.updateAddress(jsondata);
		if(result == 1){
			map.put("success", "true");
		}else{
			map.put("success", "false");
		}
		return map;
	}
	
	*//**
	 * 
	 * @Title: updateDef 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param addressId
	 * @param @param model
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @throws
	 *//*
	@RequestMapping(value = "/updateDef", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> updateDef(@RequestParam(value = "addressId") String addressId) throws Exception {

		Map<String, String> map = Maps.newHashMap();
		int result = addressService.updateDef(addressId, CacheUtils.getCacheUser().getMember().getMemberId().toString());
		if(result == 1){
			map.put("success", "true");
		}else{
			map.put("success", "false");
		}
		return map;
	}
	*/
	
}
