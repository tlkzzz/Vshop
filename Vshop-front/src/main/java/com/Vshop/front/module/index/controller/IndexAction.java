package com.Vshop.front.module.index.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.Vshop.core.common.UserAgentUtils;
import com.Vshop.core.entity.base.App;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.vo.ArticleClassTitleVo;
import com.Vshop.core.entity.vo.CartVo;
import com.Vshop.front.utils.CommonConstants;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.front.weibo4j.util.WeiboConfig;
import com.Vshop.service.module.cart.service.CartService;
import com.Vshop.service.module.index.service.SignupService;
import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.service.module.website.service.ArticleClassService;
import com.Vshop.service.utils.page.Pager;
import com.qq.connect.utils.QQConnectConfig;
import com.qq.connect.utils.RandomStatusGenerator;


/**
 * 项目名称：Vshop-front
 * 类名称：IndexAction
 * 类描述：
 * 创建人：liuhao
 * 修改备注：
 */
@Controller
@RequestMapping("/")
@Slf4j
public class IndexAction {

    String message = "success";

    @Resource
    private ArticleClassService articleClassService;

    @Resource
    private SignupService signupService;

    @Resource
    private MemberService memberService;

    
    @Resource
    private CartService cartService;
    

    /**
     * 导航主页面跳转
     * @param @param  apm 加载的
     * @param @return 设定文件
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     * @Title: index
     */
    @RequestMapping("")
    public ModelAndView index() {
        try {
            ModelAndView model = new ModelAndView("/index/index");
            model.addObject("apm", "index");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("卖家中心首页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }


    /**
     * 默认加载的中心页面
     * @param @return 设定文件
     * @return ModelAndView    返回类型
     * @throws
     * @Title: main
     */
    @RequestMapping(value = "/content")
    public ModelAndView content() {
        ModelAndView model = new ModelAndView("/index/index_content");
        return model;
    }


    /**
     * 跳转至登陆页面
     *
     * @return
     */
    @SuppressWarnings("deprecation")
	@RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response, Model model, ModelAndView modelAndView) {
    	String weixinurl = CommonConstants.WEIXIN_AUTHORIZATIONCODEURL + "?appid=" + CommonConstants.WEIXIN_APPID;
    	weixinurl += "&redirect_uri=" + CommonConstants.WEIXIN_REDIRECT_URI;
    	weixinurl += "&response_type=" + CommonConstants.WEIXIN_RESPONSE_TYPE;
    	weixinurl += "&scope=" + CommonConstants.WEIXIN_SCOPE;
    	String referer = request.getHeader("Referer");
		//System.out.println("referer-======" + referer);
    	String state = RandomStatusGenerator.getUniqueState();
    	String qqurl = new StringBuilder().append(QQConnectConfig.getValue("authorizeURL").trim()).append("?response_type=")
				.append("code").append("&client_id=").append(QQConnectConfig.getValue("app_ID").trim()).append("&redirect_uri=")
				.append(URLEncoder.encode(QQConnectConfig.getValue("redirect_URI").trim())).append("&state=").append(state).toString();
    	
    	String sinaurl = WeiboConfig.getValue("baseURL").trim() + "/authorize?client_id=" + WeiboConfig.getValue("client_ID").trim()
				+ "&redirect_uri=" + WeiboConfig.getValue("redirect_URI").trim();
    	
    	model.addAttribute("weixinurl", weixinurl);
    	model.addAttribute("qqnurl", qqurl);
    	model.addAttribute("sinaurl", sinaurl);
    	model.addAttribute("referer", referer);
    	
//    	if (modelAndView != null){
//			// 如果是手机或平板访问的话，则跳转到手机视图页面。
//			if(UserAgentUtils.isMobileOrTablet(request) && !StringUtils.startsWithIgnoreCase(modelAndView.getViewName(), "redirect:")){
//				//modelAndView.setViewName(CommonConstants.FRONT_SERVER + "/m/index/login");
//				if(!CacheUtils.isLogin()){
//					try {
//						response.sendRedirect(CommonConstants.FRONT_SERVER + "/m/index/login");
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
    	
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return "/index/login";
        }
        return "/index/login";
    }

    /**
     * 生成验证码图片io流
     */
    @RequestMapping(value = "generateImage", method = RequestMethod.GET)
    public void generateImage(HttpServletResponse response,HttpServletRequest request)
            throws ServletException, IOException {
        /*Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String captcha = PatchcaUtils.generateImage(response);
        session.setAttribute("captcha", captcha);*/
        RandCodeImageUtils.generateImage(response, request);
    }

    /**
     * 用户登录操作
     *
     * @return 登录后的地址
     */
    @SuppressWarnings("unused")
	@RequestMapping(value = "loginCheck", method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> loginCheck(@RequestParam(value = "username") String username,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "captcha") String captcha,
                             @RequestParam(value = "referer", required = false) String referer,
                             @RequestParam(value = "remember_me", required = false) String remember_me,
                             Model model, HttpServletRequest request) {
//    	System.out.println("referer======-------------------------------------------------"+referer);
        String error = null;
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Map<String,Object> map = Maps.newHashMap();
        Member member=memberService.findMemberByName(username);
        if(member!=null&&member.getMemberState()==0){//判断会员是否有登陆权限
        	map.put("success",false);
			map.put("message", "该会员禁止登陆");
			return map;
        }
        String sessionCaptcha = (String) session.getAttribute("captcha");
        if (!sessionCaptcha.equals(captcha.trim().toUpperCase())){
        	map.put("success",false);
			map.put("message", "验证码错误");
			return map;
        }
        boolean isrmemberName = "1".equals(remember_me) ? true : false;
        String host = IpUtil.getIpAddr(request);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray(), isrmemberName, host, captcha, false);
        try {
            if ("1".equals(remember_me)) {
                token.setRememberMe(true);
            }
            subject.login(token);
            CacheUtils.initCacheUser(subject.getPrincipal().toString());
        } catch (UnknownAccountException e) {
            //error = "用户名/密码错误";
            map.put("success",false);
			map.put("message", "用户名/密码错误");
			return map;
        } catch (IncorrectCredentialsException e) {
        	map.put("success",false);
 			map.put("message", "用户名/密码错误");
 			return map;
            //error = "用户名/密码错误";
        } catch (Exception e) {
            //其他错误，比如锁定，如果想单独处理请单独catch 处理
           // error = "其他错误：" + e.getMessage();
            map.put("success",false);
			map.put("message", "其他错误:"+e.getMessage());
			error = "其他错误：" + e.getMessage();
			return map;
        }
        //修改登陆者的登陆信息
        memberService.updateweiMember( CacheUtils.getCacheUser().getMember().getMemberId());
        //登录成功后,session中购物车数据存表
        CartVo cartVo = (CartVo) session.getAttribute(Constants.CART_KEY);
    	if(cartVo != null){
			cartService.addLoginCart(cartVo, CacheUtils.getCacheUser().getMember().getMemberId());
    	}
    	session.removeAttribute(Constants.CART_KEY);
    	
    	/**
    	 * 登录环信通讯
    	 */
    	//EasemobIMUsers.imUserLogin(username, password);
    	
        map.put("success",true);
		map.put("message","登陆成功");
		return map;
    }

