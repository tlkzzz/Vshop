package com.Vshop.service.module.trade.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.ReturnOrder;
import com.Vshop.core.entity.vo.ReturnOrderVo;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * 退货表
 * @author liukai
 */
@SqlMapper
public interface ReturnOrderMapper {
	
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
	 * @param pager
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
}
