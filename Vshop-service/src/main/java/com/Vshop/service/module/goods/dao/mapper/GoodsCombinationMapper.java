package com.Vshop.service.module.goods.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.GoodsCombination;
import com.Vshop.core.orm.mybatis.SqlMapper;

/**
 * 组合商品
 * @author chen
 * 2015年08月14日11:00:49
 */
@SqlMapper
public interface GoodsCombinationMapper {

	/**
	 * @author chen
	 * 保存组合商品
	 */
	void saveGoodsCombination(GoodsCombination goodsCombination);
	
	/**
	 * @author chen
	 * 通过goodsId删除组合商品
	 */
	void deleteByGoodsId(Integer goodsId);
	
	/**
	 * @author chen
	 * 通过条件获取组合商品
	 * 参数说明:在GoodsCombination实体类中设置条件,则返回相应的list
	 * 比如:设置goodsid=1,则返回goodsid=1的组合商品
	 */
	List<GoodsCombination> selectByCondition(GoodsCombination goodsCombination);
	
}
