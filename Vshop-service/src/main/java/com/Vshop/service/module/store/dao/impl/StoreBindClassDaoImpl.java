package com.Vshop.service.module.store.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.StoreBindClass;
import com.Vshop.service.module.store.dao.StoreBindClassDao;
import com.Vshop.service.module.store.dao.mapper.StoreBindClassMapper;
/**
 * 
 *    
 * @项目名称：Vshop-seller   
 * @类名称：StoreBindClassDaoImpl   
 * @类描述：   
 * @创建人：shining   
 * @创建时间：2014年12月3日 上午11:57:21   
 * @修改人：shining   
 * @修改时间：2014年12月3日 上午11:57:21   
 * @修改备注：   
 * @version    
 *
 */
@Repository
public class StoreBindClassDaoImpl implements StoreBindClassDao {
	@Resource
	private StoreBindClassMapper storeBindClassMapper;

	@Override
	public List<StoreBindClass> queryByStoreId(Long id) {
		return storeBindClassMapper.queryByStoreId(id);
		// storeBindClassMapper.findById(new Long(1));
		// return null;
	}
}
