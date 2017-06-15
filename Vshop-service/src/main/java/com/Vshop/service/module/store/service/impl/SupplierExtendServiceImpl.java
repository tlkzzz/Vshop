package com.Vshop.service.module.store.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.Express;
import com.Vshop.core.entity.base.SupplierExtend;
import com.Vshop.service.module.setting.service.ExpressService;
import com.Vshop.service.module.store.dao.SupplierExtendDao;
import com.Vshop.service.module.store.service.SupplierExtendService;

@Service
public class SupplierExtendServiceImpl implements SupplierExtendService{
	
	@Resource
	private SupplierExtendDao supplierExtendDao;
	
	@Resource
	private ExpressService expressService;
	
	/**
	 * 保存店铺物流表
	 * @param supplierExtend
	 */
	public void saveSupplierExtend(SupplierExtend supplierExtend){
		supplierExtendDao.saveSupplierExtend(supplierExtend);
	}
	
	/**
	 * 修改店铺物流表
	 * @param supplierExtend
	 */
	public void updateSupplierExtend(SupplierExtend supplierExtend){
		supplierExtendDao.updateSupplierExtend(supplierExtend);
	}
	
	/**
	 * 删除店铺物流表
	 * @param supplierId
	 */
	public void deleteSupplierExtend(Integer supplierId){
		supplierExtendDao.deleteSupplierExtend(supplierId);
	}
	
	/**
	 * 通过id查询店铺物流表
	 * @param supplierId
	 * @return
	 */
	public SupplierExtend findBySupplierId(Integer supplierId){
		return supplierExtendDao.findBySupplierId(supplierId);
	}
	
	/**
	 * 查询店铺选择的物流
	 * @return
	 */
	public List<Express> findExpressByExpressIds(Integer supplierId){
		SupplierExtend supplierExtend = this.findBySupplierId(supplierId);
		List<Express> list = new ArrayList<Express>();
		if(supplierExtend!=null&&StringUtils.isNotBlank(supplierExtend.getExpress())){
			String[] strs = supplierExtend.getExpress().split(",");
			for(String expressId:strs){
				Express express = expressService.findById(Integer.valueOf(expressId));
				if(express!=null && express.getEstate()==1){ //过滤可以显示的物流公司
					list.add(express);
				}
				
			}
		}
		return list;
	}
}
