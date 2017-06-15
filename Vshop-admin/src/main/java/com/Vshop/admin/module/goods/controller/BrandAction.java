package com.Vshop.admin.module.goods.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.GoodsClass;
import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.ossconfigure.OSSConfigure;
import com.Vshop.core.ossconfigure.OSSManageUtil;
import com.Vshop.service.module.goods.service.BrandService;
import com.Vshop.service.module.goods.service.GoodsClassService;
import com.Vshop.service.utils.CommonConstants;
import com.Vshop.service.utils.page.Pager;

@Slf4j
@Controller
@RequestMapping("/goods/brand")
public class BrandAction {
	@Resource
    private BrandService brandService;

    @Resource
    private GoodsClassService goodsClassService;

    /**
     * 查询品牌列表
     * @param brand
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String List(@ModelAttribute Brand brand,Model model,
                       @RequestParam(required=false, value="pageNo",defaultValue="")String pageNo){

        Pager pager = new Pager();
        if(StringUtils.isNotBlank(pageNo)){
            pager.setPageNo(Integer.parseInt(pageNo));
        }
        pager.setCondition(brand);
//        int total  = brandService.findCount( brand);//获取总条数
        List<Brand> results=brandService.findPageList(pager);
        pager.setResult(results);
//        pager.setTotalRows(total);
        model.addAttribute("pager", pager);//总数
        model.addAttribute("brand",brand);
        return "goods/brand/list";
    }

    /**
     * 修改特别推荐
     * @param
     */
    @RequestMapping("/recommond")
    public @ResponseBody boolean updateRecommond(@RequestParam int id,@RequestParam int value){

        Brand brand = new Brand();
        brand.setBrandId(id);
        brand.setBrandRecommend(value);
        brandService.update(brand);
        return true;
    }


    /**
     * 保存新增品牌
     * @param image
     * @param brand
     * @param request
     * @return
     */
    @RequestMapping("/save")
    public String  save(@RequestParam("imageFile") MultipartFile[] image,
                     @ModelAttribute("brand") Brand brand ,HttpServletRequest request,Model model) throws IOException{

        String brandPic = "";
        try {
            OSSConfigure ossConfigure=new OSSConfigure();
    		Map<String,Object> map =OSSManageUtil.uploadFile(ossConfigure, image, Constants.BRAND_UPLOAD_URL,"images",request);
        	  brandPic = (String)map.get("result");
            
            
        } catch (Exception e) {
            log.error("上传文件失败", e.toString());
        }
        brand.setBrandPic(brandPic);
        brand.setBrandApply(1);
        brandService.save(brand);
        String referer = request.getHeader("Referer");
        model.addAttribute("referer", referer);
        model.addAttribute("msg", "新增成功");
        return Constants.MSG_URL;
    }

    /**
     * 跳转
     * @param model
     * @return
     */
    @RequestMapping("/forward")
    public String forward(Model model,@RequestParam int id){

        //查询类别列表
        List<GoodsClass> list=goodsClassService.findList(0);
        model.addAttribute("list",list);
        if(id == 0){
            return "goods/brand/save";
        }else{

            //查询品牌
            Brand brand=brandService.findById(id);
            model.addAttribute("brand",brand);
            return "goods/brand/edit";
        }

    }

    /**
     * 编辑
     * @param image
     * @param brand
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String edit(@RequestParam("imageFile") MultipartFile[] image,
                       @ModelAttribute("brand") Brand brand ,HttpServletRequest request,Model model) throws IOException{

        String brandPic = "";
        try {
        	
        	  OSSConfigure ossConfigure=new OSSConfigure();
      		Map<String,Object> map =OSSManageUtil.uploadFile(ossConfigure, image, Constants.BRAND_UPLOAD_URL,"images",request);
          	  brandPic = (String)map.get("result");
        } catch (Throwable e) {
            log.error("上传文件失败", e.toString());
        }
        brand.setBrandPic(brandPic);
        brandService.update(brand);
        String referer = request.getHeader("Referer");
        model.addAttribute("referer", referer);
        model.addAttribute("msg", "修改成功");
        return Constants.MSG_URL;
    }


    /**
     * 修改排序
     * @return
     */
    @RequestMapping("/modifySort")
    public @ResponseBody Boolean modifySort(@RequestParam int id,@RequestParam Integer value){

        Brand brand = new Brand();
        brand.setBrandId(id);
        brand.setBrandSort(value);
        brandService.update(brand);
        return true;
    }

