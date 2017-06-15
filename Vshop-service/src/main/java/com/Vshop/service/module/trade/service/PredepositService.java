package com.Vshop.service.module.trade.service;

import java.math.BigDecimal;

import com.Vshop.core.entity.base.PredepositRecharge;

/**
 * 预付款相关
 * @author liukai
 */
public interface PredepositService {
	
	/**
	 * 预付款充值
	 * @param memberId 用户id
	 * @param pdrAmount 充值金额
	 */
	PredepositRecharge addRechargePredeposit(Integer memberId,BigDecimal pdrAmount);

	/**
	 * 充值成功
	 * @param pdrSn 充值表编号
	 * @param memberId 用户id
	 */
	void updateFinishRecharge(String pdrSn);
	
	/**
	 * 修改预付款充值表支付信息
	 * @param pdrSn
	 * @param paymentId
	 */
	void updateRechargePredeposit(String pdrSn,Integer paymentId);
}
