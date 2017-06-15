package com.Vshop.service.module.store.service;

import java.util.List;

import com.Vshop.core.entity.base.Express;
import com.Vshop.core.entity.base.SupplierExtend;

public interface SupplierExtendService {
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
	
	/**
	 * 查询店铺选择的物流
	 * @return
	 */
	List<Express> findExpressByExpressIds(Integer supplierId);
}
