package com.Vshop.service.module.store.service.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.StoreSnsComment;
import com.Vshop.service.module.store.dao.StoreSnsCommentDao;
import com.Vshop.service.module.store.service.StoreSnsCommentService;
import com.Vshop.service.utils.page.Pager;
/**
 * 
 *    
 * 项目名称：Vshop-admin   
 * 类名称：StoreSnsCommentServiceImpl   
 * 类描述：   
 * 创建人：yanghui   
 * 创建时间：2014年11月15日 下午7:13:16   
 * 修改人：yanghui   
 * 修改时间：2014年11月15日 下午7:13:16   
 * 修改备注：   
 * @version    
 *
 */
@Service("storeSnsCommentService")
@Slf4j
public class StoreSnsCommentServiceImpl implements StoreSnsCommentService {

	@Autowired
    private StoreSnsCommentDao storeSnsCommentDao;

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
	public int countComment(Pager pager) {
		log.info("获取log列表记录数");
		return storeSnsCommentDao.countComment(pager);
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
	public List<StoreSnsComment> queryCommentList(Pager pager) {
		log.info("获取log列表List");
		return storeSnsCommentDao.queryCommentList(pager);
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
		storeSnsCommentDao.delete(id);
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
	public StoreSnsComment findById(Integer id) {
		return storeSnsCommentDao.findLogById(id);
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
		storeSnsCommentDao.updateStateById(id,state);
	}
}

