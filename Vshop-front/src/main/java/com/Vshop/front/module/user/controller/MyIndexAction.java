package com.Vshop.front.module.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by linjianmao on 15/6/9.
 */

@Controller
@RequestMapping("/user/myindex")
@Slf4j
public class MyIndexAction {

    @RequestMapping("/index")
    public ModelAndView index(){
        try {
            ModelAndView model = new ModelAndView("/user/myindex/index");

            model.addObject("cur", "myindex");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("卖家中心首页加载失败！");
            throw new RuntimeException("导航失败!");
        }

    }

}
