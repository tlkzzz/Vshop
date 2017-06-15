package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.core.entity.base.Cart;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.cart.service.CartService;

import freemarker.template.TemplateModelException;

/**
 * 会员购物车数量
 * @author gyh
 * 添加时间：2015-07-21 12:12:00
 */
@Component
public class CartCountbyMembeiIdTag extends BaseFreeMarkerTag {

	@Resource
    private CartService cartService;
	
	/**
	 * 购物车列表
	 * @param MemberId  会员id
	 */
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
			//会员id
			Integer MemberId=ParamsUtils.getInt(params.get("MemberId"));
			//定义该会员相关的购物车数量
			Integer cartcount=null;
			//通过memberId查询购物车的数量
			List<Cart> list=null;
			if(MemberId!=null){
				list=cartService.queryBuyCart(MemberId);
				if(list.size()!=0){
					cartcount=list.size();
				}
			}
			return cartcount;
	}
}
