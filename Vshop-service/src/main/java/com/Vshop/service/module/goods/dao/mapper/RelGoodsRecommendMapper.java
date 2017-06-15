package com.Vshop.service.module.goods.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.GoodsStore;
import com.Vshop.core.entity.base.RelGoodsRecommend;
import com.Vshop.core.orm.mybatis.SqlMapper;

/**
 * 
 * 项目名称：   
 * 类名称：RelGoodsRecommendMapper   
 * 类描述：   
 * 创建人：gyh   
 * 创建时间：2015年08月24日09:23:26
 * 修改备注：   
 * @version    
 *
 */
@SqlMapper
public interface RelGoodsRecommendMapper {
	/**
     * 保存
     * @param RelGoodsRecommend
     */
    void save(RelGoodsRecommend relGoodsRecommend);
    /**
	 * 删除
	 * @param id
	 */
	void delete(int id);
	/**
     * 根据商品栏目id查询商品
     * @param reCommendId
     */
	List<RelGoodsRecommend> findgoodsList(Integer reCommendId);
	
	
	/**
     * 根据商品栏目id查询商品
     * @param reCommendId
     */
	List<RelGoodsRecommend> findStoregoodsList(Integer reCommendId,Integer storeId);
	
	/**
     * 根据商品栏目id查询商品id
     * @param reCommendId
     */
	List<RelGoodsRecommend> findgoodsids(Integer reCommendId);
	
	/**
	 * 向关联表(shop_rel_goods_recommend)中插入strore和goods关联的信息
	 * @param rel
	 */
	void saveStoreIdGoodsId(RelGoodsRecommend rel);
	
	
	/**
	 * 产品下架时删除(shop_rel_goods_recommend)中的关联数据
	 * @param goodsStore
	 */
	void deleteByGoodsIdAndStoreId(RelGoodsRecommend rel);
	/**
	 * @param rel
	 * @return
	 */
	RelGoodsRecommend findByStoreGoodsId(RelGoodsRecommend rel);
	/**
	 *	查询列表中哪件产品是新品
	 * @param rel
	 * @return
	 */
	List<RelGoodsRecommend> findByStoreGoodsIds(RelGoodsRecommend rel);
}
