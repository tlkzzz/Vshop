package com.Vshop.service.module.store.dao;


import java.util.List;

import com.Vshop.core.entity.base.StoreSnsComment;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 */
public interface StoreSnsCommentDao {
	public int countComment(Pager pager) ;
	public List<StoreSnsComment> queryCommentList(Pager pager);
	public void delete(Integer id) ;
	public StoreSnsComment findLogById(Integer id);
    public void updateStateById(Integer id, Integer state);
}
