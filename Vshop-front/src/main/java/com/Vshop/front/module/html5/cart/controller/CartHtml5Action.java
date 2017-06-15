package com.Vshop.front.module.html5.cart.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.GoodsSpec;
import com.Vshop.core.entity.Order;
import com.Vshop.core.entity.base.Cart;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.Invoice;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.OrderGoods;
import com.Vshop.core.entity.base.OrderPay;
import com.Vshop.core.entity.vo.CartVo;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.cart.service.CartService;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.module.goods.service.GoodsSpecService;
import com.Vshop.service.module.trade.service.InvoiceService;
import com.Vshop.service.module.trade.service.OrderService;

/**
 * 项目名称：Vshop-front
 * 类名称：CartHtml5Action
 * 类描述：购物车
 * 创建人：zrh
 * 创建时间：2015年8月05日 上午11:29:35
 * 修改备注：
 */
@Controller
@RequestMapping("/m/authc/cart")

@Slf4j
public class CartHtml5Action {

    String message = "success";
    
    @Resource
    private CartService cartService;

    @Resource
    private GoodsService goodsService;
    
    @Resource
    private GoodsSpecService goodsSpecService;
    
    @Resource
    private OrderService orderService;
    
    @Autowired
	private InvoiceService invoiceService;
    
    /**
     * 购物车添加成功页跳转
     *
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     */
    @RequestMapping(value = "/cartSuccess")
    public ModelAndView cartSuccess(@RequestParam(value = "goodsId") Integer goodsId) {
        try {
            ModelAndView model = new ModelAndView("/html5/cart/cart_success");
            model.addObject("goodsId", goodsId);
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("卖家中心添加购物车成功页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }   

    /**
     * 购物车课程列表页跳转
     *
     * @return ModelAndView    返回类型
     * @throws RuntimeException
     */
    @RequestMapping(value = "/cartGoodsList")
    public ModelAndView cartGoodsList() {
        try {
        	ModelAndView model = new ModelAndView("/html5/cart/cart_goodslist");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("卖家中心购物车课程列表页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }   
    
    /**
     * ajax 添加购物车
     *
     * @param @param  goodsId
     * @param @param  count
     * @param @param  model
     * @param @return
     * @param @throws JsonGenerationException
     * @param @throws JsonMappingException
     * @param @throws Exception    设定文件
     * @return Map<String,String>    返回类型
     * @throws
     * @Title: saveCart
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    @RequestMapping(value = "/saveCart", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, String> saveCart(@RequestParam(value = "goodsId") String goodsId,
                                 @RequestParam(value = "count", defaultValue = "0") String count, 
                                 @RequestParam(value = "specId") Integer specId,
    							 HttpSession session) {

        Map<String, String> map = Maps.newHashMap();
        Goods goods = goodsService.findGoodById(Integer.valueOf(goodsId)); //课程信息
        GoodsSpec goodsSpec = goodsSpecService.findByGoodsSpecId(specId);
        //加载购物车标识,根据标识判断错误,默认为0,课程数量大于100
        int result = 0;
        //判断是否登录,登录存表,不登录将信息存入session
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
        	Integer memberId = CacheUtils.getCacheUser().getMember().getMemberId(); //用户id
        	result = cartService.saveCart(goods, memberId, Integer.valueOf(count) , goodsSpec, 0);
        }else{
        	//将课程信息存入cart实体中
        	Cart cart = cartService.copyGoodsToCart(goods, goodsSpec);
            cart.setGoodsNum(Short.valueOf(count)); //课程数量
            cart.setSpecId(specId);  //重新为规格赋值,原来值为默认规格值
            //将cart信息先放入超类中
            CartVo cartVo = (CartVo) session.getAttribute(Constants.CART_KEY);
            if (cartVo == null) {
                cartVo = new CartVo();
                cartVo.add(cart);
                result = 1;
            } else {
            	result = cartVo.update(cart);
            }
            //将超类放入session
            session.setAttribute(Constants.CART_KEY, cartService.getCartVoByCart(cartVo.getList()));
        }
        //判断加入购物车是否成功
        if(result==0){ //数量超过100
    		map.put(message, "false");
    		map.put("msg", "加入相同课程数量过多,请不购买不超过100件");
    	}else if(result==-1){
    		map.put(message, "false");
    		map.put("msg", "请勿购买自己学院下的课程");
    	}else{
    		map.put(message, "true");
    		map.put("goodsId", goodsId);
    	}
        return map;
    }
    
    /**
     * 课程详情，直接购买，
     * ---如果购物车有，现在直接购买 ， 就把购物车的数据值更新之后 显示出来，不然就变成了 把之前加载进去的数量都带进去了
     * @param goodsId 课程id
     * @param count 课程数量
     * @param specId 规格id
     * @return
     */
    @RequestMapping("/buyNow")
    @ResponseBody
    public Map<String,String> buyNow(@RequestParam(value = "goodsId") String goodsId,
    							  	 @RequestParam(value = "count", defaultValue = "0") String count, 
    							  	 @RequestParam(value = "specId") Integer specId
    								) {
        try {
        	Map<String,String> map = new HashMap<String, String>();
        	
            Goods goods = goodsService.findGoodById(Integer.valueOf(goodsId)); //课程信息
            GoodsSpec goodsSpec = goodsSpecService.findByGoodsSpecId(specId); //课程规格信息
            
            //加载购物车标识,根据标识判断错误,默认为0,课程数量大于100
            int result = 0;
            Integer memberId = CacheUtils.getCacheUser().getMember().getMemberId(); //用户id
        	
            result = cartService.saveCart(goods, memberId, Integer.valueOf(count) , goodsSpec, 1);
            
            //判断加入购物车是否成功
            if(result==0){ //数量超过100
        		map.put(message, "false");
        		map.put("msg", "加入相同课程数量过多,请不购买不超过100件");
        	}else if(result==-1){
        		map.put(message, "false");
        		map.put("msg", "请勿购买自己学院下的课程");
        	}else{
        		map.put(message, "true");
        		map.put("cartIds", result+"");
        	}
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("立即购买加入购物车失败！", e.toString());
            throw new RuntimeException("导航失败!");
        }
    }
    
    /**
     * 直接购买，跳转至结算页面
     * @param @param  cartIds
     * @param @return 设定文件
     * @return ModelAndView    返回类型
     * @throws
     * @Title: gotoOrder
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    @RequestMapping("/gotoOrder")
    public ModelAndView gotoOrder(@RequestParam(value = "cartIds") String cartIds) {
        try {
        	ModelAndView model = new ModelAndView("/html5/cart/cart_order");
        	
        	if (CacheUtils.getCacheUser().getStore()!=null){
        		String storeName =CacheUtils.getCacheUser().getStore().getStoreName();
                int storeId = CacheUtils.getCacheUser().getStore().getStoreId();
                model.addObject("storeName",storeName);
                model.addObject("storeId",storeId);
        	}else {
        		model.addObject("storeName","平台自营");
        	}
        
        	model.addObject("cartIds", cartIds);
        	return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("去结算地址页加载失败！", e.toString());
            throw new RuntimeException("导航失败!");
        }
    }
    
    /**
     * 更新购物车数量
     *
     * @param @param  cartId
     * @param @param  model
     * @param @return
     * @param @throws Exception    设定文件
     * @return Map<String,String>    返回类型
     * @throws
     * @Title: updateCartCount
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    @RequestMapping(value = "/updateCartCount", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> updateCart(@RequestParam(value = "cartId") String cartId,
                                   @RequestParam(value = "count") String count, 
                                   @RequestParam(value = "storeId") String storeId,
                                   @RequestParam(value = "specId") String specId,HttpSession session) throws Exception {

        Map<String, Object> map = Maps.newHashMap();
        try {
            //根据课程规格id查询课程规格
            GoodsSpec goodsSpec = goodsSpecService.findByGoodsSpecId(Integer.valueOf(specId));
            if(Integer.valueOf(count)>goodsSpec.getSpecGoodsStorage()){
            	map.put("success", false);
                map.put("msg", "课程库存不足!");
            }else{
            	//修改购物车数量
                cartService.updatecart(Integer.valueOf(cartId), Integer.valueOf(count));
                //根据用户id查询用户购物车集合
                List<Cart> list = cartService.queryBuyCart(CacheUtils.getCacheUser().getMember().getMemberId());
                map.put("result", cartService.getCartVoByCart(list));
                map.put("success", true);
                map.put("msg", "修改购物车成功");
            }
        } catch (Exception e) {
        	e.printStackTrace();
            log.error("修改购物车出错", e.getMessage());
            map.put("success", false);
            map.put("msg", "修改购物车出错");
        }
        return map;
    }
    
    /**
     * 删除购物车数据,列表删除根据id,批量删除
     *
     * @param @param  cartId
     * @param @param  model
     * @param @return
     * @param @throws Exception    设定文件
     * @return Map<String,String>    返回类型
     * @throws
     * @Title: deletecart
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> delete(@RequestParam(value = "cartId") String cartId,
            HttpSession session) throws Exception {
    	Map<String, Object> map = Maps.newHashMap();
    	try {
    		String[] cartIds = cartId.split(",");
    		for(String id : cartIds){
    			cartService.deleteCart(Integer.valueOf(id));
    		}
            map.put("success", true);
        } catch (Exception e) {
        	map.put("success", false);
            e.printStackTrace();
            log.error("卖家中心首页加载失败！");
            throw new RuntimeException("导航失败!");
        }
        return map;
    }
    
    /**
     * 购物车 结算 显示订单页
     *
     * @param @param  cartId 为多个选中的购物车id组成的字符串,中间以逗号分隔
     * @param @return 设定文件
     * @return ModelAndView    返回类型
     * @throws
     * @Title: cartOrder
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    @RequestMapping("/cartOrder")
	public ModelAndView cartOrder(@RequestParam(value = "cartIds") String cartIds,
			@RequestParam(value = "addressId", required = false, defaultValue = "") String addressId,
			@RequestParam(value = "invoiceId", required = false, defaultValue = "0") Integer invoiceId,
			HttpSession session) {
		try {
            ModelAndView model = new ModelAndView("/html5/cart/cart_order");
            model.addObject("cartIds", cartIds);
            model.addObject("addressId", addressId);
            model.addObject("invoiceId", invoiceId);
            
			if (invoiceId > 0) {
				Invoice invoice = invoiceService.findByInvId(invoiceId);
				if (invoice != null) {
					model.addObject("invoiceStr", invoice.getInvTitle() + "-" + invoice.getInvContent());
				}
				model.addObject("invoice", invoice);
			}
            
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("去结算地址页加载失败！", e.toString());
            throw new RuntimeException("导航失败!");
        }
    }
    
    /**
     * 表单提交之前验证,验证课程的库存或者课程价格变动
     * @param cartIds
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/orderVal", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> orderVal(@RequestParam(value = "cartIds") String cartIds) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	//验证课程库存或者课程价格变动,Map<String,Object>,键为类型:understock:库存不足,pricechange:价格变动
    	
    	
    	//System.out.println("================"+cartIds);
    	
    	try {
    		
    		 Map<String, Object> valMap = cartService.orderValidation(cartIds);
        //如果两个list有数据返回map
        if(((List<Cart>)valMap.get("understock")).size()!=0 || ((List<Cart>) valMap.get("pricechange")).size()!=0){
        	map.put("result", valMap);
            map.put("success", true);
        }else{
        	map.put("success", false);
        }} catch (Exception e) {
        	map.put("result", "over");
        	map.put("success", true);
            e.printStackTrace();
          
        }
        return map;
    }

    /**
     * 提交订单
     * @param @param  cartId
     * @param @param  model
     * @param @return
     * @param  invoiceId 发票信息方法,选择发票传值,未选择不传或传""
     * @param @throws Exception    设定文件
     * @return Map<String,String>    返回类型
     * @throws
     * @Title: subOrder
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    @RequestMapping(value = "/subOrder", method = RequestMethod.POST)
    public ModelAndView subOrder(@RequestParam(value = "cartIds") String cartIds, 
    							@RequestParam(value = "storeId") String[] storeIds,
    							@RequestParam(value = "address_options") String addressId,
    							@RequestParam(value = "paytype") String paytype,
    							@RequestParam(value = "couponId",required=false,defaultValue="") String couponId,
    							@RequestParam(value = "invoiceId",required=false,defaultValue="") String invoiceId,
    							@RequestParam(value = "transport_type",required=false,defaultValue="") String[] transportType,
    							@RequestParam(value = "isPd",required=false,defaultValue="0") Integer isPd,
    							HttpServletRequest request,HttpSession session) throws Exception {
    	ModelAndView model = new ModelAndView("/html5/buyer/order_pay_view");
    	
    	//获取订单学员备注信息
    	Map<Integer,String> map = new HashMap<Integer, String>();
    	for(String storeId:storeIds){
    		map.put(Integer.valueOf(storeId), request.getParameter("orderMessage_"+storeId));
    	}
    	
    	//获取用户信息
    	Member member = CacheUtils.getCacheUser().getMember();
    	
    	//新建一个运费信息
    	String freight = "";
    	//转换运费信息格式
    	for(String str:transportType){
    		freight += str+",";
    	}
    	
    	//提交订单,返回订单支付实体
    	////提交订单,返回订单支付实体
    	OrderPay orderPay = new OrderPay();
    	if (CacheUtils.getCacheUser().getStore()!=null){
    		 orderPay = orderService.addOrderReturnPaySn(CacheUtils.getCacheUser().getStore().getStoreId(),cartIds, member.getMemberId(), map, Integer.valueOf(addressId), paytype, freight, couponId, invoiceId, isPd);
        	
    	}else {
    		 orderPay = orderService.addOrderReturnPaySn(cartIds, member.getMemberId(), map, Integer.valueOf(addressId), paytype, freight, couponId, invoiceId, isPd);
        	
    	}
    	
    	//根据payId查询订单列表
    	List<Order> orderList = orderService.findByPayId(orderPay.getPayId()); 
    	String goodsNames = ""; //所有课程名称
    	Double ordersAmount = 0.00; //应付金额
    	for(Order order : orderList){
    		for(OrderGoods orderGoods : order.getOrderGoodsList()){
    			goodsNames += orderGoods.getGoodsName()+"&nbsp;&nbsp;&nbsp;&nbsp;";
    		}
    		ordersAmount += order.getOrderAmount().doubleValue();
    	}
    	//paytype=2
    	
    	if (paytype.equals("2")){
    		 model = new ModelAndView("/html5/buyer/order_success");
    	}
    	
    	System.out.println("================="+ordersAmount);
    	model.addObject("member", member);
    	model.addObject("paytype", paytype);
    	model.addObject("orderPaySn", orderPay.getPaySn());
    	model.addObject("orderList", orderList);
    	model.addObject("goodsNames", goodsNames);
    	model.addObject("ordersAmount", ordersAmount);
    	return model;
    }
   
    /**
     * 加载运费
     * @param storeIds
     * @param cityId
     * @return
     */
    @RequestMapping("/addShipping")
    @ResponseBody
    public Map<String,Object> addShipping(@RequestParam(value = "cartIds") String cartIds,
    									  @RequestParam(value = "cityId",required=false,defaultValue="") String cityId){
    	Map<String,Object> map = new HashMap<String, Object>();
    	try{
    		Map<String,Object> storeMap = new HashMap<String, Object>();
    		//判断城市id是否存在
    		if(StringUtils.isNotBlank(cityId)){ //若存在传值
    			storeMap = cartService.queryFreightByCartIds(Integer.valueOf(cityId), cartIds);
    		}else{ //不在传null
    			storeMap = cartService.queryFreightByCartIds(null, cartIds);
    		}
    		
        	map.put("result", storeMap);
    		map.put("success", true);
    	}catch (Exception e) {
    		map.put("success", false);
    		e.printStackTrace();
    	}
		return map;
    };
    
    /**
     * 计算订单应付金额
     * @param cartIds 多个购物车id
     * @param cityId 城市id
     * @param freight 运费信息
     * @param couponId 优惠券id
     * @return
     */
    @RequestMapping("/getTotalPrice")
    @ResponseBody
    public Map<String,Object> getTotalPrice(@RequestParam(value = "cartIds") String cartIds,
    									@RequestParam(value = "couponId",required=false,defaultValue="") String couponId,
    									@RequestParam(value = "freight",required=false,defaultValue="") String freight,
    									@RequestParam(value = "cityId",required=false,defaultValue="") String cityId,
    									@RequestParam(value = "isPd",required=false,defaultValue="0") String isPd
    									){
    	Map<String,Object> map = new HashMap<String, Object>();
    	try{
//    		Map<String,Object> priceMap = cartService.queryTotalPrice(cartIds, freight, couponId, cityId, 0+"", 0);
    		Map<String,Object> priceMap = cartService.queryTotalPrice(cartIds, freight, couponId, cityId, isPd, CacheUtils.getCacheUser().getMember().getMemberId());
    		map.put("result", priceMap);
        	map.put("success", true);
    	}catch (Exception e) {
    		map.put("success", false);
    		e.printStackTrace();
    	}
		return map;
    };

    
}