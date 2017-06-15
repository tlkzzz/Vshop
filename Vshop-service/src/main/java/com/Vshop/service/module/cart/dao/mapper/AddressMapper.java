package com.Vshop.service.module.cart.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.base.Address;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;


/**
 * 
 * @author WY
 *
 */
@SqlMapper
public interface AddressMapper {
	
	List<Address> queryAddreassMemberId(@Param("memberId")Integer memberId);
	
	int saveAddress(Address address);
	
	void deleteAddress(@Param("addressId")Integer addressId);
	
	Address queryById(@Param("addressId")Integer addressId);
	
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
