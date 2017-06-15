package com.Vshop.front.module.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Vshop.core.entity.Area;
import com.Vshop.core.entity.base.Invoice;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.area.service.AreaService;
import com.Vshop.service.module.trade.service.InvoiceService;

/**
 * 发票controller
 * @author cgl
 * 2015年08月15日10:42:26
 */
@Controller
@RequestMapping("/invoice")
public class InvoiceAction {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private AreaService areaService;
    
    /**
     * 修改发票信息页面
     */
    @RequestMapping("/updateInvoiceIndex")
    public String updateInvoiceIndex(){
    	return "/user/invoice/invoice-update-index";
    }
    
    /**
     * 修改发票信息页面
     */
    @RequestMapping("/updateInvoiceCommon")
    public String updateInvoiceCommon(){
    	return "/user/invoice/invoice-update-common";
    }
    
    /**
     * 修改发票信息页面
     */
    @RequestMapping("/updateInvoiceVAT")
    public ModelAndView updateInvoiceVAT(){
    	ModelAndView model = new ModelAndView("/user/invoice/invoice-update-vat");
    	List<Area> areas = areaService.queryAll();
    	model.addObject("areas", areas);
    	return model;
    }
    
    /**
     * 修改增值税发票
     */
    @ResponseBody
    @RequestMapping("/updateInvoiceVAT.do")
    public Map<String,Object> updateInvoiceVATDo(Invoice invoice){
    	Map<String,Object> map = new HashMap<String, Object>();
    	try{
        	Integer memberId = CacheUtils.getCacheUser().getMember().getMemberId();
        	Invoice inv = invoiceService.updateVATInv(invoice, memberId);
        	map.put("invoice", inv);
        	map.put("success", true);
    	}catch(Exception e){
    		e.printStackTrace();
    		map.put("success", false);
    	}
    	return map;
    }
    
    
    @RequestMapping("/saveInvoiceTitle")
    @ResponseBody
    public Map<String,Object> saveInvoiceTitle(
    					@RequestParam(value="title") String title					
    					){
    	Map<String,Object> map = new HashMap<String, Object>();
    	try{
    		Invoice invoice = invoiceService.saveInvoiceTitle(CacheUtils.getCacheUser().getMember().getMemberId(), title);
    		map.put("invoice", invoice);
    		map.put("success", true);
    	}catch (Exception e) {
    		map.put("success", false);
    		e.printStackTrace();
    	}
    	
    	return map;
    }
    
    /**
     * 删除发票信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteInvoice")
    @ResponseBody
    public Map<String,Object> deleteInvoice(
    						@RequestParam(value="id") Integer id	
    						){
    	Map<String,Object> map = new HashMap<String, Object>();
    	try{
    		invoiceService.delete(id);
    		map.put("success", true);
    	}catch (Exception e) {
    		map.put("success", false);
    		e.printStackTrace();
    	}
    	return map;
    }
    
    /**
     * 修改发票信息
     * @param id
     * @return
     */
    @RequestMapping("/updateInvoice")
    @ResponseBody
    public Map<String,Object> updateInvoice(
    						@RequestParam(value="id") Integer id,
    						@RequestParam(value="title",required=false,defaultValue="") String title,
    						@RequestParam(value="content",required=false,defaultValue="") String content
    						){
    	Map<String,Object> map = new HashMap<String, Object>();
    	try{
    		Invoice invoice = new Invoice();
    		//修改抬头
    		if(StringUtils.isNotBlank(title)){
    			invoice.setInvId(id);
    			invoice.setInvTitle(title);
    		}
    		//修改内容
    		if(StringUtils.isNotBlank(content)){
    			invoice.setMemberId(CacheUtils.getCacheUser().getMember().getMemberId());
    			invoice.setInvContent(content);
    			invoice.setInvState("1");
    			invoiceService.deleteOrtherDef(CacheUtils.getCacheUser().getMember().getMemberId());
    			invoiceService.updateDef(id);
    		}
    		invoiceService.update(invoice);
    		map.put("id", id);
    		map.put("success", true);
    	}catch (Exception e) {
    		map.put("success", false);
    		e.printStackTrace();
    	}
    	return map;
    }
}
