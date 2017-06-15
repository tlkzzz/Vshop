package com.Vshop.front.module.api;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.Vshop.core.common.MyBeanUtils;
import com.Vshop.core.common.NumberUtils;
import com.Vshop.core.entity.GoodsClass;
import com.Vshop.core.entity.apibean.EvaluateGoodsApiBean;
import com.Vshop.core.entity.apibean.GoodsApiBean;
import com.Vshop.core.entity.apibean.GoodsFilterBean;
import com.Vshop.core.entity.apibean.SpecApiBean;
import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.base.EvaluateGoods;
import com.Vshop.core.entity.base.EvaluateStore;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.GoodsDetailBean;
import com.Vshop.core.entity.base.Promotion;
import com.Vshop.core.entity.base.Spec;
import com.Vshop.core.entity.base.SpecValue;
import com.Vshop.core.entity.base.Store;
import com.Vshop.core.entity.vo.GoodsSpecVo;
import com.Vshop.core.entity.vo.PromotionClassVo;
import com.Vshop.core.entity.vo.SpecVo;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.service.module.cart.service.FavoritesService;
import com.Vshop.service.module.goods.service.BrandService;
import com.Vshop.service.module.goods.service.GoodsClassService;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.module.goods.service.SpecValueService;
import com.Vshop.service.module.promotion.service.PromotionClassService;
import com.Vshop.service.module.search.service.GoodsSearchService;
import com.Vshop.service.module.store.service.EvaluateStoreService;
import com.Vshop.service.module.store.service.StoreService;
import com.Vshop.service.module.strategy.common.StrategyTypes;
import com.Vshop.service.module.trade.service.EvaluateGoodsService;
import com.Vshop.service.utils.goods.GoodsUtils;
import com.Vshop.service.utils.lucene.LucenePager;
import com.Vshop.service.utils.page.Pager;

/**
 * 课程API接口
 * @author LKANG
 * @version 2015-07-06 16:00:00
 */
@Slf4j
@Controller
@RequestMapping("/goods/api")
public class GoodsApi{
	
	@Resource
    private GoodsClassService goodsClassService;
	
	@Resource
    private BrandService brandService;
	
	@Resource
    private GoodsService goodsService;
	
	@Resource
	private GoodsSearchService goodsSearchService;
	
	@Resource
    private SpecValueService specValueService;
	
	@Resource
    private StoreService storeService;
	
	@Resource
	private EvaluateStoreService evaluateStoreService;
	
	@Resource
	private EvaluateGoodsService evaluateGoodsService;
	
	@Resource
	private PromotionClassService promotionClassService;
	
	@Resource
	private FavoritesService favoritesService;
	
