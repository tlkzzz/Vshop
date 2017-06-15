package com.Vshop.front.module.weChatpay.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Vshop.core.entity.SNSUserINfo;
import com.Vshop.core.entity.WeiXinOauth2Token;
import com.Vshop.core.entity.base.Pay;
import com.Vshop.front.MemContents;
import com.Vshop.front.module.weChatpay.service.WechatService;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.front.wechat.handler.HttpConnect;
import com.Vshop.front.wechat.handler.HttpRespons;
import com.Vshop.front.wechat.handler.RequestHandler;
import com.Vshop.front.wechat.handler.Sha1Util;
import com.Vshop.front.wechat.handler.TenpayUtil;
import com.Vshop.front.wechat.service.WXPrepay;
import com.Vshop.front.wechat.util.WXPay;
import com.Vshop.front.wechat.util.WeixinUtil;
import com.Vshop.service.module.trade.service.PayService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/weChatpayment")
public class WechatAction {
	@Resource
	private WechatService wechatService;
	@Resource
	private PayService payService;
	
	//微信进行首次链接验证确保正确的token
	@RequestMapping("topay")
	public void toPay(HttpServletRequest request ,HttpServletResponse response){
		wechatService.toPay(request, response);
	}
	/**
	 * 提交订单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "submitOrder")
	@ResponseBody
	public ModelAndView submitOrder(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("/html5/buyer/orderpay");
		
		Pay orderPay = payService.findPayBySn(request.getParameter("paySn"));
		
		

		 // 判断是否微信环境, 5.0 之后的支持微信支付
		boolean isweixin = isWeiXin(request);
		if (isweixin) {
			//应用ID
			String appid = MemContents.appid;
			//应用密钥
			String appsecret = MemContents.appsecret;
			//微信支付商户号
			String partner = MemContents.partner;
			//交易密码
			String partnerkey = MemContents.partnerkey;
			
			
			String orderId=orderPay.getPaySn();;
			
			
			String goodsType="7";//课程类型
			String supportvalue=orderPay.getPayAmount()+"";//课程价值
			String goodsname=orderPay.getPaySn();//课程名称
			
			//String sendpay="1";//运费
			if(StringUtils.isEmpty(goodsname)){
				goodsname = goodsType;
			}
		
			//金额转化为分为单位
			float sessionmoney = Float.parseFloat(supportvalue);
			String finalmoney = String.format("%.2f", sessionmoney);
			int i = finalmoney.indexOf(".");
			int pay = 0;
			if(Integer.parseInt(finalmoney.substring(i+1,finalmoney.length()))==0){
				pay = Integer.parseInt(finalmoney.substring(0, i))*100;
			}else{
				pay = Integer.parseInt(finalmoney.replace(".", ""));
			}
			String total_fee = String.valueOf(pay);
			
			String openId =CacheUtils.getCacheUser().getMember().getMemberOpenid();
			
			
		
			
			
			
			//商户号
			String mch_id = partner;
			//子商户号  非必须


			//订单生成的机器 IP
//			String spbill_create_ip = request.getRemoteAddr();
			String spbill_create_ip = getIp(request);
			int j = spbill_create_ip.indexOf(",");
			spbill_create_ip = spbill_create_ip.substring(j+1).trim();
			

			
			//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
			String notify_url ="http://www.ubisp.com/weChatpayment/updateorderstate";
			
			
			WXPrepay prePay = new WXPrepay();
			prePay.setAppid(appid);
			prePay.setBody(goodsname);
			prePay.setPartnerKey(partnerkey);
			prePay.setOpenid(openId);
			prePay.setMch_id(mch_id);
			// prePay.set
			// 设置支付通知地址

			prePay.setNotify_url(notify_url);
			// 设置订单编号
			prePay.setOut_trade_no(orderId);
			prePay.setSpbill_create_ip(spbill_create_ip);
			// 支付金额，从页面端设置 支付金额需要查询

			
			
			prePay.setTotal_fee(total_fee + "");
			// prePay.setTotal_fee("1");
			prePay.setTrade_type("JSAPI");
			// 此处添加获取openid的方法，获取预支付订单需要此参数！！！！！！！！！！！
			// 获取预支付订单号
			String prepay_id = null;
			
		
			
			try {
				//需要改这个
				//prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
				prepay_id = prePay.submitXmlGetPrepayId();
				
				System.out.println("========prepayid======="+prepay_id);
				if(prepay_id.equals("")){
					request.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
	    			//response.sendRedirect("/weChatpay/error.jsp");
					
					 model = new ModelAndView("/html5/buyer/order_erro");
					//response.sendRedirect("http://act2.cishinet.net/Vshop-front/cart/payerro");
					System.out.println("*****a3&&&&&&&&&&:"+prepay_id);
					
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			String jsParam =WXPay.createPackageValue(appid,
					partnerkey, prepay_id, prePay.getOpenid());
			
			System.out.print("============"+jsParam);
			model.addObject("jsParam",jsParam);
			
		}
		
		
		
    	return model;
			
	}
	
		/**
	     * 判断是否来自微信, 5.0 之后的支持微信支付
	     *
	     * @param request
	     * @return
	     */
	    public static boolean isWeiXin(HttpServletRequest request) {
	        String userAgent = request.getHeader("User-Agent");
	        if (StringUtils.isNotBlank(userAgent)) {
	            Pattern p = Pattern.compile("MicroMessenger/(\\d+).+");
	            Matcher m = p.matcher(userAgent);
	            String version = null;
	            if (m.find()) {
	                version = m.group(1);
	            }
	            return (null != version && NumberUtils.toInt(version) >= 5);
	        }
	        return false;
	    }
		 /**
		 * 获取ip
		 * @param request
		 * @return
		 */
		public static String getIp(HttpServletRequest request) {
			if (request == null)
				return "";
			String ip = request.getHeader("X-Requested-For");
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("X-Forwarded-For");
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			return ip;
		}
	
	
	//获得code后进行第二次跳转跳转的pay.html支付页面
	@RequestMapping("/topay2")
	public void toPay2(HttpServletRequest request ,HttpServletResponse response)throws  IOException{
		wechatService.toPay2(request, response);
	}
	//修改订单状态
	@SuppressWarnings("unchecked")
	@RequestMapping("/updateorderstate")
	public void updateorderstate(HttpServletRequest request ,HttpServletResponse response,Model model)throws  IOException, DocumentException{
		PrintWriter out = null;
		int status = 0;//0失败 ，1 成功
		try {
			out= response.getWriter();
		}catch (IOException e2){
			e2.printStackTrace();
		}
		 // 解析结果存储在HashMap
	    Map<String, String> map = new HashMap<String, String>();
	    InputStream inputStream = request.getInputStream();
	    // 读取输入流
	    SAXReader reader = new SAXReader();
	    Document document = reader.read(inputStream);
	    // 得到xml根元素
	    Element root = document.getRootElement();
	    // 得到根元素的所有子节点
	    List<Element> elementList = root.elements();
	    // 遍历所有子节点
	    for (Element e : elementList)
	        map.put(e.getName(), e.getText());
	    // 释放资源
	    inputStream.close();
	    inputStream = null;
	    System.out.println(map);
	    //根据反过来支付信息修改订单状态
	    if(map.get("result_code")!=null&& map.get("result_code").equals("SUCCESS")){
	    	System.out.println("修改成功了"+map.get("out_trade_no"));
	    	//根据订单号修改订单信息
	    	payService.updatePayFinish(map.get("out_trade_no"));
	    	out.print("SUCCESS");
	    }
	    /*String rebackurl="";
		String msg="";
		if(!"".equals(map.get("out_trade_no"))){
			if(map.get("out_trade_no").contains("R")){
				status = 1;
				rebackurl="/cart/topup_result";
				msg = "恭喜您，充值成功！";
			}else{
				status = 1;
				rebackurl="/cart/pay_result";
				msg = "恭喜您，支付成功！";
			}
		}
		return null;*/
	}
	
