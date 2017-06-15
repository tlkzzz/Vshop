package com.Vshop.service.module.member.dao;


import java.util.List;

import com.Vshop.core.entity.base.ShopPointsLog;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：Vshop-admin   
 * 类名称：ShopPointsLogDao   
 * 类描述：接口
 * 创建人：gyh   
 * 创建时间：2015年7月24日 上午9:39:20   
 * 修改备注：   
 * @version    
 *
 */
public interface ShopPointsLogDao {
    
	/**
	 * 保存会员积分记录
	 * @param shopStatGoods
	 */
	void save(ShopPointsLog shopPointsLog);
	/**
	 * 获取条数
	 * @param shopPointsLog
	 * @return
	 */
	int findCount(ShopPointsLog shopPointsLog);
	
	/**
	 * 获取分页数据
	 * @param pager
	 * @return
	 */
	List<ShopPointsLog> findPageList(Pager pager);

	/**
	 * 获取全部数据
	 * @return
	 */
	List<ShopPointsLog> findList();
}
