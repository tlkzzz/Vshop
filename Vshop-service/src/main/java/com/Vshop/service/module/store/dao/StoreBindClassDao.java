package com.Vshop.service.module.store.dao;

import java.util.List;

import com.Vshop.core.entity.base.StoreBindClass;

/**
 * 
 * 
 * @项目名称：Vshop-seller
 * @类名称：StoreBindClassDao
 * @类描述：
 * @创建人：shining
 * @创建时间：2014年12月3日 上午11:57:44
 * @修改人：shining
 * @修改时间：2014年12月3日 上午11:57:44
 * @修改备注：
 * @version
 * 
 */
public interface StoreBindClassDao {
	public List<StoreBindClass> queryByStoreId(Long id);
}
