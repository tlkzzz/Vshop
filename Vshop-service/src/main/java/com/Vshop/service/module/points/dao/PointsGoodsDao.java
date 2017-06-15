package com.Vshop.service.module.points.dao;

import java.util.List;

import com.Vshop.core.entity.base.PointsGoods;
import com.Vshop.service.utils.page.Pager;

/**
 * 项目名称：积分商品
 * 类名称：PointsGoodsMapper   
 * 创建人：cgl   
 * 创建时间：2015年08月26日10:51:34
 */
public interface PointsGoodsDao {
	
	/**
	 * 通过pointsGoodsid查找goods
	 * @param goodsId
	 * @return
	 */
	PointsGoods findPointsGoodById(Integer pointsGoodsId);
	
	/**
	 * 分页查询获得记录数
	 * @param pager
	 * @return
	 */
	int findPointsGoodPagerListCount(Pager pager);
	
	/**
	 * 分页查询获得list
	 * @param pager
	 * @return
	 */
	List<PointsGoods> findPointsGoodsPagerList(Pager pager);
	
	/**
	 * 保存
	 * @param PointsGoods
	 */
	void savePointsGoods(PointsGoods pointsGoods);
	
	/**
	 * 修改商品
	 * @param goods
	 */
	void updatePointsGoods(PointsGoods pointsGoods);
	
	/**
	 * 删除商品
	 * @param goodsId
	 */
	void deletePointsGoods(Integer pointsGoodsId);
	
	/**
	 * 通过一定条件的条件,查找某个商品,
	 * 这个方法只会返回一个商品,
	 * 使用方法:
	 * 新建一个PointsGoods对象,在这个对象中
	 * 一定要设置pointsGoodsId这个属性
	 * 可以选择set属性:storeId,goodsState
	 * 使用这个方法会根据你所设置的条件去查询,
	 * 如果没有返回null
	 */
	PointsGoods findOnePointsGoodByCondition(PointsGoods pointsGoods);
}
