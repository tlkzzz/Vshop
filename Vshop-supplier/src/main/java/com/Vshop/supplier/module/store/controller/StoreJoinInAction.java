package com.Vshop.supplier.module.store.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jersey.repackaged.com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections.MapUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.base.Dictionary;
import com.Vshop.core.entity.base.Supplier;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.core.ossconfigure.OSSConfigure;
import com.Vshop.core.ossconfigure.OSSManageUtil;
import com.Vshop.supplier.utils.CommonConstants;
import com.Vshop.supplier.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.dictionary.service.DictionaryService;
import com.Vshop.service.module.goods.service.BrandService;
import com.Vshop.service.module.goods.service.SupplierService;


@Slf4j
@Controller
@RequestMapping("/joinIn")
public class StoreJoinInAction {
    @Resource
    private SupplierService supplierService;
	
	@Resource
	private BrandService brandService;
	
	@Resource
	private DictionaryService dictionaryService;
	
    /**
     * 步骤1
     * @return
     */
    @RequestMapping("/step1")
    public String step1(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated() || subject.isRemembered()){
        	Supplier supplier = supplierService.findByMemberId(CacheUtils.getCacheUser().getMember().getMemberId());
            if (supplier  == null) {
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
    public String step2(Model model, @RequestParam(required=false, value="id",defaultValue="0")String id){
    	model.addAllAttributes(getDictionaryList());
    	
    	Supplier supplier = new Supplier();
    	List<Brand> brands = Lists.newArrayList();
    	if(Long.valueOf(id).longValue() != 0L){
    		supplier = supplierService.findById(Long.valueOf(id));
    		brands = supplierService.findBrandListBySupplier(ImmutableMap.of("id", Integer.valueOf(id)));
    	}
    	model.addAllAttributes(ImmutableMap.of("supplier", supplier, "brands", brands));
        
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
    public String fileUpload(@RequestParam MultipartFile[] files, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //可以在上传文件的同时接收其它参数
        Map<String, Object> map = Maps.newHashMap();
        try {
            OSSConfigure ossConfigure=new OSSConfigure();
            map =OSSManageUtil.uploadFile(ossConfigure, files, Constants.SUPPLIER_UPLOAD_URL,"images",request);
        
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传文件失败", e.toString());
        }
        String json = JsonUtils.toJsonStr(map);
        response.setContentType("text/html");
        response.getWriter().write(json);
        return null;
    }
    
    @RequestMapping("/brand/bl")
	public @ResponseBody String getBrandList(@RequestParam(required=false, value="supplierId",defaultValue="0") Integer supplierId){
    	List<Brand> brands = supplierService.findBrandListBySupplier(ImmutableMap.of("id", Integer.valueOf(supplierId)));
    	List<Brand> brandList = brandService.findList();
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> brandMaps = (List<Map<String, Object>>)JSON.toJSON(brandList);
		for(Map<String, Object> brand : brandMaps){
			for(Brand _b : brands){
				if(MapUtils.getInteger(brand, "brandId").equals(_b.getBrandId())){
					brand.put("check", true);
				}
			}
		}
		return JSON.toJSONString(brandMaps);
	}
    
    private Map<String, List<Dictionary>> getDictionaryList(){
    	List<Dictionary> entTypes =  dictionaryService.findDictionaryByCode("ent_type");
        List<Dictionary> busTypes =  dictionaryService.findDictionaryByCode("bus_type");
        List<Dictionary> shopTypes =  dictionaryService.findDictionaryByCode("shop_type");
        List<Dictionary> marketTypes =  dictionaryService.findDictionaryByCode("market_type");
        List<Dictionary> accountMethods =  dictionaryService.findDictionaryByCode("account_method");
        return ImmutableMap.of("entTypes", entTypes, "busTypes", busTypes, "shopTypes", shopTypes, "marketTypes", marketTypes, "accountMethods", accountMethods);
    }
    
}
