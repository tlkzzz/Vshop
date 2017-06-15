package com.Vshop.front.module.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Vshop.core.entity.GoodsSpec;
import com.Vshop.core.entity.apibean.CartApiBean;
import com.Vshop.core.entity.base.Address;
import com.Vshop.core.entity.base.CouponMember;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.vo.CartVo;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.service.module.cart.service.AddressService;
import com.Vshop.service.module.cart.service.CartService;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.module.goods.service.GoodsSpecService;
import com.Vshop.service.module.member.service.MemberService;

/**
 * 购物车接口api
 * @author kviuff
 * @date 2015-07-21 17:00:00
 */
@Slf4j
@Controller
@RequestMapping("/cartapi")
public class CartApi extends BaseApi {

	@Resource
    private CartService cartService;
	
	@Resource
    private GoodsService goodsService;
	
	@Resource
    private GoodsSpecService goodsSpecService;
	
	@Resource
	private AddressService addressService;
	
	@Resource
	private MemberService memberService;
	
	/**
	 * 加入购物车
	 * @param goodsId
	 * @param count
	 * @param specId
	 * @param memberId
	 * @param saveType 加入类型(加入购物车:0/立即购买:1)
	 * @return
	 */
	@RequestMapping("addCart")
	@ResponseBody
	public JSONObject addCart(
			@RequestParam(value = "goodsId") String goodsId,
            @RequestParam(value = "count", defaultValue = "1") String count, 
            @RequestParam(value = "specId") String specId,
            @RequestParam(value = "memberId") Integer memberId,
            @RequestParam(value = "saveType",required=false, defaultValue="0") Integer saveType
			){
		JSONObject jsonObj = new JSONObject();
		try {
			Map<String, Integer> map = new HashMap<String, Integer>();
			Goods goods = goodsService.findGoodById(Integer.valueOf(goodsId)); //课程信息
	        //通过所传的specId(多个specValueId以","隔开)查询课程规格
			GoodsSpec goodsSpec = goodsSpecService.getGoodsSpecBySpecValueId(specId,goods);
	        int cartIds = cartService.saveCart(goods, memberId, Integer.valueOf(count) , goodsSpec, saveType);
	        map.put("cartIds", cartIds);
	        //判断加入购物车是否成功
	        if(cartIds==0){ //数量超过100
	        	jsonObj.put("result", 0);
	            jsonObj.put("msg", "加入相同课程数量大于100");
	    	}else if(cartIds==-1){
	    		jsonObj.put("result", 0);
	            jsonObj.put("msg", "请勿购买自己学院下的课程");
	    	}else{
	    		jsonObj.put("data", JSONArray.fromObject(map, JsonUtils.getJsonConfig()));
		        jsonObj.put("result", 1);
	            jsonObj.put("msg", "加入购物车成功");
	    	}
		} catch (Exception e) {
			log.error("加入购物车API出错", e);
			jsonObj.put("result", 0);
            jsonObj.put("msg", "服务器异常");
		}
		
		return jsonObj;
	}
	
