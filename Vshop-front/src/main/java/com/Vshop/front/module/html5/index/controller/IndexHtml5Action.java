package com.Vshop.front.module.html5.index.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
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
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
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
import com.Vshop.core.common.DateUtils;
import com.Vshop.core.common.IpUtil;
import com.Vshop.core.common.StringUtils;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.Wxgoods;
import com.Vshop.core.entity.vo.CartVo;
import com.Vshop.front.MemContents;
import com.Vshop.front.module.weChatpay.service.WechatService;
import com.Vshop.front.module.wxfhb.wxsendRedPack;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.cart.service.CartService;
import com.Vshop.service.module.goods.service.WxgoodsService;
import com.Vshop.service.module.index.service.SignupService;
import com.Vshop.service.module.member.service.MemberService;

/**
 * 项目名称：Vshop-front 类名称：IndexAction 类描述： 创建人：zrh 创建时间：2015年10月40日
 * 下午10:34:55 修改备注：
 */
@Controller
@RequestMapping("/m/index")
@Slf4j
public class IndexHtml5Action {
	@Resource
    private WxgoodsService wxgoodsService;
	@Resource
	private CartService cartService;

	@Resource
	private MemberService memberService;

	@Resource
	private SignupService signupService;

	@Resource
	private WechatService wechatService;

	String message = "success";

	public static final String SELF_SUPPORT_STORE_ID = "0";
	
	/**
	 * 生成验证码图片io流
	 */
	@RequestMapping(value = "generateImage", method = RequestMethod.GET)
	public void generateImage(HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {
		RandCodeImageUtils.generateImage(response, request);
	}

	/**
	 * 导航主页面跳转
	 * 
	 * @param @param
	 *            apm 加载的
	 * @param @return
	 *            设定文件
	 * @param codeId
	 *            会员id
	 * @param code
	 *            密码(前6位随机数，6位后面是用户密码)
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 * @Title: index
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index(@RequestParam(value = "codeId", required = false) String codeId,
			@RequestParam(value = "code", required = false) String code) {
//		String weiopenid = "";
		try {

			/*
			 * if(StringUtils.isNotEmpty(codeId)){ code = code.substring(6);
			 * Member m = memberService.findById(Integer.parseInt(codeId));
			 * memberLogin(m.getMemberName(), code, false); Subject subject =
			 * SecurityUtils.getSubject(); UsernamePasswordToken token = new
			 * UsernamePasswordToken(m.getMemberName(), code.toCharArray(),
			 * false, "", "", false); subject.login(token); } else
			 * if(StringUtils.isNotEmpty(code)){
			 * weiopenid=wechatService.getopenIdbysnsapibase(MemContents.appid,
			 * MemContents.appsecret,code);
			 * if(StringUtils.isNotBlank(weiopenid)){ Subject subjectwei =
			 * SecurityUtils.getSubject(); Session sessionwei =
			 * subjectwei.getSession();
			 * if(sessionwei.getAttribute("weiopenid")==null){
			 * sessionwei.setAttribute("weiopenid",weiopenid); }
			 * checkmember(weiopenid);//根据openid自动登陆 } }
			 */

			String storeid = SELF_SUPPORT_STORE_ID;

			try {
				if (CacheUtils.getCacheUser().getStore() != null) {
					storeid = CacheUtils.getCacheUser().getStore().getStoreId() + "";
				} else {
					Subject subject = SecurityUtils.getSubject();
					CacheUtils.initCacheUser(subject.getPrincipal().toString(), storeid);
				}
			} catch (Exception e) {
				storeid = SELF_SUPPORT_STORE_ID;
			}
          
			ModelAndView model = new ModelAndView("/html5/index/indexn");
			model.addObject("storeid", storeid);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}

