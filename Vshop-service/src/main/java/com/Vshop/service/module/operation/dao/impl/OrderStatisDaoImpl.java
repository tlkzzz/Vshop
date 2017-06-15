package com.Vshop.service.module.operation.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.OrderStatis;
import com.Vshop.service.module.operation.dao.OrderStatisDao;
import com.Vshop.service.module.operation.dao.mapper.OrderStatisMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：Vshop-admin   
 * 类名称：OrderStatisDaoImpl   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年11月14日 上午12:10:28   
 * 修改人：liuhao   
 * 修改时间：2014年11月14日 上午12:10:28   
 * 修改备注：   
 * @version    
 *
 */

@Repository
public class OrderStatisDaoImpl implements OrderStatisDao{

    @Autowired
    private OrderStatisMapper orderStatisMapper ;

	@Override
	public int countOrderStatis(Pager pager) {
		// TODO Auto-generated method stub
		return orderStatisMapper.countOrderStatis(pager);
	}

	@Override
	public List<OrderStatis> queryOrderStatisList(Pager pager) {
		// TODO Auto-generated method stub
		return orderStatisMapper.queryOrderStatisList(pager);
	}
    
    
    
}
