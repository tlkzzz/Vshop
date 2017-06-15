package com.Vshop.service.module.trade.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.TransportSupplier;
import com.Vshop.service.module.trade.dao.TransportSupplierDao;
import com.Vshop.service.module.trade.dao.mapper.TransportSupplierMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * 
 * @项目名称：Vshop-seller
 * @类名称：TransportSupplierDaoImpl
 * @类描述： 运费模板
 * @创建人：shining
 * @创建时间：2014年12月7日 下午10:48:12
 * @修改人：shining
 * @修改时间：2014年12月7日 下午10:48:12
 * @修改备注：
 * @version
 * 
 */
@Repository
public class TransportSupplierDaoImpl implements TransportSupplierDao {
	@Resource
	private TransportSupplierMapper transportSupplierMapper;

	/**
	 * 查询列表
	 */
	@Override
	public List<TransportSupplier> queryList(Pager pager) {
		return transportSupplierMapper.queryList(pager);
	}

	@Override
	public TransportSupplier findById(Integer id) {
		return transportSupplierMapper.findById(id);
	}

	@Override
	public void save(TransportSupplier transport) {
		transportSupplierMapper.save(transport);
	}

	@Override
	public void delete(Integer id) {
		transportSupplierMapper.delete(id);
	}

	@Override
	public void update(TransportSupplier transport) {
		transportSupplierMapper.update(transport);
	}
	
	/**
	 * 通过店铺id 获得当前的默认运费模板
	 * @param supplierId
	 * @return
	 */
	public TransportSupplier getDefTransportBySupplierId(Integer supplierId){
		return transportSupplierMapper.getDefTransportBySupplierId(supplierId);
	}
	
	
	
}
