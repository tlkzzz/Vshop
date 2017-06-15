package com.Vshop.service.module.goods.service;

import java.util.List;
import java.util.Map;

import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.base.Supplier;
import com.Vshop.service.utils.page.Pager;

public interface SupplierService {
	void save(Supplier supplier);
	
	void delete(int id);
	
	void update(Supplier supplier);
	
	Supplier findById(long id);

	List<Supplier> findPageList(Pager pager);
	
	List<Supplier> findList();
	
	int countSupplier(Supplier supplier);
	
	void saveSupplierBrand(List<Map<String, Integer>> params);
	
	List<Brand> findBrandListBySupplier(Map<String, Integer> params);
	
	List<Supplier> findSupplierListByBrand(Brand brand);
	
	void deleteSupplierBrandBySupplierId(Map<String, Integer> params);
	
	public Supplier findByMemberId(Integer id);
	
	public void updateSupplier(Integer id);
}
