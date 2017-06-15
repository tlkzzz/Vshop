package com.Vshop.front.pay.h5.aplipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		// 合作身份者ID，以2088开头由16位纯数字组成的字符串
		public static String partner = "2088911754957710";
		
		// 收款支付宝账号，以2088开头由16位纯数字组成的字符串
		public static String seller_id = "huanglei@Vshop.com";
		// 商户的私钥
		public static String private_key = "MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBALSq0wBt4qN8hrwLhuPL3IQRLZ3vOzh10+FHm5Iqe/DEZ5WGqoQnw0JbCEj5QZcpxleQwXGYpuLu2Qzt3F/uexjii44PYlagJ5lnnM7n4RKidm934ONusoiPXjYPVIYaHMCfkTHPdYznWxk5anJ1UTmpNwK7W4MnlyK0iQQVcEx5AgMBAAECgYEAqS1hQoCjfsXoAQSmPn+ysfTIC6FuihND4ivE0R15S98OxGUDHZeCb4uA+Zx2wA99TJgfnLj+ZXiVxVajSy2tE/AZFRC1DOICpkRyUw2Z+PppEn1pEiaaYsfQBpxorb+5TnUGZnp17NLd0dvHrbivhuIW/CCO9y8DaMRjgwYXPYUCQQDidHPDFTMvQgzb9F43hnCzTIrf+hFpZvkKa0b7RQkPHK743+72VDZKxU3n1KZCALjkrpOKWb/IB0MOL6qyCPDjAkEAzD0TOV3QRymznHIb0OzGJkTOy5rZ4ZF8VGTGtHP2VNof1TNwSevgF5y0DlSHSkX2pJz/tdj2gvrJHLJ2gtfX8wJBAJdlsatANisnJn+7PW4z1I8a4lHh1tg3/tHnbNo6ilbvH0/GLvXC0VVl2bLfqZPA9dv1lzBM1nn92OfYGG/UqG8CQQCNCP/ldWsGOT3WotWiiF6svocvAlfC9HwuqwisRAeuwJGK4GKCKWgt5gBQhP6Hw5h2RBQwtwpDmHqYXoSIBhURAkEAqRIkjocTYoEiObFrBEedlRZrCa2FfOTetOxOvMa1X5SVtb5oFfiUCJ5PfGRls8tY13dd6LngWDf0Y6PP7Cxxuw==";
		// 支付宝的公钥，无需修改该值
		public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

		//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
		

		// 调试用，创建TXT日志文件夹路径
		public static String log_path = "E:\\";

		// 字符编码格式 目前支持 gbk 或 utf-8
		public static String input_charset = "utf-8";
		
		// 签名方式 不需修改
		public static String sign_type = "RSA";


}
