package com.Vshop.service.module.points.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.Vshop.core.entity.base.PointsGoods;
import com.Vshop.service.utils.page.Pager;

import freemarker.template.TemplateException;

/**
 * 
 *    
 * 项目名称：积分商品
 * 类名称：PointsGoodsMapper   
 * 创建人：cgl   
 * 创建时间：2015年08月26日10:51:34
 */
public interface PointsGoodsService {

	/**
	 * 通过goodsid查找goods
	 * @param goodsId
	 * @return
	 */
	PointsGoods findPointsGoodById(Integer pointsGoodsId);
	
	/**
	 * 分页查询获得记录数
	 * @param pager
	 * @return
	 */
	int findPointsGoodsPagerListCount(Pager pager);
	
	/**
	 * 分页查询获得list
	 * @param pager
	 * @return
	 */
	List<PointsGoods> findPointsGoodsPagerList(Pager pager);
	
	/**
	 * 保存
	 * @param goods
	 */
	void savePointsGoods(PointsGoods pointsGoods, Integer goodsId) throws IOException, TemplateException, ServletException, NullPointerException;
	
	/**
	 * 修改商品
	 * @param goods
	 */
	void updatePointsGoods(PointsGoods pointsGoods) throws IOException, TemplateException, ServletException;
	
	/**
	 * 删除商品
	 * @param goodsId
	 */
	void deletePointsGoods(Integer pointsGoodsId);
	
	/**
	 * 通过一定条件的条件,查找某个商品,
	 * 这个方法只会返回一个商品,
	 * 使用方法:
	 * 新建一个goods对象,在这个对象中
	 * 一定要设置goodsid这个属性
	 * 可以选择set属性:storeId,goodsState
	 * 使用这个方法会根据你所设置的条件去查询,
	 * 如果没有返回null
	 */
	PointsGoods findOneGoodByCondition(PointsGoods pointsGoods);
	
	public int brandCount(Pager pager);
	
    public List<PointsGoods> queryBrandList(Pager pager);
    
    /**
     * 获取商品的相册
     * @param goodsid
     * @return
     */
    List<String> getPointsGoodsImgList(int pointsGoodsId);
    
    /**
     * 获取商品的规格
     * @param goodsId
     * @return
     */
    Map<String, Object> getPointsGoodsSpec(int pointsGoodsId);
    
    /**
     * 获取商品的属性
     * @param goodsId
     * @return
     */
    Map<String, Object> getPointsGoodsAttr(int pointsGoodsId);
}
