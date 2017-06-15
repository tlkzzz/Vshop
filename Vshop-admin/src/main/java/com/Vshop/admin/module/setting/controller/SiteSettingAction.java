package com.Vshop.admin.module.setting.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

//import com.Vshop.core.base.BaseController;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.Vshop.core.common.CommonConstants;
import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.Site;
import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.ossconfigure.OSSConfigure;
import com.Vshop.core.ossconfigure.OSSManageUtil;
import com.Vshop.service.module.setting.service.SettingService;
import com.Vshop.service.module.setting.service.SiteService;

/**
 * 系统设置
 * @author zhaorh
 */
@Controller
@RequestMapping("/setting/site")
@Slf4j
public class SiteSettingAction{
	@Resource
	private SiteService settingService;
    /**
     * 跳转admin系统设置的站点设置
     * @param model
     * @return
     */
    @RequestMapping("/siteSetting")
    public ModelAndView siteSetting(){
    	ModelAndView model = new ModelAndView("setting/points/site");
		Site site = settingService.findById();
		model.addObject("site", site);
		return model;
    }
    
    /**
     * admin系统设置的站点设置保存
     * @param model
     * @return
     * @throws Exception 
     */
    @RequestMapping("/siteSave")
    public String  siteSave(@RequestParam("logo1") MultipartFile[] logo1,
    		@RequestParam("logo2") MultipartFile[] logo2,
//    		@RequestParam("logo5") MultipartFile[] logo5,@RequestParam("logo6") MultipartFile[] logo6,
    						HttpServletRequest request,Model model, @ModelAttribute("site") Site site) throws Exception{
//    	Enumeration<String> names = request.getParameterNames();
//		Map<String,String> siteMap = new HashMap<String, String>();
    
		try {
//			Map<String,Object> map = com.vixuan.core.common.FileUtils.fileUpload(logo,  CommonConstants.FILE_BASEPATH, Constants.SITE_LOGO_URL, request,"images",1);
			 OSSConfigure ossConfigure=new OSSConfigure();
			 if(logo1[0].getSize()>0){
				 Map<String,Object>	 map =OSSManageUtil.uploadFile(ossConfigure, logo1, Constants.SITE_LOGO_URL,"images",request);
//					siteMap.put("logo", (String)map.get("result")); 
					site.setSiteLogo1((String)map.get("result"));
			 }
			 if(logo2[0].getSize()>0){
				 Map<String,Object>	 map =OSSManageUtil.uploadFile(ossConfigure, logo2, Constants.SITE_LOGO_URL,"images",request);
//					siteMap.put("adminlogo", (String)map.get("result"));
					site.setSiteLogo2((String)map.get("result"));
			 }
//			 if(logo3[0].getSize()>0){
//				 Map<String,Object>	 map =OSSManageUtil.uploadFile(ossConfigure, logo3, Constants.SITE_LOGO_URL,"images",request);
////					siteMap.put("logo", (String)map.get("result")); 
//					site.setSiteLogo3((String)map.get("result"));
//			 }
//			 if(logo4[0].getSize()>0){
//				 Map<String,Object>	 map =OSSManageUtil.uploadFile(ossConfigure, logo4, Constants.SITE_LOGO_URL,"images",request);
////					siteMap.put("adminlogo", (String)map.get("result"));
//					site.setSiteLogo4((String)map.get("result"));
//			 }
//			 if(logo5[0].getSize()>0){
//				 Map<String,Object>	 map =OSSManageUtil.uploadFile(ossConfigure, logo5, Constants.SITE_LOGO_URL,"images",request);
////					siteMap.put("logo", (String)map.get("result")); 
//					site.setSiteLogo4((String)map.get("result"));
//			 }
//			 if(logo6[0].getSize()>0){
//				 Map<String,Object>	 map =OSSManageUtil.uploadFile(ossConfigure, logo6, Constants.SITE_LOGO_URL,"images",request);
////					siteMap.put("adminlogo", (String)map.get("result")); 
//					site.setSiteLogo6((String)map.get("result"));
//			 }
//			
//			while(names.hasMoreElements()){
//		    	String name= names.nextElement();
//	    		String paramValue = request.getParameter(name);
//	    		siteMap.put(name, paramValue);	
//		    	
//			}
			 
			
				settingService.update(site);
//			}else{
//				settingService.save(site);
//			}
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		String referer = request.getHeader("Referer");
		model.addAttribute("referer", referer);
		model.addAttribute("msg", "新增成功");
     	return Constants.MSG_URL;
	}
	
}
