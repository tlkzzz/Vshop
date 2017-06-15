package com.Vshop.service.module.log.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.SellerLog;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;

@SqlMapper
public interface SellerLogDaoMapper {
	
	//查询总数
	public int countSellerLog(SellerLog sellerLog);
		
	//查询日志,并且分页
	public List<SellerLog> selectSellerLogByPager(Pager pager);

	//增加日志
	public int saveSellerLog(SellerLog sellerLog);
		
	//删除日志
	public int deleteSellerLog(int logId);
	
	//修改日志
	public int updateSellerLog(SellerLog sellerLog);
		
	//查询所有日志
	public List<SellerLog> selectAllSellerLog();
	
	
}
