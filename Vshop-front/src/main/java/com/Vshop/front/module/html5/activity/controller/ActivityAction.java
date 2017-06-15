package com.Vshop.front.module.html5.activity.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.Vshop.core.auth.shiro.UsernamePasswordToken;
import com.Vshop.core.captcha.RandCodeImageUtils;
import com.Vshop.core.common.Constants;
import com.Vshop.core.common.IpUtil;
import com.Vshop.core.common.StringUtils;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.vo.CartVo;
import com.Vshop.front.MemContents;
import com.Vshop.front.module.weChatpay.service.WechatService;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.cart.service.CartService;
import com.Vshop.service.module.index.service.SignupService;
import com.Vshop.service.module.member.service.MemberService;



/**
 * 项目名称：Vshop-front
 * 类名称：IndexAction
 * 类描述：
 * 创建人：zrh
 * 创建时间：2015年10月40日 下午10:34:55
 * 修改备注：
 */
@Controller
@RequestMapping("/m/activity")
@Slf4j
public class ActivityAction {
	
	@Resource
    private CartService cartService;
	
    @Resource
    private MemberService memberService;
    
    @Resource
    private SignupService signupService;
    
    @Resource
    private WechatService wechatService;

    String message = "success";
    
    
    /**
     * 生成验证码图片io流
     */
    @RequestMapping(value = "generateImage", method = RequestMethod.GET)
    public void generateImage(HttpServletResponse response,HttpServletRequest request)
            throws ServletException, IOException {
        RandCodeImageUtils.generateImage(response, request);
    }
   
    /**
     * 导航主页面跳转
     * @param @param  apm 加载的
     * @param @return 设定文件
     * @param codeId 会员id
     * @param code 密码(前6位随机数，6位后面是用户密码)
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     * @Title: index
     */
    @RequestMapping(value = "/activity")
    public ModelAndView index(@RequestParam(value = "storeId", required = false) String storeId,
    		@RequestParam(value = "activityId", required = false) String activityId
    		) {
    	String weiopenid="";
        try {
        	
            ModelAndView model = new ModelAndView("/html5/activity/activity");
            model.addObject("activityId",activityId);
            model.addObject("storeId",storeId);
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("卖家中心首页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    } 
    
    
    
    
}