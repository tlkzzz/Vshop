package com.Vshop.service.module.goods.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.GoodsSpec;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.GoodsStore;
import com.Vshop.core.entity.vo.GoodsAttrVo;
import com.Vshop.core.entity.vo.GoodsSpecVo;
import com.Vshop.core.entity.vo.GoodsTradeVo;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.core.state.goods.GoodsState;
import com.Vshop.service.module.goods.dao.GoodsAttrIndexDao;
import com.Vshop.service.module.goods.dao.GoodsDao;
import com.Vshop.service.module.goods.dao.GoodsSpecDao;
import com.Vshop.service.module.goods.dao.GoodsSpecIndexDao;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.module.goods.service.GoodsSpecService;
import com.Vshop.service.module.search.service.GoodsSearchService;
import com.Vshop.service.module.tostatic.service.ToStaticService;
import com.Vshop.service.utils.goods.GoodsUtils;
import com.Vshop.service.utils.page.Pager;

import freemarker.template.TemplateException;

@Service
public class GoodsServiceImpl implements GoodsService{
    
    @Resource
    private GoodsDao goodsDao;
    
    @Resource
    private GoodsSpecDao goodsSpecDao;

    @Resource
    private GoodsSpecIndexDao goodsSpecIndexDao;
    
    @Resource
    private GoodsAttrIndexDao goodsAttrIndexDao;
    
    @Resource
    private GoodsSpecService goodsSpecService;
    
    @Resource
    private GoodsSearchService goodsSearchService;
    
    @Autowired
    ToStaticService toStaticService;
    
	public Goods findGoodById(Integer goodsId) {
		return goodsDao.findGoodById(goodsId);
	}

	public List<Goods> findGoodPagerList(Pager pager) {
		return goodsDao.findGoodPagerList(pager);
	}

	public void saveGoods(Goods goods)  throws IOException, TemplateException, ServletException {
		goodsDao.saveGoods(goods);
	}

	public void updateGoods(Goods goods) throws IOException, TemplateException, ServletException {
		// 修改上架则增量索引
		if (goods.getGoodsId() != null) {
			if (goods.getGoodsCollect()!=null && goods.getGoodsCollect() == -1) {
				// 减少商品关注
				goodsDao.minusGoodsCollect(goods.getGoodsId());
			} else {
				goodsDao.updateGoods(goods);
			}
		}
		
		// 修改下架等操作,删除索引
		if (goods.getGoodsId() != null) {
			Integer goodsId = goods.getGoodsId();
			boolean tag = true;
			//是否需要删除索引
			if(goods.getGoodsShow() != null){
				if(goods.getGoodsShow().equals(GoodsState.GOODS_OFF_SHOW)){
					tag = false;
				}
			}
			if(goods.getGoodsState() != null){
				if(goods.getGoodsState().equals(GoodsState.GOODS_CLOSE_STATE)){
					tag = false;
				}
			}
			if(goods.getGoodsStoreState() != null){
				if(goods.getGoodsStoreState().equals(GoodsState.GOODS_STORE_CLOSE)){
					tag = false;
				}
			}
			goodsSearchService.deleteGoodsIndex("goodsId",goodsId);
			if(tag){
				goodsSearchService.saveOneGoodsIndex(goodsId);
			}
		}else
		//店铺开启
		if(goods.getGoodsId() == null && goods.getStoreId() != null && goods.getGoodsStoreState().equals(GoodsState.GOODS_STORE_CLOSE)){
			goodsSearchService.deleteGoodsIndex("storeId",goods.getStoreId());
		}else
		//店铺关闭
		if(goods.getGoodsId() == null && goods.getStoreId() != null && goods.getGoodsStoreState().equals(GoodsState.GOODS_STORE_OPEN)){
			goodsSearchService.deleteGoodsIndex("storeId",goods.getStoreId());
			Goods goodsCondition = new Goods();
			goodsCondition.setStoreId(goods.getStoreId());
			goodsSearchService.saveGoodsIndex(goodsCondition);
		}
	}

