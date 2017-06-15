package com.Vshop.service.module.trade.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.PredepositLog;
import com.Vshop.service.module.trade.dao.PredepositLogDao;
import com.Vshop.service.module.trade.service.PredepositLogService;

/**
 * 预存款变更日志表
 * @author liukai
 */
@Service
public class PredepositLogServiceImpl implements PredepositLogService{
	
	@Resource
	private PredepositLogDao predepositLogDao;
	
	/**
	 * 保存预存款变更日志表
	 * @param predepositLog
	 */
	public void savePdl(PredepositLog predepositLog){
		predepositLogDao.savePdl(predepositLog);
	}
	
	/**
	 * 修改预存款变更日志表
	 * @param predepositLog
	 */
	public void updatePdl(PredepositLog predepositLog){
		predepositLogDao.updatePdl(predepositLog);
	}
	
	/**
	 * 通过id删除预存款变更日志表
	 * @param lgId
	 */
	public void deletePdl(Integer lgId){
		predepositLogDao.deletePdl(lgId);
	}
	
	/**
	 * 通过id查找预存款变更日志表
	 * @param lgId
	 * @return
	 */
	public PredepositLog findPdlById(Integer lgId){
		return predepositLogDao.findPdlById(lgId);
	}
	
	/**
	 * 根据用户id查询预存款变更日志表
	 * @param lgMemberId
	 * @return
	 */
	public List<PredepositLog> findByMemberId(Integer lgMemberId){
		return predepositLogDao.findByMemberId(lgMemberId);
	}
}
