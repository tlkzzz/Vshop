package com.Vshop.service.module.store.dao;

import com.Vshop.core.entity.base.SupplierExtend;

/**
 * @author llf
 * @Package com.Vshop.service.module.supplier.dao
 * @Description:
 * @date 2014/12/11 17:01
 */
public interface SupplierExtendDao {

	/**
	 * 保存店铺物流表
	 * @param supplierExtend
	 */
	void saveSupplierExtend(SupplierExtend supplierExtend);
	
	/**
	 * 修改店铺物流表
	 * @param supplierExtend
	 */
	void updateSupplierExtend(SupplierExtend supplierExtend);
	
	/**
	 * 删除店铺物流表
	 * @param supplierId
	 */
	void deleteSupplierExtend(Integer supplierId);
	
	/**
	 * 通过id查询店铺物流表
	 * @param supplierId
	 * @return
	 */
	SupplierExtend findBySupplierId(Integer supplierId);
}
