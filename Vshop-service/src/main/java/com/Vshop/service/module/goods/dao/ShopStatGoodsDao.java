package com.Vshop.service.module.goods.dao;

import java.util.List;

import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.base.ShopStatGoods;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * 
 * @项目名称：Vshop-seller
 * @类名称：ShopStatGoodsDao
 * @类描述：
 * @创建人：gyh
 * @创建时间：2015年7月23日 下午16:22:28
 * @修改备注：
 * @version
 * 
 */
public interface ShopStatGoodsDao {
	/**
	 * 保存商品记录
	 * @param brand
	 */
	void save(ShopStatGoods shopStatGoods);
	/**
	 * 删除品牌
	 * @param id
	 */
	void delete(int sId);
	/**
	 * 获取条数
	 * @param pager
	 * @return
	 */
	int findCount(Pager pager);
	/**
	 * 获取分页数据
	 * @param pager
	 * @return
	 */
	List<ShopStatGoods> findPageList(Pager pager);
	/**
	 * 获取全部数据
	 * @return
	 */
	List<ShopStatGoods> findList();
	/**
	 * 根据id商品浏览记录
	 * @param id
	 * @return
	 */
	Brand findbyIds(ShopStatGoods shopStatGoods);
}
