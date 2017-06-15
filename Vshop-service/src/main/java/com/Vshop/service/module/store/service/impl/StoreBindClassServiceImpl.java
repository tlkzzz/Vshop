package com.Vshop.service.module.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.StoreBindClass;
import com.Vshop.service.module.store.dao.StoreBindClassDao;
import com.Vshop.service.module.store.service.StoreBindClassService;
/**
 * 
 *    
 * @项目名称：Vshop-seller   
 * @类名称：StoreBindClassServiceImpl   
 * @类描述：   
 * @创建人：shining   
 * @创建时间：2014年12月3日 上午11:56:42   
 * @修改人：shining   
 * @修改时间：2014年12月3日 上午11:56:42   
 * @修改备注：   
 * @version    
 *
 */
@Service
public class StoreBindClassServiceImpl implements StoreBindClassService {
	@Autowired
	private StoreBindClassDao storeBindClassDao;

	@Override
	public List<StoreBindClass> queryByStoreId(Long id) {
		return storeBindClassDao.queryByStoreId(id);
	}

	@Override
	public List<StoreBindClass> queryBindClassList(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRate(StoreBindClass storeBindClass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBind(int bid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String saveBind(StoreBindClass storeBindClass, String goodsClass) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
