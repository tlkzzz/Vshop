package com.Vshop.service.module.store.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.SupplierExtend;
import com.Vshop.service.module.store.dao.SupplierExtendDao;
import com.Vshop.service.module.store.dao.mapper.SupplierExtendMapper;

/**
 * @author llf
 * @Package com.Vshop.service.module.supplier.dao.impl
 * @Description:
 * @date 2014/12/11 17:02
 */
@Repository
public class SupplierExtendDaoImpl implements SupplierExtendDao {

    @Resource
    private SupplierExtendMapper supplierExtendMapper;
    
    /**
	 * 保存店铺物流表
	 * @param supplierExtend
	 */
	public void saveSupplierExtend(SupplierExtend supplierExtend){
		supplierExtendMapper.saveSupplierExtend(supplierExtend);
	}
	
	/**
	 * 修改店铺物流表
	 * @param supplierExtend
	 */
	public void updateSupplierExtend(SupplierExtend supplierExtend){
		supplierExtendMapper.updateSupplierExtend(supplierExtend);
	}
	
	/**
	 * 删除店铺物流表
	 * @param supplierId
	 */
	public void deleteSupplierExtend(Integer supplierId){
		supplierExtendMapper.deleteSupplierExtend(supplierId);
	}
	
	/**
	 * 通过id查询店铺物流表
	 * @param supplierId
	 * @return
	 */
	public SupplierExtend findBySupplierId(Integer supplierId){
		return supplierExtendMapper.findBySupplierId(supplierId);
	}
}
