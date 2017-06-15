package com.Vshop.service.module.store.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Store;
import com.Vshop.core.entity.vo.StoreVo;
import com.Vshop.service.module.store.dao.StoreDao;
import com.Vshop.service.module.store.dao.mapper.StoreMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * 
 * @项目名称：Vshop-seller
 * @类名称：StoreDaoImpl
 * @类描述：
 * @创建人：shining
 * @创建时间：2014年12月2日 上午11:36:39
 * @修改人：shining
 * @修改时间：2014年12月2日 上午11:36:39
 * @修改备注：
 * @version
 * 
 */
@Repository
public class StoreDaoImpl implements StoreDao {
	@Resource
	private StoreMapper storeMapper;

	public Store findById(Integer id) {
		return storeMapper.findById(id);
	}
	
	public StoreVo findVoById(Integer id) {
		return storeMapper.findVoById(id);
	}
	
	public void updateStore(Store store){
		storeMapper.updateStore(store);
	}

    public void updateStoreCus(Store store) {
        storeMapper.updateStoreCus(store);
    }

	public Store findByMemberId(Integer id){
		return storeMapper.findByMemberId(id);
	}

	public void save(Store store) {
		storeMapper.save(store);
	}

	public Store findByStorename(String storename) {
		return storeMapper.findByStorename(storename);
	}

	public int queryCount(Store store) {
		return storeMapper.queryCount(store);
	}

	public List<Store> queryList(Pager pager) {
		return storeMapper.queryList(pager);
	}

	@Override
	public void updateStoreCount(Map<String,String> map) {
		storeMapper.updateStoreCount(map);
	}

	@Override
	public Store findByIds(Store store) {
		return storeMapper.findByIds(store);
	}
    
	 /**
	  * 
	  * @Title: delete 
	  * @Description: TODO(根据ID 删除) 
	  * @param @param id    设定文件 
	  * @return void    返回类型 
	  */
	@Override
	public void delete(Integer id) {
		storeMapper.delete(id);
	}
	
	@Override
	public List<Store> findList() {
		return storeMapper.findList();
	}

	@Override
	public int countStore(Map<String, ? extends Object> params) {
		return storeMapper.countStore(params);
	}
	
}
