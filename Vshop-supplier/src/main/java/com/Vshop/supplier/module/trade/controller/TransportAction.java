package com.Vshop.supplier.module.trade.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.Vshop.core.entity.Area;
import com.Vshop.core.entity.Order;
import com.Vshop.core.entity.TransportSupplier;
import com.Vshop.core.entity.base.ConsumerCodeSend;
import com.Vshop.core.entity.base.Daddress;
import com.Vshop.core.entity.base.Express;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.OrderDaddress;
import com.Vshop.core.entity.base.SupplierExtend;
import com.Vshop.supplier.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.area.service.AreaService;
import com.Vshop.service.module.goods.service.ConsumerCodeSendService;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.module.setting.service.ExpressService;
import com.Vshop.service.module.store.service.SupplierExtendService;
import com.Vshop.service.module.trade.service.DaddressService;
import com.Vshop.service.module.trade.service.OrderDaddressService;
import com.Vshop.service.module.trade.service.OrderService;
import com.Vshop.service.module.trade.service.TransportSupplierService;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * 
 * @项目名称：Vshop-supplier
 * @类描述： 运费模板
 * @创建人：cgl
 * @创建时间：2015年08月06日09:50:18
 * @修改备注：
 * @version
 * 
 */
@Controller
@RequestMapping("/transport")
@Slf4j
public class TransportAction {
	@Resource
	private TransportSupplierService transportSupplierService;
	
	@Resource 
	private OrderService orderService;
	
	@Resource
	private DaddressService daddressService;
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private ExpressService expressService;
	
	@Resource
	private SupplierExtendService supplierExtendService;
	
	@Resource
	private OrderDaddressService orderDaddressService;
	
	@Resource
	private ConsumerCodeSendService consumerCodeSendService;
	
	@Resource
	private GoodsService googsService;

	/**
	 * 
	 * @Title: index
	 * @Description: 跳转到主页面
	 * @param @param model
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping("/index")
	public String index(
			Model model,
			@RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNoStr) {
		try {
//			Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
			Integer supplierId = CacheUtils.getCacheUser().getSupplier().getId();

			TransportSupplier transport = new TransportSupplier();
//			transport.setStoreId(storeId);
			transport.setSupplierId(supplierId);

			Pager pager = new Pager();
			
			pager.setCondition(transport);

			List<TransportSupplier> transportList = transportSupplierService.queryList(pager);

			model.addAttribute("transportList", transportList);// 品牌列表
			model.addAttribute("pageNo", pager.getPageNo());// 当前页
			model.addAttribute("pageSize", pager.getPageSize());// 每页显示条数

			return "/transport/transport_index";
		} catch (Exception e) {
			log.error("导航失败!", e);
			throw new RuntimeException("导航失败!");
		}
	}
	
	
	
	/**
	 * 
	 * @Title: add
	 * @Description: 跳转到新增运费模板页面
	 * @param @param model
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping("/toAdd")
	public String toAdd(Model model) {
		try {
			List<Area> areas = areaService.getProvinceCityArea();
			model.addAttribute("areas", areas);
			return "/transport/transport_add";
		} catch (Exception e) {
			log.error("导航失败!", e);
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 
	 * @Title: add
	 * @Description: 跳转到新增运费模板页面
	 * @param @param model
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping("/toEdit")
	public String toEdit(Model model, Integer id) {
		try {
			TransportSupplier transport = transportSupplierService.findById(id);
			model.addAttribute("transport", transport);
			List<Area> areas = areaService.getProvinceCityArea();
			model.addAttribute("areas", areas);
			return "/transport/transport_edit";
		} catch (Exception e) {	
			log.error("导航失败!", e);
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 
	 * @Title: add
	 * 添加
	 */
	@ResponseBody
	@RequestMapping("/add")
	public String add(Model model, String title, String tranStr) {
		try {
//			Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
			Integer supplierId = CacheUtils.getCacheUser().getSupplier().getId();
			if(StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(tranStr)){
				transportSupplierService.save(supplierId, title, tranStr);
			}
			return "success";
		} catch (Exception e) {
			log.error("导航失败!", e);
			return "error";
		}
	}
	
