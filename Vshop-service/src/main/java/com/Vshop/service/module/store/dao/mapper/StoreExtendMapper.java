package com.Vshop.service.module.store.dao.mapper;

import com.Vshop.core.entity.base.StoreExtend;
import com.Vshop.core.orm.mybatis.SqlMapper;

/**
 * @author llf
 * @Package com.Vshop.service.module.store.dao.mapper
 * @Description:
 * @date 2014/12/11 16:39
 */
@SqlMapper
public interface StoreExtendMapper {
	
	/**
	 * 保存店铺物流表
	 * @param storeExtend
	 */
	void saveStoreExtend(StoreExtend storeExtend);
	
	/**
	 * 修改店铺物流表
	 * @param storeExtend
	 */
	void updateStoreExtend(StoreExtend storeExtend);
	
	/**
	 * 删除店铺物流表
	 * @param storeId
	 */
	void deleteStoreExtend(Integer storeId);
	
	/**
	 * 通过id查询店铺物流表
	 * @param storeId
	 * @return
	 */
	StoreExtend findByStoreId(Integer storeId);
}
