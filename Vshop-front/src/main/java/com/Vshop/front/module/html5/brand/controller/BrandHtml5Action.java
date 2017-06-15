package com.Vshop.front.module.html5.brand.controller;



import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 项目名称：Vshop-front
 * 类名称：CartHtml5Action
 * 类描述：
 * 创建人：zrh
 * 创建时间：2015年8月05日 上午11:29:35
 * 修改备注：
 */
@Controller
@RequestMapping("/m/brand")

@Slf4j
public class BrandHtml5Action {

    String message = "success";

    /**
     * 品牌街页面跳转
     *
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     */
    @RequestMapping(value = "/brand")
    public ModelAndView brand() {
        try {
            ModelAndView model = new ModelAndView("/html5/brand/brand");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("品牌街页面加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }     
}