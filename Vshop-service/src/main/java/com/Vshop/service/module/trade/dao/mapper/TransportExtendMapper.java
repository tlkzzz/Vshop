package com.Vshop.service.module.trade.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.base.TransportExtend;
import com.Vshop.core.orm.mybatis.SqlMapper;

@SqlMapper
public interface TransportExtendMapper {
	/**
	 * 
	 * @Title: queryByTransportId
	 * @Description: 根据 TransportId查询列表
	 * @param @param id
	 * @param @return 设定文件
	 * @return List<TransportExtend> 返回类型
	 * @throws
	 */
	public List<TransportExtend> queryByTransportId(@Param("id") Integer id);

	public void save(TransportExtend transportExtend);
	
	public void delete(Integer transportId);
	
	/**
	 * 根据transportExtend作为查询条件,查询transportExtend
	 * @param transportExtend
	 * @return
	 */
	public TransportExtend selectTransportExtendByCondition(TransportExtend transportExtend);
}
