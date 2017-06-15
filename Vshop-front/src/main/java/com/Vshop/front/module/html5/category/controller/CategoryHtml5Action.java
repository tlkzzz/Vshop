package com.Vshop.front.module.html5.category.controller;



import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;




/**
 * 项目名称：Vshop-front
 * 类名称：CategoryHtml5Action
 * 类描述：
 * 创建人：zrh
 * 创建时间：2015年7月30日 上午11:29:35
 * 修改备注：
 */
@Controller
@RequestMapping("/m/category")
@Slf4j
public class CategoryHtml5Action {

    String message = "success";

    /**
     * 一级及二级分类页面跳转
     *
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     */
    @RequestMapping(value = "/category")
    public ModelAndView category() {
        try {
            ModelAndView model = new ModelAndView("/html5/category/goodsclass");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("卖家中心分类页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }   

    /**
     * 三级分类的页面跳转
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/categoryList")
    public ModelAndView categoryList(
    		@RequestParam(value="keyword",required=false) String keyword
			) {
        try {
            ModelAndView model = new ModelAndView("/html5/category/goodsclasslist");
            if(keyword != null && !keyword.equals("")){
            	model.addObject("keyword", keyword);
            }else{
            	model.addObject("keyword", "");
            }
            
           return model;
           
        } catch (Exception e) {
            e.printStackTrace();
            log.error("卖家中心分类列表页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }   
}