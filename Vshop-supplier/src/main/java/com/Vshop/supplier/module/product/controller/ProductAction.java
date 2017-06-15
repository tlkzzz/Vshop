package com.Vshop.supplier.module.product.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.Vshop.core.common.Constants;
import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.Area;
import com.Vshop.core.entity.GoodsAttribute;
import com.Vshop.core.entity.GoodsClass;
import com.Vshop.core.entity.GoodsSpec;
import com.Vshop.core.entity.StoreGoodsClass;
import com.Vshop.core.entity.Transport;
import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.GoodsCombination;
import com.Vshop.core.entity.vo.GoodsAttrVo;
import com.Vshop.core.entity.vo.GoodsSpecVo;
import com.Vshop.core.entity.vo.GoodsTypeVO;
import com.Vshop.core.entity.vo.SpecVo;
import com.Vshop.core.entity.vo.StoreGoodsClassVo;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.core.platform.info.PlatformInfo;
import com.Vshop.core.state.goods.GoodsState;
import com.Vshop.supplier.utils.CommonConstants;
import com.Vshop.supplier.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.area.service.AreaService;
import com.Vshop.service.module.goods.service.BrandService;
import com.Vshop.service.module.goods.service.GoodsClassService;
import com.Vshop.service.module.goods.service.GoodsCombinationService;
import com.Vshop.service.module.goods.service.GoodsImagesService;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.module.goods.service.GoodsSpecService;
import com.Vshop.service.module.goods.service.GoodsTypeService;
import com.Vshop.service.module.goods.service.SupplierService;
import com.Vshop.service.module.product.service.ProductService;
import com.Vshop.service.module.store.service.StoreGoodsClassService;
import com.Vshop.service.module.tostatic.service.ToStaticService;
import com.Vshop.service.module.trade.service.TransportService;
import com.Vshop.service.utils.goods.GoodsUtils;
import com.Vshop.service.utils.http.ToStaticSendToFront;
import com.Vshop.service.utils.page.Pager;

import lombok.extern.slf4j.Slf4j;

/**
 * 商品管理
 * 
 * 类名称：ProductAction 
 * 2015年08月14日10:42:24
 */
@Controller
@RequestMapping("/pro")
@Slf4j
public class ProductAction {

	String message = "success";
	@Autowired
	private GoodsClassService goodsClassService;
	
	@Autowired
	private StoreGoodsClassService storeGoodsClassService;
	
	@Autowired
	private TransportService transportService;
	
	@Resource
	private GoodsService goodsService;
	
	@Resource
	private GoodsSpecService goodsSpecService;
	
	@Resource
	private GoodsImagesService imagesService;
	
	@Resource
	private BrandService brandService;
	
	@Resource
	private GoodsTypeService goodsTypeService;
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private ProductService productService;
	
	@Autowired
	private ToStaticService toStaticService;
	
	@Autowired
	private GoodsCombinationService goodsCombinationService;
	
	@Resource
    private SupplierService supplierService;

	/**
	 * 发布商品
	 * @return
	 */
	@RequestMapping(value = "/sell")
	public ModelAndView sell() {
		ModelAndView model = new ModelAndView("/product/pro-sell-index");

		List<GoodsClass> list = goodsClassService.findList(0);
		model.addObject("datas", list);
		return model;
	}

	/**
	 * 
	 * @Title: findChildClass
	 * @param @param id
	 * @param @param model
	 * @param @return
	 * @param @throws JsonGenerationException
	 * @param @throws JsonMappingException
	 * @param @throws Exception 设定文件
	 * @return Map<String,String> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/findChildClass")
	public @ResponseBody
	Map<String, String> findChildClass(@RequestParam(value = "id") String id,
			Model model) throws JsonGenerationException, JsonMappingException,
			Exception {
		Map<String, String> map = new HashMap<String, String>();

		List<GoodsClass> classList = goodsClassService.findList(Integer.parseInt(id));
		String json = "null";
		if (classList != null && classList.size() > 0) {
			json = JsonUtils.toJsonStr(classList);
		}
		map.put("result", json);
		map.put(message, "true");
		return map;
	}
	
	
	/**
	 * 发布商品
	 * @param gcId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selldetail")
	public ModelAndView selldetail(@RequestParam(value = "gcId") String gcId) throws Exception {
		ModelAndView model = new ModelAndView("/product/pro_sell_detail");
		model.addObject("gcId", gcId);
		//店铺id
//		Integer storeId =  CacheUtils.getCacheUser().getStore().getStoreId();
		Integer supplierId =  CacheUtils.getCacheUser().getSupplier().getId();
		
		//商品分类
		GoodsClass goodsClass = goodsClassService.findById(Integer.valueOf(gcId));
		//类型id
		model.addObject("typeId", goodsClass.getTypeId());
		//分类全名称
		String catename = goodsClass.getGcName();
		model.addObject("catename", catename);
		
		//本店商品分类
//		StoreGoodsClassVo storeGoodsClassVo = new StoreGoodsClassVo();
//		storeGoodsClassVo.setStoreId(storeId);
//		List<StoreGoodsClassVo> goodsClassVos = storeGoodsClassService.queryClasssList(storeGoodsClassVo);
		/*
		 * 将这个list的结构改为map
		 * 此map的结构为:String, list
		 * 键为:	分类的父id
		 * 值为:List<StoreGoodsClassVo>
		 */
		/*
		Map<String, List<StoreGoodsClassVo>> StoreGoodsClassVoMap = new HashMap<String, List<StoreGoodsClassVo>>();
		for(int i = 0; i < goodsClassVos.size(); i++){
			//得到当前这个实体类
			StoreGoodsClassVo sgc = goodsClassVos.get(i);
			//获得父级id
			String parentId = sgc.getParentId()+"";
			//是否已经包含这个key
			if(StoreGoodsClassVoMap.containsKey(parentId)){
				//如果包含这个key,则取出他的list值,并且add当前这个对象
				List<StoreGoodsClassVo> list = StoreGoodsClassVoMap.get(parentId);
				list.add(sgc);
				StoreGoodsClassVoMap.put(parentId, list);
			}else{
				//否则新建一个key,新建一个list,并put进去
				List<StoreGoodsClassVo> list = new ArrayList<StoreGoodsClassVo>();
				list.add(sgc);
				StoreGoodsClassVoMap.put(parentId, list);
			}
		}
		model.addObject("StoreGoodsClassVoMap", StoreGoodsClassVoMap);
		*/
		
		
		//获得类型id
		Integer typeId = goodsClass.getTypeId();
		
