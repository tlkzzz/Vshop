package com.Vshop.service.module.setting.dao;

import java.util.List;

import com.Vshop.core.entity.Site;
import com.Vshop.core.entity.base.Payment;


/**
 * 支付方式
 *    
 * 项目名称：vixuan-admin   
 * 类名称：PaymentMapper   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年11月10日 下午11:33:35   
 * 修改人：liuhao   
 * 修改时间：2014年11月10日 下午11:33:35   
 * 修改备注：   
 * @version    
 *
 */
public interface SiteDao{
	/**
	 * 查询所有信息
	 * @return 
	 */
	public Site findById();
  
	/**
	 * 修改信息
	 * @param site
	 */
	public  void update(Site site);
	
	

}
