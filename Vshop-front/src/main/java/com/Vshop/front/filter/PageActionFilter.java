package com.Vshop.front.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PageActionFilter implements Filter {

	private Log log = LogFactory.getLog(PageActionFilter.class);

	public void destroy() {
	}

	public void doFilter(final ServletRequest req, final ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		// http信息封装类
		XSSHttpRequestWrapper xssRequest = new XSSHttpRequestWrapper(request);
		String pageLink = xssRequest.getServletPath();
		log.debug(pageLink);
		
		// XSS 攻击过滤
		if(pageLink!=null){
			// 对request信息进行封装并进行校验工作，若校验失败（含非法字符），根据配置信息进行日志记录和请求中断处理
			if (!pageLink.equals(XSSSecurityConfig.FILTER_ERROR_PAGE) && xssRequest.validateParameter((HttpServletResponse) response)) {
				if (XSSSecurityConfig.IS_LOG) {
					// 记录攻击访问日志yy
					// 可使用数据库、日志、文件等方式
					log.error(request.getRemoteAddr() + " " + pageLink + " " + xssRequest.getQueryString());
				}
				if (XSSSecurityConfig.IS_CHAIN) {
					request.getRequestDispatcher(XSSSecurityConfig.FILTER_ERROR_PAGE).forward(request, response);
					return;
				}
				chain.doFilter(xssRequest, response);
			}else{
				chain.doFilter(xssRequest, response);
			}
		}else{
			chain.doFilter(xssRequest, response);
		}
	
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		XSSSecurityManager.init();
	}
}