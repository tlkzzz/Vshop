package com.Vshop.service.module.trade.service;

import java.util.List;

import com.Vshop.core.entity.base.PredepositLog;

/**
 * 预存款变更日志表
 * @author liukai
 */
public interface PredepositLogService {
	/**
	 * 保存预存款变更日志表
	 * @param predepositLog
	 */
	void savePdl(PredepositLog predepositLog);
	
	/**
	 * 修改预存款变更日志表
	 * @param predepositLog
	 */
	void updatePdl(PredepositLog predepositLog);
	
	/**
	 * 通过id删除预存款变更日志表
	 * @param lgId
	 */
	void deletePdl(Integer lgId);
	
	/**
	 * 通过id查找预存款变更日志表
	 * @param lgId
	 * @return
	 */
	PredepositLog findPdlById(Integer lgId);
	
	/**
	 * 根据用户id查询预存款变更日志表
	 * @param lgMemberId
	 * @return
	 */
	List<PredepositLog> findByMemberId(Integer lgMemberId);
}
