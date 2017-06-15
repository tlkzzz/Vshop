package com.Vshop.service.module.goods.service;

import java.util.List;

import com.Vshop.core.entity.base.GoodsStore;
import com.Vshop.core.entity.base.RelGoodsRecommend;

/**
 *    
 * 项目名称：Vshop-seller  
 * 类名称：RelGoodsRecommendService   
 * 类描述：   
 * 创建人：gyh 
 * 创建时间：2015年8月25日 下午10:59:04   
 * 修改备注：   
 * @version    
 *
 */
public interface RelGoodsRecommendService {
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
     * 根据商品栏目id查询商品id
     * @param reCommendId
     */
	List<RelGoodsRecommend> findgoodsids(Integer reCommendId);

	
	/**
     * 根据商品栏目id查询商品
     * @param reCommendId
     * @param storeid
     */
	List<RelGoodsRecommend> findStoregoodsList(Integer reCommendId,Integer storeid);

	
	
	/**
	 * 向关联表(shop_rel_goods_recommend)中插入strore和goods关联的信息
	 * @param rel
	 */
	void saveStoreIdGoodsId(RelGoodsRecommend rel);
	
	/**
	 * 产品下架时删除关联表中的关联数据
	 */
	void deleteByGoodsIdAndStoreId(RelGoodsRecommend rel);
	/**
	 * 判断是否有相同数据存在
	 * @param rel
	 * @return 
	 */
	RelGoodsRecommend findByStoreAndGoodsId(RelGoodsRecommend rel);
	
	/**
	 * 出售列表查询是否是新品上架
	 * @param rel
	 * @return
	 */
	List<RelGoodsRecommend> findByStoreAndGoodsIds(RelGoodsRecommend rel);

}
