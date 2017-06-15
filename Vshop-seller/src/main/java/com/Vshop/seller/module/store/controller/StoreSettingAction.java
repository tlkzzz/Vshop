package com.Vshop.seller.module.store.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.Area;
import com.Vshop.core.entity.Classs;
import com.Vshop.core.entity.base.Store;
import com.Vshop.core.entity.base.Upload;
import com.Vshop.core.entity.vo.StoreVo;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.core.ossconfigure.OSSConfigure;
import com.Vshop.core.ossconfigure.OSSManageUtil;
import com.Vshop.seller.utils.CommonConstants;
import com.Vshop.seller.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.area.service.AreaService;
import com.Vshop.service.module.store.service.ClasssService;
import com.Vshop.service.module.store.service.StoreService;
import com.Vshop.service.utils.page.Pager;

/**
 * @项目名称：Vshop-seller
 * @类名称：StoreAction
 * @类描述： 店铺模块
 * @创建人：sangyuchen
 * @创建时间：2014年12月1日 下午10:39:53
 * @修改人：sangyuchen
 * @修改时间：2014年12月1日 下午10:39:53
 * @修改备注：
 */
@Controller
@RequestMapping("/storeSetting")
@Slf4j
public class StoreSettingAction {


    @Resource
    private StoreService storeService;
    @Resource
   	private AreaService areaService;
    @Resource
	private ClasssService classsService;
    String message = "success";

    /**
     * 店铺自分类页面跳转
     *
     * @param @return 设定文件
     * @return ModelAndView 返回类型
     * @throws
     * @Title: index
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    @RequestMapping(value = "")
    public ModelAndView index(@RequestParam(required = false, value = "result", defaultValue = "") String result) {
        ModelAndView model = new ModelAndView("/store/shop-set");
        int storeId = CacheUtils.getCacheUser().getStore().getStoreId();
        StoreVo storeVo = storeService.findVoById(storeId);
        model.addObject("storeVo", storeVo);
        if (StringUtils.isNotBlank(result)) {
            model.addObject("result", result);
        }
        return model;
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

    @RequestMapping("/edit")
    public String edit(@ModelAttribute("storeVo") StoreVo storeVo, RedirectAttributes redirectAttributes) {
        String result = "修改店铺设置成功";
        try {
            Store store = CacheUtils.getCacheUser().getStore();
            store.setStoreKeywords(storeVo.getStoreKeywords());
            store.setDescription(storeVo.getDescription());
            store.setStoreBanner(storeVo.getStoreBanner());
            store.setStoreLabel(storeVo.getStoreLabel());
            store.setStoreWw(storeVo.getStoreWw());
            store.setStoreQq(storeVo.getStoreQq());
            store.setStoreZy(storeVo.getStoreZy());
             //   store.setStoreDomainTimes(false);
            store.setStoreDomain(storeVo.getStoreDomain());
            storeService.updateStore(store);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("修改店铺设置失败", e.getMessage());
            result = "修改店铺设置成功";
        }
        redirectAttributes.addFlashAttribute("result", result);
        return "redirect:/storeSetting";
    }
    
    @RequestMapping("/storemanager")
    public String storemanager(Model model) {
        return "manager/storemanager";
    }
    
    /**
     * 步骤跳转
     * @return
     */
    @RequestMapping("/storeseting")
    public String storeseting(Model model){
        //个人信息修改菜单
    	Store storet = CacheUtils.getCacheUser().getStore();
    	Store store=null;
    	if(storet!=null){
    	   store=storeService.findById(storet.getStoreId());
    	}
    	Pager pager = new Pager();
        pager.setCondition(new Classs());
  		Classs storeclass=classsService.queryById(store.getScId()==null?0:store.getScId());
        model.addAttribute("storeclass", storeclass);//店铺等级
        List<Area> areas = areaService.queryAll();
        model.addAttribute("areas", areas);
    	model.addAttribute("store", store);
        return "manager/storeupdate";
    }
    
    /**
     * 幻灯片上传
     * @return
     */
    @RequestMapping("/storeside")
	public String storeside(Model model) {
		// 个人信息修改菜单
		Store storet = CacheUtils.getCacheUser().getStore();
		Store store = null;
		if (storet.getStoreId() != null) {
			store = storeService.findById(storet.getStoreId());
		}
		List<Upload> list = new ArrayList<Upload>();
		if (StringUtils.isNotEmpty(store.getStoreSlide())) {
			String[] slide = store.getStoreSlide().split(",");
			for (int i = 0; i < slide.length; i++) {
				Upload upload = new Upload();
				upload.setFileName(slide[i]);
				list.add(upload);
			}
			if (store.getStoreSlideUrl() != null && StringUtils.isNotEmpty(store.getStoreSlideUrl())) {
				String[] urls = store.getStoreSlideUrl().split(",");
				for (int j = 0; j < slide.length; j++) {
					Upload upload = list.get(j);
					upload.setImgUrl(urls[j]);
				}
			}
		}
		model.addAttribute("list",list);
		model.addAttribute("store", store);
		return "manager/store_side";
	}
    
    /**
	 * 加载地图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/loadmap")
	public ModelAndView loadmap() {
		try {
			ModelAndView model = new ModelAndView("/manager/load_baidumap");
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("导航管理加载失败！");
			throw new RuntimeException("导航管理加载失败!");
		}
	}
}
