package com.Vshop.admin.module.goods.controller;


import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import static com.Vshop.core.common.FileUtils.fileUpload;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.base.Dictionary;
import com.Vshop.core.entity.base.Supplier;
import com.Vshop.core.entity.vo.GoodsTradeVo;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.service.module.dictionary.service.DictionaryService;
import com.Vshop.service.module.goods.service.BrandService;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.module.goods.service.SupplierService;
import com.Vshop.service.utils.CommonConstants;
import com.Vshop.service.utils.page.Pager;

@Slf4j
@Controller
@RequestMapping("/goods/supplier")
public class SupplierAction {
	@Resource
    private SupplierService supplierService;
	
	@Resource
	private BrandService brandService;
	
	@Resource
	private DictionaryService dictionaryService;
	
	@Resource
	private GoodsService goodsService;

	@RequestMapping("/list")
    public String List(@ModelAttribute Supplier supplier, Model model, @RequestParam(required=false, value="pageNo",defaultValue="")String pageNo){
        Pager pager = new Pager();
        if(StringUtils.isNotBlank(pageNo)){
            pager.setPageNo(Integer.parseInt(pageNo));
        }
        pager.setCondition(supplier);
        List<Supplier> results = supplierService.findPageList(pager);
        pager.setResult(results);
        model.addAllAttributes(ImmutableMap.of("pager", pager, "supplier", supplier)).addAllAttributes(getDictionaryList());
        return "goods/supplier/list";
    }
	
	@RequestMapping(value = "/auditList")
	public String auditList(@ModelAttribute Supplier supplier, Model model, @RequestParam(required=false, value="pageNo",defaultValue="")String pageNo){
		Pager pager = new Pager();
		if(StringUtils.isNotBlank(pageNo)){
			pager.setPageNo(Integer.parseInt(pageNo));
		}
		supplier.setSupplierState(2);
		pager.setCondition(supplier);
		List<Supplier> results = supplierService.findPageList(pager);
        pager.setResult(results);
        model.addAllAttributes(ImmutableMap.of("pager", pager, "supplier", supplier)).addAllAttributes(getDictionaryList());
        
        return "goods/supplier/audit_list";
	}
	
	@RequestMapping("/forward")
    public String forward(Model model,@RequestParam int id){
        model.addAllAttributes(getDictionaryList());
        if(id == 0){
            return "goods/supplier/save";
        }else{
            //查询供应商
        	Supplier supplier = supplierService.findById(id);
        	List<Brand> brands = supplierService.findBrandListBySupplier(ImmutableMap.of("id", id));
            model.addAllAttributes(ImmutableMap.of("supplier", supplier, "brands", brands));
            return "goods/supplier/edit";
        }
    }
	
	@RequestMapping(value = "/findById")
	public String findById(Model model, @RequestParam(required=false, value="id",defaultValue="")Integer id){
		model.addAllAttributes(getDictionaryList());
		Supplier supplier = supplierService.findById(id);
		List<Brand> brands = supplierService.findBrandListBySupplier(ImmutableMap.of("id", id));
        model.addAllAttributes(ImmutableMap.of("supplier", supplier, "brands", brands));
		return "goods/supplier/detail";
	}
	
