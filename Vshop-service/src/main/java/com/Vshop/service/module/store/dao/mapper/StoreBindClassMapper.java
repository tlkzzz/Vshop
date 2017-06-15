package com.Vshop.service.module.store.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.base.StoreBindClass;
import com.Vshop.core.orm.mybatis.SqlMapper;

/**
 * 
 * 
 * @项目名称：Vshop-seller
 * @类名称：StoreBindClassMapper
 * @类描述：
 * @创建人：shining
 * @创建时间：2014年12月3日 上午11:58:05
 * @修改人：shining
 * @修改时间：2014年12月3日 上午11:58:05
 * @修改备注：
 * @version
 * 
 */
@SqlMapper
public interface StoreBindClassMapper {
	public List<StoreBindClass> queryByStoreId(@Param("id") Long id);

	public StoreBindClass findById(@Param("id") Long id);
}
