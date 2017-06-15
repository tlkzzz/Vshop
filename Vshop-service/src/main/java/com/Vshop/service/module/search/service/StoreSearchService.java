package com.Vshop.service.module.search.service;

import com.Vshop.core.entity.base.Store;
import com.Vshop.service.utils.lucene.LucenePager;

/**
 * 店铺搜索
 * @author cgl
 */
public interface StoreSearchService {

	/**
	 * 批量建立店铺索引
	 * @param storeConditions 自定义条件
	 * @return 返回1 建立成功 返回0 则发生错误
	 */
	Integer createStoreIndex(Store storeConditions);
	
	/**
	 * 建立指定店铺索引
	 * @param storeId 店铺id
	 * @return 返回1 建立成功 返回0 则发生错误
	 */
	Integer addStoreIndex(Integer storeId);
	
	/**
	 * 删除指定店铺索引
	 * @param id 店铺id
	 * @return  返回1 删除成功 返回0 则发生错误
	 */
	void deleteStoreIndex(Integer id);
	
	/**
	 * 搜索店铺
	 * @param lucenePager lucenePager实体类
	 * @return LucenePager lucenePager实体类 该方法将会result属性,注入查询结果
	 */
	LucenePager searchStore(LucenePager lucenePager);
}