    /**
     * 用户登出操作
     *
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }


    /**
     * @param @param  brandId
     * @param @return 设定文件
     * @return ModelAndView    返回类型
     * @throws
     * @Title: brandindex
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    @RequestMapping("/brandindex")
    public ModelAndView brandindex(@RequestParam(value = "brandId") String brandId) {
        try {
            ModelAndView model = new ModelAndView("/index/brand");
            // 尾部菜单
            List<ArticleClassTitleVo> listArticleClassTitleVo = articleClassService
                    .findTitleList();

            model.addObject("div", "dataListDiv");// 显示的DIV数据区域
            model.addObject("listArticleClassTitleVo", listArticleClassTitleVo);
            model.addObject("brandId", brandId);
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("卖家中心首页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }

//    /**
//     * @param @param  brandId
//     * @param @return 设定文件
//     * @return ModelAndView    返回类型
//     * @throws
//     * @Title: brandindex
//     * @Description: TODO(这里用一句话描述这个方法的作用)
//     */
//    @RequestMapping("/brandList")
//    public ModelAndView brandList(@RequestParam(value = "brandId") String brandId,
//                                  @RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNoStr) {
//        try {
//            ModelAndView model = new ModelAndView("/index/brand-list");
//            Pager pager = new Pager();
//            if (Strings.isNotEmpty(pageNoStr)) {
//                pager.setPageNo(Integer.valueOf(pageNoStr));
//            }
//            Goods goods = new Goods();
//            goods.setBrandId(Integer.valueOf(brandId));
//            pager.setCondition(goods);
//
//
//            int total = goodsService.brandCount(pager);
//            List<Goods> goodsList = goodsService.queryBrandList(pager);
//            // 尾部菜单
//            List<ArticleClassTitleVo> listArticleClassTitleVo = articleClassService
//                    .findTitleList();
//
//
//            model.addObject("pager", pager);
//            model.addObject("pageNo", pager.getPageNo());// 当前页
//            model.addObject("pageSize", pager.getPageSize());// 每页显示条数
//            model.addObject("recordCount", total);// 总数
//            model.addObject("div", "dataListDiv");// 显示的DIV数据区域
//            model.addObject("listArticleClassTitleVo", listArticleClassTitleVo);
//            model.addObject("goodsList", goodsList);
//            model.addObject("brandId", brandId);
//            model.addObject("toUrl", "/brandList");//总数
//            return model;
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("卖家中心首页加载失败！");
//            throw new RuntimeException("导航失败!");
//        }
//    }

