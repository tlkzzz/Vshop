package  com.Vshop.front.module.thirdlogin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.github.sd4324530.fastweixin.api.JsAPI;
import com.github.sd4324530.fastweixin.api.OauthAPI;
import com.github.sd4324530.fastweixin.api.UserAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.enums.OauthScope;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.api.response.GetSignatureResponse;
import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;
import com.github.sd4324530.fastweixin.api.response.GetUsersResponse;
import com.github.sd4324530.fastweixin.api.response.OauthGetTokenResponse;
import com.github.sd4324530.fastweixin.handle.EventHandle;
import com.github.sd4324530.fastweixin.handle.MessageHandle;
import com.github.sd4324530.fastweixin.message.Article;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.NewsMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;
import com.github.sd4324530.fastweixin.util.JSONUtil;
//import com.heyi.bean.SiteUserBean;
//import com.heyi.bean.SiteUserWealthBean;
//import com.heyi.extend.spring.PropertyConfigurerSon;
//import com.heyi.model.SiteUserModel;
//import com.heyi.service.SiteUserService;
//import com.heyi.service.SiteUserWealthService;
//import com.heyi.utils.EmojiFilter;
//import com.heyi.utils.HtmlUtil;
//import com.heyi.utils.PostTokenUtil;
//import com.heyi.utils.SpringContextUtil;
//import com.heyi.wx.extend.MyGetSignatureResponse;
//import com.heyi.wx.extend.MyMenuAPI;
//import com.heyi.wx.extend.api.CustomServiceAPI;
//import com.heyi.wx.extend.eventHandle.AgainSubscribe;
//import com.heyi.wx.extend.eventHandle.FirestSubscribe;
//import com.heyi.wx.extend.eventHandle.RecordUserInfoSubscribe;
//import com.heyi.wx.extend.eventHandle.RecordUserInfoUnsubscribe;
//import com.heyi.wx.extend.msgHandle.CustomServiceMsgHandle;
//import com.heyi.wx.extend.msgHandle.KeyWordMsgHandle;
import com.Vshop.front.utils.CommonConstants;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.front.module.thirdlogin.eventHandle.RecordUserInfoSubscribe;
import com.Vshop.front.module.thirdlogin.eventHandle.RecordUserInfoUnsubscribe;
import com.Vshop.core.auth.shiro.UsernamePasswordToken;
import com.Vshop.core.common.IpUtil;
import com.Vshop.core.entity.base.Member;
import com.Vshop.front.module.thirdlogin.eventHandle.FirestSubscribe;

@RestController
@RequestMapping(value = "/weixin", produces = "text/html;charset=UTF-8")
public class WeixinController extends WeixinControllerSupport {
	


//	@Autowired(required = false)
//	private SiteUserService<SiteUserBean> siteUserService;
//
//	private PropertyConfigurerSon _propertyConfigurerSon;

	@Autowired(required = false)
	private FirestSubscribe FirestSubscribe;

	@Autowired(required = false)
	private RecordUserInfoSubscribe recordUserInfoSubscribe;

	@Autowired(required = false)
	private RecordUserInfoUnsubscribe recordUserInfoUnsubscribe;
	
	@Resource
    private MemberService memberService;

//	@Autowired(required = false)
//	private AgainSubscribe againSubscribe;
//	
//	@Autowired(required = false)
//	private CustomServiceMsgHandle customServiceMsgHandle;
//	
//	@Autowired(required = false)
//	private SiteUserWealthService<SiteUserWealthBean> siteUserWealthService;
	
	

	
	/**
	 * 消息處理
	 */
	//@Autowired( required= false)
	//private KeyWordMsgHandle keyWordMsgHandle;
	
	private static final Logger log = LoggerFactory
			.getLogger(WeixinController.class);

	private String TOKEN = null;
	private String appid = null;
	private String appsecret = null;
	private String success = null;
	
	
	
	
	// 设置TOKEN，用于绑定微信服务器
	@Override
	protected String getToken() {
		if (StringUtils.isEmpty(TOKEN)) {
			this.TOKEN = CommonConstants.WEIXIN_TOKEN;
			this.appid =  CommonConstants.WEIXIN_APPID ;
			this.appsecret = CommonConstants.WEIXIN_APPSECRET;
			// ..其他参数
			log.debug(String.format("初始化微信appid,token ==> %s , %s", this.appid,
					this.TOKEN));
		}
		return this.TOKEN;
	}

