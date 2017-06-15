/**
 * 
 */
package com.Vshop.front.module.points.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Title: PointsAction.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年8月28日
 * @version 1.0
 */

@Controller
@RequestMapping("/points")
public class PointsAction {
	
	@RequestMapping("/index")
	public String index(Model model){
		
		return "/points/index";
	}
	
	@RequestMapping("/more")
	public String more(Model model){
		
		return "/points/pointslist";
	}

}