		/*
		 * 通过类型id获得类型下的品牌,规格,属性
		 * 首先通过类型id获得goodsTypeVo
		 * 在这个超类中,有3个list,是品牌,规格,属性
		 */
		GoodsTypeVO goodsTypeVO = goodsTypeService.selectTypeFetchOther(typeId);
		List<Brand> brandsSupplier = supplierService.findBrandListBySupplier(ImmutableMap.of("id", supplierId));
		
		if(goodsTypeVO != null){
				
			if(goodsTypeVO.getBrandList() != null){
				//获得该类型下的品牌
				List<Brand> brands = goodsTypeVO.getBrandList();
				List<Brand> brandsShow = new ArrayList<Brand>();
				
				for (Brand brand : brands) {
					
					for (Brand brandSup : brandsSupplier) {
						if(brandSup.getBrandId().equals(brand.getBrandId()) ){
							
							brandsShow.add(brand);
						}
					}
				}
				
				//放入model
				model.addObject("brands", brandsShow);
			}
			
			if(goodsTypeVO.getSpecList() != null){
				//获得该类型下的规格
				List<SpecVo> specs = goodsTypeVO.getSpecList();
				//放入model
				model.addObject("specs", specs);
			}
			
			if(goodsTypeVO.getAttributes() != null){
				//获得该类型下的属性
				List<GoodsAttribute> goodsAttributes = goodsTypeVO.getAttributes();
				//放入model
				model.addObject("goodsAttributes", goodsAttributes);
			}
		}
		
		//运费模板
        Transport transport = transportService.getDefTransportByStoreId(PlatformInfo.PLATFORM_STORE_ID);
		model.addObject("transport", transport);
		
        //一级地区加载
        List<Area> areas = areaService.queryAll();
        model.addObject("areas", areas);
		
		model.addObject("listimgSize", 0);//默认5个图片
		//图片base路径
		model.addObject("imageServer", CommonConstants.IMG_SERVER);


