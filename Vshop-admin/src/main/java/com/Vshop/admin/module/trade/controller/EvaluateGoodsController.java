package com.Vshop.admin.module.trade.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.base.EvaluateGoods;
import com.Vshop.service.module.trade.service.EvaluateGoodsService;
import com.Vshop.service.utils.page.Pager;

/**
 * @author llf
 * @Package com.Vshop.admin.module.trade.controller
 * @Description:
 * @date 2014/11/12 13:13
 */

@Slf4j
@Controller
@RequestMapping("/trade/evalGoods")
public class EvaluateGoodsController {

    @Resource
    private EvaluateGoodsService evaluateGoodsService;

    @RequestMapping("/list")
    public String list(Model model,@ModelAttribute EvaluateGoods evaluateGoods,
                       @RequestParam(required=false, value="div",defaultValue="")String div,
                       @RequestParam(required=false, value="pageNo",defaultValue="") String pageNoStr){

        Pager pager = new Pager();
        pager.setCondition(evaluateGoods);
        if(!StringUtils.isEmpty(pageNoStr)){
            pager.setPageNo(Integer.parseInt(pageNoStr));
        }
        pager.setCondition(evaluateGoods);
        List<EvaluateGoods> results = evaluateGoodsService.findPageList(pager);//结果集
        pager.setResult(results);
        model.addAttribute("pager",pager);
        model.addAttribute("geval",evaluateGoods);
        return "trade/evalGoods/list";
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam int id,Model model,HttpServletRequest request){

        String referer = request.getHeader("Referer");
        model.addAttribute("referer", referer);
        if(id == 0){
            model.addAttribute("msg", "删除失败");
        }else{
            evaluateGoodsService.delete(id);
            model.addAttribute("msg", "删除成功");
        }
        return Constants.MSG_URL;
    }
}
