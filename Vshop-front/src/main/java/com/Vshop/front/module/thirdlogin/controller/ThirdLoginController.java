package com.Vshop.front.module.thirdlogin.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Vshop.core.auth.shiro.UsernamePasswordToken;
import com.Vshop.core.common.Digests;
import com.Vshop.core.common.IpUtil;
import com.Vshop.core.common.NumberUtils;
import com.Vshop.core.common.httpclient.HttpProtocolHandler;
import com.Vshop.core.entity.SNSUserINfo;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.front.MemContents;
import com.Vshop.front.module.weChatpay.service.WechatService;
import com.Vshop.front.utils.CommonConstants;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.front.wechat.util.WeixinUtil;
import com.Vshop.front.weibo4j.Users;
import com.Vshop.front.weibo4j.model.User;
import com.Vshop.front.weibo4j.model.WeiboException;
import com.Vshop.service.module.member.service.MemberGradeService;
import com.Vshop.service.module.member.service.MemberService;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;


/**
 *    
 * 项目名称：Vshop-front  
 * 类名称：ThirdLoginController   
 * 类描述：第三方登录
 * 创建人：lkang   
 * 创建时间：2015年9月08日 10:00:00   
 */
@Slf4j
@Controller
@RequestMapping("/thirdlogin")
public class ThirdLoginController {
	
	@Resource
    private MemberService memberService;
	
	@Resource
	private MemberGradeService memberGradeService;
	
	@Resource
	private WechatService wechatService;
	