		return model;
	}
	
	/**
	 * 初始化goods对象
	 * @param storeId 店铺id
	 * @param storeClassId 店铺分类id
	 * @param goodsName 商品名称
	 * @param goodsState 商品状态，0开启，1违规下架
	 * @param goodsShow 商品上架1上架0下架
	 * @return
	 */
	private Goods initGoods(Integer supplierId, String storeClassId, String goodsName, int goodsState, int goodsShow){
		//准备查询条件
		Goods goods = new Goods();
		//设置当前店铺
//		goods.setStoreId(storeId);
		goods.setSupplierId(supplierId);
		//设置状态开启
		goods.setGoodsState(goodsState);
		//设置上架状态
		goods.setGoodsShow(goodsShow);
		//是否有搜索
		//本店分类
		if(StringUtils.isNotEmpty(storeClassId)){
			goods.setStoreClassId(Integer.parseInt(storeClassId));
		}
		//关键词搜索
		if(StringUtils.isNotEmpty(goodsName)){
			goods.setGoodsName(goodsName);
		}
		//时间降序
		goods.setSortField("goodsAddTime");
		goods.setOrderBy("desc");
		return goods;
	}
	
	/**
	 * 初始化商品分类
	 * @param storeId
	 * @return
	 */
	private Map<String, List<StoreGoodsClassVo>> initGoodsClass(Integer storeId){
		
		//本店商品分类
		StoreGoodsClassVo storeGoodsClassVo = new StoreGoodsClassVo();
		storeGoodsClassVo.setStoreId(storeId);
		List<StoreGoodsClassVo> goodsClassVos = storeGoodsClassService.queryClasssList(storeGoodsClassVo);
		/*
		 * 将这个list的结构改为map
		 * 此map的结构为:String, list
		 * 键为:	分类的父id
		 * 值为:List<StoreGoodsClassVo>
		 */
		Map<String, List<StoreGoodsClassVo>> StoreGoodsClassVoMap = new HashMap<String, List<StoreGoodsClassVo>>();
		for(int i = 0; i < goodsClassVos.size(); i++){
			//得到当前这个实体类
			StoreGoodsClassVo sgc = goodsClassVos.get(i);
			//获得父级id
			String parentId = sgc.getParentId()+"";
			//是否已经包含这个key
			if(StoreGoodsClassVoMap.containsKey(parentId)){
				//如果包含这个key,则取出他的list值,并且add当前这个对象
				List<StoreGoodsClassVo> list = StoreGoodsClassVoMap.get(parentId);
				list.add(sgc);
				StoreGoodsClassVoMap.put(parentId, list);
			}else{
				//否则新建一个key,新建一个list,并put进去
				List<StoreGoodsClassVo> list = new ArrayList<StoreGoodsClassVo>();
				list.add(sgc);
				StoreGoodsClassVoMap.put(parentId, list);
			}
		}
		
		return StoreGoodsClassVoMap;
	}
	
	/**
	 * 初始化pager
	 * @param pageNoStr
	 * @param storeId
	 * @param storeClassId
	 * @param goodsName
	 * @return
	 */
	private Pager initPager(String pageNoStr, String storeClassId, String goodsName, Goods goods){
		Pager pager = new Pager();
		if (StringUtils.isNotBlank(pageNoStr)) {
            pager.setPageNo(Integer.parseInt(pageNoStr));
        }
		pager.setCondition(goods);
		return pager;
	}
	
	/**
	 * 出售中的商品
	 * @param pageNoStr
	 * @param storeClassId
	 * @param goodsName
	 * @return
	 */
	@RequestMapping(value = "/sale")
	public ModelAndView sale(
            @RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNoStr,
            @RequestParam(required = false, value = "storeClassId", defaultValue = "") String storeClassId,
            @RequestParam(required = false, value = "goodsName", defaultValue = "") String goodsName
            ) {
		ModelAndView model = new ModelAndView("/product/pro-sale");
		//当前店铺id
		//Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
		Integer suplierId = CacheUtils.getCacheUser().getSupplier().getId();
		
		//准备查询条件
		Goods goods = new Goods();
		goods = initGoods(suplierId, storeClassId, goodsName, GoodsState.GOODS_OPEN_STATE, GoodsState.GOODS_ON_SHOW);
		
		Pager pager = new Pager();
		pager = initPager(pageNoStr,  storeClassId, goodsName, goods);
		//查找list
		List<Goods> goodsList = goodsService.findGoodPagerList(pager);
		/*
		 * 将这个list的结构改为map
		 * 此map的结构为:String, list
		 * 键为:	分类的父id
		 * 值为:List<StoreGoodsClassVo>
		 */
//		Map<String, List<StoreGoodsClassVo>> StoreGoodsClassVoMap = new HashMap<String, List<StoreGoodsClassVo>>();
//		StoreGoodsClassVoMap = initGoodsClass(storeId);
		
		model.addObject("goodsList", goodsList);
//		model.addObject("StoreGoodsClassVoMap", StoreGoodsClassVoMap);
		model.addObject("storeClassId", storeClassId);
		model.addObject("goodsName", goodsName);
		model.addObject("imgServer", CommonConstants.IMG_SERVER);
		model.addObject("imgSrc", Constants.SPECIMAGE_PATH);
        model.addObject("pageNo", pager.getPageNo());// 当前页
        model.addObject("pageSize", pager.getPageSize());// 每页显示条数
        model.addObject("recordCount", pager.getTotalRows());// 总数
    	model.addObject("pager", pager);
        model.addObject("toUrl", "/pro/sale");// 跳转URL
		return model;
	}
	
	/**
	 * 仓库中的商品
	 * @param pageNoStr
	 * @param storeClassId
	 * @param goodsName
	 * @return
	 */
	@RequestMapping(value = "/store")
	public ModelAndView store(
            @RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNoStr,
            @RequestParam(required = false, value = "storeClassId", defaultValue = "") String storeClassId,
            @RequestParam(required = false, value = "goodsName", defaultValue = "") String goodsName
            ) {
		ModelAndView model = new ModelAndView("/product/pro-store");
		//当前店铺id
//		Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
		Integer suplierId = CacheUtils.getCacheUser().getSupplier().getId();
		
		//准备查询条件
		Goods goods = new Goods();
		goods = initGoods(suplierId, storeClassId, goodsName, GoodsState.GOODS_OPEN_STATE, GoodsState.GOODS_OFF_SHOW);
		
		Pager pager = new Pager();
		pager = initPager(pageNoStr,  storeClassId, goodsName, goods);
		//查找list
		List<Goods> goodsList = goodsService.findGoodPagerList(pager);
		/*
		 * 将这个list的结构改为map
		 * 此map的结构为:String, list
		 * 键为:	分类的父id
		 * 值为:List<StoreGoodsClassVo>
		 */
//		Map<String, List<StoreGoodsClassVo>> StoreGoodsClassVoMap = new HashMap<String, List<StoreGoodsClassVo>>();
//		StoreGoodsClassVoMap = initGoodsClass(storeId);
		
		model.addObject("goodsList", goodsList);
//		model.addObject("StoreGoodsClassVoMap", StoreGoodsClassVoMap);
		model.addObject("storeClassId", storeClassId);
		model.addObject("goodsName", goodsName);
		model.addObject("imgServer", CommonConstants.IMG_SERVER);
		model.addObject("imgSrc", Constants.SPECIMAGE_PATH);
        model.addObject("pageNo", pager.getPageNo());    // 当前页
        model.addObject("pageSize", pager.getPageSize());// 每页显示条数
        model.addObject("recordCount", pager.getTotalRows());// 总数
    	model.addObject("pager", pager);
        model.addObject("toUrl", "/pro/store");          // 跳转URL
		return model;
	}
	
	/**
	 * 违规下架的商品
	 * @param pageNoStr
	 * @param storeClassId
	 * @param goodsName
	 * @return
	 */
	@RequestMapping(value = "/offShow")
	public ModelAndView offShowGoods(
            @RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNoStr,
            @RequestParam(required = false, value = "storeClassId", defaultValue = "") String storeClassId,
            @RequestParam(required = false, value = "goodsName", defaultValue = "") String goodsName
            ) {
		ModelAndView model = new ModelAndView("/product/pro-offshow");
		//当前店铺id
//		Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
		Integer suplierId = CacheUtils.getCacheUser().getSupplier().getId();
		
		//准备查询条件
		Goods goods = new Goods();
		goods = initGoods(suplierId, storeClassId, goodsName, GoodsState.GOODS_CLOSE_STATE, GoodsState.GOODS_OFF_SHOW);
		
		Pager pager = new Pager();
		pager = initPager(pageNoStr,  storeClassId, goodsName, goods);
		//查找list
		List<Goods> goodsList = goodsService.findGoodPagerList(pager);
		/*
		 * 将这个list的结构改为map
		 * 此map的结构为:String, list
		 * 键为:	分类的父id
		 * 值为:List<StoreGoodsClassVo>
		 */
//		Map<String, List<StoreGoodsClassVo>> StoreGoodsClassVoMap = new HashMap<String, List<StoreGoodsClassVo>>();
//		StoreGoodsClassVoMap = initGoodsClass(storeId);
		
		model.addObject("goodsList", goodsList);
//		model.addObject("StoreGoodsClassVoMap", StoreGoodsClassVoMap);
		model.addObject("storeClassId", storeClassId);
		model.addObject("goodsName", goodsName);
		model.addObject("imgServer", CommonConstants.IMG_SERVER);
		model.addObject("imgSrc", Constants.SPECIMAGE_PATH);
        model.addObject("pageNo", pager.getPageNo());    // 当前页
        model.addObject("pageSize", pager.getPageSize());// 每页显示条数
        model.addObject("recordCount", pager.getTotalRows());// 总数
    	model.addObject("pager", pager);
        model.addObject("toUrl", "/pro/offShow");          // 跳转URL
		return model;
	}

	/**
	 * message:0:失败, 1:成功
	 * @param request
	 * @param goods
	 * @return
	 */
	@RequestMapping(value = "/savePro")
	public @ResponseBody Map<String, String> savePro(HttpServletRequest request, Goods goods, 
			@RequestParam(value="prepareUp", required=false, defaultValue="") String prepareUpTime){
		Map<String, String> map = Maps.newHashMap();
		try{
//			String goodsName = goods.getGoodsName();
//			SensitivewordFilter filter = new SensitivewordFilter(CommonConstants.FILE_BASEPATH + Constants.SENSITIVE_UPLOAD_URL);
//			Set<String> set = filter.getSensitiveWord(goodsName, 1);
//			int size = set.size();
//			if(size>0){
//				//将失败的信号传到前台
//				map.put("message", "0");
//				return map;
//			}
			//设置utf-8
			request.setCharacterEncoding("utf-8");
			String goodsSpecJson = request.getParameter("goodsSpecJson");
			goods.setGoodsStorePrice(new BigDecimal(0));
			//获得当前店铺id
//			Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
			//获得当前店铺名称
//			String storeName = CacheUtils.getCacheUser().getStore().getStoreName();
			goods.setGoodsCommend(0);
			Integer supplierId = CacheUtils.getCacheUser().getSupplier().getId();
			goods.setSupplierId(supplierId);
			goods.setSupplierName(CacheUtils.getCacheUser().getSupplier().getName());
			
			goods.setBrandName(brandService.findById(goods.getBrandId()).getBrandName());
			
			goods.setGoodsState(GoodsState.GOODS_APPLY_PREPARE);
			goods.setGoodsShow(1);
			
			Integer storeId = PlatformInfo.PLATFORM_STORE_ID;
			String storeName = PlatformInfo.PLATFORM_STORE_NAME;
			
			//设置到goods中
			goods.setStoreId(storeId);
			goods.setStoreName(storeName);
			goods.setCreateTime(new Date().getTime());
			//上架时间
			if(StringUtils.isNotEmpty(prepareUpTime)){
				goods.setUpdateTime(DateUtils.strToLong(prepareUpTime));
			}
			//调用保存的service的方法,返回状态0为失败1为成功
			Integer goodsId = productService.savePreGoods(goods, goodsSpecJson);
			
			//判断是否成功
			if(goodsId == 0){
				//将失败的信号传入前台
				map.put("message", "0");
				return map;
			}
			
			/**生成静态页面*/
			
			ToStaticSendToFront.onegoodsDetailStatic(goodsId, goods.getStoreId());
			//将成功的信号传导前台
			map.put("message", "1");
			return map;
		}catch(Exception e){
			e.printStackTrace();
			//将失败的信号传到前台
			map.put("message", "0");
			return map;
		}
	}
	
	
	/**
	 * @throws Exception 
	 * 修改商品
	 * 填写基本信息
	 * @Title: selldetail
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/editgoods")
	public ModelAndView editgoods(@RequestParam(value = "goodsId") String goodsId) throws Exception {
		ModelAndView model = new ModelAndView("/product/pro_edit_goods");
		//根据goodsid获得goods
		Goods goods = new Goods();
		goods.setGoodsId(Integer.parseInt(goodsId));
		//获得当前店铺id
//		Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
		Integer storeId =  PlatformInfo.PLATFORM_STORE_ID;
		Integer supplierId = CacheUtils.getCacheUser().getSupplier().getId();
//		goods.setStoreId(storeId);
		goods.setSupplierId(supplierId);
		goods = goodsService.findOneGoodByCondition(goods);
		//放入model
		model.addObject("goods", goods);
		
		//分类id
		Integer gcId = goods.getGcId();
		//放入model
		model.addObject("gcId", gcId);
		
		//分类名称
		String catename = goods.getGcName();
		//放入model
		model.addObject("catename", catename);
		
		//本店商品分类
//		StoreGoodsClassVo storeGoodsClassVo = new StoreGoodsClassVo();
//		storeGoodsClassVo.setStoreId(storeId);
//		List<StoreGoodsClassVo> goodsClassVos = storeGoodsClassService.queryClasssList(storeGoodsClassVo);
		/*
		 * 将这个list的结构改为map
		 * 此map的结构为:String, list
		 * 键为:	分类的父id
		 * 值为:List<StoreGoodsClassVo>
		 */
