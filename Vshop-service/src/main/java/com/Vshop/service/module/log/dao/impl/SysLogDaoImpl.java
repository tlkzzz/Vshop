package com.Vshop.service.module.log.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.SysLog;
import com.Vshop.service.module.log.dao.SysLogDao;
import com.Vshop.service.module.log.dao.mapper.SysLogMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * 项目名称：Vshop-admin   
 * 类名称：OrderStatisDaoImpl   
 * 创建人：linjm   
 * 创建时间：2014年11月14日 上午12:10:28   
 * 修改备注：   
 * @version    
 */

@Repository
public class SysLogDaoImpl implements SysLogDao{

    @Resource
    private SysLogMapper SysLogMapper ;

	@Override
	public int countSysLog(SysLog sysLog) {
		return SysLogMapper.countSysLog(sysLog);
	}

	@Override
	public List<SysLog> querySysLogList(Pager pager) {
		return SysLogMapper.querySysLogList(pager);
	}

	@Override
	public void delete(Long id) {
		SysLogMapper.delete(id);
	}

	@Override
	public SysLog findSysLogById(Long id) {
		return SysLogMapper.findSysLogById(id);
	}

    /**
     * 保存日志
     * @param SysLog
     */
    @Override
    public void save(SysLog sysLog) {
        SysLogMapper.save(sysLog);
    }

}