	/**
	 * 这个方法是授权成功后，返回的路径，请求的这个方法 获取qq用户的信息
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(params = "qqUserInfo")
	public String qqUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		response.setContentType("text/html,charset-utf-8"); 
		try {
			// 授权成功后获取token
			// 在getAccessTokenByRequest()方法中有client.post()先拿到了返回来的code然后去获取token的
			AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
			
			// 对AccessToken进行判断，如果用户取消了 AccessToken就为空
			if (!accessTokenObj.getAccessToken().equals("")) {
				String accessToken = null;
				String openID = null;

				accessToken = accessTokenObj.getAccessToken();

				// 利用获取到的accessToken去获取当前用的openid
				OpenID openIDObj = new OpenID(accessToken);
				openID = openIDObj.getUserOpenID(); // 同一个qq号的openid是相同的
				// 利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息
				UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
				UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
				
				//本站会员对象，获取用户信息后存入本地用户表
				if(userInfoBean.getRet() == 0){
					String name = userInfoBean.getNickname();
					Integer sex = userInfoBean.getGender().equals("男") ? 1 : 0;
					saveInfoToMember(name, name, sex, openID, "qq", "", request);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("QQ第三方登录授权失败", e);
			return "redirect:/";
		}
		return "redirect:/";
	}
	
	
	/**
	 * 这个方法是授权成功后，返回的路径，请求的这个方法 获取weixin用户的信息
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "weixinUserInfo")
	public String weixinUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html,charset-utf-8"); 
		try {
			// 授权成功后获取code
			String code = request.getParameter("code");
			// 根据code获取access_token
			String accessTokenUrl = CommonConstants.WEIXIN_ACCESSTOKEN + "?appid=" + CommonConstants.WEIXIN_APPID 
					+ "&secret=" + CommonConstants.WEIXIN_APPSECRET + "&code=" + code + "&grant_type=authorization_code";
			
			HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();
	        // 获取返回信息
	        String strResult = httpProtocolHandler.httpsRequest(accessTokenUrl);
	        Map<String, Object> map = JsonUtils.readJsonToMap1(strResult);
	        String errcode = map.get("errcode") + "";
	        
	        if(!NumberUtils.isNumber(errcode)){
	        	String access_token = map.get("access_token") + "";
	        	
	        	// 根据token获取用户信息
	        	String userInfoUrl = CommonConstants.WEIXIN_USERINFO + "?access_token=" + access_token + "&openid=" + CommonConstants.WEIXIN_APPID;
	        	String infoStr = httpProtocolHandler.httpsRequest(userInfoUrl);
	        	Map<String, Object> userMap = JsonUtils.readJsonToMap1(infoStr);
	        	errcode = userMap.get("errcode") + "";
	        	if(!NumberUtils.isNumber(errcode)){
	        		//本站会员对象，获取用户信息后存入本地用户表
	        		String name = userMap.get("nickname") + "";
	        		Integer s = Integer.parseInt(userMap.get("sex") + "");
	        		s = s == 2 ? 0 : 1;
	        		String unionid = userMap.get("unionid") + "";
	        		String headimgurl = userMap.get("headimgurl") + "";
	        		saveInfoToMember(name, name, s, unionid, "weixin", headimgurl, request);
	        	}
	        }else{
	        	return "redirect:/";
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
			return CommonConstants.FRONT_SERVER + "/login";
		}
		return "redirect:/";
	}
	
	/**
	 * 获取新浪用户的信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "sinaUserInfo")
	public String sinaUserInfo(HttpServletRequest request) {
		try {
			// 获取授权以后的CODE值
			String code = request.getParameter("code");
			// 根据CODE获取ACCESSTOKEN
			com.Vshop.front.weibo4j.Oauth oauth = new com.Vshop.front.weibo4j.Oauth();
			com.Vshop.front.weibo4j.http.AccessToken accesstoken = oauth.getAccessTokenByCode(code);
			// 根据ACCESSTOKEN获取UID
			String uid = accesstoken.getUid();
			// 根据UID获取用户信息
			String token = accesstoken.getAccessToken();
			Users users = new Users();
			users.setToken(token);
			User user = users.showUserById(uid);
			
			// 本站会员对象，获取SINA用户信息后存入本地用户表
    		String name = user.getName();
    		String unionid = user.getId();
    		String headimgurl = user.getProfileImageUrl();
    		String sex = user.getGender();
    		int s = sex == "m" ? 1: 0;
    		saveInfoToMember(name, name, s, unionid, "sina", headimgurl, request);

		} catch (WeiboException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 第三方登录用户信息存入本站数据库
	 * @param name
	 * @param truename
	 * @param s
	 * @param uid
	 * @param type
	 * @param headimgurl
	 * @param request
	 */
	private void saveInfoToMember(String name, String truename, int s, String uid, String type, String headimgurl, HttpServletRequest request){
		
		Member member = memberService.findMemberByName(name);
		if(null == member){
			Member m = new Member();
			m.setMemberName(name);                         // 用户名
			m.setMemberTruename(truename);                 // 昵称
			m.setMemberSex(s);                             // 性别
			m.setCreateTime(System.currentTimeMillis());;  // 注册时间
			m.setMemberType(type);                         // 用户登录类型
			m.setMemberPasswd(Digests.entryptPassword(uid));                        
			m.setMemberLoginIp(IpUtil.getIpAddr(request));
			m.setMemberOpenid(uid);                        // 用户的的唯一标识
			if(StringUtils.isEmpty(headimgurl)){
				m.setMemberAvatar("/upload/img/avatar/01.jpg");//会员头像
			}else{
				m.setMemberAvatar(headimgurl);
			}
			memberService.save(m);
		}else{
			member.setMemberName(name);                         // 用户名
			member.setMemberTruename(truename);                 // 昵称
			member.setMemberSex(s);                             // 性别
			member.setMemberLoginIp(IpUtil.getIpAddr(request));
			member.setMemberType(type);
			if(StringUtils.isEmpty(headimgurl)){
				member.setMemberAvatar("/upload/img/avatar/01.jpg");//会员头像
			}else{
				member.setMemberAvatar(headimgurl);
			}
			memberService.updateMember(member);
			memberService.updateweiMember(member.getMemberId());
		}
		//String host = IpUtil.getIpAddr(request);
//		String host="223.104.38.107";
		Subject subject = SecurityUtils.getSubject();
//		UsernamePasswordToken token = new UsernamePasswordToken(name, uid.toCharArray(), false, host, null, false);
		//subject.login(token);
		UsernamePasswordToken token = new UsernamePasswordToken();  
		token.setUsername(name);  
	    token.setPassword(uid.toCharArray());  
	    subject.login(token);  
		CacheUtils.initCacheUser(subject.getPrincipal().toString());
		memberService.updateweiMember( CacheUtils.getCacheUser().getMember().getMemberId());
	}
	

