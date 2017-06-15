package com.Vshop.front.module.unionpay.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UnionpayService {

	public String toUnionpay(String orderSn);
	
	public String Unionpayfront(HttpServletRequest request,HttpServletResponse rep);
	
	public String Unionpayback(HttpServletRequest request,HttpServletResponse resp);
	
}