	/**
	 * 购物车列表
	 * @param memberId
	 * @return
	 */
	@RequestMapping("cartList")
	@ResponseBody
	public JSONObject cartList(
			@RequestParam(value = "memberId") Integer memberId
			){
		JSONObject jsonObj = new JSONObject();
		try {
			List<CartVo> cartVoList = cartService.queryCartByMemberID(memberId);
			jsonObj.put("result", 1);
			jsonObj.put("data", JSONArray.fromObject(cartVoList, JsonUtils.getJsonConfig()));
		} catch (Exception e) {
			log.error("购物车列表API出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "无数据");
		}
		
		return jsonObj;
	}
	
	/**
	 * 删除购物车
	 * @param cartId
	 * @return
	 */
	@RequestMapping("deleteCart")
	@ResponseBody
	public JSONObject deleteCart(
			@RequestParam(value = "cartId") Integer cartId
			){
		JSONObject jsonObj = new JSONObject();
		try {
			cartService.deleteCart(cartId);
			jsonObj.put("result", 1);
            jsonObj.put("msg", "删除购物车成功");
		} catch (Exception e) {
			log.error("删除购物车API出错", e);
			jsonObj.put("result", 0);
            jsonObj.put("msg", "服务器异常");
            jsonObj.put("data", "删除购物车失败");
		}
		return jsonObj;
	}
	
	/**
	 * 修改购物车数量
	 * @param cartId
	 * @param count
	 * @return
	 */
	@RequestMapping("updateCartCount")
	@ResponseBody
	public JSONObject updateCartCount(
			@RequestParam(value = "cartId") Integer cartId,
			@RequestParam(value = "count") Integer count
			){
		JSONObject jsonObj = new JSONObject();
		try {
			int result = cartService.updatecart(cartId, count);
			jsonObj.put("result", result);
			if(result == 1){
				jsonObj.put("msg", "修改购物车数量成功");
			}else{
				jsonObj.put("msg", "修改购物车数量失败");
			}
		} catch (Exception e) {
			log.error("修改购物车数量API出错", e);
			jsonObj.put("result", 0);
            jsonObj.put("msg", "服务器异常");
            jsonObj.put("data", "修改购物车数量失败");
		}
		return jsonObj;
	}
	
	/**
	 * 确认订单
	 * @param cartIds
	 * @param memberId
	 * @return
	 */
	@RequestMapping("subCartToOrder")
	@ResponseBody
	public JSONObject subCartToOrder(
			@RequestParam(value = "cartId") String cartIds
			){
		JSONObject jsonObj = new JSONObject();
		try {
			List<CartVo> cartVoList = cartService.queryVOListByCartIds(cartIds);
            jsonObj.put("result", 1);
            jsonObj.put("data", JSONArray.fromObject(cartVoList, JsonUtils.getJsonConfig()));
		} catch (Exception e) {
			log.error("确认订单API出错", e);
			jsonObj.put("result", 0);
            jsonObj.put("msg", "服务器异常");
            jsonObj.put("data", "确认订单失败");
		}
		
		return jsonObj;
	}
	
	/**
	 * 确认订单
	 * @param cartIds
	 * @param memberId
	 * @return
	 */
	@RequestMapping("subToOrder")
	@ResponseBody
	public JSONObject subToOrder(
			@RequestParam(value = "cartId") String cartIds,
			@RequestParam(value = "memberId") Integer memberId
			){
		JSONObject jsonObj = new JSONObject();
		try {
			//新建一个购物车接口实体
			CartApiBean cartApiBean = new CartApiBean();
			
			List<CartVo> cartVoList = cartService.queryVOListByCartIds(cartIds);
			//获取商户收货地址
            List<Address> addressList = addressService.queryAddreassMemberId(memberId);
            
          //通过多个购物车id和用户id查询优惠券集合
			List<CouponMember> couponMemberList = cartService.queryCouponByCartIds(cartIds, memberId);
            
			//通过用户id查询用户信息
			Member member = memberService.findById(memberId);
			
            cartApiBean.setAddressList(addressList);
            cartApiBean.setCartVoList(cartVoList);
            cartApiBean.setCouponCount(couponMemberList.size()); //优惠券数量
            //用户余额
            if(member.getAvailablePredeposit()!=null){
            	cartApiBean.setMemberAvailable(member.getAvailablePredeposit().toString());
            }else{
            	cartApiBean.setMemberAvailable("0");
            }
            
            jsonObj.put("result", 1);
            jsonObj.put("data", JSONArray.fromObject(cartApiBean, JsonUtils.getJsonConfig()));
		} catch (Exception e) {
			log.error("确认订单API出错", e);
			jsonObj.put("result", 0);
            jsonObj.put("msg", "服务器异常");
            jsonObj.put("data", "确认订单失败");
		}
		
		return jsonObj;
	}
	
	/**
	 * 清空购物车
	 * @param memberId 用户id
	 * @return
	 */
	@RequestMapping("deleteAllCart")
	@ResponseBody
	public JSONObject deleteAllCart(
			@RequestParam(value = "memberId") Integer memberId
			){
		JSONObject jsonObj = new JSONObject();
		try {
			cartService.deleteAllCartByMemberId(memberId);
			jsonObj.put("result", 1);
            jsonObj.put("msg", "清空购物车成功");
		} catch (Exception e) {
			log.error("清空购物车API出错", e);
			jsonObj.put("result", 0);
            jsonObj.put("msg", "服务器异常");
		}
		return jsonObj;
	}
	
	/**
	 * 获取运费
	 * @param cartIds 购物车的id
	 * @param cityId 城市id
	 * @return
	 */
	@RequestMapping("addShipping")
	@ResponseBody
	public JSONObject addShipping(
			@RequestParam(value = "cartIds") String cartIds,
			@RequestParam(value = "cityId",required=false,defaultValue="") String cityId
			){
		JSONObject jsonObj = new JSONObject();
		try {
			Map<String,Object> storeMap = new HashMap<String, Object>();
			//判断城市id是否存在
    		if(StringUtils.isNotBlank(cityId)){ //若存在传值
    			storeMap = cartService.queryFreightByCartIds(Integer.valueOf(cityId), cartIds);
    		}else{ //不在传null
    			storeMap = cartService.queryFreightByCartIds(null, cartIds);
    		}
			jsonObj.put("result", 1);
			jsonObj.put("data", JSONArray.fromObject(storeMap, JsonUtils.getJsonConfig()));
		} catch (Exception e) {
			log.error("加载运费API出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "无数据");
		}
		
		return jsonObj;
	}
	
	/**
	 * 获取运费
	 * @param cartIds 购物车的id
	 * @param cityId 城市id
	 * @return
	 */
	@RequestMapping("addCouponMember")
	@ResponseBody
	public JSONObject addCouponMember(
			@RequestParam(value = "cartIds") String cartIds,
			@RequestParam(value = "memberId") Integer memberId
			){
		JSONObject jsonObj = new JSONObject();
		try {
			//通过多个购物车id和用户id查询优惠券集合
			List<CouponMember> couponMemberList = cartService.queryCouponByCartIds(cartIds, memberId);
			jsonObj.put("result", 1);
			jsonObj.put("data", JSONArray.fromObject(couponMemberList, JsonUtils.getJsonConfig()));
		} catch (Exception e) {
			log.error("加载运费API出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "无数据");
		}
		return jsonObj;
	}
	
	/**
	 * 获取运费
	 * @param cartIds 购物车的id
	 * @param cityId 城市id
	 * @return
	 */
	@RequestMapping("getPrice")
	@ResponseBody
	public JSONObject getPrice(
			@RequestParam(value = "cartIds") String cartIds,
			@RequestParam(value = "couponId",required=false,defaultValue="") String couponId,
			@RequestParam(value = "freight",required=false,defaultValue="") String freight,
			@RequestParam(value = "cityId",required=false,defaultValue="") String cityId,
			@RequestParam(value = "isPd",required=false,defaultValue="0") String isPd,
			@RequestParam(value = "memberId",required=false,defaultValue="0") Integer memberId
			){
		JSONObject jsonObj = new JSONObject();
		try {
			Map<String,Object> priceMap = cartService.queryTotalPrice(cartIds, freight, couponId, cityId, isPd, memberId);
			jsonObj.put("result", 1);
			jsonObj.put("data", JSONArray.fromObject(priceMap, JsonUtils.getJsonConfig()));
		} catch (Exception e) {
			log.error("加载运费API出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "无数据");
		}
		return jsonObj;
	}
	
	/**
	 * 获取购物车数量
	 * @param memberId
	 * @return
	 */
	@RequestMapping("cartCount")
	@ResponseBody
	public JSONObject cartCount(
			@RequestParam(value = "memberId") Integer memberId
			){
		jsonObj = new JSONObject();
		try {
			if(null == memberId){
				jsonObj.put("result", 1);
				jsonObj.put("msg", "会员id为空");
				jsonObj.put("data", "[]");
			}else{
				Integer cartCount = cartService.queryCountByMemberId(memberId);
				if(cartCount==null){
					cartCount = 0;
				}
				Map<String, Integer> map = new HashMap<String, Integer>(); 
				map.put("cartCount", cartCount);
				jsonObj.put("result", 1);
				jsonObj.put("msg", "获取成功");
				jsonObj.put("data", JSONArray.fromObject(map, JsonUtils.getJsonConfig()));
			}
		} catch (Exception e) {
			log.error("获取购物车数量出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		
		return jsonObj;
	}
}
