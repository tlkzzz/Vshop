package com.Vshop.service.module.cart.dao;


import java.util.List;

import com.Vshop.core.entity.base.Address;
import com.Vshop.service.utils.page.Pager;


/**
 * 
 */
public interface AddressDao {

	
	List<Address> queryAddreassMemberId(Integer memberId);
	
	int saveAddress(Address address);
	
	void deleteAddress(String addressId);
	
	Address queryById(Integer addressId);
	
	void updateAddress(Address address);

	/**
	 * 获取总条数
	 * @param pager
	 * @return
	 */
	public int countfindAll(Address address);

	/**
	 * 获取分页集合
	 * @param pager
	 * @return
	 */
	public List<Address> findList(Pager pager);
	
}
