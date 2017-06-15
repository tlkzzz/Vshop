package com.Vshop.admin.module.platformstore;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.Vshop.core.common.Constants;
import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.Area;
import com.Vshop.core.entity.GoodsAttribute;
import com.Vshop.core.entity.GoodsClass;
import com.Vshop.core.entity.GoodsSpec;
import com.Vshop.core.entity.Transport;
import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.GoodsCombination;
import com.Vshop.core.entity.base.GoodsStore;
import com.Vshop.core.entity.vo.GoodsAttrVo;
import com.Vshop.core.entity.vo.GoodsSpecVo;
import com.Vshop.core.entity.vo.GoodsTypeVO;
import com.Vshop.core.entity.vo.SpecVo;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.core.platform.info.PlatformInfo;
import com.Vshop.core.state.goods.GoodsState;
import com.Vshop.service.module.area.service.AreaService;
import com.Vshop.service.module.goods.service.BrandService;
import com.Vshop.service.module.goods.service.GoodsClassService;
import com.Vshop.service.module.goods.service.GoodsCombinationService;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.module.goods.service.GoodsSpecService;
import com.Vshop.service.module.goods.service.GoodsTypeService;
import com.Vshop.service.module.goods.service.SupplierService;
import com.Vshop.service.module.product.service.ProductService;
import com.Vshop.service.module.store.service.StoreService;
import com.Vshop.service.module.tostatic.service.ToStaticService;
import com.Vshop.service.module.trade.service.TransportService;
import com.Vshop.service.utils.CommonConstants;
import com.Vshop.service.utils.goods.GoodsUtils;
import com.Vshop.service.utils.http.ToStaticSendToFront;
import com.Vshop.service.utils.page.Pager;

import freemarker.template.TemplateException;

/**
 * action描述:平台关于商品跳转action
 * 创建人：cgl   
 * 创建时间：2015年08月03日16:04:35
 * 平台自营
 */
@Controller
@RequestMapping("/platform")
public class PlatformGoodsAction {
	
	@Autowired
	private GoodsClassService goodsClassService;
	
	@Resource
	private GoodsTypeService goodsTypeService;
	
	@Autowired
	private TransportService transportService;	
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private GoodsService goodsService;
	
	@Resource
	private BrandService brandService;
	
	@Resource
	private GoodsSpecService goodsSpecService;
	
	@Autowired
	private ToStaticService toStaticService;
	
	@Autowired
	private GoodsCombinationService goodsCombinationService;
	
	@Resource
	private StoreService storeService;
	
	@Resource
	private SupplierService supplierService;
	
	/**
	 * 发布商品前选择分类
	 */
	@RequestMapping(value = "/sellIndex")
	public ModelAndView sellIndex(){
		
		ModelAndView model = new ModelAndView("/platform/goods/pro-sell-index");

		List<GoodsClass> list = goodsClassService.findList(0);
		
		model.addObject("datas", list);
		
		return model;
	}
	
