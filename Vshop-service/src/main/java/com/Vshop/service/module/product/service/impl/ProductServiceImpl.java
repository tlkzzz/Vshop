package com.Vshop.service.module.product.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.GoodsSpec;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.GoodsAttrIndex;
import com.Vshop.core.entity.base.GoodsSpecIndex;
import com.Vshop.core.entity.base.GoodsStore;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.core.state.goods.GoodsState;
import com.Vshop.service.module.goods.service.GoodsAttrIndexService;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.module.goods.service.GoodsSpecIndexService;
import com.Vshop.service.module.goods.service.GoodsSpecService;
import com.Vshop.service.module.product.service.ProductService;
import com.Vshop.service.utils.goods.GoodsUtils;

import freemarker.template.TemplateException;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	GoodsSpecService goodsSpecService;
	
	@Autowired
	GoodsSpecIndexService goodsSpecIndexService;
	
	@Autowired
	GoodsAttrIndexService goodsAttrIndexService;
	
	
	/**
	 * 保存goods
	 * cgl
	 * 2015年06月17日11:50:08
	 * 返回 0 则保存失败
	 * 否则返回goodsId
	 */
	@Override
	public Integer saveGoods(Goods goods, String goodsSpecJson) {
		try{
		//保存goods
		//商品状态
		goods.setGoodsState(0);
		//商品添加时间
		//发布开始时间
		//发布结束时间
		//商品所在店铺状态 0开启 1关闭
		goods.setGoodsStoreState(0);
		goodsService.saveGoods(goods);
		//保存至goodsspec
		saveToGoodsSpec(goods, goodsSpecJson);
		//保存到2涨索引表(goodsAttrIndex&goodsSpecIndex)
		Integer goodsId = saveToSpecAndAttrIndex(goods);
		return goodsId;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	
	/**
	 * 保存goods
	 * cgl
	 * 2015年06月17日11:50:08
	 * 返回 0 则保存失败
	 * 否则返回goodsId
	 */
	@Override
	public Integer savePreGoods(Goods goods, String goodsSpecJson) {
		try{
		//保存goods
		//商品状态
		goods.setGoodsState(GoodsState.GOODS_APPLY_PREPARE);//待审核
		//商品添加时间
		//发布开始时间
		//发布结束时间
		//商品所在店铺状态 0开启 1关闭
		goods.setGoodsStoreState(0);
		goodsService.saveGoods(goods);
		//保存至goodsspec
		saveToGoodsSpec(goods, goodsSpecJson);
		//保存到2涨索引表(goodsAttrIndex&goodsSpecIndex)
		Integer goodsId = saveToSpecAndAttrIndex(goods);
		return goodsId;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	
	/**
	 * 修改goods
	 * cgl
	 * 2015年06月30日19:18:44
	 * 返回 0 修改失败
	 * 返回 1 修改成功
	 */
	@Override
	public Integer updateGoods(Goods goods, String goodsSpecJson) {
		try{
		//保存goods
		//商品状态
		goods.setGoodsState(0);
		//商品添加时间
		//发布开始时间
		//发布结束时间
		//商品所在店铺状态 0开启 1关闭
		goods.setGoodsStoreState(0);
		goods.setSalenum(0);
		goodsService.updateGoods(goods);
		//保存至goodsspec
		saveToGoodsSpec(goods, goodsSpecJson);
		//保存到2涨索引表(goodsAttrIndex&goodsSpecIndex)
		saveToSpecAndAttrIndex(goods);
		return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//存入goodsspec表
	private void saveToGoodsSpec(Goods goods, String goodsSpecJson) throws IOException, TemplateException, ServletException{
		//在保存之前首先删除goodsSpec表中关于这个goodsId的数据
		goodsSpecService.deleteGoodsSpecByGoodsId(goods.getGoodsId());
		if(goodsSpecJson != null && !goodsSpecJson.trim().equals("")){
			//准备创建表shop_goods_sepc的实体类对象
			List<Object> goodsSpecs = JsonUtils.readJsonList(goodsSpecJson, GoodsSpec.class);
			//循环获得goodsspec
			for(int i = 0; i < goodsSpecs.size(); i++){
				GoodsSpec goodsSpec = (GoodsSpec) goodsSpecs.get(i);
				//设置商品id
				goodsSpec.setGoodsId(goods.getGoodsId());
				//设置销售量为0
				goodsSpec.setSpecSalenum(0);
				//保存goodsspecs
				goodsSpecService.saveGoodsSpec(goodsSpec);
				if(i == 0){
					//设置商品specid
					goods.setSpecId(goodsSpec.getGoodsSpecId());
				}
			}
		}else{
			GoodsSpec goodsSpec = new GoodsSpec();
			//设置商品id
			goodsSpec.setGoodsId(goods.getGoodsId());
			//价格
			goodsSpec.setSpecGoodsPrice(goods.getGoodsStorePrice());
			//库存
			goodsSpec.setSpecGoodsStorage(goods.getGoodsTotalStorage());
			//货号
			goodsSpec.setSpecGoodsSerial(goods.getGoodsSerial());
			//设置销售量为0
			goodsSpec.setSpecSalenum(0);
			//保存goodsspecs
			goodsSpecService.saveGoodsSpec(goodsSpec);
			//设置商品specid
			goods.setSpecId(goodsSpec.getGoodsSpecId());
		}

		//再次修改goods表
		//判断是否存有goodsspec
		if(goods.getSpecId() != null){
			Goods tagGoods = new Goods();
			tagGoods.setGoodsId(goods.getGoodsId());
			tagGoods.setSpecId(goods.getSpecId());
			goodsService.updateGoods(goods);
		}
	}
	
	private Integer saveToSpecAndAttrIndex(Goods goods){
		//商品的id
		Integer goodsId = goods.getGoodsId();
		//商品分类id
		Integer gcId = goods.getGcId();
		//商品类型id
		Integer typeId = goods.getTypeId();
		
		//存入goodsSpecIndex,在数据库中与goods表为多对一,
		//一个oods会对应多个goodsspecIndex
		//判断是否有规格
		if(goods.getSpecId() != null && !goods.getGoodsSpec().trim().equals("")){
			saveToGoodsSpecIndex(goods.getGoodsSpec(), goodsId, gcId, typeId);
		}
		
		//存入goodsAttrIndex
		if(goods.getGoodsAttr() != null &&  !goods.getGoodsAttr().trim().equals("")){
			saveToGoodsAttrIndex(goods.getGoodsAttr(), goodsId, gcId, typeId);
		}
		return goodsId;

	}
	
	//存入GoodsSpecIndex表
	private void saveToGoodsSpecIndex(String spec, Integer goodsId, Integer gcId, Integer typeId){
		//在保存之前,首先删除关于这个商品所有的数据
		goodsSpecIndexService.deleteByGoodsId(goodsId);
		//利用工具类将字符串 转换成实体类,获得的list遍历存入数据库
		List<GoodsSpecIndex> list = GoodsUtils.goodsSpecStrToGoodsSpecIndexClass(spec, goodsId, gcId, typeId);
		//循环放入数据库
		for(int p = 0; p < list.size(); p++){
			goodsSpecIndexService.save(list.get(p));
		}
	}
	
	//存入saveToGoodsAttrIndex表
	private void saveToGoodsAttrIndex(String attr, Integer goodsId, Integer gcId, Integer typeId){
		//在保存之前,首先删除关于这个商品所有的数据
		goodsAttrIndexService.deleteByGoodsId(goodsId);
		//利用工具类将字符串 转换成实体类,获得的list遍历存入数据库
		List<GoodsAttrIndex> list = GoodsUtils.goodsAttrStrToGoodsSpecIndexClass(attr, goodsId, gcId, typeId);
		//循环放入数据库
		for(int p = 0; p < list.size(); p++){
			goodsAttrIndexService.save(list.get(p));
		}
	}


    /**
     * 修改库存
     * @parm GoodsSpec 需要2个参数 specId 以及出售数量 specSalenum(这个出售数量是本次的出售数量)
     * 返回 0 则保存失败
	 * 返回 1 则保存成功
     */
	@Override
	public Integer updateStorage(GoodsSpec goodsSpec) throws IOException, TemplateException, ServletException {
		if(goodsSpec != null){
			if(goodsSpec.getGoodsId() != null && goodsSpec.getSpecSalenum() != null){
				goodsSpecService.updateGoodsSpecStorage(goodsSpec);
				Integer goodsId = goodsSpec.getGoodsId();
				Goods goods = new Goods();
				goods.setGoodsId(goodsId);
				goods.setSalenum(goodsSpec.getSpecSalenum());
				goodsService.updateGoods(goods);
				return 1;
			}
		}
		return 0;
	}
	

	/**
	 * 保存goods
	 * cgl
	 * 2015年06月17日11:50:08
	 * 返回 0 则保存失败
	 * 否则返回goodsId
	 */
	@Override
	public Integer saveStoreGoods(GoodsStore goodsStore) {
		try{
		//保存goods
		//商品状态
//		goods.setGoodsState(0);
		//商品添加时间
		//发布开始时间
		//发布结束时间
		//商品所在店铺状态 0开启 1关闭
//		goods.setGoodsStoreState(0);
		goodsService.saveStoreGoods(goodsStore);
		
		Integer goodsId = goodsStore.getGoodsId();
		return goodsId;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	

}
