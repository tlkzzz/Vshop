/**
 * 
 */
package com.Vshop.admin.module.dev;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>Title: CacheSetting.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年9月28日
 * @version 1.0
 */
@Controller
@Slf4j
@RequestMapping("/dev/cache")
public class CacheSettingAction {
	
	@RequestMapping("/setting")
	public ModelAndView setting(){
		ModelAndView model = new ModelAndView("/dev/cache/cachesetting");
		return model;
	}

}
