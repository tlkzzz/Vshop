package com.Vshop.service.module.search.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.vo.SpecVo;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * 商品搜索
 * @author cgl
 */
@SqlMapper
public interface GoodsSearchMapper {
	
	/**
	 * 分页查询获得记录数
	 * @param pager
	 * @return
	 */
	int findGoodPagerListCount(Goods goods);
	
	/**
	 * 分页查询获得list
	 * @param pager
	 * @return
	 */
	List<Goods> findGoodPagerList(Pager pager);
	
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
	Goods findOneGoodByCondition(Goods goods);
	
	/**
	 * 获得当前关键词所对应的可选规格
	 */
	List<SpecVo> getSpecListByKeyword(String keyword);
	
	/**
	 * 获得当前关键词所对应的可选规格
	 */
	List<SpecVo> getSpecListByGcId(Integer gcId);
	
	/**
	 * 获得当前关键词所对应的可选规格
	 */
	List<SpecVo> getSpecListByTypeId(Integer typeId);
	
	/**
	 * 获得当前关键词所对应的可选规格
	 */
	List<SpecVo> getSpecListByBrandId(Integer brandId);
	
	/**
	 * 获得当前关键词所对应的可选品牌
	 */
	List<Brand> getBrandListByKeyword(String keyword);
	
	/**
	 * 获得当前关键词所对应的可选品牌
	 */
	List<Brand> getBrandListByGcId(Integer gcId);
	
	/**
	 * 获得当前关键词所对应的可选品牌
	 */
	List<Brand> getBrandListByTypeId(Integer typeId);
	
}
