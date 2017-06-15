package com.Vshop.supplier.module.store.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.Vshop.core.common.Constants;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.core.ossconfigure.OSSConfigure;
import com.Vshop.core.ossconfigure.OSSManageUtil;
import com.Vshop.core.entity.GoodsClass;
import com.Vshop.core.entity.base.Brand;
import com.Vshop.supplier.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.goods.service.BrandService;
import com.Vshop.service.module.goods.service.GoodsClassService;
import com.Vshop.service.utils.CommonConstants;
import com.Vshop.service.utils.page.Pager;

/**
 * @项目名称：Vshop-supplier
 * @类名称：StoreBrandAction
 * @类描述：
 * @修改备注：
 */
@Controller
@RequestMapping("/storeBrand")
@Slf4j
public class StoreBrandAction {
    String message = "success";

    @Resource
    private BrandService brandService;

    @Autowired
    private GoodsClassService goodsClassService;

    @RequestMapping("/index")
    public String index() {
        try {
            return "/store/store-brand-index";
        } catch (Exception e) {
            log.error("导航失败!", e);
            throw new RuntimeException("导航失败!");
        }
    }

    /**
     * @param @param  model
     * @param @param  div
     * @param @param  pageNoStr
     * @param @param  brandName
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: list
     * @Description: 查询品牌列表
     */
    @RequestMapping("/list")
    public  ModelAndView list(
    		@ModelAttribute Brand brand,
            @RequestParam(required = false, value = "div", defaultValue = "") String div,
            @RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNoStr) {
        try {
        	ModelAndView model = new ModelAndView("/store/store-brand-list");
            //Brand brand = new Brand();
            Pager pager = new Pager();
            // 只查询自己商户的品牌
            brand.setStoreId(CacheUtils.getCacheUser().getStore().getStoreId());
            if (StringUtils.isNotBlank(pageNoStr)) {
                pager.setPageNo(Integer.parseInt(pageNoStr));
            }
            pager.setCondition(brand);
            // 页面查询条件品牌列表
            List<Brand> brandList = brandService.findPageList(pager);
            model.addObject("brandList", brandList);// 品牌列表
            model.addObject("pageNo", pager.getPageNo());// 当前页
            model.addObject("pageSize", pager.getPageSize());// 每页显示条数
            model.addObject("recordCount", pager.getTotalRows());// 总数
        	model.addObject("pager", pager);
            model.addObject("div", div);// 显示的DIV数据区域
            model.addObject("toUrl", "/storeBrand/list");// 跳转URL
            model.addObject("brand", brand);
            // 转发请求到FTL页面
            return model;
        } catch (Exception e) {
            log.error("导航失败!", e);
            throw new RuntimeException("导航失败!");
        }
    }

    /**
     * @param @param  id
     * @param @param  model
     * @param @return 设定文件
     * @return Map<String,String> 返回类型
     * @throws
     * @Title: delBrand
     * @Description: 删除品牌
     */
    @RequestMapping(value = "/del")
    public
    @ResponseBody
    Map<String, String> delBrand(@RequestParam(value = "id") String id,
                                 Model model) {
        Map<String, String> map = new HashMap<String, String>();
        if (Strings.isNullOrEmpty(id)) {
            model.addAttribute("result", "ID为空");
            map.put("result", "ID为空");
            map.put("success", "true");
            return map;
        }
        brandService.delete(Integer.parseInt(id));
        map.put("result", "删除成功");
		map.put("success", "true");
        return map;
    }

    /**
     * @param @param  model
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: toApply
     * @Description: 跳转到申请品牌页面
     */
    @RequestMapping("/toApply")
    public String toApply(Model model) {
        try {
            model.addAttribute("classList", goodsClassService.findList(0));

            return "/store/store-brand-apply";
        } catch (Exception e) {
            log.error("导航失败!", e);
            throw new RuntimeException("导航失败!");
        }
    }

