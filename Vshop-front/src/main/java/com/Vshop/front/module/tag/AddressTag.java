package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.entity.base.Address;
import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.cart.service.AddressService;

import freemarker.template.TemplateModelException;

/**
 * 收货地址标签
 * @author liukai
 */
@Component
public class AddressTag extends BaseFreeMarkerTag{
	
	@Resource
	private AddressService addressService;

	@SuppressWarnings("rawtypes")
	@Override
	protected Object exec(Map params) throws TemplateModelException {
		//获取学员收货地址
        List<Address> addressList = addressService.queryAddreassMemberId(CacheUtils.getCacheUser().getMember().getMemberId());
		return addressList;
	}
}
