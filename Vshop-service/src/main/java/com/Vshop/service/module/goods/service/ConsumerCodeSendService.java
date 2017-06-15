package com.Vshop.service.module.goods.service;

import java.util.List;

import com.Vshop.core.entity.base.ConsumerCodeSend;
import com.Vshop.service.utils.page.Pager;

public interface ConsumerCodeSendService {

	/**
	 * 发码
	 * @param consumerCodeSend
	 */
	ConsumerCodeSend sendCode(ConsumerCodeSend consumerCodeSend) throws Exception;
	
	/**
	 * 收码
	 * @param consumerCodeSend
	 * @return
	 * @throws Exception
	 */
	ConsumerCodeSend receiveCode(ConsumerCodeSend consumerCodeSend) throws Exception;
	
	/**
	 * 撤回作废码
	 * @param consumerCodeSend
	 * @return
	 * @throws Exception
	 */
	public ConsumerCodeSend recallCode(ConsumerCodeSend consumerCodeSend) throws Exception;
	
	/**
	 * 根据Id更新状态及更新状态时间
	 * @param consumerCodeSend
	 */
	boolean updateById(ConsumerCodeSend consumerCodeSend);
	
	/**
	 * 根据课程码更新状态及更新状态时间
	 * @param consumerCodeSend
	 */
	boolean updateByCode(ConsumerCodeSend consumerCodeSend);
	
	/**
	 * 根据Id查询课程码
	 * @param consumerCodeId
	 * @return
	 */
	ConsumerCodeSend findById(Integer consumerCodeId);
	
	/**
	 * 根据课程码查询课程码
	 * @param consumerCodeBunch
	 * @return
	 */
	ConsumerCodeSend findByCode(String consumerCodeBunch);
	
	/**
	 * 分页查询收发码
	 * @param pager
	 * @return
	 */
	List<ConsumerCodeSend> findPageList(Pager pager);
	
	/**
	 * 根据consumerCodeSend查询
	 * @param consumerCodeSend
	 * @return 返回前1000条
	 */
	public List<ConsumerCodeSend> findList(ConsumerCodeSend consumerCodeSend);
	
	/**
	 * 根据Id查询记录数
	 * @param consumerCodeId
	 * @return
	 */
	Integer countById(Integer consumerCodeId);
	
	/**
	 * 根据课程码查询记录数
	 * @param consumerCodeBunch
	 * @return
	 */
	Integer countByCode(String consumerCodeBunch);
	
}
