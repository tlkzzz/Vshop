package com.Vshop.service.module.log.dao;

import java.util.List;

import com.Vshop.core.entity.base.SysLog;
import com.Vshop.service.utils.page.Pager;

/**
 * 日志DAO
 * 项目名称：Vshop-admin   
 * 类名称：OrderStatisDao   
 * 创建人：linjm   
 * 创建时间：2014年11月14日 上午12:10:28   
 * 修改备注：   
 * @version    
 */
public interface SysLogDao {

	/**
	 * @Title: countSysLog 
	 * @Description: TODO(count总数查询) 
	 * @param @param pager
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
    public int countSysLog(SysLog sysLog);
    
    /**
     * @Title: querySysLogList 
     * @Description: TODO(带分页list 查询) 
     * @param @param pager
     * @param @return    设定文件 
     * @return List<SysLog>    返回类型 
     * @throws
     */
    public List<SysLog> querySysLogList(Pager pager);
    
    /**
     * @Title: delete 
     * @Description: TODO(根据id删除数据) 
     * @param @param id    设定文件 
     * @return void    返回类型 
     * @throws
     */
    public void delete(Long id);
    
    /**
     * @Title: findLogById 
     * @Description: TODO(根据ID 查询明细) 
     * @param @param id
     * @param @return    设定文件 
     * @return SysLog    返回类型 
     * @throws
     */
    public SysLog findSysLogById(Long id);

    /**
     * 保存日志
     * @param SysLog
     */
    public void save(SysLog sysLog);
}
