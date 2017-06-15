package com.Vshop.service.module.cart.service;


import java.util.List;
import java.util.Map;

import com.Vshop.core.entity.base.Address;
import com.Vshop.service.utils.page.Pager;


/**
 * 
 */
public interface AddressService {
	
	List<Address> queryAddreassMemberId(Integer memberId);
	
	Map<String,Object> saveAddress(String jsondata,Integer memberId);
	
	void deleteAddress(String addressId);
	
	Address queryById(Integer addressId);
	
	public int updateAddress(String jsondata) ;
	
	int updateDef(String addressId,String memberId);

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
	
	/**
	 * 保存收货地址
	 * @param address
	 */
	void saveAddress(Address address);
	
	/**
	 * 修改收货地址
	 * @param address
	 */
	void updateAddress(Address address);
}
