package com.Vshop.service.module.tostatic.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.freemarker.TagCreator;
import com.Vshop.core.freemarker.ToStaticPageUtils;
import com.Vshop.core.freemarker.shiro.ShiroTags;
import com.Vshop.core.state.goods.GoodsState;
import com.Vshop.service.module.goods.dao.GoodsDao;
import com.Vshop.service.module.tostatic.service.ToStaticService;
import com.Vshop.service.utils.CommonConstants;
import com.Vshop.service.utils.page.Pager;

import freemarker.template.TemplateException;

/**
 * 
 * @author cgl
 * 2015年08月11日09:29:20
 */
@Service
public class ToStaticServiceImpl implements ToStaticService{
	
	@Autowired
	GoodsDao goodsDao;

	/**
	 * 将首页页面转化为静态页面
	 * 抛出异常:IOException, TemplateException, ServletException
	 */
	@Override
	public void indexToStatic() throws IOException, TemplateException, ServletException {
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		/*标签*/
		data.put("newTag", new TagCreator());
		
		/*基本路径*/
		data.put("base", CommonConstants.FRONT_SERVER);
		data.put("imgServer", CommonConstants.IMG_SERVER);
		data.put("sellerServer", CommonConstants.SELLER_SERVER);
		
		/*登陆信息*/
		data.put("shiro", new ShiroTags());
		
		String modelBasePath = CommonConstants.MODEL_BASEPATH;
		
		String modelPath = Constants.INDEX_MODEL;
		
		String staticPagePath = CommonConstants.STATIC_PAGE_BASEPATH + Constants.STATIC_INDEX + "/index.html";
		
		ToStaticPageUtils.createHTML(data, modelBasePath, modelPath, staticPagePath);
		
	}

	/**
	 * 指定某一条商品详细页转为静态页面
	 * 抛出异常:IOException, TemplateException, ServletException
	 */
	@Override
	public void goodsDetailToStaticByGoodsId(Integer goodsId, Integer storeId) throws IOException, TemplateException, ServletException {

		Map<String, Object> data = new HashMap<String, Object>();
		
		/*标签*/
		data.put("newTag", new TagCreator());
		
		/*基本路径*/
		data.put("base", CommonConstants.FRONT_SERVER);
		
		/*登陆信息*/
		data.put("shiro", new ShiroTags());
		
		/*商品路径*/
		data.put("goodsId", goodsId);
		
		/*图片目录*/
		data.put("imgServer", CommonConstants.IMG_SERVER);
		data.put("sellerServer", CommonConstants.SELLER_SERVER);
		data.put("imgSrc", Constants.STORE_IMG_PATH +  "/" + storeId);
		
		String modelBasePath = CommonConstants.MODEL_BASEPATH;
		
		String modelPath = Constants.GOODS_DETAIL_MODEL;
		
		String staticPagePath = CommonConstants.STATIC_PAGE_BASEPATH + Constants.STATIC_GOODS_DETAIL + "/" + goodsId + ".html";
		
		ToStaticPageUtils.createHTML(data, modelBasePath, modelPath, staticPagePath);
	}
	
	/**
	 * 批量将商品详细页转为静态页面
	 */
	@Override
	public void  goodsDetailToStaticBatch() throws IOException, TemplateException, ServletException {

		/**
		 * 这里利用分页,来将数据库中的所有上架商品进行静态化处理,
		 * 就一条数据对应一个静态页面,
		 * 这里利用分页是因为:如果数据库数据太多,一次取出导致内存溢出.分页取出很好的避免了这个危险
		 */
		Pager pager = new Pager();
		
		/**设置一页的大小*/
		pager.setPageSize(1000);
		
		/**查询条件*/
		Goods goodsCondition = new Goods();
		
		/**上架*/
		goodsCondition.setGoodsShow(GoodsState.GOODS_ON_SHOW);
		
		/**店铺状态开启*/
		goodsCondition.setGoodsStoreState(GoodsState.GOODS_STORE_OPEN);
		
		/**商品开启*/
		goodsCondition.setGoodsState(GoodsState.GOODS_OPEN_STATE);
		
		/**设置查询条件到pager*/
		pager.setCondition(goodsCondition);
		
		/**获得共有多少条数据*/
		int count = goodsDao.countGoods(goodsCondition);
		
		/**算出共有多少页*/
		int pageCount;
		
		if(count%pager.getPageSize() != 0){
			
			pageCount = count/pager.getPageSize() + 1;
			
		}else{
			
			pageCount = count/pager.getPageSize();
			
		}
		
		/**循环的将所有页静态化*/
		for(int i = 1; i <= pageCount; i++){
			
			/**设置第几页*/
			pager.setPageNo(i);
			
			List<Goods> list = goodsDao.findGoodPagerList(pager);
			
			/**循环将每一页的商品进行静态化*/
			for(Goods goods : list){
				
				/**调用本类中的方法,生成静态化页面*/
				goodsDetailToStaticByGoodsId(goods.getGoodsId(), goods.getStoreId());
				
			}
			
		}
		
	}

	/**
	 * 删除指定的商品静态页面
	 */
	@Override
	public void deleteGoodsDetailStaticPage(Integer goodsId) {
		
		String staticPagePath = CommonConstants.STATIC_PAGE_BASEPATH + Constants.STATIC_GOODS_DETAIL + "/" + goodsId + ".html";
		
		File file = new File(staticPagePath);
		
		/**判断是否存在这个网址*/
		if(file.exists()){
			file.delete();
		}
		
	}
}
