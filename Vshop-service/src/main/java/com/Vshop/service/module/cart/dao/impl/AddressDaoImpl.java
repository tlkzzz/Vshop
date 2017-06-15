package com.Vshop.service.module.cart.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Address;
import com.Vshop.service.module.base.BaseDao;
import com.Vshop.service.module.cart.dao.AddressDao;
import com.Vshop.service.module.cart.dao.mapper.AddressMapper;
import com.Vshop.service.utils.page.Pager;


/**
 * 
 *    
 * 项目名称：Vshop-front   
 * 类名称：AddressDaoImpl   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年12月27日 下午5:06:54   
 * 修改人：liuhao   
 * 修改时间：2014年12月27日 下午5:06:54   
 * 修改备注：   
 * @version    
 *
 */
@Repository
public class AddressDaoImpl extends BaseDao implements AddressDao {
    @Resource
    private AddressMapper addressMapper;

	@Override
	public List<Address> queryAddreassMemberId(Integer memberId) {
		// TODO Auto-generated method stub
		return addressMapper.queryAddreassMemberId(memberId);
	}

	@Override
	public int saveAddress(Address address) {
		// TODO Auto-generated method stub
		return addressMapper.saveAddress(address);
	}

	@Override
	public void deleteAddress(String addressId) {
		// TODO Auto-generated method stub
		addressMapper.deleteAddress(Integer.valueOf(addressId));
	}

	@Override
	public Address queryById(Integer addressId) {
		// TODO Auto-generated method stub
		return addressMapper.queryById(addressId);
	}

	@Override
	public void updateAddress(Address address) {
		// TODO Auto-generated method stub
		addressMapper.updateAddress(address);
	}

	@Override
	public int countfindAll(Address address) {
		return addressMapper.countfindAll(address);
	}

	@Override
	public List<Address> findList(Pager pager) {
		return addressMapper.findList(pager);
	}


}
