package com.Vshop.front.unionpay.sdksample.jiaofei;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.Vshop.front.unionpay.gwj.util.JsonUtil;
import com.Vshop.front.unionpay.sdk.SDKConfig;
import com.Vshop.front.unionpay.sdk.SDKConstants;
import com.Vshop.front.unionpay.sdk.SDKUtil;
import com.Vshop.front.unionpay.sdk.SecureUtil;

/**
 * 
 * 名称：全渠道缴费接口<br>
 * 功能：1.1.2　帐单支付<br>
 * 后台交易<br>
 * 版本： 5.0<br>
 * 日期： 2014-07<br>
 * 作者： 中国银联ACP团队<br>
 * 版权： 中国银联<br>
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考。<br>
 */
public class BillPay extends DemoBase{

	//组装请求报文
	private static Map<String, String> buildBillPayRequest() throws Exception {

		Map<String, String> postData = new HashMap<String, String>();
		postData.put("version", "5.0.0");// 固定填写 5.0.0
		postData.put("encoding", encoding);
		postData.put("signMethod", "01"); // 01-RSA, 02-MD5
		postData.put("txnType", "13"); //
		postData.put("txnSubType", "01"); // 01：便民缴费 02：网上缴税 03：信用卡还款 04：保险缴费
											// 05：现金缴费
		postData.put("bizType", "000601");
		postData.put("channelType", "07");
		postData.put("backUrl", "http://www.specialUrl.com");// 交易后台返回商户结果时使用，如上送，则发送商户后台交易结果通知
		postData.put("accessType", "0");
		postData.put("merId", "898340183980105");// 700000000000001、898340183980105
		postData.put("orderId", getOrderId());
		postData.put("txnTime", getCurrentTime());
		postData.put("currencyCode", "156");
		postData.put("accType", "01"); // 01：银行卡 02：存折 03：IC卡
		postData.put("accNo", "6222021001079775513");
		postData.put("txnAmt", "000000693940");
		
		/**
		 * <pre>
		 * bussCode 三元组：
		 * 参考获取账单业务要素（BIZ）说明
		 * 本例以I1_9800_0007作为示例
		 * </pre>
		 */
		postData.put("bussCode", "I1_9800_0007");// 业务代码

		/**
		 * <pre>
		 * 根据获取到的biz文件要求填写 
		 * 参看子域填法 ，json报文结构
		 * 详见1.2.4章节的说明
		 * 本字段需做BASE64处理
		 * </pre>
		 */
		postData.put("billQueryInfo", getBillQueryInfo(postData));
		/**
		 * <pre>
		 * 先查询后缴费的交易，需上送查询交易的
		 * 此处如为先查后缴类交易，上送1.2.1应答报文中返回的queryId
		 * </pre>
		 */
		postData.put("origQryId", 	"201502031409491000748");//原始交易流水号
		return postData;
	}

	/**
	 * <pre>
	 * 本方法只是示例代码,辅助商户理解billQueryInfo域的构造方法,商户可根据
	 * 业务要求并参照接口文档自行开发
	 * </pre>
	 * 
	 * @param reqMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	private static String getBillQueryInfo(Map<String, String> reqMap)
			throws UnsupportedEncodingException, IOException {
		Map<String, String> billQueryInfo = new HashMap<String, String>();
		// 1.组装billQueryInfo域所需的上送信息
		billQueryInfo.put("bill_id", "2015020310291310005281");//
		// 2.将billQueryInfo域转换成JSON格式
		String jsonStr = JsonUtil.toJson(billQueryInfo);
		// 3.对billQueryInfo域做BASE64编码
		String base64Str = new String(SecureUtil.base64Encode(jsonStr
				.getBytes(encoding)));
		return base64Str;
	}

	public static void main(String[] args) throws Exception {

		/**
		 * <pre>
		 * 参数初始化 
		 * 1.以java main方法运行时,必须每次都执行加载配置文件 
		 * 2.以java web应用程序运行时,配置文件初始化代码可以写在listener中
		 * </pre>
		 */
		SDKConfig.getConfig().loadPropertiesFromSrc();// 从classpath加载acp_sdk.properties文件

		/**
		 * 交易请求url 从配置文件读取
		 */
		String requestBackUrl = SDKConfig.getConfig().getBackRequestUrl();
		Map<String, String> resmap = submitDate(buildBillPayRequest(), requestBackUrl);

		if (!SDKUtil.isEmpty(resmap.get(SDKConstants.param_billDetailInfo))) {
			// 解析billDetailInfo,此处只是展示一下billDetailInfo域的格式
			String encodeValue = resmap.get(SDKConstants.param_billDetailInfo);
			String decodeValue = new String(SecureUtil.base64Decode(encodeValue
					.getBytes(encoding)), encoding);
			System.out.println("billDetailInfo=[" + decodeValue + "]");
		}

	}

}
