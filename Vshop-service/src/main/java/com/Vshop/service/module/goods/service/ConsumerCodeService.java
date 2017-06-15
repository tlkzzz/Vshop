package com.Vshop.service.module.goods.service;

import java.util.List;

import com.Vshop.core.entity.base.ConsumerCode;
import com.Vshop.service.utils.page.Pager;

public interface ConsumerCodeService {

	/**
     * 生成商品消费码，然后保存
     * @param goodsConsumerCode
     */
    void save(ConsumerCode consumerCode);
    
    /**
     * 直接保存，没有添加任何逻辑
     * @param consumerCode
     */
    void directSave(ConsumerCode consumerCode);
    
    /**
     * 修改商品码状态
     * @param goodsConsumerCode
     */
    public void update(ConsumerCode consumerCode);

	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
    public List<ConsumerCode> findPageList(Pager pager);
    
    public Integer findPageListCount(Pager pager);
    
    /**
     * 根据主键查询
     * @param consumerCodeId
     * @return
     */
	public ConsumerCode findById(Integer consumerCodeId);

    /**
     * 根据主键删除
     * @param consumerCodeId
     */
	public void deleteById(Integer consumerCodeId);
  
	
	/**
	 * 根据会员Id查询列表
	 * @param memberId
	 * @return
	 */
	public List<ConsumerCode> findListByMemberId(Integer memberId);

	
	
}
