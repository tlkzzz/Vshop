package com.Vshop.front.module.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Vshop.core.common.MyBeanUtils;
import com.Vshop.core.entity.apibean.InvoiceApiBean;
import com.Vshop.core.entity.base.Invoice;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.service.module.trade.service.InvoiceService;

@Slf4j
@Controller
@RequestMapping("/invoiceapi")
public class InvoiceApi extends BaseApi{
	
	@Resource
	private InvoiceService invoiceService;
	
	/**
	 * 保存或修改发票信息
	 * @param memberId 用户id
	 * @param invTitle 发票抬头
	 * @param invContent 发票内容
	 * @param invState 发票类型
	 * @return
	 */
	@RequestMapping("/addInvoice")
	@ResponseBody
	public JSONObject addInvoice(
						@RequestParam(value="memberId") Integer memberId,
						@RequestParam(value="invTitle") String invTitle,
						@RequestParam(value="invContent") String invContent,
						@RequestParam(value="invState") String invState
						){
		jsonObj = new JSONObject();
		try{
			Invoice invoice = invoiceService.saveInvoiceForApi(memberId, invTitle, invContent, invState);
			jsonObj.put("data", JSONArray.fromObject(invoice, JsonUtils.getJsonConfig()));
	        jsonObj.put("result", 1);
            jsonObj.put("msg", "保存发票成功");
		}catch (Exception e) {
			log.error("保存发票API出错", e);
			jsonObj.put("result", 0);
            jsonObj.put("msg", "服务器异常");
		}
		return jsonObj;
	}
	
	/**
	 * 获取发票列表
	 * @param memberId 用户id
	 * @param invState 发票类型
	 * @return
	 */
	@RequestMapping("/invoiceList")
	@ResponseBody
	public JSONObject invoiceList(
						@RequestParam(value="memberId") Integer memberId,
						@RequestParam(value="invState") String invState
						){
		jsonObj = new JSONObject();
		try{
			Invoice invoice = new Invoice();
			invoice.setMemberId(memberId);
			invoice.setInvState(invState);
			List<Invoice> invoices = invoiceService.findInvoiceList(invoice);
			List<InvoiceApiBean> list = new ArrayList<InvoiceApiBean>();
			for(Invoice inv:invoices){
				InvoiceApiBean invoiceApiBean = new InvoiceApiBean();
				MyBeanUtils.copyBeanNotNull2Bean(inv,invoiceApiBean);
				list.add(invoiceApiBean);
			}
			jsonObj.put("data", JSONArray.fromObject(list, JsonUtils.getJsonConfig()));
	        jsonObj.put("result", 1);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("发票列表API出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "无数据");
		}
		return jsonObj;
	}
}