	/**
	 * 课程分类api
	 * @param parentId 父级id，第一级传0
	 * @return 0:无课程分类  1:返回分类列表
	 */
	@RequestMapping("classlist")
	@ResponseBody
	public JSONObject goodsClassApi(@RequestParam int parentId){
		JSONObject jsonObj = new JSONObject();
		try {
			List<GoodsClass> classList = new ArrayList<GoodsClass>();
			classList = goodsClassService.findList(parentId);
			if(classList.size() == 0){
				jsonObj.put("result", 1);
				jsonObj.put("msg", "无数据");
				jsonObj.put("data", "[]");
			}else{
				if(parentId==0){ //若查询的是一级分类,将查询分类的第三级分类的前两个分类存进一级分类的gcLastChild
					classList = goodsClassService.setApiGcLastChild(classList);
				}
				jsonObj.put("result", 1);
				jsonObj.put("msg", "获取成功");
				jsonObj.put("data", JSONArray.fromObject(classList, JsonUtils.getJsonConfig()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		
		return jsonObj;
	}
	
	/**
	 * 品牌api
	 * @param classId 分类id
	 * @param storeId 学院id
	 * @param typeId 类型id
	 * @return
	 */
	@RequestMapping("brandlist")
	@ResponseBody
	public JSONObject brandApi(
			@RequestParam(required=false, value="classid",defaultValue="0")int classId,
			@RequestParam(required=false, value="storeid",defaultValue="0")int storeId,
			@RequestParam(required=false, value="typeid",defaultValue="0")int typeId
			){
		JSONObject jsonObj = new JSONObject();
		try {
			List<Brand> brandList = new ArrayList<Brand>();
			if(0 != classId){
				brandList = brandService.findByClassId(classId);
			}else if(0 != storeId){
				brandList = brandService.getBrandListByStoreId(storeId);
			}else if(0 != typeId){
				brandList = brandService.getBrandListByTypeId(typeId);
			}else{
				brandList = brandService.findList();
			}
			if(brandList.size() == 0){
				jsonObj.put("result", 1);
				jsonObj.put("msg", "无数据");
				jsonObj.put("data", "[]");
				return jsonObj;
			}
			
			jsonObj.put("result", 1);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data", JSONArray.fromObject(brandList, JsonUtils.getJsonConfig()));
		} catch (Exception e) {
			e.printStackTrace();
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		
		return jsonObj;
	}
	
	/**
	 * 课程详细信息api
	 * @param goodsId
	 * @return
	 */
	@RequestMapping("goodsdetail")
	@ResponseBody
	public JSONObject goodsApi(@RequestParam int goodsId,
							   @RequestParam(required=false, value="memberId",defaultValue="")String memberId){
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj = new JSONObject();
			GoodsApiBean bean = new GoodsApiBean();
			Goods goods = goodsService.findGoodById(goodsId);
			if(null == goods){
				jsonObj.put("result", 1);
				jsonObj.put("msg", "无数据");
				jsonObj.put("data", "[]");
				return jsonObj;
			}
			MyBeanUtils.copyBeanNotNull2Bean(goods, bean);
			/*String attr = goods.getGoodsAttr();
			List<GoodsAttrVo> goodsAttrVos = GoodsUtils.goodsAttrStrToGoodsAttrVoClass(attr);
			bean.setGoodsAttrList(goodsAttrVos);*/
			
			Store store = storeService.findById(goods.getStoreId());
			MyBeanUtils.copyBeanNotNull2Bean(store, bean);
			
			List<String> imgList = goodsService.getGoodsImgList(goodsId);
			bean.setGoodsCallyList(imgList);
			
			Map<String, List<GoodsSpecVo>> specValue = GoodsUtils.goodsSpecStrToMapList(goods.getGoodsSpec());
			if(specValue!=null){
				bean.setGoodsSpecValueAll(specValue);
			}else{
				Map<String, List<GoodsSpecVo>> specValue1 = new HashMap<String, List<GoodsSpecVo>>();
				bean.setGoodsSpecValueAll(specValue1);
			}
			
			
			/**
			 * 获取课程学院评分
			 */
			EvaluateStore evaluateStore = evaluateStoreService.findEvaluateStoreByStoreId(goods.getStoreId());
			bean.setEvaluateStore(evaluateStore);
			
			/**
			 * 获取课程最新两条评论
			 */
			//新建课程评论分页查询信息
			Pager pager = new Pager();
			//新建一个课程评论实体
			EvaluateGoods evaluateGoods = new EvaluateGoods();
			evaluateGoods.setGoodsId(goods.getGoodsId());
			//将课程信息塞入要查询的分页信息中
			pager.setCondition(evaluateGoods);
			//设置页码为1
			pager.setPageNo(1);
			//设置条数为2
			pager.setPageSize(2);
			List<EvaluateGoods> evaluateGoodsList = evaluateGoodsService.findPageList(pager);
			//获取EvaluateGoodsApiBean集合
			List<EvaluateGoodsApiBean> apiBeanList = evaluateGoodsService.getApiBeanList(evaluateGoodsList);
			
			bean.setEvaluateGoodsList(apiBeanList);
			
			/**
			 * 获取促销规则
			 */
			//新建一个促销规则字段
			String promotionClass = promotionClassService.findMessage();
			bean.setPromotionClass(promotionClass);
			
			/**
			 * 查询学院是否收藏
			 */
			int isFav = 0;
			if(StringUtils.isNotBlank(memberId)){
				isFav = favoritesService.findcountFav(goodsId,Integer.valueOf(memberId),1);
			}
			bean.setIsFav(isFav);
			
			/**
			 * 课程平均分
			 */
			BigDecimal avearageScoll = evaluateGoodsService.getAverageScoreByGooodsId(goods.getGoodsId());
			bean.setEvaluate(avearageScoll == null ? new BigDecimal(3) : avearageScoll);
			
			// 购物车数量
			/*if(StringUtils.isNotEmpty(memberId)){
				int cartCount = cartService.queryCountByMemberId(Integer.parseInt(memberId));
				bean.setCartCount(cartCount);
			}else{
				bean.setCartCount(0);
			}*/
			
			jsonObj.put("result", 1);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data", JSONArray.fromObject(bean, JsonUtils.getJsonConfig()));
		} catch (Exception e) {
			e.printStackTrace();
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		
		return jsonObj;
	}
	
	/**
	 * 课程列表api接口
	 * @param searchType 搜索课程的类型,比如:搜索课程的名字,分类,类型,学院
	 * 1. keywordSearch 关键词搜索
	 * 2. gcIdSearch 分类查找,在keyword设置对应的id
	 * 3. typeIdSearch 类型查找,在keyword设置对应的id
	 * 排序字段
	 * sortField,sortOrder
	 * 解释:sortField:需要排序的字段 sortOrder:asc,desc 顺序,逆序
	 * 比如 sortField:salenum  sortOder:asc 则是销售量的排序
	 * 排序字段根据 goods的实体类的属性名称而定
	 * 
	 * 规格过滤器
	 * specFilter:过滤规格
	 * 解释:传入规格值id,多个过滤以逗号分隔 比如:2,3则会过滤出 课程中 规格值id包含有2和3的
	 * @param brandId:品牌id
	 * @param areaId:地区id
	 * @param keyword 关键词
	 * @param pageNo 分页的页码
	 * @param sortOrder 排序类型,desc,asc
	 * @param sortField 排序的字段
	 * @param filterConditions 过滤条件
	 * @param specFilter
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("goodslist")
	@ResponseBody
	public JSONObject goodsListApi(
			@RequestParam(value="searchType",required=false, defaultValue="keywordSearch") String searchType,
			@RequestParam(value="keyword",required=false) String keyword,
			@RequestParam(value="pageNo",required=false, defaultValue="1")String pageNo,
			@RequestParam(value="brandId",required=false)String brandId,
			@RequestParam(value="areaId",required=false)String areaId,
			@RequestParam(value="specFilter",required=false)String specFilter,
			@RequestParam(value="sortField",required=false)String sortField,
			@RequestParam(value="sortOrder",required=false)String sortOrder,
			@RequestParam(value="pageSize",required=false, defaultValue="10")String pageSize
			){
		JSONObject jsonObj = new JSONObject();
		try {
			//准备lucenepager
			LucenePager lucenePager = new LucenePager();
			//设置每页大小
			lucenePager.setPageSize(Integer.parseInt(pageSize));
			//设置页面编号
			if(pageNo != null && !pageNo.equals("")){
				lucenePager.setPageNo(Integer.parseInt(pageNo));
			}
			//设置关键词
			lucenePager.setKeyword(keyword);
			//设置查询的区域
			lucenePager.setModel("goods");
			lucenePager.setSearchType(searchType);
			//设置搜索的类
			lucenePager.setSearchObjClass(Goods.class);
			List list = new ArrayList();
			if(StringUtils.isNotEmpty(brandId)){
				JSONObject brand = new JSONObject();
				brand.put("filterName", "brandId");
				brand.put("conditionData", brandId);
				list.add(brand);
			}
			if(StringUtils.isNotEmpty(areaId)){
				JSONObject area = new JSONObject();
				area.put("filterName", "provinceId");
				area.put("conditionData", areaId);
				list.add(area);
			}
			//是否过滤
			if(list.size() > 0){
				lucenePager.setFilterConditionsStr(JSONArray.fromObject(list).toString());
			}
			//规格筛选
			if(specFilter != null && !specFilter.equals("")){
				String[] specFilterStr = specFilter.split(",");
				lucenePager.setSpValueIdsFilter(specFilterStr);
			}
			//检查是否排序
			if(sortOrder != null && !sortOrder.trim().equals("") && sortField != null && !sortField.trim().equals("")){
				//设置排序的字段
				lucenePager.setSortField(sortField);
				//设置排序规则
				lucenePager.setSortOrder(sortOrder);
			}
			lucenePager = goodsSearchService.searchGoods(lucenePager);
			List<Goods> goodsList = (List<Goods>) lucenePager.getResult();
			List<GoodsDetailBean> beanList = new ArrayList<GoodsDetailBean>();
			if(null != goodsList){
				for (Goods goods : goodsList) {
					GoodsDetailBean detail = new GoodsDetailBean();
					MyBeanUtils.copyBeanNotNull2Bean(goods, detail);
					BigDecimal avearageScoll = evaluateGoodsService.getAverageScoreByGooodsId(goods.getGoodsId());
					detail.setEvaluate(avearageScoll == null ? new BigDecimal(3) : avearageScoll);
					beanList.add(detail);
				}
				jsonObj.put("searchType", searchType);
				jsonObj.put("keyword", keyword);
				jsonObj.put("pageNo", pageNo);
				jsonObj.put("brandId", brandId);
				jsonObj.put("areaId", areaId);
				jsonObj.put("specFilter", specFilter);
				jsonObj.put("sortField", sortField);
				jsonObj.put("sortOrder", sortOrder);
				jsonObj.put("pageSize", pageSize);
				jsonObj.put("result", 1);
				jsonObj.put("data", JSONArray.fromObject(beanList, JsonUtils.getJsonConfig()));
			}else{
				jsonObj.put("result", 1);
				jsonObj.put("msg", "无数据");
				jsonObj.put("data", "[]");
			}
		} catch (Exception e) {
			log.error("课程列表API出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		
		return jsonObj;
	}
	
	/**
	 * 获取课程筛选条件
	 * @return
	 */
	@RequestMapping("goodsfilter")
	@ResponseBody
	public JSONObject getGoodsFilterInfo(
			@RequestParam(value="searchType",required=false, defaultValue="keywordSearch") String searchType,
			@RequestParam(value="keyword",required=false) String keyword
			){
		JSONObject jsonObj = new JSONObject();
		try {
			//准备lucenepager
			LucenePager lucenePager = new LucenePager();
			
			//设置关键词
			lucenePager.setKeyword(keyword);
			
			//设置查询的区域
			lucenePager.setModel("goods");
			lucenePager.setSearchType(searchType);
			//设置搜索的类
			lucenePager.setSearchObjClass(Goods.class);
			
			lucenePager = goodsSearchService.searchGoods(lucenePager);
			GoodsFilterBean filterBean = new GoodsFilterBean();
			List<SpecVo> specList = (List<SpecVo>)lucenePager.getSpecList();
			List<SpecApiBean> specBeanList = new ArrayList<SpecApiBean>();
			if(null != specList){
				for (Spec spec : specList) {
					SpecApiBean specBean = new SpecApiBean();
					MyBeanUtils.copyBeanNotNull2Bean(spec, specBean);
					specBeanList.add(specBean);
				}
				List<Brand> brandList = (List<Brand>)lucenePager.getBrandList();
				if(null != brandList && brandList.size() > 0){
					SpecApiBean specBean = new SpecApiBean();
					String brandSpecId = "0000" + keyword;
					specBean.setSpId(brandSpecId);
					specBean.setSpName("品牌");
					specBeanList.add(specBean);
				}
				filterBean.setSpecList(specBeanList);
				jsonObj.put("result", 1);
				jsonObj.put("searchType", searchType);
				jsonObj.put("keyword", keyword);
				jsonObj.put("data", JSONArray.fromObject(filterBean, JsonUtils.getJsonConfig()));
			}else{
				jsonObj.put("result", 1);
				jsonObj.put("msg", "无数据");
				jsonObj.put("data", "[]");
			}
		} catch (Exception e) {
			log.error("课程过滤条件出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
	/**
	 * 根据规则id获取规格值
	 * @param spId
	 * @return
	 */
	@RequestMapping("specvalue")
	@ResponseBody
	public JSONObject specValue(
			@RequestParam(value="spid",required=false) String specId
			){
		JSONObject jsonObj = new JSONObject();
		try {
			if(specId.startsWith("0000")){
				String key = specId.substring(4);
				List<Brand> brandlist = new ArrayList<Brand>();
				if(NumberUtils.isNumber(key)){
					Integer typeId = Integer.parseInt(key);
					brandlist = brandService.getBrandListByTypeId(typeId);
				}else{
					brandlist = goodsSearchService.getBrandListByKeyword(key);
				}
				if(brandlist.size() == 0){
					jsonObj.put("result", 1);
					jsonObj.put("msg", "无数据");
					jsonObj.put("data", "[]");
				}else{
					jsonObj.put("result", 1);
					jsonObj.put("msg", "获取成功");
					jsonObj.put("data", JSONArray.fromObject(brandlist, JsonUtils.getJsonConfig()));
				}
			}else{
				List<SpecValue> list = specValueService.findListBySpId(Integer.parseInt(specId));
				if(list.size() == 0){
					jsonObj.put("result", 1);
					jsonObj.put("msg", "无数据");
					jsonObj.put("data", "[]");
				}else{
					jsonObj.put("result", 1);
					jsonObj.put("msg", "获取成功");
					jsonObj.put("data", JSONArray.fromObject(list, JsonUtils.getJsonConfig()));
				}
			}
		} catch (Exception e) {
			log.error("课程规格值出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
	public static void main(String[] args) {
		System.out.println("00001".substring(4));
	}
	
}