	/**
	 * 
	 * @Title: add
	 * 添加
	 */
	@ResponseBody
	@RequestMapping("/edit")
	public String edit(Model model,Integer transportId, String title, String tranStr) {
		try {
//			Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
			Integer supplierId = CacheUtils.getCacheUser().getSupplier().getId();
			if(StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(tranStr)){
				transportSupplierService.update(transportId, supplierId, title, tranStr);
			}
			return "success";
		} catch (Exception e) {
			log.error("导航失败!", e);
			return "error";
		}
	}
	
	/**
	 * 
	 * @Title: delete
	 * 删除模板
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(Integer transportId) {
		try {
			//Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
			transportSupplierService.delete(transportId);
			return "success";
		} catch (Exception e) {
			log.error("导航失败!", e);
			return "error";
		}
	}
	
	/**
	 * 
	 * @Title: setDefaultTransport
	 * 修改默认的运费模板
	 */
	@ResponseBody
	@RequestMapping("/setDefaultTransport")
	public String updateDefaultTransport(Integer transportId) {
		try {
//			Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
			Integer supplierId = CacheUtils.getCacheUser().getSupplier().getId();
			transportSupplierService.updateDefaultTransport(supplierId, transportId);
			return "success";
		} catch (Exception e) {
			log.error("导航失败!", e);
			return "error";
		}
	}

//	/**
//	 * 
//	 * @Title: list
//	 * @Description: 查询列表
//	 * @param @param model
//	 * @param @param div
//	 * @param @param pageNoStr
//	 * @param @return 设定文件
//	 * @return String 返回类型
//	 * @throws
//	 */
//	@RequestMapping("/list")
//	public String list(
//			Model model,
//			@RequestParam(required = false, value = "div1", defaultValue = "") String div,
//			@RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNoStr) {
//		try {
//			Integer storeId = new Integer(1);
//
//			Transport transport = new Transport();
//			transport.setStoreId(storeId);
//
//			Pager pager = new Pager();
//			pager.setCondition(transport);
//
//			List<Transport> transportList = transportService.queryList(pager);
//
//			model.addAttribute("transportList", transportList);// 品牌列表
//			// model.addAttribute("div1", div);// 显示的DIV数据区域
//			model.addAttribute("pageNo", pager.getPageNo());// 当前页
//			model.addAttribute("pageSize", pager.getPageSize());// 每页显示条数
//
//			return "/trade/transport_list";
//		} catch (Exception e) {
//			log.error("导航失败!", e);
//			throw new RuntimeException("导航失败!");
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: copy
//	 * @Description: 复制模板
//	 * @param @param model
//	 * @param @param id
//	 * @param @return 设定文件
//	 * @return String 返回类型
//	 * @throws
//	 */
//	@RequestMapping("/copy")
//	public String copy(Model model, @RequestParam(value = "id") Integer id) {
//		try {
//			transportService.addCopy(id);
//
//			return "redirect:/transport/list";
//		} catch (Exception e) {
//			log.error("导航失败!", e);
//			throw new RuntimeException("导航失败!");
//		}
//	}
//
//	
//
//	/**
//	 * 
//	 * @Title: del
//	 * @Description: 删除模板
//	 * @param @param model
//	 * @param @param id
//	 * @param @return 设定文件
//	 * @return String 返回类型
//	 * @throws
//	 */
//	@RequestMapping("/delete")
//	public String del(Model model, @RequestParam(value = "id") Integer id) {
//		try {
//			transportService.delete(id);
//
//			return "redirect:/transport/list";
//			// return "forward:/transport/list";
//		} catch (Exception e) {
//			log.error("导航失败!", e);
//			throw new RuntimeException("导航失败!");
//		}
//	}
//
//	@RequestMapping("/toModify")
//	public String toModify(Model model, @RequestParam(value = "id") Integer id) {
//		try {
//			Transport transport = transportService.findById(id);
//
//			model.addAttribute("transport", transport);
//			return "/trade/transport_modify";
//		} catch (Exception e) {
//			log.error("导航失败!", e);
//			throw new RuntimeException("导航失败!");
//		}
//	}
//
//	@RequestMapping("/modify")
//	public String modify(HttpServletRequest request, Model model) {
//		try {
//			String queryStr = request.getQueryString();
//			String title = request.getParameter("title");
//			String transport_id = request.getParameter("transport_id");
//			String form_submit = request.getParameter("form_submit");
//			String type = request.getParameter("type");
//			String xxn = request.getParameter("areas[kd]n");
//			String xx2 = request.getParameter("areas[kd]2");
//
//			Map map = request.getParameterMap();
//
//			Set keysSet = map.keySet();
//			Iterator iterator = keysSet.iterator();
//			while (iterator.hasNext()) {
//				String key = (String) iterator.next();// key
//				String[] value = (String[]) map.get(key);// value
//				System.out.println("=============");
//				System.out.println(key.toString());
//
//				for (String s : value) {
//					System.out.println(s);
//				}
//			}
//
//			// for (Object obj : map.values()) {
//			// String key=obj.toString();
//			// System.out.println(key + ":" + map.get(key));
//			// }
//
//			// String transportId = request.getParameter("transportId");
//			// String transportTitle = request.getParameter("transportTitle");
//			//
//			// String defaultTransportExtendId = request
//			// .getParameter("defaultTransportExtendId");
//			// String defaultSnum = request.getParameter("defaultSnum");
//			// String defaultSprice = request.getParameter("defaultSprice");
//			// String defaultXnum = request.getParameter("defaultXnum");
//			// String defaultXprice = request.getParameter("defaultXprice");
//			//
//			// String[] transportExtendIds = request
//			// .getParameterValues("transportExtendId");
//			//
//			// for (String transportExtendId : transportExtendIds) {
//			// String snum = request.getParameter("snum" + transportExtendId);
//			// String sprice = request.getParameter("sprice"
//			// + transportExtendId);
//			// String xnum = request.getParameter("xnum" + transportExtendId);
//			// String xprice = request.getParameter("xprice"
//			// + transportExtendId);
//			// }
//
//			// model.addAttribute("transport", transport);
//			return "trade/transport_modify";
//		} catch (Exception e) {
//			log.error("导航失败!", e);
//			throw new RuntimeException("导航失败!");
//		}
//	}
//	
	/**
	 * 发货列表
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/shipments")
	public ModelAndView shipments(
			@RequestParam(required=false, value="pageNo",defaultValue="")String pageNoStr,
			@RequestParam(required=false, value="orderSn",defaultValue="")String orderSn,
			@RequestParam(required=false, value="orderState",defaultValue="20")String orderState){
		try{
			ModelAndView model = new ModelAndView("/trade/trade_shipments");
			Pager pager = new Pager();
			Order order = new Order();
			
			/**查询条件，放入实体中，**/
			if(StringUtils.isNotBlank(orderSn.trim())){
				order.setOrderSn(orderSn.trim());
				model.addObject("orderSn", orderSn.trim());
			}
			
