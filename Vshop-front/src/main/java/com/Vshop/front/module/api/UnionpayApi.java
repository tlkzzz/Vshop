package com.Vshop.front.module.api;

import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Vshop.core.common.StringUtils;
import com.Vshop.core.entity.base.Pay;
import com.Vshop.front.pay.mobile.unionpay.acp.util.AppConsumeUtil;
import com.Vshop.service.module.trade.service.PayService;

/**
 * 银联
 * @author kviuff
 * @date 2015-08-17
 */
@Controller
@RequestMapping("/unionpay/api")
public class UnionpayApi extends BaseApi {
	
	@Resource
	private PayService payService;
	@Resource
	private PayService payservice;
	/**
	 * 服务器端对订单信息进行签名
	 * @param orderCode 订单号
	 * @return
	 */
	@RequestMapping("tounionpay")
	@ResponseBody
	public JSONObject tounionpay(
			 @RequestParam(value="orderCode",required = false, defaultValue="") String orderCode,
			 @RequestParam(value="amount",required=false) Double amount,
			 @RequestParam(value="memberId",required=false) Integer memberId){
		jsonObj = new JSONObject();
		try {
			// 支付
			Pay ordert=null;
			//判断是订单支付还是余额支付
			if(StringUtils.isNotBlank(orderCode)){
				ordert = payService.findPayBySn(orderCode.trim());
			}else{
				ordert = payService.addPredepositRechargeToPay(amount, memberId);
			}
			//判读是充值还是购物
			if(!"".equals(orderCode)){
			   payservice.updatePaymentBySn(orderCode,"ZFB");
			}
			if(ordert != null) {
					if(ordert != null){
					     	Map<String , Object> resultMap = AppConsumeUtil.getTn(ordert,false);
					     	if(resultMap.containsKey("respCode")) {
								Object resCodeValue = resultMap.get("respCode");
								if(resCodeValue != null && "00".equals(resCodeValue.toString())) {
									if(resultMap.containsKey("tn")) {
										jsonObj.put("result" ,1);
										jsonObj.put("tn" ,resultMap.get("tn"));
									}else {
										jsonObj.put("tn" ,"");
										jsonObj.put("result" ,0);
										jsonObj.put("msg", "请求异常,交易流水号获取失败!");
									}
								} else {
									jsonObj.put("tn" , "");
									jsonObj.put("result" ,0);
									jsonObj.put("msg", "请求异常异常代码【" + resCodeValue + "】,交易流水号获取失败!");
								}
							}
				  }
			} else {
				jsonObj.put("result", 0);
				jsonObj.put("msg", "订单信息不存在！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObj.put("result", 0);
	        jsonObj.put("msg", "服务器异常");
		}
		return jsonObj;
	}
	
}
