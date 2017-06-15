package com.Vshop.service.module.trade.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jersey.repackaged.com.google.common.base.Joiner;
import jersey.repackaged.com.google.common.collect.Lists;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;
import com.Vshop.core.common.DateUtils;
import com.Vshop.core.common.MyBeanUtils;
import com.Vshop.core.common.NumberUtils;
import com.Vshop.core.entity.GoodsSpec;
import com.Vshop.core.entity.Order;
import com.Vshop.core.entity.base.Address;
import com.Vshop.core.entity.base.Cart;
import com.Vshop.core.entity.base.ConsumerCodeSend;
import com.Vshop.core.entity.base.Coupon;
import com.Vshop.core.entity.base.CouponMember;
import com.Vshop.core.entity.base.Daddress;
import com.Vshop.core.entity.base.Express;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.Invoice;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.OrderAddress;
import com.Vshop.core.entity.base.OrderDaddress;
import com.Vshop.core.entity.base.OrderGoods;
import com.Vshop.core.entity.base.OrderInvoice;
import com.Vshop.core.entity.base.OrderLog;
import com.Vshop.core.entity.base.OrderPay;
import com.Vshop.core.entity.base.Payment;
import com.Vshop.core.entity.base.PredepositLog;
import com.Vshop.core.entity.base.RefundLog;
import com.Vshop.core.entity.base.ReturnGoods;
import com.Vshop.core.entity.base.ReturnOrder;
import com.Vshop.core.entity.base.ShopPointsLog;
import com.Vshop.core.entity.vo.CartVo;
import com.Vshop.core.entity.vo.OrderVo;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.service.module.calculate.service.CalculateService;
import com.Vshop.service.module.cart.dao.CartDao;
import com.Vshop.service.module.cart.service.AddressService;
import com.Vshop.service.module.cart.service.CartService;
import com.Vshop.service.module.cart.service.OrderPayService;
import com.Vshop.service.module.coupon.service.CouponMemberService;
import com.Vshop.service.module.coupon.service.CouponService;
import com.Vshop.service.module.goods.service.ConsumerCodeSendService;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.module.goods.service.GoodsSpecService;
import com.Vshop.service.module.member.common.PointsLogType;
import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.service.module.member.service.ShopPointsLogService;
import com.Vshop.service.module.product.service.ProductService;
import com.Vshop.service.module.push.ServiceMessagePush;
import com.Vshop.service.module.setting.service.ExpressService;
import com.Vshop.service.module.setting.service.PaymentService;
import com.Vshop.service.module.setting.service.SettingService;
import com.Vshop.service.module.store.service.StoreService;
import com.Vshop.service.module.strategy.common.StrategyCondition;
import com.Vshop.service.module.trade.common.OrderState;
import com.Vshop.service.module.trade.dao.OrderDao;
import com.Vshop.service.module.trade.dao.OrderGoodsDao;
import com.Vshop.service.module.trade.dao.OrderLogDao;
import com.Vshop.service.module.trade.service.DaddressService;
import com.Vshop.service.module.trade.service.InvoiceService;
import com.Vshop.service.module.trade.service.OrderAddressService;
import com.Vshop.service.module.trade.service.OrderDaddressService;
import com.Vshop.service.module.trade.service.OrderInvoiceService;
import com.Vshop.service.module.trade.service.OrderService;
import com.Vshop.service.module.trade.service.PredepositLogService;
import com.Vshop.service.module.trade.service.RefundLogService;
import com.Vshop.service.module.trade.service.ReturnGoodsService;
import com.Vshop.service.module.trade.service.ReturnOrderService;
import com.Vshop.service.module.trade.service.TransportService;
import com.Vshop.service.sms.sender.Sender;
import com.Vshop.service.sms.sender.client.Message;
import com.Vshop.service.utils.page.Pager;

/**
 * 订单
 * @author liukai
 */

@Service
public class OrderServiceImpl implements OrderService{
	
	@Resource
	private OrderDao orderDao;
	
	@Resource
	private OrderGoodsDao orderGoodsDao;
	
	@Resource
	private OrderLogDao orderLogDao;
	
	@Resource
	private CartDao cartDao;
	
	@Resource
	private GoodsSpecService goodsSpecService;
	
	@Resource
	private OrderPayService orderPayService;
	
	@Resource 
	private ProductService productService;
	
	@Resource
	private RefundLogService refundLogService;
	
	@Resource
	private ReturnOrderService returnOrderService;
	
	@Resource
	private ReturnGoodsService returnGoodsService;
	
	@Resource  
	private PaymentService paymentService;
	
	@Resource
	private AddressService addressService;
	
	@Resource
	private TransportService transportService;
	
	@Resource
	private CouponService couponService;
	
	@Resource
	private CalculateService calculateService;
	
	@Resource
	private CartService cartService;
	
	@Resource
	private MemberService memberService;
	
	@Resource
	private ShopPointsLogService shopPointsLogService;
	
	@Resource
	private CouponMemberService couponMemberService;
	
	@Resource
	private InvoiceService invoiceService;
	
	@Resource
	private OrderInvoiceService orderInvoiceService;
	
	@Resource
	private ExpressService expressService;
	
	@Resource
	private PredepositLogService predepositLogService;
	
	@Resource
	private GoodsService goodsService;
	
	@Resource
	private SettingService settingService;
	
	@Resource
	private OrderAddressService orderAddressService;
	
	@Resource
	private DaddressService daddressService;
	
	@Resource
	private OrderDaddressService orderDaddressService;
	
	@Resource
	private ConsumerCodeSendService consumerCodeSendService;
	
	@Resource
	private StoreService storeService;
	
	/**
	 * 删除订单
	 * @param orderId 订单id
	 */
	public void deleteOrder(Integer orderId){
		orderDao.deleteOrder(orderId);
		//删除订单的订单项
		orderGoodsDao.deleteByOrderId(orderId);
	}
	
	/**
	 * 修改订单
	 * @param order
	 */
	@Override
	public void updateOrder(Order order) {
		orderDao.updateOrder(order);
	}
	
	/**
	 * 修改订单状态
	 * @param order 订单实体,需orderaId,orderState字段,如果需要修改付款状态,需给paymentState字段赋值,
	 * 同时可以传入finnshedTime和shippingTime来更改订单完成和配送时间
	 * @param orderLog 订单日志,可传可不传,传可保存订单日志,不传不保存
	 */
	@Override
	public void updateOrderState(Order order,OrderLog orderLog) {
		orderDao.updateOrderState(order);
		//如果有订单日志,保存订单日志表
		if(orderLog != null){
			orderLog.setCreateTime(System.currentTimeMillis());
			orderLogDao.saveOrderLog(orderLog);
		}
	}
	
	/**
	 * 分页查询订单总条数
	 * @param order
	 * 		可加查询条件:订单编号,店铺名称,订单状态,买家名称,支付名称编号,开始结束时间(starttime,endtime),店铺id,买家id
	 * @return
	 */
	@Override
	public int findOrderCount(Order order) {
		return orderDao.findOrderCount(order);
	}
	
	/**
	 * 分页查询订单
	 * @param pager
	 * 		可加查询条件:订单编号,店铺名称,订单状态,买家名称,支付名称编号,开始结束时间(starttime,endtime),店铺id,买家id
	 * @return
	 */
	@Override
	public List<Order> findOrderList(Pager pager) {
		return orderDao.findOrderList(pager);
	}
	
	/**
	 * 根据id查询订单,有订单项,订单日志
	 * @param orderId
	 * @return
	 */
	@Override
	public Order findById(Integer orderId) {
		return orderDao.findById(orderId);
	}
	
	/**
	 * 订单详情,必传订单id,可传用户id和店铺id,不需要传null
	 * @param orderId 订单id
	 * @param buyerId 用户id
	 * @param storeId 店铺id
	 * @return
	 */
	public Order findOrderDetail(Integer orderId,Integer buyerId,Integer storeId){
		Order order = new Order();
		order.setOrderId(orderId);
		if(buyerId!=null){
			order.setBuyerId(buyerId);
		}
		if(storeId!=null){
			order.setStoreId(storeId);
		}
		return orderDao.findOrderDetail(order);
	}
	
