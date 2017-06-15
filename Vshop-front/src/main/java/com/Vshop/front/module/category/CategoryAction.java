/**
 * 
 */
package com.Vshop.front.module.category;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Title: CategoryAction.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月16日
 * @version 1.0
 */
@Controller
@RequestMapping("/all")
public class CategoryAction {
	
	
	@RequestMapping("/class")
	public String category(Model model){
		
		return "/category/all_class";
	}
	
	@RequestMapping("/brand")
	public String brand(Model model){
		
		return "/category/all_brand";
	}

}
