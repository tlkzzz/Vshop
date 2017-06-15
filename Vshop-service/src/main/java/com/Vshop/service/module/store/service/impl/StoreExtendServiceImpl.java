package com.Vshop.service.module.store.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.Express;
import com.Vshop.core.entity.base.StoreExtend;
import com.Vshop.service.module.setting.service.ExpressService;
import com.Vshop.service.module.store.dao.StoreExtendDao;
import com.Vshop.service.module.store.service.StoreExtendService;

@Service
public class StoreExtendServiceImpl implements StoreExtendService{
	
	@Resource
	private StoreExtendDao storeExtendDao;
	
	@Resource
	private ExpressService expressService;
	
	/**
	 * 保存店铺物流表
	 * @param storeExtend
	 */
	public void saveStoreExtend(StoreExtend storeExtend){
		storeExtendDao.saveStoreExtend(storeExtend);
	}
	
	/**
	 * 修改店铺物流表
	 * @param storeExtend
	 */
	public void updateStoreExtend(StoreExtend storeExtend){
		storeExtendDao.updateStoreExtend(storeExtend);
	}
	
	/**
	 * 删除店铺物流表
	 * @param storeId
	 */
	public void deleteStoreExtend(Integer storeId){
		storeExtendDao.deleteStoreExtend(storeId);
	}
	
	/**
	 * 通过id查询店铺物流表
	 * @param storeId
	 * @return
	 */
	public StoreExtend findByStoreId(Integer storeId){
		return storeExtendDao.findByStoreId(storeId);
	}
	
	/**
	 * 查询店铺选择的物流
	 * @return
	 */
	public List<Express> findExpressByExpressIds(Integer storeId){
		StoreExtend storeExtend = this.findByStoreId(storeId);
		List<Express> list = new ArrayList<Express>();
		if(storeExtend!=null&&StringUtils.isNotBlank(storeExtend.getExpress())){
			String[] strs = storeExtend.getExpress().split(",");
			for(String expressId:strs){
				Express express = expressService.findById(Integer.valueOf(expressId));
				if(express!=null && express.getEstate()==1){ //过滤可以显示的物流公司
					list.add(express);
				}
				
			}
		}
		return list;
	}
}
