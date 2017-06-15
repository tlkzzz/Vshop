package com.Vshop.service.module.goods.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.base.Supplier;
import com.Vshop.service.module.goods.dao.SupplierDao;
import com.Vshop.service.module.goods.dao.mapper.SupplierMapper;
import com.Vshop.service.utils.page.Pager;

@Repository
public class SupplierDaoImpl implements SupplierDao {
	@Resource
	private SupplierMapper supplierMapper;
	
	@Override
	public List<Supplier> findPageList(Pager pager) {
		return supplierMapper.findPageList(pager);
	}

	@Override
	public void save(Supplier supplier) {
		supplierMapper.save(supplier);
	}

	@Override
	public void delete(int id) {
		supplierMapper.delete(id);
	}

	@Override
	public void update(Supplier supplier) {
		supplierMapper.update(supplier);
	}

	@Override
	public List<Supplier> findList() {
		return supplierMapper.findList();
	}

	@Override
	public Supplier findById(long id) {
		return supplierMapper.findById(id);
	}

	@Override
	public void saveSupplierBrand(List<Map<String, Integer>> list) {
		supplierMapper.saveSupplierBrand(list);
	}

	@Override
	public List<Brand> findBrandListBySupplier(Map<String, Integer> params) {
		return supplierMapper.findBrandListBySupplier(params);
	}

	@Override
	public int countSupplier(Supplier supplier) {
		return supplierMapper.countSupplier(supplier);
	}

	@Override
	public List<Supplier> findSupplierListByBrand(Brand brand) {
		return supplierMapper.findSupplierListByBrand(brand);
	}

	@Override
	public void deleteSupplierBrandBySupplierId(Map<String, Integer> params) {
		supplierMapper.deleteSupplierBrandBySupplierId(params);
	}
	
	public Supplier findByMemberId(Integer id){
		return supplierMapper.findByMemberId(id);
	}

}
