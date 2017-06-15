package com.Vshop.service.module.trade.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.ReturnOrder;
import com.Vshop.core.entity.vo.ReturnOrderVo;
import com.Vshop.service.module.trade.dao.ReturnOrderDao;
import com.Vshop.service.module.trade.dao.mapper.ReturnOrderMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * 退货表
 * @author liukai
 */
@Repository
public class ReturnOrderDaoImpl implements ReturnOrderDao{
	
	@Resource
	private ReturnOrderMapper returnOrderMapper;
	
	/**
	 * 保存退货表
	 * @param returnOrder
	 */
	public void saveReturnOrder(ReturnOrder returnOrder){
		returnOrderMapper.saveReturnOrder(returnOrder);
	}
	
	/**
	 * 修改退货表
	 * @param returnOrder
	 */
	public void updateReturnOrder(ReturnOrder returnOrder){
		returnOrderMapper.updateReturnOrder(returnOrder);
	}
	
	/**
	 * 根据id删除退货表
	 * @param returnId
	 */
	public void deleteReturnOrder(Integer returnId){
		returnOrderMapper.deleteReturnOrder(returnId);
	}
	
	/**
	 * 分页查询退货总条数
	 * @param returnOrder
	 * @return
	 */
	public int findReturnOrderCount(ReturnOrder returnOrder){
		return returnOrderMapper.findReturnOrderCount(returnOrder);
	}
	
	/**
	 * 分页查询退货记录
	 * @param pager
	 * @return
	 */
	public List<ReturnOrder> findReturnOrderList(Pager pager){
		return returnOrderMapper.findReturnOrderList(pager);
	}
	
	/**
	 * 根据id查询退货记录
	 * @param returnId
	 * @return
	 */
	public ReturnOrderVo findById(Integer returnId){
		return returnOrderMapper.findById(returnId);
	}
	
	/**
	 * 根据订单id查询退货信息
	 * @param orderId 订单id
	 * @return
	 */
	public ReturnOrderVo findByOrderId(Integer orderId){
		return returnOrderMapper.findByOrderId(orderId);
	}
}
