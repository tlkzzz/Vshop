package com.Vshop.admin.module.store.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.base.StoreGrade;
import com.Vshop.service.module.store.service.StoreGradeService;
import com.Vshop.service.utils.page.Pager;

/**
 * Created by rabook on 2014/12/12.
 */
@Slf4j
@Controller
@RequestMapping("/store/grade")
public class StoreGradeAction {

    @Resource
    private StoreGradeService storeGradeService;
    /**
     * 列表
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model,@RequestParam(required=false, value="sgName",defaultValue="") String sgName){

        Pager pager = new Pager();
        StoreGrade storeGrade = new StoreGrade();
        storeGrade.setSgName(sgName);
        pager.setCondition(storeGrade);
        model.addAttribute("list",storeGradeService.queryStoreGradeList(pager));
        model.addAttribute("sgName",sgName);
        return "store/grade/list";
    }

    /**
     * 跳转
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/forward")
    public String forward(@RequestParam long id,Model model){
        model.addAttribute("grade",storeGradeService.queryById(id));
        if(id == 0){
            return "store/grade/save";
        }else{
            return "store/grade/edit";
        }
    }

    /**
     * 校验重复
     * @return
     */
    @RequestMapping("/validate")
    public @ResponseBody boolean validate(@ModelAttribute StoreGrade storeGrade){

        if(storeGradeService.queryCount(storeGrade) > 0){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 编辑或新增
     * @param storeGrade
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String edit(@ModelAttribute StoreGrade storeGrade,HttpServletRequest request,Model model){

        String referer = request.getHeader("Referer");
        model.addAttribute("referer", referer);
        if(storeGrade.getSgId() == null){
            //新增
            storeGrade.setSgSpaceLimit(100);
            //默认需要审核
            storeGrade.setSgConfirm(1);
            storeGradeService.save(storeGrade);
            model.addAttribute("msg", "新增成功");
        }else{
        	storeGrade.setUpdateTime(new java.util.Date().getTime());
            //修改
            storeGradeService.update(storeGrade);
            model.addAttribute("msg", "修改成功");
        }
        return Constants.MSG_URL;
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam long[] ids,HttpServletRequest request,Model model){

        String referer = request.getHeader("Referer");
        for(long id : ids){
            storeGradeService.delete(id);
        }
        model.addAttribute("referer", referer);
        model.addAttribute("msg", "删除成功");
        return Constants.MSG_URL;
    }
}
