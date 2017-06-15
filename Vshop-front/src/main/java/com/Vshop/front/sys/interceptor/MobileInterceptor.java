package com.Vshop.front.sys.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.Vshop.core.common.StringUtils;
import com.Vshop.core.common.UserAgentUtils;
import com.Vshop.front.utils.CommonConstants;
import com.Vshop.front.utils.sessionKey.CacheUtils;

/**
 * 手机端视图拦截器
 * @author Think
 * @version 2014-9-1
 */
public class MobileInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null){
			// 如果是手机或平板访问的话，则跳转到手机视图页面。
			if(UserAgentUtils.isMobileOrTablet(request) && !StringUtils.startsWithIgnoreCase(modelAndView.getViewName(), "redirect:")){
				//modelAndView.setViewName(CommonConstants.FRONT_SERVER + "/m/index/login");
				if(!CacheUtils.isLogin()){
					response.sendRedirect(CommonConstants.FRONT_SERVER + "/m/index/login");
				}
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception {
		
	}

}
