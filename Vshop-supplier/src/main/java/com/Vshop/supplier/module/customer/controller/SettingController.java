package com.Vshop.supplier.module.customer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.Vshop.core.entity.CustomerFace;
import com.Vshop.core.entity.base.Store;
import com.Vshop.core.entity.vo.AfterVo;
import com.Vshop.core.entity.vo.PreVo;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.supplier.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.store.service.StoreService;

/**
 * @author llf
 * @Package com.Vshop.seller.module.customer.controller
 * @Description:
 * @date 2015/3/6 16:53
 */

@Slf4j
@Controller
@RequestMapping("/cusSetting")
public class SettingController {

    @Resource
    private StoreService storeService;
    /**
     * 首页
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model){

        Store store = storeService.findById(CacheUtils.getCacheUser().getStore().getStoreId());
        CustomerFace face = new CustomerFace();
        List<PreVo> preList = JsonUtils.fromJson(store.getStorePresales(), ArrayList.class);
        List<AfterVo> afterList = JsonUtils.fromJson(store.getStoreAftersales(),ArrayList.class);
        face.setPreList(preList);
        face.setAfterList(afterList);
        face.setStoreWorkingtime(store.getStoreWorkingtime());
        model.addAttribute("face",face);
        return "customer/cus-set";
    }

    /**
     * 保存客户配置
     * @return
     */
    @RequestMapping("/saveSetting")
    public @ResponseBody Map<String,Object> saveSetting(@ModelAttribute CustomerFace face){

        Map<String,Object> map = Maps.newHashMap();
        try{
            String preSetting = JsonUtils.toJsonStr(face.getPreList());
            String afterSetting = JsonUtils.toJsonStr(face.getAfterList());
            Store store = new Store();
            store.setStoreId(CacheUtils.getCacheUser().getStore().getStoreId());
            store.setStorePresales(preSetting);
            store.setStoreAftersales(afterSetting);
            store.setStoreWorkingtime(face.getStoreWorkingtime());
            storeService.updateStoreCus(store);
            map.put("success",true);
            map.put("msg","保存成功");
        }catch (Exception e){
            log.error(e.toString());
            map.put("success",false);
            map.put("msg","保存失败");
        }
        return map;
    }

}