    /**
     * 修改分类名称
     * @param id
     * @param value
     * @return
     */
    @RequestMapping("/modifyName")
    public @ResponseBody Boolean modifyname(@RequestParam int id,@RequestParam String value){

        Brand brand = new Brand();
        brand.setBrandId(id);
        brand.setBrandName(value);
        //Pager page = new Pager();
       //page.setCondition(brand);
        //判断是否有重复名称
        if(brandService.countBrand(brand) > 0){
            return false;
        }else{
            //执行修改操作
            brandService.update(brand);
            return true;
        }
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public String  delete(@RequestParam int[] ids,HttpServletRequest request,Model model){

        String referer = request.getHeader("Referer");
        for(int id : ids){
            brandService.delete(id);
        }
        model.addAttribute("referer", referer);
        model.addAttribute("msg", "删除成功");
        return Constants.MSG_URL;
    }

    /**
     * 校验表单
     * @return
     */
    @RequestMapping("/validate")
    public @ResponseBody String validateForm(@ModelAttribute Brand brand){

        //Pager pager = new Pager();
        //pager.setCondition(brand);
        //校验重复
        if(brandService.countBrand(brand) > 0){
            return "false";
        }else{
            return "true";
        }
    }

    /**
     * 待审核列表
     * @param brand
     * @param model
     * @param pageNo
     * @return
     */
    @RequestMapping("/applyList")
    public String applyList(@ModelAttribute Brand brand,Model model,
                            @RequestParam(required=false, value="pageNo",defaultValue="")String pageNo){

        Pager pager = new Pager();
        if(StringUtils.isNotBlank(pageNo)){
            pager.setPageNo(Integer.parseInt(pageNo));
        }
        brand.setBrandApply(0);
        pager.setCondition(brand);
        //int total  = brandService.findCount(pager);//获取总条数
        List<Brand> results=brandService.findPageList(pager);
        pager.setResult(results);
        //pager.setTotalRows(total);
        model.addAttribute("pager", pager);//总数
        model.addAttribute("brand",brand);
        return "goods/brand/applyList";
    }

    /**
     * 通过品牌
     * @param ids
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/pass")
    public String  pass(@RequestParam int[] ids,HttpServletRequest request,Model model){

        String referer = request.getHeader("Referer");
        for(int id : ids){
            Brand brand = brandService.findById((long)id);
            brand.setBrandApply(1);
            brandService.update(brand);
        }
        model.addAttribute("referer", referer);
        model.addAttribute("msg", "品牌通过");
        return Constants.MSG_URL;
    }
    
    
    /**
     * 拒绝品牌
     * @param ids
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/refuse")
    public String  refuse(@RequestParam int[] ids,HttpServletRequest request,Model model){

        String referer = request.getHeader("Referer");
        for(int id : ids){
            Brand brand = brandService.findById((long)id);
            brand.setBrandApply(2);
            brandService.update(brand);
        }
        model.addAttribute("referer", referer);
        model.addAttribute("msg", "品牌未通过");
        return Constants.MSG_URL;
    }
    
    @RequestMapping("/delValide")
    public @ResponseBody Map<String, ? extends Object> delValide(@RequestParam String ids, HttpServletRequest request,Model model){
       List<Integer> brandIds = JSON.parseArray(ids, Integer.class);
       List<Map<String, Object>> usedList = brandService.queryBrandUseList(brandIds);
       return CollectionUtils.isEmpty(usedList) ?  ImmutableMap.of("success", true) : ImmutableMap.of("success", false, "msg", "品牌不能删除", "data", usedList);
    }
}