	public void deleteGoods(Integer goodsId) {
    	//删除商品表(shop_goods)
		goodsDao.deleteGoods(goodsId);
		//shop_goods_spec
		//goodsSpecDao.deleteGoodsSpecByGoodsId(goodsId);
		//shop_goods_spec_index
		//goodsSpecIndexDao.deleteByGoodsId(goodsId);
		//shou_goods_attr_index
		//goodsAttrIndexDao.deleteByGoodsId(goodsId);
		goodsSearchService.deleteGoodsIndex("goodsId",goodsId);
	}

	public Goods findOneGoodByCondition(Goods goods) {
		return goodsDao.findOneGoodByCondition(goods);
	}

	public List<String> getGoodsImgList(int goodsId) {
		Goods goods = goodsDao.findGoodById(goodsId);
		List<String> imageList = null;
				if(goods!=null){
					if(goods.getGoodsImageMore()!=null && !"".equals(goods.getGoodsImageMore())){
						imageList=Arrays.asList(goods.getGoodsImageMore().split(","));
					}
				}
		return imageList;
	}

	public Map<String, Object> getGoodsSpec(int goodsId) {
		Goods goods = goodsDao.findGoodById(goodsId);
		if(goods==null) {
			return null;
		}
		String goodsSpec = goods.getGoodsSpec();
		String specName = goods.getSpecName();
		if(specName == null || specName.equals("")){
			return null;
		}
		Map<String, String> specNameMap = JsonUtils.readJsonToMap(specName);
		Map<String, List<GoodsSpecVo>> goodsSpecMap = GoodsUtils.goodsSpecStrToMapList(goodsSpec);
		List<GoodsSpec> goodsSpecs = goodsSpecService.findListByGoodsId(goodsId);
        //规格颜色对应的图片
        Map<String, String> goodsColImg = GoodsUtils.goodsColImgStrToMap(goods.getGoodsColImg());
        //得到该商品的所有goodsvalueId的String,以逗号分割
        for(int i = 0; i < goodsSpecs.size(); i++){
        	goodsSpecs.get(i).setSpecValueIdStr(
        			GoodsUtils.getThisGoodsAllSpecValueId(
        					goodsSpecs.get(i).getSpecGoodsSpec()
        			)
        	);
        }
		Map<String, Object> specmap = new HashMap<String, Object>();
		specmap.put("goodsColImg", goodsColImg);
		specmap.put("specname", specNameMap);
		specmap.put("specvalue", goodsSpecMap);
		specmap.put("goodsSpecs", goodsSpecs);
		return specmap;
	}

	public Map<String, Object> getGoodsAttr(int goodsId) {
		Goods goods = goodsDao.findGoodById(goodsId);
		String attr = goods.getGoodsAttr();
		List<GoodsAttrVo> goodsAttrVos = GoodsUtils.goodsAttrStrToGoodsAttrVoClass(attr);
		String goodsBody = goods.getGoodsBody();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsattr", goodsAttrVos);
		map.put("goodsbody", goodsBody);
		map.put("goodsbrandname", goods.getBrandName());
		return map;
	}

	public int countGoods(Goods goods) {
		return goodsDao.countGoods(goods);
	}
	/**
	 * 分页查询获得findTradeGoodlist
	 * @param pager
	 * @return
	 */
	@Override
	public List<GoodsTradeVo> findTradeGoodPagerList(Pager pager) {
		return goodsDao.findTradeGoodPagerList(pager);
	}
	/**
	 * 根据商品字段获取商品的数量
	 * @param goods
	 * @return
	 */
	@Override
	public int findTradeGoodcount(GoodsTradeVo goodsTradeVo) {
		// TODO Auto-generated method stub
		return goodsDao.findTradeGoodcount(goodsTradeVo);
	}

