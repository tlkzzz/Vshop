package com.Vshop.service.module.store.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.StoreSnsComment;
import com.Vshop.service.module.base.BaseDao;
import com.Vshop.service.module.store.dao.StoreSnsCommentDao;
import com.Vshop.service.module.store.dao.mapper.StoreSnsCommentMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：Vshop-admin   
 * 类名称：StoreSnsCommentDaoImpl   
 * 类描述：   
 * 创建人：yanghui   
 * 创建时间：2014年11月15日 下午7:09:32   
 * 修改人：yanghui   
 * 修改时间：2014年11月15日 下午7:09:32   
 * 修改备注：   
 * @version    
 *
 */
@Service("storeSnsCommentDao")
public class StoreSnsCommentDaoImpl extends BaseDao implements StoreSnsCommentDao {
    @Autowired
    private StoreSnsCommentMapper storeSnsCommentMapper;

    @Override
	public int countComment(Pager pager) {
		return storeSnsCommentMapper.countComment(pager);
	}
	
	@Override
	public List<StoreSnsComment> queryCommentList(Pager pager) {
		return storeSnsCommentMapper.queryCommentList(pager);
	}

	
	@Override
	public void delete(Integer id) {
		storeSnsCommentMapper.delete(id);
	}

	
	@Override
	public StoreSnsComment findLogById(Integer id) {
		return storeSnsCommentMapper.findLogById(id);
	}
   
    @Override
    public void updateStateById(Integer id, Integer state) {
    	storeSnsCommentMapper.updateStateById(id,state);
    }

}
