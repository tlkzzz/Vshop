package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Vshop.core.entity.base.Payment;
import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.setting.service.PaymentService;

import freemarker.template.TemplateModelException;

/**
 * Tag支付方式
 * @author LKANG
 * 2015-07-08下午11:33:23
 */
@Component
public class PaymentTag extends BaseFreeMarkerTag {

	@Autowired
	private PaymentService paymentService;
	
	/**
	 * 获取支付方式
	 * @param isshow 是否显示 1显示，0不显示
	 */
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		String isshow = ParamsUtils.getString(params.get("isshow"));
		List<Payment> Paymentlist = paymentService.querybystatelist(isshow);
		return Paymentlist;
	}

}
