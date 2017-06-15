package com.Vshop.service.module.operation.service;

import java.util.List;

import com.Vshop.core.entity.base.OrderStatis;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * 项目名称：Vshop-admin   
 * 类名称：OrderStatisService   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年11月14日 上午12:09:00   
 * 修改人：liuhao   
 * 修改时间：2014年11月14日 上午12:09:00   
 * 修改备注：   
 * @version    
 *
 */
public interface OrderStatisService {
	
	/**
	 * 
	 * @Title: countOrderStatis 
	 * @Description: TODO(count总数查询) 
	 * @param @param pager
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
    public int countOrderStatis(Pager pager);
    
    /**
     * 
     * @Title: queryOrderStatisList 
     * @Description: TODO(带分页list 查询) 
     * @param @param pager
     * @param @return    设定文件 
     * @return List<AdminLog>    返回类型 
     * @throws
     */
    public List<OrderStatis> queryOrderStatisList(Pager pager);
    
}