	/**
	 * 引导用户进如授权页面吧
	 * 
	 * @param request
	 * @return
	 */
	  @RequestMapping("goOauth2")
	  public void goOauth2(HttpServletRequest request ,HttpServletResponse response)throws  IOException{
		     String   url = URLEncoder.encode(MemContents.Oauth2back, "UTF-8");
			String requestUrl=MemContents.oauth2+"appid="+MemContents.appid+"&redirect_uri="+url+"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
		    requestUrl=requestUrl.replace(MemContents.appid,urlEnodeUTF8(MemContents.appid));//转码
			System.out.println("=============="+requestUrl);
			WeixinUtil.httpRequest(requestUrl,"GET", null);
	  }
	/**微信公共平台
	 * 这个方法是授权成功后，返回的路径，请求的这个方法 获取weixin用户的信息
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "weixincommonUserInfo")
	public String weixincommonUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		response.setContentType("text/html,charset-utf-8"); 
		try {
			// 授权成功后获取code
			String code = request.getParameter("code");
			String storeId = null;
			if (request.getParameter("storeId")!=null){
				 storeId = request.getParameter("storeId").toString();
			}
			
			SNSUserINfo snsUserinfo=null;
			Member member=null;
			if(StringUtils.isNotEmpty(code)){
				 snsUserinfo=wechatService.getOauth2AccessToken(MemContents.appid,MemContents.appsecret, code);
		         if(snsUserinfo!=null){
		        	    Member memberwei=new Member();
		        	    memberwei.setMemberOpenid(snsUserinfo.getOpenId());
		        	    member = memberService.findMember(memberwei);
		        	    if(member==null){
		        	    	
		        	    	    Member m = new Member();
			                    
		        	  			m.setMemberName(snsUserinfo.getNickname());                         // 用户名
		        	  			m.setMemberTruename(snsUserinfo.getNickname());                 // 昵称
		        	  			m.setMemberSex(snsUserinfo.getSex());                             // 性别
		        	  			m.setCreateTime(System.currentTimeMillis());  // 注册时间
		        	  			m.setMemberType("weixin");                    // 用户登录类型
		        	  			m.setMemberPasswd(snsUserinfo.getOpenId());                        
		        	  			
		        	  			m.setMemberOpenid(snsUserinfo.getOpenId());                        // 用户的的唯一标识
		        	  			if(StringUtils.isEmpty(snsUserinfo.getHeadImgUrl())){
		        	  				m.setMemberAvatar("/upload/img/avatar/01.jpg");//会员头像
		        	  			}else{
		        	  				m.setMemberAvatar(snsUserinfo.getHeadImgUrl());
		        	  			}
		        	  			memberService.save(m);
		        	  			String host = IpUtil.getIpAddr(request);
			        			Subject subject = SecurityUtils.getSubject();
			        			UsernamePasswordToken token = new UsernamePasswordToken(m.getMemberName(),m.getMemberOpenid().toCharArray(), false, host, null, false);
			        			subject.login(token);
			        			CacheUtils.initCacheUser(subject.getPrincipal().toString());
			        			if(storeId!=null&&storeId.length()>0)
			        		      CacheUtils.initCacheUser(subject.getPrincipal().toString(),storeId);
			        			memberService.updateweiMember(CacheUtils.getCacheUser().getMember().getMemberId());
		        	  	
		        	    	//saveInfoToMember(snsUserinfo.getNickname(),snsUserinfo.getNickname(),snsUserinfo.getSex(),snsUserinfo.getOpenId(), "weixin",snsUserinfo.getHeadImgUrl(), request);
		        	    	//return "redirect:/m/index/index";//跳转到个人中心
		        	    }
		        	    else{
		        	    	String host = IpUtil.getIpAddr(request);
		        			Subject subject = SecurityUtils.getSubject();
		        			UsernamePasswordToken token = new UsernamePasswordToken(member.getMemberName(),member.getMemberOpenid().toCharArray(), false, host, null, false);
		        			subject.login(token);
		        			CacheUtils.initCacheUser(subject.getPrincipal().toString());
		        			if(storeId!=null&&storeId.length()>0)
			        		    CacheUtils.initCacheUser(subject.getPrincipal().toString(),storeId);
		        			memberService.updateweiMember(CacheUtils.getCacheUser().getMember().getMemberId());
		        	    }
		        	    
		        	
		        			return "redirect:/m/index/index";
		        	   // }
		        	}else{
		        		return "redirect:/m/index/login";//跳转到登陆页面
		        	}
			}else{
				return "redirect:/m/index/login";//跳转到登陆页面
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/m/index/index";//跳转到登陆页面
		}
	}
	@SuppressWarnings("static-access")
	@RequestMapping(value = "weixincommonUserInfofhb")
	public String weixincommonUserInfos(HttpServletRequest request, HttpServletResponse response,RedirectAttributes model) throws IOException {
//		response.setContentType("text/html,charset-utf-8"); 
		try {
			// 授权成功后获取code
			String code = request.getParameter("code");
			String p = null;
			if (request.getParameter("p")!=null){
				 p = request.getParameter("p").toString();
			}
			System.out.println("*---------------------------*****"+p);
//			 return "redirect:/m/index/login";//跳转到登陆页面;
			SNSUserINfo snsUserinfo=null;
			Member member=null;
			if(StringUtils.isNotEmpty(code)){
				 snsUserinfo=wechatService.getOauth2AccessToken(MemContents.appid,MemContents.appsecret, code);
				 System.out.println(snsUserinfo+"++++++++++++++++++/*/*/*/*/*/*/");
		         if(snsUserinfo!=null){
		        	    Member memberwei=new Member();
		        	    memberwei.setMemberOpenid(snsUserinfo.getOpenId());
		        	    member = memberService.findMember(memberwei);
		        	    if(member==null){
		        	    	    Member m = new Member();
		        	  			m.setMemberName(snsUserinfo.getNickname());                         // 用户名
		        	  			m.setMemberTruename(snsUserinfo.getNickname());                 // 昵称
		        	  			m.setMemberSex(snsUserinfo.getSex());                             // 性别
		        	  			m.setCreateTime(System.currentTimeMillis());  // 注册时间
		        	  			m.setMemberType("weixin");                    // 用户登录类型
		        	  			m.setMemberPasswd(snsUserinfo.getOpenId());                        
		        	  			
		        	  			m.setMemberOpenid(snsUserinfo.getOpenId());                        // 用户的的唯一标识
		        	  			if(StringUtils.isEmpty(snsUserinfo.getHeadImgUrl())){
		        	  				m.setMemberAvatar("/upload/img/avatar/01.jpg");//会员头像
		        	  			}else{
		        	  				m.setMemberAvatar(snsUserinfo.getHeadImgUrl());
		        	  			}
		        	  			memberService.save(m);
		        	  			String host = IpUtil.getIpAddr(request);
			        			Subject subject = SecurityUtils.getSubject();
			        			UsernamePasswordToken token = new UsernamePasswordToken(m.getMemberName(),m.getMemberOpenid().toCharArray(), false, host, null, false);
			        			subject.login(token);
			        			CacheUtils.initCacheUser(subject.getPrincipal().toString());
			        			if(p!=null&&p.length()>0)
			        		     // CacheUtils.initCacheUser(subject.getPrincipal().toString(),p);
			        			memberService.updateweiMember(CacheUtils.getCacheUser().getMember().getMemberId());
		        	  	
		        	    	//saveInfoToMember(snsUserinfo.getNickname(),snsUserinfo.getNickname(),snsUserinfo.getSex(),snsUserinfo.getOpenId(), "weixin",snsUserinfo.getHeadImgUrl(), request);
		        	    	//return "redirect:/m/index/index";//跳转到个人中心
		        	    }
		        	    else{
		        	    	String host = IpUtil.getIpAddr(request);
		        			Subject subject = SecurityUtils.getSubject();
		        			UsernamePasswordToken token = new UsernamePasswordToken(member.getMemberName(),member.getMemberOpenid().toCharArray(), false, host, null, false);
		        			subject.login(token);
		        			CacheUtils.initCacheUser(subject.getPrincipal().toString());
		        			if(p!=null&&p.length()>0)
			        		    //CacheUtils.initCacheUser(subject.getPrincipal().toString(),p);
		        			memberService.updateweiMember(CacheUtils.getCacheUser().getMember().getMemberId());
		        	    }
		        	    
		        	    model.addAttribute("openid", snsUserinfo.getOpenId());
		        	    model.addAttribute("name", snsUserinfo.getNickname());
		        	    model.addAttribute("p", p);
		        			return "redirect:/m/index/fhb";
		        	   // }
		        	}else{
		        		return "redirect:/m/index/fhb";//跳转到登陆页面
		        	}
			}else{
				return "redirect:/m/index/login";//跳转到登陆页面
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/m/index/fhb";//跳转到登陆页面
		}
	}
	
	
	
	/**微信公共平台
	 * 这个方法是授权成功后，返回的路径，请求的这个方法 获取weixin用户的信息
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "weixincommonUserInfoGoods")
	public String weixincommonUserInfoGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		response.setContentType("text/html,charset-utf-8"); 
		try {
			// 授权成功后获取code
			String code = request.getParameter("code");
			String storeId = null;
			if (request.getParameter("storeId")!=null){
				 storeId = request.getParameter("storeId").toString();
			}
			
			String goodsId= null;
			if (request.getParameter("goodsId")!=null){
				goodsId = request.getParameter("goodsId").toString();
			}
			SNSUserINfo snsUserinfo=null;
			Member member=null;
			if(StringUtils.isNotEmpty(code)){
				 snsUserinfo=wechatService.getOauth2AccessToken(MemContents.appid,MemContents.appsecret, code);
		         if(snsUserinfo!=null){
		        	    Member memberwei=new Member();
		        	    memberwei.setMemberOpenid(snsUserinfo.getOpenId());
		        	    member = memberService.findMember(memberwei);
		        	    if(member==null){
		        	    	
		        	    	    Member m = new Member();
			                    
		        	  			m.setMemberName(snsUserinfo.getNickname());                         // 用户名
		        	  			m.setMemberTruename(snsUserinfo.getNickname());                 // 昵称
		        	  			m.setMemberSex(snsUserinfo.getSex());                             // 性别
		        	  			m.setCreateTime(System.currentTimeMillis());  // 注册时间
		        	  			m.setMemberType("weixin");                    // 用户登录类型
		        	  			m.setMemberPasswd(snsUserinfo.getOpenId());                        
		        	  			
		        	  			m.setMemberOpenid(snsUserinfo.getOpenId());                        // 用户的的唯一标识
		        	  			if(StringUtils.isEmpty(snsUserinfo.getHeadImgUrl())){
		        	  				m.setMemberAvatar("/upload/img/avatar/01.jpg");//会员头像
		        	  			}else{
		        	  				m.setMemberAvatar(snsUserinfo.getHeadImgUrl());
		        	  			}
		        	  			memberService.save(m);
		        	  			String host = IpUtil.getIpAddr(request);
			        			Subject subject = SecurityUtils.getSubject();
			        			UsernamePasswordToken token = new UsernamePasswordToken(m.getMemberName(),m.getMemberOpenid().toCharArray(), false, host, null, false);
			        			subject.login(token);
			        			CacheUtils.initCacheUser(subject.getPrincipal().toString());
			        			if(storeId!=null&&storeId.length()>0)
			        		      CacheUtils.initCacheUser(subject.getPrincipal().toString(),storeId);
			        			memberService.updateweiMember(CacheUtils.getCacheUser().getMember().getMemberId());
		        	  	
		        	    	//saveInfoToMember(snsUserinfo.getNickname(),snsUserinfo.getNickname(),snsUserinfo.getSex(),snsUserinfo.getOpenId(), "weixin",snsUserinfo.getHeadImgUrl(), request);
		        	    	//return "redirect:/m/index/index";//跳转到个人中心
		        	    }
		        	    else{
		        	    	String host = IpUtil.getIpAddr(request);
		        			Subject subject = SecurityUtils.getSubject();
		        			UsernamePasswordToken token = new UsernamePasswordToken(member.getMemberName(),member.getMemberOpenid().toCharArray(), false, host, null, false);
		        			subject.login(token);
		        			CacheUtils.initCacheUser(subject.getPrincipal().toString());
		        			if(storeId!=null&&storeId.length()>0)
			        		    CacheUtils.initCacheUser(subject.getPrincipal().toString(),storeId);
		        			memberService.updateweiMember(CacheUtils.getCacheUser().getMember().getMemberId());
		        	    }
		        	    
		        	    if(goodsId!=null&&goodsId.length()>0)
		        		  return "redirect:/m/goods/goodsdetail?id="+goodsId;//跳转到个人中心
		        	    else 
		        	    	return "redirect:/m/index/index";
		        	}else{
		        		return "redirect:/m/index/login";//跳转到登陆页面
		        	}
			}else{
				return "redirect:/m/index/login";//跳转到登陆页面
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/m/index/login";//跳转到登陆页面
		}
	}
	
	
	
	
	/**微信公共平台
	 * 这个方法是授权成功后，返回的路径，请求的这个方法 获取weixin用户的信息
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "weixincommonUserInfoC")
	public String weixincommonUserInfoC(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		response.setContentType("text/html,charset-utf-8"); 
		try {
			// 授权成功后获取code
			String code = request.getParameter("code");
			SNSUserINfo snsUserinfo=null;
			Member member=null;
			if(StringUtils.isNotEmpty(code)){
				 snsUserinfo=wechatService.getOauth2AccessToken(MemContents.appid,MemContents.appsecret, code);
		         if(snsUserinfo!=null){
		        	    Member memberwei=new Member();
		        	    memberwei.setMemberOpenid(snsUserinfo.getOpenId());
		        	    member = memberService.findMember(memberwei);
		        	    if(member==null){
		        	    	//saveInfoToMember(snsUserinfo.getNickname(),snsUserinfo.getNickname(),snsUserinfo.getSex(),snsUserinfo.getOpenId(), "weixin",snsUserinfo.getHeadImgUrl(), request);
		        	    	//return "redirect:/m/index/index";//跳转到个人中心
		        	    }
		        	    //else{
		        	    	String host = IpUtil.getIpAddr(request);
		        			Subject subject = SecurityUtils.getSubject();
		        			UsernamePasswordToken token = new UsernamePasswordToken(member.getMemberName(),member.getMemberOpenid().toCharArray(), false, host, null, false);
		        			subject.login(token);
		        			CacheUtils.initCacheUser(subject.getPrincipal().toString());
		        			memberService.updateweiMember(CacheUtils.getCacheUser().getMember().getMemberId());
		        			return "redirect:/m/authc/buyer/center";//跳转到个人中心
		        	   // }
		        	}else{
		        		return "redirect:/m/index/login";//跳转到登陆页面
		        	}
			}else{
				return "redirect:/m/index/login";//跳转到登陆页面
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/m/index/login";//跳转到登陆页面
		}
	}
	
	
	
	public static String urlEnodeUTF8(String str){
        String result = str;
        try {
            result = URLEncoder.encode(str,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
	
	public static void main(String[] args) {
		System.out.println(StringUtils.isBlank("null"));
		System.out.println(StringUtils.isEmpty("null"));
	}

}