			if(StringUtils.isNotBlank(orderState)&&!"99".equals(orderState)){
				int[] orderStates = {Integer.valueOf(orderState),60};
				order.setOrderStates(orderStates);
			}
				
			if(StringUtils.isNotBlank(pageNoStr)){
				pager.setPageNo(Integer.parseInt(pageNoStr));
			}
			
//			order.setStoreId(CacheUtils.getCacheUser().getStore().getStoreId());
			order.setSupplierId(CacheUtils.getCacheUser().getSupplier().getId());
			
			pager.setCondition(order);//实体加载在pager中
			pager.setPageSize(20);//每页默认显示20条
			
			List<Order> results = orderService.findOrderList(pager);// 结果集

			model.addObject("orderLists", results);// 结果集
			model.addObject("pageNo", pager.getPageNo());// 当前页
			model.addObject("pageSize", pager.getPageSize());// 每页显示条数
			model.addObject("recordCount", pager.getTotalRows());// 总数
            model.addObject("toUrl","/transport/shipments");
            model.addObject("orderState",orderState);
			return model;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("发货加载失败！");
			throw new RuntimeException("发货加载失败!");
			
		}
	}
	
	/**
	 * 发货
	 * @return
	 */
	@RequestMapping("/shipments_deliver")
	public String shipmentsDeliver(
				Model model,
				@RequestParam(required=false, value="orderId",defaultValue="")String orderId){
//		Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
		Integer supplierId = CacheUtils.getCacheUser().getSupplier().getId();
		Order order = orderService.findOrderDetail(Integer.valueOf(orderId),null,null);
		Integer goodsId = order.getOrderGoodsList().get(0).getGoodsId();
		Goods goods = googsService.findGoodsById(goodsId);
//		List<Daddress> daddressList = daddressService.findDaddressByStoreId(CacheUtils.getCacheUser().getStore().getStoreId());
//		List<Daddress> daddressList = daddressService.findDaddressBySupplierId(supplierId);
		Daddress daddress = daddressService.findDefaultDaddressById(supplierId);
//		List<Express> expressList = storeExtendService.findExpressByExpressIds(CacheUtils.getCacheUser().getStore().getStoreId());
		List<Express> expressList = supplierExtendService.findExpressByExpressIds(CacheUtils.getCacheUser().getSupplier().getId());
		model.addAttribute("expressList", expressList);
		model.addAttribute("goods",goods);
//		model.addAttribute("daddressList", daddressList);
		model.addAttribute("daddress",daddress);
		model.addAttribute("order",order);
		return  "/trade/trade_shipments_deliver";
	}
	
	/**
	 * 编辑发货信息
	 * @return
	 */
	@RequestMapping("/shipments_deliver_update")
	public String shipmentsDeliverUpdate(
				Model model,
				@RequestParam(required=false, value="orderId",defaultValue="")String orderId){
//		Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
		//Integer supplierId = CacheUtils.getCacheUser().getSupplier().getId();
		Order order = orderService.findOrderDetail(Integer.valueOf(orderId),null,null);
		OrderDaddress daddress = orderDaddressService.findOrderDaddressById(order.getDaddressId());
//		List<Express> expressList = storeExtendService.findExpressByExpressIds(CacheUtils.getCacheUser().getStore().getStoreId());
		List<Express> expressList = supplierExtendService.findExpressByExpressIds(CacheUtils.getCacheUser().getSupplier().getId());
		
		
		//如果有码，查询发码列表
		
		ConsumerCodeSend cond = new ConsumerCodeSend();
		
		cond.setOrderId(Integer.parseInt( orderId));
		
		List<ConsumerCodeSend> codeList = consumerCodeSendService.findList(cond);
		
		
		model.addAttribute("expressList", expressList);
		model.addAttribute("daddress", daddress);
		model.addAttribute("codeList", codeList);
		model.addAttribute("order",order);
		return  "/trade/trade_shipments_deliver_update";
	}
	
	/**
	 *  保存发货信息
	 * @return
	 */
	@RequestMapping("/shipments_save")  
	@ResponseBody
	public Map<String,Object> shipmentsSave(@RequestParam(required=false, value="orderSn",defaultValue="")String orderSn,
											@RequestParam(required=false, value="daddressId",defaultValue="0")String daddressId,
											@RequestParam(required=false, value="shippingExpressId",defaultValue="0")String shippingExpressId,
											@RequestParam(required=false, value="shippingCode",defaultValue="")String shippingCode,
											@RequestParam(required=false, value="deliverExplain",defaultValue="")String deliverExplain
											){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			Integer supplierId = CacheUtils.getCacheUser().getSupplier().getId();
			
			orderService.updateDeliveryOrderSupplier(orderSn, Integer.valueOf(daddressId), Integer.valueOf(shippingExpressId), shippingCode, deliverExplain,supplierId);
			map.put("success", true);
		}catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
			log.error("发货保存失败！");
			throw new RuntimeException("发货保存失败！");
		}
		return map;
	}
	/**
	 *  保存发货信息
	 * @return
	 */
	@RequestMapping("/shipments_update")  
	@ResponseBody
	public Map<String,Object> shipmentsUpdate(@RequestParam(required=false, value="orderSn",defaultValue="")String orderSn,
											@RequestParam(required=false, value="daddressId",defaultValue="0")String daddressId,
											@RequestParam(required=false, value="shippingCode",defaultValue="")String shippingCode,
											@RequestParam(required=false, value="deliverExplain",defaultValue="")String deliverExplain
											){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			orderService.updateOrderShipments(orderSn, Integer.valueOf(daddressId), shippingCode, deliverExplain);
			map.put("success", true);
		}catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
			log.error("发货保存失败！");
			throw new RuntimeException("发货保存失败！");
		}
		return map;
	}
	
	/**
	 * 发货地址设置
	 * @return
	 */
	@RequestMapping("/shipments_daddress")
	public String shipmentsDaddress(Model model){
//		List<Daddress> daddressList = daddressService.findDaddressByStoreId(CacheUtils.getCacheUser().getStore().getStoreId());
		List<Daddress> daddressList = daddressService.findDaddressBySupplierId(CacheUtils.getCacheUser().getSupplier().getId());
		model.addAttribute("daddressList", daddressList);
		return  "/trade/trade_shipments_daddress";
	}
	
	/**
	 * 发货设置页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/shipments_setting")
	public ModelAndView shipments_setting(){
		try{
			ModelAndView model = new ModelAndView("/trade/trade_shipments_setting");
//			List<Daddress> list = daddressService.findDaddressByStoreId(CacheUtils.getCacheUser().getStore().getStoreId());
			List<Daddress> list = daddressService.findDaddressBySupplierId(CacheUtils.getCacheUser().getSupplier().getId());
			model.addObject("list", list);
			return model;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("发货设置失败！");
			throw new RuntimeException("发货设置失败!");
		}
	}
	
	/**
	 * 设置默认发货地址
	 * @param addressId
	 * @return
	 */
	@RequestMapping("/defaultDaddress")
	@ResponseBody
	public Map<String,Object> defaultDaddress(
			@RequestParam(required=false, value="addressId",defaultValue="")String addressId){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			Integer supplierId = CacheUtils.getCacheUser().getSupplier().getId();
//			daddressService.updateDefault(CacheUtils.getCacheUser().getStore().getStoreId(), Integer.valueOf(addressId));
			daddressService.updateDefaultSupplier(supplierId, Integer.valueOf(addressId));
			
			map.put("success", true);
		}catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
			log.error("默认地址设置失败！");
			throw new RuntimeException("默认地址设置失败!");
		}
		return map;
	}
	
	/**
	 * 删除发货地址
	 * @param addressId
	 * @return
	 */
	@RequestMapping("/deleteDaddress")
	@ResponseBody
	public Map<String,Object> deleteDaddress(@RequestParam(required=false, value="addressId",defaultValue="")String addressId){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			daddressService.deleteDaddress(Integer.valueOf(addressId));
			map.put("success", true);
		}catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
			log.error("删除地址失败！");
			throw new RuntimeException("删除地址成功!");
		}
		return map;
	}
	
	/**
	 * 添加发货地址
	 * @return
	 */
	@RequestMapping("/addAddress")
	public ModelAndView addAddress(){
		try{
			ModelAndView model = new ModelAndView("/trade/trade_daddress_add");
			
			List<Area> areas = areaService.queryAll();
//			model.addObject("storeId", CacheUtils.getCacheUser().getStore().getStoreId());
			model.addObject("supplierId", CacheUtils.getCacheUser().getSupplier().getId());
			model.addObject("areas",areas);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 保存发货地址
	 * @param model
	 * @param daddress
	 * @return
	 */
	@RequestMapping("/saveAddress")
	@ResponseBody
	public Map<String, Object> saveAddress(Model model,@ModelAttribute Daddress daddress){
		Map<String, Object> map = Maps.newHashMap();
		try{
			if(daddressService.findDefaultCountBySupplierId(CacheUtils.getCacheUser().getSupplier().getId())==0){ //判断店铺下是否有默认选中的项
				daddress.setIsDefault("1");
			}else{
				daddress.setIsDefault("0");
			}
			daddress.setStoreId(0);
			daddressService.saveDaddress(daddress);
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
	 * 修改发货地址
	 * @param addressId
	 * @return
	 */
	@RequestMapping("/updateAddress")
	public ModelAndView updateAddress(@RequestParam(required=false, value="id",defaultValue="")String addressId){
		try{
			ModelAndView model = new ModelAndView("/trade/trade_daddress_add");
			Daddress daddress = daddressService.findDaddressById(Integer.valueOf(addressId));
			List<Area> areas = areaService.queryAll();
//			model.addObject("storeId", CacheUtils.getCacheUser().getStore().getStoreId());
			model.addObject("supplierId", CacheUtils.getCacheUser().getSupplier().getId());
			model.addObject("daddress", daddress);
			model.addObject("areas",areas);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 修改发货地址
	 * @param model
	 * @param daddress
	 * @return
	 */
	@RequestMapping("/updateAdd")
	@ResponseBody
	public Map<String, Object> updateAdd(Model model,@ModelAttribute Daddress daddress){
		Map<String, Object> map = Maps.newHashMap();
		try{
			daddressService.updateDaddress(daddress);
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
	 * 默认物流公司
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/tradelogis")
	public ModelAndView tradelogis(){
		try{
			ModelAndView model = new ModelAndView("/trade/trade-logis-ltd");
//			StoreExtend storeExtend = storeExtendService.findByStoreId(CacheUtils.getCacheUser().getStore().getStoreId());
			SupplierExtend supplierExtend = supplierExtendService.findBySupplierId(CacheUtils.getCacheUser().getSupplier().getId());
			if(supplierExtend!=null){
				String[] expressIds = supplierExtend.getExpress().split(",");//将查询到的公司物流公司id几个拆分为字符串
				model.addObject("supplierExtend", supplierExtend);
				model.addObject("expressIds", expressIds);
			}
			List<Express> list = expressService.findExpressListByState(1,null);//查询所有物流公司
			
			model.addObject("list", list);
			return model;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("默认物流公司加载失败！");
			throw new RuntimeException("默认物流公司加载失败!");
		}
	}
	
	/**
	 * 保存店铺物流公司
	 * @param storeId
	 * @param cexpress
	 * @return
	 */
	@RequestMapping("/saveSupplierExtend")
	@ResponseBody
	public Map<String,Object> saveSupplierExtend(@RequestParam(required=false, value="supplierId",defaultValue="")String supplierId,
											  @RequestParam(required=false, value="cexpress[]",defaultValue="")String[] cexpress){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			SupplierExtend supplierExtend = new SupplierExtend();
			String str = "";//新建一个字符串用来存储物流公司id
			for(String expressId:cexpress){
				str +=  expressId + ",";
			}
			supplierExtend.setExpress(str);
			if("".equals(supplierId)){
//				supplierExtend.setStoreId(CacheUtils.getCacheUser().getStore().getStoreId());
				supplierExtend.setSupplierId(CacheUtils.getCacheUser().getSupplier().getId());
				supplierExtendService.saveSupplierExtend(supplierExtend);
			}else{
				supplierExtend.setSupplierId(Integer.valueOf(supplierId));
				supplierExtendService.updateSupplierExtend(supplierExtend);
			}
			map.put("success", true);
		}catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
			log.error("添加物流公司失败！");
			throw new RuntimeException("添加物流公司失败!");
		}
		return map;
	}
	
	/**
	 * 发货列表
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/receivecode")
	public ModelAndView receivecode(
			@RequestParam(required=false, value="pageNo",defaultValue="")String pageNoStr,
			@RequestParam(required=false, value="orderSn",defaultValue="")String orderSn,
			@RequestParam(required=false, value="orderState",defaultValue="40")String orderState){
		try{
			ModelAndView model = new ModelAndView("/trade/receive_code");
			Pager pager = new Pager();
			Order order = new Order();
			
			/**查询条件，放入实体中，**/
			if(StringUtils.isNotBlank(orderSn.trim())){
				order.setOrderSn(orderSn.trim());
				model.addObject("orderSn", orderSn.trim());
			}
			
			if(StringUtils.isNotBlank(orderState)&&!"99".equals(orderState)){
				int[] orderStates = {Integer.valueOf(orderState),60};
				order.setOrderStates(orderStates);
			}
				
			if(StringUtils.isNotBlank(pageNoStr)){
				pager.setPageNo(Integer.parseInt(pageNoStr));
			}
			
//			order.setStoreId(CacheUtils.getCacheUser().getStore().getStoreId());
			order.setSupplierId(CacheUtils.getCacheUser().getSupplier().getId());
			
			pager.setCondition(order);//实体加载在pager中
			pager.setPageSize(20);//每页默认显示20条
			
			List<Order> results = orderService.findOrderListByCodeStatus(pager);//.findOrderList(pager);// 结果集

			model.addObject("orderLists", results);// 结果集
			model.addObject("pageNo", pager.getPageNo());// 当前页
			model.addObject("pageSize", pager.getPageSize());// 每页显示条数
			model.addObject("recordCount", pager.getTotalRows());// 总数
            model.addObject("toUrl","/transport/receivecode");
            model.addObject("orderState",orderState);
			return model;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("发货加载失败！");
			throw new RuntimeException("发货加载失败!");
			
		}
	}
	
	/**
	 * 编辑发货信息
	 * @return
	 */
	@RequestMapping("/receivecode_deliver_update")
	public String receivecodeDeliverUpdate(
				Model model,
				@RequestParam(required=false, value="orderId",defaultValue="")String orderId){
//		Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
		//Integer supplierId = CacheUtils.getCacheUser().getSupplier().getId();
		Order order = orderService.findOrderDetail(Integer.valueOf(orderId),null,null);
		OrderDaddress daddress = orderDaddressService.findOrderDaddressById(order.getDaddressId());
//		List<Express> expressList = storeExtendService.findExpressByExpressIds(CacheUtils.getCacheUser().getStore().getStoreId());
		List<Express> expressList = supplierExtendService.findExpressByExpressIds(CacheUtils.getCacheUser().getSupplier().getId());
		
		
		//如果有码，查询发码列表
		
		ConsumerCodeSend cond = new ConsumerCodeSend();
		
		cond.setOrderId(Integer.parseInt( orderId));
		
		List<ConsumerCodeSend> codeList = consumerCodeSendService.findList(cond);
		
		
		model.addAttribute("expressList", expressList);
		model.addAttribute("daddress", daddress);
		model.addAttribute("codeList", codeList);
		model.addAttribute("order",order);
		return  "/trade/receivecode_deliver_update";
	}
	
	/**
	 * 修改码状态
	 * @param model
	 * @param consumerCode
	 * @return
	 */
	@RequestMapping("/updateCode")
	@ResponseBody
	public Map<String, Object> updateCode(Model model,@ModelAttribute ConsumerCodeSend consumerCode){
		Map<String, Object> map = Maps.newHashMap();
		map.put("success", false);
		try{
			consumerCodeSendService.receiveCode(consumerCode);
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			map.put("message", e.getMessage());
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
		}
		return map;
	}
}
