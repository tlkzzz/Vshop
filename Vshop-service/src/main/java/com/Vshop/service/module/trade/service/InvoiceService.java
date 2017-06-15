package com.Vshop.service.module.trade.service;

import java.util.List;

import com.Vshop.core.entity.base.Invoice;
import com.Vshop.service.utils.page.Pager;

/**
 * 发票service
 * @author cgl
 * 2015年08月14日16:14:55
 */
public interface InvoiceService {
	
	/**
	 * 修改
	 * 描述:这个方法只能修改某条数据
	 */
	void update(Invoice invoice);
	
	/**
	 * 删除
	 * @param invId
	 */
	void delete(Integer invId);
	
	/**
	 * 修改
	 * 描述:这个方法修改的是普通发票的信息
	 * invTitle:发票抬头的数组
	 * invContent:发票内容
	 * invTitleDef:默认的发票抬头,应是invTitle数组里面的某一个值
	 * memberId:会员id
	 */
	void updateCommonInv(String [] invTitles, String invContent, Integer memberId);
	
	/**
	 * 修改
	 * 描述:这个方法修改的是增值税发票的信息
	 */
	Invoice updateVATInv(Invoice invoice, Integer memberId);
	
	/**
	 * 设置为默认值
	 */
	void updateDef(Integer invId);
	
	/**
	 * 通过主键id查找
	 */
	Invoice findByInvId(Integer invId);
	
	/**
	 * 总条数
	 */
	int findPageCount(Invoice invoice);
	
	/**
	 * 查询列表页
	 */
	List<Invoice> findPageList(Pager pager);
	
	/**
	 * 查询列表页不分页
	 * @return
	 */
	List<Invoice> findInvoiceList(Invoice invoice);
	
	/**
	 * 保存发票抬头
	 * @param memberId 用户id
	 * @param title 发票抬头
	 */
	Invoice saveInvoiceTitle(Integer memberId,String title);
	
	/**
	 * 删除其他发票默认信息
	 * @param memberId
	 */
	void deleteOrtherDef(Integer memberId);
	
	/**
	 * api接口添加发票信息
	 * @param memberId 用户id
	 * @param invTitle 发票抬头
	 * @param invContent 发票内容
	 * @param invState 发票类型
	 * @return
	 */
	Invoice saveInvoiceForApi(Integer memberId, String invTitle, String invContent, String invState);
}