	/**
	 * 
	 * @Title: findChildClass
	 * @Description: TODO(获取到子分类)
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
		map.put("success", "true");
		return map;
	}
	
	/**
	 * 发布商品的详细页面
	 */
	@RequestMapping(value = "/sellDetail")
	public String sellDetail(@RequestParam(value = "gcId") String gcId, HttpServletRequest request, Model model){
		try{
			
			model.addAttribute("gcId", gcId);
			//店铺id
			Integer storeId =  PlatformInfo.PLATFORM_STORE_ID;
			
			//商品分类
			GoodsClass goodsClass = goodsClassService.findById(Integer.valueOf(gcId));
			//类型id
			model.addAttribute("typeId", goodsClass.getTypeId());
			//分类全名称
			String catename = goodsClass.getGcName();
			model.addAttribute("catename", catename);
			
			//获得类型id
			Integer typeId = goodsClass.getTypeId();
			
			/*
			 * 通过类型id获得类型下的品牌,规格,属性
			 * 首先通过类型id获得goodsTypeVo
			 * 在这个超类中,有3个list,是品牌,规格,属性
			 */
			GoodsTypeVO goodsTypeVO = goodsTypeService.selectTypeFetchOther(typeId);
			
			//获得该类型下的品牌
			List<Brand> brands = goodsTypeVO.getBrandList();
			//放入model
			model.addAttribute("brands", brands);
			
			//获得该类型下的规格
			List<SpecVo> specs = goodsTypeVO.getSpecList();
			//放入model
			model.addAttribute("specs", specs);
			
			//获得该类型下的属性
			List<GoodsAttribute> goodsAttributes = goodsTypeVO.getAttributes();
			//放入model
			model.addAttribute("goodsAttributes", goodsAttributes);
			
			
//			model.addAttribute("stores",storeService.findList());
			
			//品牌供应商
			model.addAttribute("suppliers",supplierService.findList());
			 
			
			//运费模板
	        Transport transport = transportService.getDefTransportByStoreId(storeId);
			model.addAttribute("transport", transport);
			
	        //一级地区加载
	        List<Area> areas = areaService.queryAll();
	        model.addAttribute("areas", areas);
			
			model.addAttribute("listimgSize", 0);//默认5个图片
			//图片base路径
			model.addAttribute("imageServer", CommonConstants.IMG_SERVER);

		}catch(Exception e){
			e.printStackTrace();
	        String referer = request.getHeader("Referer");
	        model.addAttribute("referer", referer);
	        model.addAttribute("msg", "没有找到该分类下的类型,请重新选择分类");
	        return Constants.MSG_URL;
		}

		return "/platform/goods/pro_sell_detail";
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
			//设置utf-8
			request.setCharacterEncoding("utf-8");
			String goodsSpecJson = request.getParameter("goodsSpecJson");
			
			
			//获得当前店铺id
			Integer storeId = Constants.PLATFORM_STORE_ID;
			//获得当前店铺名称
			String storeName = PlatformInfo.PLATFORM_STORE_NAME;
			//设置到goods中
			goods.setStoreId(storeId);
			goods.setStoreName(storeName);
			
			
			//选择店铺
//			goods.setStoreName(storeService.findById(goods.getStoreId()).getStoreName());
			
			goods.setSupplierName(supplierService.findById(goods.getSupplierId()).getName());
			
			goods.setBrandName(brandService.findById(goods.getBrandId()).getBrandName());
			
			//上架时间
			if(StringUtils.isNotEmpty(prepareUpTime)){
				goods.setUpdateTime(DateUtils.strToLong(prepareUpTime));
			}
			//发布时间
			goods.setCreateTime(new Date().getTime());
			//调用保存的service的方法,返回状态0为失败1为成功
			Integer goodsId = productService.saveGoods(goods, goodsSpecJson);
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
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/editgoods")
	public ModelAndView editgoods(@RequestParam(value = "goodsId") String goodsId) throws Exception {
		ModelAndView model = new ModelAndView("/platform/goods/pro_edit_goods");
		//根据goodsid获得goods
		Goods goods = new Goods();
		goods.setGoodsId(Integer.parseInt(goodsId));
		//获得当前店铺id
		Integer storeId = Constants.PLATFORM_STORE_ID;
		goods.setStoreId(storeId);
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
		
		//获得该类型下的品牌
		List<Brand> brands = goodsTypeVO.getBrandList();
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
		
        //一级地区加载
        List<Area> areas = areaService.queryAll();
        model.addObject("areas", areas);
        
		//商品分类
//        Map<String, List<StoreGoodsClass>> classmap = storeGoodsClassService.queryStoreClass(storeId);
//		model.addObject("classmap", classmap);//商品分类
		
		//商品图片
		String[] imageMore = goods.getGoodsImageMore().split(",");
		List<String> imageList = Arrays.asList(imageMore);
		model.addObject("imageList", imageList);
		
//		model.addObject("stores",storeService.findList());
		
		//运费模板
		Transport transport = transportService.getDefTransportByStoreId(goods.getStoreId());
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
			Integer storeId = PlatformInfo.PLATFORM_STORE_ID;
			//获得当前店铺名称
			String storeName = PlatformInfo.PLATFORM_STORE_NAME;
			//设置到goods中
			goods.setStoreId(storeId);
			goods.setStoreName(storeName);
//			//选择店铺
//			goods.setStoreName(storeService.findById(goods.getStoreId()).getStoreName());
			
			goods.setSupplierName(supplierService.findById(goods.getSupplierId()).getName());
			
			goods.setBrandName(brandService.findById(goods.getBrandId()).getBrandName());
			
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
	 * 
	 * @Title: list
	 * @Description: (加载数据页面)
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping("/sellList")
	public String sellList(@ModelAttribute Goods goods,Model model,
            @RequestParam(required=false, value="pageNo",defaultValue="")String pageNo,
            @RequestParam(required=false, value="brandId",defaultValue="")String brandId,
            @RequestParam(required=false, value="storeId",defaultValue="")String storeId,
            @RequestParam(required=false, value="supplierId",defaultValue="")String supplierId) {

			Pager pager = new Pager();
			if (StringUtils.isNotBlank(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
//            goods.setGcIds(this.createGcIds(goods.getGcId()));
			
    		goods.setStoreId(PlatformInfo.PLATFORM_STORE_ID);
			if(StringUtils.isNotEmpty(storeId)){
				goods.setStoreId(Integer.valueOf(storeId));
			}
    		goods.setGoodsShow(GoodsState.GOODS_ON_SHOW);
    		goods.setGoodsState(GoodsState.GOODS_OPEN_STATE);
    		if(StringUtils.isNotEmpty(brandId)){
    			goods.setBrandId(Integer.valueOf(brandId));
    		}
    		
    		if(StringUtils.isNotEmpty(supplierId)){
				goods.setSupplierId(Integer.valueOf(supplierId));
			}
    		
			pager.setCondition(goods);// 实体加载在pager中

			List<Goods> results = goodsService.findGoodPagerList(pager);// 结果集

			// 页面查询条件品牌列表
            pager.setResult(results);
            model.addAttribute("pager", pager);//总数
            model.addAttribute("goods",goods);
//            model.addAttribute("storeList",storeService.findList());
            model.addAttribute("brandList",brandService.findList());
            model.addAttribute("classList",goodsClassService.findList(0));
            //图片路径
            model.addAttribute("imgSrc",Constants.SPECIMAGE_PATH);
			// 转发请求到FTL页面
			return "/platform/goods/sell-list";
	}
	
	/**
	 * 
	 * @Title: list
	 * @Description: (加载数据页面)
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping("/storeList")
	public String storeList(@ModelAttribute Goods goods,Model model,
			@RequestParam(required=false, value="pageNo",defaultValue="")String pageNo) {
		
		Pager pager = new Pager();
		if (StringUtils.isNotBlank(pageNo)) {
			pager.setPageNo(Integer.parseInt(pageNo));
		}
//            goods.setGcIds(this.createGcIds(goods.getGcId()));
		goods.setStoreId(PlatformInfo.PLATFORM_STORE_ID);
		goods.setGoodsShow(GoodsState.GOODS_OFF_SHOW);
		goods.setGoodsState(GoodsState.GOODS_OPEN_STATE);
		pager.setCondition(goods);// 实体加载在pager中
		
		List<Goods> results = goodsService.findGoodPagerList(pager);// 结果集
		
		// 页面查询条件品牌列表
		pager.setResult(results);
		model.addAttribute("pager", pager);//总数
		model.addAttribute("goods",goods);
		model.addAttribute("brandList",brandService.findList());
//		 model.addAttribute("storeList",storeService.findList());
		model.addAttribute("classList",goodsClassService.findList(0));
		//图片路径
		model.addAttribute("imgSrc",Constants.SPECIMAGE_PATH);
		// 转发请求到FTL页面
		return "/platform/goods/store-list";
	}
	
	
	/**
	 * 
	 * @Title: list
	 * @Description:待审核的商品
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping("/preApply")
	public String preApply(@ModelAttribute Goods goods,Model model,
		    @RequestParam(required=false, value="area",defaultValue="")String area,
		    @RequestParam(required=false, value="city_id",defaultValue="")String city_id,
			@RequestParam(required=false, value="pageNo",defaultValue="")String pageNo) {
		
		Pager pager = new Pager();
		if (StringUtils.isNotBlank(pageNo)) {
			pager.setPageNo(Integer.parseInt(pageNo));
		}
		if(StringUtils.isNotEmpty(area)){
			goods.setProvinceId(Integer.valueOf(area));
		}
		if(StringUtils.isNotEmpty(city_id)){
			goods.setCityId(Integer.valueOf(city_id));
		}
		goods.setStoreId(PlatformInfo.PLATFORM_STORE_ID);
//		goods.setGoodsShow(GoodsState.GOODS_ON_SHOW);//上架
		goods.setGoodsState(GoodsState.GOODS_APPLY_PREPARE);//待审核
		pager.setCondition(goods);// 实体加载在pager中
		
		List<Goods> results = goodsService.findGoodPagerList(pager);// 结果集
		GoodsClass goodsClass = goodsClassService.findById(goods.getGcId());
		String gcIdPath = "";
		String firstLevel = "";
		String secondLevel = "";
		String threeLevel = "";
		if(null != goodsClass){
			gcIdPath = goodsClass.getGcIdpath();
			String[] path = gcIdPath.split(",");
			firstLevel = path[0];
			if(path.length>1) 
			secondLevel = path[1];
			if(path.length>2)
			threeLevel = path[2];
		}
		// 页面查询条件品牌列表
		pager.setResult(results);
		model.addAttribute("pager", pager);//总数
		model.addAttribute("goods",goods);
		model.addAttribute("area",area);
		model.addAttribute("city_id",city_id);
		model.addAttribute("areas",areaService.queryAll());//获取地区一级菜单
		model.addAttribute("brandList",brandService.findList());
		model.addAttribute("classList",goodsClassService.findList(0));
		model.addAttribute("gcidpath", gcIdPath);
        model.addAttribute("firstLevel", firstLevel);
        model.addAttribute("secondLevel", secondLevel);
        model.addAttribute("threeLevel", threeLevel);
		//图片路径
		model.addAttribute("imgSrc",Constants.SPECIMAGE_PATH);
		// 转发请求到FTL页面
		return "/platform/goods/store-preApply";
	}
	
	
	
	/**
	 * 
	 * @Title: list
	 * @Description:已拒绝的商品
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping("/offApply")
	public String offApply(@ModelAttribute Goods goods,Model model,
		    @RequestParam(required=false, value="area",defaultValue="")String area,
		    @RequestParam(required=false, value="city_id",defaultValue="")String city_id,
			@RequestParam(required=false, value="pageNo",defaultValue="")String pageNo) {
		
		Pager pager = new Pager();
		if (StringUtils.isNotBlank(pageNo)) {
			pager.setPageNo(Integer.parseInt(pageNo));
		}
		if(StringUtils.isNotEmpty(area)){
			goods.setProvinceId(Integer.valueOf(area));
		}
		if(StringUtils.isNotEmpty(city_id)){
			goods.setCityId(Integer.valueOf(city_id));
		}
		goods.setStoreId(PlatformInfo.PLATFORM_STORE_ID);
//		goods.setGoodsShow(GoodsState.GOODS_ON_SHOW);//上架
		goods.setGoodsState(GoodsState.GOODS_APPLY_OFF);//未通过
		pager.setCondition(goods);// 实体加载在pager中
		
		List<Goods> results = goodsService.findGoodPagerList(pager);// 结果集
		GoodsClass goodsClass = goodsClassService.findById(goods.getGcId());
		String gcIdPath = "";
		String firstLevel = "";
		String secondLevel = "";
		String threeLevel = "";
		if(null != goodsClass){
			gcIdPath = goodsClass.getGcIdpath();
			String[] path = gcIdPath.split(",");
			firstLevel = path[0];
			if(path.length>1) 
			secondLevel = path[1];
			if(path.length>2)
			threeLevel = path[2];
		}
		// 页面查询条件品牌列表
		pager.setResult(results);
		model.addAttribute("pager", pager);//总数
		model.addAttribute("goods",goods);
		model.addAttribute("area",area);
		model.addAttribute("city_id",city_id);
		model.addAttribute("areas",areaService.queryAll());//获取地区一级菜单
		model.addAttribute("brandList",brandService.findList());
		model.addAttribute("classList",goodsClassService.findList(0));
		model.addAttribute("gcidpath", gcIdPath);
        model.addAttribute("firstLevel", firstLevel);
        model.addAttribute("secondLevel", secondLevel);
        model.addAttribute("threeLevel", threeLevel);
		//图片路径
		model.addAttribute("imgSrc",Constants.SPECIMAGE_PATH);
		// 转发请求到FTL页面
		return "/platform/goods/store-offApply";
	}
	
	
	/**
	 * 上架商品
	 */
    @RequestMapping("/upGoods")
    public @ResponseBody Map<String,Object> upGoods(
    		@RequestParam(value="goodsIds",required=true) String goodsIdsStr){
        Map<String,Object> map = Maps.newHashMap();
        try{
        	//循环删除
        	if(!goodsIdsStr.equals("")){
        		String[] goodsIds = goodsIdsStr.split(",");
        		for(int i = 0; i < goodsIds.length; i++){
        			Goods goods = new Goods();
        			goods.setGoodsId(Integer.parseInt(goodsIds[i]));
        			goods.setGoodsShow(GoodsState.GOODS_ON_SHOW);
        			goodsService.updateGoods(goods);
        			/**生成静态页面*/
        			ToStaticSendToFront.onegoodsDetailStatic(goods.getGoodsId(), PlatformInfo.PLATFORM_STORE_ID);
        		}
        	}
            map.put("success",true);
        }catch(Exception e){
            map.put("success",false);
            map.put("result","下架商品失败");
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
        			goodsService.updateGoods(goods);
        			toStaticService.deleteGoodsDetailStaticPage(goods.getGoodsId());
        		}
        	}
            map.put("success",true);
        }catch(Exception e){
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
    	
    	ModelAndView modelAndView = new ModelAndView("/platform/goods/pro-combination");
    	
    	/**得到所有店铺下的id*/
    	Pager pager = new Pager();
    	pager.setPageSize(Integer.MAX_VALUE);
    	Goods goods = new Goods();
    	goods.setStoreId(storeId);
    	goods.setGoodsState(GoodsState.GOODS_OPEN_STATE);
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
    @RequestMapping(value="/toUpdateMoney")
    public ModelAndView updateMoney(@RequestParam(value="goodsId") String goodsId){
    	ModelAndView model = new ModelAndView("/platform/goods/pro_up_goods");
    	Goods goods = goodsService.findGoodsById(Integer.parseInt(goodsId));
    	model.addObject("goods",goods);
    	model.addObject("goodsId",goodsId);
    	return model;
    }
    
    @RequestMapping(value="/updateMoney")
    public @ResponseBody Map<String,Object> updateMoney(@ModelAttribute Goods goods){
    	Map<String,Object> result = new HashMap<String,Object>();
    	try {
			if(goods!=null){
				Goods goodsVo = goodsService.findGoodsById(goods.getGoodsId());
				if(goodsVo!=null){
					if(!goods.getGoodsState().equals(0)){
						goods.setGoodsState(GoodsState.GOODS_APPLY_OFF);
					}
					if("".equals(goods.getGoodsCloseInfo().trim())){
						goods.setGoodsCloseInfo(null);
					}
					goodsService.updateGoodsToMoney(goods);
					result.put("result", "success");
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", "error");
			return result;
		} 
    }
	
}