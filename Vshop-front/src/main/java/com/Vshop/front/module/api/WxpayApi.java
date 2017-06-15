package com.Vshop.front.module.api;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Vshop.core.common.StringUtils;
import com.Vshop.core.entity.base.Pay;
import com.Vshop.front.pay.mobile.wxpay.util.SignUtils;
import com.Vshop.service.module.trade.service.PayService;

/**
 * 微信
 * @author kviuff
 * @date 2015-08-17
 */
@Controller
@RequestMapping("/wxpay/api")
public class WxpayApi extends BaseApi {
	
	@Resource
	private PayService payService;
	
	/**
	 * 服务器端对订单信息进行签名
	 * @param orderCode 订单号
	 * @return
	 */
	@RequestMapping("towxpayInfo")
	@ResponseBody
	public JSONObject towxpay(
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
			if(ordert != null) {
				// 订单状态：0:已取消;10:待付款;20:待发货;30:待收货;40:交易完成;50:已提交;60:已确认;
				if(ordert.getOrderState()!=null){ //单个订单支付
					if(ordert.getOrderState() == 0) {// 支付状态为1表示已支付
						jsonObj.put("result", 0);
						jsonObj.put("msg", "该订单已取消，无法进行付款操作,订单签名失败!");
						return jsonObj;
					} else if(ordert.getOrderState() == 20) {// 支付状态为1表示已支付
						jsonObj.put("result", 0);
						jsonObj.put("msg", "该订单已支付完成，无法进行付款操作,订单签名失败!");
						return jsonObj;
					}
				}
				jsonObj.put("result" ,1);
				jsonObj.put("data" , new SignUtils().advancePayment(ordert,false));// 对订单进行签名
			} else {
				jsonObj.put("result", 0);
				jsonObj.put("msg", "订单信息不存在，无法进行签名！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObj.put("result", 0);
	        jsonObj.put("msg", "服务器异常");
		}
		return jsonObj;
	}
	
}
