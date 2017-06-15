package com.Vshop.service.module.trade.service;

import java.util.List;

import com.Vshop.core.entity.base.ReturnOrder;
import com.Vshop.core.entity.vo.ReturnOrderVo;
import com.Vshop.service.utils.page.Pager;

/**
 * 退货表
 * @author liukai
 */
public interface ReturnOrderService {
	/**
	 * 保存退货表
	 * @param returnOrder
	 */
	void saveReturnOrder(ReturnOrder returnOrder);
	
	/**
	 * 修改退货表
	 * @param returnOrder
	 */
	void updateReturnOrder(ReturnOrder returnOrder);
	
	/**
	 * 根据id删除退货表
	 * @param returnId
	 */
	void deleteReturnOrder(Integer returnId);
	
	/**
	 * 分页查询退货总条数
	 * @param returnOrder
	 * @return
	 */
	int findReturnOrderCount(ReturnOrder returnOrder);
	
	/**
	 * 分页查询退货记录
	 * @param pager
	 * @return
	 */
	List<ReturnOrder> findReturnOrderList(Pager pager);
	
	/**
	 * 根据id查询退货记录
	 * @param returnId
	 * @return
	 */
	ReturnOrderVo findById(Integer returnId);
	
	/**
	 * 根据订单id查询退货信息
	 * @param orderId 订单id
	 * @return
	 */
	ReturnOrderVo findByOrderId(Integer orderId);
	
	/**
	 * 卖家退货审核
	 * @param returnId 退货id
	 * @param returnState 退货状态
	 * @param returnMessage 退货备注
	 */
	void updateReturnOrderSeller(Integer returnId, Integer returnState,String returnMessage);
}