	// 使用安全模式时设置：APPID
	// 不再强制重写，有加密需要时自行重写该方法
	@Override
	protected String getAppId() {
		if (StringUtils.isEmpty(appid)) {
			this.TOKEN = CommonConstants.WEIXIN_TOKEN;
			this.appid = CommonConstants.WEIXIN_APPID ;
			this.appsecret = CommonConstants.WEIXIN_APPSECRET;
			// ..其他参数
			log.debug(String.format("初始化微信appid,token ==> %s , %s", this.appid,
					this.TOKEN));
		}
		return this.appid;
	}

	// 使用安全模式时设置：密钥
	// 不再强制重写，有加密需要时自行重写该方法
	@Override
	protected String getAESKey() {
		return null;
	}

	// 重写父类方法，处理对应的微信消息
	@Override
	protected BaseMsg handleTextMsg(TextReqMsg msg) {
		String content = msg.getContent();
		log.debug("用户发送到服务器的内容:{}", content);

		System.out.println("==========appid======="+this.appid);
		System.out.println("=======appsecret=========="+this.appsecret);
		ApiConfig config = new ApiConfig(this.appid, this.appsecret);
		UserAPI userAPI = new UserAPI(config);
		GetUserInfoResponse userInfo = userAPI.getUserInfo(msg
				.getFromUserName());

		
		
		String strrespones = "亲爱的用户(正式服务器版):" + userInfo.getNickname()
				+ " ,欢迎访问课程汇 ";
		// try {
		// //strrespones = new String(strrespones.getBytes("utf-8"),
		// "ISO8859_1");
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return new TextMsg(strrespones);
//		return null;
	}

	/*
	 * 1.1版本新增，重写父类方法，加入自定义微信消息处理器不是必须的，上面的方法是统一处理所有的文本消息，如果业务觉复杂，上面的会显得比较乱
	 * 这个机制就是为了应对这种情况，每个MessageHandle就是一个业务，只处理指定的那部分消息
	 */
	@Override
	protected List<MessageHandle> initMessageHandles() {
		List<MessageHandle> handles = new ArrayList<MessageHandle>();
//		handles.add(new MyMessageHandle());
		
		/**
		 * 文字消息类
		 */
		//handles.add(keyWordMsgHandle);// 关键字回复
		//handles.add(customServiceMsgHandle); //记录客服消息
		
		
		
		return handles;
	}

	// 1.1版本新增，重写父类方法，加入自定义微信事件处理器，同上
	@Override
	protected List<EventHandle> initEventHandles() {
		List<EventHandle> handles = new ArrayList<EventHandle>();

		/**
		 * 订阅相关事件
		 */
		handles.add(recordUserInfoSubscribe);// 记录用户信息 [无返回消息]
		handles.add(FirestSubscribe);// 首次关注送豪礼
		//handles.add(againSubscribe);// 再次关注

		// handles.add(new MyEventHandle());

		/**
		 * 取消关注事件
		 */
		handles.add(recordUserInfoUnsubscribe);// 记录用户信息 [无返回消息]

		return handles;
	}

