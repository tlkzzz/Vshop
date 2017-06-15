package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.Vshop.core.entity.base.Invoice;
import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.trade.service.InvoiceService;

import freemarker.template.TemplateModelException;

/**
 * 获得默认发票信息
 * @author cgl
 *
 */
@Component
public class InvoiceInfoTag extends BaseFreeMarkerTag {
	
	@Resource
	private InvoiceService invoiceService;

	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		
		/**普通发票还是增值税发票*/
		String invState = ParamsUtils.getString(params.get("invState"));
		
		/**是否是默认的*/
		String isDefault =  ParamsUtils.getString(params.get("isDefault"));
		
		/**会员id*/
		Integer memberId = CacheUtils.getCacheUser().getMember().getMemberId();
		
		Invoice invoice = new Invoice();
		
		invoice.setMemberId(memberId);
		
		/**判断是否为空*/
		if(StringUtils.isNotEmpty(invState)){
			invoice.setInvState(invState);
		}
		
		if(StringUtils.isNotEmpty(isDefault)){
			invoice.setIsDefault(Integer.parseInt(isDefault));
		}
		
		List<Invoice> invoices = invoiceService.findInvoiceList(invoice);
		
		if(invoices != null){
			/**判断是否是增值税发票,如果是增值税发票,则取出第一条,因为增值税发票只会有一条记录*/
			return invoices;
		}
		
		return null;
	}

}