	@Override
	public void delserchgoods(Integer storeId) {
		List<Goods> goodslist=goodsDao.findGoodidList(storeId);
		for (Goods goodst : goodslist) {
			if(goodst.getGoodsId()!=null){
            Goods goods = new Goods();
            goods.setGoodsId(goodst.getGoodsId());
            goods.setGoodsState(GoodsState.GOODS_CLOSE_STATE);
            goods.setGoodsShow(GoodsState.GOODS_OFF_SHOW);
            goods.setGoodsCloseReason("店铺关闭");
            try {
				updateGoods(goods);
				toStaticService.deleteGoodsDetailStaticPage(goodst.getGoodsId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		}	
	}
	
	@Override
	public void saveStoreGoods(GoodsStore goodsStore)  throws Exception {
		goodsDao.saveStoreGoods(goodsStore);
	}
	
	@Override
	public GoodsStore findStoreGoodByCondition(GoodsStore goodsStore) {
		return goodsDao.findStoreGoodByCondition(goodsStore);
	}
	
	
	public void updateStoreGoods(GoodsStore goodsStore) throws IOException, TemplateException, ServletException {
		//修改上架则增量索引
		goodsDao.updateStoreGoods(goodsStore);
		
		/*TODO索引问题因为拆表后不清
		//修改下架等操作,删除索引
		if(goodsStore.getGoodsId() != null){
			Integer goodsId = goods.getGoodsId();
			boolean tag = true;
			//是否需要删除索引
			if(goodsStore.getGoodsShow() != null){
				if(goodsStore.getGoodsShow().equals(GoodsState.GOODS_OFF_SHOW)){
					tag = false;
				}
			}
			if(goods.getGoodsState() != null){
				if(goods.getGoodsState().equals(GoodsState.GOODS_CLOSE_STATE)){
					tag = false;
				}
			}
			if(goodsStore.getGoodsStoreState() != null){
				if(goodsStore.getGoodsStoreState().equals(GoodsState.GOODS_STORE_CLOSE)){
					tag = false;
				}
			}
			goodsSearchService.deleteGoodsIndex("goodsId",goodsId);
			if(tag){
				goodsSearchService.saveOneGoodsIndex(goodsId);
			}
		}else
		//店铺开启
		if(goodsStore.getGoodsId() == null && goodsStore.getStoreId() != null && goodsStore.getGoodsStoreState().equals(GoodsState.GOODS_STORE_CLOSE)){
			goodsSearchService.deleteGoodsIndex("storeId",goodsStore.getStoreId());
		}else
		//店铺关闭
		if(goodsStore.getGoodsId() == null && goodsStore.getStoreId() != null && goodsStore.getGoodsStoreState().equals(GoodsState.GOODS_STORE_OPEN)){
			goodsSearchService.deleteGoodsIndex("storeId",goodsStore.getStoreId());
			Goods goodsCondition = new Goods();
			goodsCondition.setStoreId(goods.getStoreId());
			goodsSearchService.saveGoodsIndex(goodsCondition);
		}
		
		*/
	}
	
	
	public List<Goods> findStoreGoodPagerList(Pager pager) {
		return goodsDao.findStoreGoodPagerList(pager);
	}
	public List<Goods> findSaleGoodPagerList(Pager pager) {
		return goodsDao.findSaleGoodPagerList(pager);
	}

	@Override
	public Goods findGoodsById(Integer goodsId) {
		return goodsDao.findGoodsById(goodsId);
	}

	

	@Override
	public GoodsStore findGoodsStoreEndTime(Goods gd) {
		return goodsDao.findGoodsStoreEndTime(gd);
	}
	
	/**
	 * 更新点击量
	 * @param storeId
	 */
	@Override
	public void updateGoodsClick(Integer goodsId) {
		goodsDao.updateGoodsClick(goodsId);

		// 更新索引
		if (goodsId != null) {
			// 先删除
			goodsSearchService.deleteGoodsIndex("goodsId", goodsId);
			goodsSearchService.saveOneGoodsIndex(goodsId);
		}
	}

	@Override
	public List<Goods> findGoodsByTypeId(int[] ids) {
		return goodsDao.findGoodsByTypeId(ids);
	}

	@Override
	public void updateGoodsToMoney(Goods goods) {
		goodsDao.updateGoods(goods);
	}
	
	/**
	 * 根据shop_goods_recommend 和 学院id查询课程
	 * @param pager
	 * @return
	 */
	public List<Goods> findRecommendGoodPager(Pager pager) {
		return goodsDao.findRecommendGoodPager(pager);
	}

	@Override
	public List<Goods> findRcGoodPager(Pager pager) {
		// TODO Auto-generated method stub
		return goodsDao.findRcGoodPager(pager);
	}

	@Override
	public List<Goods> findAcGoodPager(Pager pager) {
		// TODO Auto-generated method stub
		return goodsDao.findAcGoodPager(pager);
	}
}
