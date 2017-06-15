package com.Vshop.front.module.html5.article.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * h5文章
 * @author kviuff
 */
@RequestMapping("/article/Api")
@Controller
@Slf4j
public class ArticleHtml5Action {
	
	/**
     * 文章详细页面跳转
     *
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     */
    @RequestMapping(value = "/content")
    public ModelAndView content() {
        try {
            ModelAndView model = new ModelAndView("/html5/article/content");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("文章详细页加载失败！");
            throw new RuntimeException("文章详细页加载失败!");
        }
    }   
}
