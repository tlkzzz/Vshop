package com.Vshop.front.module.tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.entity.base.Address;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.vo.CartVo;
import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.cart.service.AddressService;
import com.Vshop.service.module.cart.service.CartService;
import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.service.module.promotion.service.PromotionClassService;

import freemarker.template.TemplateModelException;

/**
 * 购物车列表标签
 * @author liuk
 *
 */
@Component
public class CartTag extends BaseFreeMarkerTag{
	
	@Resource
	private CartService cartService;
	
	@Resource
	private AddressService addressService;
	
	@Resource
	private MemberService memberService;
	
	@Resource
	private PromotionClassService promotionClassService;

	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		Map<String, Object> map = new HashMap<String, Object>();
		String cartIds = ParamsUtils.getString(params.get("cartIds"));
		if("".equals(cartIds)){
			List<CartVo> cartVoList = cartService.queryCartByMemberID(CacheUtils.getCacheUser().getMember().getMemberId());
			map.put("cartVoList",cartVoList);
            map.put("map", cartService.getTotalPrice(cartVoList));
		}else{
			//根据多个cartId查询购物车集合,分单
            List<CartVo> cartVoList = cartService.queryVOListByCartIds(cartIds);
            //获取学员收货地址
            List<Address> addressList = addressService.queryAddreassMemberId(CacheUtils.getCacheUser().getMember().getMemberId());
            //根据当前登录用户id,查询用户信息
            Member member = memberService.findById(CacheUtils.getCacheUser().getMember().getMemberId());
            //查询促销信息
            String promotionMessage = promotionClassService.findMessage();
            map.put("map", cartService.getTotalPrice(cartVoList));
            map.put("addressList", addressList);
            map.put("member", member);
            map.put("cartVoList", cartVoList);
            map.put("promotionMessage", promotionMessage);
		}
		return map;
	}

}
