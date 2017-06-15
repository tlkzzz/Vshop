package com.Vshop.service.module.setting.dao;


import java.util.List;

import com.Vshop.core.entity.base.Express;
import com.Vshop.service.utils.page.Pager;


/**
 * 项目名称：Vshop-admin   
 * 类名称：ExpressDao   
 * 类描述：接口
 * 修改备注：   
 * @version    
 */
public interface ExpressDao {
    
    
    public int findExpressCount(Express express);

	public List<Express> findExpressList(Pager pager);
	
	public List<Express> findExpressList();

	public void updateState(Express express);

	public void updateOrder(Express express);

	public void delete(Long id);
	
	public List<Express> findList();
	
	public Express findById(Integer id);
	
	/**
	 * 根据显示状态和是否常用状态查询物流公司信息
	 * @param eState
	 * @param eOrder
	 * @return
	 */
	public List<Express> findExpressListByState(Integer eState,Integer eOrder);
    
}