	/**
	 * 导航主页面跳转
	 * 
	 * @param @param
	 *            apm 加载的
	 * @param @return
	 *            设定文件
	 * @param codeId
	 *            会员id
	 * @param code
	 *            密码(前6位随机数，6位后面是用户密码)
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 * @Title: index
	 */
	@RequestMapping(value = "/indexo")
	public ModelAndView indexo(@RequestParam(value = "codeId", required = false) String codeId,
			@RequestParam(value = "code", required = false) String code) {
		String weiopenid = "";
		try {
			if (StringUtils.isNotEmpty(codeId)) {
				code = code.substring(6);
				Member m = memberService.findById(Integer.parseInt(codeId));
				memberLogin(m.getMemberName(), code, false);
				Subject subject = SecurityUtils.getSubject();
				UsernamePasswordToken token = new UsernamePasswordToken(m.getMemberName(), code.toCharArray(), false,
						"", "", false);
				subject.login(token);
			} else if (StringUtils.isNotEmpty(code)) {
				weiopenid = wechatService.getopenIdbysnsapibase(MemContents.appid, MemContents.appsecret, code);
				if (StringUtils.isNotBlank(weiopenid)) {
					Subject subjectwei = SecurityUtils.getSubject();
					Session sessionwei = subjectwei.getSession();
					if (sessionwei.getAttribute("weiopenid") == null) {
						sessionwei.setAttribute("weiopenid", weiopenid);
					}
					checkmember(weiopenid);// 根据openid自动登陆
				}
			}
			ModelAndView model = new ModelAndView("/html5/index/indexn");
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}

	/**
	 * 跳转至登陆页面
	 *
	 * @return
	 */
	@RequestMapping(value = "login")
	public ModelAndView login() {
		try {
			ModelAndView model = new ModelAndView("/html5/index/login");
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心登陆页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}

	/**
	 * 跳转至注册页面
	 *
	 * @return
	 */
	@RequestMapping(value = "register")
	public ModelAndView register() {
		try {
			ModelAndView model = new ModelAndView("/html5/index/register");
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心登陆页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	/**
	 * 微信红包
	 *
	 * @return
	 */
	@RequestMapping(value = "fhb")
	public ModelAndView fhb(@ModelAttribute("openid") String openid,@ModelAttribute("name") String name,@ModelAttribute("p") String p) {
		try {
			Map<String,Object> map =new HashMap<String,Object>();
		
			System.out.println("获取的openid："+openid);
			System.out.println("获取的name："+name);
			System.out.println("获取的p："+p);
			Wxgoods goods = new Wxgoods();
			goods = wxgoodsService.findById(p); //查询商品状态0 有效 1 已扫过2删除
			if(goods!=null){
				
			
			String t = goods.getState();
			if(t.equals("0")){
				if(goods.getJe().equals("0") || goods.getJe().equals("") || goods.getJe().equals(null)){
					//把扫码人信息存入数据库
					goods.setSmr(name);
					goods.setSmsj(DateUtils.getDate24());
					goods.setState("1");
					goods.setNb(goods.getNb()+1);
					wxgoodsService.update(goods);
					map.put("key", 0);//红包没有钱
					map.put("g", goods);
				}else{	
					//调用红包接口
					//String opid,int je,String fszmc,String tgfmc,String hdmc,String bz,String hbzfy
				String result=wxsendRedPack.sendRedPack(openid, Integer.parseInt(goods.getJe()),goods.getFszmc(),goods.getTgfmc(),goods.getHdmc(),goods.getBz(),goods.getHbzfy(),goods.getSjzfc());
					//返回发送红包成功
				if(result.equals("true")){
					
			            goods.setSmr(name);
						goods.setSmsj(DateUtils.getDate24());
						goods.setState("1");
						goods.setNb(goods.getNb()+1);
						wxgoodsService.update(goods);
						int j = Integer.parseInt(goods.getJe())/100;
						goods.setJe(j+"");
						map.put("key", 1);//扫成功
						map.put("g", goods);
				}else{
					    goods.setSmr(name);
						goods.setSmsj(DateUtils.getDate24());
						goods.setState("1");
						goods.setNb(goods.getNb()+1);
						wxgoodsService.update(goods);
						map.put("key", 3);//扫成功
						map.put("g", goods);
				}
				}
				
			}else if(t.equals("1")){//已经被扫过
				goods.setNb(goods.getNb()+1);
				wxgoodsService.update(goods);
				map.put("key", 2);//红包已经被扫过
//				map.put("nb", goods.getNb()+1);
				map.put("g", goods);
//				map.put("smsj", goods.getSmsj());
			}
			}
			ModelAndView model = new ModelAndView("/html5/index/zsp",map);
			
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心登陆页加载失败！");
			throw new RuntimeException("导航失败!");
		}
		
		
	}


	/**
	 * 用户登录验证码验证的操作
	 *
	 * @return
	 */
	@RequestMapping(value = "codeCheck", method = RequestMethod.POST)
	public @ResponseBody String codeCheck(@RequestParam(value = "captcha") String captcha, Model model,
			HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		String sessionCaptcha = (String) session.getAttribute("captcha");
		String json = "true";
		if (!sessionCaptcha.equals(captcha.trim().toUpperCase())) {
			json = "false";
		}
		return json;
	}

	/**
	 * 用户登录操作
	 *
	 * @return 登录后的地址
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "loginCheck", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> loginCheck(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, Model model, HttpServletRequest request) {
		String error = null;
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		Map<String, Object> map = Maps.newHashMap();
		try {
			String host = IpUtil.getIpAddr(request);
			memberLogin(username, password, false);
		} catch (UnknownAccountException e) {
			map.put("success", false);
			map.put("message", "用户名/密码错误");
			return map;
		} catch (IncorrectCredentialsException e) {
			map.put("success", false);
			map.put("message", "用户名/密码错误");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("message", "其他错误:" + e.getMessage());
			error = "其他错误：" + e.getMessage();
			return map;
		}
		// 登录成功后,session中购物车数据存表
		CartVo cartVo = (CartVo) session.getAttribute(Constants.CART_KEY);
		if (cartVo != null) {
			cartService.addLoginCart(cartVo, CacheUtils.getCacheUser().getMember().getMemberId());
		}
		String openid = session.getAttribute("weiopenid") + "";
		if (StringUtils.isNotEmpty(openid)) {
			getmemberbyopenid(username, openid);// 通过openid查询用户
		}
		session.removeAttribute(Constants.CART_KEY);
		map.put("success", true);
		map.put("message", "登陆成功");
		return map;
	}

	/**
	 * 用户登出操作
	 *
	 * @return
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public ModelAndView logout() {

		try {
			Subject subject = SecurityUtils.getSubject();
			subject.logout();
			ModelAndView model = new ModelAndView("/html5/index/indexn");
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}

	}

	@RequestMapping("/checkMemeber")
	public @ResponseBody boolean checkMemeber(@RequestParam String name) {
		// Map<String, Object> map = Maps.newHashMap();
		if (memberService.findMemberByName(name) != null) {
			return false;
		} else {
			return true;
		}
	}

	@RequestMapping("/checkEmail")
	public @ResponseBody boolean checkEmail(@RequestParam String email) {
		if (memberService.findMemberByEmail(email) != null) {
			return false;
		} else {
			return true;
		}

	}

	@RequestMapping("/checkMobile")
	public @ResponseBody boolean checkMobile(@RequestParam String mobile) {
		if (memberService.findMemberByMobile(mobile) != null) {
			return false;
		} else {
			return true;
		}

	}

	@RequestMapping("/sign")
	public @ResponseBody Map<String, Object> sign(@ModelAttribute Member member) {

		Map<String, Object> map = Maps.newHashMap();
		try {
			// 后台验证用户名是否重复注册
			Member membervali = memberService.findMemberByName(member.getMemberName());
			if (membervali == null) {
				String passwd = member.getMemberPasswd();
				savemember(member);// 保存会员信息
				// signupService.saveSendEmail(member);
				map.put("success", true);
				map.put("msg", "邮件已发送，请到邮箱激活");
				// String passwd2 = member.getMemberPasswd();
				memberLogin(member.getMemberName(), passwd, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("用户注册失败", e.getMessage());
			map.put("success", false);
			map.put("msg", "注册失败");
		}

		return map;
	}

	public static String urlEnodeUTF8(String str) {
		String result = str;
		try {
			result = URLEncoder.encode(str, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 保存用户信息
	 *
	 */
	public void savemember(Member member) {
		member.setMemberType("wzzc");// 默认会员类型为网站注册
		member.setMemberTruename(member.getMemberName());// 会员真实姓名默认为会员账号
		if (member.getMemberAvatar() == null || "".equals(member.getMemberAvatar())) {
			member.setMemberAvatar("/upload/img/avatar/01.jpg");// 会员头像
		}
		memberService.save(member);
	}

	/**
	 * 通过openid查找member openid 微信用户唯一标示
	 */
	public void getmemberbyopenid(String membername, String openid) {
		Member member1 = new Member();
		member1.setMemberOpenid(openid);
		Member member = memberService.findMember(member1);
		if (member == null) {
			Member member3 = new Member();
			member = memberService.findMemberByName(membername);
			member3.setMemberOpenid(openid);
			member3.setMemberId(member.getMemberId());
			memberService.updateMember(member3);
		}
	}

	/**
	 * 通过openid查找member openid 微信用户唯一标示
	 */
	public void checkmember(String openid) {
		Member member = new Member();
		member.setMemberOpenid(openid);
		Member member6 = memberService.findMember(member);
		if (member6 != null) {
			memberLogin(member6.getMemberName(), openid, true);// 把以微信
																// openid作为密码的用户注入session
		}
	}

	/**
	 * 会员登录
	 * 
	 * @param name
	 *            用户名
	 * @param password
	 *            密码
	 * @param isMobile
	 *            是否手机登录
	 */
	private void memberLogin(String name, String password, boolean isMobile) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(name, password.toCharArray(), false, "", "", isMobile);
		subject.login(token);
	}
}