	/**
	 * 根据订单编号查询订单信息
	 * @param orderSn
	 * @return
	 */
	@Override
	public Order findByOrderSn(String orderSn) {
		return orderDao.findByOrderSn(orderSn);
	}
	
	
	/**
	 * 提交订单
	 * @param cartIds 多个购物车id
	 * @param memberId 用户id
	 * @param map 存储买家留言信息,键为店铺id,值为店铺留言
	 * @param addressId 收货地址id
	 * @param paytype 支付方式 1:在线支付,2:货到付款
	 * @param freight 运费信息
	 * @param couponId 优惠券id
	 * @param invoiceId 发票id
	 * @param isPd 是否余额支付 1为是
	 * @return 返回OrderPay 订单支付表
	 */
	@Override
	public OrderPay addOrderReturnPaySn(String cartIds,Integer memberId,Map<Integer,String> map,Integer addressId,String paytype, String freight, String couponId, String invoiceId, Integer isPd){
		try{
			//通过用户id查询用户信息
			Member member = memberService.findById(memberId);
			//创建一个新的订单支付编号
			String paySn = "P"+DateUtils.getDateStr("yyyyMMddHHmmssSSS");
			OrderPay orderPay = new OrderPay();
			orderPay.setPaySn(paySn);
			orderPay.setBuyerId(member.getMemberId());
			orderPay.setApiPayState("0");
			//保存订单支付表
			orderPayService.saveOrderPay(orderPay);
			
			/**
			 * 订单发票信息
			 */
			//新建一个订单发票
			OrderInvoice orderInvoice = new OrderInvoice();
			//新建一个订单表
			Invoice invoice = null;
			//判断买家是否选择填写发票信息
			if(StringUtils.isNotBlank(invoiceId)){  //存在,查表
				invoice = invoiceService.findByInvId(Integer.valueOf(invoiceId));
			}
			//判断发票信息是否存在
			if(invoice!=null){
				MyBeanUtils.copyBeanNotNull2Bean(invoice, orderInvoice);
			}else{ //不存在新增信息
				orderInvoice.setInvTitle("个人"); //订单发票台头
				orderInvoice.setMemberId(member.getMemberId()); //订单发票用户id
				orderInvoice.setInvContent("1"); //订单发票内容
				orderInvoice.setInvState("1"); //订单发票类型 1:普通发票信息
			}
			//新建一个发票信息字符串
			String invStr = "";
			//判断发票类型
			if(orderInvoice.getInvState().equals("1")){ //普通发票
				invStr += "普通发票&nbsp;&nbsp;" + orderInvoice.getInvTitle()+"&nbsp;&nbsp;";
			}else if(orderInvoice.getInvState().equals("2")){ //增值税发票
				invStr += "增值税发票&nbsp;&nbsp;" + orderInvoice.getInvCompany()+"&nbsp;&nbsp;";
			}
			//判断发票内容
			if(orderInvoice.getInvContent().equals("1")){ //不开发票
				invStr += "不开发票";
			}else if(orderInvoice.getInvContent().equals("2")){ //明细
				invStr += "明细";
			}
			
			/**
			 * 订单优惠券gt
			 */
			//新建一个新的优惠券
			Coupon coupon = null;
			//判断是否使用优惠券
			if(StringUtils.isNotBlank(couponId)&&Integer.valueOf(couponId)!=0){ //使用优惠券,查表
				coupon = couponService.getCouponById(Integer.valueOf(couponId));
			}
			
			/**
			 * 保存订单收货地址
			 */
			//查询收货地址信息
			Address address = addressService.queryById(addressId);
			OrderAddress orderAddress = new OrderAddress();
			MyBeanUtils.copyBeanNotNull2Bean(address, orderAddress);
			orderAddressService.saveOrderAddress(orderAddress);
			
			//计算有订单的相关金额
			List<OrderVo> orderVoList = this.getAmount(cartIds, coupon, orderAddress, freight, isPd, member);
			
			/**
			 * 订单信息
			 */
			for(OrderVo orderVo :orderVoList){
				Order order = new Order();
				order.setOrderSn(DateUtils.getDateStr("yyyyMMddHHmmssSSS")); //订单编号
				order.setBuyerId(member.getMemberId()); //购买用户id
				order.setStoreId(orderVo.getStoreId()); //店铺id
				order.setStoreName(orderVo.getStoreName()); //店铺名称
				order.setBuyerEmail(member.getMemberEmail()); //购买用户邮箱
				order.setBuyerName(member.getMemberName()); //购买用户名称
				order.setDaddressId(0); //发货地址id,暂时写死
				order.setAddressId(orderAddress.getAddressId()); //收货地址id
				order.setGoodsAmount(orderVo.getGoodsAmount()); //商品总价格
				order.setShippingFee(orderVo.getShippingFee()); //运费金额
				order.setPredepositAmount(orderVo.getPredepositAmount()); //余额支付金额
				order.setOrderAmount(orderVo.getOrderAmount()); //订单应付金额
				order.setOrderTotalPrice(orderVo.getGoodsAmount().add(orderVo.getShippingFee())); //订单总金额=商品总金额+运费
				order.setOrderMessage(map.get(orderVo.getStoreId())); //订单留言
				order.setOrderType(OrderState.ORDER_TYPE_ORDINARY); //订单类型 0.普通 1.团购
				order.setPaymentCode(paytype); //支付方式名称代码
				order.setPaymentDirect("1"); //支付类型:1是即时到帐,2是担保交易
				order.setPaymentId(Integer.valueOf(paytype)); //支付方式id
				order.setPayId(orderPay.getPayId()); //支付表id
				order.setPaySn(paySn); //支付表编号
				if("1".equals(paytype)){
					order.setPaymentName("在线支付"); //支付方式名称
					//判断有没有剩余支付金额
					if(orderVo.getOrderAmount().doubleValue()==0){ //若支付完成
						order.setOrderState(OrderState.ORDER_STATE_UNFILLED); //订单状态：0:已取消;10:待付款;20:待发货;30:待收货;40:交易完成;50:已提交;60:已确认;
					}else{ //未支付完成
						order.setOrderState(OrderState.ORDER_STATE_NO_PATMENT); //订单状态：0:已取消;10:待付款;20:待发货;30:待收货;40:交易完成;50:已提交;60:已确认;
					}	
				}else if("2".equals(paytype)){
					order.setPaymentName("货到付款"); //支付方式名称
					//判断有没有剩余支付金额
					if(orderVo.getOrderAmount().doubleValue()==0){ //若支付完成
						order.setOrderState(OrderState.ORDER_STATE_UNFILLED); //订单状态：0:已取消;10:待付款;20:待发货;30:待收货;40:交易完成;50:已提交;60:已确认;
					}else{ //未支付完成
						order.setOrderState(OrderState.ORDER_STATE_SUBMIT); //订单状态：0:已取消;10:待付款;20:待发货;30:待收货;40:交易完成;50:已提交;60:已确认;
					}	
				}
				//判断有没有剩余支付金额
				if(orderVo.getOrderAmount().doubleValue()==0){ //若支付完成
					order.setPaymentState(OrderState.PAYMENT_STATE_YES); //付款状态
				}else{ //未支付完成
					order.setPaymentState(OrderState.PAYMENT_STATE_NO); //付款状态
				}
				
				order.setOutSn(""); //订单编号，外部支付时使用，有些外部支付系统要求特定的订单编号
				order.setInvoice(invStr); //订单发票信息
				order.setCreateTime(System.currentTimeMillis()); //订单生成时间
				//保存订单表
				orderDao.saveOrder(order); 
				
				for(Cart cart : orderVo.getList()){
					OrderGoods orderGoods = new OrderGoods();
					//查询商品规格表,实时查找信息
					GoodsSpec goodsSpec = goodsSpecService.findByGoodsSpecId(cart.getSpecId());
					orderGoods.setGoodsId(cart.getGoodsId());
					orderGoods.setGoodsImage(cart.getGoodsImages());
					orderGoods.setGoodsName(cart.getGoodsName());
					orderGoods.setGoodsNum(cart.getGoodsNum().intValue());
					orderGoods.setGoodsPrice(goodsSpec.getSpecGoodsPrice());
					orderGoods.setOrderId(order.getOrderId());
					orderGoods.setSpecId(cart.getSpecId());
					orderGoods.setSpecInfo(cart.getSpecInfo());
					orderGoods.setStoreId(cart.getStoreId());
					
					//减去商品库存
					goodsSpec.setSpecSalenum(cart.getGoodsNum().intValue());
					productService.updateStorage(goodsSpec);
					orderGoodsDao.saveOrderGoods(orderGoods);
					cartDao.deleteCart(cart.getCartId()); //删除购物车数据
				}
				
				OrderLog orderLog = new OrderLog();
				orderLog.setOperator(member.getMemberName());
				orderLog.setChangeState("未发货");
				orderLog.setOrderId(order.getOrderId());
				orderLog.setOrderState("未付款");
				orderLog.setStateInfo("提交订单");
				orderLog.setCreateTime(System.currentTimeMillis());
				//保存订单日志
				orderLogDao.saveOrderLog(orderLog);
				
				orderInvoice.setOrderId(order.getOrderId());
				//保存订单发票表
				orderInvoiceService.saveOrderInvoice(orderInvoice);
			}
			
			/**
			 * 修改优惠券使用情况
			 */
			//判断是否使用优惠券
			if(coupon!=null){
				int couponusage = coupon.getCouponusage(); //使用数量
				coupon.setCouponusage(couponusage+1); //使用数量加1
				//修改优惠券表
				couponService.updateCoupon(coupon);
				
				CouponMember couponMember = new CouponMember();
				couponMember.setCouponId(Integer.valueOf(couponId));
				couponMember.setCouponMemberId(member.getMemberId());
				couponMember.setCouponIsUser(1); //优惠券已使用
				//修改中间表
				couponMemberService.updateCouponMember(couponMember);
			}
			
			return orderPay;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 提交订单
	 * @param cartIds 多个购物车id
	 * @param memberId 用户id
	 * @param map 存储买家留言信息,键为店铺id,值为店铺留言
	 * @param addressId 收货地址id
	 * @param paytype 支付方式 1:在线支付,2:货到付款
	 * @param freight 运费信息
	 * @param couponId 优惠券id
	 * @param invoiceId 发票id
	 * @param isPd 是否余额支付 1为是
	 * @return 返回OrderPay 订单支付表
	 */
	@Override
	public OrderPay addOrderReturnPaySn(Integer storeId,String cartIds,Integer memberId,Map<Integer,String> map,Integer addressId,String paytype, String freight, String couponId, String invoiceId, Integer isPd){
		try{
			//通过用户id查询用户信息
			Member member = memberService.findById(memberId);
			//创建一个新的订单支付编号
			String paySn = "P"+DateUtils.getDateStr("yyyyMMddHHmmssSSS");
			OrderPay orderPay = new OrderPay();
			orderPay.setPaySn(paySn);
			orderPay.setBuyerId(member.getMemberId());
			orderPay.setApiPayState("0");
			//保存订单支付表
			orderPayService.saveOrderPay(orderPay);
			
			/**
			 * 订单发票信息
			 */
			//新建一个订单发票
			OrderInvoice orderInvoice = new OrderInvoice();
			//新建一个订单表
			Invoice invoice = null;
			//判断买家是否选择填写发票信息
			if(StringUtils.isNotBlank(invoiceId)){  //存在,查表
				invoice = invoiceService.findByInvId(Integer.valueOf(invoiceId));
			}
			//判断发票信息是否存在
			if(invoice!=null){
				MyBeanUtils.copyBeanNotNull2Bean(invoice, orderInvoice);
			}else{ //不存在新增信息
				orderInvoice.setInvTitle("个人"); //订单发票台头
				orderInvoice.setMemberId(member.getMemberId()); //订单发票用户id
				orderInvoice.setInvContent("1"); //订单发票内容
				orderInvoice.setInvState("1"); //订单发票类型 1:普通发票信息
			}
			//新建一个发票信息字符串
			String invStr = "";
			//判断发票类型
			if(orderInvoice.getInvState().equals("1")){ //普通发票
				invStr += "普通发票&nbsp;&nbsp;" + orderInvoice.getInvTitle()+"&nbsp;&nbsp;";
			}else if(orderInvoice.getInvState().equals("2")){ //增值税发票
				invStr += "增值税发票&nbsp;&nbsp;" + orderInvoice.getInvCompany()+"&nbsp;&nbsp;";
			}
			//判断发票内容
			if(orderInvoice.getInvContent().equals("1")){ //不开发票
				invStr += "不开发票";
			}else if(orderInvoice.getInvContent().equals("2")){ //明细
				invStr += "明细";
			}
			
			/**
			 * 订单优惠券gt
			 */
			//新建一个新的优惠券
			Coupon coupon = null;
			//判断是否使用优惠券
			if(StringUtils.isNotBlank(couponId)&&Integer.valueOf(couponId)!=0){ //使用优惠券,查表
				coupon = couponService.getCouponById(Integer.valueOf(couponId));
			}
			
			/**
			 * 保存订单收货地址
			 */
			//查询收货地址信息
			Address address = addressService.queryById(addressId);
			OrderAddress orderAddress = new OrderAddress();
			MyBeanUtils.copyBeanNotNull2Bean(address, orderAddress);
			orderAddressService.saveOrderAddress(orderAddress);
			
			//计算有订单的相关金额
			List<OrderVo> orderVoList = this.getAmount(cartIds, coupon, orderAddress, freight, isPd, member);
			
			/**
			 * 订单信息
			 */
			for(OrderVo orderVo :orderVoList){
				Order order = new Order();
				order.setOrderSn(DateUtils.getDateStr("yyyyMMddHHmmssSSS")); //订单编号
				order.setBuyerId(member.getMemberId()); //购买用户id
				
				
				order.setStoreId(orderVo.getStoreId()); //店铺id
				
//				order.setStoreId(storeId); //店铺id
				
				//storeService.findById(java.lang.Integer.valueOf(storeId)).getStoreName();
				order.setStoreName(orderVo.getStoreName()); //店铺名称
//				order.setStoreName(storeService.findById(java.lang.Integer.valueOf(storeId)).getStoreName());
				order.setBuyerEmail(member.getMemberEmail()); //购买用户邮箱
				order.setBuyerName(member.getMemberName()); //购买用户名称
				order.setDaddressId(0); //发货地址id,暂时写死
				order.setAddressId(orderAddress.getAddressId()); //收货地址id
				order.setGoodsAmount(orderVo.getGoodsAmount()); //商品总价格
				order.setShippingFee(orderVo.getShippingFee()); //运费金额
				order.setPredepositAmount(orderVo.getPredepositAmount()); //余额支付金额
				order.setOrderAmount(orderVo.getOrderAmount()); //订单应付金额
				order.setOrderTotalPrice(orderVo.getGoodsAmount().add(orderVo.getShippingFee())); //订单总金额=商品总金额+运费
				order.setOrderMessage(map.get(orderVo.getStoreId())); //订单留言
				order.setOrderType(OrderState.ORDER_TYPE_ORDINARY); //订单类型 0.普通 1.团购
				order.setPaymentCode(paytype); //支付方式名称代码
				order.setPaymentDirect("1"); //支付类型:1是即时到帐,2是担保交易
				order.setPaymentId(Integer.valueOf(paytype)); //支付方式id
				order.setPayId(orderPay.getPayId()); //支付表id
				order.setPaySn(paySn); //支付表编号
				if("1".equals(paytype)){
					order.setPaymentName("在线支付"); //支付方式名称
					//判断有没有剩余支付金额
					if(orderVo.getOrderAmount().doubleValue()==0){ //若支付完成
						order.setOrderState(OrderState.ORDER_STATE_UNFILLED); //订单状态：0:已取消;10:待付款;20:待发货;30:待收货;40:交易完成;50:已提交;60:已确认;
					}else{ //未支付完成
						order.setOrderState(OrderState.ORDER_STATE_NO_PATMENT); //订单状态：0:已取消;10:待付款;20:待发货;30:待收货;40:交易完成;50:已提交;60:已确认;
					}	
				}else if("2".equals(paytype)){
					order.setPaymentName("货到付款"); //支付方式名称
					//判断有没有剩余支付金额
					if(orderVo.getOrderAmount().doubleValue()==0){ //若支付完成
						order.setOrderState(OrderState.ORDER_STATE_UNFILLED); //订单状态：0:已取消;10:待付款;20:待发货;30:待收货;40:交易完成;50:已提交;60:已确认;
					}else{ //未支付完成
						order.setOrderState(OrderState.ORDER_STATE_SUBMIT); //订单状态：0:已取消;10:待付款;20:待发货;30:待收货;40:交易完成;50:已提交;60:已确认;
					}	
				}
				//判断有没有剩余支付金额
				if(orderVo.getOrderAmount().doubleValue()==0){ //若支付完成
					order.setPaymentState(OrderState.PAYMENT_STATE_YES); //付款状态
				}else{ //未支付完成
					order.setPaymentState(OrderState.PAYMENT_STATE_NO); //付款状态
				}
				
				order.setOutSn(""); //订单编号，外部支付时使用，有些外部支付系统要求特定的订单编号
				order.setInvoice(invStr); //订单发票信息
				order.setCreateTime(System.currentTimeMillis()); //订单生成时间
				//保存订单表
				orderDao.saveOrder(order); 
				
				for(Cart cart : orderVo.getList()){
					OrderGoods orderGoods = new OrderGoods();
					//查询商品规格表,实时查找信息
					GoodsSpec goodsSpec = goodsSpecService.findByGoodsSpecId(cart.getSpecId());
					orderGoods.setGoodsId(cart.getGoodsId());
					orderGoods.setGoodsImage(cart.getGoodsImages());
					orderGoods.setGoodsName(cart.getGoodsName());
					orderGoods.setGoodsNum(cart.getGoodsNum().intValue());
					orderGoods.setGoodsPrice(goodsSpec.getSpecGoodsPrice());
					orderGoods.setOrderId(order.getOrderId());
					orderGoods.setSpecId(cart.getSpecId());
					orderGoods.setSpecInfo(cart.getSpecInfo());
					orderGoods.setStoreId(cart.getStoreId());
					
					//减去商品库存
					goodsSpec.setSpecSalenum(cart.getGoodsNum().intValue());
					productService.updateStorage(goodsSpec);
					orderGoodsDao.saveOrderGoods(orderGoods);
					cartDao.deleteCart(cart.getCartId()); //删除购物车数据
				}
				
				OrderLog orderLog = new OrderLog();
				orderLog.setOperator(member.getMemberName());
				orderLog.setChangeState("未发货");
				orderLog.setOrderId(order.getOrderId());
				orderLog.setOrderState("未付款");
				orderLog.setStateInfo("提交订单");
				orderLog.setCreateTime(System.currentTimeMillis());
				//保存订单日志
				orderLogDao.saveOrderLog(orderLog);
				
				orderInvoice.setOrderId(order.getOrderId());
				//保存订单发票表
				orderInvoiceService.saveOrderInvoice(orderInvoice);
			}
			
			/**
			 * 修改优惠券使用情况
			 */
			//判断是否使用优惠券
			if(coupon!=null){
				int couponusage = coupon.getCouponusage(); //使用数量
				coupon.setCouponusage(couponusage+1); //使用数量加1
				//修改优惠券表
				couponService.updateCoupon(coupon);
				
				CouponMember couponMember = new CouponMember();
				couponMember.setCouponId(Integer.valueOf(couponId));
				couponMember.setCouponMemberId(member.getMemberId());
				couponMember.setCouponIsUser(1); //优惠券已使用
				//修改中间表
				couponMemberService.updateCouponMember(couponMember);
			}
			
			return orderPay;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 根据订单编号修改订单状态和付款状态
	 * @param orderSn 订单编号
	 * @param orderState 订单状态
	 * @param paymentState 付款状态
	 * @param paymentTime 付款时间
	 */
	public void updateOrderStateByOrderSn(String orderSn, Integer orderState, Integer paymentState, Long paymentTime){
		orderDao.updateOrderStateByOrderSn(orderSn, orderState, paymentState, paymentTime);
	}
	
	/**
	 * 计算订单总价格,数据库中数据
	 * @param cartVo
	 * @return
	 */
	@Override
	public BigDecimal findAmount(CartVo cartVo) {
		double goodsAmount = 0.00;
		for(Cart cart:cartVo.getList()){
			GoodsSpec goodsSpec = goodsSpecService.findByGoodsSpecId(cart.getSpecId());
			goodsAmount += goodsSpec.getSpecGoodsPrice().doubleValue()*cart.getGoodsNum();
		}
		return BigDecimal.valueOf(goodsAmount);
	}
	
	/**
	 * 根据订单支付编号查询订单
	 * @param paySn
	 * @return
	 */
	@Override
	public List<Order> findByPaySn(String paySn) {
		return orderDao.findByPaySn(paySn);
	}
	
	/**
	 * 根据订单支付id查询订单
	 * @param payId
	 * @return
	 */
	@Override
	public List<Order> findByPayId(Integer payId) {
		return orderDao.findByPayId(payId);
	}
	
	/**
	 * 根据支付单号查询订单总价,支付单号可传订单号和支付单号
	 * @param paySn 可传订单号和支付单号
	 * @return 返回PaySn只有paySn,orderList,payAmount三个字段有值
	 */
	public OrderPay findOrderPayBySn(String paySn){
		OrderPay orderPay = new OrderPay();
		if("P".equals(paySn.substring(0, 1))){ //判断编号类型,支付单号
			//通过支付单号查询订单集合
			List<Order> orderList = orderDao.findByPaySn(paySn);
			//支付总额
			double amount = 0.00;
			for(Order order:orderList){
				amount += order.getOrderAmount().doubleValue();
			}
			//将支付单号存入paySn
			orderPay.setPaySn(paySn);
			orderPay.setPayAmount(BigDecimal.valueOf(amount));
		}else{ //订单编号
			List<Order> orderList = new ArrayList<Order>();
			//通过订单编号查询单个订单
			Order order = orderDao.findByOrderSn(paySn);
			orderList.add(order);
			//将订单号存入paySn
			orderPay.setPaySn(paySn);
			orderPay.setPayAmount(order.getOrderAmount());
			//单个订单的时候存入订单状态
			orderPay.setOrderState(order.getOrderState());
		}
		return orderPay;
	}
	
	/**
	 * 支付完成后,修改订单状态
	 * @param paySn
	 */
	public void updateOrderStatePayFinish(String paySn){
		//新建一个订单总的应付金额
		double orderAmount = 0.0;
		//新建一个余额支付金额
		double predepositAmount = 0.0;
		
		Integer memberId = null;
		
		if("P".equals(paySn.substring(0, 1))){ //判断编号类型,支付单号
			List<Order> orderList = orderDao.findByPaySn(paySn);
			for(Order order : orderList){
				if(order!=null){
					memberId = order.getBuyerId();
					if(order.getPaymentState()==0){
						//新建一个订单日志
						OrderLog orderLog = new OrderLog();
						orderLog.setOrderState(OrderState.ORDER_STATE_UNFILLED+"");
						orderLog.setChangeState(OrderState.ORDER_STATE_NOT_RECEIVING+"");
						orderLog.setStateInfo("订单付款完成");
						orderLog.setOrderId(order.getOrderId());
						orderLog.setOperator(order.getBuyerName());
						orderLog.setCreateTime(System.currentTimeMillis());
						//保存订单日志
						orderLogDao.saveOrderLog(orderLog);
						//修改订单状态
						orderDao.updateOrderStateByOrderSn(order.getOrderSn(), OrderState.ORDER_STATE_UNFILLED, OrderState.PAYMENT_STATE_YES,System.currentTimeMillis());
						//累加订单支付金额和订单余额支付金额
						orderAmount += order.getOrderAmount().doubleValue();
						predepositAmount += order.getPredepositAmount().doubleValue();
					}
				}
			}
		}else{
			//新建一个订单日志
			Order order = orderDao.findByOrderSn(paySn);
			if(order!=null){
				memberId = order.getBuyerId();
				if(order.getPaymentState()==0){
					OrderLog orderLog = new OrderLog();
					orderLog.setOrderState(OrderState.ORDER_STATE_UNFILLED+"");
					orderLog.setChangeState(OrderState.ORDER_STATE_NOT_RECEIVING+"");
					orderLog.setStateInfo("订单付款完成");
					orderLog.setOrderId(order.getOrderId());
					orderLog.setOperator(order.getBuyerName());
					orderLog.setCreateTime(System.currentTimeMillis());
					//保存订单日志
					orderLogDao.saveOrderLog(orderLog);
					//修改订单状态
					orderDao.updateOrderStateByOrderSn(order.getOrderSn(), OrderState.ORDER_STATE_UNFILLED, OrderState.PAYMENT_STATE_YES,System.currentTimeMillis());
					//累加订单支付金额和订单余额支付金额
					orderAmount += order.getOrderAmount().doubleValue();
					predepositAmount += order.getPredepositAmount().doubleValue();
				}
			}
		}
		Member member = memberService.findById(memberId);
		
		//判断应付金额不为0和余额支付金额不为0(此时支付的余额为冻结状态)
		if(orderAmount!=0&&predepositAmount!=0&&memberId!=null){
			//在冻结金额中减去支付完的余额
			double freeze = member.getFreezePredeposit().doubleValue() - predepositAmount;
			if(freeze>0){
				member.setFreezePredeposit(BigDecimal.valueOf(freeze));
			}else{
				member.setFreezePredeposit(BigDecimal.valueOf(0));
			}	
			memberService.update(member);
			
			//创建一个新的变更日志实体
			PredepositLog predepositLog = new PredepositLog();
			predepositLog.setLgMemberId(member.getMemberId()); //会员编号
			predepositLog.setLgMemberName(member.getMemberName()); //会员名称
			predepositLog.setLgType("order_comb_pay"); //操作类型:下单支付被冻结的预存款
			predepositLog.setLgAvAmount(BigDecimal.valueOf(0)); //可用金额变更0表示未变更
			predepositLog.setLgFreezeAmount(BigDecimal.valueOf(freeze)); //冻结金额变更0表示未变更
			predepositLog.setLgDesc("订单支付冻结余额"); //描述
			predepositLog.setCreateTime(System.currentTimeMillis()); //添加时间
			//保存预存款变更日志表
			predepositLogService.savePdl(predepositLog);
		}
		
		/**
		 * 订单积分修改,订单积分日志保存
		 */
		Integer points = Integer.parseInt(new java.text.DecimalFormat("0").format(orderAmount));;
		Integer rankPoint = member.getMemberRankPoints();
		Integer consPoint = member.getMemberConsumePoints();
		if(rankPoint==null) rankPoint = 0;
		if(consPoint==null) consPoint = 0;
		//获取积分设置购买商品(一元等于多少积分)等级积分
		String rankSettingPoints = settingService.findByNameAndCode("points", "buygoods_rank");
		//获取积分设置购买商品(一元等于多少积分)消费积分
		String consSettingPoints = settingService.findByNameAndCode("points", "buygoods_cons");
		if(StringUtils.isNotBlank(rankSettingPoints)){
			rankPoint += Integer.valueOf(rankSettingPoints)*points;
		}else{ //若没设置购买商品等级积分,则按(一元等于一积分计算)
			rankPoint += points;
		}
		if(StringUtils.isNotBlank(consSettingPoints)){
			consPoint += Integer.valueOf(consSettingPoints)*points;
		}else{ //若没设置购买商品消费积分,则按(一元等于一积分计算)
			consPoint += points;
		}
		
		//修改用户积分
		member.setMemberRankPoints(rankPoint);
		member.setMemberConsumePoints(consPoint);
		memberService.update(member);
		
		ShopPointsLog shopPointsLog = new ShopPointsLog();
		shopPointsLog.setMemberid(member.getMemberId());
		shopPointsLog.setMembername(member.getMemberName());
		shopPointsLog.setAdminid(1);
		shopPointsLog.setAdminname("admin");
		shopPointsLog.setPoints(consPoint);
		shopPointsLog.setCreateTime(System.currentTimeMillis());
		shopPointsLog.setType(PointsLogType.POINTS_TYPE_ORDERPAY); //积分操作类型
		shopPointsLog.setDesc("付款完成");
		shopPointsLog.setStage("商品付款成功,增加会员积分");
		//保存会员积分日志表
		shopPointsLogService.save(shopPointsLog);
	}

	/**
	 * 根据订单状态查询订单数量
	 * @param order 可加查询条件:订单编号,店铺名称,订单状态,买家名称,支付名称编号,开始结束时间(starttime,endtime),店铺id,买家id
	 * @return
	 */
	public int findOrderCountByOrder(Order order) {
		return orderDao.findOrderCountByOrder(order);
	}
	
	/**
	 * 取消订单
	 * @param orderSn 订单编号
	 * @param cancelCause 取消原因
	 * @param opType 操作人(1:买家;2:卖家,3:系统定时)
	 */
	public void updateCancelOrder(String orderSn,String cancelCause,Integer opType){
		try{
			//通过订单编号查询订单
			Order order = orderDao.findByOrderSn(orderSn);
			order.setCancelCause(cancelCause); //取消原因
			order.setOrderState(OrderState.ORDER_STATE_CANCLE); //订单状态
			order.setPaymentState(OrderState.PAYMENT_STATE_NO); //订单支付状态
			//修改商品库存和销量
			for(OrderGoods orderGoods:order.getOrderGoodsList()){
				//查询商品规格表,实时查找信息
				GoodsSpec goodsSpec = goodsSpecService.findByGoodsSpecId(orderGoods.getSpecId());
				//增加商品库存和销量,传值为负数
				goodsSpec.setSpecSalenum(-orderGoods.getGoodsNum().intValue());
				productService.updateStorage(goodsSpec);
			}
			//修改订单状态
			orderDao.updateOrder(order);
			//订单日志
			OrderLog orderLog = new OrderLog();
			orderLog.setOrderState(OrderState.ORDER_STATE_CANCLE+"");
			orderLog.setChangeState("");
			orderLog.setStateInfo("取消订单");
			orderLog.setOrderId(order.getOrderId());
			//判断操作人
			switch (opType) {
				case 1:
					orderLog.setOperator(order.getBuyerName());
					break;
				case 2:
					orderLog.setOperator(order.getStoreName());
					break;
				case 3:
					orderLog.setOperator("系统定时取消");
					break;
			}
			
			orderLog.setCreateTime(System.currentTimeMillis());
			//保存订单日志
			orderLogDao.saveOrderLog(orderLog);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 订单完成
	 * @param orderSn
	 */
	public void updateFinishOrder(String orderSn){
		//通过订单编号查询订单
		Order order = orderDao.findByOrderSn(orderSn);
		order.setOrderState(OrderState.ORDER_STATE_FINISH); //订单状态
		order.setFinnshedTime(System.currentTimeMillis());
		//修改订单状态
		orderDao.updateOrderState(order);
		//订单日志
		OrderLog orderLog = new OrderLog();
		orderLog.setOrderState(OrderState.ORDER_STATE_FINISH+"");
		orderLog.setChangeState("");
		orderLog.setStateInfo("订单已完成");
		orderLog.setOrderId(order.getOrderId());
		orderLog.setOperator(order.getBuyerName());
		orderLog.setCreateTime(System.currentTimeMillis());
		//保存订单日志
		orderLogDao.saveOrderLog(orderLog);
	}
	
	/**
	 * 订单确认(货到付款确认)
	 * @param orderSn
	 */
	public void updateConfirmOrder(String orderSn){
		//通过订单编号查询订单
		Order order = orderDao.findByOrderSn(orderSn);
		order.setOrderState(OrderState.ORDER_STATE_CONFIRM); //订单状态
		//修改订单状态
		orderDao.updateOrderState(order);
		//订单日志
		OrderLog orderLog = new OrderLog();
		orderLog.setOrderState(OrderState.ORDER_STATE_CONFIRM+"");
		orderLog.setChangeState(OrderState.ORDER_STATE_NOT_RECEIVING+"");
		orderLog.setStateInfo("订单已确认");
		orderLog.setOrderId(order.getOrderId());
		orderLog.setOperator(order.getStoreName());
		orderLog.setCreateTime(System.currentTimeMillis());
		//保存订单日志
		orderLogDao.saveOrderLog(orderLog);
	}
	
	/**
	 * 订单发货
	 * @param orderSn 订单编号
	 * @param daddressId 订单发货地址id
	 * @param shippingExpressId 配送公司id
	 * @param shippingCode 物流单号
	 * @param deliverExplain 发货备注
	 */
	@Override
	public void updateDeliveryOrder(String orderSn,Integer daddressId,Integer shippingExpressId,
									String shippingCode,String deliverExplain) {
		try{
			//通过订单编号查询订单
			Order order = orderDao.findByOrderSn(orderSn);
			order.setOrderState(OrderState.ORDER_STATE_NOT_RECEIVING); //订单状态
			order.setShippingTime(System.currentTimeMillis()); //配送时间
			if(daddressId!=null&&daddressId!=0&&order.getDaddressId()!=daddressId){
				OrderDaddress orderDaddress = new OrderDaddress();
				Daddress daddress = daddressService.findDaddressById(daddressId);
				MyBeanUtils.copyBeanNotNull2Bean(daddress, orderDaddress);
				orderDaddressService.saveOrderDaddress(orderDaddress);
				order.setDaddressId(orderDaddress.getAddressId());
			}
			if(shippingExpressId!=null&&shippingExpressId!=0){
				order.setShippingExpressId(shippingExpressId);
				Express express = expressService.findById(shippingExpressId);
				if(express != null){
					order.setShippingExpressCode(express.getECode());
				}else{
					order.setShippingExpressCode("");
				}
			}else{
				order.setShippingExpressCode("");
			}
			if(StringUtils.isNotBlank(shippingCode)){
				order.setShippingCode(shippingCode);
			}else{
				order.setShippingCode("");
			}
			if(StringUtils.isNotBlank(deliverExplain)){
				order.setDeliverExplain(deliverExplain);
			}
			//修改订单状态
			orderDao.updateOrder(order);
			//订单日志
			OrderLog orderLog = new OrderLog();
			orderLog.setOrderState(OrderState.ORDER_STATE_NOT_RECEIVING+"");
			orderLog.setChangeState(OrderState.ORDER_STATE_FINISH+"");
			orderLog.setStateInfo("订单已发货");
			orderLog.setOrderId(order.getOrderId());
			orderLog.setOperator(order.getStoreName());
			orderLog.setCreateTime(System.currentTimeMillis());
			//保存订单日志
			orderLogDao.saveOrderLog(orderLog);
			
			/**
			 * APP推送发货信息
			 */
			//订单商品集合
			List<OrderGoods> orderGoodsList = order.getOrderGoodsList();
			//获取购买商品名称
			String orderGoodsStr = "";
			for(int i=0; i<orderGoodsList.size(); i++){
				orderGoodsStr += orderGoodsList.get(i).getGoodsName();
				if(i!=0&&i!=orderGoodsList.size()-1){
					orderGoodsStr += "、";
				}
			}
			ServiceMessagePush.orderShipPush("您购买的"+orderGoodsStr+"已发货", "订单已发货", "订单发货通知", order.getBuyerId(), order.getOrderId());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改订单发货信息
	 * @param orderSn 订单编号
	 * @param daddressId 订单发货地址id
	 * @param shippingCode 物流单号
	 * @param deliverExplain 发货备注
	 */
	@Override
	public void updateOrderShipments(String orderSn,Integer daddressId,String shippingCode,String deliverExplain) {
		try{
			//通过订单编号查询订单
			Order order = orderDao.findByOrderSn(orderSn);
			if(daddressId!=null&&daddressId!=0&&order.getDaddressId()!=daddressId){
				OrderDaddress orderDaddress = new OrderDaddress();
				Daddress daddress = daddressService.findDaddressById(daddressId);
				MyBeanUtils.copyBeanNotNull2Bean(daddress, orderDaddress);
				orderDaddressService.saveOrderDaddress(orderDaddress);
				order.setDaddressId(orderDaddress.getAddressId());
			}
			if(StringUtils.isNotBlank(shippingCode)){
				order.setShippingCode(shippingCode);
			}else{
				order.setShippingCode("");
			}
			if(StringUtils.isNotBlank(deliverExplain)){
				order.setDeliverExplain(deliverExplain);
			}
			//修改订单
			orderDao.updateOrder(order);
			//订单日志
			OrderLog orderLog = new OrderLog();
			orderLog.setOrderState(OrderState.ORDER_STATE_NOT_RECEIVING+"");
			orderLog.setChangeState(OrderState.ORDER_STATE_FINISH+"");
			orderLog.setStateInfo("卖家修改订单发货信息");
			orderLog.setOrderId(order.getOrderId());
			orderLog.setOperator(order.getStoreName());
			orderLog.setCreateTime(System.currentTimeMillis());
			//保存订单日志
			orderLogDao.saveOrderLog(orderLog);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 订单退款
	 * @param orderId 订单id
	 * @param orderRefund 退款金额
	 * @param buyerMessage 退款原因
	 */
	public void addOrderRefund(Integer orderId,BigDecimal orderRefund,String buyerMessage){
		// 通过订单id查询订单信息
		Order order = orderDao.findById(orderId);
		
		int refundLogCount = refundLogService.findRefundLogCountByOrderId(order.getOrderId());
		if (refundLogCount > 0) {
			// 已经存在退款，就别再退了，重复记录，系统会出异常
			return;
		}
		
		// 新建订单退款表
		RefundLog refundLog = new RefundLog();
		refundLog.setOrderId(order.getOrderId()); //订单id
		refundLog.setRefundSn("8"+DateUtils.getDateStr("yyyyMMddHHmmssSSS")); //退款编号
		refundLog.setOrderSn(order.getOrderSn()); //订单编号
		refundLog.setStoreId(order.getStoreId()); //店铺id
		refundLog.setStoreName(order.getStoreName()); //店铺名称
		refundLog.setBuyerId(order.getBuyerId()); //买家id
		refundLog.setBuyerName(order.getBuyerName()); //买家名称
		refundLog.setOrderAmount(order.getOrderAmount()); //订单金额
		refundLog.setOrderRefund(orderRefund); //退款金额
		refundLog.setRefundPaymentName(order.getPaymentName()); //支付方式名称
		refundLog.setRefundPaymentCode(order.getPaymentCode()); //支付方式代码
		refundLog.setBuyerMessage(buyerMessage); //退款原因
		refundLog.setRefundType(1); //类型
		refundLog.setRefundState(1); //状态
		refundLog.setCreateTime(System.currentTimeMillis()); //创建时间
		
		// 保存退款表
		refundLogService.saveRefundLog(refundLog);
	}
	
	/**
	 * 订单退货
	 * @param orderId 订单id
	 * @param jsondata 订单退货数量
	 * @param buyerMessage 退货原因
	 * @param goodsIds 退货商品id
	 */
	public void addOrderReturn(Integer orderId,String jsondata,String buyerMessage,String goodsIds){
		//通过订单id查询订单信息
		Order order = orderDao.findById(orderId);
		//将json串转换成map
		Map<String,String> map = JsonUtils.readJsonToMap(jsondata);
		//将商品id字符串分割成数组
		String[] ids = goodsIds.split(",");
		
		int totalNum = 0; //退货商品总数
		for(String goodsId:ids){
			totalNum += Integer.valueOf(map.get(goodsId));
		}
		
		ReturnOrder returnOrder = new ReturnOrder();
		returnOrder.setReturnSn("9"+DateUtils.getDateStr("yyyyMMddHHmmssSSS")); //退货编号
		returnOrder.setBuyerId(order.getBuyerId()); //买家id
		returnOrder.setBuyerMessage(buyerMessage); //退货原因
		returnOrder.setBuyerName(order.getBuyerName()); //买家名称
		returnOrder.setOrderId(order.getOrderId()); //订单id
		returnOrder.setOrderSn(order.getOrderSn()); //订单编号
		returnOrder.setReturnGoodsNum(totalNum); //退货数量
		returnOrder.setReturnState(1); //退货状态
		returnOrder.setReturnType(1); //类型
		returnOrder.setStoreId(order.getStoreId()); //店铺id
		returnOrder.setStoreName(order.getStoreName()); //店铺名称
		returnOrder.setCreateTime(System.currentTimeMillis()); //创建时间
		//保存退货表
		returnOrderService.saveReturnOrder(returnOrder);
		
		for(String goodsId:ids){
			//通过订单商品id查询订单商品
			OrderGoods orderGoods = orderGoodsDao.findById(Integer.valueOf(goodsId));
			
			ReturnGoods returnGoods = new ReturnGoods();
			returnGoods.setGoodsId(orderGoods.getGoodsId()); //商品ID
			returnGoods.setGoodsName(orderGoods.getGoodsName()); //商品名称
			returnGoods.setGoodsImage(orderGoods.getGoodsImage()); //商品图片
			returnGoods.setGoodsPrice(orderGoods.getGoodsPrice()); //商品价格
			returnGoods.setGoodsNum(orderGoods.getGoodsNum()); //商品数量
			returnGoods.setGoodsReturnNum(Integer.valueOf(map.get(goodsId))); //退货数量
			returnGoods.setOrderId(orderGoods.getOrderId()); //订单ID
			returnGoods.setReturnId(returnOrder.getReturnId()); //退货记录ID
			returnGoods.setSpecId(orderGoods.getSpecId()); //规格ID
			returnGoods.setSpecInfo(orderGoods.getSpecInfo()); //规格描述
			//保存退货商品表
			returnGoodsService.saveReturnGoods(returnGoods);
		}
	}
	
	/**
	 * 订单评价完成
	 * @param orderSn
	 */
	public void updateEvaluationOrder(String orderSn){
		//通过订单编号查询订单
		Order order = orderDao.findByOrderSn(orderSn);
		order.setEvaluationStatus(OrderState.ORDER_EVALUATION_YES); //订单评价状态
		order.setEvaluationTime(System.currentTimeMillis()); //订单评价时间
		//修改订单状态
		orderDao.updateOrderState(order);
	}
	
	/**
	 * 订单结算完成
	 * @param orderSn
	 */
	public void updateBalanceOrder(String orderSn){
		//通过订单编号查询订单
		Order order = orderDao.findByOrderSn(orderSn);
		order.setBalanceState(OrderState.ORDER_BALANCE_YES); //订单结算状态
		order.setBalanceTime(System.currentTimeMillis()); //订单结算时间
		//修改订单状态
		orderDao.updateOrder(order);
	}
	
	/**
	 * 订单批量结算 
	 * @param ids 订单id,中间已","分隔 
	 */
	public void updateBalanceOrderByIds(String ids){
		//将多个订单id分隔成一个id数组
		String[] orderIds = ids.split(","); 
		for(String orderId:orderIds){
			//通过订单id查询订单
			Order order = orderDao.findById(Integer.valueOf(orderId));
			order.setBalanceState(OrderState.ORDER_BALANCE_YES); //订单结算状态
			order.setBalanceTime(System.currentTimeMillis()); //订单结算时间
			//修改订单状态
			orderDao.updateOrder(order);
		}
	}
	
	/**
	 * 根据支付单号更改订单支付方式id和支付方式名称代码
	 * @param paySn 支付单号
	 * @param paymentId 支付方式id
	 */
	public void updateOrderPaymentByPaySn(String paySn,Integer paymentId){
		try{
			Payment payment = paymentService.findById(paymentId.longValue());
			if("P".equals(paySn.substring(0, 1))){ //判断编号类型,支付单号
				List<Order> orderList = orderDao.findByPaySn(paySn);
				for(Order order : orderList){
					order.setPaymentCode(payment.getPaymentCode()); //支付方式名称代码
					order.setPaymentId(payment.getPaymentId().intValue()); //支付方式id
					order.setPaymentName(payment.getPaymentName()); //支付方式名称
					orderDao.updateOrder(order);
				}
			}else{ //订单编号
				Order order = orderDao.findByOrderSn(paySn);
				order.setPaymentCode(payment.getPaymentCode()); //支付方式名称代码
				order.setPaymentId(payment.getPaymentId().intValue()); //支付方式id
				order.setPaymentName(payment.getPaymentName()); //支付方式名称
				orderDao.updateOrder(order);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 计算有订单的相关金额
	 * @param cartIds 多个购物车id
	 * @param Coupon 优惠券
	 * @param orderAddress 订单收货地址
	 * @param freight 运费信息
	 * @param isPd 是否使用余额 1为是
	 * @param member 用户信息
	 * @return
	 */
	public List<OrderVo> getAmount(String cartIds,Coupon coupon,OrderAddress orderAddress,String freight,Integer isPd,Member member){
		try {
			//新建一个OrderVo集合,存储店铺相关的金额
			List<OrderVo> list = new ArrayList<OrderVo>();
			
			List<CartVo> cartVoList = cartService.queryVOListByCartIds(cartIds);
			/**
			 * 计算订单运费信息
			 */
			
			//新建一个map,用来存储一个店铺下的运费信息,键为店铺id,值为运费类型
			Map<Integer,String> storeShipMap = new HashMap<Integer, String>();
			//判断运费是否包邮
			if(StringUtils.isNotBlank(freight)){ //不包邮
				//将运费信息根据','拆分为例如"py|10"的格式,'py'是运输类型,'10'为店铺id
				String[] ships = freight.split(",");
				for(String ship:ships){ //遍历运费信息,进行进一步查分
					String[] fres = ship.split("\\|");
					storeShipMap.put(Integer.valueOf(fres[1]), fres[0]);
				}
			}
			//新建一个所有订单应付总金额
			double allOrderAmount = 0.0;
			
			for(CartVo cartVo:cartVoList){
				//新建一个orderVo
				OrderVo orderVo = new OrderVo();
				//将cartVo相同字段复制到OrderVo
				MyBeanUtils.copyBeanNotNull2Bean(cartVo, orderVo);
				//获取单个运费模板的运费
				BigDecimal shipprice = null;
				if(storeShipMap.get(cartVo.getStoreId())!=null){ //判断是否存在运费
					//新建一个店铺不包邮商品的数量
					int transGoodsNum = 0;
					for(Cart cart:cartVo.getList()){
						Goods goods = goodsService.findGoodById(cart.getGoodsId());
						if(goods.getGoodsTransfeeCharge()==0){ //商品为买家承担运费
							transGoodsNum += cart.getGoodsNum();
						}
					}
					shipprice = transportService.getFreightForStore(cartVo.getStoreId(), storeShipMap.get(cartVo.getStoreId()), orderAddress.getCityId(), transGoodsNum);
				}
				//订单运费
				if(shipprice!=null){
					orderVo.setShippingFee(shipprice);
				}else{
					orderVo.setShippingFee(BigDecimal.valueOf(0));
				}
				//计算订单商品总价
				BigDecimal goodsAmount = this.findAmount(cartVo);
				orderVo.setGoodsAmount(goodsAmount);
				//计算订单应付金额
				double orderAmount = this.getOrderAmount(goodsAmount.doubleValue(), orderVo.getShippingFee().doubleValue(), coupon);
				orderVo.setOrderAmount(BigDecimal.valueOf(orderAmount));
				//所有订单应付金额
				allOrderAmount += orderAmount;
				list.add(orderVo);
			}
			//判断是否使用余额
			if(isPd==1){
				//创建一个新的余额变更日志实体
				PredepositLog predepositLog = new PredepositLog();
				predepositLog.setLgMemberId(member.getMemberId()); //会员编号
				predepositLog.setLgMemberName(member.getMemberName()); //会员名称
				
				//获取用户余额
				double availablePredeposit = member.getAvailablePredeposit().doubleValue();
				//冻结用户余额
				//判断余额是否充足
				if(availablePredeposit>=allOrderAmount){ //若余额充足
					//用户余额=原有余额-订单应付总金额
					double available = availablePredeposit-allOrderAmount;
					member.setAvailablePredeposit(BigDecimal.valueOf(available));
					//修改用户余额
					memberService.update(member);
					
					//修改订单的余额支付金额和应付金额
					for(OrderVo orderVo:list){
						//将订单总价放入订单余额支付金额
						orderVo.setPredepositAmount(orderVo.getOrderAmount());
						//将订单应付金额设为0
						orderVo.setOrderAmount(BigDecimal.valueOf(0));
					}
					/**
					 * 完善余额日志表
					 */
					predepositLog.setLgType("order_pay"); //操作类型:订单支付
					predepositLog.setLgAvAmount(BigDecimal.valueOf(availablePredeposit-allOrderAmount)); //可用金额
					predepositLog.setLgFreezeAmount(BigDecimal.valueOf(0)); //冻结金额
				}else{ //余额不足
					//冻结金额=原有的所有的余额+原有的冻结金额
					double freeze = member.getAvailablePredeposit().doubleValue() + member.getFreezePredeposit().doubleValue();
					//用户余额=0.0
					member.setAvailablePredeposit(BigDecimal.valueOf(0));
					member.setFreezePredeposit(BigDecimal.valueOf(freeze));
					//修改用户余额
					memberService.update(member);
					//新建一个用掉的余额金额
					double oldAvailablePredeposit = 0.0;
					
					for(int i=0; i<list.size(); i++){
						OrderVo orderVo = list.get(i);
						//判断是否为最后循环
						if(i==list.size()-1){ //若为最后一次
							double amount = orderVo.getOrderAmount().doubleValue();
							//订单余额支付金额,余额不足时,最后循环,将剩余余额-已分配完的余额
							double predepositAmount = availablePredeposit - oldAvailablePredeposit;
							orderVo.setPredepositAmount(BigDecimal.valueOf(predepositAmount)); //余额支付金额
							orderVo.setOrderAmount(BigDecimal.valueOf(amount-predepositAmount)); //订单应付金额=订单总价-余额支付金额
						}else{
							double amount = orderVo.getOrderAmount().doubleValue();
							//订单余额支付金额,余额不足时,按订单总价/所有需支付订单总价*用户所剩余额
							double predepositAmount = NumberUtils.round(((orderVo.getOrderAmount().doubleValue()/allOrderAmount)*availablePredeposit),2);
							//记录已支付过的余额
							oldAvailablePredeposit += predepositAmount;
							orderVo.setPredepositAmount(BigDecimal.valueOf(predepositAmount)); //余额支付金额
							orderVo.setOrderAmount(BigDecimal.valueOf(amount-predepositAmount)); //订单应付金额=订单总价-余额支付金额
						}
					}
					/**
					 * 完善余额日志表
					 */
					predepositLog.setLgType("order_freeze"); //操作类型:冻结余额
					predepositLog.setLgAvAmount(BigDecimal.valueOf(0)); //可用金额
					predepositLog.setLgFreezeAmount(BigDecimal.valueOf(availablePredeposit)); //冻结金额
				}
				
				predepositLog.setLgDesc("生成预付款支付信息"); //描述
				predepositLog.setCreateTime(System.currentTimeMillis()); //添加时间
				//保存预存款变更日志表
				predepositLogService.savePdl(predepositLog);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 获取订单总金额
	 * @param goodsAmount 商品总价 
	 * @param freight 运费信息
	 * @param couponId 优惠券id
	 * @return
	 */
	public double getOrderAmount(Double goodsAmount, Double freight, Coupon coupon){
		/**
		 * 优惠券使用信息
		 */
		//新建一个优惠券金额
		double couponPrice = 0.0;
		//判断是否使用优惠券
		if(coupon!=null){ //使用,查询优惠券信息
			couponPrice = coupon.getCouponPrice().doubleValue();
		}
		/**
		 * 优惠信息
		 */
		StrategyCondition condition = new StrategyCondition();
		//condition.setPromoteValue(totalGoodsPrice);//优惠的金额 可以是邮费，打折，满减等
		condition.setOrderFreight(freight);
		double realPrice = calculateService.Calculate(goodsAmount, condition);
		
		//新建一个订单应付金额
		double totalPrice = 0.0;
		if(realPrice>couponPrice){
			totalPrice = realPrice - couponPrice;
		}
		return totalPrice;
	}
	
	
	/**
     * 修改订单项评价状态
     * @param recId 订单项id
     * @param order
     */
    public void updateEvaluationStatus(Order order,Integer recId){
    	//新建一个字段,记录订单项评价数量
    	int esNum = 0;
    	
    	List<OrderGoods> list = order.getOrderGoodsList();
    	for(OrderGoods orderGoods:list){
    		//判断订单项是否为当前要评论的订单项
    		if(orderGoods.getRecId().intValue()==recId){ //若订单项为当前评价订单号,修改
    			orderGoods.setEvaluationStatus(OrderState.ORDER_EVALUATION_YES); //订单项评价状态
            	orderGoods.setEvaluationTime(System.currentTimeMillis()); //订单项评价时间
            	orderGoodsDao.updateOrderGoods(orderGoods);
    		}
    		//判断当前商品是否支付
    		if(orderGoods.getEvaluationStatus()==1){ //当前订单已评价
    			esNum += 1; //订单项评价数量加1
    		}
    	}
    	
    	/**
    	 * 根据订单项评价个数修改订单评价状态
    	 */
    	if(esNum==list.size()){ //若订单项全部评价
    		Order upOrder = new Order();
    		upOrder.setOrderId(order.getOrderId());
    		upOrder.setEvaluationStatus(OrderState.ORDER_EVALUATION_YES); //订单评价状态,已评价
    		upOrder.setEvaluationTime(System.currentTimeMillis()); //订单评价时间
    		//修改订单
    		orderDao.updateOrder(upOrder);
    	}
    }
    
    
    @Override
	public void updateDeliveryOrderSupplier( String orderSn,Integer daddressId,Integer shippingExpressId,
									String shippingCode,String deliverExplain,Integer sendUserId) throws Exception {
//		try{
			//通过订单编号查询订单
			Order order = orderDao.findByOrderSn(orderSn);
			order.setOrderState(OrderState.ORDER_STATE_NOT_RECEIVING); //订单状态
			order.setShippingTime(System.currentTimeMillis()); //配送时间
			if(daddressId!=null&&daddressId!=0&&order.getDaddressId()!=daddressId){
				OrderDaddress orderDaddress = new OrderDaddress();
				Daddress daddress = daddressService.findDaddressById(daddressId);
				MyBeanUtils.copyBeanNotNull2Bean(daddress, orderDaddress);
				orderDaddressService.saveOrderDaddress(orderDaddress);
				order.setDaddressId(orderDaddress.getAddressId());
			}
			if(shippingExpressId!=null&&shippingExpressId!=0){
				order.setShippingExpressId(shippingExpressId);
				Express express = expressService.findById(shippingExpressId);
				if(express != null){
					order.setShippingExpressCode(express.getECode());
				}else{
					order.setShippingExpressCode("");
				}
			}else{
				order.setShippingExpressCode("");
				
				
				//如果没有物流就发码,循环商品发码
				List<String> goodsNames = Lists.newArrayList();
				List<String> conumerCodes = Lists.newArrayList();
				List<OrderGoods> orderGoodsList = order.getOrderGoodsList();
				for (OrderGoods orderGoods : orderGoodsList) {
					
					ConsumerCodeSend conumerCode = new ConsumerCodeSend();
					conumerCode.setSendUser(sendUserId);
					conumerCode.setOrderId(order.getOrderId());
					conumerCode.setGoodsId(orderGoods.getGoodsId());
					conumerCode.setOrderSn(orderSn);
					conumerCode =  consumerCodeSendService.sendCode(conumerCode);
					
					goodsNames.add(orderGoods.getGoodsName());
					conumerCodes.add(conumerCode.getConsumerCodeBunch());
				}
				
				//发送消费码短信给客户，订单号，消费码
				Message message = new Message();
				message.setMobiles(ImmutableList.of(orderAddressService.findById(order.getAddressId()).getMobPhone()));
				message.setMessage(Joiner.on(",").join(goodsNames), orderSn, Joiner.on(",").join(conumerCodes));
				Sender.send(message);
			}
			if(StringUtils.isNotBlank(shippingCode)){
				order.setShippingCode(shippingCode);
			}else{
				order.setShippingCode("");
			}
			if(StringUtils.isNotBlank(deliverExplain)){
				order.setDeliverExplain(deliverExplain);
			}
			//修改订单状态
			orderDao.updateOrder(order);
			//订单日志
			OrderLog orderLog = new OrderLog();
			orderLog.setOrderState(OrderState.ORDER_STATE_NOT_RECEIVING+"");
			orderLog.setChangeState(OrderState.ORDER_STATE_FINISH+"");
			orderLog.setStateInfo("订单已发货");
			orderLog.setOrderId(order.getOrderId());
			orderLog.setOperator(order.getStoreName());
			orderLog.setCreateTime(System.currentTimeMillis());
			//保存订单日志
			orderLogDao.saveOrderLog(orderLog);
			
			/**
			 * APP推送发货信息
			 */
			//订单商品集合
			List<OrderGoods> orderGoodsList = order.getOrderGoodsList();
			//获取购买商品名称
			String orderGoodsStr = "";
			for(int i=0; i<orderGoodsList.size(); i++){
				orderGoodsStr += orderGoodsList.get(i).getGoodsName();
				if(i!=0&&i!=orderGoodsList.size()-1){
					orderGoodsStr += "、";
				}
			}
			ServiceMessagePush.orderShipPush("您购买的"+orderGoodsStr+"已发货", "订单已发货", "订单发货通知", order.getBuyerId(), order.getOrderId());
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public List<Order> findOrderListByCodeStatus(Pager pager) {
		return orderDao.findOrderListByCodeStatus(pager);
	}
}
