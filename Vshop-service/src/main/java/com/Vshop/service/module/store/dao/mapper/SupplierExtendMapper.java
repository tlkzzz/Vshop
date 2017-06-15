package com.Vshop.service.module.store.dao.mapper;

import com.Vshop.core.entity.base.SupplierExtend;
import com.Vshop.core.orm.mybatis.SqlMapper;

/**
 * @author llf
 * @Package com.Vshop.service.module.supplier.dao.mapper
 * @Description:
 * @date 2014/12/11 16:39
 */
@SqlMapper
public interface SupplierExtendMapper {
	
	/**
	 * 保存店铺物流表
	 * @param SupplierExtend
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
