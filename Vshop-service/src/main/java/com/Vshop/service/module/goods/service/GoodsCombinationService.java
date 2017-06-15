package com.Vshop.service.module.goods.service;

import java.util.List;

import com.Vshop.core.entity.base.GoodsCombination;

/**
 * 组合商品service
 * @author chen
 * 2015年08月14日11:00:49
 */
public interface GoodsCombinationService {

	/**
	 * @author chen
	 * 通过条件获取组合商品
	 * 参数说明:在GoodsCombination实体类中设置条件,则返回相应的list
	 * 比如:设置goodsid=1,则返回goodsid=1的组合商品
	 */
	List<GoodsCombination> selectByCondition(GoodsCombination goodsCombination);
	
	
	/**
	 * @author chen
	 * 修改组合商品
	 * 参数说明:
	 * 在这个实体类中需要设置2个参数:goodsId商品的id,
	 * 以及allCombinationGoodsIdStr所有被组合的商品id,以逗号分隔
	 * 2015年08月14日11:03:24
	 */
	void updateGoodsCombination(GoodsCombination goodsCombination);
}
