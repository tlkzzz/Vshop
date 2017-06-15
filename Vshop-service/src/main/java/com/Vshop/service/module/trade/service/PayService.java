package com.Vshop.service.module.trade.service;

import com.Vshop.core.entity.base.Pay;

/**
 * 支付相关service
 * @author liukai
 */
public interface PayService {
	
	/**
	 * 根据单号查询支付金额,支付单号可传订单号,支付单号或充值单号
	 * @param Pay 可传订单号和支付单号
	 * @return 返回Pay只有paySn,payAmount两个字段有值
	 */
	Pay findPayBySn(String sn);
	
	/**
	 * 支付完成后,根据编号修改相应的信息
	 * @param sn
	 */
	void updatePayFinish(String sn);
	
	/**
	 * 修改相关支付信息
	 * @param sn 编号
	 * @param code 支付标识
	 */
	void updatePaymentBySn(String sn,String code);
	
	/**
	 * 充值新增充值信息并set到支付实体
	 * @param amount
	 * @param memberId
	 * @return
	 */
	Pay addPredepositRechargeToPay(Double amount,Integer memberId);
}