    /**
     * 跳转到注册页面
     *
     * @return
     */
    @RequestMapping("/signUp")
    public String signUp() {
        return "index/signup";
    }

    @RequestMapping("/sign")
    public
    @ResponseBody
    Map<String, Object> sign(@ModelAttribute Member member, @RequestParam(value = "captcha") String captcha
            , HttpSession session) {

        Map<String, Object> map = Maps.newHashMap();
        String sessionCaptcha = (String) session.getAttribute("captcha");
        if (!sessionCaptcha.equals(captcha.trim().toUpperCase())) {
            map.put("success", false);
            map.put("msg", "验证码错误");
        } else {
            try {
            	//后台验证用户名是否重复注册
            	Member membervali=memberService.findMemberByName(member.getMemberName());
            	if(membervali==null){
            		savemember(member);//保存会员信息
	                //signupService.saveSendEmail(member);发邮件方法
	                map.put("success", true);
//	                map.put("msg", "邮件已发送，请到邮箱激活");
	                map.put("msg", "恭喜您注册成功！");
            	}
            } catch (Exception e) {
                e.printStackTrace();
                log.error("用户注册失败", e.getMessage());
                map.put("success", false);
                map.put("msg", "注册失败");
            }
        }
        return map;
    }

    @RequestMapping("/signResult")
    public String signResult(@RequestParam String userName, @RequestParam String code, Model model) {

        model.addAttribute("msg", signupService.updateSign(userName, code));
        return "index/signResult";
    }

