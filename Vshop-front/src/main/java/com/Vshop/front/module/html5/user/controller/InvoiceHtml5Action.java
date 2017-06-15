package com.Vshop.front.module.html5.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.Vshop.core.entity.base.Invoice;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.trade.service.InvoiceService;

/**
 * 发票controller
 * @author cgl
 * 2015年08月15日10:42:26
 */
@Controller
@RequestMapping("/m/invoice")
public class InvoiceHtml5Action {
	
	@Autowired
	private InvoiceService invoiceService;
	
    /**
     * 修改发票信息页面
     */
    @RequestMapping("/update")
	public ModelAndView update(@RequestParam(value = "cartIds", required = false) String cartIds,
			@RequestParam(value = "invoiceId", required = false, defaultValue = "0") Integer invoiceId,
			@RequestParam(value = "addressId", required = false) String addressId) {
		ModelAndView model = new ModelAndView("/html5/cart/invoice");
		model.addObject("cartIds", cartIds);
		model.addObject("addressId", addressId);
		model.addObject("invoiceId", invoiceId);

		if (invoiceId > 0) {
			Invoice invoice = invoiceService.findByInvId(invoiceId);
			model.addObject("invoice", invoice);
		}

		return model;
	}
    
    
    @RequestMapping("/saveInvoiceTitle")
    @ResponseBody
	public Map<String, Object> saveInvoiceTitle(@RequestParam(value = "title") String title) {
		Map<String, Object> map = Maps.newHashMap();
		try {
			Invoice invoice = invoiceService.saveInvoiceTitle(CacheUtils.getCacheUser().getMember().getMemberId(), title);
			map.put("invoice", invoice);
			map.put("success", true);
		} catch (Exception e) {
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
	public Map<String, Object> deleteInvoice(@RequestParam(value = "id") Integer id) {
		Map<String, Object> map = Maps.newHashMap();
		try {
			invoiceService.delete(id);
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}
		return map;
	}
    
	@RequestMapping("/saveInvoice")
    @ResponseBody
	public Map<String, Object> saveInvoice(
			@RequestParam(value = "cartIds", required = false) String cartIds,
			@RequestParam(value = "addressId", required = false) String addressId,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "content", required = false, defaultValue = "") String content) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int memberId = CacheUtils.getCacheUser().getMember().getMemberId();

			// 将用户发票下所有发票去掉默认
			invoiceService.deleteOrtherDef(memberId);

			// 保存发票信息
			Invoice invoice = invoiceService.saveInvoiceForApi(memberId, title, content, "1");

			map.put("invoice", invoice);
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}

		map.put("cartIds", cartIds);
		map.put("addressId", addressId);
		
		return map;
	}
	
    /**
     * 修改发票信息
     * @param id
     * @return
     */
    @RequestMapping("/updateInvoice")
    @ResponseBody
	public Map<String, Object> updateInvoice(@RequestParam(value = "id") Integer id,
			@RequestParam(value = "cartIds", required = false) String cartIds,
			@RequestParam(value = "addressId", required = false) String addressId,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "content", required = false, defaultValue = "") String content) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Invoice invoice = new Invoice();
			// 修改抬头
			if (StringUtils.isNotBlank(title)) {
				invoice.setInvId(id);
				invoice.setInvTitle(title);
			}
			// 修改内容
			if (StringUtils.isNotBlank(content)) {
				invoice.setMemberId(CacheUtils.getCacheUser().getMember().getMemberId());
				invoice.setInvContent(content);
				invoice.setInvState("1");
				invoiceService.deleteOrtherDef(CacheUtils.getCacheUser().getMember().getMemberId());
				invoiceService.updateDef(id);
			}
			invoiceService.update(invoice);
			map.put("id", id);
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}

		map.put("cartIds", cartIds);
		map.put("addressId", addressId);
		
		return map;
	}
}
