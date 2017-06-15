package com.Vshop.seller.module.store.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.Area;
import com.Vshop.core.entity.Classs;
import com.Vshop.core.entity.Type;
import com.Vshop.core.entity.base.Store;
import com.Vshop.core.entity.base.StoreExpand;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.core.ossconfigure.OSSConfigure;
import com.Vshop.core.ossconfigure.OSSManageUtil;
import com.Vshop.seller.utils.CommonConstants;
import com.Vshop.seller.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.area.service.AreaService;
import com.Vshop.service.module.store.service.ClasssService;
import com.Vshop.service.module.store.service.StoreExpandService;
import com.Vshop.service.module.store.service.StoreService;
import com.Vshop.service.module.store.service.TypeService;


@Slf4j
@Controller
@RequestMapping("/joinIn")
public class StoreJoinInAction {

    @Resource
    private StoreService storeService;
    @Resource
	private AreaService areaService;
    @Resource
    private ClasssService classService;
    @Resource
    private TypeService typeService;
    @Resource
	private StoreExpandService storeExpandService;
    /**
     * 步骤1
     * @return
     */
    @RequestMapping("/step1")
    public String step1(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated() || subject.isRemembered()){
            Store store = storeService.findByMemberId(CacheUtils.getCacheUser().getMember().getMemberId());
            if (store  == null) {
                return "store/joinIn";
            } else {
                return  "redirect:/";
            }

        }else{
            return "redirect:/login";
        }

    }

    /**
     * 步骤2
     * @return
     */
    @RequestMapping("/step2")
    public String step2(Model model, @RequestParam(required=false, value="storeId",defaultValue="0")String storeId){
    	//一级地区加载
        List<Area> areas = areaService.queryAll();
        List<Classs> list = classService.findList(0);
        for(Classs gc : list){
            gc.setClassList(classService.findList(gc.getId()));
        }
        
        List<Type> typeList = Lists.newArrayList();
        List<Type> findAllList = typeService.findAllList();
        for(Type t : findAllList){
        	if(t.getParentId().equals(0)){
        		typeList.add(t);
        	}
        }
        
        Store store = new Store();
        StoreExpand storeExpand = new StoreExpand();
        if(Long.valueOf(storeId).longValue() != 0L){
        	store = storeService.findById(Integer.valueOf(storeId));
        	storeExpand = storeExpandService.findByStoreId(Integer.valueOf(storeId));
    	}
        
        model.addAllAttributes(ImmutableMap.of("classList1", list, "typeList1", typeList, "areas", areas, "typeAll", JSON.toJSONString(findAllList)));
        model.addAllAttributes(ImmutableMap.of("store", store, "storeExpand", storeExpand));
        // 尾部菜单
        return "store/joinInStep2";
    }

   
   
  
    @RequestMapping("/JoinInSuccess")
    public String JoinInSuccess(Model model){
        model.addAttribute("success","success");
        return "store/joinInSuccess";
    }

    @RequestMapping("/step3")
    public String joinInStep3(Model model) {
        return "store/joinInStep3";
    }
    
    @RequestMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam MultipartFile[] files,
                             HttpServletRequest request, HttpServletResponse response) throws IOException {
        //可以在上传文件的同时接收其它参数
        Map<String, Object> map = Maps.newHashMap();
        try {
            
            OSSConfigure ossConfigure=new OSSConfigure();
 			 map =OSSManageUtil.uploadFile(ossConfigure, files, Constants.LOGO_UPLOAD_URL,"images",request);
       
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传文件失败", e.toString());
        }
        String json = JsonUtils.toJsonStr(map);
        response.setContentType("text/html");
        response.getWriter().write(json);
        return null;
    }
    
}
