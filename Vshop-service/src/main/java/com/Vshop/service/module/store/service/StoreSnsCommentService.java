package com.Vshop.service.module.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.base.StoreSnsComment;
import com.Vshop.service.utils.page.Pager;
/**
 * 
 *    
 * 项目名称：Vshop-admin   
 * 类名称：StoreSnsCommentService   
 * 类描述：   
 * 创建人：yanghui   
 * 创建时间：2014年11月15日 下午7:12:20   
 * 修改人：yanghui   
 * 修改时间：2014年11月15日 下午7:12:20   
 * 修改备注：   
 * @version    
 *
 */
public interface StoreSnsCommentService {
	
	/**
	 * 
	 * @Title: countComment
	 * @Description: TODO(count总数查询) 
	 * @param @param pager
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
    public int countComment(Pager pager);
    
    /**
     * 
     * @Title: queryCommentList 
     * @Description: TODO(带分页list 查询) 
     * @param @param pager
     * @param @return    设定文件 
     * @return List<AdminLog>    返回类型 
     * @throws
     */
    public List<StoreSnsComment> queryCommentList(Pager pager);
    
    /**
     * 
     * @Title: delete 
     * @Description: TODO(根据id删除数据) 
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
    public StoreSnsComment findById(@Param("id") Integer id);
    
    /**
     * 
     * @Title: updateById 
     * @Description: TODO(根据ID修改状态) 
     * @param @param id
     * @param @param state    设定文件 
     * @return void    返回类型 
     * @throws
     */
    public void updateStateById(@Param("id") Integer id, @Param("state") Integer state);
    
}
