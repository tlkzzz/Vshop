package com.Vshop.front.module.alipay.service;

import javax.servlet.http.HttpServletRequest;

public interface AlipayService {

	public String toPay(String orderSn);
	
	public String payfront(HttpServletRequest request);
	
	public String payback(HttpServletRequest request);
	
}
