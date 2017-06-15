package com.Vshop.service.module.goods.dao.mapper;

import java.util.List;
import java.util.Map;

import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.base.Supplier;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;

@SqlMapper
public interface SupplierMapper {

	void save(Supplier supplier);
	
	void delete(int id);
	
	void update(Supplier supplier);
	
	List<Supplier> findPageList(Pager pager);

	List<Supplier> findList();

	Supplier findById(long id);
	
	int countSupplier(Supplier supplier);
	
	void saveSupplierBrand(List<Map<String, Integer>> list);
	
	List<Brand> findBrandListBySupplier(Map<String, Integer> params);
	
	List<Supplier> findSupplierListByBrand(Brand brand);
	
	void deleteSupplierBrandBySupplierId(Map<String, Integer> params);
	
	public Supplier findByMemberId(Integer id);
}
