package com.Vshop.service.module.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.ShopPointsLog;
import com.Vshop.service.module.member.dao.ShopPointsLogDao;
import com.Vshop.service.module.member.service.ShopPointsLogService;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：Vshop-admin   
 * 类名称：ShopPointsLogService   
 * 类描述：service实现类
 * 创建人：gyh   
 * 创建时间：2015年7月24日 上午9:44:03   
 * 修改备注：   
 * @version    
 *
 */
@Service("ShopPointsLogService")
public class ShopPointsLogServiceImpl implements ShopPointsLogService {
	@Autowired
	private ShopPointsLogDao shopPointsLogDao;

	@Override
	public void save(ShopPointsLog shopPointsLog) {
		shopPointsLogDao.save(shopPointsLog);
	}

	@Override
	public int findCount(ShopPointsLog shopPointsLog) {
		return shopPointsLogDao.findCount(shopPointsLog);
	}

	@Override
	public List<ShopPointsLog> findPageList(Pager pager) {
		return shopPointsLogDao.findPageList(pager);
	}

	@Override
	public List<ShopPointsLog> findList() {
		return shopPointsLogDao.findList();
	}

}
