package com.Vshop.service.module.store.service;

import java.util.List;

import com.Vshop.core.entity.base.StoreBindClass;

/**
 * 
 * 
 * @项目名称：Vshop-seller
 * @类名称：StoreBindClassService
 * @类描述：
 * @创建人：shining
 * @创建时间：2014年12月3日 上午11:57:05
 * @修改人：shining
 * @修改时间：2014年12月3日 上午11:57:05
 * @修改备注：
 * @version
 * 
 */
public interface StoreBindClassService {
	public List<StoreBindClass> queryByStoreId(Long id);
	
	/**
     * 查询列表
     * @return
     */
    List<StoreBindClass> queryBindClassList(int id);

    /**
     * 修改比例
     * @param storeBindClass
     */
    void updateRate(StoreBindClass storeBindClass);

    /**
     * 删除
     * @param bid
     */
    void deleteBind(int bid);

    /**
     * 保存
     */
    String saveBind(StoreBindClass storeBindClass, String goodsClass);
}