//		Map<String, List<StoreGoodsClassVo>> StoreGoodsClassVoMap = new HashMap<String, List<StoreGoodsClassVo>>();
//		if(goodsClassVos != null){
//			for(int i = 0; i < goodsClassVos.size(); i++){
//				//得到当前这个实体类
//				StoreGoodsClassVo sgc = goodsClassVos.get(i);
//				//获得父级id
//				String parentId = sgc.getParentId()+"";
//				//是否已经包含这个key
//				if(StoreGoodsClassVoMap.containsKey(parentId)){
//					//如果包含这个key,则取出他的list值,并且add当前这个对象
//					List<StoreGoodsClassVo> list = StoreGoodsClassVoMap.get(parentId);
//					list.add(sgc);
//					StoreGoodsClassVoMap.put(parentId, list);
//				}else{
//					//否则新建一个key,新建一个list,并put进去
//					List<StoreGoodsClassVo> list = new ArrayList<StoreGoodsClassVo>();
//					list.add(sgc);
//					StoreGoodsClassVoMap.put(parentId, list);
//				}
//			}
//		}

//		model.addObject("StoreGoodsClassVoMap", StoreGoodsClassVoMap);
		
		//规格
		//通过goodsid在数据库中查出goods_spec
		//得到goodsSpec的list
		List<GoodsSpec> goodsSpecs = goodsSpecService.findListByGoodsId(goods.getGoodsId());
		for(int i = 0; i < goodsSpecs.size(); i++){
			if(goodsSpecs.get(i).getSpecGoodsSpec() != null && !goodsSpecs.get(i).getSpecGoodsSpec().trim().equals("")){
				goodsSpecs.get(i).setSpecValueIdStr(GoodsUtils.getThisGoodsAllSpecValueId(goodsSpecs.get(i).getSpecGoodsSpec()));
			}
		}
		//放入model
		model.addObject("goodsSpecs", goodsSpecs);
		if(goodsSpecs.size() == 1){
			model.addObject("goodsstorenum", goodsSpecs.get(0).getSpecGoodsStorage());
		}
		
		String goodsAttr = goods.getGoodsAttr();
		List<GoodsAttrVo> attrVoList = Lists.newArrayList();
		if(goodsAttr != null && !goodsAttr.trim().equals("")){
			//得到超类
			attrVoList = GoodsUtils.goodsAttrStrToGoodsAttrVoClass(goodsAttr);
			model.addObject("attrVoList", attrVoList);
		}
		String goodsSpec =  goods.getGoodsSpec();
		/**判断是否有规格属性，如果没有返回的list是null*/
		if(goodsSpec != null && !goodsSpec.trim().equals("")){
			Map<String, List<GoodsSpecVo>> specMap = GoodsUtils.goodsSpecStrToMapList(goodsSpec);
			System.out.println(specMap);
			model.addObject("specMap", specMap);
		}
		//规格颜色的图片
		String goodsColImg = goods.getGoodsColImg();
		if(goodsColImg != null && !goodsColImg.trim().equals("")){
			//得到map
			Map<String, String> colImgMap = GoodsUtils.goodsColImgStrToMap(goodsColImg);
			model.addObject("colImgMap", colImgMap);
		}
		
		/*
		 * 通过类型id获得类型下的品牌,规格,属性
		 * 首先通过类型id获得goodsTypeVo
		 * 在这个超类中,有3个list,是品牌,规格,属性
		 */
		GoodsTypeVO goodsTypeVO = goodsTypeService.selectTypeFetchOther(goods.getTypeId());
		if(goodsTypeVO!=null){
				//获得该类型下的品牌
				List<Brand> brands = null;
				if(goodsTypeVO.getBrandList().size()!=0){
					brands=goodsTypeVO.getBrandList();
				}
				//放入model
				model.addObject("brands", brands);
				//获得该类型下的规格
				List<SpecVo> specs = goodsTypeVO.getSpecList();
				//放入model
				model.addObject("specs", specs);
				
				//获得该类型下的属性
				List<GoodsAttribute> goodsAttributes = goodsTypeVO.getAttributes();
				//放入model
				model.addObject("goodsAttributes", goodsAttributes);
		} 
         //一级地区加载
        List<Area> areas = areaService.queryAll();
        model.addObject("areas", areas);
        
		//商品分类
        Map<String, List<StoreGoodsClass>> classmap = storeGoodsClassService.queryStoreClass(storeId);
		model.addObject("classmap", classmap);//商品分类
		
		//商品图片
		String[] imageMore = goods.getGoodsImageMore().split(",");
		List<String> imageList = Arrays.asList(imageMore);
		model.addObject("imageList", imageList);
		
		//运费模板
		Transport transport = transportService.getDefTransportByStoreId(storeId);
		model.addObject("transport", transport);
		
		//图片server路径
		String imgServer = CommonConstants.IMG_SERVER;
		model.addObject("imgServer", imgServer);
		
		//图片目录
		String imgSrc = Constants.SPECIMAGE_PATH;
		model.addObject("imgSrc", imgSrc);
		
		return model;
	}
	
	
	/**
	 * @throws Exception 
	 * 查看商品
	 * 填写基本信息
	 * @Title: selldetail
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/lookgoods")
	public ModelAndView lookgoods(@RequestParam(value = "goodsId") String goodsId) throws Exception {
		ModelAndView model = new ModelAndView("/product/pro_look_goods");
		//根据goodsid获得goods
		Goods goods = new Goods();
		goods.setGoodsId(Integer.parseInt(goodsId));
		//获得当前店铺id
//		Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
		Integer storeId =  PlatformInfo.PLATFORM_STORE_ID;
		Integer supplierId = CacheUtils.getCacheUser().getSupplier().getId();
//		goods.setStoreId(storeId);
		goods.setSupplierId(supplierId);
		goods = goodsService.findOneGoodByCondition(goods);
		//放入model
		model.addObject("goods", goods);
		
		//分类id
		Integer gcId = goods.getGcId();
		//放入model
		model.addObject("gcId", gcId);
		
		//分类名称
		String catename = goods.getGcName();
		//放入model
		model.addObject("catename", catename);
		
		//本店商品分类
//		StoreGoodsClassVo storeGoodsClassVo = new StoreGoodsClassVo();
//		storeGoodsClassVo.setStoreId(storeId);
//		List<StoreGoodsClassVo> goodsClassVos = storeGoodsClassService.queryClasssList(storeGoodsClassVo);
		/*
		 * 将这个list的结构改为map
		 * 此map的结构为:String, list
		 * 键为:	分类的父id
		 * 值为:List<StoreGoodsClassVo>
		 */
