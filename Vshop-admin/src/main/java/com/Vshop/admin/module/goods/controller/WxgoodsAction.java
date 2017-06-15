package com.Vshop.admin.module.goods.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.Vshop.admin.utils.ExportExcelUtils;
import com.Vshop.core.common.Constants;
import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.GoodsClass;
import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.base.Wxgoods;
import com.Vshop.core.ossconfigure.OSSConfigure;
import com.Vshop.core.ossconfigure.OSSManageUtil;
import com.Vshop.service.module.goods.service.BrandService;
import com.Vshop.service.module.goods.service.GoodsClassService;
import com.Vshop.service.module.goods.service.WxgoodsService;
import com.Vshop.service.utils.CommonConstants;
import com.Vshop.service.utils.page.Pager;

@Slf4j
@Controller
@RequestMapping("/goods/wxgoods")
public class WxgoodsAction {
	@Resource
    private WxgoodsService wxgoodsService;
//	@Resource
//	private BrandService wxgoodsService;



    /**
     * 查询数据表
     * @param wxgoods
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String List(@ModelAttribute Wxgoods wxgoods,Model model,
                       @RequestParam(required=false, value="pageNo",defaultValue="")String pageNo){

        Pager pager = new Pager();
        if(StringUtils.isNotBlank(pageNo)){
            pager.setPageNo(Integer.parseInt(pageNo));
        }
        pager.setCondition(wxgoods);
//        int total  = brandService.findCount( brand);//获取总条数
        List<Wxgoods> results=wxgoodsService.findPageList(pager);
        pager.setResult(results);
//        pager.setTotalRows(total);
        model.addAttribute("pager", pager);//总数
        model.addAttribute("wxgoods",wxgoods);
        return "goods/wxgoods/list";
    }
  //导出excel
  	@RequestMapping("/exportExcel")  
      public void exportExcel(@RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNo,
  			@RequestParam(required = false, value = "name", defaultValue = "") String name,
  			@RequestParam(required = false, value = "spbh", defaultValue = "") String spbh,
  			@RequestParam(required = false, value = "je", defaultValue = "") String je,
  			@RequestParam(required = false, value = "state", defaultValue = "") String state,
  			HttpServletResponse response,
  			HttpServletRequest request) throws Exception{  
  		String fileName = System.currentTimeMillis() +"";
          response.setCharacterEncoding("utf-8");  
          response.setContentType("multipart/form-data");    
          response.setHeader("Content-Disposition", "attachment;fileName="+fileName+".xlsx");  
          Pager pager = new Pager();
  		Wxgoods w = new Wxgoods();
  		if (StringUtils.isNotEmpty(name)) {
  			w.setName(name);
  		}
  		if (StringUtils.isNotEmpty(spbh)) {
  			w.setSpbh(spbh);
  		}
  		if (StringUtils.isNotEmpty(je)) {
  			w.setJe(je);
  		}
  		if (StringUtils.isNotEmpty(state)) {
  			w.setState(state);
  		}
//  		if (StringUtils.isNotEmpty(pageNo)) {
//  			pager.setPageNo(Integer.parseInt("0"));
//  		}
  		// 将sellerlog对象放入page中
//  		pager.setCondition(w);
  		// 获取总数,放入page
  		// 获取list,得到
  		 List<Wxgoods> results=wxgoodsService.findList(w);
  		 if(results.size()>0){
  			 
  		
  		String realPath = CommonConstants.FILE_BASEPATH + Constants.WXHB_PATH;
  		String url = realPath + "/" + fileName;
  		log.debug(url);
//  		 List<String> list=new ArrayList<String>();
//  		 list.add("id");
//  		 list.add("spbh");
  		ExportExcelUtils.exports(results, url);
          try {  
              File file=new File(url + ".xlsx");  
              response.setHeader("Content-type", "application/vnd.ms-excel"); 
              InputStream inputStream=new FileInputStream(file);  
              OutputStream os=response.getOutputStream();  
              byte[] b=new byte[1024];  
              int length;  
              while((length=inputStream.read(b))>0){  
                  os.write(b,0,length);  
              }  
              inputStream.close();  
          } catch (FileNotFoundException e) {  
              log.error("",e);
          } catch (IOException e) {  
              log.error("",e);
          }
  	}
      }
    /**
     * 生成一位字母加6位数字
     * @param length
     * @return
     */
	public static String randomGoodsNO(int length){
		String retStr = "";
		int param = 1;
		for(int i=length-2;i>0;i--){param=param*10;}
		char c = (char)(int)(Math.random()*26+65);
		retStr =  String.valueOf(c);
		retStr += (int)((Math.random()*9+1)*param);
		if(StringUtils.isBlank(retStr))retStr = randomGoodsNO(length);
		return retStr;
	}
//    /**
//     * 修改特别推荐
//     * @param
//     */
//    @RequestMapping("/recommond")
//    public @ResponseBody boolean updateRecommond(@RequestParam int id,@RequestParam int value){
//
//    	Wxgoods brand = new Wxgoods();
//        brand.setBrandId(id);
//        brand.setBrandRecommend(value);
//        brandService.update(brand);
//        return true;
//    }


