package com.Vshop.service.module.trade.dao;

import java.util.List;

import com.Vshop.core.entity.base.PredepositRecharge;

/**
 * 预存款充值表
 * @author liukai
 */
public interface PredepositRechargeDao {
	
	/**
	 * 保存预存款充值表
	 * @param predepositRecharge
	 */
	void savePdr(PredepositRecharge predepositRecharge);
	
	/**
	 * 根据id删除预存款充值表
	 * @param pdrId
	 */
	void deletePdr(Integer pdrId);
	
	/**
	 * 修改预存款充值表
	 * @param predepositRecharge
	 */
	void updatePdr(PredepositRecharge predepositRecharge);
	
	/**
	 * 根据id查询预存款充值表
	 * @param pdrId
	 * @return
	 */
	PredepositRecharge findById(Integer pdrId);
	
	/**
	 * 根据用户id查询预存款充值表
	 * @param pdrMemberId
	 * @return
	 */
	List<PredepositRecharge> findByMemberId(Integer pdrMemberId);
	
	/**
	 * 根据充值表编号查询预存款充值表信息
	 * @param pdrSn
	 * @return
	 */
	PredepositRecharge findByPdrSn(String pdrSn);
}