//		Map<String, List<StoreGoodsClassVo>> StoreGoodsClassVoMap = new HashMap<String, List<StoreGoodsClassVo>>();
//		if(goodsClassVos != null){
//			for(int i = 0; i < goodsClassVos.size(); i++){
//				//得到当前这个实体类
//				StoreGoodsClassVo sgc = goodsClassVos.get(i);
//				//获得父级id
//				String parentId = sgc.getParentId()+"";
//				//是否已经包含这个key
//				if(StoreGoodsClassVoMap.containsKey(parentId)){
//					//如果包含这个key,则取出他的list值,并且add当前这个对象
//					List<StoreGoodsClassVo> list = StoreGoodsClassVoMap.get(parentId);
//					list.add(sgc);
//					StoreGoodsClassVoMap.put(parentId, list);
//				}else{
//					//否则新建一个key,新建一个list,并put进去
//					List<StoreGoodsClassVo> list = new ArrayList<StoreGoodsClassVo>();
//					list.add(sgc);
//					StoreGoodsClassVoMap.put(parentId, list);
//				}
//			}
//		}

//		model.addObject("StoreGoodsClassVoMap", StoreGoodsClassVoMap);
		
		//规格
		//通过goodsid在数据库中查出goods_spec
		//得到goodsSpec的list
		List<GoodsSpec> goodsSpecs = goodsSpecService.findListByGoodsId(goods.getGoodsId());
		for(int i = 0; i < goodsSpecs.size(); i++){
			if(goodsSpecs.get(i).getSpecGoodsSpec() != null && !goodsSpecs.get(i).getSpecGoodsSpec().trim().equals("")){
				goodsSpecs.get(i).setSpecValueIdStr(GoodsUtils.getThisGoodsAllSpecValueId(goodsSpecs.get(i).getSpecGoodsSpec()));
			}
		}
		//放入model
		model.addObject("goodsSpecs", goodsSpecs);
		if(goodsSpecs.size() == 1){
			model.addObject("goodsstorenum", goodsSpecs.get(0).getSpecGoodsStorage());
		}
		
		String goodsAttr = goods.getGoodsAttr();
		List<GoodsAttrVo> attrVoList = Lists.newArrayList();
		if(goodsAttr != null && !goodsAttr.trim().equals("")){
			//得到超类
			attrVoList = GoodsUtils.goodsAttrStrToGoodsAttrVoClass(goodsAttr);
			model.addObject("attrVoList", attrVoList);
		}
		String goodsSpec =  goods.getGoodsSpec();
		/**判断是否有规格属性，如果没有返回的list是null*/
		if(goodsSpec != null && !goodsSpec.trim().equals("")){
			Map<String, List<GoodsSpecVo>> specMap = GoodsUtils.goodsSpecStrToMapList(goodsSpec);
			System.out.println(specMap);
			model.addObject("specMap", specMap);
		}
		//规格颜色的图片
		String goodsColImg = goods.getGoodsColImg();
		if(goodsColImg != null && !goodsColImg.trim().equals("")){
			//得到map
			Map<String, String> colImgMap = GoodsUtils.goodsColImgStrToMap(goodsColImg);
			model.addObject("colImgMap", colImgMap);
		}
		
		/*
		 * 通过类型id获得类型下的品牌,规格,属性
		 * 首先通过类型id获得goodsTypeVo
		 * 在这个超类中,有3个list,是品牌,规格,属性
		 */
		GoodsTypeVO goodsTypeVO = goodsTypeService.selectTypeFetchOther(goods.getTypeId());
		if(goodsTypeVO!=null){
				//获得该类型下的品牌
				List<Brand> brands = null;
				if(goodsTypeVO.getBrandList().size()!=0){
					brands=goodsTypeVO.getBrandList();
				}
				//放入model
				model.addObject("brands", brands);
				//获得该类型下的规格
				List<SpecVo> specs = goodsTypeVO.getSpecList();
				//放入model
				model.addObject("specs", specs);
				
				//获得该类型下的属性
				List<GoodsAttribute> goodsAttributes = goodsTypeVO.getAttributes();
				//放入model
				model.addObject("goodsAttributes", goodsAttributes);
		} 
         //一级地区加载
        List<Area> areas = areaService.queryAll();
        model.addObject("areas", areas);
        
		//商品分类
        Map<String, List<StoreGoodsClass>> classmap = storeGoodsClassService.queryStoreClass(storeId);
		model.addObject("classmap", classmap);//商品分类
		
		//商品图片
		String[] imageMore = goods.getGoodsImageMore().split(",");
		List<String> imageList = Arrays.asList(imageMore);
		model.addObject("imageList", imageList);
		
		//运费模板
		Transport transport = transportService.getDefTransportByStoreId(storeId);
		model.addObject("transport", transport);
		
		//图片server路径
		String imgServer = CommonConstants.IMG_SERVER;
		model.addObject("imgServer", imgServer);
		
		//图片目录
		String imgSrc = Constants.SPECIMAGE_PATH;
		model.addObject("imgSrc", imgSrc);
		
		return model;
	}
	
	/**
	 * 
	 * @Title: savePro 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param parentid
	 * @param @return
	 * @param @throws JsonGenerationException
	 * @param @throws JsonMappingException
	 * @param @throws Exception    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/updatePro", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> updatePro(HttpServletRequest request, Goods goods,
			@RequestParam(value="prepareUp", required=false, defaultValue="") String prepareUpTime){
		Map<String, String> map = Maps.newHashMap();
		try{
			//设置utf-8
			request.setCharacterEncoding("utf-8");
			String goodsSpecJson = request.getParameter("goodsSpecJson");
			//获得当前店铺id
			//Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
			//获得当前店铺名称
			//String storeName = CacheUtils.getCacheUser().getStore().getStoreName();
			Integer supplierId = CacheUtils.getCacheUser().getSupplier().getId();
			goods.setBrandName(brandService.findById(goods.getBrandId()).getBrandName());
			goods.setSupplierId(supplierId);
			
			//设置到goods中
			//goods.setStoreId(storeId);
			//goods.setStoreName(storeName);
			//上架时间
			if(StringUtils.isNotEmpty(prepareUpTime)){
				goods.setUpdateTime(DateUtils.strToLong(prepareUpTime));
			}
			//调用保存的service的方法,返回状态0为失败1为成功
			Integer state = productService.updateGoods(goods, goodsSpecJson);
			//判断是否成功
			if(state == 0){
				//将失败的信号传入前台
				map.put("message", "0");
				return map;
			}
			/**生成静态页面*/
			ToStaticSendToFront.onegoodsDetailStatic(goods.getGoodsId(), goods.getStoreId());
			//将成功的信号传导前台
			map.put("message", "1");
			return map;
		}catch(Exception e){
			e.printStackTrace();
			//将失败的信号传到前台
			map.put("message", "0");
			return map;
		}
	}

	/**
	 * 上架商品
	 */
    @RequestMapping("/upGoods")
    public @ResponseBody Map<String,Object> upGoods(
    		@RequestParam(value="goodsIds",required=true) String goodsIdsStr){
        Map<String,Object> map = Maps.newHashMap();
//        Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
        Integer supplierId = CacheUtils.getCacheUser().getSupplier().getId();
        try{
        	//循环删除
        	if(!goodsIdsStr.equals("")){
        		String[] goodsIds = goodsIdsStr.split(",");
        		for(int i = 0; i < goodsIds.length; i++){
        			Goods goods = new Goods();
        			goods.setGoodsId(Integer.parseInt(goodsIds[i]));
        			goods.setGoodsShow(GoodsState.GOODS_ON_SHOW);
        			goods.setUpdateTime(new Date().getTime());
        			goods.setStartTime(new Date().getTime());
        			goodsService.updateGoods(goods);
        			/**生成静态页面*/
//        			ToStaticSendToFront.onegoodsDetailStatic(goods.getGoodsId(), supplierId);
        		}
        	}
            map.put("success",true);
        }catch(Exception e){
            log.error("添加属性值失败",e.toString());
            map.put("success",false);
            map.put("result","上架商品失败");
        }
        return map;
    }
	
	/**
	 * 下架商品
	 */
    @RequestMapping("/downGoods")
    public @ResponseBody Map<String,Object> downGoods(
    		@RequestParam(value="goodsIds",required=true) String goodsIdsStr){
        Map<String,Object> map = Maps.newHashMap();
        try{
        	//循环删除
        	if(!goodsIdsStr.equals("")){
        		String[] goodsIds = goodsIdsStr.split(",");
        		for(int i = 0; i < goodsIds.length; i++){
        			Goods goods = new Goods();
        			goods.setGoodsId(Integer.parseInt(goodsIds[i]));
        			goods.setGoodsShow(GoodsState.GOODS_OFF_SHOW);
        			goods.setUpdateTime(new Date().getTime());
        			goods.setEndTime(new Date().getTime());
        			goodsService.updateGoods(goods);
        			toStaticService.deleteGoodsDetailStaticPage(goods.getGoodsId());
        		}
        	}
            map.put("success",true);
        }catch(Exception e){
            log.error("添加属性值失败",e.toString());
            map.put("success",false);
            map.put("result","下架商品失败");
        }
        return map;
    }
	
	/**
	 * 删除商品
	 */
    @RequestMapping("/deleteGoods")
    public @ResponseBody Map<String,Object> deleteGoods(
    		@RequestParam(value="goodsIds",required=true) String goodsIdsStr){
        Map<String,Object> map = Maps.newHashMap();
        try{
        	//循环删除
        	if(!goodsIdsStr.equals("")){
        		String[] goodsIds = goodsIdsStr.split(",");
        		for(int i = 0; i < goodsIds.length; i++){
        			goodsService.deleteGoods(Integer.parseInt(goodsIds[i]));
        			toStaticService.deleteGoodsDetailStaticPage(Integer.parseInt(goodsIds[i]));
        		}
        	}
            map.put("success",true);
        }catch(Exception e){
            log.error("添加属性值失败",e.toString());
            map.put("success",false);
            map.put("result","删除商品失败");
        }
        return map;
    }
    
    /**
     * 跳转修改组合商品页面
     */
    @RequestMapping("/updateCombinationIndex")
    public ModelAndView updateCombinationIndex(Integer storeId,Integer goodsId){
    	
    	ModelAndView modelAndView = new ModelAndView("/product/pro-combination");
    	
    	/**得到所有店铺下的id*/
    	Pager pager = new Pager();
    	pager.setPageSize(Integer.MAX_VALUE);
    	Goods goods = new Goods();
    	goods.setStoreId(storeId);
    	goods.setGoodsState(GoodsState.GOODS_OPEN_STATE);
    	//上架 1
    	goods.setGoodsShow(1);
    	//不违规 0
    	goods.setGoodsState(0);
    	pager.setCondition(goods);
    	
    	List<Goods> goodsList = goodsService.findGoodPagerList(pager);
    	modelAndView.addObject("goodsList", goodsList);
    	
    	/**创建设置查询条件*/
    	GoodsCombination goodsCombination = new GoodsCombination();
    	
    	/**设置查询条件*/
    	goodsCombination.setGoodsId(goodsId);
    	
    	/**查询*/
    	List<GoodsCombination> list = goodsCombinationService.selectByCondition(goodsCombination);
    	
    	modelAndView.addObject("goodsCombinations", list);
    	
    	modelAndView.addObject("goodsId", goodsId);
    	
    	return modelAndView;
    }
    
    /**
     * 修改组合商品
     */
    @ResponseBody
    @RequestMapping("/updateCombination")
    public String updateCombination(GoodsCombination goodsCombination){
    	try{
        	goodsCombinationService.updateGoodsCombination(goodsCombination);
        	return "true";
    	}catch(Exception e){
    		return "false";
    	}
    }
    
    
	
}