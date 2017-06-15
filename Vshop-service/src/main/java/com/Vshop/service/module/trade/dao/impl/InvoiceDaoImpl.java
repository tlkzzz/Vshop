package com.Vshop.service.module.trade.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Invoice;
import com.Vshop.service.module.trade.dao.InvoiceDao;
import com.Vshop.service.module.trade.dao.mapper.InvoiceMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * 发票dao
 * @author cgl
 * 2015年08月14日16:14:55
 */
@Repository
public class InvoiceDaoImpl implements InvoiceDao{

	@Autowired
	private InvoiceMapper invoiceMapper;
	
	@Override
	public void save(Invoice invoice) {
		// TODO Auto-generated method stub
		invoiceMapper.save(invoice);
	}

	@Override
	public void delete(Integer invId) {
		// TODO Auto-generated method stub
		invoiceMapper.delete(invId);
	}
	
	@Override
	public void deleteByCondition(Invoice invoice){
		invoiceMapper.deleteByCondition(invoice);
	}

	@Override
	public void update(Invoice invoice) {
		// TODO Auto-generated method stub
		invoiceMapper.update(invoice);
	}

	@Override
	public Invoice findByInvId(Integer invId) {
		// TODO Auto-generated method stub
		return invoiceMapper.findByInvId(invId);
	}

	@Override
	public int findPageCount(Invoice invoice) {
		// TODO Auto-generated method stub
		return invoiceMapper.findPageCount(invoice);
	}

	@Override
	public List<Invoice> findPageList(Pager pager) {
		// TODO Auto-generated method stub
		return invoiceMapper.findPageList(pager);
	}
	
	/**
	 * 查询列表页不分页
	 * @return
	 */
	@Override
	public List<Invoice> findInvoiceList(Invoice invoice){
		return invoiceMapper.findInvoiceList(invoice);
	}
	
	/**
	 * 修改当前用户下的发票都为不是默认
	 * @param memeberId
	 */
	public void updateDef(Integer memberId){
		invoiceMapper.updateDef(memberId);
	}
}
