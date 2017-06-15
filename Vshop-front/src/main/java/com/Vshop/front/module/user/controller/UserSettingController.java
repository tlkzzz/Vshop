package com.Vshop.front.module.user.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.Area;
import com.Vshop.core.entity.base.Member;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.area.service.AreaService;
import com.Vshop.service.module.member.service.MemberService;

/**
 * Created by rabook on 2014/12/27.
 */

@Slf4j
@Controller
@RequestMapping("/user/setting")
public class UserSettingController {

    @Resource
    private MemberService memberService;
    @Resource
	private AreaService areaService;
	
    
    @RequestMapping("/index")
    public String index(Model model){

//        String memberName = SecurityUtils.getSubject().getPrincipal().toString();
        
        Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
        if(member.getMemberBirthday()!=null&&!"".equals(member.getMemberBirthday())){
        	member.setMemberBirthdaystr(new Timestamp(member.getMemberBirthday()));
        }
        CacheUtils.initCacheUser(member.getMemberName());
        model.addAttribute("member",member);
        //一级地区加载
        List<Area> areas = areaService.queryAll();
		
		model.addAttribute("areas",areas);
        
        model.addAttribute("titleName", "账户信息");
		model.addAttribute("apm", "setting");
//        model.addAttribute("member",memberService.findMemberByName(memberName));
        return "user/setting/acc-info";
    }
    
    
    /**
     * 
     * @Title: updateMember 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param jsondata
     * @param @param model
     * @param @return
     * @param @throws Exception    设定文件 
     * @return Map<String,String>    返回类型 
     * @throws
     */
    @RequestMapping(value = "/updateMember", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updateMember(@ModelAttribute Member member,Model model,
			@RequestParam(required=false, value="Birthday", defaultValue="") String Birthday) throws Exception {
    	Map<String,Object> map = Maps.newHashMap();
    	if(StringUtils.isNotEmpty(Birthday)){
    		member.setMemberBirthday(DateUtils.strToLong(Birthday+" "+"16:06:30"));
		}
		try{
			memberService.updateMember(member);
			map.put("message","修改成功");
			map.put("success",true);
		}catch (Exception e) {
			map.put("message","修改失败");
			map.put("success",false);
		}
		return map;
	}
    
    
  

    /**
     * 
     * @Title: queryPass 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @return    设定文件 
     * @return ModelAndView    返回类型 
     * @throws
     */
	@RequestMapping("/queryPass")
	public ModelAndView queryPass() {
		try {
			ModelAndView model = new ModelAndView("user/setting/acc-pass");
			Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
	        model.addObject("member",member);
			
			model.addObject("titleName", "账户信息");
			model.addObject("cur", "accinfo");
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	
	@RequestMapping(value = "/updatePass", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> updatePass(@RequestParam(value = "memberPasswd") String memberPasswd,
			@RequestParam(value = "newPasswd") String newPasswd,
			Model model) throws Exception {
		
		Map<String, String> map = Maps.newHashMap();
        int result = memberService.updatePass(memberPasswd, newPasswd, CacheUtils.getCacheUser().getMember().getMemberId());
		if(result == 1){
			map.put("success", "true");
			map.put("message", "修改成功!");
		}else if(result == 2){
			map.put("success", "false");
			map.put("message", "原始密码不正确！");
		}else{
			map.put("success", "false");
			map.put("message", "修改密码失败！");
		}
		return map;
	}
	
	
	/**
	 * 
	 * @Title: queryFace 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return ModelAndView    返回类型 
	 * @throws
	 */
	@RequestMapping("/queryFace")
	public ModelAndView queryFace() {
		try {
			
			ModelAndView model = new ModelAndView("user/setting/acc-face");
			
			Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
	        model.addObject("member",member);
			model.addObject("titleName", "账户信息");
			model.addObject("cur", "accinfo");
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	@RequestMapping(value = "/updateFace", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> updateFace(@RequestParam(value = "memberAvatar") String memberAvatar,
			Model model) throws Exception {
		
		Map<String, String> map = Maps.newHashMap();
        int result = memberService.updateFace(memberAvatar,CacheUtils.getCacheUser().getMember().getMemberId());
		if(result == 1){
			map.put("success", "true");
			map.put("message", "设置成功！");
		}else{
			map.put("success", "false");
			map.put("message", "修改头像失败");
		}
		return map;
	}
	
}
