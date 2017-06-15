package com.Vshop.front.module.alipayinterna.service;

import javax.servlet.http.HttpServletRequest;

public interface AlipayInternaService {

	public String toPay(String orderSn);
	
	public String payfront(HttpServletRequest request);
	
	public String payback(HttpServletRequest request);
	
}
