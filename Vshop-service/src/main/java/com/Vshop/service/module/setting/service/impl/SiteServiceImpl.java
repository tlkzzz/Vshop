package com.Vshop.service.module.setting.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.Site;
import com.Vshop.core.entity.base.Payment;
import com.Vshop.service.module.setting.dao.PaymentDao;
import com.Vshop.service.module.setting.dao.SiteDao;
import com.Vshop.service.module.setting.service.PaymentService;
import com.Vshop.service.module.setting.service.SiteService;

/**
 * 支付方式
 * 项目名称：vixuan-admin   
 * 类名称：PaymentServiceImpl   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年11月10日 下午11:33:15   
 * 修改人：liuhao   
 * 修改时间：2014年11月10日 下午11:33:15   
 * 修改备注：   
 * @version    
 *
 */

@Service("siteService")
public class SiteServiceImpl implements SiteService {

	@Resource
    private SiteDao siteDao;

	@Override
	public Site findById() {
		// TODO 自动生成的方法存根
		
		return this.siteDao.findById();
	}

	@Override
	public void update(Site site) {
		// TODO 自动生成的方法存根
		this.siteDao.update(site);
	}




	


	
}

