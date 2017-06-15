package com.Vshop.service.module.trade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.Invoice;
import com.Vshop.service.module.trade.dao.InvoiceDao;
import com.Vshop.service.module.trade.service.InvoiceService;
import com.Vshop.service.utils.page.Pager;

/**
 * 发票service
 * @author cgl
 * 2015年08月14日16:14:55
 */
@Service
public class InvoiceServiceImpl implements InvoiceService{
	
	@Autowired
	private InvoiceDao invoiceDao;

	@Override
	public void update(Invoice invoice) {
		// TODO Auto-generated method stub
		invoiceDao.update(invoice);
	}
	
	/**
	 * 删除
	 * @param invId
	 */
	public void delete(Integer invId){
		invoiceDao.delete(invId);
	}
	
	/**
	 * 修改
	 * 描述:这个方法修改的是普通发票的信息
	 * invTitle:发票抬头的数组
	 * invContent:发票内容
	 * invTitleDef:默认的发票抬头,应是invTitle数组里面的某一个值
	 */
	@Override
	public void updateCommonInv(String [] invTitles, String invContent, Integer memberId) {
		
		Invoice invoice = new Invoice();
		
		/**设置会员*/
		invoice.setMemberId(memberId);
		
		/**设置发票的invState,设置为1,代表普通发票*/
		invoice.setInvState("1");
		
		/**
		 * 将该会员下的所有普通发票数据删除
		 */
		invoiceDao.deleteByCondition(invoice);

		
		/**设置发票详细内容*/
		invoice.setInvContent(invContent);
		
		/**将数组中的发票抬头循环遍历,放入实体类中,保存*/
		for(String invTitle : invTitles){
			/**设置抬头*/
			invoice.setInvTitle(invTitle);
			/**
			 * 保存
			 */
			invoiceDao.save(invoice);
		}
	}
	
	/**
	 * 修改
	 * 描述:这个方法修改的是增值税发票的信息
	 */
	public Invoice updateVATInv(Invoice invoice ,Integer memberId){
		
		Invoice invoiceCondition = new Invoice();
		
		/**设置会员*/
		invoiceCondition.setMemberId(memberId);
		
		/**设置发票的invState,设置为1,代表普通发票*/
		invoiceCondition.setInvState("2");
		
		/**
		 * 将该会员下的所有增值税发票数据删除
		 */
		invoiceDao.deleteByCondition(invoiceCondition);
		
		invoice.setInvState("2");
		
		invoice.setMemberId(memberId);
		
		/**
		 * 保存
		 */
		invoiceDao.save(invoice);
		return invoice;
	}
	

	/**
	 * 设置为默认值
	 */
	@Override
	public void updateDef(Integer invId) {
		Invoice invoice = new Invoice();
		invoice.setInvId(invId);
		invoice.setIsDefault(1);
		update(invoice);
	}
	
	@Override
	public Invoice findByInvId(Integer invId) {
		// TODO Auto-generated method stub
		return invoiceDao.findByInvId(invId);
	}

	@Override
	public int findPageCount(Invoice invoice) {
		// TODO Auto-generated method stub
		return invoiceDao.findPageCount(invoice);
	}

	@Override
	public List<Invoice> findPageList(Pager pager) {
		// TODO Auto-generated method stub
		return invoiceDao.findPageList(pager);
	}
	
	/**
	 * 查询列表页不分页
	 * @return
	 */
	@Override
	public List<Invoice> findInvoiceList(Invoice invoice){
		return invoiceDao.findInvoiceList(invoice);
	}
	
	/**
	 * 保存发票抬头
	 * @param memberId 用户id
	 * @param title 发票抬头
	 */
	@Override
	public Invoice saveInvoiceTitle(Integer memberId,String title){
		//将用户发票下所有发票去掉默认
		invoiceDao.updateDef(memberId);
		//新建发票信息
		Invoice invoice = new Invoice();
		invoice.setMemberId(memberId); //用户id
		invoice.setInvTitle(title); //发票抬头
		invoice.setInvState("1"); //发票类型,设置普通发票
		invoice.setIsDefault(1); //设置为默认发票
		invoice.setInvContent("2"); //设置发票类型为明细
		//保存发票信息
		invoiceDao.save(invoice); 
		return invoice;
	}
	
	/**
	 * 删除其他发票默认信息
	 * @param memberId
	 */
	public void deleteOrtherDef(Integer memberId){
		invoiceDao.updateDef(memberId);
	}
	
	/**
	 * api接口添加发票信息
	 * @param memberId 用户id
	 * @param invTitle 发票抬头
	 * @param invContent 发票内容
	 * @param invState 发票类型
	 * @return
	 */
	public Invoice saveInvoiceForApi(Integer memberId, String invTitle, String invContent, String invState){
		//将用户发票下所有发票去掉默认
		invoiceDao.updateDef(memberId);
		//新建发票信息
		Invoice invoice = new Invoice();
		invoice.setMemberId(memberId); //用户id
		invoice.setInvTitle(invTitle); //发票抬头
		invoice.setInvState(invState); //发票类型
		invoice.setIsDefault(1); //设置为默认发票
		invoice.setInvContent(invContent); //设置发票类型
		//保存发票信息
		invoiceDao.save(invoice); 
		return invoice;
	}
}