	@RequestMapping("/verify")
    public String verify(@ModelAttribute Supplier supplier ,Model model,HttpServletRequest request){
    	String referer = request.getHeader("Referer");
    	supplierService.update(supplier);
    	model.addAttribute("referer", referer);
        return Constants.MSG_URL;
    }
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestParam("imageFile") MultipartFile[] image,
    		@ModelAttribute("supplier") Supplier supplier,@RequestParam("brandId") String brandId, HttpServletRequest request, Model model){
		//图片上传及保存
		imagesHandler(supplier, request, image);
		supplierService.save(supplier);
		saveSupplierBrand(supplier, brandId);
		
        return confirmView(model, "新增成功", request);
    }
	
	@RequestMapping("/edit")
    public String edit(@RequestParam("imageFile") MultipartFile[] image,
    		@ModelAttribute("supplier") Supplier supplier, @RequestParam("brandId") String brandId, HttpServletRequest request, Model model){
		//图片上传及保存
		imagesHandler(supplier, request, image);
		supplierService.deleteSupplierBrandBySupplierId(ImmutableMap.of("id", supplier.getId()));
		this.saveSupplierBrand(supplier, brandId);
		supplierService.update(supplier);
        return confirmView(model, "修改成功", request);
    }
	
	@RequestMapping("/delete")
    public String  delete(@RequestParam int[] ids, HttpServletRequest request, Model model){
		for(int id : ids){
			supplierService.deleteSupplierBrandBySupplierId(ImmutableMap.of("id", id));
        	supplierService.delete(id);
        }
        return confirmView(model, "删除成功", request);
    }
	
	@RequestMapping("/bl")
	public @ResponseBody String getBrandList(@RequestParam("supplierId") Integer supplierId){
		List<Brand> brands = supplierService.findBrandListBySupplier(ImmutableMap.of("id", supplierId));
		List<Map<String, ? extends Object>> brandForSupplier = Lists.newArrayList();
		for(Brand brand : brands){
			GoodsTradeVo goodsTradeVo = new GoodsTradeVo();
			goodsTradeVo.setBrandId(brand.getBrandId());
			Integer count = goodsService.findTradeGoodcount(goodsTradeVo);
			brandForSupplier.add(ImmutableMap.of("id", brand.getBrandId(), "count", count));
		}
		
		List<Brand> brandList = brandService.findList();
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> brandMaps = (List<Map<String, Object>>)JSON.toJSON(brandList);
		for(Map<String, Object> brand : brandMaps){
			for(Map<String, ? extends Object> _b : brandForSupplier){
				if(MapUtils.getInteger(brand, "brandId").equals(MapUtils.getInteger(_b, "id"))){
					brand.put("check", true);
					brand.putAll(_b);
				}
			}
		}
		return JSON.toJSONString(brandMaps);
	}
	
    @RequestMapping("/validate")
    public @ResponseBody String validateForm(@ModelAttribute Supplier supplier){
        //校验重复
        if(supplierService.countSupplier(supplier) > 0){
            return "false";
        }else{
            return "true";
        }
    }
    
    private String confirmView(Model model, String msg, HttpServletRequest request){
    	String referer = request.getHeader("Referer");
        model.addAttribute("referer", referer);
        model.addAttribute("msg", msg);
        return Constants.MSG_URL;
    }
    
    private void saveSupplierBrand(Supplier supplier, String bIdText) {
    	if(StringUtils.isNotBlank(bIdText) && supplier.getId() != null){
    		Integer supplierId = supplier.getId();
    		List<String> brandIds = Arrays.asList(bIdText.split(","));
    		List<Map<String, Integer>> params = Lists.newArrayList();
    		for(String sid : brandIds){
    			params.add(ImmutableMap.of("supplierId", supplierId, "brandId", Integer.valueOf(sid)));
    		}
    		supplierService.saveSupplierBrand(params);
    	}
	}
    
    private Map<String, List<Dictionary>> getDictionaryList(){
    	List<Dictionary> entTypes =  dictionaryService.findDictionaryByCode("ent_type");
        List<Dictionary> busTypes =  dictionaryService.findDictionaryByCode("bus_type");
        List<Dictionary> shopTypes =  dictionaryService.findDictionaryByCode("shop_type");
        List<Dictionary> marketTypes =  dictionaryService.findDictionaryByCode("market_type");
        List<Dictionary> accountMethods =  dictionaryService.findDictionaryByCode("account_method");
        return ImmutableMap.of("entTypes", entTypes, "busTypes", busTypes, "shopTypes", shopTypes, "marketTypes", marketTypes, "accountMethods", accountMethods);
    }
    
    private void imagesHandler(Supplier supplier, HttpServletRequest request, MultipartFile[] image){
    	try {
			Map<String, MultipartFile[]> images = Maps.newHashMap();
			for(int i = 0; i < image.length; i++){
				MultipartFile[] _M = {image[i]};
				switch(i){
	            	case 0:
	            		images.put("BusLicenPurl", _M);
	            		break;
	            	case 1:
	            		images.put("DishuiRegistPurl", _M);
	            		break;
	            	case 2:
	            		images.put("GuoshuiRegistPurl", _M);
	            		break;
	            	case 3:
	            		images.put("ZzshuiPurl", _M);
	            		break;
	            	case 4:
	            		images.put("LegalerPurl", _M);
	            		break;
	            	default:
	            		break;
            	}
			}
			Iterator<String> keys = images.keySet().iterator();
			while(keys.hasNext()){
				String key = keys.next();
				Map<String,Object> map = fileUpload(images.get(key), CommonConstants.FILE_BASEPATH, Constants.SUPPLIER_UPLOAD_URL, request, "images", 1);
				
				
				
				String result = MapUtils.getString(map, "result");
				if(StringUtils.isNotBlank(result)){
					supplier.getClass().getMethod(String.format("set%s", key), String.class).invoke(supplier, result);
				}
			}
        } catch (Exception e) {
           log.error("上传文件失败", e.toString());
        }
    }
    
    
    

    @RequestMapping(value = "/findSupplierListByBrandId", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, String> findSupplierListByBrandId(@RequestParam(value = "id") String brandId) throws JsonGenerationException, JsonMappingException,
            Exception {
        Map<String, String> map = Maps.newHashMap();
        
        String json = "null";
        if(StringUtils.isNotEmpty(brandId)){
        	
        	Brand cond = new Brand();
        	cond.setBrandId(Integer.valueOf(brandId));
        	
        	List<Supplier> suppliers = supplierService.findSupplierListByBrand(cond);
        	if (suppliers != null && suppliers.size() > 0) {
        		json = JsonUtils.toJsonStr(suppliers);
        	}
        	
        }
        map.put("result", json);
        map.put("success", "true");
        return map;
    }
    
}