    /**
     * @param @param  image
     * @param @param  brand
     * @param @param  request
     * @param @param  response
     * @param @param  model
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: saveImg
     * @Description: 申请品牌(图片和其他信息是分别保存的)，保存品牌logo
     */
    @RequestMapping("/saveImg")
    public String saveImg(@RequestParam("imageFile") MultipartFile[] image,
                          @ModelAttribute("brand") Brand brand, HttpServletRequest request,
                          HttpServletResponse response, Model model) throws IOException {

        String brandPic = "";
        try {
        	
            OSSConfigure ossConfigure=new OSSConfigure();
            Map<String, Object> map =OSSManageUtil.uploadFile(ossConfigure, image, Constants.BRAND_UPLOAD_URL,"images",request);
            
            brandPic = (String) map.get("result");
        } catch (Exception e) {
            log.error("上传文件失败", e.toString());
        }

        String referer = request.getHeader("Referer");
        model.addAttribute("referer", referer);
        model.addAttribute("msg", "新增成功");
        // return Constants.MSG_URL;
        try {
            String message = "<script>parent.imgCallback('"
                    + Constants.BRAND_UPLOAD_URL + "','" + brandPic
                    + "')</script>";

            response.getWriter().write(message);
            response.getWriter().flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * @param @param  brand
     * @param @param  response
     * @param @param  model
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: save
     * @Description: 申请品牌(图片和其他信息是分别保存的)，保存名称、类别等信息
     */
    @RequestMapping("/save")
    public String save(@ModelAttribute("brand") Brand brand,
                       HttpServletResponse response, Model model) {
        // 测试使用，正式的应该从session获取当前用户，再获取storeId
        Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
        brand.setStoreId(storeId);
        brand.setBrandApply(0);
        brand.setBrandSort(0);
        brandService.save(brand);
        return "redirect:/storeBrand/index";
    }

    /**
     * @param @param  model
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: toEdit
     * @Description: 跳转到编辑页面
     */
    @RequestMapping("/toEdit")
    public String toEdit(@RequestParam(value = "id") String id, Model model) {
        try {
            Brand brand = brandService.findById(Long.parseLong(id));
            model.addAttribute("brand", brand);
            model.addAttribute("classList", goodsClassService.findList(0));
            return "/store/store-brand-edit";
        } catch (Exception e) {
            log.error("导航失败!", e);
            throw new RuntimeException("导航失败!");
        }
    }

    @RequestMapping("/edit")
    public String edit(@ModelAttribute("brand") Brand brand,
                       HttpServletResponse response, Model model) {
        // 测试使用，正式的应该从session获取当前用户，再获取storeId
        Long storeId = new Long(1);

        brand.setStoreId(storeId);
        brand.setBrandApply(0);
        brandService.update(brand);

        try {
            String message = "<script>parent.callback()</script>";

            response.getWriter().write(message);
            response.getWriter().flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @RequestMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam MultipartFile[] myfiles, HttpServletResponse response, HttpServletRequest request) throws IOException {

        Map<String, Object> map = Maps.newHashMap();
        try {
            OSSConfigure ossConfigure=new OSSConfigure();
            map =OSSManageUtil.uploadFile(ossConfigure, myfiles, Constants.BRAND_UPLOAD_URL,"images",request);
            
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传文件失败", e.toString());
        }
        String json = JsonUtils.toJsonStr(map);
        response.setContentType("text/html");
        response.getWriter().write(json);

        return null;
    }
    
	/**
	 * 
	 * 
	 * @Title: index
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/toAdd")
	public ModelAndView toAdd() {
		try {
			ModelAndView model = new ModelAndView("/store/store-brand-add");
			List<GoodsClass> list=goodsClassService.findList(0);
			model.addObject("list", list);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 *
	 * @Title: saveStorebrand
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jsondata
	 * @param @param model
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return Map<String,Object>    返回类型
	 * @throws
	 */
	@RequestMapping(value = "/saveStorebrand", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> saveAddress(@RequestParam(value = "data") String jsondata,Model model) throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		Integer storeid=CacheUtils.getCacheUser().getStore().getStoreId();
		map = brandService.saveStorebrand(jsondata,storeid);
		return map;
	}
	
	/**
	 * 
	 * @Title: updatestorebrand 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return ModelAndView    返回类型 
	 * @throws
	 */
	@RequestMapping("/updatestorebrand")
	public ModelAndView updatestorebrand(@RequestParam(value = "brandId") String brandId) {
		try {
			ModelAndView model = new ModelAndView("/store/store-brand-update");
            Brand brand=brandService.findById(Long.valueOf(brandId));	
            List<GoodsClass> list=goodsClassService.findList(0);
			model.addObject("list", list);
			model.addObject("brand",brand);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("店铺品牌加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	
	/**
	 *
	 * @Title: saveStorebrand
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jsondata
	 * @param @param model
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return Map<String,Object>    返回类型
	 * @throws
	 */
	@RequestMapping(value = "/saveStorebrands", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> saveStorebrands(@ModelAttribute("brand") Brand brand) throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		brand.setStoreId(CacheUtils.getCacheUser().getStore().getStoreId());
		map = brandService.saveStorebrand(brand);
		return map;
	}
}
