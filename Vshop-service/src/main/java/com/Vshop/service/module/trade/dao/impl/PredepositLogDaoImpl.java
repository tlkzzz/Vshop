package com.Vshop.service.module.trade.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.PredepositLog;
import com.Vshop.service.module.trade.dao.PredepositLogDao;
import com.Vshop.service.module.trade.dao.mapper.PredepositLogMapper;

/**
 * 预存款变更日志表
 * @author liukai
 */
@Repository
public class PredepositLogDaoImpl implements PredepositLogDao{
	
	@Resource
	private PredepositLogMapper predepositLogMapper;
	/**
	 * 保存预存款变更日志表
	 * @param predepositLog
	 */
	public void savePdl(PredepositLog predepositLog){
		predepositLogMapper.savePdl(predepositLog);
	}
	
	/**
	 * 修改预存款变更日志表
	 * @param predepositLog
	 */
	public void updatePdl(PredepositLog predepositLog){
		predepositLogMapper.updatePdl(predepositLog);
	}
	
	/**
	 * 通过id删除预存款变更日志表
	 * @param lgId
	 */
	public void deletePdl(Integer lgId){
		predepositLogMapper.deletePdl(lgId);
	}
	
	/**
	 * 通过id查找预存款变更日志表
	 * @param lgId
	 * @return
	 */
	public PredepositLog findPdlById(Integer lgId){
		return predepositLogMapper.findPdlById(lgId);
	}
	
	/**
	 * 根据用户id查询预存款变更日志表
	 * @param lgMemberId
	 * @return
	 */
	public List<PredepositLog> findByMemberId(Integer lgMemberId){
		return predepositLogMapper.findByMemberId(lgMemberId);
	}
}
