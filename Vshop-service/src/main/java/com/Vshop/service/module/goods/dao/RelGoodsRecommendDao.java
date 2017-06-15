package com.Vshop.service.module.goods.dao;

import java.util.List;

import com.Vshop.core.entity.base.GoodsStore;
import com.Vshop.core.entity.base.RelGoodsRecommend;

/**
 * 
 *    
 * 项目名称：Vshop-front
 * 类名称：RelGoodsRecommendDao   
 * 类描述：   
 * 创建人：gyh   
 * 创建时间：2015年8月25日 上午10:44:25   
 * 修改备注：   
 * @version    
 *
 */
public interface RelGoodsRecommendDao {
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
	List<RelGoodsRecommend> findStoregoodsList(Integer reCommendId,Integer storeid);
	
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
	 * 判断是否有相同数据
	 * @param rel
	 * @return
	 */
	RelGoodsRecommend findByStoreGoodsId(RelGoodsRecommend rel);
	/**
	 * 出售商品列表判断是否是新品
	 * @param rel
	 * @return
	 */
	List<RelGoodsRecommend> findByStoreGoodsIds(RelGoodsRecommend rel);
}
