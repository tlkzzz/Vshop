package com.Vshop.service.module.store.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.base.TraceLog;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;
/**
 * 店铺动态
 *    
 * 项目名称：Vshop-admin   
 * 类名称：TraceLogMapper   
 * 类描述：   
 * 创建人：yanghui   
 * 创建时间：2014年11月7日 下午2:04:36   
 * 修改人：yanghui   
 * 修改时间：2014年11月7日 下午2:04:36   
 * 修改备注：   
 * @version    
 *
 */
@SqlMapper
public interface TracelogMapper{
	 
	/**
	 * 
	 * @Title: countTraceLog 
	 * @Description: TODO(count总数查询) 
	 * @param @param pager
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
    public int countTraceLog(Pager pager);
    
   /**
    * 
    * @Title: queryTraceLogList 
    * @Description: TODO(带分页list查询) 
    * @param @param pager
    * @param @return    设定文件 
    * @return List<TraceLog>    返回类型 
    * @throws
    */
    public List<TraceLog> queryTraceLogList(Pager pager);
    
    /**
     * 
     * @Title: delete 
     * @Description: TODO(根据ID 删除) 
     * @param @param id    设定文件 
     * @return void    返回类型 
     * @throws
     */
    public void delete(@Param("id") Integer id);
    
    /**
     * 
     * @Title: findLogById 
     * @Description: TODO(根据ID 查询明细) 
     * @param @param id
     * @param @return    设定文件 
     * @return AdminLog    返回类型 
     * @throws
     */
    public TraceLog findLogById(@Param("id") Integer id);
    
    public void updateStateById(@Param("id") Integer id, @Param("state") Integer state);
}
