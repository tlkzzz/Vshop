package com.Vshop.service.module.member.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.ShopPointsLog;
import com.Vshop.service.module.base.BaseDao;
import com.Vshop.service.module.member.dao.ShopPointsLogDao;
import com.Vshop.service.module.member.dao.mapper.ShopPointsLogMapper;
import com.Vshop.service.utils.page.Pager;

/**
 *    
 * 项目名称：Vshop-admin   
 * 类名称：ShopPointsLogDaoImpl   
 * 类描述：   DAO 实现类
 * 创建人：gyh  
 * 创建时间：2015年7月24日 上午7:33:15   
 * 修改备注：   
 * @version    
 *
 */
@Repository
public class ShopPointsLogDaoImpl extends BaseDao implements ShopPointsLogDao {
    @Autowired
    private ShopPointsLogMapper shopPointsLogMapper;

	@Override
	public void save(ShopPointsLog shopPointsLog) {
		shopPointsLogMapper.save(shopPointsLog);
	}

	@Override
	public int findCount(ShopPointsLog shopPointsLog) {
		return shopPointsLogMapper.findCount(shopPointsLog);
	}

	@Override
	public List<ShopPointsLog> findPageList(Pager pager) {
		return shopPointsLogMapper.findPageList(pager);
	}

	@Override
	public List<ShopPointsLog> findList() {
		return shopPointsLogMapper.findList();
	}
	
}
