package com.Vshop.service.module.store.service.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.TraceLog;
import com.Vshop.service.module.store.dao.TracelogDao;
import com.Vshop.service.module.store.service.TraceLogService;
import com.Vshop.service.utils.page.Pager;
/**
 * 
 *    
 * 项目名称：Vshop-admin   
 * 类名称：TraceLogServiceImpl   
 * 类描述：   
 * 创建人：yanghui   
 * 创建时间：2014年11月7日 下午2:04:30   
 * 修改人：yanghui   
 * 修改时间：2014年11月7日 下午2:04:30   
 * 修改备注：   
 * @version    
 *
 */
@Service("traceLogService")
@Slf4j
public class TraceLogServiceImpl implements TraceLogService {

	@Autowired
    private TracelogDao tracelogDao;

	/**
	 * 
	 * @Title: countTraceLog 
	 * @Description: TODO(count总数查询) 
	 * @param @param pager
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@Override
	public int countTraceLog(Pager pager) {
		log.info("获取log列表记录数");
		return tracelogDao.countTraceLog(pager);
	}

	/**
     * 
     * @Title: queryTraceLogList 
     * @Description: TODO(带分页list 查询) 
     * @param @param pager
     * @param @return    设定文件 
     * @return List<AdminLog>    返回类型 
     * @throws
     */
	@Override
	public List<TraceLog> queryTraceLogList(Pager pager) {
		log.info("获取log列表List");
		return tracelogDao.queryTraceLogList(pager);
	}

	 /**
     * 
     * @Title: delete 
     * @Description: TODO(根据id删除数据) 
     * @param @param id    设定文件 
     * @return void    返回类型 
     * @throws
     */
	@Override
	public void delete(Integer id) {
		tracelogDao.delete(id);
	}

	 /**
     * 
     * @Title: findLogById 
     * @Description: TODO(根据ID 查询明细) 
     * @param @param id
     * @param @return    设定文件 
     * @return AdminLog    返回类型 
     * @throws
     */
	@Override
	public TraceLog findLogById(Integer id) {
		return tracelogDao.findLogById(id);
	}
	
	
	/**
     * 
     * @Title: updateById 
     * @Description: TODO(根据ID修改状态) 
     * @param @param id
     * @param @param state    设定文件 
     * @return void    返回类型 
     * @throws
     */
	@Override
    public void updateStateById(Integer id,Integer state){
		tracelogDao.updateStateById(id,state);
	}
}

