package com.Vshop.service.module.goods.dao;

import java.util.List;

import com.Vshop.core.entity.base.ConsumerCodeSend;
import com.Vshop.service.utils.page.Pager;

public interface ConsumerCodeSendDao {

	/**
	 * 发码记录
	 * @param consumerCodeSend
	 */
	Integer save(ConsumerCodeSend consumerCodeSend);
	
	/**
	 * 根据Id更新状态及更新状态时间
	 * @param consumerCodeSend
	 */
	void updateById(ConsumerCodeSend consumerCodeSend);
	
	/**
	 * 根据课程码更新状态及更新状态时间
	 * @param consumerCodeSend
	 */
	void updateByCode(ConsumerCodeSend consumerCodeSend);
	
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
