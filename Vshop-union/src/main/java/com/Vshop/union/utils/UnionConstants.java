package com.Vshop.union.utils;

/**
 * 联盟常量
 * 
 * @author liuzhen
 * @version 2015-9-24
 */
public class UnionConstants {

	/********************************** 初次使用需要配置区 ************************************/

	/** client_id:创建应用时的Appkey（从JOS控制台->管理应用中获取） */
	public final static String JD_CLIENT_ID = "3B7EE2372E06E8F0E4C4B51DA646A0C2";
	/** appsecret值 */
	public final static String JD_APPSECRET = "2d2af1102e3f467cbe29de441bd00087";
	/** 应用的回调地址，必须与创建应用时所填回调页面url一致 */
	public final static String JD_CALLBACK_URL = "http://58.132.170.171:90/Vshop-admin/unionJD/callBack";
	/** 参数为您在京东联盟网站注册的网站编号，也可以在联盟网站上找到。 */
	public final static String JD_WEBID = "340898175";
	/** 联盟ID(京东客ID) */
	public final static long JD_UNIONID = 320958015L;

	/*************************** 系统自动更新区，无需手动填写 *********************************/

	/** code值 */
	public static String JD_CODE = "mnyx66";// iFvLSd
	/** refresh_token值 */
	public static String JD_REFRESH_TOKEN = "708dbb0b-8d6c-4b07-9a36-ce7e0a6bef63";
	/** accesstoken值 */
	public static String jd_ACCESSTOKEN = "377fd3d0-0196-497e-8cf7-ed786600b21a";
	/** 授权的时间点（UNIX时间戳，单位：毫秒） */
	public static long JD_TIME = 1442993187235L;
	/** 失效时间（从当前时间算起，单位：秒） */
	public static long JD_EXPIRES_IN = 86399;

	/************************************** 固定值区域 **************************************/

	/** 移动端授权，该值固定为wap；非移动端授权，无需传值 */
	public final static String JD_VIEW = "";
	/** 状态参数，由ISV自定义，颁发授权后会原封不动返回 */
	public final static String JD_CODE_STATE = "getCode";
	/** 状态参数，由ISV自定义，颁发授权后会原封不动返回 */
	public final static String JD_TOKEN_STATE = "getToken";
	/** 固定为code */
	public final static String JD_RESPONSE_TYPE = "code";
	/** 权限参数，API组名串。多个组名时，用"，"分隔，目前支持参数值：read */
	public final static String JD_SCOPE = "red";

	/************************************ 接口地址区 ****************************************/

	/** 获取code接口地址 */
	public static String JD_GETCODE_URL = "https://oauth.jd.com/oauth/authorize"
			+ "?response_type="
			+ JD_RESPONSE_TYPE
			+ "&client_id="
			+ JD_CLIENT_ID
			+ "&redirect_uri="
			+ JD_CALLBACK_URL
			+ "&state="
			+ JD_CODE_STATE + "&scope=" + JD_SCOPE + "&view=" + JD_VIEW;

	/** 通过code后去token接口地址 */
	public static String JD_GETACCESSTOKEN_URL = "https://oauth.jd.com/oauth/token"
			+ "?grant_type="
			+ "authorization_code"
			+ "&client_id="
			+ JD_CLIENT_ID
			+ "&client_secret="
			+ JD_APPSECRET
			+ "&scope="
			+ JD_SCOPE
			+ "&redirect_uri="
			+ JD_CALLBACK_URL
			+ "&state="
			+ JD_TOKEN_STATE + "&code=";

	/** 刷新token接口地址 */
	public static String JD_REFRESH_TOKEN_URL = "https://oauth.jd.com/oauth/token"
			+ "?client_id="
			+ JD_CLIENT_ID
			+ "&client_secret="
			+ JD_APPSECRET
			+ "&grant_type=refresh_token" + "&refresh_token=";

	/** 京东联盟获取推广商品信息接口地址 */
	public final static String JD_GOODS_PROMOTION_URL = "https://api.jd.com/routerjson";

	/*********************************** 错误码区 *****************************************/

	/** 京东错误说明参考 */
	public final static String JD_ERROR_MSG = "下面错误参考："
			+ "<br/>-1：为系统异常"
			+ "<br/><br/>客户端异常 "
			+ "<br/>101 : 没找到应用（确认应用是否已“上线运行”；确认应用类型是否为“通用应用”，“无线应用”、“网站应用”无法被授权）"
			+ "<br/><br/>用户登录时，用户异常如下："
			+ "<br/>201 : 您的IP服务受限，请联系客服解决"
			+ "<br/>202 : 请输入用户名"
			+ "<br/>203 : 用户名不存在"
			+ "<br/>204 : 请输入密码"
			+ "<br/>205 : 登录信息与密码不匹配"
			+ "<br/>206 : 用户名与密码不匹配，还可尝试{0}次，如失败账户将被冻结2小时"
			+ "<br/>207 : 登录失败超过6次，账户已被冻结2个小时"
			+ "<br/>208 : 容器检查登录用户不在应授权用户中（个人账号不能给商家应用授权）"
			+ "<br/>209 : 未知异常，请联系管理员"
			+ "<br/>301 : 缺少responsetype参数 或者为空"
			+ "<br/?>302 : 缺少clientId 参数 或者为空"
			+ "<br/>303 : 缺少redirectUri 参数 或者为空"
			+ "<br/>304 : 防止session伪造（在授权过程中点击了回退按钮）"
			+ "<br/>305 : 拼写的redirect_uri和注册应用的“回调页面URL”不一致"
			+ "<br/><br/>用户登录时，系统异常如下："
			+ "<br/><br/>251 : 调用验证登录信息的接口失败"
			+ "<br/>251 : 调用验证登录信息接口返回的json串格式错误，解析失败"
			+ "<br/><br/>其他异常"
			+ "<br/>401 : 没有此流程的认证权限（应用使用了错误的授权方式进行授权）"
			+ "<br/>402 : 错误的code（1、code码超时，code码时效为5分钟；2、测试环境与正式环境code码混用；3、自己编造的code码）"
			+ "<br/>403 : url不匹配（请求url与开发者中心创建应用时填写的url不一致；2、没有填写url）"
			+ "<br/>404 : 错误的请求（JOS授权安全机制限制：用户在授权页面输入账号密码出错后，重新在原页面输入账号密码均会报404。处理方法：请重新获取授权页面，或回退两次即可）"
			+ "<br/>405 : \"code\":\"405\",\"error_description\":\"用户[xxx]无权给app[C05EBAAB019CC6E91D44D45834ER]授权\"（请将报错信息，包含用户名及appkey发送至jos#jd.com申请绑定授权关系）"
			+ "<br/><br/>京东联盟文档地址：<a href=\"http://jos.jd.com/doc/channel.htm?id=152\">http://jos.jd.com/doc/channel.htm?id=152</a>"
			+ "<br/><br/>如遇到code失效，需要重新授权code,请点此登录  <a href=\""
			+ JD_GETCODE_URL + "\">重新授权</a>";
}
