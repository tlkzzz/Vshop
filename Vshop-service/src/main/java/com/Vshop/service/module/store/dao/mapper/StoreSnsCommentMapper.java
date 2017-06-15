package com.Vshop.service.module.store.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.base.StoreSnsComment;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;

@SqlMapper
public interface StoreSnsCommentMapper {
	public int countComment(Pager pager);
	
	public List<StoreSnsComment> queryCommentList(Pager pager);
	
	public StoreSnsComment findLogById(@Param("id") Integer id);

    public void delete(@Param("id") Integer id);

    int updateStateById(@Param("id") Integer id, @Param("state") Integer state);
}