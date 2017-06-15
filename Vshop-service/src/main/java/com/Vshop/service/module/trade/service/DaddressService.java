package com.Vshop.service.module.trade.service;

import java.util.List;

import com.Vshop.core.entity.base.Daddress;

/**
 * 发货地址 
 * @author Administrator
 */
public interface DaddressService {
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
	 * 设置默认发货地址
	 * @param storeId 店铺id
	 * @param addressId 发货地址id
	 */
	void updateDefault(Integer storeId,Integer addressId);
	
	/**
	 * 根据店铺id查询店铺下的默认发货地址数量
	 * @param storeId
	 * @return
	 */
	int findDefaultCountByStoreId(Integer storeId);
	
	List<Daddress> findDaddressBySupplierId(Integer supplierId);
	
	
	public int findDefaultCountBySupplierId(Integer supplierId);
	
	public void updateDefaultSupplier(Integer supplierId,Integer addressId);

	/**
	 * 根据ID获取默认收获地址
	 * @param supplierId
	 * @return
	 */
	Daddress findDefaultDaddressById(Integer supplierId);
}