	 //获取网页授权凭证
	  @RequestMapping("getAccessToken")
	  public void getWeiXinOauth2Token(HttpServletRequest request){
		  String code=request.getParameter("code");
		  WeiXinOauth2Token wat=null;
		  if(StringUtils.isNotEmpty(code)&&code.equals("authdeny")){
			  WeixinUtil.httpRequest(MemContents.h5index,"GET", null);
		  }else if(StringUtils.isNotEmpty(code)&&code.equals("CODE")){
			  wechatService.getOauth2AccessToken(MemContents.appid, MemContents.appsecret,code);//appid 公众账号唯一标示 ，appsecret 公众账号的秘钥
		  }
	  }
	  //刷新网页授权凭证  appid 公众账号唯一标示,refreshToken 刷新凭证 
	  public WeiXinOauth2Token refreshOauth2AccessToken(String Appid,String refreshToken){

		  if(StringUtils.isNotEmpty(refreshToken) && StringUtils.isNotEmpty(Appid)){
			  wechatService.refreshOauth2AccessToken(Appid, refreshToken);
		  }
		  return null;
	  }
	  
	  //通过网页授权获取页面信息  access_token 网页授权接口调用凭证,openid 用户的唯一标识 
	  public SNSUserINfo getSNSUserInfo(String accessToken, String openid){
		  SNSUserINfo snsUserInfo=null;
		  if(StringUtils.isNotEmpty(accessToken) && StringUtils.isNotEmpty(openid)){
			  snsUserInfo=wechatService.getSNSUSerInfo(accessToken, openid);
		  }
		  return snsUserInfo;
	  }
}
