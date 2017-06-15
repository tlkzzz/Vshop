package com.Vshop.service.module.goods.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.base.ShopStatGoods;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * 
 * @项目名称：Vshop-seller
 * @类名称：BrandMapper
 * @类描述：
 * @创建人：shining
 * @创建时间：2014年12月3日 下午6:22:15
 * @修改人：shining
 * @修改时间：2014年12月3日 下午6:22:15
 * @修改备注：
 * @version
 * 
 */
@SqlMapper
public interface ShopStatGoodsMapper {
	/**
	 * 保存商品浏览记录
	 * @param shopStatGoods
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
	 * 根据id查找内容
	 * @param id
	 * @return
	 */
	Brand findbyIds(ShopStatGoods shopStatGoods);
    
}
