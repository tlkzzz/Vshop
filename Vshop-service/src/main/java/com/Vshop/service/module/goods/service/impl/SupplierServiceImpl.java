package com.Vshop.service.module.goods.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.base.Supplier;
import com.Vshop.service.module.goods.dao.SupplierDao;
import com.Vshop.service.module.goods.service.SupplierService;
import com.Vshop.service.utils.page.Pager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SupplierServiceImpl implements SupplierService {
	@Resource
	private SupplierDao supplierDao;
	
	@Override
	public List<Supplier> findPageList(Pager pager) {
		return supplierDao.findPageList(pager);
	}

	@Override
	public void save(Supplier supplier) {
		supplierDao.save(supplier);
		
	}

	@Override
	public void delete(int id) {
		supplierDao.delete(id);
		
	}

	@Override
	public void update(Supplier supplier) {
		supplierDao.update(supplier);
		
	}

	@Override
	public Supplier findById(long id) {
		return supplierDao.findById(id);
	}

	@Override
	public List<Supplier> findList() {
		return supplierDao.findList();
	}

	@Override
	public void saveSupplierBrand(List<Map<String, Integer>> list) {
		supplierDao.saveSupplierBrand(list);
	}

	@Override
	public List<Brand> findBrandListBySupplier(Map<String, Integer> params) {
		return supplierDao.findBrandListBySupplier(params);
	}

	@Override
	public int countSupplier(Supplier supplier) {
		return supplierDao.countSupplier(supplier);
	}

	@Override
	public List<Supplier> findSupplierListByBrand(Brand brand) {
		return supplierDao.findSupplierListByBrand(brand);
	}

	@Override
	public void deleteSupplierBrandBySupplierId(Map<String, Integer> params) {
		log.debug("delete SupplierBrand by supplierId");
		supplierDao.deleteSupplierBrandBySupplierId(params);	
	}
	
	public Supplier findByMemberId(Integer id){
		return supplierDao.findByMemberId(id);
	}
	
	@Override
	public void updateSupplier(Integer id) {
		Supplier supplier=supplierDao.findById(id);//获取店铺信息
		Supplier supplier2=new Supplier();
		if(supplier!=null){
			supplier2.setId(id);
			supplier2.setSupplierLastLogintime(supplier.getSupplierLogintime());
			supplier2.setSupplierLogintime(System.currentTimeMillis());
			supplierDao.update(supplier2);
		}
	}
}
