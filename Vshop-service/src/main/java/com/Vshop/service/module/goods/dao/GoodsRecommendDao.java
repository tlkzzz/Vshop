package com.Vshop.service.module.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.base.GoodsRecommend;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：Vshop-front
 * 类名称：GoodsRecommendDao   
 * 类描述：   
 * 创建人：gyh   
 * 创建时间：2015年8月25日 上午10:24:25   
 * 修改备注：   
 * @version    
 *
 */
public interface GoodsRecommendDao {
	/**
     * 保存
     * @param GoodsRecommend
     */
    void save(GoodsRecommend goodsRecommend);
    /**
	 * 删除
	 * @param id
	 */
	void delete(int reCommendid);
	
	/**
	 * 修改
	 * @param goodsType
	 */
	void update(GoodsRecommend goodsRecommend);
	
	/**
     * 删除
     * @param
     */
	GoodsRecommend findById(@Param("reCommendid") Integer reCommendid);
	
	/**
     * 获取商品栏目集合
     * @param
     */
	List<GoodsRecommend> findList(GoodsRecommend goodsRecommend);
	
	/**
	 * 分页查询获得记录数
	 * @param pager
	 * @return
	 */
	int findCount(GoodsRecommend goodsRecommend);
	
	/**
	 * 分页查询获得list
	 * @param pager
	 * @return
	 */
	List<GoodsRecommend> findPageList(Pager pager);
	/**
     * 根据栏目名称查找
     * @param
     */
	GoodsRecommend findBycolum(@Param("recommendName") String recommendName);
}