	/**
	 * 处理添加关注事件，有需要时子类重写
	 * 
	 * @param event
	 *            添加关注事件对象
	 * @return 响应消息对象
	 */
	
	
	@Override
	protected BaseMsg handleSubscribe(BaseEvent event) {
		
		return new TextMsg("感谢您的关注!");
	}
	/*
    //JSAPI接口
	@RequestMapping(value = "/getJsSDKConfig.json", method = RequestMethod.POST)
	@ResponseBody
	public void getJsSDKConfig(@RequestBody String jsonStr,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		log.debug("WX /weixin/getJSSDKConfig ===> request json : "+jsonStr.toString());
		PostTokenUtil postTokenUtil = (PostTokenUtil)SpringContextUtil.getBean("postTokenUtil");
		String key = "all";
		String heyitoken = request.getHeader(postTokenUtil.getToken(key));
		log.debug("request header穿过来的加密后的md5串 = "+heyitoken);
		log.debug("token是否正确 ： " + postTokenUtil.validateToken(jsonStr,heyitoken,key));
		if (!postTokenUtil.validateToken(jsonStr,heyitoken,key)) {
			log.debug("token不正确");
			HtmlUtil.writerJson(response, null);
			return;
		}
		Map<String, Object> params = JSONUtil.toMap(jsonStr);
		ApiConfig config = new ApiConfig(this.getAppId(), this.appsecret, true);
		JsAPI jsAPI = new JsAPI(config);
		log.debug("remote url :"+params.get(
				"targetUrl").toString());
		GetSignatureResponse gresponse = jsAPI.getSignature(params.get(
				"targetUrl").toString());
		MyGetSignatureResponse myGetSignatureResponse = new MyGetSignatureResponse();
		BeanUtils.copyProperties(gresponse, myGetSignatureResponse);
		myGetSignatureResponse.setAppid(this.appid);
		HtmlUtil.writerJson(response, myGetSignatureResponse);
	}

	//创建菜单接口
	@RequestMapping(value = "/createMenu.json", method = RequestMethod.POST)
	@ResponseBody
	public void createMenu(@RequestBody String jsonStr,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		log.debug("WX /weixin/createMenu ===> request json : "+jsonStr.toString());
		PostTokenUtil postTokenUtil = (PostTokenUtil)SpringContextUtil.getBean("postTokenUtil");
		String key = "all";
		String heyitoken = request.getHeader(postTokenUtil.getToken(key));
		log.debug("request header穿过来的加密后的md5串 = "+heyitoken);
		log.debug("token是否正确 ： " + postTokenUtil.validateToken(jsonStr,heyitoken,key));
		if (!postTokenUtil.validateToken(jsonStr,heyitoken,key)) {
			log.debug("token不正确");
			HtmlUtil.writerJson(response, null);
		}
		Map<String, Object> params = JSONUtil.toMap(jsonStr);
		ApiConfig config = new ApiConfig(this.getAppId(), this.appsecret, true);
	    MyMenuAPI menuAPI = new MyMenuAPI(config);	 
		ResultType resultType = menuAPI.createMenu(jsonStr);
		HtmlUtil.writerJson(response, resultType);
	}*/

	
	/**
	 * 发送客服消息
	 * @param jsonStr
	 * @param response
	 */
	/*
	@RequestMapping(value = "/sendCustomServiceTextMsg.json", method = RequestMethod.POST)
	@ResponseBody
	public void sendCustomServiceTextMsg(@RequestBody String jsonStr,
			HttpServletResponse response,HttpServletRequest request){
		log.debug("WX /weixin/sendCustomServiceTextMsg ===> request json : "+jsonStr.toString());
		PostTokenUtil postTokenUtil = (PostTokenUtil)SpringContextUtil.getBean("postTokenUtil");
		String key = "all";
		String heyitoken = request.getHeader(postTokenUtil.getToken(key));
		log.debug("request header穿过来的加密后的md5串 = "+heyitoken);
		log.debug("token是否正确 ： " + postTokenUtil.validateToken(jsonStr,heyitoken,key));
		if (!postTokenUtil.validateToken(jsonStr,heyitoken,key)) {
			log.debug("token不正确");
			HtmlUtil.writerJson(response, null);
			return;
		}
		//获取输入参数
		Map<String, Object> params = JSONUtil.toMap(jsonStr);
		
		String txtMsg = (String)params.get("msg");
		String sendOpenID = (String)params.get("openid");//接收人  微信的openid
		
		ApiConfig config = new ApiConfig(this.getAppId(), this.appsecret);
		CustomServiceAPI customServiceAPI = new CustomServiceAPI(config);
		
		BaseResponse sendTextMsgResponse = customServiceAPI.SendTextMsg(txtMsg, sendOpenID);
		
		HtmlUtil.writerJson(response, sendTextMsgResponse);
	}
	*/
	
	
	/**
	 * oauthor2.0
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	//发红包
	@SuppressWarnings("static-access")
	@RequestMapping("/authors")
	public void author(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String code = null;
		if (request.getParameter("code") != null) {
			code = request.getParameter("code").toString();
		}
		ApiConfig config = new ApiConfig(getAppId(), this.appsecret);
		OauthAPI oauthAPI = new OauthAPI(config);
		if (code == null) { //非腾讯回调地址
			String wxauthorurl =  CommonConstants.WEIXIN_REDIRECT_URI_FHB;
			//获取URL当前参数

			if (request.getParameter("p") != null) {
            	String p = request.getParameter("p").toString();
            	wxauthorurl+="?p="+p;
            }
			String pageUrl = oauthAPI.getOauthPageUrl(wxauthorurl ,OauthScope.SNSAPI_USERINFO, "123");
			System.out.println("=============="+pageUrl);
			response.sendRedirect(pageUrl);
		} 
	}
	@SuppressWarnings("static-access")
	@RequestMapping("/author")
	public void authors(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String code = null;
		if (request.getParameter("code") != null) {
			code = request.getParameter("code").toString();
		}
	 
		ApiConfig config = new ApiConfig(getAppId(), this.appsecret);

		OauthAPI oauthAPI = new OauthAPI(config);

	
		if (code == null) { //非腾讯回调地址
			
			
			//http://localhost:8080/HEYI_WX/weixin/author?id=2&kk=3&bb=4
			// 这里可以自定义需要的参数
			// 比如游戏分享的时候 要确定分享人的时候可以
			// http://localhost:8080/HEYI_WX/weixin/author?id=3&shareUseropenid=XXXXX
			String wxauthorurl =  CommonConstants.WEIXIN_REDIRECT_URI;
			//获取URL当前参数
			if (request.getParameter("storeId") != null) {
            	String storeId = request.getParameter("storeId").toString();
            	wxauthorurl+="?storeId="+storeId;
            }
			String pageUrl = oauthAPI.getOauthPageUrl(wxauthorurl ,
					OauthScope.SNSAPI_USERINFO, "123");
            
			System.out.println("=============="+pageUrl);
			response.sendRedirect(pageUrl);

		} else {
			//腾讯回调的必定包含code
			
			
			OauthGetTokenResponse  token = oauthAPI.getToken(code);

			// 1 .从微信接口获取 用户详细信息
			GetUserInfoResponse userInfo = oauthAPI.getUserInfo( token.getAccessToken(),  token.getOpenid());
			
			 if(userInfo!=null){
	        	    Member memberwei=new Member();
	        	    memberwei.setMemberOpenid(userInfo.getOpenid());
	        	    Member  member = memberService.findMember(memberwei);
	        	    if(member==null){
	        	    	//saveInfoToMember(snsUserinfo.getNickname(),snsUserinfo.getNickname(),snsUserinfo.getSex(),snsUserinfo.getOpenId(), "weixin",snsUserinfo.getHeadImgUrl(), request);
	        	    	//return "redirect:/m/index/index";//跳转到个人中心
	        	    }
	        	    //else{
	        	    	String host = IpUtil.getIpAddr(request);
	        			Subject subject = SecurityUtils.getSubject();
	        			UsernamePasswordToken tokenP = new UsernamePasswordToken(member.getMemberName(),member.getMemberOpenid().toCharArray(), false, host, null, false);
	        			subject.login(tokenP);
	        			CacheUtils.initCacheUser(subject.getPrincipal().toString());
	        			memberService.updateweiMember(CacheUtils.getCacheUser().getMember().getMemberId());
	        			response.sendRedirect("/Vshop-front/m/index/index");
	        			//return "redirect:/m/index/index";//跳转到个人中心
	        	   // }
	        	}else{
	        		response.sendRedirect("/m/index/login");
	        	
	        	}
			 
			 

		}

	}
	
	
	@SuppressWarnings("static-access")
	@RequestMapping("/detail")
	public void detail(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String code = null;
		if (request.getParameter("code") != null) {
			code = request.getParameter("code").toString();
		}
	 
		ApiConfig config = new ApiConfig(getAppId(), this.appsecret);

		OauthAPI oauthAPI = new OauthAPI(config);

	
		if (code == null) { //非腾讯回调地址
			
			
			//http://localhost:8080/HEYI_WX/weixin/author?id=2&kk=3&bb=4
			// 这里可以自定义需要的参数
			// 比如游戏分享的时候 要确定分享人的时候可以
			// http://localhost:8080/HEYI_WX/weixin/author?id=3&shareUseropenid=XXXXX
			String wxauthorurl =  CommonConstants.WEIXIN_REDIRECT_URI_G;
			//获取URL当前参数
			if (request.getParameter("storeId") != null) {
            	String storeId = request.getParameter("storeId").toString();
            	wxauthorurl+="?storeId="+storeId;
            }
			if (request.getParameter("goodsId") != null) {
            	String goodsId = request.getParameter("goodsId").toString();
            	wxauthorurl+="&goodsId="+goodsId;
            }
			String pageUrl = oauthAPI.getOauthPageUrl(wxauthorurl ,
					OauthScope.SNSAPI_USERINFO, "123");
            
			System.out.println("=============="+pageUrl);
			response.sendRedirect(pageUrl);

		} else {
			//腾讯回调的必定包含code
			
			
			OauthGetTokenResponse  token = oauthAPI.getToken(code);

			// 1 .从微信接口获取 用户详细信息
			GetUserInfoResponse userInfo = oauthAPI.getUserInfo( token.getAccessToken(),  token.getOpenid());
			
			 if(userInfo!=null){
	        	    Member memberwei=new Member();
	        	    memberwei.setMemberOpenid(userInfo.getOpenid());
	        	    Member  member = memberService.findMember(memberwei);
	        	    if(member==null){
	        	    	//saveInfoToMember(snsUserinfo.getNickname(),snsUserinfo.getNickname(),snsUserinfo.getSex(),snsUserinfo.getOpenId(), "weixin",snsUserinfo.getHeadImgUrl(), request);
	        	    	//return "redirect:/m/index/index";//跳转到个人中心
	        	    }
	        	    //else{
	        	    	String host = IpUtil.getIpAddr(request);
	        			Subject subject = SecurityUtils.getSubject();
	        			UsernamePasswordToken tokenP = new UsernamePasswordToken(member.getMemberName(),member.getMemberOpenid().toCharArray(), false, host, null, false);
	        			subject.login(tokenP);
	        			CacheUtils.initCacheUser(subject.getPrincipal().toString());
	        			memberService.updateweiMember(CacheUtils.getCacheUser().getMember().getMemberId());
	        			response.sendRedirect("/Vshop-front/m/index/index");
	        			
	        	   // }
	        	}else{
	        		response.sendRedirect("/m/index/login");
	        	
	        	}
			 
			 

		}

	}
	
	
	//m/authc/buyer/center
	
	
	@SuppressWarnings("static-access")
	@RequestMapping("/authorcenter")
	public void authorcenter(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String code = null;
		if (request.getParameter("code") != null) {
			code = request.getParameter("code").toString();
		}
	 
		ApiConfig config = new ApiConfig(getAppId(), this.appsecret);

		OauthAPI oauthAPI = new OauthAPI(config);

	
		if (code == null) { //非腾讯回调地址
			
			
			//http://localhost:8080/HEYI_WX/weixin/author?id=2&kk=3&bb=4
			// 这里可以自定义需要的参数
			// 比如游戏分享的时候 要确定分享人的时候可以
			// http://localhost:8080/HEYI_WX/weixin/author?id=3&shareUseropenid=XXXXX
			String wxauthorurl =  CommonConstants.WEIXIN_REDIRECT_URI_C;
			//获取URL当前参数
			
			String pageUrl = oauthAPI.getOauthPageUrl(wxauthorurl ,
					OauthScope.SNSAPI_USERINFO, "123");

			System.out.println("=============="+pageUrl);
			response.sendRedirect(pageUrl);

		} else {
			//腾讯回调的必定包含code
			
			
			OauthGetTokenResponse  token = oauthAPI.getToken(code);

			// 1 .从微信接口获取 用户详细信息
			GetUserInfoResponse userInfo = oauthAPI.getUserInfo( token.getAccessToken(),  token.getOpenid());
			
			 if(userInfo!=null){
	        	    Member memberwei=new Member();
	        	    memberwei.setMemberOpenid(userInfo.getOpenid());
	        	    Member  member = memberService.findMember(memberwei);
	        	    if(member==null){
	        	    	//saveInfoToMember(snsUserinfo.getNickname(),snsUserinfo.getNickname(),snsUserinfo.getSex(),snsUserinfo.getOpenId(), "weixin",snsUserinfo.getHeadImgUrl(), request);
	        	    	//return "redirect:/m/index/index";//跳转到个人中心
	        	    }
	        	    //else{
	        	    	String host = IpUtil.getIpAddr(request);
	        			Subject subject = SecurityUtils.getSubject();
	        			UsernamePasswordToken tokenP = new UsernamePasswordToken(member.getMemberName(),member.getMemberOpenid().toCharArray(), false, host, null, false);
	        			subject.login(tokenP);
	        			CacheUtils.initCacheUser(subject.getPrincipal().toString());
	        			memberService.updateweiMember(CacheUtils.getCacheUser().getMember().getMemberId());
	        			response.sendRedirect("/Vshop-front/m/authc/buyer/center");
	        			//return "redirect:/m/index/index";//跳转到个人中心
	        	   // }
	        	}else{
	        		response.sendRedirect("/m/index/login");
	        	
	        	}
			 
			 

		}

	}
	
	/*
	
	//查询单个微信用户
	@RequestMapping(value = "/getUserInfo.json", method = RequestMethod.POST)
	@ResponseBody
	public void getUserInfo(@RequestBody String jsonStr, HttpServletResponse response, HttpServletRequest request){
		log.debug("WX /weixin/getUserInfo ===> request json : "+jsonStr.toString());
		PostTokenUtil postTokenUtil = (PostTokenUtil)SpringContextUtil.getBean("postTokenUtil");
		String key = "all";
		String heyitoken = request.getHeader(postTokenUtil.getToken(key));
		log.debug("request header穿过来的加密后的md5串 = "+heyitoken);
		log.debug("token是否正确 ： " + postTokenUtil.validateToken(jsonStr,heyitoken,key));
		if (!postTokenUtil.validateToken(jsonStr,heyitoken,key)) {
			log.debug("token不正确");
			HtmlUtil.writerJson(response, null);
			return;
		}
		
		
		Map<String, Object> params = JSONUtil.toMap(jsonStr);
		ApiConfig config = new ApiConfig(this.getAppId(), 
				(String) _propertyConfigurerSon.getContextProperty("wx.appsecret"), true);
		UserAPI userAPI = new UserAPI(config);
		String Openid =(String) params.get("Openid");
		GetUserInfoResponse userInfo = userAPI.getUserInfo(Openid);
		
		HtmlUtil.writerJson(response, userInfo);
	}
	
	//查询微信用户列表
	@RequestMapping(value = "/getUsers.json", method = RequestMethod.POST)
	@ResponseBody
	public void getUsers(@RequestBody String jsonStr, HttpServletResponse response, HttpServletRequest request){
		log.debug("WX /weixin/getUsers ===> request json : "+jsonStr.toString());
		PostTokenUtil postTokenUtil = (PostTokenUtil)SpringContextUtil.getBean("postTokenUtil");
		String key = "all";
		String heyitoken = request.getHeader(postTokenUtil.getToken(key));
		log.debug("request header穿过来的加密后的md5串 = "+heyitoken);
		log.debug("token是否正确 ： " + postTokenUtil.validateToken(jsonStr,heyitoken,key));
		if (!postTokenUtil.validateToken(jsonStr,heyitoken,key)) {
			log.debug("token不正确");
			HtmlUtil.writerJson(response, null);
			return;
		}
		
		Map<String, Object> params = JSONUtil.toMap(jsonStr);
		ApiConfig config = new ApiConfig(this.getAppId(), 
				(String) _propertyConfigurerSon.getContextProperty("wx.appsecret"), true);
		UserAPI userAPI = new UserAPI(config);
		
		String nextOpenid =(String) params.get("nextOpenid");
		
		GetUsersResponse users = userAPI.getUsers(nextOpenid);
		
		HtmlUtil.writerJson(response, users);
	}*/
}