package com.Vshop.service.module.store.dao;


import java.util.List;

import com.Vshop.core.entity.base.TraceLog;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 */
public interface TracelogDao {
	public int countTraceLog(Pager pager) ;
	public List<TraceLog> queryTraceLogList(Pager pager);
	public void delete(Integer id) ;
	public TraceLog findLogById(Integer id);
    public void updateStateById(Integer id, Integer state);
}