    /**
     * 检查用户是否登陆
     *
     * @return
     */
    @RequestMapping("/checkLogin")
    public
    @ResponseBody
    Map<String, Object> checkLogin() {

        Map<String, Object> map = Maps.newHashMap();
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() || subject.isRemembered()) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }

    @RequestMapping("/ajaxLogin")
    public
    @ResponseBody
    Map<String, Object> ajaxLogin(@RequestParam(value = "username") String username,
                                  @RequestParam(value = "password") String password,
                                  @RequestParam(value = "captcha") String captcha,
                                  @RequestParam(value = "remember_me", required = false) String remember_me) {

        Map<String, Object> map = Maps.newHashMap();
        Subject subject = SecurityUtils.getSubject();
//        Session session = subject.getSession();
//        String sessionCaptcha = (String) session.getAttribute("captcha");
//        if (!sessionCaptcha.equals(captcha.trim().toUpperCase())) {
//            map.put("success", false);
//            map.put("msg", "验证码错误");
//        }
        
        boolean isrmemberName = "1".equals(remember_me) ? true : false;
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray(), isrmemberName, "", captcha, false);
        try {
            if ("1".equals(remember_me)) {
                token.setRememberMe(true);
            }
            subject.login(token);
            CacheUtils.initCacheUser(subject.getPrincipal().toString());
            map.put("success", true);
        } catch (UnknownAccountException e) {
            map.put("success", false);
            map.put("msg", "用户名密码错误");
            return map;
        } catch (IncorrectCredentialsException e) {
            map.put("success", false);
            map.put("msg", "用户名密码错误");
            return map;
        } catch (Exception e) {
            //其他错误，比如锁定，如果想单独处理请单独catch 处理
            map.put("success", false);
            map.put("msg", "其他错误");
            return map;
        }
        return map;
    }

    @RequestMapping("/checkMemeber")
    public
    @ResponseBody
    boolean checkMemeber(@RequestParam String name) {
       // Map<String, Object> map = Maps.newHashMap();
        if (memberService.findMemberByName(name) != null) {
            return false;
        } else {
            return true;
        }
    }

    @RequestMapping("/checkEmail")
    public
    @ResponseBody
    boolean checkEmail(@RequestParam String email) {
        if (memberService.findMemberByEmail(email) != null) {
            return false;
        } else {
            return true;
        }

    }
    @RequestMapping("/checkMobile")
    public
    @ResponseBody
    boolean checkMobile(@RequestParam String mobile) {
        if (memberService.findMemberByMobile(mobile) != null) {
            return false;
        } else {
            return true;
        }

    }
    @RequestMapping("/checkCaptcha")
    public
    @ResponseBody
    boolean checkCaptcha(@RequestParam String captcha) {
    	Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionCaptcha = (String) session.getAttribute("captcha");
        if (!sessionCaptcha.equals(captcha.trim().toUpperCase())) {
        	return false;
        }else{
        	return true;
        }
    }
    
	
	/**
	 * cgl
	 * 获得登陆者的姓名,在页头中能够显示用户名
	 * 2015年08月12日16:16:43
	 */
	@RequestMapping("index/getUsername")
	@ResponseBody
	public Map<String, String> getUsername() {
		
		Map<String, String> map = new HashMap<String, String>();
		
		/**
		 * 获得用户名,如果为空,则表示没有登陆
		 */
		try {
			
			String memberName = CacheUtils.getCacheUser().getMember().getMemberName();
			
			if(memberName == null){
				map.put("result", "false");
				return map;
			}
			
			/**
			 * 获得到memberName,表示用户已经登陆
			 */
			map.put("result", "true");
			/**将用户名存入map*/
			map.put("memberName", memberName);
			
			return map;
			
		} catch (Exception e) {
			// TODO: handle exception
			/**
			 * 用户没有登陆,返回空
			 */
			map.put("result", "false");
			return map;
		}
	}
	
	 /**
     * 用户登录验证码验证的操作
     *
     * @return 
     */
    @RequestMapping(value = "webcodeCheck", method = RequestMethod.POST)
    public @ResponseBody String codeCheck(
    		@RequestParam(value = "captcha") String captcha,
    		Model model, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionCaptcha = (String) session.getAttribute("captcha");
        String json = "true";
        if (!sessionCaptcha.equals(captcha.trim().toUpperCase())){
        	json = "false";
        }
		return json;
    }
    /**
     * 保存用户信息
     *
     */
    public void savemember(Member member){
    	 member.setMemberType("wzzc");//默认会员类型为网站注册
    	 member.setMemberTruename(member.getMemberName());//会员真实姓名默认为会员账号
    	 if(member.getMemberAvatar()==null||"".equals(member.getMemberAvatar())){
    		 member.setMemberAvatar("/upload/img/avatar/01.jpg");//会员头像
    	 }
    	 memberService.save(member);
    }
    
}