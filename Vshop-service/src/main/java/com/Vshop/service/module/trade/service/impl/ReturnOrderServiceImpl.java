package com.Vshop.service.module.trade.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.ReturnOrder;
import com.Vshop.core.entity.vo.ReturnOrderVo;
import com.Vshop.service.module.trade.dao.ReturnOrderDao;
import com.Vshop.service.module.trade.service.ReturnOrderService;
import com.Vshop.service.utils.page.Pager;

/**
 * 退货表
 * @author liukai
 */
@Service
public class ReturnOrderServiceImpl implements ReturnOrderService{
	
	@Resource
	private ReturnOrderDao returnOrderDao;
	
	/**
	 * 保存退货表
	 * @param returnOrder
	 */
	public void saveReturnOrder(ReturnOrder returnOrder){
		returnOrderDao.saveReturnOrder(returnOrder);
	}
	
	/**
	 * 修改退货表
	 * @param returnOrder
	 */
	public void updateReturnOrder(ReturnOrder returnOrder){
		returnOrderDao.updateReturnOrder(returnOrder);
	}
	
	/**
	 * 根据id删除退货表
	 * @param returnId
	 */
	public void deleteReturnOrder(Integer returnId){
		returnOrderDao.deleteReturnOrder(returnId);
		
	}
	
	/**
	 * 分页查询退货总条数
	 * @param returnOrder
	 * @return
	 */
	public int findReturnOrderCount(ReturnOrder returnOrder){
		return returnOrderDao.findReturnOrderCount(returnOrder);
	}
	
	/**
	 * 分页查询退货记录
	 * @param pager
	 * @return
	 */
	public List<ReturnOrder> findReturnOrderList(Pager pager){
		return returnOrderDao.findReturnOrderList(pager);
	}
	
	/**
	 * 根据id查询退货记录
	 * @param returnId
	 * @return
	 */
	public ReturnOrderVo findById(Integer returnId){
		return returnOrderDao.findById(returnId);
	}
	
	/**
	 * 根据订单id查询退货信息
	 * @param orderId 订单id
	 * @return
	 */
	public ReturnOrderVo findByOrderId(Integer orderId){
		return returnOrderDao.findByOrderId(orderId);
	}
	
	/**
	 * 卖家退货审核
	 * @param returnId 退货id
	 * @param returnState 退货状态
	 * @param returnMessage 退货备注
	 */
	public void updateReturnOrderSeller(Integer returnId, Integer returnState,String returnMessage){
		ReturnOrder returnOrder = returnOrderDao.findById(returnId);
		if(returnOrder != null){
			returnOrder.setReturnState(returnState);
			returnOrder.setReturnMessage(returnMessage);
			returnOrder.setSellerTime(System.currentTimeMillis());
			returnOrderDao.updateReturnOrder(returnOrder);
		}
	}
}