    /**
     * 保存新增品牌
     * @param image
     * @param brand
     * @param request
     * @return
     */
    @RequestMapping("/save")
    public String  save(@ModelAttribute("wxgoods") Wxgoods wxgoods ,HttpServletRequest request,Model model) throws IOException{
    	int js = wxgoods.getSj();
    	for(int i =0;i<js;i++){
    		wxgoods.setId(DateUtils.getUUID());//id
    		wxgoods.setCjsj(DateUtils.getDate24());//创建时间 
    		wxgoods.setSjzfc(DateUtils.getUUID());//随机字符串
    		wxgoods.setSpbh(randomGoodsNO(6));//生产商品编号
    		wxgoods.setState("0");
    		wxgoods.setUrl("http://www.ubisp.com/weixin/authors?p="+wxgoods.getId());
    		wxgoodsService.save(wxgoods);
    	}
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
    public String forward(Model model){
            return "goods/wxgoods/save";
      

    }
    /**
     * 跳转
     * @param model
     * @return
     */
    @RequestMapping("/forwards")
    public String forwards(Model model,@RequestParam String id){
            //查询品牌
            Wxgoods wxgoods=wxgoodsService.findById(id);
            model.addAttribute("w",wxgoods);
            return "goods/wxgoods/edit";
        

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
    public String edit(@ModelAttribute("wxgoods") Wxgoods wxgoods ,HttpServletRequest request,Model model) throws IOException{

    	wxgoodsService.update(wxgoods);
        String referer = request.getHeader("Referer");
        model.addAttribute("referer", referer);
        model.addAttribute("msg", "修改成功");
        return Constants.MSG_URL;
    }


   


    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping("/delValide")
    public @ResponseBody Map<String, Object> delValide(@RequestParam String id, HttpServletRequest request,Model model){
    	Map<String, Object> map = new HashMap<String, Object>();
    	if(!id.equals("")){
    		String [] a = id.split(",");
    		for(int i=0;i<a.length;i++){
    			wxgoodsService.delete(a[i]);
    		}
    		 map.put("success", true);
    	}else{
    		 map.put("success", false);
    	}
    	
       return map;
    }

    /**
     * 校验表单
     * @return
     */
    @RequestMapping("/validate")
    public @ResponseBody String validateForm(@ModelAttribute Wxgoods wxgoods){

        //Pager pager = new Pager();
        //pager.setCondition(brand);
        //校验重复
        if(wxgoodsService.countBrand(wxgoods) > 0){
            return "false";
        }else{
            return "true";
        }
    }

  

    /**
     * 通过品牌
     * @param ids
     * @param request
     * @param model
     * @return
     */
//    @RequestMapping("/pass")
//    public String  pass(@RequestParam int[] ids,HttpServletRequest request,Model model){
//
//        String referer = request.getHeader("Referer");
//        for(int id : ids){
//            Brand brand = brandService.findById((long)id);
//            brand.setBrandApply(1);
//            brandService.update(brand);
//        }
//        model.addAttribute("referer", referer);
//        model.addAttribute("msg", "品牌通过");
//        return Constants.MSG_URL;
//    }
    
    
   
}
