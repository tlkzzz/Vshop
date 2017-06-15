package com.Vshop.service.module.trade.dao;

import java.util.List;

import com.Vshop.core.entity.base.Daddress;

/**
 * Created by rabook on 2014/12/20.
 */
public interface DaddressDao {

	/**
	 * 保存发货地址
	 * @param daddress
	 */
	void saveDaddress(Daddress daddress);
	
	/**
	 * 修改发货地址,条件可传两个值,发货地址id和店铺id
	 * @param daddress
	 */
	void updateDaddress(Daddress daddress);
	
	/**
	 * 根据id删除发货地址
	 * @param addressId
	 */
	void deleteDaddress(Integer addressId);
	
	/**
	 * 根据id查询发货地址
	 * @param addressId
	 * @return
	 */
	Daddress findDaddressById(Integer addressId);
	
	/**
	 * 根据店铺id查询发货地址列表
	 * @param storeId
	 * @return
	 */
	List<Daddress> findDaddressByStoreId(Integer storeId);
	
	/**
	 * 根据店铺id查询店铺下的默认发货地址数量
	 * @param storeId
	 * @return
	 */
	int findDefaultCountByStoreId(Integer storeId);
	
	
	/**
	 * 根据店铺id查询发货地址列表
	 * @param storeId
	 * @return
	 */
	List<Daddress> findDaddressBySupplierId(Integer supplierId);
	
	
	public int findDefaultCountBySupplierId(Integer supplierId);
	
	/**
	 * 修改发货地址,条件可传两个值,发货地址id和店铺id
	 * @param daddress
	 */
	void updateDaddressSupplier(Daddress daddress);

	/**根据店铺ID查询默认发货地址
	 * @param supplierId
	 * @return
	 */
	Daddress findDefaultDaddressById(Integer supplierId);
}
