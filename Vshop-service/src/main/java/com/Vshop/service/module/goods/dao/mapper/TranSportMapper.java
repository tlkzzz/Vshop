package com.Vshop.service.module.goods.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.core.entity.Transport;

/**
 * 
 *    
 * 项目名称：Vshop-seller   
 * 类名称：AreaMapper   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年12月5日 上午12:09:43   
 * 修改人：liuhao   
 * 修改时间：2014年12月5日 上午12:09:43   
 * 修改备注：   
 * @version    
 *
 */
@SqlMapper
public interface TranSportMapper {
	
	public List<Transport> queryStoreList(@Param("storeId") Integer storeId);
}
