package com.Vshop.front.pay.mobile.unionpay.acp.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.Vshop.core.entity.base.Pay;
import com.Vshop.front.pay.mobile.unionpay.acp.util.base.BaseUtil;
import com.Vshop.front.pay.mobile.unionpay.config.appConsumeConfig.AppConsumeConfig;
import com.Vshop.front.unionpay.sdk.SDKConfig;
import com.Vshop.front.utils.MoneyConvertUtil;


/**
 * 名称： 第一卷 商户卷 第5部分 手机支付 ——手机控件支付课程<br>
 * 功能： 6.2　消费类交易<br>
 * 前台交易类<br>
 * 版本： 5.0<br>
 * 日期： 2014-07<br>
 * 
 * @company 雷铭智信j
 * @author 谢进伟
 * @DateTime 2015-3-29 上午11:59:34
 */
public class AppConsumeUtil extends BaseUtil {
	
	/**
	 * 获取银联流水号
	 * 
	 * @param isTopUp
	 * 
	 * @return
	 */
	public static Map<String , Object> getTn(Pay ordert , boolean isTopUp) {
		try {
			// 请求报文组装数据
			Map<String , String> data = new HashMap<String , String>();
			// 响应报文数据
			Map<String , Object> resultMap = null;
			AppConsumeConfig acc = AppConsumeConfig.getConfig();
			// 版本号
			data.put("version" , acc.getVersion());
			// 字符集编码 默认"UTF-8"
			data.put("encoding" , acc.getEncoding());
			// 签名方法 01 RSA
			data.put("signMethod" , acc.getSignMethod());
			// 交易类型 01-消费
			data.put("txnType" , acc.getTxnType());
			// 交易子类型 01:自助消费 02:订购 03:分期付款
			data.put("txnSubType" , acc.getTxnSubType());
			// 业务类型
			data.put("bizType" , acc.getBizType());
			// 渠道类型，07-PC，08-手机
			data.put("channelType" , acc.getChannelType());
			// 前台通知地址 ，控件接入方式无作用
			data.put("frontUrl" , acc.getFrontUrl());
			// 后台通知地址
			data.put("backUrl" , isTopUp ? acc.getNotifyUrl_topUp() : acc.getBackUrl());
			// 接入类型，商户接入填0 0- 商户 ， 1： 收单， 2：平台商户
			data.put("accessType" , acc.getAccessType());
			// 商户号码，请改成自己的商户号
			data.put("merId" , acc.getMerId());
			// 商户订单号，8-40位数字字母
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date nowDate = new Date();
			data.put("orderId" ,ordert.getPaySn());
			// 订单发送时间，取系统时间
			data.put("txnTime" , simpleDateFormat.format(nowDate));
			// 交易金额，单位分
			data.put("txnAmt" ,MoneyConvertUtil.yuanToFen(ordert.getPayAmount()+""));
			// 交易币种
			data.put("currencyCode" , acc.getCurrencyCode());
			// 请求方保留域，透传字段，查询、通知、对账文件中均会原样出现
			data.put("reqReserved" , acc.getReqReserved());
			// 订单描述，可不上送，上送时控件中会显示该信息
			data.put("orderDesc" , acc.getOrderDesc());
			Map<String , String> submitFromData = signData(data , acc.getCertPath() , acc.getCertPwd());
			// 交易请求url 从配置文件读取
			String requestAppUrl = SDKConfig.getConfig().getAppRequestUrl();
			System.out.println("url:*******:"+requestAppUrl);
			System.out.println("submit:&&&&&&&&&:"+submitFromData);
			Map<String , String> resmap = submitUrl(submitFromData , requestAppUrl);
			// 处理响应报文,提取重要信息
			Iterator<String> iterator = resmap.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				String value = resmap.get(key);
				if(!data.containsKey(key)) {
					if(resultMap == null) {
						resultMap = new HashMap<String , Object>();
					}
					resultMap.put(key , value);
				}
			}
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
