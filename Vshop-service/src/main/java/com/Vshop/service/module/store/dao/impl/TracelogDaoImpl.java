package com.Vshop.service.module.store.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.TraceLog;
import com.Vshop.service.module.base.BaseDao;
import com.Vshop.service.module.store.dao.TracelogDao;
import com.Vshop.service.module.store.dao.mapper.TracelogMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：Vshop-admin   
 * 类名称：TracelogDaoImpl   
 * 类描述：   
 * 创建人：yanghui   
 * 创建时间：2014年11月13日 下午4:13:20   
 * 修改人：yanghui   
 * 修改时间：2014年11月13日 下午4:13:20   
 * 修改备注：   
 * @version    
 *
 */
@Service("tracelogDao")
public class TracelogDaoImpl extends BaseDao implements TracelogDao {
    @Autowired
    private TracelogMapper tracelogMapper;

    @Override
	public int countTraceLog(Pager pager) {
		return tracelogMapper.countTraceLog(pager);
	}
	
	@Override
	public List<TraceLog> queryTraceLogList(Pager pager) {
		return tracelogMapper.queryTraceLogList(pager);
	}

	
	@Override
	public void delete(Integer id) {
		tracelogMapper.delete(id);
	}

	
	@Override
	public TraceLog findLogById(Integer id) {
		return tracelogMapper.findLogById(id);
	}
   
    @Override
    public void updateStateById(Integer id, Integer state) {
    	tracelogMapper.updateStateById(id,state);
    }

}
